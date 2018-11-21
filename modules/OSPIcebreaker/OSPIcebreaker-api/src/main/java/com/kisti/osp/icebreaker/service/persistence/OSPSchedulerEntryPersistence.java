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

import com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException;
import com.kisti.osp.icebreaker.model.OSPSchedulerEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the osp scheduler entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jerry H. Seo
 * @see com.kisti.osp.icebreaker.service.persistence.impl.OSPSchedulerEntryPersistenceImpl
 * @see OSPSchedulerEntryUtil
 * @generated
 */
@ProviderType
public interface OSPSchedulerEntryPersistence extends BasePersistence<OSPSchedulerEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OSPSchedulerEntryUtil} to access the osp scheduler entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the osp scheduler entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching osp scheduler entries
	*/
	public java.util.List<OSPSchedulerEntry> findByUuid(String uuid);

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
	public java.util.List<OSPSchedulerEntry> findByUuid(String uuid, int start,
		int end);

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
	public java.util.List<OSPSchedulerEntry> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

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
	public java.util.List<OSPSchedulerEntry> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first osp scheduler entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the first osp scheduler entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

	/**
	* Returns the last osp scheduler entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the last osp scheduler entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

	/**
	* Returns the osp scheduler entries before and after the current osp scheduler entry in the ordered set where uuid = &#63;.
	*
	* @param schedulerEntryId the primary key of the current osp scheduler entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public OSPSchedulerEntry[] findByUuid_PrevAndNext(long schedulerEntryId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Removes all the osp scheduler entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of osp scheduler entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching osp scheduler entries
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the osp scheduler entry where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOSPSchedulerEntryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry findByUUID_G(String uuid, long groupId)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the osp scheduler entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the osp scheduler entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the osp scheduler entry where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the osp scheduler entry that was removed
	*/
	public OSPSchedulerEntry removeByUUID_G(String uuid, long groupId)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the number of osp scheduler entries where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching osp scheduler entries
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the osp scheduler entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching osp scheduler entries
	*/
	public java.util.List<OSPSchedulerEntry> findByUuid_C(String uuid,
		long companyId);

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
	public java.util.List<OSPSchedulerEntry> findByUuid_C(String uuid,
		long companyId, int start, int end);

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
	public java.util.List<OSPSchedulerEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

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
	public java.util.List<OSPSchedulerEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first osp scheduler entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the first osp scheduler entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

	/**
	* Returns the last osp scheduler entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the last osp scheduler entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

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
	public OSPSchedulerEntry[] findByUuid_C_PrevAndNext(long schedulerEntryId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Removes all the osp scheduler entries where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of osp scheduler entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching osp scheduler entries
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the osp scheduler entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching osp scheduler entries
	*/
	public java.util.List<OSPSchedulerEntry> findByGroupId(long groupId);

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
	public java.util.List<OSPSchedulerEntry> findByGroupId(long groupId,
		int start, int end);

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
	public java.util.List<OSPSchedulerEntry> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

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
	public java.util.List<OSPSchedulerEntry> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first osp scheduler entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the first osp scheduler entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

	/**
	* Returns the last osp scheduler entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the last osp scheduler entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

	/**
	* Returns the osp scheduler entries before and after the current osp scheduler entry in the ordered set where groupId = &#63;.
	*
	* @param schedulerEntryId the primary key of the current osp scheduler entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public OSPSchedulerEntry[] findByGroupId_PrevAndNext(
		long schedulerEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns all the osp scheduler entries that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching osp scheduler entries that the user has permission to view
	*/
	public java.util.List<OSPSchedulerEntry> filterFindByGroupId(long groupId);

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
	public java.util.List<OSPSchedulerEntry> filterFindByGroupId(long groupId,
		int start, int end);

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
	public java.util.List<OSPSchedulerEntry> filterFindByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

	/**
	* Returns the osp scheduler entries before and after the current osp scheduler entry in the ordered set of osp scheduler entries that the user has permission to view where groupId = &#63;.
	*
	* @param schedulerEntryId the primary key of the current osp scheduler entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public OSPSchedulerEntry[] filterFindByGroupId_PrevAndNext(
		long schedulerEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Removes all the osp scheduler entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of osp scheduler entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching osp scheduler entries
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of osp scheduler entries that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching osp scheduler entries that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Returns all the osp scheduler entries where name = &#63;.
	*
	* @param name the name
	* @return the matching osp scheduler entries
	*/
	public java.util.List<OSPSchedulerEntry> findByName(String name);

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
	public java.util.List<OSPSchedulerEntry> findByName(String name, int start,
		int end);

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
	public java.util.List<OSPSchedulerEntry> findByName(String name, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

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
	public java.util.List<OSPSchedulerEntry> findByName(String name, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first osp scheduler entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry findByName_First(String name,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the first osp scheduler entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByName_First(String name,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

	/**
	* Returns the last osp scheduler entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry findByName_Last(String name,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the last osp scheduler entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByName_Last(String name,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

	/**
	* Returns the osp scheduler entries before and after the current osp scheduler entry in the ordered set where name = &#63;.
	*
	* @param schedulerEntryId the primary key of the current osp scheduler entry
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public OSPSchedulerEntry[] findByName_PrevAndNext(long schedulerEntryId,
		String name,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Removes all the osp scheduler entries where name = &#63; from the database.
	*
	* @param name the name
	*/
	public void removeByName(String name);

	/**
	* Returns the number of osp scheduler entries where name = &#63;.
	*
	* @param name the name
	* @return the number of matching osp scheduler entries
	*/
	public int countByName(String name);

	/**
	* Returns all the osp scheduler entries where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching osp scheduler entries
	*/
	public java.util.List<OSPSchedulerEntry> findByG_Name(long groupId,
		String name);

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
	public java.util.List<OSPSchedulerEntry> findByG_Name(long groupId,
		String name, int start, int end);

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
	public java.util.List<OSPSchedulerEntry> findByG_Name(long groupId,
		String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

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
	public java.util.List<OSPSchedulerEntry> findByG_Name(long groupId,
		String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first osp scheduler entry in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry findByG_Name_First(long groupId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the first osp scheduler entry in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByG_Name_First(long groupId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

	/**
	* Returns the last osp scheduler entry in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry findByG_Name_Last(long groupId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the last osp scheduler entry in the ordered set where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByG_Name_Last(long groupId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

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
	public OSPSchedulerEntry[] findByG_Name_PrevAndNext(long schedulerEntryId,
		long groupId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns all the osp scheduler entries that the user has permission to view where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching osp scheduler entries that the user has permission to view
	*/
	public java.util.List<OSPSchedulerEntry> filterFindByG_Name(long groupId,
		String name);

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
	public java.util.List<OSPSchedulerEntry> filterFindByG_Name(long groupId,
		String name, int start, int end);

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
	public java.util.List<OSPSchedulerEntry> filterFindByG_Name(long groupId,
		String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

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
	public OSPSchedulerEntry[] filterFindByG_Name_PrevAndNext(
		long schedulerEntryId, long groupId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Removes all the osp scheduler entries where groupId = &#63; and name LIKE &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	*/
	public void removeByG_Name(long groupId, String name);

	/**
	* Returns the number of osp scheduler entries where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the number of matching osp scheduler entries
	*/
	public int countByG_Name(long groupId, String name);

	/**
	* Returns the number of osp scheduler entries that the user has permission to view where groupId = &#63; and name LIKE &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the number of matching osp scheduler entries that the user has permission to view
	*/
	public int filterCountByG_Name(long groupId, String name);

	/**
	* Returns all the osp scheduler entries where status = &#63;.
	*
	* @param status the status
	* @return the matching osp scheduler entries
	*/
	public java.util.List<OSPSchedulerEntry> findByStatus(int status);

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
	public java.util.List<OSPSchedulerEntry> findByStatus(int status,
		int start, int end);

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
	public java.util.List<OSPSchedulerEntry> findByStatus(int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

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
	public java.util.List<OSPSchedulerEntry> findByStatus(int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first osp scheduler entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry findByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the first osp scheduler entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

	/**
	* Returns the last osp scheduler entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry findByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the last osp scheduler entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

	/**
	* Returns the osp scheduler entries before and after the current osp scheduler entry in the ordered set where status = &#63;.
	*
	* @param schedulerEntryId the primary key of the current osp scheduler entry
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public OSPSchedulerEntry[] findByStatus_PrevAndNext(long schedulerEntryId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Removes all the osp scheduler entries where status = &#63; from the database.
	*
	* @param status the status
	*/
	public void removeByStatus(int status);

	/**
	* Returns the number of osp scheduler entries where status = &#63;.
	*
	* @param status the status
	* @return the number of matching osp scheduler entries
	*/
	public int countByStatus(int status);

	/**
	* Returns all the osp scheduler entries where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching osp scheduler entries
	*/
	public java.util.List<OSPSchedulerEntry> findByG_S(long groupId, int status);

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
	public java.util.List<OSPSchedulerEntry> findByG_S(long groupId,
		int status, int start, int end);

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
	public java.util.List<OSPSchedulerEntry> findByG_S(long groupId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

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
	public java.util.List<OSPSchedulerEntry> findByG_S(long groupId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first osp scheduler entry in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry findByG_S_First(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the first osp scheduler entry in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByG_S_First(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

	/**
	* Returns the last osp scheduler entry in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry findByG_S_Last(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the last osp scheduler entry in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByG_S_Last(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

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
	public OSPSchedulerEntry[] findByG_S_PrevAndNext(long schedulerEntryId,
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns all the osp scheduler entries that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching osp scheduler entries that the user has permission to view
	*/
	public java.util.List<OSPSchedulerEntry> filterFindByG_S(long groupId,
		int status);

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
	public java.util.List<OSPSchedulerEntry> filterFindByG_S(long groupId,
		int status, int start, int end);

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
	public java.util.List<OSPSchedulerEntry> filterFindByG_S(long groupId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

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
	public OSPSchedulerEntry[] filterFindByG_S_PrevAndNext(
		long schedulerEntryId, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Removes all the osp scheduler entries where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	*/
	public void removeByG_S(long groupId, int status);

	/**
	* Returns the number of osp scheduler entries where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching osp scheduler entries
	*/
	public int countByG_S(long groupId, int status);

	/**
	* Returns the number of osp scheduler entries that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching osp scheduler entries that the user has permission to view
	*/
	public int filterCountByG_S(long groupId, int status);

	/**
	* Returns the osp scheduler entry where name = &#63; and version = &#63; or throws a {@link NoSuchOSPSchedulerEntryException} if it could not be found.
	*
	* @param name the name
	* @param version the version
	* @return the matching osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry findByNameVersion(String name, String version)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the osp scheduler entry where name = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @param version the version
	* @return the matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByNameVersion(String name, String version);

	/**
	* Returns the osp scheduler entry where name = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param version the version
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	*/
	public OSPSchedulerEntry fetchByNameVersion(String name, String version,
		boolean retrieveFromCache);

	/**
	* Removes the osp scheduler entry where name = &#63; and version = &#63; from the database.
	*
	* @param name the name
	* @param version the version
	* @return the osp scheduler entry that was removed
	*/
	public OSPSchedulerEntry removeByNameVersion(String name, String version)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the number of osp scheduler entries where name = &#63; and version = &#63;.
	*
	* @param name the name
	* @param version the version
	* @return the number of matching osp scheduler entries
	*/
	public int countByNameVersion(String name, String version);

	/**
	* Caches the osp scheduler entry in the entity cache if it is enabled.
	*
	* @param ospSchedulerEntry the osp scheduler entry
	*/
	public void cacheResult(OSPSchedulerEntry ospSchedulerEntry);

	/**
	* Caches the osp scheduler entries in the entity cache if it is enabled.
	*
	* @param ospSchedulerEntries the osp scheduler entries
	*/
	public void cacheResult(
		java.util.List<OSPSchedulerEntry> ospSchedulerEntries);

	/**
	* Creates a new osp scheduler entry with the primary key. Does not add the osp scheduler entry to the database.
	*
	* @param schedulerEntryId the primary key for the new osp scheduler entry
	* @return the new osp scheduler entry
	*/
	public OSPSchedulerEntry create(long schedulerEntryId);

	/**
	* Removes the osp scheduler entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param schedulerEntryId the primary key of the osp scheduler entry
	* @return the osp scheduler entry that was removed
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public OSPSchedulerEntry remove(long schedulerEntryId)
		throws NoSuchOSPSchedulerEntryException;

	public OSPSchedulerEntry updateImpl(OSPSchedulerEntry ospSchedulerEntry);

	/**
	* Returns the osp scheduler entry with the primary key or throws a {@link NoSuchOSPSchedulerEntryException} if it could not be found.
	*
	* @param schedulerEntryId the primary key of the osp scheduler entry
	* @return the osp scheduler entry
	* @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	*/
	public OSPSchedulerEntry findByPrimaryKey(long schedulerEntryId)
		throws NoSuchOSPSchedulerEntryException;

	/**
	* Returns the osp scheduler entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param schedulerEntryId the primary key of the osp scheduler entry
	* @return the osp scheduler entry, or <code>null</code> if a osp scheduler entry with the primary key could not be found
	*/
	public OSPSchedulerEntry fetchByPrimaryKey(long schedulerEntryId);

	@Override
	public java.util.Map<java.io.Serializable, OSPSchedulerEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the osp scheduler entries.
	*
	* @return the osp scheduler entries
	*/
	public java.util.List<OSPSchedulerEntry> findAll();

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
	public java.util.List<OSPSchedulerEntry> findAll(int start, int end);

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
	public java.util.List<OSPSchedulerEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator);

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
	public java.util.List<OSPSchedulerEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the osp scheduler entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of osp scheduler entries.
	*
	* @return the number of osp scheduler entries
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}