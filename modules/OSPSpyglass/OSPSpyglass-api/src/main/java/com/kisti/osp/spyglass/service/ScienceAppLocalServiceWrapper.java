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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ScienceAppLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ScienceAppLocalService
 * @generated
 */
@ProviderType
public class ScienceAppLocalServiceWrapper implements ScienceAppLocalService,
	ServiceWrapper<ScienceAppLocalService> {
	public ScienceAppLocalServiceWrapper(
		ScienceAppLocalService scienceAppLocalService) {
		_scienceAppLocalService = scienceAppLocalService;
	}

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
	@Override
	public boolean existApp(java.lang.String appName,
		java.lang.String appVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.existApp(appName, appVersion);
	}

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
	@Override
	public boolean existAppName(java.lang.String appName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.existAppName(appName);
	}

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
	@Override
	public boolean verifyScienceAppName(java.lang.String appName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.verifyScienceAppName(appName);
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
	science app name to be verified.
	* @param appVersion:
	science app version to be verified.
	* @return true if the version number follows naming rules and valid. false,
	Otherwise.
	* @throws SystemException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#verifyVersionNumber(java.lang.String,
	java.lang.String)
	*/
	@Override
	public boolean verifyVersionNumber(java.lang.String appName,
		java.lang.String appVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.verifyVersionNumber(appName, appVersion);
	}

	/**
	* Adds the science app to the database. Also notifies the appropriate model listeners.
	*
	* @param scienceApp the science app
	* @return the science app that was added
	*/
	@Override
	public com.kisti.osp.spyglass.model.ScienceApp addScienceApp(
		com.kisti.osp.spyglass.model.ScienceApp scienceApp) {
		return _scienceAppLocalService.addScienceApp(scienceApp);
	}

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
	@Override
	public com.kisti.osp.spyglass.model.ScienceApp addScienceApp(
		com.kisti.osp.spyglass.model.ScienceApp scienceApp,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.addScienceApp(scienceApp, sc);
	}

	@Override
	public com.kisti.osp.spyglass.model.ScienceApp copyScienceApp(
		long scienceAppId, com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.copyScienceApp(scienceAppId, sc);
	}

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
	@Override
	public com.kisti.osp.spyglass.model.ScienceApp createScienceApp(
		java.lang.String appName, java.lang.String appVersion,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.createScienceApp(appName, appVersion, sc);
	}

	/**
	* Creates a new science app with the primary key. Does not add the science app to the database.
	*
	* @param scienceAppId the primary key for the new science app
	* @return the new science app
	*/
	@Override
	public com.kisti.osp.spyglass.model.ScienceApp createScienceApp(
		long scienceAppId) {
		return _scienceAppLocalService.createScienceApp(scienceAppId);
	}

	/**
	* Deletes the science app from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceApp the science app
	* @return the science app that was removed
	* @throws PortalException
	* @throws SystemException
	*/
	@Override
	public com.kisti.osp.spyglass.model.ScienceApp deleteScienceApp(
		com.kisti.osp.spyglass.model.ScienceApp scienceApp)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.deleteScienceApp(scienceApp);
	}

	/**
	* Deletes the science app with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the science app
	* @return the science app that was removed
	* @throws PortalException if a science app with the primary key could not be found
	* @throws SystemException
	*/
	@Override
	public com.kisti.osp.spyglass.model.ScienceApp deleteScienceApp(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.deleteScienceApp(scienceAppId);
	}

	@Override
	public com.kisti.osp.spyglass.model.ScienceApp fetchScienceApp(
		long scienceAppId) {
		return _scienceAppLocalService.fetchScienceApp(scienceAppId);
	}

	/**
	* Returns the science app matching the UUID and group.
	*
	* @param uuid the science app's UUID
	* @param groupId the primary key of the group
	* @return the matching science app, or <code>null</code> if a matching science app could not be found
	*/
	@Override
	public com.kisti.osp.spyglass.model.ScienceApp fetchScienceAppByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _scienceAppLocalService.fetchScienceAppByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Get the science app's latest version
	*
	* @param appName:
	science app name
	* @return the latest version science app instance.
	* @throws SystemException
	* @see com.kisti.science.platform.service.ScienceAppLocalService#getLatestVersion(java.lang.String)
	*/
	@Override
	public com.kisti.osp.spyglass.model.ScienceApp getLatestVersion(
		java.lang.String appName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getLatestVersion(appName);
	}

	@Override
	public com.kisti.osp.spyglass.model.ScienceApp getScienceApp(
		java.lang.String scienceAppName, java.lang.String scienceAppVersion)
		throws com.kisti.osp.spyglass.exception.NoSuchScienceAppException {
		return _scienceAppLocalService.getScienceApp(scienceAppName,
			scienceAppVersion);
	}

	/**
	* Returns the science app with the primary key.
	*
	* @param scienceAppId the primary key of the science app
	* @return the science app
	* @throws PortalException if a science app with the primary key could not be found
	*/
	@Override
	public com.kisti.osp.spyglass.model.ScienceApp getScienceApp(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scienceAppLocalService.getScienceApp(scienceAppId);
	}

	/**
	* Returns the science app matching the UUID and group.
	*
	* @param uuid the science app's UUID
	* @param groupId the primary key of the group
	* @return the matching science app
	* @throws PortalException if a matching science app could not be found
	*/
	@Override
	public com.kisti.osp.spyglass.model.ScienceApp getScienceAppByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scienceAppLocalService.getScienceAppByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the science app in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param scienceApp the science app
	* @return the science app that was updated
	*/
	@Override
	public com.kisti.osp.spyglass.model.ScienceApp updateScienceApp(
		com.kisti.osp.spyglass.model.ScienceApp scienceApp) {
		return _scienceAppLocalService.updateScienceApp(scienceApp);
	}

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
	@Override
	public com.kisti.osp.spyglass.model.ScienceApp updateScienceApp(
		com.kisti.osp.spyglass.model.ScienceApp scienceApp,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.updateScienceApp(scienceApp, sc);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _scienceAppLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _scienceAppLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _scienceAppLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _scienceAppLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scienceAppLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scienceAppLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Counts all science apps in the database.
	*
	* @return int - count
	*/
	@Override
	public int countAllScienceApps()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.countAllScienceApps();
	}

	@Override
	public int countScienceAppsByAuthorId(long authorId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.countScienceAppsByAuthorId(authorId);
	}

	@Override
	public int countScienceAppsByGroupId(long groupId) {
		return _scienceAppLocalService.countScienceAppsByGroupId(groupId);
	}

	/**
	* Returns the number of science apps.
	*
	* @return the number of science apps
	*/
	@Override
	public int getScienceAppsCount() {
		return _scienceAppLocalService.getScienceAppsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _scienceAppLocalService.getOSGiServiceIdentifier();
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
	@Override
	public java.lang.String getScienceAppBinPath(long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppBinPath(scienceAppId);
	}

	@Override
	public java.lang.String getScienceAppDataPath(long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppDataPath(scienceAppId);
	}

	@Override
	public java.lang.String getScienceAppInputPorts(long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppInputPorts(scienceAppId);
	}

	@Override
	public java.lang.String getScienceAppLogPorts(long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppLogPorts(scienceAppId);
	}

	@Override
	public java.lang.String getScienceAppOutputPorts(long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppOutputPorts(scienceAppId);
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
	@Override
	public java.lang.String getScienceAppSrcPath(long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppSrcPath(scienceAppId);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _scienceAppLocalService.dynamicQuery(dynamicQuery);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _scienceAppLocalService.dynamicQuery(dynamicQuery, start, end);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _scienceAppLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.kisti.osp.spyglass.model.ScienceApp> getAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getAll();
	}

	@Override
	public java.util.List<com.kisti.osp.spyglass.model.ScienceApp> getScienceAppListByAuthorId(
		long authorId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByAuthorId(authorId);
	}

	@Override
	public java.util.List<com.kisti.osp.spyglass.model.ScienceApp> getScienceAppListByAuthorId(
		long authorId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByAuthorId(authorId,
			start, end);
	}

	@Override
	public java.util.List<com.kisti.osp.spyglass.model.ScienceApp> getScienceAppListByAuthorIdAppType(
		long authorId, java.lang.String appClass, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByAuthorIdAppType(authorId,
			appClass, start, end);
	}

	@Override
	public java.util.List<com.kisti.osp.spyglass.model.ScienceApp> getScienceAppListByAuthorIdAppType(
		long authorId, java.lang.String appType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByAuthorIdAppType(authorId,
			appType);
	}

	@Override
	public java.util.List<com.kisti.osp.spyglass.model.ScienceApp> getScienceAppListByAuthorIdStatus(
		long authorId, int appStatus, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByAuthorIdStatus(authorId,
			appStatus, start, end);
	}

	@Override
	public java.util.List<com.kisti.osp.spyglass.model.ScienceApp> getScienceAppListByAuthorIdStatus(
		long authorId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByAuthorIdStatus(authorId,
			status);
	}

	@Override
	public java.util.List<com.kisti.osp.spyglass.model.ScienceApp> getScienceAppListByOpenLevel(
		java.lang.String openLevel)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByOpenLevel(openLevel);
	}

	@Override
	public java.util.List<com.kisti.osp.spyglass.model.ScienceApp> getScienceAppListByOpenLevel(
		java.lang.String openLevel, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByOpenLevel(openLevel,
			start, end);
	}

	@Override
	public java.util.List<com.kisti.osp.spyglass.model.ScienceApp> getScienceAppListByStage(
		java.lang.String stage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByStage(stage);
	}

	@Override
	public java.util.List<com.kisti.osp.spyglass.model.ScienceApp> getScienceAppListByStatus(
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppLocalService.getScienceAppListByStatus(status);
	}

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
	@Override
	public java.util.List<com.kisti.osp.spyglass.model.ScienceApp> getScienceApps(
		int start, int end) {
		return _scienceAppLocalService.getScienceApps(start, end);
	}

	@Override
	public java.util.List<com.kisti.osp.spyglass.model.ScienceApp> getScienceAppsByGroupId(
		long groupId) {
		return _scienceAppLocalService.getScienceAppsByGroupId(groupId);
	}

	@Override
	public java.util.List<com.kisti.osp.spyglass.model.ScienceApp> getScienceAppsByGroupId(
		long groupId, int start, int end) {
		return _scienceAppLocalService.getScienceAppsByGroupId(groupId, start,
			end);
	}

	/**
	* Returns all the science apps matching the UUID and company.
	*
	* @param uuid the UUID of the science apps
	* @param companyId the primary key of the company
	* @return the matching science apps, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.kisti.osp.spyglass.model.ScienceApp> getScienceAppsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _scienceAppLocalService.getScienceAppsByUuidAndCompanyId(uuid,
			companyId);
	}

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
	@Override
	public java.util.List<com.kisti.osp.spyglass.model.ScienceApp> getScienceAppsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.kisti.osp.spyglass.model.ScienceApp> orderByComparator) {
		return _scienceAppLocalService.getScienceAppsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _scienceAppLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _scienceAppLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void deleteAllScienceApps()
		throws com.liferay.portal.kernel.exception.SystemException {
		_scienceAppLocalService.deleteAllScienceApps();
	}

	@Override
	public void downloadScienceApp(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		com.kisti.osp.spyglass.constants.ScienceAppUploadTypes type) {
		_scienceAppLocalService.downloadScienceApp(portletRequest,
			portletResponse, type);
	}

	@Override
	public void setScienceAppInputPorts(long scienceAppId,
		java.lang.String inputPorts)
		throws com.liferay.portal.kernel.exception.SystemException {
		_scienceAppLocalService.setScienceAppInputPorts(scienceAppId, inputPorts);
	}

	@Override
	public void setScienceAppLogPorts(long scienceAppId,
		java.lang.String logPorts)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_scienceAppLocalService.setScienceAppLogPorts(scienceAppId, logPorts);
	}

	@Override
	public void setScienceAppOutputPorts(long scienceAppId,
		java.lang.String outputPorts)
		throws com.liferay.portal.kernel.exception.SystemException {
		_scienceAppLocalService.setScienceAppOutputPorts(scienceAppId,
			outputPorts);
	}

	@Override
	public void uploadScienceApp(java.lang.String appName,
		java.lang.String appVersion, java.lang.String fileName,
		java.io.File uploadFile,
		com.kisti.osp.spyglass.constants.ScienceAppUploadTypes type)
		throws com.kisti.osp.spyglass.exception.UnknownUploadTypeException,
			java.io.IOException {
		_scienceAppLocalService.uploadScienceApp(appName, appVersion, fileName,
			uploadFile, type);
	}

	@Override
	public ScienceAppLocalService getWrappedService() {
		return _scienceAppLocalService;
	}

	@Override
	public void setWrappedService(ScienceAppLocalService scienceAppLocalService) {
		_scienceAppLocalService = scienceAppLocalService;
	}

	private ScienceAppLocalService _scienceAppLocalService;
}