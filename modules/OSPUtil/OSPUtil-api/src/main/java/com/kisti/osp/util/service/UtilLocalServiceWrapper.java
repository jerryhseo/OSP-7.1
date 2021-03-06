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

package com.kisti.osp.util.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UtilLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UtilLocalService
 * @generated
 */
@ProviderType
public class UtilLocalServiceWrapper implements UtilLocalService,
	ServiceWrapper<UtilLocalService> {
	public UtilLocalServiceWrapper(UtilLocalService utilLocalService) {
		_utilLocalService = utilLocalService;
	}

	/**
	* Adds the util to the database. Also notifies the appropriate model listeners.
	*
	* @param util the util
	* @return the util that was added
	*/
	@Override
	public com.kisti.osp.util.model.Util addUtil(
		com.kisti.osp.util.model.Util util) {
		return _utilLocalService.addUtil(util);
	}

	/**
	* Creates a new util with the primary key. Does not add the util to the database.
	*
	* @param utilId the primary key for the new util
	* @return the new util
	*/
	@Override
	public com.kisti.osp.util.model.Util createUtil(long utilId) {
		return _utilLocalService.createUtil(utilId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _utilLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the util with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param utilId the primary key of the util
	* @return the util that was removed
	* @throws PortalException if a util with the primary key could not be found
	*/
	@Override
	public com.kisti.osp.util.model.Util deleteUtil(long utilId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _utilLocalService.deleteUtil(utilId);
	}

	/**
	* Deletes the util from the database. Also notifies the appropriate model listeners.
	*
	* @param util the util
	* @return the util that was removed
	*/
	@Override
	public com.kisti.osp.util.model.Util deleteUtil(
		com.kisti.osp.util.model.Util util) {
		return _utilLocalService.deleteUtil(util);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _utilLocalService.dynamicQuery();
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
		return _utilLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.util.model.impl.UtilModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _utilLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.util.model.impl.UtilModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _utilLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _utilLocalService.dynamicQueryCount(dynamicQuery);
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
		return _utilLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.kisti.osp.util.model.Util fetchUtil(long utilId) {
		return _utilLocalService.fetchUtil(utilId);
	}

	/**
	* Returns the util matching the UUID and group.
	*
	* @param uuid the util's UUID
	* @param groupId the primary key of the group
	* @return the matching util, or <code>null</code> if a matching util could not be found
	*/
	@Override
	public com.kisti.osp.util.model.Util fetchUtilByUuidAndGroupId(
		String uuid, long groupId) {
		return _utilLocalService.fetchUtilByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _utilLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _utilLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _utilLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _utilLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the util with the primary key.
	*
	* @param utilId the primary key of the util
	* @return the util
	* @throws PortalException if a util with the primary key could not be found
	*/
	@Override
	public com.kisti.osp.util.model.Util getUtil(long utilId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _utilLocalService.getUtil(utilId);
	}

	/**
	* Returns the util matching the UUID and group.
	*
	* @param uuid the util's UUID
	* @param groupId the primary key of the group
	* @return the matching util
	* @throws PortalException if a matching util could not be found
	*/
	@Override
	public com.kisti.osp.util.model.Util getUtilByUuidAndGroupId(String uuid,
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _utilLocalService.getUtilByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the utils.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.util.model.impl.UtilModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of utils
	* @param end the upper bound of the range of utils (not inclusive)
	* @return the range of utils
	*/
	@Override
	public java.util.List<com.kisti.osp.util.model.Util> getUtils(int start,
		int end) {
		return _utilLocalService.getUtils(start, end);
	}

	/**
	* Returns all the utils matching the UUID and company.
	*
	* @param uuid the UUID of the utils
	* @param companyId the primary key of the company
	* @return the matching utils, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.kisti.osp.util.model.Util> getUtilsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _utilLocalService.getUtilsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of utils matching the UUID and company.
	*
	* @param uuid the UUID of the utils
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of utils
	* @param end the upper bound of the range of utils (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching utils, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.kisti.osp.util.model.Util> getUtilsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.kisti.osp.util.model.Util> orderByComparator) {
		return _utilLocalService.getUtilsByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of utils.
	*
	* @return the number of utils
	*/
	@Override
	public int getUtilsCount() {
		return _utilLocalService.getUtilsCount();
	}

	/**
	* Updates the util in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param util the util
	* @return the util that was updated
	*/
	@Override
	public com.kisti.osp.util.model.Util updateUtil(
		com.kisti.osp.util.model.Util util) {
		return _utilLocalService.updateUtil(util);
	}

	@Override
	public UtilLocalService getWrappedService() {
		return _utilLocalService;
	}

	@Override
	public void setWrappedService(UtilLocalService utilLocalService) {
		_utilLocalService = utilLocalService;
	}

	private UtilLocalService _utilLocalService;
}