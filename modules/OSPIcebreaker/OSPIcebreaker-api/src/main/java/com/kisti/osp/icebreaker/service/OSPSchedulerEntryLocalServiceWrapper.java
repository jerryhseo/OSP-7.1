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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OSPSchedulerEntryLocalService}.
 *
 * @author Jerry H. Seo
 * @see OSPSchedulerEntryLocalService
 * @generated
 */
@ProviderType
public class OSPSchedulerEntryLocalServiceWrapper
	implements OSPSchedulerEntryLocalService,
		ServiceWrapper<OSPSchedulerEntryLocalService> {
	public OSPSchedulerEntryLocalServiceWrapper(
		OSPSchedulerEntryLocalService ospSchedulerEntryLocalService) {
		_ospSchedulerEntryLocalService = ospSchedulerEntryLocalService;
	}

	/**
	* Adds the osp scheduler entry to the database. Also notifies the appropriate model listeners.
	*
	* @param ospSchedulerEntry the osp scheduler entry
	* @return the osp scheduler entry that was added
	*/
	@Override
	public com.kisti.osp.icebreaker.model.OSPSchedulerEntry addOSPSchedulerEntry(
		com.kisti.osp.icebreaker.model.OSPSchedulerEntry ospSchedulerEntry) {
		return _ospSchedulerEntryLocalService.addOSPSchedulerEntry(ospSchedulerEntry);
	}

	@Override
	public com.kisti.osp.icebreaker.model.OSPSchedulerEntry addScheduler(
		String name, String version, String serverIp, String sshPort,
		String schedulerClass, String accessMethod, String authorizedUser,
		String authorizedPassword, String description, String templateDir,
		String templateRealDir, long[] permissionGroupIds,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.kernel.exception.PortalException {
		return _ospSchedulerEntryLocalService.addScheduler(name, version,
			serverIp, sshPort, schedulerClass, accessMethod, authorizedUser,
			authorizedPassword, description, templateDir, templateRealDir,
			permissionGroupIds, sc);
	}

	@Override
	public int countSchedulers()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ospSchedulerEntryLocalService.countSchedulers();
	}

	@Override
	public int countSchedulers(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ospSchedulerEntryLocalService.countSchedulers(groupId);
	}

	@Override
	public com.kisti.osp.icebreaker.model.OSPScheduler createOSPScheduler(
		long schedulerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ospSchedulerEntryLocalService.createOSPScheduler(schedulerEntryId);
	}

	@Override
	public com.kisti.osp.icebreaker.model.OSPScheduler createOSPScheduler(
		String className, String user, String password, String ip, String port) {
		return _ospSchedulerEntryLocalService.createOSPScheduler(className,
			user, password, ip, port);
	}

	/**
	* Creates a new osp scheduler entry with the primary key. Does not add the osp scheduler entry to the database.
	*
	* @param schedulerEntryId the primary key for the new osp scheduler entry
	* @return the new osp scheduler entry
	*/
	@Override
	public com.kisti.osp.icebreaker.model.OSPSchedulerEntry createOSPSchedulerEntry(
		long schedulerEntryId) {
		return _ospSchedulerEntryLocalService.createOSPSchedulerEntry(schedulerEntryId);
	}

	/**
	* Deletes the osp scheduler entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param schedulerEntryId the primary key of the osp scheduler entry
	* @return the osp scheduler entry that was removed
	* @throws PortalException if a osp scheduler entry with the primary key could not be found
	*/
	@Override
	public com.kisti.osp.icebreaker.model.OSPSchedulerEntry deleteOSPSchedulerEntry(
		long schedulerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ospSchedulerEntryLocalService.deleteOSPSchedulerEntry(schedulerEntryId);
	}

	/**
	* Deletes the osp scheduler entry from the database. Also notifies the appropriate model listeners.
	*
	* @param ospSchedulerEntry the osp scheduler entry
	* @return the osp scheduler entry that was removed
	*/
	@Override
	public com.kisti.osp.icebreaker.model.OSPSchedulerEntry deleteOSPSchedulerEntry(
		com.kisti.osp.icebreaker.model.OSPSchedulerEntry ospSchedulerEntry) {
		return _ospSchedulerEntryLocalService.deleteOSPSchedulerEntry(ospSchedulerEntry);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ospSchedulerEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ospSchedulerEntryLocalService.dynamicQuery();
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
		return _ospSchedulerEntryLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _ospSchedulerEntryLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _ospSchedulerEntryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _ospSchedulerEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ospSchedulerEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.kisti.osp.icebreaker.model.OSPSchedulerEntry fetchOSPSchedulerEntry(
		long schedulerEntryId) {
		return _ospSchedulerEntryLocalService.fetchOSPSchedulerEntry(schedulerEntryId);
	}

	/**
	* Returns the osp scheduler entry matching the UUID and group.
	*
	* @param uuid the osp scheduler entry's UUID
	* @param groupId the primary key of the group
	* @return the matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	@Override
	public com.kisti.osp.icebreaker.model.OSPSchedulerEntry fetchOSPSchedulerEntryByUuidAndGroupId(
		String uuid, long groupId) {
		return _ospSchedulerEntryLocalService.fetchOSPSchedulerEntryByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ospSchedulerEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _ospSchedulerEntryLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ospSchedulerEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _ospSchedulerEntryLocalService.getOSGiServiceIdentifier();
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
	@Override
	public java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getOSPSchedulerEntries(
		int start, int end) {
		return _ospSchedulerEntryLocalService.getOSPSchedulerEntries(start, end);
	}

	/**
	* Returns all the osp scheduler entries matching the UUID and company.
	*
	* @param uuid the UUID of the osp scheduler entries
	* @param companyId the primary key of the company
	* @return the matching osp scheduler entries, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getOSPSchedulerEntriesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _ospSchedulerEntryLocalService.getOSPSchedulerEntriesByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getOSPSchedulerEntriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> orderByComparator) {
		return _ospSchedulerEntryLocalService.getOSPSchedulerEntriesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of osp scheduler entries.
	*
	* @return the number of osp scheduler entries
	*/
	@Override
	public int getOSPSchedulerEntriesCount() {
		return _ospSchedulerEntryLocalService.getOSPSchedulerEntriesCount();
	}

	/**
	* Returns the osp scheduler entry with the primary key.
	*
	* @param schedulerEntryId the primary key of the osp scheduler entry
	* @return the osp scheduler entry
	* @throws PortalException if a osp scheduler entry with the primary key could not be found
	*/
	@Override
	public com.kisti.osp.icebreaker.model.OSPSchedulerEntry getOSPSchedulerEntry(
		long schedulerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ospSchedulerEntryLocalService.getOSPSchedulerEntry(schedulerEntryId);
	}

	/**
	* Returns the osp scheduler entry matching the UUID and group.
	*
	* @param uuid the osp scheduler entry's UUID
	* @param groupId the primary key of the group
	* @return the matching osp scheduler entry
	* @throws PortalException if a matching osp scheduler entry could not be found
	*/
	@Override
	public com.kisti.osp.icebreaker.model.OSPSchedulerEntry getOSPSchedulerEntryByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ospSchedulerEntryLocalService.getOSPSchedulerEntryByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ospSchedulerEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.kisti.osp.icebreaker.model.OSPSchedulerEntry getSchedulerEntry(
		String name, String version)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return _ospSchedulerEntryLocalService.getSchedulerEntry(name, version);
	}

	@Override
	public java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getSchedulers()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ospSchedulerEntryLocalService.getSchedulers();
	}

	@Override
	public java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getSchedulers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ospSchedulerEntryLocalService.getSchedulers(start, end);
	}

	@Override
	public java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getSchedulers(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ospSchedulerEntryLocalService.getSchedulers(groupId);
	}

	@Override
	public java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getSchedulers(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ospSchedulerEntryLocalService.getSchedulers(groupId, start, end);
	}

	@Override
	public java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getSchedulers(
		String name) throws com.liferay.portal.kernel.exception.SystemException {
		return _ospSchedulerEntryLocalService.getSchedulers(name);
	}

	@Override
	public java.util.List<com.kisti.osp.icebreaker.model.OSPSchedulerEntry> getSchedulers(
		String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ospSchedulerEntryLocalService.getSchedulers(name, start, end);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getSchedulersJson()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ospSchedulerEntryLocalService.getSchedulersJson();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getSchedulersJson(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ospSchedulerEntryLocalService.getSchedulersJson(start, end);
	}

	@Override
	public com.kisti.osp.icebreaker.model.OSPSchedulerEntry removeScheduler(
		long schedulerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ospSchedulerEntryLocalService.removeScheduler(schedulerId);
	}

	@Override
	public com.kisti.osp.icebreaker.model.OSPSchedulerEntry removeScheduler(
		String name, String version)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.kernel.exception.PortalException {
		return _ospSchedulerEntryLocalService.removeScheduler(name, version);
	}

	@Override
	public void removeSchedulers(String name)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.kernel.exception.PortalException {
		_ospSchedulerEntryLocalService.removeSchedulers(name);
	}

	@Override
	public com.kisti.osp.icebreaker.model.Job submitJob(
		com.kisti.osp.icebreaker.model.Job job,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.kernel.exception.PortalException,
			java.io.IOException {
		return _ospSchedulerEntryLocalService.submitJob(job, sc);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray toJsonArray(
		com.kisti.osp.icebreaker.model.OSPSchedulerEntry scheduler) {
		return _ospSchedulerEntryLocalService.toJsonArray(scheduler);
	}

	/**
	* Updates the osp scheduler entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ospSchedulerEntry the osp scheduler entry
	* @return the osp scheduler entry that was updated
	*/
	@Override
	public com.kisti.osp.icebreaker.model.OSPSchedulerEntry updateOSPSchedulerEntry(
		com.kisti.osp.icebreaker.model.OSPSchedulerEntry ospSchedulerEntry) {
		return _ospSchedulerEntryLocalService.updateOSPSchedulerEntry(ospSchedulerEntry);
	}

	@Override
	public com.kisti.osp.icebreaker.model.OSPSchedulerEntry updateScheduler(
		long schedulerId, String name, String version, String serverIp,
		String sshPort, String schedulerClass, String accessMethod,
		String authorizedUser, String authorizedPassword, String description,
		String templateDir, String templateRealDir, long[] permissionGroupIds,
		int status, com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ospSchedulerEntryLocalService.updateScheduler(schedulerId,
			name, version, serverIp, sshPort, schedulerClass, accessMethod,
			authorizedUser, authorizedPassword, description, templateDir,
			templateRealDir, permissionGroupIds, status, sc);
	}

	@Override
	public com.kisti.osp.icebreaker.model.OSPSchedulerEntry updateScheduler(
		com.kisti.osp.icebreaker.model.OSPSchedulerEntry scheduler,
		long[] permissionGroupIds,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.kernel.exception.PortalException {
		return _ospSchedulerEntryLocalService.updateScheduler(scheduler,
			permissionGroupIds, sc);
	}

	@Override
	public void updateSchedulerPermissions(
		com.kisti.osp.icebreaker.model.OSPSchedulerEntry scheduler,
		long[] permissionGroupIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ospSchedulerEntryLocalService.updateSchedulerPermissions(scheduler,
			permissionGroupIds);
	}

	@Override
	public OSPSchedulerEntryLocalService getWrappedService() {
		return _ospSchedulerEntryLocalService;
	}

	@Override
	public void setWrappedService(
		OSPSchedulerEntryLocalService ospSchedulerEntryLocalService) {
		_ospSchedulerEntryLocalService = ospSchedulerEntryLocalService;
	}

	private OSPSchedulerEntryLocalService _ospSchedulerEntryLocalService;
}