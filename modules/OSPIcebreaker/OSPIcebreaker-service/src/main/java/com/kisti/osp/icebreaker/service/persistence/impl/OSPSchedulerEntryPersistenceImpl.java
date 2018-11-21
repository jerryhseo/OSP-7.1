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

package com.kisti.osp.icebreaker.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException;
import com.kisti.osp.icebreaker.model.OSPSchedulerEntry;
import com.kisti.osp.icebreaker.model.impl.OSPSchedulerEntryImpl;
import com.kisti.osp.icebreaker.model.impl.OSPSchedulerEntryModelImpl;
import com.kisti.osp.icebreaker.service.persistence.OSPSchedulerEntryPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the osp scheduler entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jerry H. Seo
 * @see OSPSchedulerEntryPersistence
 * @see com.kisti.osp.icebreaker.service.persistence.OSPSchedulerEntryUtil
 * @generated
 */
@ProviderType
public class OSPSchedulerEntryPersistenceImpl extends BasePersistenceImpl<OSPSchedulerEntry>
	implements OSPSchedulerEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OSPSchedulerEntryUtil} to access the osp scheduler entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OSPSchedulerEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			OSPSchedulerEntryModelImpl.UUID_COLUMN_BITMASK |
			OSPSchedulerEntryModelImpl.NAME_COLUMN_BITMASK |
			OSPSchedulerEntryModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the osp scheduler entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching osp scheduler entries
	 */
	@Override
	public List<OSPSchedulerEntry> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<OSPSchedulerEntry> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<OSPSchedulerEntry> findByUuid(String uuid, int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<OSPSchedulerEntry> findByUuid(String uuid, int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<OSPSchedulerEntry> list = null;

		if (retrieveFromCache) {
			list = (List<OSPSchedulerEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OSPSchedulerEntry ospSchedulerEntry : list) {
					if (!Objects.equals(uuid, ospSchedulerEntry.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<OSPSchedulerEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OSPSchedulerEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first osp scheduler entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching osp scheduler entry
	 * @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry findByUuid_First(String uuid,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = fetchByUuid_First(uuid,
				orderByComparator);

		if (ospSchedulerEntry != null) {
			return ospSchedulerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOSPSchedulerEntryException(msg.toString());
	}

	/**
	 * Returns the first osp scheduler entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByUuid_First(String uuid,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		List<OSPSchedulerEntry> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last osp scheduler entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching osp scheduler entry
	 * @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry findByUuid_Last(String uuid,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = fetchByUuid_Last(uuid,
				orderByComparator);

		if (ospSchedulerEntry != null) {
			return ospSchedulerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOSPSchedulerEntryException(msg.toString());
	}

	/**
	 * Returns the last osp scheduler entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByUuid_Last(String uuid,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<OSPSchedulerEntry> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public OSPSchedulerEntry[] findByUuid_PrevAndNext(long schedulerEntryId,
		String uuid, OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = findByPrimaryKey(schedulerEntryId);

		Session session = null;

		try {
			session = openSession();

			OSPSchedulerEntry[] array = new OSPSchedulerEntryImpl[3];

			array[0] = getByUuid_PrevAndNext(session, ospSchedulerEntry, uuid,
					orderByComparator, true);

			array[1] = ospSchedulerEntry;

			array[2] = getByUuid_PrevAndNext(session, ospSchedulerEntry, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OSPSchedulerEntry getByUuid_PrevAndNext(Session session,
		OSPSchedulerEntry ospSchedulerEntry, String uuid,
		OrderByComparator<OSPSchedulerEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ospSchedulerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OSPSchedulerEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the osp scheduler entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (OSPSchedulerEntry ospSchedulerEntry : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ospSchedulerEntry);
		}
	}

	/**
	 * Returns the number of osp scheduler entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching osp scheduler entries
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OSPSCHEDULERENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "ospSchedulerEntry.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "ospSchedulerEntry.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(ospSchedulerEntry.uuid IS NULL OR ospSchedulerEntry.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			OSPSchedulerEntryModelImpl.UUID_COLUMN_BITMASK |
			OSPSchedulerEntryModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the osp scheduler entry where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOSPSchedulerEntryException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching osp scheduler entry
	 * @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry findByUUID_G(String uuid, long groupId)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = fetchByUUID_G(uuid, groupId);

		if (ospSchedulerEntry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchOSPSchedulerEntryException(msg.toString());
		}

		return ospSchedulerEntry;
	}

	/**
	 * Returns the osp scheduler entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the osp scheduler entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof OSPSchedulerEntry) {
			OSPSchedulerEntry ospSchedulerEntry = (OSPSchedulerEntry)result;

			if (!Objects.equals(uuid, ospSchedulerEntry.getUuid()) ||
					(groupId != ospSchedulerEntry.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<OSPSchedulerEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					OSPSchedulerEntry ospSchedulerEntry = list.get(0);

					result = ospSchedulerEntry;

					cacheResult(ospSchedulerEntry);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (OSPSchedulerEntry)result;
		}
	}

	/**
	 * Removes the osp scheduler entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the osp scheduler entry that was removed
	 */
	@Override
	public OSPSchedulerEntry removeByUUID_G(String uuid, long groupId)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = findByUUID_G(uuid, groupId);

		return remove(ospSchedulerEntry);
	}

	/**
	 * Returns the number of osp scheduler entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching osp scheduler entries
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OSPSCHEDULERENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "ospSchedulerEntry.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "ospSchedulerEntry.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(ospSchedulerEntry.uuid IS NULL OR ospSchedulerEntry.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "ospSchedulerEntry.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			OSPSchedulerEntryModelImpl.UUID_COLUMN_BITMASK |
			OSPSchedulerEntryModelImpl.COMPANYID_COLUMN_BITMASK |
			OSPSchedulerEntryModelImpl.NAME_COLUMN_BITMASK |
			OSPSchedulerEntryModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the osp scheduler entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching osp scheduler entries
	 */
	@Override
	public List<OSPSchedulerEntry> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<OSPSchedulerEntry> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<OSPSchedulerEntry> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
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
	@Override
	public List<OSPSchedulerEntry> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<OSPSchedulerEntry> list = null;

		if (retrieveFromCache) {
			list = (List<OSPSchedulerEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OSPSchedulerEntry ospSchedulerEntry : list) {
					if (!Objects.equals(uuid, ospSchedulerEntry.getUuid()) ||
							(companyId != ospSchedulerEntry.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<OSPSchedulerEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OSPSchedulerEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public OSPSchedulerEntry findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (ospSchedulerEntry != null) {
			return ospSchedulerEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOSPSchedulerEntryException(msg.toString());
	}

	/**
	 * Returns the first osp scheduler entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		List<OSPSchedulerEntry> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public OSPSchedulerEntry findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (ospSchedulerEntry != null) {
			return ospSchedulerEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOSPSchedulerEntryException(msg.toString());
	}

	/**
	 * Returns the last osp scheduler entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<OSPSchedulerEntry> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public OSPSchedulerEntry[] findByUuid_C_PrevAndNext(long schedulerEntryId,
		String uuid, long companyId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = findByPrimaryKey(schedulerEntryId);

		Session session = null;

		try {
			session = openSession();

			OSPSchedulerEntry[] array = new OSPSchedulerEntryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, ospSchedulerEntry,
					uuid, companyId, orderByComparator, true);

			array[1] = ospSchedulerEntry;

			array[2] = getByUuid_C_PrevAndNext(session, ospSchedulerEntry,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OSPSchedulerEntry getByUuid_C_PrevAndNext(Session session,
		OSPSchedulerEntry ospSchedulerEntry, String uuid, long companyId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ospSchedulerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OSPSchedulerEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the osp scheduler entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (OSPSchedulerEntry ospSchedulerEntry : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ospSchedulerEntry);
		}
	}

	/**
	 * Returns the number of osp scheduler entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching osp scheduler entries
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OSPSCHEDULERENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "ospSchedulerEntry.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "ospSchedulerEntry.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(ospSchedulerEntry.uuid IS NULL OR ospSchedulerEntry.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "ospSchedulerEntry.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			OSPSchedulerEntryModelImpl.GROUPID_COLUMN_BITMASK |
			OSPSchedulerEntryModelImpl.NAME_COLUMN_BITMASK |
			OSPSchedulerEntryModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the osp scheduler entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching osp scheduler entries
	 */
	@Override
	public List<OSPSchedulerEntry> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<OSPSchedulerEntry> findByGroupId(long groupId, int start,
		int end) {
		return findByGroupId(groupId, start, end, null);
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
	@Override
	public List<OSPSchedulerEntry> findByGroupId(long groupId, int start,
		int end, OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
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
	@Override
	public List<OSPSchedulerEntry> findByGroupId(long groupId, int start,
		int end, OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<OSPSchedulerEntry> list = null;

		if (retrieveFromCache) {
			list = (List<OSPSchedulerEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OSPSchedulerEntry ospSchedulerEntry : list) {
					if ((groupId != ospSchedulerEntry.getGroupId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<OSPSchedulerEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OSPSchedulerEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first osp scheduler entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching osp scheduler entry
	 * @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry findByGroupId_First(long groupId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = fetchByGroupId_First(groupId,
				orderByComparator);

		if (ospSchedulerEntry != null) {
			return ospSchedulerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchOSPSchedulerEntryException(msg.toString());
	}

	/**
	 * Returns the first osp scheduler entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByGroupId_First(long groupId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		List<OSPSchedulerEntry> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last osp scheduler entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching osp scheduler entry
	 * @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry findByGroupId_Last(long groupId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (ospSchedulerEntry != null) {
			return ospSchedulerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchOSPSchedulerEntryException(msg.toString());
	}

	/**
	 * Returns the last osp scheduler entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByGroupId_Last(long groupId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<OSPSchedulerEntry> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public OSPSchedulerEntry[] findByGroupId_PrevAndNext(
		long schedulerEntryId, long groupId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = findByPrimaryKey(schedulerEntryId);

		Session session = null;

		try {
			session = openSession();

			OSPSchedulerEntry[] array = new OSPSchedulerEntryImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, ospSchedulerEntry,
					groupId, orderByComparator, true);

			array[1] = ospSchedulerEntry;

			array[2] = getByGroupId_PrevAndNext(session, ospSchedulerEntry,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OSPSchedulerEntry getByGroupId_PrevAndNext(Session session,
		OSPSchedulerEntry ospSchedulerEntry, long groupId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ospSchedulerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OSPSchedulerEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the osp scheduler entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching osp scheduler entries that the user has permission to view
	 */
	@Override
	public List<OSPSchedulerEntry> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<OSPSchedulerEntry> filterFindByGroupId(long groupId, int start,
		int end) {
		return filterFindByGroupId(groupId, start, end, null);
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
	@Override
	public List<OSPSchedulerEntry> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OSPSchedulerEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, OSPSchedulerEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, OSPSchedulerEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<OSPSchedulerEntry>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public OSPSchedulerEntry[] filterFindByGroupId_PrevAndNext(
		long schedulerEntryId, long groupId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(schedulerEntryId, groupId,
				orderByComparator);
		}

		OSPSchedulerEntry ospSchedulerEntry = findByPrimaryKey(schedulerEntryId);

		Session session = null;

		try {
			session = openSession();

			OSPSchedulerEntry[] array = new OSPSchedulerEntryImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session,
					ospSchedulerEntry, groupId, orderByComparator, true);

			array[1] = ospSchedulerEntry;

			array[2] = filterGetByGroupId_PrevAndNext(session,
					ospSchedulerEntry, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OSPSchedulerEntry filterGetByGroupId_PrevAndNext(
		Session session, OSPSchedulerEntry ospSchedulerEntry, long groupId,
		OrderByComparator<OSPSchedulerEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OSPSchedulerEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, OSPSchedulerEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, OSPSchedulerEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ospSchedulerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OSPSchedulerEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the osp scheduler entries where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (OSPSchedulerEntry ospSchedulerEntry : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ospSchedulerEntry);
		}
	}

	/**
	 * Returns the number of osp scheduler entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching osp scheduler entries
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OSPSCHEDULERENTRY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of osp scheduler entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching osp scheduler entries that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_OSPSCHEDULERENTRY_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OSPSchedulerEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "ospSchedulerEntry.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByName",
			new String[] { String.class.getName() },
			OSPSchedulerEntryModelImpl.NAME_COLUMN_BITMASK |
			OSPSchedulerEntryModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the osp scheduler entries where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching osp scheduler entries
	 */
	@Override
	public List<OSPSchedulerEntry> findByName(String name) {
		return findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<OSPSchedulerEntry> findByName(String name, int start, int end) {
		return findByName(name, start, end, null);
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
	@Override
	public List<OSPSchedulerEntry> findByName(String name, int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return findByName(name, start, end, orderByComparator, true);
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
	@Override
	public List<OSPSchedulerEntry> findByName(String name, int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] { name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] { name, start, end, orderByComparator };
		}

		List<OSPSchedulerEntry> list = null;

		if (retrieveFromCache) {
			list = (List<OSPSchedulerEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OSPSchedulerEntry ospSchedulerEntry : list) {
					if (!Objects.equals(name, ospSchedulerEntry.getName())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals("")) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				if (!pagination) {
					list = (List<OSPSchedulerEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OSPSchedulerEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first osp scheduler entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching osp scheduler entry
	 * @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry findByName_First(String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = fetchByName_First(name,
				orderByComparator);

		if (ospSchedulerEntry != null) {
			return ospSchedulerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append("}");

		throw new NoSuchOSPSchedulerEntryException(msg.toString());
	}

	/**
	 * Returns the first osp scheduler entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByName_First(String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		List<OSPSchedulerEntry> list = findByName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last osp scheduler entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching osp scheduler entry
	 * @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry findByName_Last(String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = fetchByName_Last(name,
				orderByComparator);

		if (ospSchedulerEntry != null) {
			return ospSchedulerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append("}");

		throw new NoSuchOSPSchedulerEntryException(msg.toString());
	}

	/**
	 * Returns the last osp scheduler entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByName_Last(String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		int count = countByName(name);

		if (count == 0) {
			return null;
		}

		List<OSPSchedulerEntry> list = findByName(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public OSPSchedulerEntry[] findByName_PrevAndNext(long schedulerEntryId,
		String name, OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = findByPrimaryKey(schedulerEntryId);

		Session session = null;

		try {
			session = openSession();

			OSPSchedulerEntry[] array = new OSPSchedulerEntryImpl[3];

			array[0] = getByName_PrevAndNext(session, ospSchedulerEntry, name,
					orderByComparator, true);

			array[1] = ospSchedulerEntry;

			array[2] = getByName_PrevAndNext(session, ospSchedulerEntry, name,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OSPSchedulerEntry getByName_PrevAndNext(Session session,
		OSPSchedulerEntry ospSchedulerEntry, String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_NAME_NAME_1);
		}
		else if (name.equals("")) {
			query.append(_FINDER_COLUMN_NAME_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_NAME_NAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ospSchedulerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OSPSchedulerEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the osp scheduler entries where name = &#63; from the database.
	 *
	 * @param name the name
	 */
	@Override
	public void removeByName(String name) {
		for (OSPSchedulerEntry ospSchedulerEntry : findByName(name,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ospSchedulerEntry);
		}
	}

	/**
	 * Returns the number of osp scheduler entries where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching osp scheduler entries
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OSPSCHEDULERENTRY_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals("")) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "ospSchedulerEntry.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "ospSchedulerEntry.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(ospSchedulerEntry.name IS NULL OR ospSchedulerEntry.name = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NAME = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_Name",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NAME = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_Name",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the osp scheduler entries where groupId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching osp scheduler entries
	 */
	@Override
	public List<OSPSchedulerEntry> findByG_Name(long groupId, String name) {
		return findByG_Name(groupId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<OSPSchedulerEntry> findByG_Name(long groupId, String name,
		int start, int end) {
		return findByG_Name(groupId, name, start, end, null);
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
	@Override
	public List<OSPSchedulerEntry> findByG_Name(long groupId, String name,
		int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return findByG_Name(groupId, name, start, end, orderByComparator, true);
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
	@Override
	public List<OSPSchedulerEntry> findByG_Name(long groupId, String name,
		int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NAME;
		finderArgs = new Object[] { groupId, name, start, end, orderByComparator };

		List<OSPSchedulerEntry> list = null;

		if (retrieveFromCache) {
			list = (List<OSPSchedulerEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OSPSchedulerEntry ospSchedulerEntry : list) {
					if ((groupId != ospSchedulerEntry.getGroupId()) ||
							!StringUtil.wildcardMatches(
								ospSchedulerEntry.getName(), name, '_', '%',
								'\\', true)) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_NAME_GROUPID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_NAME_NAME_1);
			}
			else if (name.equals("")) {
				query.append(_FINDER_COLUMN_G_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_NAME_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(name);
				}

				if (!pagination) {
					list = (List<OSPSchedulerEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OSPSchedulerEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public OSPSchedulerEntry findByG_Name_First(long groupId, String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = fetchByG_Name_First(groupId,
				name, orderByComparator);

		if (ospSchedulerEntry != null) {
			return ospSchedulerEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", name=");
		msg.append(name);

		msg.append("}");

		throw new NoSuchOSPSchedulerEntryException(msg.toString());
	}

	/**
	 * Returns the first osp scheduler entry in the ordered set where groupId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByG_Name_First(long groupId, String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		List<OSPSchedulerEntry> list = findByG_Name(groupId, name, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public OSPSchedulerEntry findByG_Name_Last(long groupId, String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = fetchByG_Name_Last(groupId, name,
				orderByComparator);

		if (ospSchedulerEntry != null) {
			return ospSchedulerEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", name=");
		msg.append(name);

		msg.append("}");

		throw new NoSuchOSPSchedulerEntryException(msg.toString());
	}

	/**
	 * Returns the last osp scheduler entry in the ordered set where groupId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByG_Name_Last(long groupId, String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		int count = countByG_Name(groupId, name);

		if (count == 0) {
			return null;
		}

		List<OSPSchedulerEntry> list = findByG_Name(groupId, name, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public OSPSchedulerEntry[] findByG_Name_PrevAndNext(long schedulerEntryId,
		long groupId, String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = findByPrimaryKey(schedulerEntryId);

		Session session = null;

		try {
			session = openSession();

			OSPSchedulerEntry[] array = new OSPSchedulerEntryImpl[3];

			array[0] = getByG_Name_PrevAndNext(session, ospSchedulerEntry,
					groupId, name, orderByComparator, true);

			array[1] = ospSchedulerEntry;

			array[2] = getByG_Name_PrevAndNext(session, ospSchedulerEntry,
					groupId, name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OSPSchedulerEntry getByG_Name_PrevAndNext(Session session,
		OSPSchedulerEntry ospSchedulerEntry, long groupId, String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_NAME_GROUPID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_G_NAME_NAME_1);
		}
		else if (name.equals("")) {
			query.append(_FINDER_COLUMN_G_NAME_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_NAME_NAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ospSchedulerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OSPSchedulerEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the osp scheduler entries that the user has permission to view where groupId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching osp scheduler entries that the user has permission to view
	 */
	@Override
	public List<OSPSchedulerEntry> filterFindByG_Name(long groupId, String name) {
		return filterFindByG_Name(groupId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<OSPSchedulerEntry> filterFindByG_Name(long groupId,
		String name, int start, int end) {
		return filterFindByG_Name(groupId, name, start, end, null);
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
	@Override
	public List<OSPSchedulerEntry> filterFindByG_Name(long groupId,
		String name, int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_Name(groupId, name, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_NAME_GROUPID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_G_NAME_NAME_1);
		}
		else if (name.equals("")) {
			query.append(_FINDER_COLUMN_G_NAME_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_NAME_NAME_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OSPSchedulerEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, OSPSchedulerEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, OSPSchedulerEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindName) {
				qPos.add(name);
			}

			return (List<OSPSchedulerEntry>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public OSPSchedulerEntry[] filterFindByG_Name_PrevAndNext(
		long schedulerEntryId, long groupId, String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_Name_PrevAndNext(schedulerEntryId, groupId, name,
				orderByComparator);
		}

		OSPSchedulerEntry ospSchedulerEntry = findByPrimaryKey(schedulerEntryId);

		Session session = null;

		try {
			session = openSession();

			OSPSchedulerEntry[] array = new OSPSchedulerEntryImpl[3];

			array[0] = filterGetByG_Name_PrevAndNext(session,
					ospSchedulerEntry, groupId, name, orderByComparator, true);

			array[1] = ospSchedulerEntry;

			array[2] = filterGetByG_Name_PrevAndNext(session,
					ospSchedulerEntry, groupId, name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OSPSchedulerEntry filterGetByG_Name_PrevAndNext(Session session,
		OSPSchedulerEntry ospSchedulerEntry, long groupId, String name,
		OrderByComparator<OSPSchedulerEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_NAME_GROUPID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_G_NAME_NAME_1);
		}
		else if (name.equals("")) {
			query.append(_FINDER_COLUMN_G_NAME_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_NAME_NAME_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OSPSchedulerEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, OSPSchedulerEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, OSPSchedulerEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ospSchedulerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OSPSchedulerEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the osp scheduler entries where groupId = &#63; and name LIKE &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 */
	@Override
	public void removeByG_Name(long groupId, String name) {
		for (OSPSchedulerEntry ospSchedulerEntry : findByG_Name(groupId, name,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ospSchedulerEntry);
		}
	}

	/**
	 * Returns the number of osp scheduler entries where groupId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the number of matching osp scheduler entries
	 */
	@Override
	public int countByG_Name(long groupId, String name) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NAME;

		Object[] finderArgs = new Object[] { groupId, name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OSPSCHEDULERENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_NAME_GROUPID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_NAME_NAME_1);
			}
			else if (name.equals("")) {
				query.append(_FINDER_COLUMN_G_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindName) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of osp scheduler entries that the user has permission to view where groupId = &#63; and name LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the number of matching osp scheduler entries that the user has permission to view
	 */
	@Override
	public int filterCountByG_Name(long groupId, String name) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_Name(groupId, name);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_OSPSCHEDULERENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_NAME_GROUPID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_G_NAME_NAME_1);
		}
		else if (name.equals("")) {
			query.append(_FINDER_COLUMN_G_NAME_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_NAME_NAME_2);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OSPSchedulerEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindName) {
				qPos.add(name);
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_NAME_GROUPID_2 = "ospSchedulerEntry.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_NAME_NAME_1 = "ospSchedulerEntry.name IS NULL";
	private static final String _FINDER_COLUMN_G_NAME_NAME_2 = "ospSchedulerEntry.name LIKE ?";
	private static final String _FINDER_COLUMN_G_NAME_NAME_3 = "(ospSchedulerEntry.name IS NULL OR ospSchedulerEntry.name LIKE '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatus",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS =
		new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus",
			new String[] { Integer.class.getName() },
			OSPSchedulerEntryModelImpl.STATUS_COLUMN_BITMASK |
			OSPSchedulerEntryModelImpl.NAME_COLUMN_BITMASK |
			OSPSchedulerEntryModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the osp scheduler entries where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching osp scheduler entries
	 */
	@Override
	public List<OSPSchedulerEntry> findByStatus(int status) {
		return findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<OSPSchedulerEntry> findByStatus(int status, int start, int end) {
		return findByStatus(status, start, end, null);
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
	@Override
	public List<OSPSchedulerEntry> findByStatus(int status, int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return findByStatus(status, start, end, orderByComparator, true);
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
	@Override
	public List<OSPSchedulerEntry> findByStatus(int status, int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status, start, end, orderByComparator };
		}

		List<OSPSchedulerEntry> list = null;

		if (retrieveFromCache) {
			list = (List<OSPSchedulerEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OSPSchedulerEntry ospSchedulerEntry : list) {
					if ((status != ospSchedulerEntry.getStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				if (!pagination) {
					list = (List<OSPSchedulerEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OSPSchedulerEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first osp scheduler entry in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching osp scheduler entry
	 * @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry findByStatus_First(int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = fetchByStatus_First(status,
				orderByComparator);

		if (ospSchedulerEntry != null) {
			return ospSchedulerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchOSPSchedulerEntryException(msg.toString());
	}

	/**
	 * Returns the first osp scheduler entry in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByStatus_First(int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		List<OSPSchedulerEntry> list = findByStatus(status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last osp scheduler entry in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching osp scheduler entry
	 * @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry findByStatus_Last(int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = fetchByStatus_Last(status,
				orderByComparator);

		if (ospSchedulerEntry != null) {
			return ospSchedulerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchOSPSchedulerEntryException(msg.toString());
	}

	/**
	 * Returns the last osp scheduler entry in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByStatus_Last(int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		int count = countByStatus(status);

		if (count == 0) {
			return null;
		}

		List<OSPSchedulerEntry> list = findByStatus(status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public OSPSchedulerEntry[] findByStatus_PrevAndNext(long schedulerEntryId,
		int status, OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = findByPrimaryKey(schedulerEntryId);

		Session session = null;

		try {
			session = openSession();

			OSPSchedulerEntry[] array = new OSPSchedulerEntryImpl[3];

			array[0] = getByStatus_PrevAndNext(session, ospSchedulerEntry,
					status, orderByComparator, true);

			array[1] = ospSchedulerEntry;

			array[2] = getByStatus_PrevAndNext(session, ospSchedulerEntry,
					status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OSPSchedulerEntry getByStatus_PrevAndNext(Session session,
		OSPSchedulerEntry ospSchedulerEntry, int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);

		query.append(_FINDER_COLUMN_STATUS_STATUS_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ospSchedulerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OSPSchedulerEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the osp scheduler entries where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	@Override
	public void removeByStatus(int status) {
		for (OSPSchedulerEntry ospSchedulerEntry : findByStatus(status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ospSchedulerEntry);
		}
	}

	/**
	 * Returns the number of osp scheduler entries where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching osp scheduler entries
	 */
	@Override
	public int countByStatus(int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STATUS;

		Object[] finderArgs = new Object[] { status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OSPSCHEDULERENTRY_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_STATUS_STATUS_2 = "ospSchedulerEntry.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			OSPSchedulerEntryModelImpl.GROUPID_COLUMN_BITMASK |
			OSPSchedulerEntryModelImpl.STATUS_COLUMN_BITMASK |
			OSPSchedulerEntryModelImpl.NAME_COLUMN_BITMASK |
			OSPSchedulerEntryModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_S = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the osp scheduler entries where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching osp scheduler entries
	 */
	@Override
	public List<OSPSchedulerEntry> findByG_S(long groupId, int status) {
		return findByG_S(groupId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<OSPSchedulerEntry> findByG_S(long groupId, int status,
		int start, int end) {
		return findByG_S(groupId, status, start, end, null);
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
	@Override
	public List<OSPSchedulerEntry> findByG_S(long groupId, int status,
		int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return findByG_S(groupId, status, start, end, orderByComparator, true);
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
	@Override
	public List<OSPSchedulerEntry> findByG_S(long groupId, int status,
		int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S;
			finderArgs = new Object[] { groupId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S;
			finderArgs = new Object[] {
					groupId, status,
					
					start, end, orderByComparator
				};
		}

		List<OSPSchedulerEntry> list = null;

		if (retrieveFromCache) {
			list = (List<OSPSchedulerEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OSPSchedulerEntry ospSchedulerEntry : list) {
					if ((groupId != ospSchedulerEntry.getGroupId()) ||
							(status != ospSchedulerEntry.getStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

				if (!pagination) {
					list = (List<OSPSchedulerEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OSPSchedulerEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public OSPSchedulerEntry findByG_S_First(long groupId, int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = fetchByG_S_First(groupId, status,
				orderByComparator);

		if (ospSchedulerEntry != null) {
			return ospSchedulerEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchOSPSchedulerEntryException(msg.toString());
	}

	/**
	 * Returns the first osp scheduler entry in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByG_S_First(long groupId, int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		List<OSPSchedulerEntry> list = findByG_S(groupId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public OSPSchedulerEntry findByG_S_Last(long groupId, int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = fetchByG_S_Last(groupId, status,
				orderByComparator);

		if (ospSchedulerEntry != null) {
			return ospSchedulerEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchOSPSchedulerEntryException(msg.toString());
	}

	/**
	 * Returns the last osp scheduler entry in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByG_S_Last(long groupId, int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		int count = countByG_S(groupId, status);

		if (count == 0) {
			return null;
		}

		List<OSPSchedulerEntry> list = findByG_S(groupId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public OSPSchedulerEntry[] findByG_S_PrevAndNext(long schedulerEntryId,
		long groupId, int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = findByPrimaryKey(schedulerEntryId);

		Session session = null;

		try {
			session = openSession();

			OSPSchedulerEntry[] array = new OSPSchedulerEntryImpl[3];

			array[0] = getByG_S_PrevAndNext(session, ospSchedulerEntry,
					groupId, status, orderByComparator, true);

			array[1] = ospSchedulerEntry;

			array[2] = getByG_S_PrevAndNext(session, ospSchedulerEntry,
					groupId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OSPSchedulerEntry getByG_S_PrevAndNext(Session session,
		OSPSchedulerEntry ospSchedulerEntry, long groupId, int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_S_STATUS_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ospSchedulerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OSPSchedulerEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the osp scheduler entries that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching osp scheduler entries that the user has permission to view
	 */
	@Override
	public List<OSPSchedulerEntry> filterFindByG_S(long groupId, int status) {
		return filterFindByG_S(groupId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<OSPSchedulerEntry> filterFindByG_S(long groupId, int status,
		int start, int end) {
		return filterFindByG_S(groupId, status, start, end, null);
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
	@Override
	public List<OSPSchedulerEntry> filterFindByG_S(long groupId, int status,
		int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_S(groupId, status, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OSPSchedulerEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, OSPSchedulerEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, OSPSchedulerEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(status);

			return (List<OSPSchedulerEntry>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public OSPSchedulerEntry[] filterFindByG_S_PrevAndNext(
		long schedulerEntryId, long groupId, int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator)
		throws NoSuchOSPSchedulerEntryException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_S_PrevAndNext(schedulerEntryId, groupId, status,
				orderByComparator);
		}

		OSPSchedulerEntry ospSchedulerEntry = findByPrimaryKey(schedulerEntryId);

		Session session = null;

		try {
			session = openSession();

			OSPSchedulerEntry[] array = new OSPSchedulerEntryImpl[3];

			array[0] = filterGetByG_S_PrevAndNext(session, ospSchedulerEntry,
					groupId, status, orderByComparator, true);

			array[1] = ospSchedulerEntry;

			array[2] = filterGetByG_S_PrevAndNext(session, ospSchedulerEntry,
					groupId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OSPSchedulerEntry filterGetByG_S_PrevAndNext(Session session,
		OSPSchedulerEntry ospSchedulerEntry, long groupId, int status,
		OrderByComparator<OSPSchedulerEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OSPSCHEDULERENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OSPSchedulerEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OSPSchedulerEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, OSPSchedulerEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, OSPSchedulerEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ospSchedulerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OSPSchedulerEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the osp scheduler entries where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 */
	@Override
	public void removeByG_S(long groupId, int status) {
		for (OSPSchedulerEntry ospSchedulerEntry : findByG_S(groupId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ospSchedulerEntry);
		}
	}

	/**
	 * Returns the number of osp scheduler entries where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching osp scheduler entries
	 */
	@Override
	public int countByG_S(long groupId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_S;

		Object[] finderArgs = new Object[] { groupId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OSPSCHEDULERENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of osp scheduler entries that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching osp scheduler entries that the user has permission to view
	 */
	@Override
	public int filterCountByG_S(long groupId, int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_S(groupId, status);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_OSPSCHEDULERENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OSPSchedulerEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(status);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_S_GROUPID_2 = "ospSchedulerEntry.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_S_STATUS_2 = "ospSchedulerEntry.status = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_NAMEVERSION = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByNameVersion",
			new String[] { String.class.getName(), String.class.getName() },
			OSPSchedulerEntryModelImpl.NAME_COLUMN_BITMASK |
			OSPSchedulerEntryModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAMEVERSION = new FinderPath(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByNameVersion",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns the osp scheduler entry where name = &#63; and version = &#63; or throws a {@link NoSuchOSPSchedulerEntryException} if it could not be found.
	 *
	 * @param name the name
	 * @param version the version
	 * @return the matching osp scheduler entry
	 * @throws NoSuchOSPSchedulerEntryException if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry findByNameVersion(String name, String version)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = fetchByNameVersion(name, version);

		if (ospSchedulerEntry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(", version=");
			msg.append(version);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchOSPSchedulerEntryException(msg.toString());
		}

		return ospSchedulerEntry;
	}

	/**
	 * Returns the osp scheduler entry where name = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @param version the version
	 * @return the matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByNameVersion(String name, String version) {
		return fetchByNameVersion(name, version, true);
	}

	/**
	 * Returns the osp scheduler entry where name = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param version the version
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching osp scheduler entry, or <code>null</code> if a matching osp scheduler entry could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByNameVersion(String name, String version,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { name, version };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_NAMEVERSION,
					finderArgs, this);
		}

		if (result instanceof OSPSchedulerEntry) {
			OSPSchedulerEntry ospSchedulerEntry = (OSPSchedulerEntry)result;

			if (!Objects.equals(name, ospSchedulerEntry.getName()) ||
					!Objects.equals(version, ospSchedulerEntry.getVersion())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_OSPSCHEDULERENTRY_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAMEVERSION_NAME_1);
			}
			else if (name.equals("")) {
				query.append(_FINDER_COLUMN_NAMEVERSION_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAMEVERSION_NAME_2);
			}

			boolean bindVersion = false;

			if (version == null) {
				query.append(_FINDER_COLUMN_NAMEVERSION_VERSION_1);
			}
			else if (version.equals("")) {
				query.append(_FINDER_COLUMN_NAMEVERSION_VERSION_3);
			}
			else {
				bindVersion = true;

				query.append(_FINDER_COLUMN_NAMEVERSION_VERSION_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				if (bindVersion) {
					qPos.add(version);
				}

				List<OSPSchedulerEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_NAMEVERSION,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"OSPSchedulerEntryPersistenceImpl.fetchByNameVersion(String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OSPSchedulerEntry ospSchedulerEntry = list.get(0);

					result = ospSchedulerEntry;

					cacheResult(ospSchedulerEntry);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_NAMEVERSION,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (OSPSchedulerEntry)result;
		}
	}

	/**
	 * Removes the osp scheduler entry where name = &#63; and version = &#63; from the database.
	 *
	 * @param name the name
	 * @param version the version
	 * @return the osp scheduler entry that was removed
	 */
	@Override
	public OSPSchedulerEntry removeByNameVersion(String name, String version)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = findByNameVersion(name, version);

		return remove(ospSchedulerEntry);
	}

	/**
	 * Returns the number of osp scheduler entries where name = &#63; and version = &#63;.
	 *
	 * @param name the name
	 * @param version the version
	 * @return the number of matching osp scheduler entries
	 */
	@Override
	public int countByNameVersion(String name, String version) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAMEVERSION;

		Object[] finderArgs = new Object[] { name, version };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OSPSCHEDULERENTRY_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAMEVERSION_NAME_1);
			}
			else if (name.equals("")) {
				query.append(_FINDER_COLUMN_NAMEVERSION_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAMEVERSION_NAME_2);
			}

			boolean bindVersion = false;

			if (version == null) {
				query.append(_FINDER_COLUMN_NAMEVERSION_VERSION_1);
			}
			else if (version.equals("")) {
				query.append(_FINDER_COLUMN_NAMEVERSION_VERSION_3);
			}
			else {
				bindVersion = true;

				query.append(_FINDER_COLUMN_NAMEVERSION_VERSION_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				if (bindVersion) {
					qPos.add(version);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_NAMEVERSION_NAME_1 = "ospSchedulerEntry.name IS NULL AND ";
	private static final String _FINDER_COLUMN_NAMEVERSION_NAME_2 = "ospSchedulerEntry.name = ? AND ";
	private static final String _FINDER_COLUMN_NAMEVERSION_NAME_3 = "(ospSchedulerEntry.name IS NULL OR ospSchedulerEntry.name = '') AND ";
	private static final String _FINDER_COLUMN_NAMEVERSION_VERSION_1 = "ospSchedulerEntry.version IS NULL";
	private static final String _FINDER_COLUMN_NAMEVERSION_VERSION_2 = "ospSchedulerEntry.version = ?";
	private static final String _FINDER_COLUMN_NAMEVERSION_VERSION_3 = "(ospSchedulerEntry.version IS NULL OR ospSchedulerEntry.version = '')";

	public OSPSchedulerEntryPersistenceImpl() {
		setModelClass(OSPSchedulerEntry.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the osp scheduler entry in the entity cache if it is enabled.
	 *
	 * @param ospSchedulerEntry the osp scheduler entry
	 */
	@Override
	public void cacheResult(OSPSchedulerEntry ospSchedulerEntry) {
		entityCache.putResult(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class, ospSchedulerEntry.getPrimaryKey(),
			ospSchedulerEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				ospSchedulerEntry.getUuid(), ospSchedulerEntry.getGroupId()
			}, ospSchedulerEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_NAMEVERSION,
			new Object[] {
				ospSchedulerEntry.getName(), ospSchedulerEntry.getVersion()
			}, ospSchedulerEntry);

		ospSchedulerEntry.resetOriginalValues();
	}

	/**
	 * Caches the osp scheduler entries in the entity cache if it is enabled.
	 *
	 * @param ospSchedulerEntries the osp scheduler entries
	 */
	@Override
	public void cacheResult(List<OSPSchedulerEntry> ospSchedulerEntries) {
		for (OSPSchedulerEntry ospSchedulerEntry : ospSchedulerEntries) {
			if (entityCache.getResult(
						OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
						OSPSchedulerEntryImpl.class,
						ospSchedulerEntry.getPrimaryKey()) == null) {
				cacheResult(ospSchedulerEntry);
			}
			else {
				ospSchedulerEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all osp scheduler entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OSPSchedulerEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the osp scheduler entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OSPSchedulerEntry ospSchedulerEntry) {
		entityCache.removeResult(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class, ospSchedulerEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((OSPSchedulerEntryModelImpl)ospSchedulerEntry,
			true);
	}

	@Override
	public void clearCache(List<OSPSchedulerEntry> ospSchedulerEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OSPSchedulerEntry ospSchedulerEntry : ospSchedulerEntries) {
			entityCache.removeResult(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
				OSPSchedulerEntryImpl.class, ospSchedulerEntry.getPrimaryKey());

			clearUniqueFindersCache((OSPSchedulerEntryModelImpl)ospSchedulerEntry,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		OSPSchedulerEntryModelImpl ospSchedulerEntryModelImpl) {
		Object[] args = new Object[] {
				ospSchedulerEntryModelImpl.getUuid(),
				ospSchedulerEntryModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			ospSchedulerEntryModelImpl, false);

		args = new Object[] {
				ospSchedulerEntryModelImpl.getName(),
				ospSchedulerEntryModelImpl.getVersion()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_NAMEVERSION, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_NAMEVERSION, args,
			ospSchedulerEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		OSPSchedulerEntryModelImpl ospSchedulerEntryModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					ospSchedulerEntryModelImpl.getUuid(),
					ospSchedulerEntryModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((ospSchedulerEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					ospSchedulerEntryModelImpl.getOriginalUuid(),
					ospSchedulerEntryModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					ospSchedulerEntryModelImpl.getName(),
					ospSchedulerEntryModelImpl.getVersion()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAMEVERSION, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAMEVERSION, args);
		}

		if ((ospSchedulerEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAMEVERSION.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					ospSchedulerEntryModelImpl.getOriginalName(),
					ospSchedulerEntryModelImpl.getOriginalVersion()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAMEVERSION, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAMEVERSION, args);
		}
	}

	/**
	 * Creates a new osp scheduler entry with the primary key. Does not add the osp scheduler entry to the database.
	 *
	 * @param schedulerEntryId the primary key for the new osp scheduler entry
	 * @return the new osp scheduler entry
	 */
	@Override
	public OSPSchedulerEntry create(long schedulerEntryId) {
		OSPSchedulerEntry ospSchedulerEntry = new OSPSchedulerEntryImpl();

		ospSchedulerEntry.setNew(true);
		ospSchedulerEntry.setPrimaryKey(schedulerEntryId);

		String uuid = PortalUUIDUtil.generate();

		ospSchedulerEntry.setUuid(uuid);

		ospSchedulerEntry.setCompanyId(companyProvider.getCompanyId());

		return ospSchedulerEntry;
	}

	/**
	 * Removes the osp scheduler entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schedulerEntryId the primary key of the osp scheduler entry
	 * @return the osp scheduler entry that was removed
	 * @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	 */
	@Override
	public OSPSchedulerEntry remove(long schedulerEntryId)
		throws NoSuchOSPSchedulerEntryException {
		return remove((Serializable)schedulerEntryId);
	}

	/**
	 * Removes the osp scheduler entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the osp scheduler entry
	 * @return the osp scheduler entry that was removed
	 * @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	 */
	@Override
	public OSPSchedulerEntry remove(Serializable primaryKey)
		throws NoSuchOSPSchedulerEntryException {
		Session session = null;

		try {
			session = openSession();

			OSPSchedulerEntry ospSchedulerEntry = (OSPSchedulerEntry)session.get(OSPSchedulerEntryImpl.class,
					primaryKey);

			if (ospSchedulerEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOSPSchedulerEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ospSchedulerEntry);
		}
		catch (NoSuchOSPSchedulerEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected OSPSchedulerEntry removeImpl(OSPSchedulerEntry ospSchedulerEntry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ospSchedulerEntry)) {
				ospSchedulerEntry = (OSPSchedulerEntry)session.get(OSPSchedulerEntryImpl.class,
						ospSchedulerEntry.getPrimaryKeyObj());
			}

			if (ospSchedulerEntry != null) {
				session.delete(ospSchedulerEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ospSchedulerEntry != null) {
			clearCache(ospSchedulerEntry);
		}

		return ospSchedulerEntry;
	}

	@Override
	public OSPSchedulerEntry updateImpl(OSPSchedulerEntry ospSchedulerEntry) {
		boolean isNew = ospSchedulerEntry.isNew();

		if (!(ospSchedulerEntry instanceof OSPSchedulerEntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(ospSchedulerEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(ospSchedulerEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in ospSchedulerEntry proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom OSPSchedulerEntry implementation " +
				ospSchedulerEntry.getClass());
		}

		OSPSchedulerEntryModelImpl ospSchedulerEntryModelImpl = (OSPSchedulerEntryModelImpl)ospSchedulerEntry;

		if (Validator.isNull(ospSchedulerEntry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			ospSchedulerEntry.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (ospSchedulerEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				ospSchedulerEntry.setCreateDate(now);
			}
			else {
				ospSchedulerEntry.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!ospSchedulerEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				ospSchedulerEntry.setModifiedDate(now);
			}
			else {
				ospSchedulerEntry.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (ospSchedulerEntry.isNew()) {
				session.save(ospSchedulerEntry);

				ospSchedulerEntry.setNew(false);
			}
			else {
				ospSchedulerEntry = (OSPSchedulerEntry)session.merge(ospSchedulerEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!OSPSchedulerEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { ospSchedulerEntryModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					ospSchedulerEntryModelImpl.getUuid(),
					ospSchedulerEntryModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { ospSchedulerEntryModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] { ospSchedulerEntryModelImpl.getName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
				args);

			args = new Object[] { ospSchedulerEntryModelImpl.getStatus() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
				args);

			args = new Object[] {
					ospSchedulerEntryModelImpl.getGroupId(),
					ospSchedulerEntryModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((ospSchedulerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ospSchedulerEntryModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { ospSchedulerEntryModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((ospSchedulerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ospSchedulerEntryModelImpl.getOriginalUuid(),
						ospSchedulerEntryModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						ospSchedulerEntryModelImpl.getUuid(),
						ospSchedulerEntryModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((ospSchedulerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ospSchedulerEntryModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { ospSchedulerEntryModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((ospSchedulerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ospSchedulerEntryModelImpl.getOriginalName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);

				args = new Object[] { ospSchedulerEntryModelImpl.getName() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);
			}

			if ((ospSchedulerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ospSchedulerEntryModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);

				args = new Object[] { ospSchedulerEntryModelImpl.getStatus() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);
			}

			if ((ospSchedulerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ospSchedulerEntryModelImpl.getOriginalGroupId(),
						ospSchedulerEntryModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
					args);

				args = new Object[] {
						ospSchedulerEntryModelImpl.getGroupId(),
						ospSchedulerEntryModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
					args);
			}
		}

		entityCache.putResult(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
			OSPSchedulerEntryImpl.class, ospSchedulerEntry.getPrimaryKey(),
			ospSchedulerEntry, false);

		clearUniqueFindersCache(ospSchedulerEntryModelImpl, false);
		cacheUniqueFindersCache(ospSchedulerEntryModelImpl);

		ospSchedulerEntry.resetOriginalValues();

		return ospSchedulerEntry;
	}

	/**
	 * Returns the osp scheduler entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the osp scheduler entry
	 * @return the osp scheduler entry
	 * @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	 */
	@Override
	public OSPSchedulerEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOSPSchedulerEntryException {
		OSPSchedulerEntry ospSchedulerEntry = fetchByPrimaryKey(primaryKey);

		if (ospSchedulerEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOSPSchedulerEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ospSchedulerEntry;
	}

	/**
	 * Returns the osp scheduler entry with the primary key or throws a {@link NoSuchOSPSchedulerEntryException} if it could not be found.
	 *
	 * @param schedulerEntryId the primary key of the osp scheduler entry
	 * @return the osp scheduler entry
	 * @throws NoSuchOSPSchedulerEntryException if a osp scheduler entry with the primary key could not be found
	 */
	@Override
	public OSPSchedulerEntry findByPrimaryKey(long schedulerEntryId)
		throws NoSuchOSPSchedulerEntryException {
		return findByPrimaryKey((Serializable)schedulerEntryId);
	}

	/**
	 * Returns the osp scheduler entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the osp scheduler entry
	 * @return the osp scheduler entry, or <code>null</code> if a osp scheduler entry with the primary key could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
				OSPSchedulerEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OSPSchedulerEntry ospSchedulerEntry = (OSPSchedulerEntry)serializable;

		if (ospSchedulerEntry == null) {
			Session session = null;

			try {
				session = openSession();

				ospSchedulerEntry = (OSPSchedulerEntry)session.get(OSPSchedulerEntryImpl.class,
						primaryKey);

				if (ospSchedulerEntry != null) {
					cacheResult(ospSchedulerEntry);
				}
				else {
					entityCache.putResult(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
						OSPSchedulerEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
					OSPSchedulerEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ospSchedulerEntry;
	}

	/**
	 * Returns the osp scheduler entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schedulerEntryId the primary key of the osp scheduler entry
	 * @return the osp scheduler entry, or <code>null</code> if a osp scheduler entry with the primary key could not be found
	 */
	@Override
	public OSPSchedulerEntry fetchByPrimaryKey(long schedulerEntryId) {
		return fetchByPrimaryKey((Serializable)schedulerEntryId);
	}

	@Override
	public Map<Serializable, OSPSchedulerEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OSPSchedulerEntry> map = new HashMap<Serializable, OSPSchedulerEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OSPSchedulerEntry ospSchedulerEntry = fetchByPrimaryKey(primaryKey);

			if (ospSchedulerEntry != null) {
				map.put(primaryKey, ospSchedulerEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
					OSPSchedulerEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (OSPSchedulerEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OSPSCHEDULERENTRY_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (OSPSchedulerEntry ospSchedulerEntry : (List<OSPSchedulerEntry>)q.list()) {
				map.put(ospSchedulerEntry.getPrimaryKeyObj(), ospSchedulerEntry);

				cacheResult(ospSchedulerEntry);

				uncachedPrimaryKeys.remove(ospSchedulerEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OSPSchedulerEntryModelImpl.ENTITY_CACHE_ENABLED,
					OSPSchedulerEntryImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the osp scheduler entries.
	 *
	 * @return the osp scheduler entries
	 */
	@Override
	public List<OSPSchedulerEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<OSPSchedulerEntry> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<OSPSchedulerEntry> findAll(int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<OSPSchedulerEntry> findAll(int start, int end,
		OrderByComparator<OSPSchedulerEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<OSPSchedulerEntry> list = null;

		if (retrieveFromCache) {
			list = (List<OSPSchedulerEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_OSPSCHEDULERENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OSPSCHEDULERENTRY;

				if (pagination) {
					sql = sql.concat(OSPSchedulerEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OSPSchedulerEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OSPSchedulerEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the osp scheduler entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OSPSchedulerEntry ospSchedulerEntry : findAll()) {
			remove(ospSchedulerEntry);
		}
	}

	/**
	 * Returns the number of osp scheduler entries.
	 *
	 * @return the number of osp scheduler entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OSPSCHEDULERENTRY);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return OSPSchedulerEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the osp scheduler entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(OSPSchedulerEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_OSPSCHEDULERENTRY = "SELECT ospSchedulerEntry FROM OSPSchedulerEntry ospSchedulerEntry";
	private static final String _SQL_SELECT_OSPSCHEDULERENTRY_WHERE_PKS_IN = "SELECT ospSchedulerEntry FROM OSPSchedulerEntry ospSchedulerEntry WHERE schedulerEntryId IN (";
	private static final String _SQL_SELECT_OSPSCHEDULERENTRY_WHERE = "SELECT ospSchedulerEntry FROM OSPSchedulerEntry ospSchedulerEntry WHERE ";
	private static final String _SQL_COUNT_OSPSCHEDULERENTRY = "SELECT COUNT(ospSchedulerEntry) FROM OSPSchedulerEntry ospSchedulerEntry";
	private static final String _SQL_COUNT_OSPSCHEDULERENTRY_WHERE = "SELECT COUNT(ospSchedulerEntry) FROM OSPSchedulerEntry ospSchedulerEntry WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "ospSchedulerEntry.schedulerEntryId";
	private static final String _FILTER_SQL_SELECT_OSPSCHEDULERENTRY_WHERE = "SELECT DISTINCT {ospSchedulerEntry.*} FROM icebreaker_OSPSchedulerEntry ospSchedulerEntry WHERE ";
	private static final String _FILTER_SQL_SELECT_OSPSCHEDULERENTRY_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {icebreaker_OSPSchedulerEntry.*} FROM (SELECT DISTINCT ospSchedulerEntry.schedulerEntryId FROM icebreaker_OSPSchedulerEntry ospSchedulerEntry WHERE ";
	private static final String _FILTER_SQL_SELECT_OSPSCHEDULERENTRY_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN icebreaker_OSPSchedulerEntry ON TEMP_TABLE.schedulerEntryId = icebreaker_OSPSchedulerEntry.schedulerEntryId";
	private static final String _FILTER_SQL_COUNT_OSPSCHEDULERENTRY_WHERE = "SELECT COUNT(DISTINCT ospSchedulerEntry.schedulerEntryId) AS COUNT_VALUE FROM icebreaker_OSPSchedulerEntry ospSchedulerEntry WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "ospSchedulerEntry";
	private static final String _FILTER_ENTITY_TABLE = "icebreaker_OSPSchedulerEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ospSchedulerEntry.";
	private static final String _ORDER_BY_ENTITY_TABLE = "icebreaker_OSPSchedulerEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OSPSchedulerEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OSPSchedulerEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(OSPSchedulerEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}