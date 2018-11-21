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

import com.kisti.osp.util.model.UTIL;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the util service. This utility wraps {@link com.kisti.osp.util.service.persistence.impl.UTILPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UTILPersistence
 * @see com.kisti.osp.util.service.persistence.impl.UTILPersistenceImpl
 * @generated
 */
@ProviderType
public class UTILUtil {
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
	public static void clearCache(UTIL util) {
		getPersistence().clearCache(util);
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
	public static List<UTIL> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UTIL> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UTIL> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<UTIL> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UTIL update(UTIL util) {
		return getPersistence().update(util);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UTIL update(UTIL util, ServiceContext serviceContext) {
		return getPersistence().update(util, serviceContext);
	}

	/**
	* Returns all the utils where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching utils
	*/
	public static List<UTIL> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<UTIL> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<UTIL> findByUuid(String uuid, int start, int end,
		OrderByComparator<UTIL> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<UTIL> findByUuid(String uuid, int start, int end,
		OrderByComparator<UTIL> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first util in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching util
	* @throws NoSuchUTILException if a matching util could not be found
	*/
	public static UTIL findByUuid_First(String uuid,
		OrderByComparator<UTIL> orderByComparator)
		throws com.kisti.osp.util.exception.NoSuchUTILException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first util in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching util, or <code>null</code> if a matching util could not be found
	*/
	public static UTIL fetchByUuid_First(String uuid,
		OrderByComparator<UTIL> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last util in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching util
	* @throws NoSuchUTILException if a matching util could not be found
	*/
	public static UTIL findByUuid_Last(String uuid,
		OrderByComparator<UTIL> orderByComparator)
		throws com.kisti.osp.util.exception.NoSuchUTILException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last util in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching util, or <code>null</code> if a matching util could not be found
	*/
	public static UTIL fetchByUuid_Last(String uuid,
		OrderByComparator<UTIL> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the utils before and after the current util in the ordered set where uuid = &#63;.
	*
	* @param utilId the primary key of the current util
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next util
	* @throws NoSuchUTILException if a util with the primary key could not be found
	*/
	public static UTIL[] findByUuid_PrevAndNext(long utilId, String uuid,
		OrderByComparator<UTIL> orderByComparator)
		throws com.kisti.osp.util.exception.NoSuchUTILException {
		return getPersistence()
				   .findByUuid_PrevAndNext(utilId, uuid, orderByComparator);
	}

	/**
	* Removes all the utils where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of utils where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching utils
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the util where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchUTILException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching util
	* @throws NoSuchUTILException if a matching util could not be found
	*/
	public static UTIL findByUUID_G(String uuid, long groupId)
		throws com.kisti.osp.util.exception.NoSuchUTILException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the util where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching util, or <code>null</code> if a matching util could not be found
	*/
	public static UTIL fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the util where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching util, or <code>null</code> if a matching util could not be found
	*/
	public static UTIL fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the util where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the util that was removed
	*/
	public static UTIL removeByUUID_G(String uuid, long groupId)
		throws com.kisti.osp.util.exception.NoSuchUTILException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of utils where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching utils
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the utils where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching utils
	*/
	public static List<UTIL> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<UTIL> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<UTIL> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<UTIL> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<UTIL> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<UTIL> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first util in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching util
	* @throws NoSuchUTILException if a matching util could not be found
	*/
	public static UTIL findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<UTIL> orderByComparator)
		throws com.kisti.osp.util.exception.NoSuchUTILException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first util in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching util, or <code>null</code> if a matching util could not be found
	*/
	public static UTIL fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<UTIL> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last util in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching util
	* @throws NoSuchUTILException if a matching util could not be found
	*/
	public static UTIL findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<UTIL> orderByComparator)
		throws com.kisti.osp.util.exception.NoSuchUTILException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last util in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching util, or <code>null</code> if a matching util could not be found
	*/
	public static UTIL fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<UTIL> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static UTIL[] findByUuid_C_PrevAndNext(long utilId, String uuid,
		long companyId, OrderByComparator<UTIL> orderByComparator)
		throws com.kisti.osp.util.exception.NoSuchUTILException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(utilId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the utils where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of utils where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching utils
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the util in the entity cache if it is enabled.
	*
	* @param util the util
	*/
	public static void cacheResult(UTIL util) {
		getPersistence().cacheResult(util);
	}

	/**
	* Caches the utils in the entity cache if it is enabled.
	*
	* @param utils the utils
	*/
	public static void cacheResult(List<UTIL> utils) {
		getPersistence().cacheResult(utils);
	}

	/**
	* Creates a new util with the primary key. Does not add the util to the database.
	*
	* @param utilId the primary key for the new util
	* @return the new util
	*/
	public static UTIL create(long utilId) {
		return getPersistence().create(utilId);
	}

	/**
	* Removes the util with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param utilId the primary key of the util
	* @return the util that was removed
	* @throws NoSuchUTILException if a util with the primary key could not be found
	*/
	public static UTIL remove(long utilId)
		throws com.kisti.osp.util.exception.NoSuchUTILException {
		return getPersistence().remove(utilId);
	}

	public static UTIL updateImpl(UTIL util) {
		return getPersistence().updateImpl(util);
	}

	/**
	* Returns the util with the primary key or throws a {@link NoSuchUTILException} if it could not be found.
	*
	* @param utilId the primary key of the util
	* @return the util
	* @throws NoSuchUTILException if a util with the primary key could not be found
	*/
	public static UTIL findByPrimaryKey(long utilId)
		throws com.kisti.osp.util.exception.NoSuchUTILException {
		return getPersistence().findByPrimaryKey(utilId);
	}

	/**
	* Returns the util with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param utilId the primary key of the util
	* @return the util, or <code>null</code> if a util with the primary key could not be found
	*/
	public static UTIL fetchByPrimaryKey(long utilId) {
		return getPersistence().fetchByPrimaryKey(utilId);
	}

	public static java.util.Map<java.io.Serializable, UTIL> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the utils.
	*
	* @return the utils
	*/
	public static List<UTIL> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<UTIL> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<UTIL> findAll(int start, int end,
		OrderByComparator<UTIL> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<UTIL> findAll(int start, int end,
		OrderByComparator<UTIL> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the utils from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of utils.
	*
	* @return the number of utils
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static UTILPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<UTILPersistence, UTILPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(UTILPersistence.class);

		ServiceTracker<UTILPersistence, UTILPersistence> serviceTracker = new ServiceTracker<UTILPersistence, UTILPersistence>(bundle.getBundleContext(),
				UTILPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}