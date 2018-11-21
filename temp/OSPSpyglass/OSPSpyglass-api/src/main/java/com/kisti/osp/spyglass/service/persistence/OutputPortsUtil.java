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

package com.kisti.osp.spyglass.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.kisti.osp.spyglass.model.OutputPorts;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the output ports service. This utility wraps {@link com.kisti.osp.spyglass.service.persistence.impl.OutputPortsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OutputPortsPersistence
 * @see com.kisti.osp.spyglass.service.persistence.impl.OutputPortsPersistenceImpl
 * @generated
 */
@ProviderType
public class OutputPortsUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(OutputPorts outputPorts) {
		getPersistence().clearCache(outputPorts);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<OutputPorts> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OutputPorts> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OutputPorts> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OutputPorts> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OutputPorts update(OutputPorts outputPorts) {
		return getPersistence().update(outputPorts);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OutputPorts update(OutputPorts outputPorts,
		ServiceContext serviceContext) {
		return getPersistence().update(outputPorts, serviceContext);
	}

	/**
	* Caches the output ports in the entity cache if it is enabled.
	*
	* @param outputPorts the output ports
	*/
	public static void cacheResult(OutputPorts outputPorts) {
		getPersistence().cacheResult(outputPorts);
	}

	/**
	* Caches the output portses in the entity cache if it is enabled.
	*
	* @param outputPortses the output portses
	*/
	public static void cacheResult(List<OutputPorts> outputPortses) {
		getPersistence().cacheResult(outputPortses);
	}

	/**
	* Creates a new output ports with the primary key. Does not add the output ports to the database.
	*
	* @param scienceAppId the primary key for the new output ports
	* @return the new output ports
	*/
	public static OutputPorts create(long scienceAppId) {
		return getPersistence().create(scienceAppId);
	}

	/**
	* Removes the output ports with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the output ports
	* @return the output ports that was removed
	* @throws NoSuchOutputPortsException if a output ports with the primary key could not be found
	*/
	public static OutputPorts remove(long scienceAppId)
		throws com.kisti.osp.spyglass.exception.NoSuchOutputPortsException {
		return getPersistence().remove(scienceAppId);
	}

	public static OutputPorts updateImpl(OutputPorts outputPorts) {
		return getPersistence().updateImpl(outputPorts);
	}

	/**
	* Returns the output ports with the primary key or throws a {@link NoSuchOutputPortsException} if it could not be found.
	*
	* @param scienceAppId the primary key of the output ports
	* @return the output ports
	* @throws NoSuchOutputPortsException if a output ports with the primary key could not be found
	*/
	public static OutputPorts findByPrimaryKey(long scienceAppId)
		throws com.kisti.osp.spyglass.exception.NoSuchOutputPortsException {
		return getPersistence().findByPrimaryKey(scienceAppId);
	}

	/**
	* Returns the output ports with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param scienceAppId the primary key of the output ports
	* @return the output ports, or <code>null</code> if a output ports with the primary key could not be found
	*/
	public static OutputPorts fetchByPrimaryKey(long scienceAppId) {
		return getPersistence().fetchByPrimaryKey(scienceAppId);
	}

	public static java.util.Map<java.io.Serializable, OutputPorts> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the output portses.
	*
	* @return the output portses
	*/
	public static List<OutputPorts> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the output portses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OutputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of output portses
	* @param end the upper bound of the range of output portses (not inclusive)
	* @return the range of output portses
	*/
	public static List<OutputPorts> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the output portses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OutputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of output portses
	* @param end the upper bound of the range of output portses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of output portses
	*/
	public static List<OutputPorts> findAll(int start, int end,
		OrderByComparator<OutputPorts> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the output portses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OutputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of output portses
	* @param end the upper bound of the range of output portses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of output portses
	*/
	public static List<OutputPorts> findAll(int start, int end,
		OrderByComparator<OutputPorts> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the output portses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of output portses.
	*
	* @return the number of output portses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static OutputPortsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OutputPortsPersistence, OutputPortsPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OutputPortsPersistence.class);

		ServiceTracker<OutputPortsPersistence, OutputPortsPersistence> serviceTracker =
			new ServiceTracker<OutputPortsPersistence, OutputPortsPersistence>(bundle.getBundleContext(),
				OutputPortsPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}