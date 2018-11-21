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
 * Provides a wrapper for {@link LogPortsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LogPortsLocalService
 * @generated
 */
@ProviderType
public class LogPortsLocalServiceWrapper implements LogPortsLocalService,
	ServiceWrapper<LogPortsLocalService> {
	public LogPortsLocalServiceWrapper(
		LogPortsLocalService logPortsLocalService) {
		_logPortsLocalService = logPortsLocalService;
	}

	/**
	* Adds the log ports to the database. Also notifies the appropriate model listeners.
	*
	* @param logPorts the log ports
	* @return the log ports that was added
	*/
	@Override
	public com.kisti.osp.spyglass.model.LogPorts addLogPorts(
		com.kisti.osp.spyglass.model.LogPorts logPorts) {
		return _logPortsLocalService.addLogPorts(logPorts);
	}

	@Override
	public com.kisti.osp.spyglass.model.LogPorts addLogPorts(
		long scienceAppId, java.lang.String logPorts) {
		return _logPortsLocalService.addLogPorts(scienceAppId, logPorts);
	}

	/**
	* Creates a new log ports with the primary key. Does not add the log ports to the database.
	*
	* @param scienceAppId the primary key for the new log ports
	* @return the new log ports
	*/
	@Override
	public com.kisti.osp.spyglass.model.LogPorts createLogPorts(
		long scienceAppId) {
		return _logPortsLocalService.createLogPorts(scienceAppId);
	}

	/**
	* Deletes the log ports from the database. Also notifies the appropriate model listeners.
	*
	* @param logPorts the log ports
	* @return the log ports that was removed
	*/
	@Override
	public com.kisti.osp.spyglass.model.LogPorts deleteLogPorts(
		com.kisti.osp.spyglass.model.LogPorts logPorts) {
		return _logPortsLocalService.deleteLogPorts(logPorts);
	}

	/**
	* Deletes the log ports with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the log ports
	* @return the log ports that was removed
	* @throws PortalException if a log ports with the primary key could not be found
	*/
	@Override
	public com.kisti.osp.spyglass.model.LogPorts deleteLogPorts(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _logPortsLocalService.deleteLogPorts(scienceAppId);
	}

	@Override
	public com.kisti.osp.spyglass.model.LogPorts fetchLogPorts(
		long scienceAppId) {
		return _logPortsLocalService.fetchLogPorts(scienceAppId);
	}

	/**
	* Returns the log ports with the primary key.
	*
	* @param scienceAppId the primary key of the log ports
	* @return the log ports
	* @throws PortalException if a log ports with the primary key could not be found
	*/
	@Override
	public com.kisti.osp.spyglass.model.LogPorts getLogPorts(long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _logPortsLocalService.getLogPorts(scienceAppId);
	}

	/**
	* Updates the log ports in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param logPorts the log ports
	* @return the log ports that was updated
	*/
	@Override
	public com.kisti.osp.spyglass.model.LogPorts updateLogPorts(
		com.kisti.osp.spyglass.model.LogPorts logPorts) {
		return _logPortsLocalService.updateLogPorts(logPorts);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _logPortsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _logPortsLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _logPortsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _logPortsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _logPortsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of log portses.
	*
	* @return the number of log portses
	*/
	@Override
	public int getLogPortsesCount() {
		return _logPortsLocalService.getLogPortsesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _logPortsLocalService.getOSGiServiceIdentifier();
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
		return _logPortsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.spyglass.model.impl.LogPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _logPortsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.spyglass.model.impl.LogPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _logPortsLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the log portses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.spyglass.model.impl.LogPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of log portses
	* @param end the upper bound of the range of log portses (not inclusive)
	* @return the range of log portses
	*/
	@Override
	public java.util.List<com.kisti.osp.spyglass.model.LogPorts> getLogPortses(
		int start, int end) {
		return _logPortsLocalService.getLogPortses(start, end);
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
		return _logPortsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _logPortsLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public LogPortsLocalService getWrappedService() {
		return _logPortsLocalService;
	}

	@Override
	public void setWrappedService(LogPortsLocalService logPortsLocalService) {
		_logPortsLocalService = logPortsLocalService;
	}

	private LogPortsLocalService _logPortsLocalService;
}