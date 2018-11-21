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

package com.kisti.osp.icebreaker.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.kisti.osp.icebreaker.model.OSPSchedulerEntry;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the osp scheduler entry service. This utility wraps {@link com.kisti.osp.icebreaker.service.persistence.impl.OSPSchedulerEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jerry H. Seo
 * @see OSPSchedulerEntryPersistence
 * @see com.kisti.osp.icebreaker.service.persistence.impl.OSPSchedulerEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class OSPSchedulerEntryUtil {
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
	public static void clearCache(OSPSchedulerEntry ospSchedulerEntry) {
		getPersistence().clearCache(ospSchedulerEntry);
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
	public static List<OSPSchedulerEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OSPSchedulerEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OSPSchedulerEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OSPSchedulerEntry update(OSPSchedulerEntry ospSchedulerEntry) {
		return getPersistence().update(ospSchedulerEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OSPSchedulerEntry update(
		OSPSchedulerEntry ospSchedulerEntry, ServiceContext serviceContext) {
		return getPersistence().update(ospSchedulerEntry, serviceContext);
	}

	/**
	* Returns all the osp scheduler entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the osp scheduler entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @return the range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByUuid(String uuid, int start,
		int end, OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByUuid(String uuid, int start,
		int end, OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first osp scheduler entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry findByUuid_First(String uuid,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first osp scheduler entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByUuid_First(String uuid,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last osp scheduler entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry findByUuid_Last(String uuid,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last osp scheduler entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByUuid_Last(String uuid,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the osp scheduler entries before and after the current osp scheduler entry in the ordered set where uuid = &#63;.
	*
	* @param schedulerEntryId the primary key of the current osp scheduler entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public static OSPSchedulerEntry[] findByUuid_PrevAndNext(
		long schedulerEntryId, String uuid,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence()
				   .findByUuid_PrevAndNext(schedulerEntryId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the osp scheduler entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of osp scheduler entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching osp scheduler entries
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the osp scheduler entry where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOSPSchedulerEntryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry findByUUID_G(String uuid, long groupId)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the osp scheduler entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the osp scheduler entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the osp scheduler entry where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the osp scheduler entry that was removed
	*/
	public static OSPSchedulerEntry removeByUUID_G(String uuid, long groupId)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of osp scheduler entries where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching osp scheduler entries
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the osp scheduler entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the osp scheduler entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @return the range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first osp scheduler entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first osp scheduler entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last osp scheduler entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry findByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last osp scheduler entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the osp scheduler entries before and after the current osp scheduler entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param schedulerEntryId the primary key of the current osp scheduler entry
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public static OSPSchedulerEntry[] findByUuid_C_PrevAndNext(
		long schedulerEntryId, String uuid, long companyId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(schedulerEntryId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the osp scheduler entries where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of osp scheduler entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching osp scheduler entries
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the osp scheduler entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the osp scheduler entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @return the range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByGroupId(long groupId,
		int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first osp scheduler entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry findByGroupId_First(long groupId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first osp scheduler entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByGroupId_First(long groupId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last osp scheduler entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry findByGroupId_Last(long groupId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last osp scheduler entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByGroupId_Last(long groupId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the osp scheduler entries before and after the current osp scheduler entry in the ordered set where groupId = &#63;.
	*
	* @param schedulerEntryId the primary key of the current osp scheduler entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public static OSPSchedulerEntry[] findByGroupId_PrevAndNext(
		long schedulerEntryId, long groupId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(schedulerEntryId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the osp scheduler entries that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching osp scheduler entries that the user has permission to view
	*/
	public static List<OSPSchedulerEntry> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Returns a range of all the osp scheduler entries that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @return the range of matching osp scheduler entries that the user has permission to view
	*/
	public static List<OSPSchedulerEntry> filterFindByGroupId(long groupId,
		int start, int end) {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching osp scheduler entries that the user has permission to view
	*/
	public static List<OSPSchedulerEntry> filterFindByGroupId(long groupId,
		int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the osp scheduler entries before and after the current osp scheduler entry in the ordered set of osp scheduler entries that the user has permission to view where groupId = &#63;.
	*
	* @param schedulerEntryId the primary key of the current osp scheduler entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public static OSPSchedulerEntry[] filterFindByGroupId_PrevAndNext(
		long schedulerEntryId, long groupId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(schedulerEntryId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the osp scheduler entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of osp scheduler entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching osp scheduler entries
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of osp scheduler entries that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching osp scheduler entries that the user has permission to view
	*/
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Returns all the osp scheduler entries where name = &#63;.
	*
	* @param name the name
	* @return the matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByName(String name) {
		return getPersistence().findByName(name);
	}

	/**
	* Returns a range of all the osp scheduler entries where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @return the range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByName(String name, int start,
		int end) {
		return getPersistence().findByName(name, start, end);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByName(String name, int start,
		int end, OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence().findByName(name, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByName(String name, int start,
		int end, OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByName(name, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first osp scheduler entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry findByName_First(String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence().findByName_First(name, orderByComparator);
	}

	/**
	* Returns the first osp scheduler entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByName_First(String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence().fetchByName_First(name, orderByComparator);
	}

	/**
	* Returns the last osp scheduler entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry findByName_Last(String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence().findByName_Last(name, orderByComparator);
	}

	/**
	* Returns the last osp scheduler entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByName_Last(String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence().fetchByName_Last(name, orderByComparator);
	}

	/**
	* Returns the osp scheduler entries before and after the current osp scheduler entry in the ordered set where name = &#63;.
	*
	* @param schedulerEntryId the primary key of the current osp scheduler entry
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public static OSPSchedulerEntry[] findByName_PrevAndNext(
		long schedulerEntryId, String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence()
				   .findByName_PrevAndNext(schedulerEntryId, name,
			orderByComparator);
	}

	/**
	* Removes all the osp scheduler entries where name = &#63; from the database.
	*
	* @param name the name
	*/
	public static void removeByName(String name) {
		getPersistence().removeByName(name);
	}

	/**
	* Returns the number of osp scheduler entries where name = &#63;.
	*
	* @param name the name
	* @return the number of matching osp scheduler entries
	*/
	public static int countByName(String name) {
		return getPersistence().countByName(name);
	}

	/**
	* Returns all the osp scheduler entries where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByG_Name(long groupId, String name) {
		return getPersistence().findByG_Name(groupId, name);
	}

	/**
	* Returns a range of all the osp scheduler entries where groupId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @return the range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByG_Name(long groupId,
		String name, int start, int end) {
		return getPersistence().findByG_Name(groupId, name, start, end);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries where groupId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByG_Name(long groupId,
		String name, int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence()
				   .findByG_Name(groupId, name, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries where groupId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByG_Name(long groupId,
		String name, int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_Name(groupId, name, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first osp scheduler entry in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry findByG_Name_First(long groupId,
		String name, OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence()
				   .findByG_Name_First(groupId, name, orderByComparator);
	}

	/**
	* Returns the first osp scheduler entry in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByG_Name_First(long groupId,
		String name, OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence()
				   .fetchByG_Name_First(groupId, name, orderByComparator);
	}

	/**
	* Returns the last osp scheduler entry in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry findByG_Name_Last(long groupId,
		String name, OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence()
				   .findByG_Name_Last(groupId, name, orderByComparator);
	}

	/**
	* Returns the last osp scheduler entry in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByG_Name_Last(long groupId,
		String name, OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence()
				   .fetchByG_Name_Last(groupId, name, orderByComparator);
	}

	/**
	* Returns the osp scheduler entries before and after the current osp scheduler entry in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param schedulerEntryId the primary key of the current osp scheduler entry
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public static OSPSchedulerEntry[] findByG_Name_PrevAndNext(
		long schedulerEntryId, long groupId, String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence()
				   .findByG_Name_PrevAndNext(schedulerEntryId, groupId, name,
			orderByComparator);
	}

	/**
	* Returns all the osp scheduler entries that the user has permission to view where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching osp scheduler entries that the user has permission to view
	*/
	public static List<OSPSchedulerEntry> filterFindByG_Name(long groupId,
		String name) {
		return getPersistence().filterFindByG_Name(groupId, name);
	}

	/**
	* Returns a range of all the osp scheduler entries that the user has permission to view where groupId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @return the range of matching osp scheduler entries that the user has permission to view
	*/
	public static List<OSPSchedulerEntry> filterFindByG_Name(long groupId,
		String name, int start, int end) {
		return getPersistence().filterFindByG_Name(groupId, name, start, end);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries that the user has permissions to view where groupId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching osp scheduler entries that the user has permission to view
	*/
	public static List<OSPSchedulerEntry> filterFindByG_Name(long groupId,
		String name, int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence()
				   .filterFindByG_Name(groupId, name, start, end,
			orderByComparator);
	}

	/**
	* Returns the osp scheduler entries before and after the current osp scheduler entry in the ordered set of osp scheduler entries that the user has permission to view where groupId = &#63; and name LIKE &#63;.
	*
	* @param schedulerEntryId the primary key of the current osp scheduler entry
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public static OSPSchedulerEntry[] filterFindByG_Name_PrevAndNext(
		long schedulerEntryId, long groupId, String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence()
				   .filterFindByG_Name_PrevAndNext(schedulerEntryId, groupId,
			name, orderByComparator);
	}

	/**
	* Removes all the osp scheduler entries where groupId = &#63; and name LIKE &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	*/
	public static void removeByG_Name(long groupId, String name) {
		getPersistence().removeByG_Name(groupId, name);
	}

	/**
	* Returns the number of osp scheduler entries where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the number of matching osp scheduler entries
	*/
	public static int countByG_Name(long groupId, String name) {
		return getPersistence().countByG_Name(groupId, name);
	}

	/**
	* Returns the number of osp scheduler entries that the user has permission to view where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the number of matching osp scheduler entries that the user has permission to view
	*/
	public static int filterCountByG_Name(long groupId, String name) {
		return getPersistence().filterCountByG_Name(groupId, name);
	}

	/**
	* Returns all the osp scheduler entries where status = &#63;.
	*
	* @param status the status
	* @return the matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByStatus(int status) {
		return getPersistence().findByStatus(status);
	}

	/**
	* Returns a range of all the osp scheduler entries where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @return the range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByStatus(int status, int start,
		int end) {
		return getPersistence().findByStatus(status, start, end);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByStatus(int status, int start,
		int end, OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence()
				   .findByStatus(status, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByStatus(int status, int start,
		int end, OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByStatus(status, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first osp scheduler entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry findByStatus_First(int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence().findByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the first osp scheduler entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByStatus_First(int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence().fetchByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the last osp scheduler entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry findByStatus_Last(int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence().findByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the last osp scheduler entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByStatus_Last(int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence().fetchByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the osp scheduler entries before and after the current osp scheduler entry in the ordered set where status = &#63;.
	*
	* @param schedulerEntryId the primary key of the current osp scheduler entry
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public static OSPSchedulerEntry[] findByStatus_PrevAndNext(
		long schedulerEntryId, int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence()
				   .findByStatus_PrevAndNext(schedulerEntryId, status,
			orderByComparator);
	}

	/**
	* Removes all the osp scheduler entries where status = &#63; from the database.
	*
	* @param status the status
	*/
	public static void removeByStatus(int status) {
		getPersistence().removeByStatus(status);
	}

	/**
	* Returns the number of osp scheduler entries where status = &#63;.
	*
	* @param status the status
	* @return the number of matching osp scheduler entries
	*/
	public static int countByStatus(int status) {
		return getPersistence().countByStatus(status);
	}

	/**
	* Returns all the osp scheduler entries where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByG_S(long groupId, int status) {
		return getPersistence().findByG_S(groupId, status);
	}

	/**
	* Returns a range of all the osp scheduler entries where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @return the range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByG_S(long groupId, int status,
		int start, int end) {
		return getPersistence().findByG_S(groupId, status, start, end);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByG_S(long groupId, int status,
		int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence()
				   .findByG_S(groupId, status, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findByG_S(long groupId, int status,
		int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_S(groupId, status, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first osp scheduler entry in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry findByG_S_First(long groupId, int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence()
				   .findByG_S_First(groupId, status, orderByComparator);
	}

	/**
	* Returns the first osp scheduler entry in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByG_S_First(long groupId, int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence()
				   .fetchByG_S_First(groupId, status, orderByComparator);
	}

	/**
	* Returns the last osp scheduler entry in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry findByG_S_Last(long groupId, int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence()
				   .findByG_S_Last(groupId, status, orderByComparator);
	}

	/**
	* Returns the last osp scheduler entry in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByG_S_Last(long groupId, int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence()
				   .fetchByG_S_Last(groupId, status, orderByComparator);
	}

	/**
	* Returns the osp scheduler entries before and after the current osp scheduler entry in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param schedulerEntryId the primary key of the current osp scheduler entry
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public static OSPSchedulerEntry[] findByG_S_PrevAndNext(
		long schedulerEntryId, long groupId, int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence()
				   .findByG_S_PrevAndNext(schedulerEntryId, groupId, status,
			orderByComparator);
	}

	/**
	* Returns all the osp scheduler entries that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching osp scheduler entries that the user has permission to view
	*/
	public static List<OSPSchedulerEntry> filterFindByG_S(long groupId,
		int status) {
		return getPersistence().filterFindByG_S(groupId, status);
	}

	/**
	* Returns a range of all the osp scheduler entries that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @return the range of matching osp scheduler entries that the user has permission to view
	*/
	public static List<OSPSchedulerEntry> filterFindByG_S(long groupId,
		int status, int start, int end) {
		return getPersistence().filterFindByG_S(groupId, status, start, end);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries that the user has permissions to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching osp scheduler entries that the user has permission to view
	*/
	public static List<OSPSchedulerEntry> filterFindByG_S(long groupId,
		int status, int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence()
				   .filterFindByG_S(groupId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the osp scheduler entries before and after the current osp scheduler entry in the ordered set of osp scheduler entries that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param schedulerEntryId the primary key of the current osp scheduler entry
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public static OSPSchedulerEntry[] filterFindByG_S_PrevAndNext(
		long schedulerEntryId, long groupId, int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence()
				   .filterFindByG_S_PrevAndNext(schedulerEntryId, groupId,
			status, orderByComparator);
	}

	/**
	* Removes all the osp scheduler entries where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	*/
	public static void removeByG_S(long groupId, int status) {
		getPersistence().removeByG_S(groupId, status);
	}

	/**
	* Returns the number of osp scheduler entries where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching osp scheduler entries
	*/
	public static int countByG_S(long groupId, int status) {
		return getPersistence().countByG_S(groupId, status);
	}

	/**
	* Returns the number of osp scheduler entries that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching osp scheduler entries that the user has permission to view
	*/
	public static int filterCountByG_S(long groupId, int status) {
		return getPersistence().filterCountByG_S(groupId, status);
	}

	/**
	* Returns the osp scheduler entry where name = &#63; and version = &#63; or throws a {@link NoSuchOSPSchedulerEntryException} if it could not be found.
	*
	* @param name the name
	* @param version the version
	* @return the matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry findByNameVersion(String name,
		String version)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence().findByNameVersion(name, version);
	}

	/**
	* Returns the osp scheduler entry where name = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @param version the version
	* @return the matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByNameVersion(String name,
		String version) {
		return getPersistence().fetchByNameVersion(name, version);
	}

	/**
	* Returns the osp scheduler entry where name = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param version the version
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public static OSPSchedulerEntry fetchByNameVersion(String name,
		String version, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByNameVersion(name, version, retrieveFromCache);
	}

	/**
	* Removes the osp scheduler entry where name = &#63; and version = &#63; from the database.
	*
	* @param name the name
	* @param version the version
	* @return the osp scheduler entry that was removed
	*/
	public static OSPSchedulerEntry removeByNameVersion(String name,
		String version)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence().removeByNameVersion(name, version);
	}

	/**
	* Returns the number of osp scheduler entries where name = &#63; and version = &#63;.
	*
	* @param name the name
	* @param version the version
	* @return the number of matching osp scheduler entries
	*/
	public static int countByNameVersion(String name, String version) {
		return getPersistence().countByNameVersion(name, version);
	}

	/**
	* Caches the osp scheduler entry in the entity cache if it is enabled.
	*
	* @param ospSchedulerEntry the osp scheduler entry
	*/
	public static void cacheResult(OSPSchedulerEntry ospSchedulerEntry) {
		getPersistence().cacheResult(ospSchedulerEntry);
	}

	/**
	* Caches the osp scheduler entries in the entity cache if it is enabled.
	*
	* @param ospSchedulerEntries the osp scheduler entries
	*/
	public static void cacheResult(List<OSPSchedulerEntry> ospSchedulerEntries) {
		getPersistence().cacheResult(ospSchedulerEntries);
	}

	/**
	* Creates a new osp scheduler entry with the primary key. Does not add the osp scheduler entry to the database.
	*
	* @param schedulerEntryId the primary key for the new osp scheduler entry
	* @return the new osp scheduler entry
	*/
	public static OSPSchedulerEntry create(long schedulerEntryId) {
		return getPersistence().create(schedulerEntryId);
	}

	/**
	* Removes the osp scheduler entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param schedulerEntryId the primary key of the osp scheduler entry
	* @return the osp scheduler entry that was removed
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public static OSPSchedulerEntry remove(long schedulerEntryId)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence().remove(schedulerEntryId);
	}

	public static OSPSchedulerEntry updateImpl(
		OSPSchedulerEntry ospSchedulerEntry) {
		return getPersistence().updateImpl(ospSchedulerEntry);
	}

	/**
	* Returns the osp scheduler entry with the primary key or throws a {@link NoSuchOSPSchedulerEntryException} if it could not be found.
	*
	* @param schedulerEntryId the primary key of the osp scheduler entry
	* @return the osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public static OSPSchedulerEntry findByPrimaryKey(long schedulerEntryId)
		throws com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException {
		return getPersistence().findByPrimaryKey(schedulerEntryId);
	}

	/**
	* Returns the osp scheduler entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param schedulerEntryId the primary key of the osp scheduler entry
	* @return the osp scheduler entry, or <code>null</code> if a osp scheduler entry with the primary key could not be found
	*/
	public static OSPSchedulerEntry fetchByPrimaryKey(long schedulerEntryId) {
		return getPersistence().fetchByPrimaryKey(schedulerEntryId);
	}

	public static java.util.Map<java.io.Serializable, OSPSchedulerEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the osp scheduler entries.
	*
	* @return the osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the osp scheduler entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @return the range of osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findAll(int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the osp scheduler entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OSPSchedulerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of osp scheduler entries
	* @param end the upper bound of the range of osp scheduler entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of osp scheduler entries
	*/
	public static List<OSPSchedulerEntry> findAll(int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the osp scheduler entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of osp scheduler entries.
	*
	* @return the number of osp scheduler entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static OSPSchedulerEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OSPSchedulerEntryPersistence, OSPSchedulerEntryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OSPSchedulerEntryPersistence.class);

		ServiceTracker<OSPSchedulerEntryPersistence, OSPSchedulerEntryPersistence> serviceTracker =
			new ServiceTracker<OSPSchedulerEntryPersistence, OSPSchedulerEntryPersistence>(bundle.getBundleContext(),
				OSPSchedulerEntryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}