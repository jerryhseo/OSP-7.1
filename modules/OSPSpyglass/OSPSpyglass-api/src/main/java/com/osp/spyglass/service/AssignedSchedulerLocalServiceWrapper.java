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

package com.osp.spyglass.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssignedSchedulerLocalService}.
 *
 * @author Jerry H. Seo
 * @see AssignedSchedulerLocalService
 * @generated
 */
@ProviderType
public class AssignedSchedulerLocalServiceWrapper
	implements AssignedSchedulerLocalService,
		ServiceWrapper<AssignedSchedulerLocalService> {
	public AssignedSchedulerLocalServiceWrapper(
		AssignedSchedulerLocalService assignedSchedulerLocalService) {
		_assignedSchedulerLocalService = assignedSchedulerLocalService;
	}

	/**
	* Adds the assigned scheduler to the database. Also notifies the appropriate model listeners.
	*
	* @param assignedScheduler the assigned scheduler
	* @return the assigned scheduler that was added
	*/
	@Override
	public com.osp.spyglass.model.AssignedScheduler addAssignedScheduler(
		com.osp.spyglass.model.AssignedScheduler assignedScheduler) {
		return _assignedSchedulerLocalService.addAssignedScheduler(assignedScheduler);
	}

	/**
	* Creates a new assigned scheduler with the primary key. Does not add the assigned scheduler to the database.
	*
	* @param scienceAppId the primary key for the new assigned scheduler
	* @return the new assigned scheduler
	*/
	@Override
	public com.osp.spyglass.model.AssignedScheduler createAssignedScheduler(
		long scienceAppId) {
		return _assignedSchedulerLocalService.createAssignedScheduler(scienceAppId);
	}

	/**
	* Deletes the assigned scheduler from the database. Also notifies the appropriate model listeners.
	*
	* @param assignedScheduler the assigned scheduler
	* @return the assigned scheduler that was removed
	*/
	@Override
	public com.osp.spyglass.model.AssignedScheduler deleteAssignedScheduler(
		com.osp.spyglass.model.AssignedScheduler assignedScheduler) {
		return _assignedSchedulerLocalService.deleteAssignedScheduler(assignedScheduler);
	}

	/**
	* Deletes the assigned scheduler with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the assigned scheduler
	* @return the assigned scheduler that was removed
	* @throws PortalException if a assigned scheduler with the primary key could not be found
	*/
	@Override
	public com.osp.spyglass.model.AssignedScheduler deleteAssignedScheduler(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assignedSchedulerLocalService.deleteAssignedScheduler(scienceAppId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assignedSchedulerLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assignedSchedulerLocalService.dynamicQuery();
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
		return _assignedSchedulerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.osp.spyglass.model.impl.AssignedSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assignedSchedulerLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.osp.spyglass.model.impl.AssignedSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assignedSchedulerLocalService.dynamicQuery(dynamicQuery, start,
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
		return _assignedSchedulerLocalService.dynamicQueryCount(dynamicQuery);
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
		return _assignedSchedulerLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.osp.spyglass.model.AssignedScheduler fetchAssignedScheduler(
		long scienceAppId) {
		return _assignedSchedulerLocalService.fetchAssignedScheduler(scienceAppId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _assignedSchedulerLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the assigned scheduler with the primary key.
	*
	* @param scienceAppId the primary key of the assigned scheduler
	* @return the assigned scheduler
	* @throws PortalException if a assigned scheduler with the primary key could not be found
	*/
	@Override
	public com.osp.spyglass.model.AssignedScheduler getAssignedScheduler(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assignedSchedulerLocalService.getAssignedScheduler(scienceAppId);
	}

	/**
	* Returns a range of all the assigned schedulers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.osp.spyglass.model.impl.AssignedSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of assigned schedulers
	* @param end the upper bound of the range of assigned schedulers (not inclusive)
	* @return the range of assigned schedulers
	*/
	@Override
	public java.util.List<com.osp.spyglass.model.AssignedScheduler> getAssignedSchedulers(
		int start, int end) {
		return _assignedSchedulerLocalService.getAssignedSchedulers(start, end);
	}

	/**
	* Returns the number of assigned schedulers.
	*
	* @return the number of assigned schedulers
	*/
	@Override
	public int getAssignedSchedulersCount() {
		return _assignedSchedulerLocalService.getAssignedSchedulersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _assignedSchedulerLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _assignedSchedulerLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assignedSchedulerLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.osp.spyglass.model.AssignedScheduler setSchedulers(
		long scienceAppId, String schedulers) {
		return _assignedSchedulerLocalService.setSchedulers(scienceAppId,
			schedulers);
	}

	/**
	* Updates the assigned scheduler in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assignedScheduler the assigned scheduler
	* @return the assigned scheduler that was updated
	*/
	@Override
	public com.osp.spyglass.model.AssignedScheduler updateAssignedScheduler(
		com.osp.spyglass.model.AssignedScheduler assignedScheduler) {
		return _assignedSchedulerLocalService.updateAssignedScheduler(assignedScheduler);
	}

	@Override
	public AssignedSchedulerLocalService getWrappedService() {
		return _assignedSchedulerLocalService;
	}

	@Override
	public void setWrappedService(
		AssignedSchedulerLocalService assignedSchedulerLocalService) {
		_assignedSchedulerLocalService = assignedSchedulerLocalService;
	}

	private AssignedSchedulerLocalService _assignedSchedulerLocalService;
}