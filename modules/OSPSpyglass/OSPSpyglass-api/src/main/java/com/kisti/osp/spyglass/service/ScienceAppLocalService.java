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

package com.kisti.osp.spyglass.service;

import aQute.bnd.annotation.ProviderType;

import com.kisti.osp.spyglass.constants.ScienceAppUploadTypes;
import com.kisti.osp.spyglass.exception.NoSuchScienceAppException;
import com.kisti.osp.spyglass.exception.UnknownUploadTypeException;
import com.kisti.osp.spyglass.model.ScienceApp;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * Provides the local service interface for ScienceApp. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ScienceAppLocalServiceUtil
 * @see com.kisti.osp.spyglass.service.base.ScienceAppLocalServiceBaseImpl
 * @see com.kisti.osp.spyglass.service.impl.ScienceAppLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ScienceAppLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ScienceAppLocalServiceUtil} to access the science app local service. Add custom service methods to {@link com.kisti.osp.spyglass.service.impl.ScienceAppLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Test if there is an science app with the specified name and version in the
	* database.
	*
	* @param appName:
	science app name to be tesed.
	* @param appVersion:
	science app version to be tesed.
	* @return true if a science app with the name and the version already exist
	in the database, false, otherwise.
	* @throws SystemException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#existApp(java.lang.String,
	java.lang.String)
	*/
	public boolean existApp(java.lang.String appName,
		java.lang.String appVersion) throws SystemException;

	/**
	* Test if there is a science app name in the database already.
	*
	* @param appName:
	science app name to be tesed.
	* @return true if the app name already exist in the database, false,
	otherwise
	* @throws SystemException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#existAppName(java.lang.String)
	*/
	public boolean existAppName(java.lang.String appName)
		throws SystemException;

	/**
	* Verifies ScienceApp name if the name follows specified naming rules and
	* there is no science app in the database already.
	*
	* @param appName:
	science app name to be tesed.
	* @return true if the name follows naming rules and brand new. false,
	Otherwise.
	* @throws SystemException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#verifyScienceAppName(java.lang.String,
	long)
	*/
	public boolean verifyScienceAppName(java.lang.String appName)
		throws SystemException;

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
	science app name to be verified.
	* @param appVersion:
	science app version to be verified.
	* @return true if the version number follows naming rules and valid. false,
	Otherwise.
	* @throws SystemException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#verifyVersionNumber(java.lang.String,
	java.lang.String)
	*/
	public boolean verifyVersionNumber(java.lang.String appName,
		java.lang.String appVersion) throws SystemException;

	/**
	* Adds the science app to the database. Also notifies the appropriate model listeners.
	*
	* @param scienceApp the science app
	* @return the science app that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ScienceApp addScienceApp(ScienceApp scienceApp);

	/**
	* Saves the specified ScienceApp instance to database.
	*
	* @param scienceApp:
	ScienceApp instance to be saved.
	* @param sc:
	service context of the ScienceApp class
	* @return ScienceApp instance saved.
	* @throws SystemException
	* @throws PortalException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#addScienceApp(com.kisti.science.platform.model.ScienceApp,
	com.liferay.portal.service.ServiceContext)
	*/
	public ScienceApp addScienceApp(ScienceApp scienceApp, ServiceContext sc)
		throws PortalException, SystemException;

	public ScienceApp copyScienceApp(long scienceAppId, ServiceContext sc)
		throws PortalException, SystemException;

	/**
	* Creates a ScienceApp instance with specified name and version. The new
	* instance created is not yet saved in the database. Use addScienceApp() to
	* save the instance.
	*
	* @param appName:
	ScienceApp name to be created
	* @param appVersion:
	ScienceApp version to be created
	* @param sc:
	ServiceContext instance for ScienceApp
	* @return If appName is not follows naming rules or already exists in the
	database, returns null. If appVersion is not follows versioning
	rules, returns null. Otherwise returns a ScienceApp instance with
	initialized data.
	
	Some attributes of the returned instance are set initial value as
	followings: -stage: ScienceAppConstants.EMPTY -authorId: current
	user id of service context instance -createDate: date created of
	service context instance -modifiedDate: date created of service
	context instance -userId: current user id of service context
	instance -recentModifierId: current user id of service context
	instance -groupId: scope group id of service context instance
	-companyId: company id of service context instance
	* @throws SystemException
	* @throws PortalException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#createScienceApp(java.lang.String,
	java.lang.String, com.liferay.portal.service.ServiceContext)
	*/
	public ScienceApp createScienceApp(java.lang.String appName,
		java.lang.String appVersion, ServiceContext sc)
		throws SystemException;

	/**
	* Creates a new science app with the primary key. Does not add the science app to the database.
	*
	* @param scienceAppId the primary key for the new science app
	* @return the new science app
	*/
	public ScienceApp createScienceApp(long scienceAppId);

	/**
	* Deletes the science app from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceApp the science app
	* @return the science app that was removed
	* @throws PortalException
	* @throws SystemException
	*/
	@Indexable(type = IndexableType.DELETE)
	public ScienceApp deleteScienceApp(ScienceApp scienceApp)
		throws PortalException, SystemException;

	/**
	* Deletes the science app with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the science app
	* @return the science app that was removed
	* @throws PortalException if a science app with the primary key could not be found
	* @throws SystemException
	*/
	@Indexable(type = IndexableType.DELETE)
	public ScienceApp deleteScienceApp(long scienceAppId)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ScienceApp fetchScienceApp(long scienceAppId);

	/**
	* Returns the science app matching the UUID and group.
	*
	* @param uuid the science app's UUID
	* @param groupId the primary key of the group
	* @return the matching science app, or <code>null</code> if a matching science app could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ScienceApp fetchScienceAppByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	/**
	* Get the science app's latest version
	*
	* @param appName:
	science app name
	* @return the latest version science app instance.
	* @throws SystemException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#getLatestVersion(java.lang.String)
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ScienceApp getLatestVersion(java.lang.String appName)
		throws SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ScienceApp getScienceApp(java.lang.String scienceAppName,
		java.lang.String scienceAppVersion) throws NoSuchScienceAppException;

	/**
	* Returns the science app with the primary key.
	*
	* @param scienceAppId the primary key of the science app
	* @return the science app
	* @throws PortalException if a science app with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ScienceApp getScienceApp(long scienceAppId)
		throws PortalException;

	/**
	* Returns the science app matching the UUID and group.
	*
	* @param uuid the science app's UUID
	* @param groupId the primary key of the group
	* @return the matching science app
	* @throws PortalException if a matching science app could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ScienceApp getScienceAppByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Updates the science app in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param scienceApp the science app
	* @return the science app that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ScienceApp updateScienceApp(ScienceApp scienceApp);

	/**
	* Update a science app
	*
	* @param scienceApp:
	science app instance to be updated.
	* @param sc:
	ServiceContext instance for the ScienceApp class.
	* @return ScienceApp instance updated.
	* @throws SystemException
	* @throws PortalException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#updateScienceApp(com.kisti.science.platform.model.ScienceApp,
	com.liferay.portal.service.ServiceContext)
	*/
	public ScienceApp updateScienceApp(ScienceApp scienceApp, ServiceContext sc)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Counts all science apps in the database.
	*
	* @return int - count
	*/
	public int countAllScienceApps() throws SystemException;

	public int countScienceAppsByAuthorId(long authorId)
		throws SystemException;

	public int countScienceAppsByGroupId(long groupId);

	/**
	* Returns the number of science apps.
	*
	* @return the number of science apps
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getScienceAppsCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Get path of the executable binary file. The full path can be obtained by
	* adding base directory for science apps.
	*
	* @param scienceAppId
	* @return String of the path.
	* @throws PortalException
	* @throws SystemException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getScienceAppBinPath(long scienceAppId)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getScienceAppDataPath(long scienceAppId)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getScienceAppInputPorts(long scienceAppId)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getScienceAppLogPorts(long scienceAppId)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getScienceAppOutputPorts(long scienceAppId)
		throws PortalException, SystemException;

	/**
	* Get path of the source file. The full path can be obtained by adding base
	* directory for science apps.
	*
	* @param scienceAppId
	* @return String of the path.
	* @throws PortalException
	* @throws SystemException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getScienceAppSrcPath(long scienceAppId)
		throws PortalException, SystemException;

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.spyglass.model.impl.ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.spyglass.model.impl.ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScienceApp> getAll() throws SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScienceApp> getScienceAppListByAuthorId(long authorId)
		throws SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScienceApp> getScienceAppListByAuthorId(long authorId,
		int start, int end) throws SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScienceApp> getScienceAppListByAuthorIdAppType(long authorId,
		java.lang.String appClass, int start, int end)
		throws SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScienceApp> getScienceAppListByAuthorIdAppType(long authorId,
		java.lang.String appType) throws SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScienceApp> getScienceAppListByAuthorIdStatus(long authorId,
		int appStatus, int start, int end) throws SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScienceApp> getScienceAppListByAuthorIdStatus(long authorId,
		int status) throws SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScienceApp> getScienceAppListByOpenLevel(
		java.lang.String openLevel) throws SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScienceApp> getScienceAppListByOpenLevel(
		java.lang.String openLevel, int start, int end)
		throws SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScienceApp> getScienceAppListByStage(java.lang.String stage)
		throws SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScienceApp> getScienceAppListByStatus(int status)
		throws SystemException;

	/**
	* Returns a range of all the science apps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.spyglass.model.impl.ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of science apps
	* @param end the upper bound of the range of science apps (not inclusive)
	* @return the range of science apps
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScienceApp> getScienceApps(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScienceApp> getScienceAppsByGroupId(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScienceApp> getScienceAppsByGroupId(long groupId, int start,
		int end);

	/**
	* Returns all the science apps matching the UUID and company.
	*
	* @param uuid the UUID of the science apps
	* @param companyId the primary key of the company
	* @return the matching science apps, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScienceApp> getScienceAppsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of science apps matching the UUID and company.
	*
	* @param uuid the UUID of the science apps
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of science apps
	* @param end the upper bound of the range of science apps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching science apps, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ScienceApp> getScienceAppsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public void deleteAllScienceApps() throws SystemException;

	public void downloadScienceApp(PortletRequest portletRequest,
		PortletResponse portletResponse, ScienceAppUploadTypes type);

	public void setScienceAppInputPorts(long scienceAppId,
		java.lang.String inputPorts) throws SystemException;

	public void setScienceAppLogPorts(long scienceAppId,
		java.lang.String logPorts) throws PortalException, SystemException;

	public void setScienceAppOutputPorts(long scienceAppId,
		java.lang.String outputPorts) throws SystemException;

	public void uploadScienceApp(java.lang.String appName,
		java.lang.String appVersion, java.lang.String fileName,
		File uploadFile, ScienceAppUploadTypes type)
		throws UnknownUploadTypeException, IOException;
}