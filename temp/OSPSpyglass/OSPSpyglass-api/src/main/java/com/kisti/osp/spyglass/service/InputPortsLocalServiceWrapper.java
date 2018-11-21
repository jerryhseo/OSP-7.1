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
 * Provides a wrapper for {@link InputPortsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see InputPortsLocalService
 * @generated
 */
@ProviderType
public class InputPortsLocalServiceWrapper implements InputPortsLocalService,
	ServiceWrapper<InputPortsLocalService> {
	public InputPortsLocalServiceWrapper(
		InputPortsLocalService inputPortsLocalService) {
		_inputPortsLocalService = inputPortsLocalService;
	}

	/**
	* Adds the input ports to the database. Also notifies the appropriate model listeners.
	*
	* @param inputPorts the input ports
	* @return the input ports that was added
	*/
	@Override
	public com.kisti.osp.spyglass.model.InputPorts addInputPorts(
		com.kisti.osp.spyglass.model.InputPorts inputPorts) {
		return _inputPortsLocalService.addInputPorts(inputPorts);
	}

	/**
	* Creates a new input ports with the primary key. Does not add the input ports to the database.
	*
	* @param scienceAppId the primary key for the new input ports
	* @return the new input ports
	*/
	@Override
	public com.kisti.osp.spyglass.model.InputPorts createInputPorts(
		long scienceAppId) {
		return _inputPortsLocalService.createInputPorts(scienceAppId);
	}

	/**
	* Deletes the input ports from the database. Also notifies the appropriate model listeners.
	*
	* @param inputPorts the input ports
	* @return the input ports that was removed
	*/
	@Override
	public com.kisti.osp.spyglass.model.InputPorts deleteInputPorts(
		com.kisti.osp.spyglass.model.InputPorts inputPorts) {
		return _inputPortsLocalService.deleteInputPorts(inputPorts);
	}

	/**
	* Deletes the input ports with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the input ports
	* @return the input ports that was removed
	* @throws PortalException if a input ports with the primary key could not be found
	*/
	@Override
	public com.kisti.osp.spyglass.model.InputPorts deleteInputPorts(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _inputPortsLocalService.deleteInputPorts(scienceAppId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _inputPortsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _inputPortsLocalService.dynamicQuery();
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
		return _inputPortsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.spyglass.model.impl.InputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _inputPortsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.spyglass.model.impl.InputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _inputPortsLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _inputPortsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _inputPortsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.kisti.osp.spyglass.model.InputPorts fetchInputPorts(
		long scienceAppId) {
		return _inputPortsLocalService.fetchInputPorts(scienceAppId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _inputPortsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _inputPortsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the input ports with the primary key.
	*
	* @param scienceAppId the primary key of the input ports
	* @return the input ports
	* @throws PortalException if a input ports with the primary key could not be found
	*/
	@Override
	public com.kisti.osp.spyglass.model.InputPorts getInputPorts(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _inputPortsLocalService.getInputPorts(scienceAppId);
	}

	/**
	* Returns a range of all the input portses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.spyglass.model.impl.InputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of input portses
	* @param end the upper bound of the range of input portses (not inclusive)
	* @return the range of input portses
	*/
	@Override
	public java.util.List<com.kisti.osp.spyglass.model.InputPorts> getInputPortses(
		int start, int end) {
		return _inputPortsLocalService.getInputPortses(start, end);
	}

	/**
	* Returns the number of input portses.
	*
	* @return the number of input portses
	*/
	@Override
	public int getInputPortsesCount() {
		return _inputPortsLocalService.getInputPortsesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _inputPortsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _inputPortsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the input ports in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param inputPorts the input ports
	* @return the input ports that was updated
	*/
	@Override
	public com.kisti.osp.spyglass.model.InputPorts updateInputPorts(
		com.kisti.osp.spyglass.model.InputPorts inputPorts) {
		return _inputPortsLocalService.updateInputPorts(inputPorts);
	}

	@Override
	public InputPortsLocalService getWrappedService() {
		return _inputPortsLocalService;
	}

	@Override
	public void setWrappedService(InputPortsLocalService inputPortsLocalService) {
		_inputPortsLocalService = inputPortsLocalService;
	}

	private InputPortsLocalService _inputPortsLocalService;
}