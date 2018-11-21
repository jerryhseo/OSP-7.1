/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.kisti.osp.spyglass.service.impl;

import aQute.bnd.annotation.ProviderType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.apache.commons.lang3.SerializationUtils;

import com.kisti.osp.spyglass.constants.ScienceAppStages;
import com.kisti.osp.spyglass.constants.ScienceAppUploadTypes;
import com.kisti.osp.spyglass.exception.NoSuchScienceAppException;
import com.kisti.osp.spyglass.exception.UnknownUploadTypeException;
import com.kisti.osp.spyglass.model.InputPorts;
import com.kisti.osp.spyglass.model.LogPorts;
import com.kisti.osp.spyglass.model.OutputPorts;
import com.kisti.osp.spyglass.model.ScienceApp;
import com.kisti.osp.spyglass.model.impl.ScienceAppImpl;
import com.kisti.osp.spyglass.service.base.ScienceAppLocalServiceBaseImpl;
import com.kisti.osp.util.constants.OSPPropertyKeys;
import com.kisti.osp.util.file.OSPFileUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;

/**
 * The implementation of the science app local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.kisti.osp.spyglass.service.ScienceAppLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Jerry H. Seo
 * @see ScienceAppLocalServiceBaseImpl
 * @see com.kisti.osp.spyglass.service.ScienceAppLocalServiceUtil
 */
@ProviderType
public class ScienceAppLocalServiceImpl extends ScienceAppLocalServiceBaseImpl {
	public static String VALID_NAME_EXPR = "[a-zA-Z][a-zA-Z0-9 \\-\\.\\+\\_]+";
	public static String VALID_VERSION_EXPR = "[1-9][0-9]*\\.(0|[1-9][0-9]*)\\.(0|[1-9][0-9]*)";

	
	/**
	 * Creates a ScienceApp instance with specified name and version. The new
	 * instance created is not yet saved in the database. Use addScienceApp() to
	 * save the instance.
	 * 
	 * @param appName:
	 *          ScienceApp name to be created
	 * @param appVersion:
	 *          ScienceApp version to be created
	 * @param sc:
	 *          ServiceContext instance for ScienceApp
	 * @return If appName is not follows naming rules or already exists in the
	 *         database, returns null. If appVersion is not follows versioning
	 *         rules, returns null. Otherwise returns a ScienceApp instance with
	 *         initialized data.
	 * 
	 *         Some attributes of the returned instance are set initial value as
	 *         followings: -stage: ScienceAppConstants.EMPTY -authorId: current
	 *         user id of service context instance -createDate: date created of
	 *         service context instance -modifiedDate: date created of service
	 *         context instance -userId: current user id of service context
	 *         instance -recentModifierId: current user id of service context
	 *         instance -groupId: scope group id of service context instance
	 *         -companyId: company id of service context instance
	 * @throws SystemException
	 * @throws PortalException 
	 *
	 * @see com.kisti.science.platform.service.ScienceAppLocalService#createScienceApp(java.lang.String,
	 *      java.lang.String, com.liferay.portal.service.ServiceContext)
	 */
	public ScienceApp createScienceApp(String appName, String appVersion, ServiceContext sc)
		throws SystemException{
		if(!this.verifyScienceAppName(appName))
			return null;
		if(this.existAppName(appName)){
			if(!this.verifyVersionNumber(appName, appVersion))
				return null;
		}

		ScienceApp newApp = null;
		long newAppId = super.counterLocalService.increment();
		newApp = super.scienceAppPersistence.create(newAppId);

		newApp.setName(appName);
		newApp.setVersion(appVersion);
		newApp.setStage(ScienceAppStages.EMPTY.toString());
		newApp.setAuthorId(sc.getUserId());
		newApp.setCreateDate(sc.getCreateDate());
		newApp.setModifiedDate(sc.getModifiedDate());
		newApp.setUserId(sc.getUserId());
		newApp.setGroupId(sc.getScopeGroupId());
		newApp.setCompanyId(sc.getCompanyId());

		return newApp;
	}
  
	/**
	 * Saves the specified ScienceApp instance to database.
	 * 
	 * @param scienceApp:
	 *          ScienceApp instance to be saved.
	 * @param sc:
	 *          service context of the ScienceApp class
	 * @return ScienceApp instance saved.
	 * @throws SystemException
	 * @throws PortalException
	 *
	 * @see com.kisti.science.platform.service.ScienceAppLocalService#addScienceApp(com.kisti.science.platform.model.ScienceApp,
	 *      com.liferay.portal.service.ServiceContext)
	 */
	public ScienceApp addScienceApp(ScienceApp scienceApp, ServiceContext sc) throws SystemException,
		PortalException{
		super.addScienceApp(scienceApp);
		
		super.resourceLocalService.addResources(
				scienceApp.getCompanyId(), 
				scienceApp.getGroupId(), 
				scienceApp.getUserId(), 
				ScienceApp.class.getName(), 
				scienceApp.getScienceAppId(), 
				false, 
				true, 
				true);

		AssetEntry assetEntry = super.assetEntryLocalService.updateEntry(
				scienceApp.getUserId(), 
				scienceApp.getGroupId(), 
				scienceApp.getCreateDate(), 
				scienceApp.getModifiedDate(), 
				ScienceApp.class.getName(),
				scienceApp.getScienceAppId(), 
				scienceApp.getUuid(), 
				0, 
				sc.getAssetCategoryIds(), 
				sc.getAssetTagNames(),
				true,
				true,
				null, 
				null, 
				null, 
				null,
				ContentTypes.TEXT_HTML, 
				scienceApp.getName(), 
				scienceApp.getTitle(), 
				scienceApp.getTitle(), 
				null, 
				null, 
				0, 
				0,
				null);

		assetLinkLocalService.updateLinks(
				scienceApp.getUserId(), 
				assetEntry.getEntryId(), 
				sc.getAssetLinkEntryIds(), 
				AssetLinkConstants.TYPE_RELATED);

		return scienceApp;
	}

	public void setScienceAppInputPorts(long scienceAppId, String inputPorts) throws SystemException{
		super.inputPortsLocalService.addInputPorts(scienceAppId, inputPorts);
	}

	public String getScienceAppInputPorts(long scienceAppId) throws SystemException, PortalException{
		InputPorts ports = super.inputPortsLocalService.getInputPorts(scienceAppId);
		return ports.getInputPorts();
	}
	
	public void setScienceAppLogPorts(long scienceAppId, String logPorts) throws SystemException, PortalException{
		super.logPortsLocalService.addLogPorts(scienceAppId, logPorts);
	}

	public String getScienceAppLogPorts(long scienceAppId) throws SystemException, PortalException{
		LogPorts ports = super.logPortsLocalService.getLogPorts(scienceAppId);
		return ports.getLogPorts();
	}

	public void setScienceAppOutputPorts(long scienceAppId, String outputPorts) throws SystemException{
		super.outputPortsLocalService.addOutputPorts(scienceAppId, outputPorts);
	}

	public String getScienceAppOutputPorts(long scienceAppId) throws SystemException, PortalException{
		OutputPorts ports = super.outputPortsLocalService.getOutputPorts(scienceAppId);
		return ports.getOutputPorts();
	}

	/**
	 * Verifies ScienceApp name if the name follows specified naming rules and
	 * there is no science app in the database already.
	 * 
	 * @param appName:
	 *          science app name to be tesed.
	 * @return true if the name follows naming rules and brand new. false,
	 *         Otherwise.
	 * @throws SystemException
	 *
	 * @see com.kisti.science.platform.service.ScienceAppLocalService#verifyScienceAppName(java.lang.String,
	 *      long)
	 */
	public boolean verifyScienceAppName(String appName) throws SystemException{
		if( !appName.matches(VALID_NAME_EXPR) )
			return false;

		return !this.existAppName(appName);
	}

	/**
	 * Test if there is a science app name in the database already.
	 * 
	 * @param appName:
	 *          science app name to be tesed.
	 * @return true if the app name already exist in the database, false,
	 *         otherwise
	 * @throws SystemException
	 * 
	 * @see com.kisti.science.platform.service.ScienceAppLocalService#existAppName(java.lang.String)
	 */
	public boolean existAppName(String appName) throws SystemException{
		if(super.scienceAppPersistence.countByName(appName) > 0)
			return true;
		else
			return false;
	}

	/**
	 * Test if there is an science app with the specified name and version in the
	 * database.
	 * 
	 * @param appName:
	 *          science app name to be tesed.
	 * @param appVersion:
	 *          science app version to be tesed.
	 * @return true if a science app with the name and the version already exist
	 *         in the database, false, otherwise.
	 * @throws SystemException
	 * 
	 * @see com.kisti.science.platform.service.ScienceAppLocalService#existApp(java.lang.String,
	 *      java.lang.String)
	 */
	public boolean existApp(String appName, String appVersion) throws SystemException{
		if(super.scienceAppPersistence.countByNameVersion(appName, appVersion) > 0)
			return true;
		else
			return false;
	}

	/**
	 * Get the science app's latest version
	 * 
	 * @param appName:
	 *          science app name
	 * @return the latest version science app instance.
	 * @throws SystemException
	 * 
	 * @see com.kisti.science.platform.service.ScienceAppLocalService#getLatestVersion(java.lang.String)
	 */
	public ScienceApp getLatestVersion(String appName) throws SystemException{
		ScienceApp app = null;
		OrderByComparator orderBy = OrderByComparatorFactoryUtil.create(ScienceAppImpl.TABLE_NAME, "createDate",
			false);
		app = super.scienceAppPersistence.fetchByName_First(appName, orderBy);
		return app;
	}

	/**
	 * Verify version number of a science app. Version number of a science app
	 * should be consisted of 3 sections, {major}.{sub}.{minor}. Major section
	 * number should be increased when a science app changes or added its
	 * architecture or major functions. Sub section number should be increased
	 * when the science app changes functionality. Minor section number should be
	 * increased when the science app fixes errors. Each section must be integer
	 * and be lager than before.
	 * 
	 * @param appName:
	 *          science app name to be verified.
	 * @param appVersion:
	 *          science app version to be verified.
	 * @return true if the version number follows naming rules and valid. false,
	 *         Otherwise.
	 * @throws SystemException
	 * 
	 * @see com.kisti.science.platform.service.ScienceAppLocalService#verifyVersionNumber(java.lang.String,
	 *      java.lang.String)
	 */
	public boolean verifyVersionNumber(String appName, String appVersion) throws SystemException{
		if(!appName.matches(VALID_VERSION_EXPR))
			return false;

		ScienceApp app = this.getLatestVersion(appName);
		String[] newVersion = appVersion.split("\\.");
		// System.out.println("Saved version: "+app.getVersion());
		String[] version = app.getVersion().split("\\.");
		// System.out.println("version length:
		// "+version.length+"-"+newVersion.length);

		for(int i = 0; i < newVersion.length && i < version.length; i++){
			// System.out.println("Level["+i+"]: "+ newVersion[i]+":"+version[i]);
			if(Integer.parseInt(newVersion[i]) < Integer.parseInt(version[i]))
				return false;
			else if(Integer.parseInt(newVersion[i]) > Integer.parseInt(version[i]))
				return true;
		}
		// System.out.println("All verified failed.....");

		return false;
	}

	/**
	 * Delete a science app by its id
	 * 
	 * @param scienceAppId:
	 *          science app id to be deleted
	 * @return ScienceApp instance deleted.
	 * @throws SystemException
	 * @throws PortalException
	 *
	 * @see com.kisti.science.platform.service.base.ScienceAppLocalServiceBaseImpl#deleteScienceApp(long)
	 */
	public ScienceApp deleteScienceApp(long scienceAppId) throws SystemException, PortalException{
		this.cleanIntegratedData(scienceAppId);
		return this.deleteScienceApp(super.getScienceApp(scienceAppId));
	}

	/**
	 * Delete a science app by its instance
	 * 
	 * @param scienceApp:
	 *          science app instance to be tesed.
	 * @return ScienceApp instance deleted.
	 * @throws SystemException
	 * @throws PortalException
	 *
	 * @see com.kisti.science.platform.service.base.ScienceAppLocalServiceBaseImpl#deleteScienceApp(com.kisti.science.platform.model.ScienceApp)
	 */
	public ScienceApp deleteScienceApp(ScienceApp scienceApp) throws SystemException, PortalException{
		this.cleanIntegratedData(scienceApp.getScienceAppId());
		super.resourceLocalService.deleteResource(scienceApp.getCompanyId(), ScienceApp.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, scienceApp.getScienceAppId());

		AssetEntry assetEntry = assetEntryLocalService.fetchEntry(ScienceApp.class.getName(), scienceApp
			.getScienceAppId());

		assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

		assetEntryLocalService.deleteEntry(ScienceApp.class.getName(), scienceApp.getScienceAppId());
		super.deleteScienceApp(scienceApp);

		Indexer indexer = IndexerRegistryUtil.getIndexer(ScienceApp.class);
		indexer.reindex(scienceApp);

		return scienceApp;
	}

	public void deleteAllScienceApps() throws SystemException{
		super.scienceAppPersistence.removeAll();
	}
	
	public List<ScienceApp> getAll() throws SystemException{
		return super.scienceAppPersistence.findAll();
	}
	
	public int countScienceAppsByGroupId( long groupId ){
		return super.scienceAppPersistence.countByGroupId(groupId);
	}

	public List<ScienceApp> getScienceAppsByGroupId( long groupId ){
		return super.scienceAppPersistence.findByGroupId(groupId);
	}
	
	public List<ScienceApp> getScienceAppsByGroupId( long groupId, int start, int end ){
		return super.scienceAppPersistence.findByGroupId(groupId, start, end);
	}

	/**
	 * Update a science app
	 * 
	 * @param scienceApp:
	 *          science app instance to be updated.
	 * @param sc:
	 *          ServiceContext instance for the ScienceApp class.
	 * @return ScienceApp instance updated.
	 * @throws SystemException
	 * @throws PortalException
	 *
	 * @see com.kisti.science.platform.service.ScienceAppLocalService#updateScienceApp(com.kisti.science.platform.model.ScienceApp,
	 *      com.liferay.portal.service.ServiceContext)
	 */
	public ScienceApp updateScienceApp(ScienceApp scienceApp, ServiceContext sc) throws SystemException,
		PortalException{

		long userId = sc.getUserId();

		AssetEntry assetEntry = super.assetEntryLocalService.updateEntry(
				scienceApp.getUserId(), 
				scienceApp.getGroupId(), 
				scienceApp.getCreateDate(), 
				scienceApp.getModifiedDate(), 
				ScienceApp.class.getName(),
				scienceApp.getScienceAppId(), 
				scienceApp.getUuid(), 
				0, 
				sc.getAssetCategoryIds(), 
				sc.getAssetTagNames(),
				true,
				true,
				null, 
				null, 
				null, 
				null,
				ContentTypes.TEXT_HTML, 
				scienceApp.getName(), 
				scienceApp.getTitle(), 
				scienceApp.getTitle(), 
				null, 
				null, 
				0, 
				0,
				null);

		assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(), sc.getAssetLinkEntryIds(),
			AssetLinkConstants.TYPE_RELATED);

		Indexer indexer = IndexerRegistryUtil.getIndexer(ScienceApp.class);
		indexer.reindex(scienceApp);

		return super.updateScienceApp(scienceApp);
	}

	/*
	 * From this line, the APIs are just call persistence APIs for convenience.
	 */
	public List<ScienceApp> getScienceAppListByStatus(int status) throws SystemException{
		return super.scienceAppPersistence.findByStatus(status);
	}

	public List<ScienceApp> getScienceAppListByStage(String stage) throws SystemException{
		return super.scienceAppPersistence.findByStage(stage);
	}

	public List<ScienceApp> getScienceAppListByAuthorIdStatus(long authorId, int status)
		throws SystemException{
		return super.scienceAppPersistence.findByAuthorId_S(authorId, status);
	}

	public List<ScienceApp> getScienceAppListByAuthorIdStatus(long authorId, int appStatus, int start, int end)
		throws SystemException{
		return super.scienceAppPersistence.findByAuthorId_S(authorId, appStatus, start, end);
	}

	public List<ScienceApp> getScienceAppListByAuthorIdAppType(long authorId, String appType)
		throws SystemException{
		return super.scienceAppPersistence.findByAuthorIdAppType(authorId, appType);
	}

	public List<ScienceApp> getScienceAppListByAuthorIdAppType(long authorId, String appClass, int start,
		int end) throws SystemException{
		return super.scienceAppPersistence.findByAuthorIdAppType(authorId, appClass, start, end);
	}

	public List<ScienceApp> getScienceAppListByAuthorId(long authorId) throws SystemException{
		return super.scienceAppPersistence.findByAuthorId(authorId);
	}

	public List<ScienceApp> getScienceAppListByAuthorId(long authorId, int start, int end)
		throws SystemException{
		return super.scienceAppPersistence.findByAuthorId(authorId, start, end);
	}

	public int countScienceAppsByAuthorId(long authorId) throws SystemException{
		return super.scienceAppPersistence.countByAuthorId(authorId);
	}

	public List<ScienceApp> getScienceAppListByOpenLevel(String openLevel) throws SystemException{
		return super.scienceAppPersistence.findByOpenLevel(openLevel);
	}

	public List<ScienceApp> getScienceAppListByOpenLevel(String openLevel, int start, int end)
		throws SystemException{
		return super.scienceAppPersistence.findByOpenLevel(openLevel, start, end);
	}

	/*
	public List<ScienceApp> getScienceAppListByManagerId(long managerId) throws SystemException,
		PortalException{
		long[] scienceAppIds = super.scienceAppManagerLocalService.getScienceAppIdsByManagerId(managerId);
		return this.getScienceAppListByScienceAppIds(scienceAppIds);
	}

	public List<ScienceApp> getScienceAppListByManagerId(long managerId, int start, int end)
		throws SystemException, PortalException{
		long[] scienceAppIds = super.scienceAppManagerLocalService.getScienceAppIdsByManagerId(managerId, start,
			end);
		return this.getScienceAppListByScienceAppIds(scienceAppIds);
	}

	public int countScienceAppsByManagerId(long managerId) throws SystemException{
		return super.scienceAppManagerLocalService.countScienceAppIdsByManagerId(managerId);
	}

	public long[] getScienceAppManagerIds(long scienceAppId) throws SystemException{
		return super.scienceAppManagerLocalService.getManagerIdsByScienceAppId(scienceAppId);
	}

	public long[] getScienceAppManagerIds(long scienceAppId, int start, int end) throws SystemException{
		return super.scienceAppManagerLocalService.getManagerIdsByScienceAppId(scienceAppId, start, end);
	}

	public int countScienceAppManagers(long scienceAppId) throws SystemException{
		return super.scienceAppManagerLocalService.countManagersByScienceAppId(scienceAppId);
	}

	public List<ScienceApp> getScienceAppListByCategoryId(long categoryId) throws SystemException,
		PortalException{
		long[] scienceAppIds = super.scienceAppCategoryLinkLocalService.getScienceAppIdsByCategoryId(categoryId);
		return this.getScienceAppListByScienceAppIds(scienceAppIds);
	}

	public List<ScienceApp> getScienceAppListByCategoryId(long categoryId, int start, int end)
		throws SystemException, PortalException{
		long[] scienceAppIds = super.scienceAppCategoryLinkLocalService.getScienceAppIdsByCategoryId(categoryId,
			start, end);
		return this.getScienceAppListByScienceAppIds(scienceAppIds);
	}

	public int countScienceAppsByCategoryId(long categoryId) throws SystemException{
		return super.scienceAppCategoryLinkLocalService.countScienceAppsByCategoryId(categoryId);
	}

	public long[] getScienceAppCategoryIds(long scienceAppId) throws SystemException{
		return super.scienceAppCategoryLinkLocalService.getCategoryIdsByScienceAppId(scienceAppId);
	}

	public long[] getScienceAppCategoryIds(long scienceAppId, int start, int end) throws SystemException{
		return super.scienceAppCategoryLinkLocalService.getCategoryIdsByScienceAppId(scienceAppId, start, end);
	}

	public int countScienceAppCategories(long scienceAppId) throws SystemException{
		return super.scienceAppCategoryLinkLocalService.countCategoriesByScienceAppId(scienceAppId);
	}

	public void assignScienceAppToCategories(long scienceAppId, long[] categoryIds) throws SystemException{
		for(long categoryId : categoryIds){
			this.assignScienceAppToCategory(scienceAppId, categoryId);
		}
	}

	public void assignScienceAppToCategory(long scienceAppId, long categoryId) throws SystemException{
		super.scienceAppCategoryLinkLocalService.addScienceAppCategory(categoryId, scienceAppId);
	}

	public void assignManagersToScienceApp(long scienceAppId, long[] managerIds) throws SystemException{
		for(long managerId : managerIds){
			this.assignManagerToScienceApp(scienceAppId, managerId);
		}
	}

	public void assignManagerToScienceApp(long scienceAppId, long managerId) throws SystemException{
		super.scienceAppManagerLocalService.addScienceAppManager(managerId, scienceAppId);
	}
	 */

	/**
	 * Get path of the executable binary file. The full path can be obtained by
	 * adding base directory for science apps.
	 * 
	 * @param scienceAppId
	 * @return String of the path.
	 * @throws PortalException
	 * @throws SystemException
	 */
	public String getScienceAppBinPath(long scienceAppId) throws PortalException, SystemException{
		ScienceApp scienceApp = super.getScienceApp(scienceAppId);
		String rootDir = OSPPropertyKeys.getSpyGlassAppsDirPath();
		String bin = OSPPropertyKeys.getSpyGlassAppsBinDir();
		
		Path path = Paths.get(rootDir, scienceApp.getName(), scienceApp.getVersion(), bin);
		return path.toString();
	}

	/**
	 * Get path of the source file. The full path can be obtained by adding base
	 * directory for science apps.
	 * 
	 * @param scienceAppId
	 * @return String of the path.
	 * @throws PortalException
	 * @throws SystemException
	 */
	public String getScienceAppSrcPath(long scienceAppId) throws PortalException, SystemException{
		ScienceApp scienceApp = super.getScienceApp(scienceAppId);
		String rootDir = OSPPropertyKeys.getSpyGlassAppsDirPath();
		String src = OSPPropertyKeys.getSpyGlassAppsSrcDir();
		
		Path path = Paths.get(rootDir, scienceApp.getName(), scienceApp.getVersion(), src);
		return path.toString();
	}
	
	public String getScienceAppDataPath(long scienceAppId) throws PortalException, SystemException{
		ScienceApp scienceApp = super.getScienceApp(scienceAppId);
		String rootDir = OSPPropertyKeys.getSpyGlassAppsDirPath();
		String data = OSPPropertyKeys.getSpyGlassAppsDataDir();
		
		Path path = Paths.get(rootDir, scienceApp.getName(), scienceApp.getVersion(), data);
		return path.toString();
	}

	/**
	 * Counts all science apps in the database.
	 * 
	 * @return int - count
	 */
	public int countAllScienceApps() throws SystemException{
		return super.scienceAppPersistence.countAll();
	}


	/**
	 * For reserving data integration
	 *
	 * @param scienceAppId
	 *          the science app id
	 * @throws SystemException
	 *           the system exception
	 * @throws PortalException
	 */
	protected void cleanIntegratedData(long scienceAppId) throws SystemException, PortalException{
		super.inputPortsLocalService.deleteInputPorts(scienceAppId);
		super.logPortsLocalService.deleteLogPorts(scienceAppId);
		super.outputPortsLocalService.deleteOutputPorts(scienceAppId);
		super.layoutLocalService.deleteLayout(scienceAppId);
//		super.scienceAppManagerPersistence.removeByAppId(scienceAppId);
		
		
	}

	private List<ScienceApp> getScienceAppListByScienceAppIds(long[] scienceAppIds) throws PortalException,
		SystemException{
		List<ScienceApp> apps = new ArrayList<ScienceApp>();
		for(long id :  scienceAppIds){
			apps.add(this.getScienceApp(id));
		}

		return apps;
	}

	
	public ScienceApp copyScienceApp(long scienceAppId, ServiceContext sc) throws SystemException, PortalException{
		ScienceApp app = super.getScienceApp(scienceAppId);
		
		ScienceApp clone = SerializationUtils.clone(app);
		
		clone.setScienceAppId(super.counterLocalService.increment());
		clone.setName("");
		clone.setVersion("");
		
		this.addScienceApp(clone, sc);
		
		return clone;
	}
	
	
	public void uploadScienceApp(String appName, String appVersion, String fileName, File uploadFile, ScienceAppUploadTypes type) throws UnknownUploadTypeException, IOException{
		String appRootDirPath = OSPPropertyKeys.getSpyGlassAppsDirPath();
		String dir;
		if( type == ScienceAppUploadTypes.BINARY  ){
			dir = OSPPropertyKeys.getSpyGlassAppsBinDir();
		}
		else if( type == ScienceAppUploadTypes.SOURCE ){
			dir = OSPPropertyKeys.getSpyGlassAppsSrcDir();
		}
		else if( type == ScienceAppUploadTypes.DATA ){
			dir = OSPPropertyKeys.getSpyGlassAppsDataDir();
		}
		else{
			throw new UnknownUploadTypeException( type.toString() );
		}
		
		Path path = Paths.get( appRootDirPath, appName, appVersion, dir );
		Files.deleteIfExists(path);
		Files.createDirectories(path);

		Path target = path.resolve(fileName);
		OSPFileUtil.copyFile(uploadFile.toPath(), target, true);
		OSPFileUtil.unzip(target, target.getParent(), true);
	}
	
	public void downloadScienceApp( PortletRequest portletRequest, PortletResponse portletResponse, ScienceAppUploadTypes type ){
		System.out.println("downloadScienceApp");
		
		return;
	}
	
	public ScienceApp getScienceApp( String scienceAppName, String scienceAppVersion ) throws NoSuchScienceAppException{
		return super.scienceAppPersistence.findByNameVersion(scienceAppName, scienceAppVersion);
	}
}