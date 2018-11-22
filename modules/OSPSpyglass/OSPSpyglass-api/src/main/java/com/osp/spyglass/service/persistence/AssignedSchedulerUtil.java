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

package com.osp.spyglass.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.osp.spyglass.model.AssignedScheduler;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the assigned scheduler service. This utility wraps {@link com.osp.spyglass.service.persistence.impl.AssignedSchedulerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jerry H. Seo
 * @see AssignedSchedulerPersistence
 * @see com.osp.spyglass.service.persistence.impl.AssignedSchedulerPersistenceImpl
 * @generated
 */
@ProviderType
public class AssignedSchedulerUtil {
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
	public static void clearCache(AssignedScheduler assignedScheduler) {
		getPersistence().clearCache(assignedScheduler);
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
	public static List<AssignedScheduler> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssignedScheduler> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssignedScheduler> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AssignedScheduler> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AssignedScheduler update(AssignedScheduler assignedScheduler) {
		return getPersistence().update(assignedScheduler);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AssignedScheduler update(
		AssignedScheduler assignedScheduler, ServiceContext serviceContext) {
		return getPersistence().update(assignedScheduler, serviceContext);
	}

	/**
	* Caches the assigned scheduler in the entity cache if it is enabled.
	*
	* @param assignedScheduler the assigned scheduler
	*/
	public static void cacheResult(AssignedScheduler assignedScheduler) {
		getPersistence().cacheResult(assignedScheduler);
	}

	/**
	* Caches the assigned schedulers in the entity cache if it is enabled.
	*
	* @param assignedSchedulers the assigned schedulers
	*/
	public static void cacheResult(List<AssignedScheduler> assignedSchedulers) {
		getPersistence().cacheResult(assignedSchedulers);
	}

	/**
	* Creates a new assigned scheduler with the primary key. Does not add the assigned scheduler to the database.
	*
	* @param scienceAppId the primary key for the new assigned scheduler
	* @return the new assigned scheduler
	*/
	public static AssignedScheduler create(long scienceAppId) {
		return getPersistence().create(scienceAppId);
	}

	/**
	* Removes the assigned scheduler with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the assigned scheduler
	* @return the assigned scheduler that was removed
	* @throws NoSuchAssignedSchedulerException if a assigned scheduler with the primary key could not be found
	*/
	public static AssignedScheduler remove(long scienceAppId)
		throws com.osp.spyglass.exception.NoSuchAssignedSchedulerException {
		return getPersistence().remove(scienceAppId);
	}

	public static AssignedScheduler updateImpl(
		AssignedScheduler assignedScheduler) {
		return getPersistence().updateImpl(assignedScheduler);
	}

	/**
	* Returns the assigned scheduler with the primary key or throws a {@link NoSuchAssignedSchedulerException} if it could not be found.
	*
	* @param scienceAppId the primary key of the assigned scheduler
	* @return the assigned scheduler
	* @throws NoSuchAssignedSchedulerException if a assigned scheduler with the primary key could not be found
	*/
	public static AssignedScheduler findByPrimaryKey(long scienceAppId)
		throws com.osp.spyglass.exception.NoSuchAssignedSchedulerException {
		return getPersistence().findByPrimaryKey(scienceAppId);
	}

	/**
	* Returns the assigned scheduler with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param scienceAppId the primary key of the assigned scheduler
	* @return the assigned scheduler, or <code>null</code> if a assigned scheduler with the primary key could not be found
	*/
	public static AssignedScheduler fetchByPrimaryKey(long scienceAppId) {
		return getPersistence().fetchByPrimaryKey(scienceAppId);
	}

	public static java.util.Map<java.io.Serializable, AssignedScheduler> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the assigned schedulers.
	*
	* @return the assigned schedulers
	*/
	public static List<AssignedScheduler> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the assigned schedulers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssignedSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of assigned schedulers
	* @param end the upper bound of the range of assigned schedulers (not inclusive)
	* @return the range of assigned schedulers
	*/
	public static List<AssignedScheduler> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the assigned schedulers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssignedSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of assigned schedulers
	* @param end the upper bound of the range of assigned schedulers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of assigned schedulers
	*/
	public static List<AssignedScheduler> findAll(int start, int end,
		OrderByComparator<AssignedScheduler> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the assigned schedulers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssignedSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of assigned schedulers
	* @param end the upper bound of the range of assigned schedulers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of assigned schedulers
	*/
	public static List<AssignedScheduler> findAll(int start, int end,
		OrderByComparator<AssignedScheduler> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the assigned schedulers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of assigned schedulers.
	*
	* @return the number of assigned schedulers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AssignedSchedulerPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AssignedSchedulerPersistence, AssignedSchedulerPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AssignedSchedulerPersistence.class);

		ServiceTracker<AssignedSchedulerPersistence, AssignedSchedulerPersistence> serviceTracker =
			new ServiceTracker<AssignedSchedulerPersistence, AssignedSchedulerPersistence>(bundle.getBundleContext(),
				AssignedSchedulerPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}