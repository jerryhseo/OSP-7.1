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

package com.kisti.osp.util.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.kisti.osp.util.exception.NoSuchUTILException;
import com.kisti.osp.util.model.UTIL;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the util service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.kisti.osp.util.service.persistence.impl.UTILPersistenceImpl
 * @see UTILUtil
 * @generated
 */
@ProviderType
public interface UTILPersistence extends BasePersistence<UTIL> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UTILUtil} to access the util persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the utils where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching utils
	*/
	public java.util.List<UTIL> findByUuid(String uuid);

	/**
	* Returns a range of all the utils where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UTILModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of utils
	* @param end the upper bound of the range of utils (not inclusive)
	* @return the range of matching utils
	*/
	public java.util.List<UTIL> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the utils where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UTILModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of utils
	* @param end the upper bound of the range of utils (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching utils
	*/
	public java.util.List<UTIL> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UTIL> orderByComparator);

	/**
	* Returns an ordered range of all the utils where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UTILModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of utils
	* @param end the upper bound of the range of utils (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching utils
	*/
	public java.util.List<UTIL> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UTIL> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first util in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching util
	* @throws NoSuchUTILException if a matching util could not be found
	*/
	public UTIL findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UTIL> orderByComparator)
		throws NoSuchUTILException;

	/**
	* Returns the first util in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching util, or <code>null</code> if a matching util could not be found
	*/
	public UTIL fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UTIL> orderByComparator);

	/**
	* Returns the last util in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching util
	* @throws NoSuchUTILException if a matching util could not be found
	*/
	public UTIL findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UTIL> orderByComparator)
		throws NoSuchUTILException;

	/**
	* Returns the last util in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching util, or <code>null</code> if a matching util could not be found
	*/
	public UTIL fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UTIL> orderByComparator);

	/**
	* Returns the utils before and after the current util in the ordered set where uuid = &#63;.
	*
	* @param utilId the primary key of the current util
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next util
	* @throws NoSuchUTILException if a util with the primary key could not be found
	*/
	public UTIL[] findByUuid_PrevAndNext(long utilId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UTIL> orderByComparator)
		throws NoSuchUTILException;

	/**
	* Removes all the utils where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of utils where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching utils
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the util where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchUTILException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching util
	* @throws NoSuchUTILException if a matching util could not be found
	*/
	public UTIL findByUUID_G(String uuid, long groupId)
		throws NoSuchUTILException;

	/**
	* Returns the util where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching util, or <code>null</code> if a matching util could not be found
	*/
	public UTIL fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the util where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching util, or <code>null</code> if a matching util could not be found
	*/
	public UTIL fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the util where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the util that was removed
	*/
	public UTIL removeByUUID_G(String uuid, long groupId)
		throws NoSuchUTILException;

	/**
	* Returns the number of utils where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching utils
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the utils where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching utils
	*/
	public java.util.List<UTIL> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the utils where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UTILModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of utils
	* @param end the upper bound of the range of utils (not inclusive)
	* @return the range of matching utils
	*/
	public java.util.List<UTIL> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the utils where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UTILModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of utils
	* @param end the upper bound of the range of utils (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching utils
	*/
	public java.util.List<UTIL> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UTIL> orderByComparator);

	/**
	* Returns an ordered range of all the utils where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UTILModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of utils
	* @param end the upper bound of the range of utils (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching utils
	*/
	public java.util.List<UTIL> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UTIL> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first util in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching util
	* @throws NoSuchUTILException if a matching util could not be found
	*/
	public UTIL findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UTIL> orderByComparator)
		throws NoSuchUTILException;

	/**
	* Returns the first util in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching util, or <code>null</code> if a matching util could not be found
	*/
	public UTIL fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UTIL> orderByComparator);

	/**
	* Returns the last util in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching util
	* @throws NoSuchUTILException if a matching util could not be found
	*/
	public UTIL findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UTIL> orderByComparator)
		throws NoSuchUTILException;

	/**
	* Returns the last util in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching util, or <code>null</code> if a matching util could not be found
	*/
	public UTIL fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UTIL> orderByComparator);

	/**
	* Returns the utils before and after the current util in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param utilId the primary key of the current util
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next util
	* @throws NoSuchUTILException if a util with the primary key could not be found
	*/
	public UTIL[] findByUuid_C_PrevAndNext(long utilId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UTIL> orderByComparator)
		throws NoSuchUTILException;

	/**
	* Removes all the utils where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of utils where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching utils
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Caches the util in the entity cache if it is enabled.
	*
	* @param util the util
	*/
	public void cacheResult(UTIL util);

	/**
	* Caches the utils in the entity cache if it is enabled.
	*
	* @param utils the utils
	*/
	public void cacheResult(java.util.List<UTIL> utils);

	/**
	* Creates a new util with the primary key. Does not add the util to the database.
	*
	* @param utilId the primary key for the new util
	* @return the new util
	*/
	public UTIL create(long utilId);

	/**
	* Removes the util with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param utilId the primary key of the util
	* @return the util that was removed
	* @throws NoSuchUTILException if a util with the primary key could not be found
	*/
	public UTIL remove(long utilId) throws NoSuchUTILException;

	public UTIL updateImpl(UTIL util);

	/**
	* Returns the util with the primary key or throws a {@link NoSuchUTILException} if it could not be found.
	*
	* @param utilId the primary key of the util
	* @return the util
	* @throws NoSuchUTILException if a util with the primary key could not be found
	*/
	public UTIL findByPrimaryKey(long utilId) throws NoSuchUTILException;

	/**
	* Returns the util with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param utilId the primary key of the util
	* @return the util, or <code>null</code> if a util with the primary key could not be found
	*/
	public UTIL fetchByPrimaryKey(long utilId);

	@Override
	public java.util.Map<java.io.Serializable, UTIL> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the utils.
	*
	* @return the utils
	*/
	public java.util.List<UTIL> findAll();

	/**
	* Returns a range of all the utils.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UTILModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of utils
	* @param end the upper bound of the range of utils (not inclusive)
	* @return the range of utils
	*/
	public java.util.List<UTIL> findAll(int start, int end);

	/**
	* Returns an ordered range of all the utils.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UTILModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of utils
	* @param end the upper bound of the range of utils (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of utils
	*/
	public java.util.List<UTIL> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UTIL> orderByComparator);

	/**
	* Returns an ordered range of all the utils.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UTILModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of utils
	* @param end the upper bound of the range of utils (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of utils
	*/
	public java.util.List<UTIL> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UTIL> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the utils from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of utils.
	*
	* @return the number of utils
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}