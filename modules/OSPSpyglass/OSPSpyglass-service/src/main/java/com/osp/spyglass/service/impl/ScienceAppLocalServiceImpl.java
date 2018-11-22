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

package com.osp.spyglass.service.impl;

import com.kisti.osp.util.constants.OSPPropertyKeys;
import com.kisti.osp.util.file.OSPFileUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.osp.spyglass.constants.RegisterStages;
import com.osp.spyglass.constants.UploadTypes;
import com.osp.spyglass.exception.NoSuchScienceAppException;
import com.osp.spyglass.exception.UnknownUploadTypeException;
import com.osp.spyglass.model.InputPorts;
import com.osp.spyglass.model.LogPorts;
import com.osp.spyglass.model.OutputPorts;
import com.osp.spyglass.model.ScienceApp;
import com.osp.spyglass.model.impl.ScienceAppImpl;
import com.osp.spyglass.service.base.ScienceAppLocalServiceBaseImpl;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * The implementation of the science app local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.osp.spyglass.service.ScienceAppLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Jerry H. Seo
 * @see ScienceAppLocalServiceBaseImpl
 * @see com.osp.spyglass.service.ScienceAppLocalServiceUtil
 */
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
		newApp.setRegisterStage(RegisterStages.EMPTY.toString());
		newApp.setAuthorId(sc.getUserId());
		newApp.setStatus(WorkflowConstants.STATUS_DRAFT);
		
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
	@Indexable(type=IndexableType.REINDEX)
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

		Indexer indexer = IndexerRegistryUtil.getIndexer(ScienceApp.class);
		indexer.reindex(scienceApp);

		return scienceApp;
	}

	public void setScienceAppInputPorts(long scienceAppId, String inputPorts) throws SystemException{
		super.inputPortsLocalService.setInputPorts(scienceAppId, inputPorts);
	}

	public String getScienceAppInputPorts(long scienceAppId) throws SystemException, PortalException{
		InputPorts ports = super.inputPortsLocalService.getInputPorts(scienceAppId);
		return ports.getInputPorts();
	}
	
	public void setScienceAppLogPorts(long scienceAppId, String logPorts) throws SystemException, PortalException{
		super.logPortsLocalService.setLogPorts(scienceAppId, logPorts);
	}

	public String getScienceAppLogPorts(long scienceAppId) throws SystemException, PortalException{
		LogPorts ports = super.logPortsLocalService.getLogPorts(scienceAppId);
		return ports.getLogPorts();
	}

	public void setScienceAppOutputPorts(long scienceAppId, String outputPorts) throws SystemException{
		super.outputPortsLocalService.setOutputPorts(scienceAppId, outputPorts);
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
	@Indexable(type=IndexableType.REINDEX)
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
	@Indexable(type=IndexableType.REINDEX)
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

	public ScienceApp copyScienceApp(long scienceAppId, ServiceContext sc) throws SystemException, PortalException{
		ScienceApp app = super.getScienceApp(scienceAppId);
		
		ScienceApp clone = (ScienceApp)app.clone();
		
		clone.setScienceAppId(super.counterLocalService.increment());
		clone.setName("");
		clone.setVersion("");
		clone.setPreviousVersionId(app.getScienceAppId());
		
		clone.setCreateDate(sc.getCreateDate());
		clone.setModifiedDate(sc.getModifiedDate());
		clone.setGroupId(sc.getScopeGroupId());
		clone.setUserId(sc.getUserId());
		clone.setStatus(WorkflowConstants.STATUS_DRAFT);
		
		this.addScienceApp(clone, sc);
		
		return clone;
	}
	
	
	public void uploadScienceApp(String appName, String appVersion, String fileName, File uploadFile, UploadTypes type) throws UnknownUploadTypeException, IOException{
		String appRootDirPath = OSPPropertyKeys.getSpyGlassAppsDirPath();
		String dir;
		if( type == UploadTypes.BINARY  ){
			dir = OSPPropertyKeys.getSpyGlassAppsBinDir();
		}
		else if( type == UploadTypes.SOURCE ){
			dir = OSPPropertyKeys.getSpyGlassAppsSrcDir();
		}
		else if( type == UploadTypes.DATA ){
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
	
	public void downloadScienceApp( PortletRequest portletRequest, PortletResponse portletResponse, UploadTypes type ){
		System.out.println("downloadScienceApp");
		
		return;
	}
}