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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for InputPorts. This utility wraps
 * {@link com.osp.spyglass.service.impl.InputPortsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Jerry H. Seo
 * @see InputPortsLocalService
 * @see com.osp.spyglass.service.base.InputPortsLocalServiceBaseImpl
 * @see com.osp.spyglass.service.impl.InputPortsLocalServiceImpl
 * @generated
 */
@ProviderType
public class InputPortsLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.osp.spyglass.service.impl.InputPortsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the input ports to the database. Also notifies the appropriate model listeners.
	*
	* @param inputPorts the input ports
	* @return the input ports that was added
	*/
	public static com.osp.spyglass.model.InputPorts addInputPorts(
		com.osp.spyglass.model.InputPorts inputPorts) {
		return getService().addInputPorts(inputPorts);
	}

	/**
	* Creates a new input ports with the primary key. Does not add the input ports to the database.
	*
	* @param scienceAppId the primary key for the new input ports
	* @return the new input ports
	*/
	public static com.osp.spyglass.model.InputPorts createInputPorts(
		long scienceAppId) {
		return getService().createInputPorts(scienceAppId);
	}

	/**
	* Deletes the input ports from the database. Also notifies the appropriate model listeners.
	*
	* @param inputPorts the input ports
	* @return the input ports that was removed
	*/
	public static com.osp.spyglass.model.InputPorts deleteInputPorts(
		com.osp.spyglass.model.InputPorts inputPorts) {
		return getService().deleteInputPorts(inputPorts);
	}

	/**
	* Deletes the input ports with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the input ports
	* @return the input ports that was removed
	* @throws PortalException if a input ports with the primary key could not be found
	*/
	public static com.osp.spyglass.model.InputPorts deleteInputPorts(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteInputPorts(scienceAppId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.osp.spyglass.model.impl.InputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.osp.spyglass.model.impl.InputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.osp.spyglass.model.InputPorts fetchInputPorts(
		long scienceAppId) {
		return getService().fetchInputPorts(scienceAppId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the input ports with the primary key.
	*
	* @param scienceAppId the primary key of the input ports
	* @return the input ports
	* @throws PortalException if a input ports with the primary key could not be found
	*/
	public static com.osp.spyglass.model.InputPorts getInputPorts(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getInputPorts(scienceAppId);
	}

	/**
	* Returns a range of all the input portses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.osp.spyglass.model.impl.InputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of input portses
	* @param end the upper bound of the range of input portses (not inclusive)
	* @return the range of input portses
	*/
	public static java.util.List<com.osp.spyglass.model.InputPorts> getInputPortses(
		int start, int end) {
		return getService().getInputPortses(start, end);
	}

	/**
	* Returns the number of input portses.
	*
	* @return the number of input portses
	*/
	public static int getInputPortsesCount() {
		return getService().getInputPortsesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static com.osp.spyglass.model.InputPorts setInputPorts(
		long scienceAppId, String inputPorts) {
		return getService().setInputPorts(scienceAppId, inputPorts);
	}

	/**
	* Updates the input ports in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param inputPorts the input ports
	* @return the input ports that was updated
	*/
	public static com.osp.spyglass.model.InputPorts updateInputPorts(
		com.osp.spyglass.model.InputPorts inputPorts) {
		return getService().updateInputPorts(inputPorts);
	}

	public static InputPortsLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<InputPortsLocalService, InputPortsLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(InputPortsLocalService.class);

		ServiceTracker<InputPortsLocalService, InputPortsLocalService> serviceTracker =
			new ServiceTracker<InputPortsLocalService, InputPortsLocalService>(bundle.getBundleContext(),
				InputPortsLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}