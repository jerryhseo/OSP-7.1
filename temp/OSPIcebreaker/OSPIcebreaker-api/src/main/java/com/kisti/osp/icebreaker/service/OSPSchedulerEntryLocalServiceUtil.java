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

package com.kisti.osp.icebreaker.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for OSPSchedulerEntry. This utility wraps
 * {@link com.kisti.osp.icebreaker.service.impl.OSPSchedulerEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Jerry H. Seo
 * @see OSPSchedulerEntryLocalService
 * @see com.kisti.osp.icebreaker.service.base.OSPSchedulerEntryLocalServiceBaseImpl
 * @see com.kisti.osp.icebreaker.service.impl.OSPSchedulerEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class OSPSchedulerEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.kisti.osp.icebreaker.service.impl.OSPSchedulerEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the osp scheduler entry to the database. Also notifies the appropriate model listeners.
	*
	* @param ospSchedulerEntry the osp scheduler entry
	* @return the osp scheduler entry that was added
	*/
	public static com.kisti.osp.icebreaker.model.OSPSchedulerEntry addOSPSchedulerEntry(
		com.kisti.osp.icebreaker.model.OSPSchedulerEntry ospSchedulerEntry) {
		return getService().addOSPSchedulerEntry(ospSchedulerEntry);
	}

	public static com.kisti.osp.icebreaker.model.OSPSchedulerEntry addScheduler(
		String name, String version, String serverIp, String sshPort,
		String schedulerClass, String accessMethod, String authorizedUser,
		String authorizedPassword, String description, String templateDir,
		String templateRealDir, long[] permissionGroupIds,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addScheduler(name, version, serverIp, sshPort,
			schedulerClass, accessMethod, authorizedUser, authorizedPassword,
			description, templateDir, templateRealDir, permissionGroupIds, sc);
	}

	public static int countSchedulers()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().countSchedulers();
	}

	public static int countSchedulers(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().countSchedulers(groupId);
	}

	public static com.kisti.osp.icebreaker.model.OSPScheduler createOSPScheduler(
		long schedulerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createOSPScheduler(schedulerEntryId);
	}

	public static com.kisti.osp.icebreaker.model.OSPScheduler createOSPScheduler(
		String className, String user, String password, String ip, String port) {
		return getService()
				   .createOSPScheduler(className, user, password, ip, port);
	}

	/**
	* Creates a new osp scheduler entry with the primary key. Does not add the osp scheduler entry to the database.
	*
	* @param schedulerEntryId the primary key for the new osp scheduler entry
	* @return the new osp scheduler entry
	*/
	public static com.kisti.osp.icebreaker.model.OSPSchedulerEntry createOSPSchedulerEntry(
		long schedulerEntryId) {
		return getService().createOSPSchedulerEntry(schedulerEntryId);
	}

	/**
	* Deletes the osp scheduler entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param schedulerEntryId the primary key of the osp scheduler entry
	* @return the osp scheduler entry that was removed
	* @throws PortalException if a osp scheduler entry with the primary key could not be found
	*/
	public static com.kisti.osp.icebreaker.model.OSPSchedulerEntry deleteOSPSchedulerEntry(
		long schedulerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOSPSchedulerEntry(schedulerEntryId);
	}

	/**
	* Deletes the osp scheduler entry from the database. Also notifies the appropriate model listeners.
	*
	* @param ospSchedulerEntry the osp scheduler entry
	* @return the osp scheduler entry that was removed
	*/
	public static com.kisti.osp.icebreaker.model.OSPSchedulerEntry deleteOSPSchedulerEntry(
		com.kisti.osp.icebreaker.model.OSPSchedulerEntry ospSchedulerEntry) {
		return getService().deleteOSPSchedulerEntry(ospSchedulerEntry);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icebreaker.model.impl.OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icebreaker.model.impl.OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.kisti.osp.icebreaker.model.OSPSchedulerEntry fetchOSPSchedulerEntry(
		long schedulerEntryId) {
		return getService().fetchOSPSchedulerEntry(schedulerEntryId);
	}

	/**
	* Returns the osp scheduler entry matching the UUID and group.
	*
	* @param uuid the osp scheduler entry's UUID
	* @param groupId the primary key of the group
	* @return the matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static com.kisti.osp.icebreaker.model.OSPSchedulerEntry fetchOSPSchedulerEntryByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchOSPSchedulerEntryByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Returns a range of all the osp scheduler entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icebreaker.model.impl.OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @return the range of osp scheduler entries
	*/
	public static java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getOSPSchedulerEntries(
		int start, int end) {
		return getService().getOSPSchedulerEntries(start, end);
	}

	/**
	* Returns all the osp scheduler entries matching the UUID and company.
	*
	* @param uuid the UUID of the osp scheduler entries
	* @param companyId the primary key of the company
	* @return the matching osp scheduler entries, or an empty list if no matches were found
	*/
	public static java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getOSPSchedulerEntriesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getOSPSchedulerEntriesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of osp scheduler entries matching the UUID and company.
	*
	* @param uuid the UUID of the osp scheduler entries
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching osp scheduler entries, or an empty list if no matches were found
	*/
	public static java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getOSPSchedulerEntriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> orderByComparator) {
		return getService()
				   .getOSPSchedulerEntriesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of osp scheduler entries.
	*
	* @return the number of osp scheduler entries
	*/
	public static int getOSPSchedulerEntriesCount() {
		return getService().getOSPSchedulerEntriesCount();
	}

	/**
	* Returns the osp scheduler entry with the primary key.
	*
	* @param schedulerEntryId the primary key of the osp scheduler entry
	* @return the osp scheduler entry
	* @throws PortalException if a osp scheduler entry with the primary key could not be found
	*/
	public static com.kisti.osp.icebreaker.model.OSPSchedulerEntry getOSPSchedulerEntry(
		long schedulerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOSPSchedulerEntry(schedulerEntryId);
	}

	/**
	* Returns the osp scheduler entry matching the UUID and group.
	*
	* @param uuid the osp scheduler entry's UUID
	* @param groupId the primary key of the group
	* @return the matching osp scheduler entry
	* @throws PortalException if a matching osp scheduler entry could not be found
	*/
	public static com.kisti.osp.icebreaker.model.OSPSchedulerEntry getOSPSchedulerEntryByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOSPSchedulerEntryByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static com.kisti.osp.icebreaker.model.OSPSchedulerEntry getSchedulerEntry(
		String name, String version)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getService().getSchedulerEntry(name, version);
	}

	public static java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getSchedulers()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSchedulers();
	}

	public static java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getSchedulers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSchedulers(start, end);
	}

	public static java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getSchedulers(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSchedulers(groupId);
	}

	public static java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getSchedulers(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSchedulers(groupId, start, end);
	}

	public static java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getSchedulers(
		String name) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSchedulers(name);
	}

	public static java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getSchedulers(
		String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSchedulers(name, start, end);
	}

	public static com.liferay.portal.kernel.json.JSONArray getSchedulersJson()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSchedulersJson();
	}

	public static com.liferay.portal.kernel.json.JSONArray getSchedulersJson(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSchedulersJson(start, end);
	}

	public static com.kisti.osp.icebreaker.model.OSPSchedulerEntry removeScheduler(
		long schedulerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().removeScheduler(schedulerId);
	}

	public static com.kisti.osp.icebreaker.model.OSPSchedulerEntry removeScheduler(
		String name, String version)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.kernel.exception.PortalException {
		return getService().removeScheduler(name, version);
	}

	public static void removeSchedulers(String name)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.kernel.exception.PortalException {
		getService().removeSchedulers(name);
	}

	public static com.kisti.osp.icebreaker.model.Job submitJob(
		com.kisti.osp.icebreaker.model.Job job,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.kernel.exception.PortalException,
			java.io.IOException {
		return getService().submitJob(job, sc);
	}

	public static com.liferay.portal.kernel.json.JSONArray toJsonArray(
		com.kisti.osp.icebreaker.model.OSPSchedulerEntry scheduler) {
		return getService().toJsonArray(scheduler);
	}

	/**
	* Updates the osp scheduler entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ospSchedulerEntry the osp scheduler entry
	* @return the osp scheduler entry that was updated
	*/
	public static com.kisti.osp.icebreaker.model.OSPSchedulerEntry updateOSPSchedulerEntry(
		com.kisti.osp.icebreaker.model.OSPSchedulerEntry ospSchedulerEntry) {
		return getService().updateOSPSchedulerEntry(ospSchedulerEntry);
	}

	public static com.kisti.osp.icebreaker.model.OSPSchedulerEntry updateScheduler(
		long schedulerId, String name, String version, String serverIp,
		String sshPort, String schedulerClass, String accessMethod,
		String authorizedUser, String authorizedPassword, String description,
		String templateDir, String templateRealDir, long[] permissionGroupIds,
		int status, com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateScheduler(schedulerId, name, version, serverIp,
			sshPort, schedulerClass, accessMethod, authorizedUser,
			authorizedPassword, description, templateDir, templateRealDir,
			permissionGroupIds, status, sc);
	}

	public static com.kisti.osp.icebreaker.model.OSPSchedulerEntry updateScheduler(
		com.kisti.osp.icebreaker.model.OSPSchedulerEntry scheduler,
		long[] permissionGroupIds,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.kernel.exception.PortalException {
		return getService().updateScheduler(scheduler, permissionGroupIds, sc);
	}

	public static void updateSchedulerPermissions(
		com.kisti.osp.icebreaker.model.OSPSchedulerEntry scheduler,
		long[] permissionGroupIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateSchedulerPermissions(scheduler, permissionGroupIds);
	}

	public static OSPSchedulerEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OSPSchedulerEntryLocalService, OSPSchedulerEntryLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OSPSchedulerEntryLocalService.class);

		ServiceTracker<OSPSchedulerEntryLocalService, OSPSchedulerEntryLocalService> serviceTracker =
			new ServiceTracker<OSPSchedulerEntryLocalService, OSPSchedulerEntryLocalService>(bundle.getBundleContext(),
				OSPSchedulerEntryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}