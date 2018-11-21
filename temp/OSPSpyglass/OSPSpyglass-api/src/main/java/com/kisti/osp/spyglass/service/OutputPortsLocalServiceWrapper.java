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
 * Provides a wrapper for {@link OutputPortsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see OutputPortsLocalService
 * @generated
 */
@ProviderType
public class OutputPortsLocalServiceWrapper implements OutputPortsLocalService,
	ServiceWrapper<OutputPortsLocalService> {
	public OutputPortsLocalServiceWrapper(
		OutputPortsLocalService outputPortsLocalService) {
		_outputPortsLocalService = outputPortsLocalService;
	}

	/**
	* Adds the output ports to the database. Also notifies the appropriate model listeners.
	*
	* @param outputPorts the output ports
	* @return the output ports that was added
	*/
	@Override
	public com.kisti.osp.spyglass.model.OutputPorts addOutputPorts(
		com.kisti.osp.spyglass.model.OutputPorts outputPorts) {
		return _outputPortsLocalService.addOutputPorts(outputPorts);
	}

	/**
	* Creates a new output ports with the primary key. Does not add the output ports to the database.
	*
	* @param scienceAppId the primary key for the new output ports
	* @return the new output ports
	*/
	@Override
	public com.kisti.osp.spyglass.model.OutputPorts createOutputPorts(
		long scienceAppId) {
		return _outputPortsLocalService.createOutputPorts(scienceAppId);
	}

	/**
	* Deletes the output ports with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the output ports
	* @return the output ports that was removed
	* @throws PortalException if a output ports with the primary key could not be found
	*/
	@Override
	public com.kisti.osp.spyglass.model.OutputPorts deleteOutputPorts(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _outputPortsLocalService.deleteOutputPorts(scienceAppId);
	}

	/**
	* Deletes the output ports from the database. Also notifies the appropriate model listeners.
	*
	* @param outputPorts the output ports
	* @return the output ports that was removed
	*/
	@Override
	public com.kisti.osp.spyglass.model.OutputPorts deleteOutputPorts(
		com.kisti.osp.spyglass.model.OutputPorts outputPorts) {
		return _outputPortsLocalService.deleteOutputPorts(outputPorts);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _outputPortsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _outputPortsLocalService.dynamicQuery();
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
		return _outputPortsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.spyglass.model.impl.OutputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _outputPortsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.spyglass.model.impl.OutputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _outputPortsLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _outputPortsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _outputPortsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.kisti.osp.spyglass.model.OutputPorts fetchOutputPorts(
		long scienceAppId) {
		return _outputPortsLocalService.fetchOutputPorts(scienceAppId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _outputPortsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _outputPortsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _outputPortsLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Returns the output ports with the primary key.
	*
	* @param scienceAppId the primary key of the output ports
	* @return the output ports
	* @throws PortalException if a output ports with the primary key could not be found
	*/
	@Override
	public com.kisti.osp.spyglass.model.OutputPorts getOutputPorts(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _outputPortsLocalService.getOutputPorts(scienceAppId);
	}

	/**
	* Returns a range of all the output portses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.spyglass.model.impl.OutputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of output portses
	* @param end the upper bound of the range of output portses (not inclusive)
	* @return the range of output portses
	*/
	@Override
	public java.util.List<com.kisti.osp.spyglass.model.OutputPorts> getOutputPortses(
		int start, int end) {
		return _outputPortsLocalService.getOutputPortses(start, end);
	}

	/**
	* Returns the number of output portses.
	*
	* @return the number of output portses
	*/
	@Override
	public int getOutputPortsesCount() {
		return _outputPortsLocalService.getOutputPortsesCount();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _outputPortsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the output ports in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param outputPorts the output ports
	* @return the output ports that was updated
	*/
	@Override
	public com.kisti.osp.spyglass.model.OutputPorts updateOutputPorts(
		com.kisti.osp.spyglass.model.OutputPorts outputPorts) {
		return _outputPortsLocalService.updateOutputPorts(outputPorts);
	}

	@Override
	public OutputPortsLocalService getWrappedService() {
		return _outputPortsLocalService;
	}

	@Override
	public void setWrappedService(
		OutputPortsLocalService outputPortsLocalService) {
		_outputPortsLocalService = outputPortsLocalService;
	}

	private OutputPortsLocalService _outputPortsLocalService;
}