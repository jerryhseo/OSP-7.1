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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.osp.spyglass.exception.NoSuchAssignedSchedulerException;
import com.osp.spyglass.model.AssignedScheduler;

/**
 * The persistence interface for the assigned scheduler service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jerry H. Seo
 * @see com.osp.spyglass.service.persistence.impl.AssignedSchedulerPersistenceImpl
 * @see AssignedSchedulerUtil
 * @generated
 */
@ProviderType
public interface AssignedSchedulerPersistence extends BasePersistence<AssignedScheduler> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssignedSchedulerUtil} to access the assigned scheduler persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the assigned scheduler in the entity cache if it is enabled.
	*
	* @param assignedScheduler the assigned scheduler
	*/
	public void cacheResult(AssignedScheduler assignedScheduler);

	/**
	* Caches the assigned schedulers in the entity cache if it is enabled.
	*
	* @param assignedSchedulers the assigned schedulers
	*/
	public void cacheResult(
		java.util.List<AssignedScheduler> assignedSchedulers);

	/**
	* Creates a new assigned scheduler with the primary key. Does not add the assigned scheduler to the database.
	*
	* @param scienceAppId the primary key for the new assigned scheduler
	* @return the new assigned scheduler
	*/
	public AssignedScheduler create(long scienceAppId);

	/**
	* Removes the assigned scheduler with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppId the primary key of the assigned scheduler
	* @return the assigned scheduler that was removed
	* @throws NoSuchAssignedSchedulerException if a assigned scheduler with the primary key could not be found
	*/
	public AssignedScheduler remove(long scienceAppId)
		throws NoSuchAssignedSchedulerException;

	public AssignedScheduler updateImpl(AssignedScheduler assignedScheduler);

	/**
	* Returns the assigned scheduler with the primary key or throws a {@link NoSuchAssignedSchedulerException} if it could not be found.
	*
	* @param scienceAppId the primary key of the assigned scheduler
	* @return the assigned scheduler
	* @throws NoSuchAssignedSchedulerException if a assigned scheduler with the primary key could not be found
	*/
	public AssignedScheduler findByPrimaryKey(long scienceAppId)
		throws NoSuchAssignedSchedulerException;

	/**
	* Returns the assigned scheduler with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param scienceAppId the primary key of the assigned scheduler
	* @return the assigned scheduler, or <code>null</code> if a assigned scheduler with the primary key could not be found
	*/
	public AssignedScheduler fetchByPrimaryKey(long scienceAppId);

	@Override
	public java.util.Map<java.io.Serializable, AssignedScheduler> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the assigned schedulers.
	*
	* @return the assigned schedulers
	*/
	public java.util.List<AssignedScheduler> findAll();

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
	public java.util.List<AssignedScheduler> findAll(int start, int end);

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
	public java.util.List<AssignedScheduler> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssignedScheduler> orderByComparator);

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
	public java.util.List<AssignedScheduler> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssignedScheduler> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the assigned schedulers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of assigned schedulers.
	*
	* @return the number of assigned schedulers
	*/
	public int countAll();
}