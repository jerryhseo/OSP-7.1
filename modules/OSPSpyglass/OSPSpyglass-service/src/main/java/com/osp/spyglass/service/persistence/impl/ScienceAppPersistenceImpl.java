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

package com.osp.spyglass.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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

import com.osp.spyglass.exception.NoSuchScienceAppException;
import com.osp.spyglass.model.ScienceApp;
import com.osp.spyglass.model.impl.ScienceAppImpl;
import com.osp.spyglass.model.impl.ScienceAppModelImpl;
import com.osp.spyglass.service.persistence.ScienceAppPersistence;

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
 * The persistence implementation for the science app service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jerry H. Seo
 * @see ScienceAppPersistence
 * @see com.osp.spyglass.service.persistence.ScienceAppUtil
 * @generated
 */
@ProviderType
public class ScienceAppPersistenceImpl extends BasePersistenceImpl<ScienceApp>
	implements ScienceAppPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ScienceAppUtil} to access the science app persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ScienceAppImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ScienceAppModelImpl.UUID_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the science apps where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByUuid(String uuid, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByUuid(String uuid, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator,
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

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if (!Objects.equals(uuid, scienceApp.getUuid())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

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
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
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
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByUuid_First(String uuid,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByUuid_First(uuid, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByUuid_First(String uuid,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByUuid_Last(String uuid,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByUuid_Last(uuid, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByUuid_Last(String uuid,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where uuid = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByUuid_PrevAndNext(long scienceAppId, String uuid,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByUuid_PrevAndNext(session, scienceApp, uuid,
					orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByUuid_PrevAndNext(session, scienceApp, uuid,
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

	protected ScienceApp getByUuid_PrevAndNext(Session session,
		ScienceApp scienceApp, String uuid,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ScienceApp scienceApp : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching science apps
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "scienceApp.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "scienceApp.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(scienceApp.uuid IS NULL OR scienceApp.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ScienceAppModelImpl.UUID_COLUMN_BITMASK |
			ScienceAppModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the science app where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchScienceAppException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByUUID_G(String uuid, long groupId)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByUUID_G(uuid, groupId);

		if (scienceApp == null) {
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

			throw new NoSuchScienceAppException(msg.toString());
		}

		return scienceApp;
	}

	/**
	 * Returns the science app where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the science app where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ScienceApp) {
			ScienceApp scienceApp = (ScienceApp)result;

			if (!Objects.equals(uuid, scienceApp.getUuid()) ||
					(groupId != scienceApp.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

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

				List<ScienceApp> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ScienceApp scienceApp = list.get(0);

					result = scienceApp;

					cacheResult(scienceApp);
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
			return (ScienceApp)result;
		}
	}

	/**
	 * Removes the science app where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the science app that was removed
	 */
	@Override
	public ScienceApp removeByUUID_G(String uuid, long groupId)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByUUID_G(uuid, groupId);

		return remove(scienceApp);
	}

	/**
	 * Returns the number of science apps where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching science apps
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "scienceApp.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "scienceApp.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(scienceApp.uuid IS NULL OR scienceApp.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "scienceApp.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ScienceAppModelImpl.UUID_COLUMN_BITMASK |
			ScienceAppModelImpl.COMPANYID_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the science apps where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ScienceApp> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ScienceApp> orderByComparator,
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

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if (!Objects.equals(uuid, scienceApp.getUuid()) ||
							(companyId != scienceApp.getCompanyId())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

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
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
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
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByUuid_C_PrevAndNext(long scienceAppId,
		String uuid, long companyId,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, scienceApp, uuid,
					companyId, orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByUuid_C_PrevAndNext(session, scienceApp, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScienceApp getByUuid_C_PrevAndNext(Session session,
		ScienceApp scienceApp, String uuid, long companyId,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ScienceApp scienceApp : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching science apps
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "scienceApp.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "scienceApp.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(scienceApp.uuid IS NULL OR scienceApp.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "scienceApp.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			ScienceAppModelImpl.GROUPID_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the science apps where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByGroupId(long groupId, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByGroupId(long groupId, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator,
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

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if ((groupId != scienceApp.getGroupId())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByGroupId_First(long groupId,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByGroupId_First(groupId, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByGroupId_First(long groupId,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByGroupId_Last(long groupId,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByGroupId_Last(groupId, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByGroupId_Last(long groupId,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where groupId = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByGroupId_PrevAndNext(long scienceAppId,
		long groupId, OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, scienceApp, groupId,
					orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByGroupId_PrevAndNext(session, scienceApp, groupId,
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

	protected ScienceApp getByGroupId_PrevAndNext(Session session,
		ScienceApp scienceApp, long groupId,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (ScienceApp scienceApp : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching science apps
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "scienceApp.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			ScienceAppModelImpl.USERID_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the science apps where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByUserId(long userId, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator) {
		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByUserId(long userId, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if ((userId != scienceApp.getUserId())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByUserId_First(long userId,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByUserId_First(userId, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByUserId_First(long userId,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByUserId_Last(long userId,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByUserId_Last(userId, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByUserId_Last(long userId,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where userId = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByUserId_PrevAndNext(long scienceAppId,
		long userId, OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByUserId_PrevAndNext(session, scienceApp, userId,
					orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByUserId_PrevAndNext(session, scienceApp, userId,
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

	protected ScienceApp getByUserId_PrevAndNext(Session session,
		ScienceApp scienceApp, long userId,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (ScienceApp scienceApp : findByUserId(userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching science apps
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "scienceApp.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatus",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus",
			new String[] { Integer.class.getName() },
			ScienceAppModelImpl.STATUS_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the science apps where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByStatus(int status) {
		return findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByStatus(int status, int start, int end) {
		return findByStatus(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByStatus(int status, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator) {
		return findByStatus(status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByStatus(int status, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator,
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

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if ((status != scienceApp.getStatus())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByStatus_First(int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByStatus_First(status, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByStatus_First(int status,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByStatus(status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByStatus_Last(int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByStatus_Last(status, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByStatus_Last(int status,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByStatus(status);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByStatus(status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where status = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByStatus_PrevAndNext(long scienceAppId, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByStatus_PrevAndNext(session, scienceApp, status,
					orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByStatus_PrevAndNext(session, scienceApp, status,
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

	protected ScienceApp getByStatus_PrevAndNext(Session session,
		ScienceApp scienceApp, int status,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	@Override
	public void removeByStatus(int status) {
		for (ScienceApp scienceApp : findByStatus(status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching science apps
	 */
	@Override
	public int countByStatus(int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STATUS;

		Object[] finderArgs = new Object[] { status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

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

	private static final String _FINDER_COLUMN_STATUS_STATUS_2 = "scienceApp.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U",
			new String[] { Long.class.getName(), Long.class.getName() },
			ScienceAppModelImpl.GROUPID_COLUMN_BITMASK |
			ScienceAppModelImpl.USERID_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the science apps where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByG_U(long groupId, long userId) {
		return findByG_U(groupId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the science apps where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByG_U(long groupId, long userId, int start,
		int end) {
		return findByG_U(groupId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByG_U(long groupId, long userId, int start,
		int end, OrderByComparator<ScienceApp> orderByComparator) {
		return findByG_U(groupId, userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByG_U(long groupId, long userId, int start,
		int end, OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U;
			finderArgs = new Object[] { groupId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U;
			finderArgs = new Object[] {
					groupId, userId,
					
					start, end, orderByComparator
				};
		}

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if ((groupId != scienceApp.getGroupId()) ||
							(userId != scienceApp.getUserId())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByG_U_First(long groupId, long userId,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByG_U_First(groupId, userId,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByG_U_First(long groupId, long userId,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByG_U(groupId, userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByG_U_Last(long groupId, long userId,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByG_U_Last(groupId, userId,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByG_U_Last(long groupId, long userId,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByG_U(groupId, userId);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByG_U(groupId, userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByG_U_PrevAndNext(long scienceAppId, long groupId,
		long userId, OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByG_U_PrevAndNext(session, scienceApp, groupId,
					userId, orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByG_U_PrevAndNext(session, scienceApp, groupId,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScienceApp getByG_U_PrevAndNext(Session session,
		ScienceApp scienceApp, long groupId, long userId,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 */
	@Override
	public void removeByG_U(long groupId, long userId) {
		for (ScienceApp scienceApp : findByG_U(groupId, userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching science apps
	 */
	@Override
	public int countByG_U(long groupId, long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_U;

		Object[] finderArgs = new Object[] { groupId, userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_G_U_GROUPID_2 = "scienceApp.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_USERID_2 = "scienceApp.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			ScienceAppModelImpl.GROUPID_COLUMN_BITMASK |
			ScienceAppModelImpl.STATUS_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_S = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the science apps where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByG_S(long groupId, int status) {
		return findByG_S(groupId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the science apps where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByG_S(long groupId, int status, int start,
		int end) {
		return findByG_S(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByG_S(long groupId, int status, int start,
		int end, OrderByComparator<ScienceApp> orderByComparator) {
		return findByG_S(groupId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByG_S(long groupId, int status, int start,
		int end, OrderByComparator<ScienceApp> orderByComparator,
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

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if ((groupId != scienceApp.getGroupId()) ||
							(status != scienceApp.getStatus())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
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
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByG_S_First(long groupId, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByG_S_First(groupId, status,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByG_S_First(long groupId, int status,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByG_S(groupId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByG_S_Last(long groupId, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByG_S_Last(groupId, status,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByG_S_Last(long groupId, int status,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByG_S(groupId, status);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByG_S(groupId, status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByG_S_PrevAndNext(long scienceAppId, long groupId,
		int status, OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByG_S_PrevAndNext(session, scienceApp, groupId,
					status, orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByG_S_PrevAndNext(session, scienceApp, groupId,
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

	protected ScienceApp getByG_S_PrevAndNext(Session session,
		ScienceApp scienceApp, long groupId, int status,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 */
	@Override
	public void removeByG_S(long groupId, int status) {
		for (ScienceApp scienceApp : findByG_S(groupId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching science apps
	 */
	@Override
	public int countByG_S(long groupId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_S;

		Object[] finderArgs = new Object[] { groupId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

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

	private static final String _FINDER_COLUMN_G_S_GROUPID_2 = "scienceApp.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_S_STATUS_2 = "scienceApp.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_S = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_S = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			ScienceAppModelImpl.USERID_COLUMN_BITMASK |
			ScienceAppModelImpl.STATUS_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_S = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the science apps where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByU_S(long userId, int status) {
		return findByU_S(userId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the science apps where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByU_S(long userId, int status, int start,
		int end) {
		return findByU_S(userId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByU_S(long userId, int status, int start,
		int end, OrderByComparator<ScienceApp> orderByComparator) {
		return findByU_S(userId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByU_S(long userId, int status, int start,
		int end, OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_S;
			finderArgs = new Object[] { userId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_S;
			finderArgs = new Object[] {
					userId, status,
					
					start, end, orderByComparator
				};
		}

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if ((userId != scienceApp.getUserId()) ||
							(status != scienceApp.getStatus())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_U_S_USERID_2);

			query.append(_FINDER_COLUMN_U_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(status);

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByU_S_First(long userId, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByU_S_First(userId, status,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByU_S_First(long userId, int status,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByU_S(userId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByU_S_Last(long userId, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByU_S_Last(userId, status,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByU_S_Last(long userId, int status,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByU_S(userId, status);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByU_S(userId, status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByU_S_PrevAndNext(long scienceAppId, long userId,
		int status, OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByU_S_PrevAndNext(session, scienceApp, userId,
					status, orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByU_S_PrevAndNext(session, scienceApp, userId,
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

	protected ScienceApp getByU_S_PrevAndNext(Session session,
		ScienceApp scienceApp, long userId, int status,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		query.append(_FINDER_COLUMN_U_S_USERID_2);

		query.append(_FINDER_COLUMN_U_S_STATUS_2);

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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where userId = &#63; and status = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param status the status
	 */
	@Override
	public void removeByU_S(long userId, int status) {
		for (ScienceApp scienceApp : findByU_S(userId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching science apps
	 */
	@Override
	public int countByU_S(long userId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_S;

		Object[] finderArgs = new Object[] { userId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_U_S_USERID_2);

			query.append(_FINDER_COLUMN_U_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_U_S_USERID_2 = "scienceApp.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_S_STATUS_2 = "scienceApp.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U_S = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			ScienceAppModelImpl.GROUPID_COLUMN_BITMASK |
			ScienceAppModelImpl.USERID_COLUMN_BITMASK |
			ScienceAppModelImpl.STATUS_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U_S = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the science apps where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByG_U_S(long groupId, long userId, int status) {
		return findByG_U_S(groupId, userId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByG_U_S(long groupId, long userId, int status,
		int start, int end) {
		return findByG_U_S(groupId, userId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByG_U_S(long groupId, long userId, int status,
		int start, int end, OrderByComparator<ScienceApp> orderByComparator) {
		return findByG_U_S(groupId, userId, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByG_U_S(long groupId, long userId, int status,
		int start, int end, OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S;
			finderArgs = new Object[] { groupId, userId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U_S;
			finderArgs = new Object[] {
					groupId, userId, status,
					
					start, end, orderByComparator
				};
		}

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if ((groupId != scienceApp.getGroupId()) ||
							(userId != scienceApp.getUserId()) ||
							(status != scienceApp.getStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_S_USERID_2);

			query.append(_FINDER_COLUMN_G_U_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(status);

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByG_U_S_First(long groupId, long userId, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByG_U_S_First(groupId, userId, status,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByG_U_S_First(long groupId, long userId, int status,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByG_U_S(groupId, userId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByG_U_S_Last(long groupId, long userId, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByG_U_S_Last(groupId, userId, status,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByG_U_S_Last(long groupId, long userId, int status,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByG_U_S(groupId, userId, status);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByG_U_S(groupId, userId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByG_U_S_PrevAndNext(long scienceAppId,
		long groupId, long userId, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByG_U_S_PrevAndNext(session, scienceApp, groupId,
					userId, status, orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByG_U_S_PrevAndNext(session, scienceApp, groupId,
					userId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScienceApp getByG_U_S_PrevAndNext(Session session,
		ScienceApp scienceApp, long groupId, long userId, int status,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_S_USERID_2);

		query.append(_FINDER_COLUMN_G_U_S_STATUS_2);

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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where groupId = &#63; and userId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 */
	@Override
	public void removeByG_U_S(long groupId, long userId, int status) {
		for (ScienceApp scienceApp : findByG_U_S(groupId, userId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching science apps
	 */
	@Override
	public int countByG_U_S(long groupId, long userId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_U_S;

		Object[] finderArgs = new Object[] { groupId, userId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_S_USERID_2);

			query.append(_FINDER_COLUMN_G_U_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_G_U_S_GROUPID_2 = "scienceApp.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_S_USERID_2 = "scienceApp.userId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_S_STATUS_2 = "scienceApp.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPTYPE = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAppType",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAppType",
			new String[] { String.class.getName() },
			ScienceAppModelImpl.APPTYPE_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPTYPE = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAppType",
			new String[] { String.class.getName() });

	/**
	 * Returns all the science apps where appType = &#63;.
	 *
	 * @param appType the app type
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType(String appType) {
		return findByAppType(appType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where appType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param appType the app type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType(String appType, int start, int end) {
		return findByAppType(appType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where appType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param appType the app type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType(String appType, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator) {
		return findByAppType(appType, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where appType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param appType the app type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType(String appType, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE;
			finderArgs = new Object[] { appType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPTYPE;
			finderArgs = new Object[] { appType, start, end, orderByComparator };
		}

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if (!Objects.equals(appType, scienceApp.getAppType())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			boolean bindAppType = false;

			if (appType == null) {
				query.append(_FINDER_COLUMN_APPTYPE_APPTYPE_1);
			}
			else if (appType.equals("")) {
				query.append(_FINDER_COLUMN_APPTYPE_APPTYPE_3);
			}
			else {
				bindAppType = true;

				query.append(_FINDER_COLUMN_APPTYPE_APPTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAppType) {
					qPos.add(appType);
				}

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where appType = &#63;.
	 *
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByAppType_First(String appType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByAppType_First(appType, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appType=");
		msg.append(appType);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where appType = &#63;.
	 *
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByAppType_First(String appType,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByAppType(appType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where appType = &#63;.
	 *
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByAppType_Last(String appType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByAppType_Last(appType, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appType=");
		msg.append(appType);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where appType = &#63;.
	 *
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByAppType_Last(String appType,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByAppType(appType);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByAppType(appType, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where appType = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByAppType_PrevAndNext(long scienceAppId,
		String appType, OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByAppType_PrevAndNext(session, scienceApp, appType,
					orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByAppType_PrevAndNext(session, scienceApp, appType,
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

	protected ScienceApp getByAppType_PrevAndNext(Session session,
		ScienceApp scienceApp, String appType,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		boolean bindAppType = false;

		if (appType == null) {
			query.append(_FINDER_COLUMN_APPTYPE_APPTYPE_1);
		}
		else if (appType.equals("")) {
			query.append(_FINDER_COLUMN_APPTYPE_APPTYPE_3);
		}
		else {
			bindAppType = true;

			query.append(_FINDER_COLUMN_APPTYPE_APPTYPE_2);
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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindAppType) {
			qPos.add(appType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where appType = &#63; from the database.
	 *
	 * @param appType the app type
	 */
	@Override
	public void removeByAppType(String appType) {
		for (ScienceApp scienceApp : findByAppType(appType, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where appType = &#63;.
	 *
	 * @param appType the app type
	 * @return the number of matching science apps
	 */
	@Override
	public int countByAppType(String appType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_APPTYPE;

		Object[] finderArgs = new Object[] { appType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			boolean bindAppType = false;

			if (appType == null) {
				query.append(_FINDER_COLUMN_APPTYPE_APPTYPE_1);
			}
			else if (appType.equals("")) {
				query.append(_FINDER_COLUMN_APPTYPE_APPTYPE_3);
			}
			else {
				bindAppType = true;

				query.append(_FINDER_COLUMN_APPTYPE_APPTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAppType) {
					qPos.add(appType);
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

	private static final String _FINDER_COLUMN_APPTYPE_APPTYPE_1 = "scienceApp.appType IS NULL";
	private static final String _FINDER_COLUMN_APPTYPE_APPTYPE_2 = "scienceApp.appType = ?";
	private static final String _FINDER_COLUMN_APPTYPE_APPTYPE_3 = "(scienceApp.appType IS NULL OR scienceApp.appType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPTYPE_G =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAppType_G",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAppType_G",
			new String[] { Long.class.getName(), String.class.getName() },
			ScienceAppModelImpl.GROUPID_COLUMN_BITMASK |
			ScienceAppModelImpl.APPTYPE_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPTYPE_G = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAppType_G",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the science apps where groupId = &#63; and appType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param appType the app type
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_G(long groupId, String appType) {
		return findByAppType_G(groupId, appType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where groupId = &#63; and appType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param appType the app type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_G(long groupId, String appType,
		int start, int end) {
		return findByAppType_G(groupId, appType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and appType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param appType the app type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_G(long groupId, String appType,
		int start, int end, OrderByComparator<ScienceApp> orderByComparator) {
		return findByAppType_G(groupId, appType, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and appType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param appType the app type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_G(long groupId, String appType,
		int start, int end, OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G;
			finderArgs = new Object[] { groupId, appType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPTYPE_G;
			finderArgs = new Object[] {
					groupId, appType,
					
					start, end, orderByComparator
				};
		}

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if ((groupId != scienceApp.getGroupId()) ||
							!Objects.equals(appType, scienceApp.getAppType())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_APPTYPE_G_GROUPID_2);

			boolean bindAppType = false;

			if (appType == null) {
				query.append(_FINDER_COLUMN_APPTYPE_G_APPTYPE_1);
			}
			else if (appType.equals("")) {
				query.append(_FINDER_COLUMN_APPTYPE_G_APPTYPE_3);
			}
			else {
				bindAppType = true;

				query.append(_FINDER_COLUMN_APPTYPE_G_APPTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindAppType) {
					qPos.add(appType);
				}

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where groupId = &#63; and appType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByAppType_G_First(long groupId, String appType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByAppType_G_First(groupId, appType,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", appType=");
		msg.append(appType);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where groupId = &#63; and appType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByAppType_G_First(long groupId, String appType,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByAppType_G(groupId, appType, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and appType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByAppType_G_Last(long groupId, String appType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByAppType_G_Last(groupId, appType,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", appType=");
		msg.append(appType);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and appType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByAppType_G_Last(long groupId, String appType,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByAppType_G(groupId, appType);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByAppType_G(groupId, appType, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where groupId = &#63; and appType = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param groupId the group ID
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByAppType_G_PrevAndNext(long scienceAppId,
		long groupId, String appType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByAppType_G_PrevAndNext(session, scienceApp, groupId,
					appType, orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByAppType_G_PrevAndNext(session, scienceApp, groupId,
					appType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScienceApp getByAppType_G_PrevAndNext(Session session,
		ScienceApp scienceApp, long groupId, String appType,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		query.append(_FINDER_COLUMN_APPTYPE_G_GROUPID_2);

		boolean bindAppType = false;

		if (appType == null) {
			query.append(_FINDER_COLUMN_APPTYPE_G_APPTYPE_1);
		}
		else if (appType.equals("")) {
			query.append(_FINDER_COLUMN_APPTYPE_G_APPTYPE_3);
		}
		else {
			bindAppType = true;

			query.append(_FINDER_COLUMN_APPTYPE_G_APPTYPE_2);
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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindAppType) {
			qPos.add(appType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where groupId = &#63; and appType = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param appType the app type
	 */
	@Override
	public void removeByAppType_G(long groupId, String appType) {
		for (ScienceApp scienceApp : findByAppType_G(groupId, appType,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where groupId = &#63; and appType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param appType the app type
	 * @return the number of matching science apps
	 */
	@Override
	public int countByAppType_G(long groupId, String appType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_APPTYPE_G;

		Object[] finderArgs = new Object[] { groupId, appType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_APPTYPE_G_GROUPID_2);

			boolean bindAppType = false;

			if (appType == null) {
				query.append(_FINDER_COLUMN_APPTYPE_G_APPTYPE_1);
			}
			else if (appType.equals("")) {
				query.append(_FINDER_COLUMN_APPTYPE_G_APPTYPE_3);
			}
			else {
				bindAppType = true;

				query.append(_FINDER_COLUMN_APPTYPE_G_APPTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindAppType) {
					qPos.add(appType);
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

	private static final String _FINDER_COLUMN_APPTYPE_G_GROUPID_2 = "scienceApp.groupId = ? AND ";
	private static final String _FINDER_COLUMN_APPTYPE_G_APPTYPE_1 = "scienceApp.appType IS NULL";
	private static final String _FINDER_COLUMN_APPTYPE_G_APPTYPE_2 = "scienceApp.appType = ?";
	private static final String _FINDER_COLUMN_APPTYPE_G_APPTYPE_3 = "(scienceApp.appType IS NULL OR scienceApp.appType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPTYPE_U =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAppType_U",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_U =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAppType_U",
			new String[] { Long.class.getName(), String.class.getName() },
			ScienceAppModelImpl.USERID_COLUMN_BITMASK |
			ScienceAppModelImpl.APPTYPE_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPTYPE_U = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAppType_U",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the science apps where userId = &#63; and appType = &#63;.
	 *
	 * @param userId the user ID
	 * @param appType the app type
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_U(long userId, String appType) {
		return findByAppType_U(userId, appType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where userId = &#63; and appType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param appType the app type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_U(long userId, String appType,
		int start, int end) {
		return findByAppType_U(userId, appType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where userId = &#63; and appType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param appType the app type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_U(long userId, String appType,
		int start, int end, OrderByComparator<ScienceApp> orderByComparator) {
		return findByAppType_U(userId, appType, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the science apps where userId = &#63; and appType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param appType the app type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_U(long userId, String appType,
		int start, int end, OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_U;
			finderArgs = new Object[] { userId, appType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPTYPE_U;
			finderArgs = new Object[] {
					userId, appType,
					
					start, end, orderByComparator
				};
		}

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if ((userId != scienceApp.getUserId()) ||
							!Objects.equals(appType, scienceApp.getAppType())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_APPTYPE_U_USERID_2);

			boolean bindAppType = false;

			if (appType == null) {
				query.append(_FINDER_COLUMN_APPTYPE_U_APPTYPE_1);
			}
			else if (appType.equals("")) {
				query.append(_FINDER_COLUMN_APPTYPE_U_APPTYPE_3);
			}
			else {
				bindAppType = true;

				query.append(_FINDER_COLUMN_APPTYPE_U_APPTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindAppType) {
					qPos.add(appType);
				}

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where userId = &#63; and appType = &#63;.
	 *
	 * @param userId the user ID
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByAppType_U_First(long userId, String appType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByAppType_U_First(userId, appType,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", appType=");
		msg.append(appType);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where userId = &#63; and appType = &#63;.
	 *
	 * @param userId the user ID
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByAppType_U_First(long userId, String appType,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByAppType_U(userId, appType, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where userId = &#63; and appType = &#63;.
	 *
	 * @param userId the user ID
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByAppType_U_Last(long userId, String appType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByAppType_U_Last(userId, appType,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", appType=");
		msg.append(appType);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where userId = &#63; and appType = &#63;.
	 *
	 * @param userId the user ID
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByAppType_U_Last(long userId, String appType,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByAppType_U(userId, appType);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByAppType_U(userId, appType, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where userId = &#63; and appType = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param userId the user ID
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByAppType_U_PrevAndNext(long scienceAppId,
		long userId, String appType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByAppType_U_PrevAndNext(session, scienceApp, userId,
					appType, orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByAppType_U_PrevAndNext(session, scienceApp, userId,
					appType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScienceApp getByAppType_U_PrevAndNext(Session session,
		ScienceApp scienceApp, long userId, String appType,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		query.append(_FINDER_COLUMN_APPTYPE_U_USERID_2);

		boolean bindAppType = false;

		if (appType == null) {
			query.append(_FINDER_COLUMN_APPTYPE_U_APPTYPE_1);
		}
		else if (appType.equals("")) {
			query.append(_FINDER_COLUMN_APPTYPE_U_APPTYPE_3);
		}
		else {
			bindAppType = true;

			query.append(_FINDER_COLUMN_APPTYPE_U_APPTYPE_2);
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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (bindAppType) {
			qPos.add(appType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where userId = &#63; and appType = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param appType the app type
	 */
	@Override
	public void removeByAppType_U(long userId, String appType) {
		for (ScienceApp scienceApp : findByAppType_U(userId, appType,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where userId = &#63; and appType = &#63;.
	 *
	 * @param userId the user ID
	 * @param appType the app type
	 * @return the number of matching science apps
	 */
	@Override
	public int countByAppType_U(long userId, String appType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_APPTYPE_U;

		Object[] finderArgs = new Object[] { userId, appType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_APPTYPE_U_USERID_2);

			boolean bindAppType = false;

			if (appType == null) {
				query.append(_FINDER_COLUMN_APPTYPE_U_APPTYPE_1);
			}
			else if (appType.equals("")) {
				query.append(_FINDER_COLUMN_APPTYPE_U_APPTYPE_3);
			}
			else {
				bindAppType = true;

				query.append(_FINDER_COLUMN_APPTYPE_U_APPTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindAppType) {
					qPos.add(appType);
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

	private static final String _FINDER_COLUMN_APPTYPE_U_USERID_2 = "scienceApp.userId = ? AND ";
	private static final String _FINDER_COLUMN_APPTYPE_U_APPTYPE_1 = "scienceApp.appType IS NULL";
	private static final String _FINDER_COLUMN_APPTYPE_U_APPTYPE_2 = "scienceApp.appType = ?";
	private static final String _FINDER_COLUMN_APPTYPE_U_APPTYPE_3 = "(scienceApp.appType IS NULL OR scienceApp.appType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPTYPE_S =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAppType_S",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_S =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAppType_S",
			new String[] { String.class.getName(), Integer.class.getName() },
			ScienceAppModelImpl.APPTYPE_COLUMN_BITMASK |
			ScienceAppModelImpl.STATUS_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPTYPE_S = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAppType_S",
			new String[] { String.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the science apps where appType = &#63; and status = &#63;.
	 *
	 * @param appType the app type
	 * @param status the status
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_S(String appType, int status) {
		return findByAppType_S(appType, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where appType = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param appType the app type
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_S(String appType, int status,
		int start, int end) {
		return findByAppType_S(appType, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where appType = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param appType the app type
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_S(String appType, int status,
		int start, int end, OrderByComparator<ScienceApp> orderByComparator) {
		return findByAppType_S(appType, status, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the science apps where appType = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param appType the app type
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_S(String appType, int status,
		int start, int end, OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_S;
			finderArgs = new Object[] { appType, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPTYPE_S;
			finderArgs = new Object[] {
					appType, status,
					
					start, end, orderByComparator
				};
		}

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if (!Objects.equals(appType, scienceApp.getAppType()) ||
							(status != scienceApp.getStatus())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			boolean bindAppType = false;

			if (appType == null) {
				query.append(_FINDER_COLUMN_APPTYPE_S_APPTYPE_1);
			}
			else if (appType.equals("")) {
				query.append(_FINDER_COLUMN_APPTYPE_S_APPTYPE_3);
			}
			else {
				bindAppType = true;

				query.append(_FINDER_COLUMN_APPTYPE_S_APPTYPE_2);
			}

			query.append(_FINDER_COLUMN_APPTYPE_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAppType) {
					qPos.add(appType);
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where appType = &#63; and status = &#63;.
	 *
	 * @param appType the app type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByAppType_S_First(String appType, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByAppType_S_First(appType, status,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appType=");
		msg.append(appType);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where appType = &#63; and status = &#63;.
	 *
	 * @param appType the app type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByAppType_S_First(String appType, int status,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByAppType_S(appType, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where appType = &#63; and status = &#63;.
	 *
	 * @param appType the app type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByAppType_S_Last(String appType, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByAppType_S_Last(appType, status,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appType=");
		msg.append(appType);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where appType = &#63; and status = &#63;.
	 *
	 * @param appType the app type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByAppType_S_Last(String appType, int status,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByAppType_S(appType, status);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByAppType_S(appType, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where appType = &#63; and status = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param appType the app type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByAppType_S_PrevAndNext(long scienceAppId,
		String appType, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByAppType_S_PrevAndNext(session, scienceApp, appType,
					status, orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByAppType_S_PrevAndNext(session, scienceApp, appType,
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

	protected ScienceApp getByAppType_S_PrevAndNext(Session session,
		ScienceApp scienceApp, String appType, int status,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		boolean bindAppType = false;

		if (appType == null) {
			query.append(_FINDER_COLUMN_APPTYPE_S_APPTYPE_1);
		}
		else if (appType.equals("")) {
			query.append(_FINDER_COLUMN_APPTYPE_S_APPTYPE_3);
		}
		else {
			bindAppType = true;

			query.append(_FINDER_COLUMN_APPTYPE_S_APPTYPE_2);
		}

		query.append(_FINDER_COLUMN_APPTYPE_S_STATUS_2);

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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindAppType) {
			qPos.add(appType);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where appType = &#63; and status = &#63; from the database.
	 *
	 * @param appType the app type
	 * @param status the status
	 */
	@Override
	public void removeByAppType_S(String appType, int status) {
		for (ScienceApp scienceApp : findByAppType_S(appType, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where appType = &#63; and status = &#63;.
	 *
	 * @param appType the app type
	 * @param status the status
	 * @return the number of matching science apps
	 */
	@Override
	public int countByAppType_S(String appType, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_APPTYPE_S;

		Object[] finderArgs = new Object[] { appType, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			boolean bindAppType = false;

			if (appType == null) {
				query.append(_FINDER_COLUMN_APPTYPE_S_APPTYPE_1);
			}
			else if (appType.equals("")) {
				query.append(_FINDER_COLUMN_APPTYPE_S_APPTYPE_3);
			}
			else {
				bindAppType = true;

				query.append(_FINDER_COLUMN_APPTYPE_S_APPTYPE_2);
			}

			query.append(_FINDER_COLUMN_APPTYPE_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAppType) {
					qPos.add(appType);
				}

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

	private static final String _FINDER_COLUMN_APPTYPE_S_APPTYPE_1 = "scienceApp.appType IS NULL AND ";
	private static final String _FINDER_COLUMN_APPTYPE_S_APPTYPE_2 = "scienceApp.appType = ? AND ";
	private static final String _FINDER_COLUMN_APPTYPE_S_APPTYPE_3 = "(scienceApp.appType IS NULL OR scienceApp.appType = '') AND ";
	private static final String _FINDER_COLUMN_APPTYPE_S_STATUS_2 = "scienceApp.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPTYPE_G_U =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAppType_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G_U =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAppType_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			ScienceAppModelImpl.GROUPID_COLUMN_BITMASK |
			ScienceAppModelImpl.USERID_COLUMN_BITMASK |
			ScienceAppModelImpl.APPTYPE_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPTYPE_G_U = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAppType_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the science apps where groupId = &#63; and userId = &#63; and appType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_G_U(long groupId, long userId,
		String appType) {
		return findByAppType_G_U(groupId, userId, appType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where groupId = &#63; and userId = &#63; and appType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_G_U(long groupId, long userId,
		String appType, int start, int end) {
		return findByAppType_G_U(groupId, userId, appType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and userId = &#63; and appType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_G_U(long groupId, long userId,
		String appType, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator) {
		return findByAppType_G_U(groupId, userId, appType, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and userId = &#63; and appType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_G_U(long groupId, long userId,
		String appType, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G_U;
			finderArgs = new Object[] { groupId, userId, appType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPTYPE_G_U;
			finderArgs = new Object[] {
					groupId, userId, appType,
					
					start, end, orderByComparator
				};
		}

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if ((groupId != scienceApp.getGroupId()) ||
							(userId != scienceApp.getUserId()) ||
							!Objects.equals(appType, scienceApp.getAppType())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_APPTYPE_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_APPTYPE_G_U_USERID_2);

			boolean bindAppType = false;

			if (appType == null) {
				query.append(_FINDER_COLUMN_APPTYPE_G_U_APPTYPE_1);
			}
			else if (appType.equals("")) {
				query.append(_FINDER_COLUMN_APPTYPE_G_U_APPTYPE_3);
			}
			else {
				bindAppType = true;

				query.append(_FINDER_COLUMN_APPTYPE_G_U_APPTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (bindAppType) {
					qPos.add(appType);
				}

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where groupId = &#63; and userId = &#63; and appType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByAppType_G_U_First(long groupId, long userId,
		String appType, OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByAppType_G_U_First(groupId, userId,
				appType, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", appType=");
		msg.append(appType);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where groupId = &#63; and userId = &#63; and appType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByAppType_G_U_First(long groupId, long userId,
		String appType, OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByAppType_G_U(groupId, userId, appType, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and userId = &#63; and appType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByAppType_G_U_Last(long groupId, long userId,
		String appType, OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByAppType_G_U_Last(groupId, userId,
				appType, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", appType=");
		msg.append(appType);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and userId = &#63; and appType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByAppType_G_U_Last(long groupId, long userId,
		String appType, OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByAppType_G_U(groupId, userId, appType);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByAppType_G_U(groupId, userId, appType,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where groupId = &#63; and userId = &#63; and appType = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByAppType_G_U_PrevAndNext(long scienceAppId,
		long groupId, long userId, String appType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByAppType_G_U_PrevAndNext(session, scienceApp,
					groupId, userId, appType, orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByAppType_G_U_PrevAndNext(session, scienceApp,
					groupId, userId, appType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScienceApp getByAppType_G_U_PrevAndNext(Session session,
		ScienceApp scienceApp, long groupId, long userId, String appType,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		query.append(_FINDER_COLUMN_APPTYPE_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_APPTYPE_G_U_USERID_2);

		boolean bindAppType = false;

		if (appType == null) {
			query.append(_FINDER_COLUMN_APPTYPE_G_U_APPTYPE_1);
		}
		else if (appType.equals("")) {
			query.append(_FINDER_COLUMN_APPTYPE_G_U_APPTYPE_3);
		}
		else {
			bindAppType = true;

			query.append(_FINDER_COLUMN_APPTYPE_G_U_APPTYPE_2);
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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (bindAppType) {
			qPos.add(appType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where groupId = &#63; and userId = &#63; and appType = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 */
	@Override
	public void removeByAppType_G_U(long groupId, long userId, String appType) {
		for (ScienceApp scienceApp : findByAppType_G_U(groupId, userId,
				appType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where groupId = &#63; and userId = &#63; and appType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @return the number of matching science apps
	 */
	@Override
	public int countByAppType_G_U(long groupId, long userId, String appType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_APPTYPE_G_U;

		Object[] finderArgs = new Object[] { groupId, userId, appType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_APPTYPE_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_APPTYPE_G_U_USERID_2);

			boolean bindAppType = false;

			if (appType == null) {
				query.append(_FINDER_COLUMN_APPTYPE_G_U_APPTYPE_1);
			}
			else if (appType.equals("")) {
				query.append(_FINDER_COLUMN_APPTYPE_G_U_APPTYPE_3);
			}
			else {
				bindAppType = true;

				query.append(_FINDER_COLUMN_APPTYPE_G_U_APPTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (bindAppType) {
					qPos.add(appType);
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

	private static final String _FINDER_COLUMN_APPTYPE_G_U_GROUPID_2 = "scienceApp.groupId = ? AND ";
	private static final String _FINDER_COLUMN_APPTYPE_G_U_USERID_2 = "scienceApp.userId = ? AND ";
	private static final String _FINDER_COLUMN_APPTYPE_G_U_APPTYPE_1 = "scienceApp.appType IS NULL";
	private static final String _FINDER_COLUMN_APPTYPE_G_U_APPTYPE_2 = "scienceApp.appType = ?";
	private static final String _FINDER_COLUMN_APPTYPE_G_U_APPTYPE_3 = "(scienceApp.appType IS NULL OR scienceApp.appType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPTYPE_G_U_S =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAppType_G_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G_U_S =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAppType_G_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName()
			},
			ScienceAppModelImpl.GROUPID_COLUMN_BITMASK |
			ScienceAppModelImpl.USERID_COLUMN_BITMASK |
			ScienceAppModelImpl.APPTYPE_COLUMN_BITMASK |
			ScienceAppModelImpl.STATUS_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPTYPE_G_U_S = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAppType_G_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the science apps where groupId = &#63; and userId = &#63; and appType = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param status the status
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_G_U_S(long groupId, long userId,
		String appType, int status) {
		return findByAppType_G_U_S(groupId, userId, appType, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where groupId = &#63; and userId = &#63; and appType = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_G_U_S(long groupId, long userId,
		String appType, int status, int start, int end) {
		return findByAppType_G_U_S(groupId, userId, appType, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and userId = &#63; and appType = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_G_U_S(long groupId, long userId,
		String appType, int status, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator) {
		return findByAppType_G_U_S(groupId, userId, appType, status, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and userId = &#63; and appType = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAppType_G_U_S(long groupId, long userId,
		String appType, int status, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G_U_S;
			finderArgs = new Object[] { groupId, userId, appType, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPTYPE_G_U_S;
			finderArgs = new Object[] {
					groupId, userId, appType, status,
					
					start, end, orderByComparator
				};
		}

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if ((groupId != scienceApp.getGroupId()) ||
							(userId != scienceApp.getUserId()) ||
							!Objects.equals(appType, scienceApp.getAppType()) ||
							(status != scienceApp.getStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_APPTYPE_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_APPTYPE_G_U_S_USERID_2);

			boolean bindAppType = false;

			if (appType == null) {
				query.append(_FINDER_COLUMN_APPTYPE_G_U_S_APPTYPE_1);
			}
			else if (appType.equals("")) {
				query.append(_FINDER_COLUMN_APPTYPE_G_U_S_APPTYPE_3);
			}
			else {
				bindAppType = true;

				query.append(_FINDER_COLUMN_APPTYPE_G_U_S_APPTYPE_2);
			}

			query.append(_FINDER_COLUMN_APPTYPE_G_U_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (bindAppType) {
					qPos.add(appType);
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where groupId = &#63; and userId = &#63; and appType = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByAppType_G_U_S_First(long groupId, long userId,
		String appType, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByAppType_G_U_S_First(groupId, userId,
				appType, status, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", appType=");
		msg.append(appType);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where groupId = &#63; and userId = &#63; and appType = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByAppType_G_U_S_First(long groupId, long userId,
		String appType, int status,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByAppType_G_U_S(groupId, userId, appType,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and userId = &#63; and appType = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByAppType_G_U_S_Last(long groupId, long userId,
		String appType, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByAppType_G_U_S_Last(groupId, userId,
				appType, status, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", appType=");
		msg.append(appType);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and userId = &#63; and appType = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByAppType_G_U_S_Last(long groupId, long userId,
		String appType, int status,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByAppType_G_U_S(groupId, userId, appType, status);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByAppType_G_U_S(groupId, userId, appType,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where groupId = &#63; and userId = &#63; and appType = &#63; and status = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByAppType_G_U_S_PrevAndNext(long scienceAppId,
		long groupId, long userId, String appType, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByAppType_G_U_S_PrevAndNext(session, scienceApp,
					groupId, userId, appType, status, orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByAppType_G_U_S_PrevAndNext(session, scienceApp,
					groupId, userId, appType, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScienceApp getByAppType_G_U_S_PrevAndNext(Session session,
		ScienceApp scienceApp, long groupId, long userId, String appType,
		int status, OrderByComparator<ScienceApp> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		query.append(_FINDER_COLUMN_APPTYPE_G_U_S_GROUPID_2);

		query.append(_FINDER_COLUMN_APPTYPE_G_U_S_USERID_2);

		boolean bindAppType = false;

		if (appType == null) {
			query.append(_FINDER_COLUMN_APPTYPE_G_U_S_APPTYPE_1);
		}
		else if (appType.equals("")) {
			query.append(_FINDER_COLUMN_APPTYPE_G_U_S_APPTYPE_3);
		}
		else {
			bindAppType = true;

			query.append(_FINDER_COLUMN_APPTYPE_G_U_S_APPTYPE_2);
		}

		query.append(_FINDER_COLUMN_APPTYPE_G_U_S_STATUS_2);

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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (bindAppType) {
			qPos.add(appType);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where groupId = &#63; and userId = &#63; and appType = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param status the status
	 */
	@Override
	public void removeByAppType_G_U_S(long groupId, long userId,
		String appType, int status) {
		for (ScienceApp scienceApp : findByAppType_G_U_S(groupId, userId,
				appType, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where groupId = &#63; and userId = &#63; and appType = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param appType the app type
	 * @param status the status
	 * @return the number of matching science apps
	 */
	@Override
	public int countByAppType_G_U_S(long groupId, long userId, String appType,
		int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_APPTYPE_G_U_S;

		Object[] finderArgs = new Object[] { groupId, userId, appType, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_APPTYPE_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_APPTYPE_G_U_S_USERID_2);

			boolean bindAppType = false;

			if (appType == null) {
				query.append(_FINDER_COLUMN_APPTYPE_G_U_S_APPTYPE_1);
			}
			else if (appType.equals("")) {
				query.append(_FINDER_COLUMN_APPTYPE_G_U_S_APPTYPE_3);
			}
			else {
				bindAppType = true;

				query.append(_FINDER_COLUMN_APPTYPE_G_U_S_APPTYPE_2);
			}

			query.append(_FINDER_COLUMN_APPTYPE_G_U_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (bindAppType) {
					qPos.add(appType);
				}

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

	private static final String _FINDER_COLUMN_APPTYPE_G_U_S_GROUPID_2 = "scienceApp.groupId = ? AND ";
	private static final String _FINDER_COLUMN_APPTYPE_G_U_S_USERID_2 = "scienceApp.userId = ? AND ";
	private static final String _FINDER_COLUMN_APPTYPE_G_U_S_APPTYPE_1 = "scienceApp.appType IS NULL AND ";
	private static final String _FINDER_COLUMN_APPTYPE_G_U_S_APPTYPE_2 = "scienceApp.appType = ? AND ";
	private static final String _FINDER_COLUMN_APPTYPE_G_U_S_APPTYPE_3 = "(scienceApp.appType IS NULL OR scienceApp.appType = '') AND ";
	private static final String _FINDER_COLUMN_APPTYPE_G_U_S_STATUS_2 = "scienceApp.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RUNTYPE = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRunType",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRunType",
			new String[] { String.class.getName() },
			ScienceAppModelImpl.RUNTYPE_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RUNTYPE = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRunType",
			new String[] { String.class.getName() });

	/**
	 * Returns all the science apps where runType = &#63;.
	 *
	 * @param runType the run type
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType(String runType) {
		return findByRunType(runType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param runType the run type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType(String runType, int start, int end) {
		return findByRunType(runType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param runType the run type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType(String runType, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator) {
		return findByRunType(runType, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param runType the run type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType(String runType, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE;
			finderArgs = new Object[] { runType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RUNTYPE;
			finderArgs = new Object[] { runType, start, end, orderByComparator };
		}

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if (!Objects.equals(runType, scienceApp.getRunType())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			boolean bindRunType = false;

			if (runType == null) {
				query.append(_FINDER_COLUMN_RUNTYPE_RUNTYPE_1);
			}
			else if (runType.equals("")) {
				query.append(_FINDER_COLUMN_RUNTYPE_RUNTYPE_3);
			}
			else {
				bindRunType = true;

				query.append(_FINDER_COLUMN_RUNTYPE_RUNTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRunType) {
					qPos.add(runType);
				}

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where runType = &#63;.
	 *
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByRunType_First(String runType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByRunType_First(runType, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("runType=");
		msg.append(runType);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where runType = &#63;.
	 *
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByRunType_First(String runType,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByRunType(runType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where runType = &#63;.
	 *
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByRunType_Last(String runType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByRunType_Last(runType, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("runType=");
		msg.append(runType);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where runType = &#63;.
	 *
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByRunType_Last(String runType,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByRunType(runType);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByRunType(runType, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where runType = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByRunType_PrevAndNext(long scienceAppId,
		String runType, OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByRunType_PrevAndNext(session, scienceApp, runType,
					orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByRunType_PrevAndNext(session, scienceApp, runType,
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

	protected ScienceApp getByRunType_PrevAndNext(Session session,
		ScienceApp scienceApp, String runType,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		boolean bindRunType = false;

		if (runType == null) {
			query.append(_FINDER_COLUMN_RUNTYPE_RUNTYPE_1);
		}
		else if (runType.equals("")) {
			query.append(_FINDER_COLUMN_RUNTYPE_RUNTYPE_3);
		}
		else {
			bindRunType = true;

			query.append(_FINDER_COLUMN_RUNTYPE_RUNTYPE_2);
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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindRunType) {
			qPos.add(runType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where runType = &#63; from the database.
	 *
	 * @param runType the run type
	 */
	@Override
	public void removeByRunType(String runType) {
		for (ScienceApp scienceApp : findByRunType(runType, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where runType = &#63;.
	 *
	 * @param runType the run type
	 * @return the number of matching science apps
	 */
	@Override
	public int countByRunType(String runType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RUNTYPE;

		Object[] finderArgs = new Object[] { runType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			boolean bindRunType = false;

			if (runType == null) {
				query.append(_FINDER_COLUMN_RUNTYPE_RUNTYPE_1);
			}
			else if (runType.equals("")) {
				query.append(_FINDER_COLUMN_RUNTYPE_RUNTYPE_3);
			}
			else {
				bindRunType = true;

				query.append(_FINDER_COLUMN_RUNTYPE_RUNTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRunType) {
					qPos.add(runType);
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

	private static final String _FINDER_COLUMN_RUNTYPE_RUNTYPE_1 = "scienceApp.runType IS NULL";
	private static final String _FINDER_COLUMN_RUNTYPE_RUNTYPE_2 = "scienceApp.runType = ?";
	private static final String _FINDER_COLUMN_RUNTYPE_RUNTYPE_3 = "(scienceApp.runType IS NULL OR scienceApp.runType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RUNTYPE_G =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRunType_G",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRunType_G",
			new String[] { Long.class.getName(), String.class.getName() },
			ScienceAppModelImpl.GROUPID_COLUMN_BITMASK |
			ScienceAppModelImpl.RUNTYPE_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RUNTYPE_G = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRunType_G",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the science apps where groupId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_G(long groupId, String runType) {
		return findByRunType_G(groupId, runType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where groupId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_G(long groupId, String runType,
		int start, int end) {
		return findByRunType_G(groupId, runType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_G(long groupId, String runType,
		int start, int end, OrderByComparator<ScienceApp> orderByComparator) {
		return findByRunType_G(groupId, runType, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_G(long groupId, String runType,
		int start, int end, OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G;
			finderArgs = new Object[] { groupId, runType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RUNTYPE_G;
			finderArgs = new Object[] {
					groupId, runType,
					
					start, end, orderByComparator
				};
		}

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if ((groupId != scienceApp.getGroupId()) ||
							!Objects.equals(runType, scienceApp.getRunType())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_RUNTYPE_G_GROUPID_2);

			boolean bindRunType = false;

			if (runType == null) {
				query.append(_FINDER_COLUMN_RUNTYPE_G_RUNTYPE_1);
			}
			else if (runType.equals("")) {
				query.append(_FINDER_COLUMN_RUNTYPE_G_RUNTYPE_3);
			}
			else {
				bindRunType = true;

				query.append(_FINDER_COLUMN_RUNTYPE_G_RUNTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindRunType) {
					qPos.add(runType);
				}

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where groupId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByRunType_G_First(long groupId, String runType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByRunType_G_First(groupId, runType,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", runType=");
		msg.append(runType);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where groupId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByRunType_G_First(long groupId, String runType,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByRunType_G(groupId, runType, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByRunType_G_Last(long groupId, String runType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByRunType_G_Last(groupId, runType,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", runType=");
		msg.append(runType);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByRunType_G_Last(long groupId, String runType,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByRunType_G(groupId, runType);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByRunType_G(groupId, runType, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where groupId = &#63; and runType = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param groupId the group ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByRunType_G_PrevAndNext(long scienceAppId,
		long groupId, String runType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByRunType_G_PrevAndNext(session, scienceApp, groupId,
					runType, orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByRunType_G_PrevAndNext(session, scienceApp, groupId,
					runType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScienceApp getByRunType_G_PrevAndNext(Session session,
		ScienceApp scienceApp, long groupId, String runType,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		query.append(_FINDER_COLUMN_RUNTYPE_G_GROUPID_2);

		boolean bindRunType = false;

		if (runType == null) {
			query.append(_FINDER_COLUMN_RUNTYPE_G_RUNTYPE_1);
		}
		else if (runType.equals("")) {
			query.append(_FINDER_COLUMN_RUNTYPE_G_RUNTYPE_3);
		}
		else {
			bindRunType = true;

			query.append(_FINDER_COLUMN_RUNTYPE_G_RUNTYPE_2);
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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindRunType) {
			qPos.add(runType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where groupId = &#63; and runType = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 */
	@Override
	public void removeByRunType_G(long groupId, String runType) {
		for (ScienceApp scienceApp : findByRunType_G(groupId, runType,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where groupId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @return the number of matching science apps
	 */
	@Override
	public int countByRunType_G(long groupId, String runType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RUNTYPE_G;

		Object[] finderArgs = new Object[] { groupId, runType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_RUNTYPE_G_GROUPID_2);

			boolean bindRunType = false;

			if (runType == null) {
				query.append(_FINDER_COLUMN_RUNTYPE_G_RUNTYPE_1);
			}
			else if (runType.equals("")) {
				query.append(_FINDER_COLUMN_RUNTYPE_G_RUNTYPE_3);
			}
			else {
				bindRunType = true;

				query.append(_FINDER_COLUMN_RUNTYPE_G_RUNTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindRunType) {
					qPos.add(runType);
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

	private static final String _FINDER_COLUMN_RUNTYPE_G_GROUPID_2 = "scienceApp.groupId = ? AND ";
	private static final String _FINDER_COLUMN_RUNTYPE_G_RUNTYPE_1 = "scienceApp.runType IS NULL";
	private static final String _FINDER_COLUMN_RUNTYPE_G_RUNTYPE_2 = "scienceApp.runType = ?";
	private static final String _FINDER_COLUMN_RUNTYPE_G_RUNTYPE_3 = "(scienceApp.runType IS NULL OR scienceApp.runType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RUNTYPE_U =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRunType_U",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_U =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRunType_U",
			new String[] { Long.class.getName(), String.class.getName() },
			ScienceAppModelImpl.USERID_COLUMN_BITMASK |
			ScienceAppModelImpl.RUNTYPE_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RUNTYPE_U = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRunType_U",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the science apps where userId = &#63; and runType = &#63;.
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_U(long userId, String runType) {
		return findByRunType_U(userId, runType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where userId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_U(long userId, String runType,
		int start, int end) {
		return findByRunType_U(userId, runType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where userId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_U(long userId, String runType,
		int start, int end, OrderByComparator<ScienceApp> orderByComparator) {
		return findByRunType_U(userId, runType, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the science apps where userId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_U(long userId, String runType,
		int start, int end, OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_U;
			finderArgs = new Object[] { userId, runType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RUNTYPE_U;
			finderArgs = new Object[] {
					userId, runType,
					
					start, end, orderByComparator
				};
		}

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if ((userId != scienceApp.getUserId()) ||
							!Objects.equals(runType, scienceApp.getRunType())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_RUNTYPE_U_USERID_2);

			boolean bindRunType = false;

			if (runType == null) {
				query.append(_FINDER_COLUMN_RUNTYPE_U_RUNTYPE_1);
			}
			else if (runType.equals("")) {
				query.append(_FINDER_COLUMN_RUNTYPE_U_RUNTYPE_3);
			}
			else {
				bindRunType = true;

				query.append(_FINDER_COLUMN_RUNTYPE_U_RUNTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindRunType) {
					qPos.add(runType);
				}

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where userId = &#63; and runType = &#63;.
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByRunType_U_First(long userId, String runType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByRunType_U_First(userId, runType,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", runType=");
		msg.append(runType);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where userId = &#63; and runType = &#63;.
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByRunType_U_First(long userId, String runType,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByRunType_U(userId, runType, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where userId = &#63; and runType = &#63;.
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByRunType_U_Last(long userId, String runType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByRunType_U_Last(userId, runType,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", runType=");
		msg.append(runType);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where userId = &#63; and runType = &#63;.
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByRunType_U_Last(long userId, String runType,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByRunType_U(userId, runType);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByRunType_U(userId, runType, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where userId = &#63; and runType = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByRunType_U_PrevAndNext(long scienceAppId,
		long userId, String runType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByRunType_U_PrevAndNext(session, scienceApp, userId,
					runType, orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByRunType_U_PrevAndNext(session, scienceApp, userId,
					runType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScienceApp getByRunType_U_PrevAndNext(Session session,
		ScienceApp scienceApp, long userId, String runType,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		query.append(_FINDER_COLUMN_RUNTYPE_U_USERID_2);

		boolean bindRunType = false;

		if (runType == null) {
			query.append(_FINDER_COLUMN_RUNTYPE_U_RUNTYPE_1);
		}
		else if (runType.equals("")) {
			query.append(_FINDER_COLUMN_RUNTYPE_U_RUNTYPE_3);
		}
		else {
			bindRunType = true;

			query.append(_FINDER_COLUMN_RUNTYPE_U_RUNTYPE_2);
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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (bindRunType) {
			qPos.add(runType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where userId = &#63; and runType = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 */
	@Override
	public void removeByRunType_U(long userId, String runType) {
		for (ScienceApp scienceApp : findByRunType_U(userId, runType,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where userId = &#63; and runType = &#63;.
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @return the number of matching science apps
	 */
	@Override
	public int countByRunType_U(long userId, String runType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RUNTYPE_U;

		Object[] finderArgs = new Object[] { userId, runType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_RUNTYPE_U_USERID_2);

			boolean bindRunType = false;

			if (runType == null) {
				query.append(_FINDER_COLUMN_RUNTYPE_U_RUNTYPE_1);
			}
			else if (runType.equals("")) {
				query.append(_FINDER_COLUMN_RUNTYPE_U_RUNTYPE_3);
			}
			else {
				bindRunType = true;

				query.append(_FINDER_COLUMN_RUNTYPE_U_RUNTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindRunType) {
					qPos.add(runType);
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

	private static final String _FINDER_COLUMN_RUNTYPE_U_USERID_2 = "scienceApp.userId = ? AND ";
	private static final String _FINDER_COLUMN_RUNTYPE_U_RUNTYPE_1 = "scienceApp.runType IS NULL";
	private static final String _FINDER_COLUMN_RUNTYPE_U_RUNTYPE_2 = "scienceApp.runType = ?";
	private static final String _FINDER_COLUMN_RUNTYPE_U_RUNTYPE_3 = "(scienceApp.runType IS NULL OR scienceApp.runType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RUNTYPE_S =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRunType_S",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_S =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRunType_S",
			new String[] { String.class.getName(), Integer.class.getName() },
			ScienceAppModelImpl.RUNTYPE_COLUMN_BITMASK |
			ScienceAppModelImpl.STATUS_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RUNTYPE_S = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRunType_S",
			new String[] { String.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the science apps where runType = &#63; and status = &#63;.
	 *
	 * @param runType the run type
	 * @param status the status
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_S(String runType, int status) {
		return findByRunType_S(runType, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where runType = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param runType the run type
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_S(String runType, int status,
		int start, int end) {
		return findByRunType_S(runType, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where runType = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param runType the run type
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_S(String runType, int status,
		int start, int end, OrderByComparator<ScienceApp> orderByComparator) {
		return findByRunType_S(runType, status, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the science apps where runType = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param runType the run type
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_S(String runType, int status,
		int start, int end, OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_S;
			finderArgs = new Object[] { runType, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RUNTYPE_S;
			finderArgs = new Object[] {
					runType, status,
					
					start, end, orderByComparator
				};
		}

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if (!Objects.equals(runType, scienceApp.getRunType()) ||
							(status != scienceApp.getStatus())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			boolean bindRunType = false;

			if (runType == null) {
				query.append(_FINDER_COLUMN_RUNTYPE_S_RUNTYPE_1);
			}
			else if (runType.equals("")) {
				query.append(_FINDER_COLUMN_RUNTYPE_S_RUNTYPE_3);
			}
			else {
				bindRunType = true;

				query.append(_FINDER_COLUMN_RUNTYPE_S_RUNTYPE_2);
			}

			query.append(_FINDER_COLUMN_RUNTYPE_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRunType) {
					qPos.add(runType);
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where runType = &#63; and status = &#63;.
	 *
	 * @param runType the run type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByRunType_S_First(String runType, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByRunType_S_First(runType, status,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("runType=");
		msg.append(runType);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where runType = &#63; and status = &#63;.
	 *
	 * @param runType the run type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByRunType_S_First(String runType, int status,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByRunType_S(runType, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where runType = &#63; and status = &#63;.
	 *
	 * @param runType the run type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByRunType_S_Last(String runType, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByRunType_S_Last(runType, status,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("runType=");
		msg.append(runType);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where runType = &#63; and status = &#63;.
	 *
	 * @param runType the run type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByRunType_S_Last(String runType, int status,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByRunType_S(runType, status);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByRunType_S(runType, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where runType = &#63; and status = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param runType the run type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByRunType_S_PrevAndNext(long scienceAppId,
		String runType, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByRunType_S_PrevAndNext(session, scienceApp, runType,
					status, orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByRunType_S_PrevAndNext(session, scienceApp, runType,
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

	protected ScienceApp getByRunType_S_PrevAndNext(Session session,
		ScienceApp scienceApp, String runType, int status,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		boolean bindRunType = false;

		if (runType == null) {
			query.append(_FINDER_COLUMN_RUNTYPE_S_RUNTYPE_1);
		}
		else if (runType.equals("")) {
			query.append(_FINDER_COLUMN_RUNTYPE_S_RUNTYPE_3);
		}
		else {
			bindRunType = true;

			query.append(_FINDER_COLUMN_RUNTYPE_S_RUNTYPE_2);
		}

		query.append(_FINDER_COLUMN_RUNTYPE_S_STATUS_2);

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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindRunType) {
			qPos.add(runType);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where runType = &#63; and status = &#63; from the database.
	 *
	 * @param runType the run type
	 * @param status the status
	 */
	@Override
	public void removeByRunType_S(String runType, int status) {
		for (ScienceApp scienceApp : findByRunType_S(runType, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where runType = &#63; and status = &#63;.
	 *
	 * @param runType the run type
	 * @param status the status
	 * @return the number of matching science apps
	 */
	@Override
	public int countByRunType_S(String runType, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RUNTYPE_S;

		Object[] finderArgs = new Object[] { runType, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			boolean bindRunType = false;

			if (runType == null) {
				query.append(_FINDER_COLUMN_RUNTYPE_S_RUNTYPE_1);
			}
			else if (runType.equals("")) {
				query.append(_FINDER_COLUMN_RUNTYPE_S_RUNTYPE_3);
			}
			else {
				bindRunType = true;

				query.append(_FINDER_COLUMN_RUNTYPE_S_RUNTYPE_2);
			}

			query.append(_FINDER_COLUMN_RUNTYPE_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRunType) {
					qPos.add(runType);
				}

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

	private static final String _FINDER_COLUMN_RUNTYPE_S_RUNTYPE_1 = "scienceApp.runType IS NULL AND ";
	private static final String _FINDER_COLUMN_RUNTYPE_S_RUNTYPE_2 = "scienceApp.runType = ? AND ";
	private static final String _FINDER_COLUMN_RUNTYPE_S_RUNTYPE_3 = "(scienceApp.runType IS NULL OR scienceApp.runType = '') AND ";
	private static final String _FINDER_COLUMN_RUNTYPE_S_STATUS_2 = "scienceApp.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RUNTYPE_G_U =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRunType_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G_U =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRunType_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			ScienceAppModelImpl.GROUPID_COLUMN_BITMASK |
			ScienceAppModelImpl.USERID_COLUMN_BITMASK |
			ScienceAppModelImpl.RUNTYPE_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RUNTYPE_G_U = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRunType_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the science apps where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_G_U(long groupId, long userId,
		String runType) {
		return findByRunType_G_U(groupId, userId, runType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_G_U(long groupId, long userId,
		String runType, int start, int end) {
		return findByRunType_G_U(groupId, userId, runType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_G_U(long groupId, long userId,
		String runType, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator) {
		return findByRunType_G_U(groupId, userId, runType, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_G_U(long groupId, long userId,
		String runType, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G_U;
			finderArgs = new Object[] { groupId, userId, runType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RUNTYPE_G_U;
			finderArgs = new Object[] {
					groupId, userId, runType,
					
					start, end, orderByComparator
				};
		}

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if ((groupId != scienceApp.getGroupId()) ||
							(userId != scienceApp.getUserId()) ||
							!Objects.equals(runType, scienceApp.getRunType())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_RUNTYPE_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_RUNTYPE_G_U_USERID_2);

			boolean bindRunType = false;

			if (runType == null) {
				query.append(_FINDER_COLUMN_RUNTYPE_G_U_RUNTYPE_1);
			}
			else if (runType.equals("")) {
				query.append(_FINDER_COLUMN_RUNTYPE_G_U_RUNTYPE_3);
			}
			else {
				bindRunType = true;

				query.append(_FINDER_COLUMN_RUNTYPE_G_U_RUNTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (bindRunType) {
					qPos.add(runType);
				}

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByRunType_G_U_First(long groupId, long userId,
		String runType, OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByRunType_G_U_First(groupId, userId,
				runType, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", runType=");
		msg.append(runType);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByRunType_G_U_First(long groupId, long userId,
		String runType, OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByRunType_G_U(groupId, userId, runType, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByRunType_G_U_Last(long groupId, long userId,
		String runType, OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByRunType_G_U_Last(groupId, userId,
				runType, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", runType=");
		msg.append(runType);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByRunType_G_U_Last(long groupId, long userId,
		String runType, OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByRunType_G_U(groupId, userId, runType);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByRunType_G_U(groupId, userId, runType,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByRunType_G_U_PrevAndNext(long scienceAppId,
		long groupId, long userId, String runType,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByRunType_G_U_PrevAndNext(session, scienceApp,
					groupId, userId, runType, orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByRunType_G_U_PrevAndNext(session, scienceApp,
					groupId, userId, runType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScienceApp getByRunType_G_U_PrevAndNext(Session session,
		ScienceApp scienceApp, long groupId, long userId, String runType,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		query.append(_FINDER_COLUMN_RUNTYPE_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_RUNTYPE_G_U_USERID_2);

		boolean bindRunType = false;

		if (runType == null) {
			query.append(_FINDER_COLUMN_RUNTYPE_G_U_RUNTYPE_1);
		}
		else if (runType.equals("")) {
			query.append(_FINDER_COLUMN_RUNTYPE_G_U_RUNTYPE_3);
		}
		else {
			bindRunType = true;

			query.append(_FINDER_COLUMN_RUNTYPE_G_U_RUNTYPE_2);
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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (bindRunType) {
			qPos.add(runType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where groupId = &#63; and userId = &#63; and runType = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 */
	@Override
	public void removeByRunType_G_U(long groupId, long userId, String runType) {
		for (ScienceApp scienceApp : findByRunType_G_U(groupId, userId,
				runType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @return the number of matching science apps
	 */
	@Override
	public int countByRunType_G_U(long groupId, long userId, String runType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RUNTYPE_G_U;

		Object[] finderArgs = new Object[] { groupId, userId, runType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_RUNTYPE_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_RUNTYPE_G_U_USERID_2);

			boolean bindRunType = false;

			if (runType == null) {
				query.append(_FINDER_COLUMN_RUNTYPE_G_U_RUNTYPE_1);
			}
			else if (runType.equals("")) {
				query.append(_FINDER_COLUMN_RUNTYPE_G_U_RUNTYPE_3);
			}
			else {
				bindRunType = true;

				query.append(_FINDER_COLUMN_RUNTYPE_G_U_RUNTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (bindRunType) {
					qPos.add(runType);
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

	private static final String _FINDER_COLUMN_RUNTYPE_G_U_GROUPID_2 = "scienceApp.groupId = ? AND ";
	private static final String _FINDER_COLUMN_RUNTYPE_G_U_USERID_2 = "scienceApp.userId = ? AND ";
	private static final String _FINDER_COLUMN_RUNTYPE_G_U_RUNTYPE_1 = "scienceApp.runType IS NULL";
	private static final String _FINDER_COLUMN_RUNTYPE_G_U_RUNTYPE_2 = "scienceApp.runType = ?";
	private static final String _FINDER_COLUMN_RUNTYPE_G_U_RUNTYPE_3 = "(scienceApp.runType IS NULL OR scienceApp.runType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RUNTYPE_G_U_S =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRunType_G_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G_U_S =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRunType_G_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName()
			},
			ScienceAppModelImpl.GROUPID_COLUMN_BITMASK |
			ScienceAppModelImpl.USERID_COLUMN_BITMASK |
			ScienceAppModelImpl.RUNTYPE_COLUMN_BITMASK |
			ScienceAppModelImpl.STATUS_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RUNTYPE_G_U_S = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRunType_G_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the science apps where groupId = &#63; and userId = &#63; and runType = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param status the status
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_G_U_S(long groupId, long userId,
		String runType, int status) {
		return findByRunType_G_U_S(groupId, userId, runType, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where groupId = &#63; and userId = &#63; and runType = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_G_U_S(long groupId, long userId,
		String runType, int status, int start, int end) {
		return findByRunType_G_U_S(groupId, userId, runType, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and userId = &#63; and runType = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_G_U_S(long groupId, long userId,
		String runType, int status, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator) {
		return findByRunType_G_U_S(groupId, userId, runType, status, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where groupId = &#63; and userId = &#63; and runType = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param status the status
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByRunType_G_U_S(long groupId, long userId,
		String runType, int status, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G_U_S;
			finderArgs = new Object[] { groupId, userId, runType, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RUNTYPE_G_U_S;
			finderArgs = new Object[] {
					groupId, userId, runType, status,
					
					start, end, orderByComparator
				};
		}

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if ((groupId != scienceApp.getGroupId()) ||
							(userId != scienceApp.getUserId()) ||
							!Objects.equals(runType, scienceApp.getRunType()) ||
							(status != scienceApp.getStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_USERID_2);

			boolean bindRunType = false;

			if (runType == null) {
				query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_RUNTYPE_1);
			}
			else if (runType.equals("")) {
				query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_RUNTYPE_3);
			}
			else {
				bindRunType = true;

				query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_RUNTYPE_2);
			}

			query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (bindRunType) {
					qPos.add(runType);
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where groupId = &#63; and userId = &#63; and runType = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByRunType_G_U_S_First(long groupId, long userId,
		String runType, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByRunType_G_U_S_First(groupId, userId,
				runType, status, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", runType=");
		msg.append(runType);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where groupId = &#63; and userId = &#63; and runType = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByRunType_G_U_S_First(long groupId, long userId,
		String runType, int status,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByRunType_G_U_S(groupId, userId, runType,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and userId = &#63; and runType = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByRunType_G_U_S_Last(long groupId, long userId,
		String runType, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByRunType_G_U_S_Last(groupId, userId,
				runType, status, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", runType=");
		msg.append(runType);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where groupId = &#63; and userId = &#63; and runType = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByRunType_G_U_S_Last(long groupId, long userId,
		String runType, int status,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByRunType_G_U_S(groupId, userId, runType, status);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByRunType_G_U_S(groupId, userId, runType,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where groupId = &#63; and userId = &#63; and runType = &#63; and status = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByRunType_G_U_S_PrevAndNext(long scienceAppId,
		long groupId, long userId, String runType, int status,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByRunType_G_U_S_PrevAndNext(session, scienceApp,
					groupId, userId, runType, status, orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByRunType_G_U_S_PrevAndNext(session, scienceApp,
					groupId, userId, runType, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScienceApp getByRunType_G_U_S_PrevAndNext(Session session,
		ScienceApp scienceApp, long groupId, long userId, String runType,
		int status, OrderByComparator<ScienceApp> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_GROUPID_2);

		query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_USERID_2);

		boolean bindRunType = false;

		if (runType == null) {
			query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_RUNTYPE_1);
		}
		else if (runType.equals("")) {
			query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_RUNTYPE_3);
		}
		else {
			bindRunType = true;

			query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_RUNTYPE_2);
		}

		query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_STATUS_2);

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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (bindRunType) {
			qPos.add(runType);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where groupId = &#63; and userId = &#63; and runType = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param status the status
	 */
	@Override
	public void removeByRunType_G_U_S(long groupId, long userId,
		String runType, int status) {
		for (ScienceApp scienceApp : findByRunType_G_U_S(groupId, userId,
				runType, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where groupId = &#63; and userId = &#63; and runType = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param status the status
	 * @return the number of matching science apps
	 */
	@Override
	public int countByRunType_G_U_S(long groupId, long userId, String runType,
		int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RUNTYPE_G_U_S;

		Object[] finderArgs = new Object[] { groupId, userId, runType, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_USERID_2);

			boolean bindRunType = false;

			if (runType == null) {
				query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_RUNTYPE_1);
			}
			else if (runType.equals("")) {
				query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_RUNTYPE_3);
			}
			else {
				bindRunType = true;

				query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_RUNTYPE_2);
			}

			query.append(_FINDER_COLUMN_RUNTYPE_G_U_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (bindRunType) {
					qPos.add(runType);
				}

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

	private static final String _FINDER_COLUMN_RUNTYPE_G_U_S_GROUPID_2 = "scienceApp.groupId = ? AND ";
	private static final String _FINDER_COLUMN_RUNTYPE_G_U_S_USERID_2 = "scienceApp.userId = ? AND ";
	private static final String _FINDER_COLUMN_RUNTYPE_G_U_S_RUNTYPE_1 = "scienceApp.runType IS NULL AND ";
	private static final String _FINDER_COLUMN_RUNTYPE_G_U_S_RUNTYPE_2 = "scienceApp.runType = ? AND ";
	private static final String _FINDER_COLUMN_RUNTYPE_G_U_S_RUNTYPE_3 = "(scienceApp.runType IS NULL OR scienceApp.runType = '') AND ";
	private static final String _FINDER_COLUMN_RUNTYPE_G_U_S_STATUS_2 = "scienceApp.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTitle",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTitle",
			new String[] { String.class.getName() });

	/**
	 * Returns all the science apps where title LIKE &#63;.
	 *
	 * @param title the title
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByTitle(String title) {
		return findByTitle(title, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByTitle(String title, int start, int end) {
		return findByTitle(title, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByTitle(String title, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator) {
		return findByTitle(title, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByTitle(String title, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE;
		finderArgs = new Object[] { title, start, end, orderByComparator };

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if (!StringUtil.wildcardMatches(scienceApp.getTitle(),
								title, '_', '%', '\\', true)) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_1);
			}
			else if (title.equals("")) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_TITLE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTitle) {
					qPos.add(title);
				}

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByTitle_First(String title,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByTitle_First(title, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByTitle_First(String title,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByTitle(title, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByTitle_Last(String title,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByTitle_Last(title, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByTitle_Last(String title,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByTitle(title);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByTitle(title, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where title LIKE &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByTitle_PrevAndNext(long scienceAppId,
		String title, OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByTitle_PrevAndNext(session, scienceApp, title,
					orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByTitle_PrevAndNext(session, scienceApp, title,
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

	protected ScienceApp getByTitle_PrevAndNext(Session session,
		ScienceApp scienceApp, String title,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		boolean bindTitle = false;

		if (title == null) {
			query.append(_FINDER_COLUMN_TITLE_TITLE_1);
		}
		else if (title.equals("")) {
			query.append(_FINDER_COLUMN_TITLE_TITLE_3);
		}
		else {
			bindTitle = true;

			query.append(_FINDER_COLUMN_TITLE_TITLE_2);
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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindTitle) {
			qPos.add(title);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where title LIKE &#63; from the database.
	 *
	 * @param title the title
	 */
	@Override
	public void removeByTitle(String title) {
		for (ScienceApp scienceApp : findByTitle(title, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where title LIKE &#63;.
	 *
	 * @param title the title
	 * @return the number of matching science apps
	 */
	@Override
	public int countByTitle(String title) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE;

		Object[] finderArgs = new Object[] { title };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_1);
			}
			else if (title.equals("")) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_TITLE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTitle) {
					qPos.add(title);
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

	private static final String _FINDER_COLUMN_TITLE_TITLE_1 = "scienceApp.title IS NULL";
	private static final String _FINDER_COLUMN_TITLE_TITLE_2 = "scienceApp.title LIKE ?";
	private static final String _FINDER_COLUMN_TITLE_TITLE_3 = "(scienceApp.title IS NULL OR scienceApp.title LIKE '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByName",
			new String[] { String.class.getName() },
			ScienceAppModelImpl.NAME_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the science apps where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByName(String name) {
		return findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByName(String name, int start, int end) {
		return findByName(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByName(String name, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator) {
		return findByName(name, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByName(String name, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator,
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

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if (!Objects.equals(name, scienceApp.getName())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

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
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
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
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByName_First(String name,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByName_First(name, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByName_First(String name,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByName_Last(String name,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByName_Last(name, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByName_Last(String name,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByName(name);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByName(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where name = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByName_PrevAndNext(long scienceAppId, String name,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByName_PrevAndNext(session, scienceApp, name,
					orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByName_PrevAndNext(session, scienceApp, name,
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

	protected ScienceApp getByName_PrevAndNext(Session session,
		ScienceApp scienceApp, String name,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where name = &#63; from the database.
	 *
	 * @param name the name
	 */
	@Override
	public void removeByName(String name) {
		for (ScienceApp scienceApp : findByName(name, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching science apps
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "scienceApp.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "scienceApp.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(scienceApp.name IS NULL OR scienceApp.name = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AUTHORID = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAuthorId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AUTHORID =
		new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAuthorId",
			new String[] { Long.class.getName() },
			ScienceAppModelImpl.AUTHORID_COLUMN_BITMASK |
			ScienceAppModelImpl.CREATEDATE_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AUTHORID = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAuthorId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the science apps where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @return the matching science apps
	 */
	@Override
	public List<ScienceApp> findByAuthorId(long authorId) {
		return findByAuthorId(authorId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the science apps where authorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param authorId the author ID
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAuthorId(long authorId, int start, int end) {
		return findByAuthorId(authorId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps where authorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param authorId the author ID
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAuthorId(long authorId, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator) {
		return findByAuthorId(authorId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps where authorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param authorId the author ID
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching science apps
	 */
	@Override
	public List<ScienceApp> findByAuthorId(long authorId, int start, int end,
		OrderByComparator<ScienceApp> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AUTHORID;
			finderArgs = new Object[] { authorId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AUTHORID;
			finderArgs = new Object[] { authorId, start, end, orderByComparator };
		}

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScienceApp scienceApp : list) {
					if ((authorId != scienceApp.getAuthorId())) {
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

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_AUTHORID_AUTHORID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(authorId);

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first science app in the ordered set where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByAuthorId_First(long authorId,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByAuthorId_First(authorId,
				orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("authorId=");
		msg.append(authorId);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the first science app in the ordered set where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByAuthorId_First(long authorId,
		OrderByComparator<ScienceApp> orderByComparator) {
		List<ScienceApp> list = findByAuthorId(authorId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last science app in the ordered set where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByAuthorId_Last(long authorId,
		OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByAuthorId_Last(authorId, orderByComparator);

		if (scienceApp != null) {
			return scienceApp;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("authorId=");
		msg.append(authorId);

		msg.append("}");

		throw new NoSuchScienceAppException(msg.toString());
	}

	/**
	 * Returns the last science app in the ordered set where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByAuthorId_Last(long authorId,
		OrderByComparator<ScienceApp> orderByComparator) {
		int count = countByAuthorId(authorId);

		if (count == 0) {
			return null;
		}

		List<ScienceApp> list = findByAuthorId(authorId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the science apps before and after the current science app in the ordered set where authorId = &#63;.
	 *
	 * @param scienceAppId the primary key of the current science app
	 * @param authorId the author ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp[] findByAuthorId_PrevAndNext(long scienceAppId,
		long authorId, OrderByComparator<ScienceApp> orderByComparator)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByPrimaryKey(scienceAppId);

		Session session = null;

		try {
			session = openSession();

			ScienceApp[] array = new ScienceAppImpl[3];

			array[0] = getByAuthorId_PrevAndNext(session, scienceApp, authorId,
					orderByComparator, true);

			array[1] = scienceApp;

			array[2] = getByAuthorId_PrevAndNext(session, scienceApp, authorId,
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

	protected ScienceApp getByAuthorId_PrevAndNext(Session session,
		ScienceApp scienceApp, long authorId,
		OrderByComparator<ScienceApp> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

		query.append(_FINDER_COLUMN_AUTHORID_AUTHORID_2);

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
			query.append(ScienceAppModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(authorId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scienceApp);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScienceApp> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the science apps where authorId = &#63; from the database.
	 *
	 * @param authorId the author ID
	 */
	@Override
	public void removeByAuthorId(long authorId) {
		for (ScienceApp scienceApp : findByAuthorId(authorId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @return the number of matching science apps
	 */
	@Override
	public int countByAuthorId(long authorId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AUTHORID;

		Object[] finderArgs = new Object[] { authorId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

			query.append(_FINDER_COLUMN_AUTHORID_AUTHORID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(authorId);

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

	private static final String _FINDER_COLUMN_AUTHORID_AUTHORID_2 = "scienceApp.authorId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_NAMEVERSION = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, ScienceAppImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByNameVersion",
			new String[] { String.class.getName(), String.class.getName() },
			ScienceAppModelImpl.NAME_COLUMN_BITMASK |
			ScienceAppModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAMEVERSION = new FinderPath(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByNameVersion",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns the science app where name = &#63; and version = &#63; or throws a {@link NoSuchScienceAppException} if it could not be found.
	 *
	 * @param name the name
	 * @param version the version
	 * @return the matching science app
	 * @throws NoSuchScienceAppException if a matching science app could not be found
	 */
	@Override
	public ScienceApp findByNameVersion(String name, String version)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByNameVersion(name, version);

		if (scienceApp == null) {
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

			throw new NoSuchScienceAppException(msg.toString());
		}

		return scienceApp;
	}

	/**
	 * Returns the science app where name = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @param version the version
	 * @return the matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByNameVersion(String name, String version) {
		return fetchByNameVersion(name, version, true);
	}

	/**
	 * Returns the science app where name = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param version the version
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching science app, or <code>null</code> if a matching science app could not be found
	 */
	@Override
	public ScienceApp fetchByNameVersion(String name, String version,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { name, version };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_NAMEVERSION,
					finderArgs, this);
		}

		if (result instanceof ScienceApp) {
			ScienceApp scienceApp = (ScienceApp)result;

			if (!Objects.equals(name, scienceApp.getName()) ||
					!Objects.equals(version, scienceApp.getVersion())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SCIENCEAPP_WHERE);

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

				List<ScienceApp> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_NAMEVERSION,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ScienceAppPersistenceImpl.fetchByNameVersion(String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ScienceApp scienceApp = list.get(0);

					result = scienceApp;

					cacheResult(scienceApp);
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
			return (ScienceApp)result;
		}
	}

	/**
	 * Removes the science app where name = &#63; and version = &#63; from the database.
	 *
	 * @param name the name
	 * @param version the version
	 * @return the science app that was removed
	 */
	@Override
	public ScienceApp removeByNameVersion(String name, String version)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = findByNameVersion(name, version);

		return remove(scienceApp);
	}

	/**
	 * Returns the number of science apps where name = &#63; and version = &#63;.
	 *
	 * @param name the name
	 * @param version the version
	 * @return the number of matching science apps
	 */
	@Override
	public int countByNameVersion(String name, String version) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAMEVERSION;

		Object[] finderArgs = new Object[] { name, version };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCIENCEAPP_WHERE);

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

	private static final String _FINDER_COLUMN_NAMEVERSION_NAME_1 = "scienceApp.name IS NULL AND ";
	private static final String _FINDER_COLUMN_NAMEVERSION_NAME_2 = "scienceApp.name = ? AND ";
	private static final String _FINDER_COLUMN_NAMEVERSION_NAME_3 = "(scienceApp.name IS NULL OR scienceApp.name = '') AND ";
	private static final String _FINDER_COLUMN_NAMEVERSION_VERSION_1 = "scienceApp.version IS NULL";
	private static final String _FINDER_COLUMN_NAMEVERSION_VERSION_2 = "scienceApp.version = ?";
	private static final String _FINDER_COLUMN_NAMEVERSION_VERSION_3 = "(scienceApp.version IS NULL OR scienceApp.version = '')";

	public ScienceAppPersistenceImpl() {
		setModelClass(ScienceApp.class);

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
	 * Caches the science app in the entity cache if it is enabled.
	 *
	 * @param scienceApp the science app
	 */
	@Override
	public void cacheResult(ScienceApp scienceApp) {
		entityCache.putResult(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppImpl.class, scienceApp.getPrimaryKey(), scienceApp);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { scienceApp.getUuid(), scienceApp.getGroupId() },
			scienceApp);

		finderCache.putResult(FINDER_PATH_FETCH_BY_NAMEVERSION,
			new Object[] { scienceApp.getName(), scienceApp.getVersion() },
			scienceApp);

		scienceApp.resetOriginalValues();
	}

	/**
	 * Caches the science apps in the entity cache if it is enabled.
	 *
	 * @param scienceApps the science apps
	 */
	@Override
	public void cacheResult(List<ScienceApp> scienceApps) {
		for (ScienceApp scienceApp : scienceApps) {
			if (entityCache.getResult(
						ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
						ScienceAppImpl.class, scienceApp.getPrimaryKey()) == null) {
				cacheResult(scienceApp);
			}
			else {
				scienceApp.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all science apps.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ScienceAppImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the science app.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ScienceApp scienceApp) {
		entityCache.removeResult(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppImpl.class, scienceApp.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ScienceAppModelImpl)scienceApp, true);
	}

	@Override
	public void clearCache(List<ScienceApp> scienceApps) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ScienceApp scienceApp : scienceApps) {
			entityCache.removeResult(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
				ScienceAppImpl.class, scienceApp.getPrimaryKey());

			clearUniqueFindersCache((ScienceAppModelImpl)scienceApp, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ScienceAppModelImpl scienceAppModelImpl) {
		Object[] args = new Object[] {
				scienceAppModelImpl.getUuid(), scienceAppModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			scienceAppModelImpl, false);

		args = new Object[] {
				scienceAppModelImpl.getName(), scienceAppModelImpl.getVersion()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_NAMEVERSION, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_NAMEVERSION, args,
			scienceAppModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ScienceAppModelImpl scienceAppModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					scienceAppModelImpl.getUuid(),
					scienceAppModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((scienceAppModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					scienceAppModelImpl.getOriginalUuid(),
					scienceAppModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					scienceAppModelImpl.getName(),
					scienceAppModelImpl.getVersion()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAMEVERSION, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAMEVERSION, args);
		}

		if ((scienceAppModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAMEVERSION.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					scienceAppModelImpl.getOriginalName(),
					scienceAppModelImpl.getOriginalVersion()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAMEVERSION, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAMEVERSION, args);
		}
	}

	/**
	 * Creates a new science app with the primary key. Does not add the science app to the database.
	 *
	 * @param scienceAppId the primary key for the new science app
	 * @return the new science app
	 */
	@Override
	public ScienceApp create(long scienceAppId) {
		ScienceApp scienceApp = new ScienceAppImpl();

		scienceApp.setNew(true);
		scienceApp.setPrimaryKey(scienceAppId);

		String uuid = PortalUUIDUtil.generate();

		scienceApp.setUuid(uuid);

		scienceApp.setCompanyId(companyProvider.getCompanyId());

		return scienceApp;
	}

	/**
	 * Removes the science app with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scienceAppId the primary key of the science app
	 * @return the science app that was removed
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp remove(long scienceAppId)
		throws NoSuchScienceAppException {
		return remove((Serializable)scienceAppId);
	}

	/**
	 * Removes the science app with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the science app
	 * @return the science app that was removed
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp remove(Serializable primaryKey)
		throws NoSuchScienceAppException {
		Session session = null;

		try {
			session = openSession();

			ScienceApp scienceApp = (ScienceApp)session.get(ScienceAppImpl.class,
					primaryKey);

			if (scienceApp == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchScienceAppException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(scienceApp);
		}
		catch (NoSuchScienceAppException nsee) {
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
	protected ScienceApp removeImpl(ScienceApp scienceApp) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(scienceApp)) {
				scienceApp = (ScienceApp)session.get(ScienceAppImpl.class,
						scienceApp.getPrimaryKeyObj());
			}

			if (scienceApp != null) {
				session.delete(scienceApp);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (scienceApp != null) {
			clearCache(scienceApp);
		}

		return scienceApp;
	}

	@Override
	public ScienceApp updateImpl(ScienceApp scienceApp) {
		boolean isNew = scienceApp.isNew();

		if (!(scienceApp instanceof ScienceAppModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(scienceApp.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(scienceApp);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in scienceApp proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ScienceApp implementation " +
				scienceApp.getClass());
		}

		ScienceAppModelImpl scienceAppModelImpl = (ScienceAppModelImpl)scienceApp;

		if (Validator.isNull(scienceApp.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			scienceApp.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (scienceApp.getCreateDate() == null)) {
			if (serviceContext == null) {
				scienceApp.setCreateDate(now);
			}
			else {
				scienceApp.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!scienceAppModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				scienceApp.setModifiedDate(now);
			}
			else {
				scienceApp.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (scienceApp.isNew()) {
				session.save(scienceApp);

				scienceApp.setNew(false);
			}
			else {
				scienceApp = (ScienceApp)session.merge(scienceApp);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ScienceAppModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { scienceAppModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					scienceAppModelImpl.getUuid(),
					scienceAppModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { scienceAppModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] { scienceAppModelImpl.getUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
				args);

			args = new Object[] { scienceAppModelImpl.getStatus() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
				args);

			args = new Object[] {
					scienceAppModelImpl.getGroupId(),
					scienceAppModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
				args);

			args = new Object[] {
					scienceAppModelImpl.getGroupId(),
					scienceAppModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
				args);

			args = new Object[] {
					scienceAppModelImpl.getUserId(),
					scienceAppModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_S,
				args);

			args = new Object[] {
					scienceAppModelImpl.getGroupId(),
					scienceAppModelImpl.getUserId(),
					scienceAppModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S,
				args);

			args = new Object[] { scienceAppModelImpl.getAppType() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE,
				args);

			args = new Object[] {
					scienceAppModelImpl.getGroupId(),
					scienceAppModelImpl.getAppType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE_G, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G,
				args);

			args = new Object[] {
					scienceAppModelImpl.getUserId(),
					scienceAppModelImpl.getAppType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE_U, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_U,
				args);

			args = new Object[] {
					scienceAppModelImpl.getAppType(),
					scienceAppModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_S,
				args);

			args = new Object[] {
					scienceAppModelImpl.getGroupId(),
					scienceAppModelImpl.getUserId(),
					scienceAppModelImpl.getAppType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE_G_U, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G_U,
				args);

			args = new Object[] {
					scienceAppModelImpl.getGroupId(),
					scienceAppModelImpl.getUserId(),
					scienceAppModelImpl.getAppType(),
					scienceAppModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE_G_U_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G_U_S,
				args);

			args = new Object[] { scienceAppModelImpl.getRunType() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE,
				args);

			args = new Object[] {
					scienceAppModelImpl.getGroupId(),
					scienceAppModelImpl.getRunType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_G, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G,
				args);

			args = new Object[] {
					scienceAppModelImpl.getUserId(),
					scienceAppModelImpl.getRunType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_U, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_U,
				args);

			args = new Object[] {
					scienceAppModelImpl.getRunType(),
					scienceAppModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_S,
				args);

			args = new Object[] {
					scienceAppModelImpl.getGroupId(),
					scienceAppModelImpl.getUserId(),
					scienceAppModelImpl.getRunType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_G_U, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G_U,
				args);

			args = new Object[] {
					scienceAppModelImpl.getGroupId(),
					scienceAppModelImpl.getUserId(),
					scienceAppModelImpl.getRunType(),
					scienceAppModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_G_U_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G_U_S,
				args);

			args = new Object[] { scienceAppModelImpl.getName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
				args);

			args = new Object[] { scienceAppModelImpl.getAuthorId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AUTHORID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AUTHORID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { scienceAppModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalUuid(),
						scienceAppModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						scienceAppModelImpl.getUuid(),
						scienceAppModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { scienceAppModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { scienceAppModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);

				args = new Object[] { scienceAppModelImpl.getStatus() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalGroupId(),
						scienceAppModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
					args);

				args = new Object[] {
						scienceAppModelImpl.getGroupId(),
						scienceAppModelImpl.getUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalGroupId(),
						scienceAppModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
					args);

				args = new Object[] {
						scienceAppModelImpl.getGroupId(),
						scienceAppModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalUserId(),
						scienceAppModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_S,
					args);

				args = new Object[] {
						scienceAppModelImpl.getUserId(),
						scienceAppModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_S,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalGroupId(),
						scienceAppModelImpl.getOriginalUserId(),
						scienceAppModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S,
					args);

				args = new Object[] {
						scienceAppModelImpl.getGroupId(),
						scienceAppModelImpl.getUserId(),
						scienceAppModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalAppType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE,
					args);

				args = new Object[] { scienceAppModelImpl.getAppType() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalGroupId(),
						scienceAppModelImpl.getOriginalAppType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G,
					args);

				args = new Object[] {
						scienceAppModelImpl.getGroupId(),
						scienceAppModelImpl.getAppType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalUserId(),
						scienceAppModelImpl.getOriginalAppType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_U,
					args);

				args = new Object[] {
						scienceAppModelImpl.getUserId(),
						scienceAppModelImpl.getAppType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_U,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalAppType(),
						scienceAppModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_S,
					args);

				args = new Object[] {
						scienceAppModelImpl.getAppType(),
						scienceAppModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_S,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalGroupId(),
						scienceAppModelImpl.getOriginalUserId(),
						scienceAppModelImpl.getOriginalAppType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE_G_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G_U,
					args);

				args = new Object[] {
						scienceAppModelImpl.getGroupId(),
						scienceAppModelImpl.getUserId(),
						scienceAppModelImpl.getAppType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE_G_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G_U,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G_U_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalGroupId(),
						scienceAppModelImpl.getOriginalUserId(),
						scienceAppModelImpl.getOriginalAppType(),
						scienceAppModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE_G_U_S,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G_U_S,
					args);

				args = new Object[] {
						scienceAppModelImpl.getGroupId(),
						scienceAppModelImpl.getUserId(),
						scienceAppModelImpl.getAppType(),
						scienceAppModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_APPTYPE_G_U_S,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPTYPE_G_U_S,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalRunType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE,
					args);

				args = new Object[] { scienceAppModelImpl.getRunType() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalGroupId(),
						scienceAppModelImpl.getOriginalRunType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G,
					args);

				args = new Object[] {
						scienceAppModelImpl.getGroupId(),
						scienceAppModelImpl.getRunType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalUserId(),
						scienceAppModelImpl.getOriginalRunType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_U,
					args);

				args = new Object[] {
						scienceAppModelImpl.getUserId(),
						scienceAppModelImpl.getRunType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_U,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalRunType(),
						scienceAppModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_S,
					args);

				args = new Object[] {
						scienceAppModelImpl.getRunType(),
						scienceAppModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_S,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalGroupId(),
						scienceAppModelImpl.getOriginalUserId(),
						scienceAppModelImpl.getOriginalRunType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_G_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G_U,
					args);

				args = new Object[] {
						scienceAppModelImpl.getGroupId(),
						scienceAppModelImpl.getUserId(),
						scienceAppModelImpl.getRunType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_G_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G_U,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G_U_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalGroupId(),
						scienceAppModelImpl.getOriginalUserId(),
						scienceAppModelImpl.getOriginalRunType(),
						scienceAppModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_G_U_S,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G_U_S,
					args);

				args = new Object[] {
						scienceAppModelImpl.getGroupId(),
						scienceAppModelImpl.getUserId(),
						scienceAppModelImpl.getRunType(),
						scienceAppModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_G_U_S,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G_U_S,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);

				args = new Object[] { scienceAppModelImpl.getName() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);
			}

			if ((scienceAppModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AUTHORID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scienceAppModelImpl.getOriginalAuthorId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AUTHORID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AUTHORID,
					args);

				args = new Object[] { scienceAppModelImpl.getAuthorId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AUTHORID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AUTHORID,
					args);
			}
		}

		entityCache.putResult(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
			ScienceAppImpl.class, scienceApp.getPrimaryKey(), scienceApp, false);

		clearUniqueFindersCache(scienceAppModelImpl, false);
		cacheUniqueFindersCache(scienceAppModelImpl);

		scienceApp.resetOriginalValues();

		return scienceApp;
	}

	/**
	 * Returns the science app with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the science app
	 * @return the science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp findByPrimaryKey(Serializable primaryKey)
		throws NoSuchScienceAppException {
		ScienceApp scienceApp = fetchByPrimaryKey(primaryKey);

		if (scienceApp == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchScienceAppException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return scienceApp;
	}

	/**
	 * Returns the science app with the primary key or throws a {@link NoSuchScienceAppException} if it could not be found.
	 *
	 * @param scienceAppId the primary key of the science app
	 * @return the science app
	 * @throws NoSuchScienceAppException if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp findByPrimaryKey(long scienceAppId)
		throws NoSuchScienceAppException {
		return findByPrimaryKey((Serializable)scienceAppId);
	}

	/**
	 * Returns the science app with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the science app
	 * @return the science app, or <code>null</code> if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
				ScienceAppImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ScienceApp scienceApp = (ScienceApp)serializable;

		if (scienceApp == null) {
			Session session = null;

			try {
				session = openSession();

				scienceApp = (ScienceApp)session.get(ScienceAppImpl.class,
						primaryKey);

				if (scienceApp != null) {
					cacheResult(scienceApp);
				}
				else {
					entityCache.putResult(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
						ScienceAppImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
					ScienceAppImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return scienceApp;
	}

	/**
	 * Returns the science app with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param scienceAppId the primary key of the science app
	 * @return the science app, or <code>null</code> if a science app with the primary key could not be found
	 */
	@Override
	public ScienceApp fetchByPrimaryKey(long scienceAppId) {
		return fetchByPrimaryKey((Serializable)scienceAppId);
	}

	@Override
	public Map<Serializable, ScienceApp> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ScienceApp> map = new HashMap<Serializable, ScienceApp>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ScienceApp scienceApp = fetchByPrimaryKey(primaryKey);

			if (scienceApp != null) {
				map.put(primaryKey, scienceApp);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
					ScienceAppImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ScienceApp)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SCIENCEAPP_WHERE_PKS_IN);

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

			for (ScienceApp scienceApp : (List<ScienceApp>)q.list()) {
				map.put(scienceApp.getPrimaryKeyObj(), scienceApp);

				cacheResult(scienceApp);

				uncachedPrimaryKeys.remove(scienceApp.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ScienceAppModelImpl.ENTITY_CACHE_ENABLED,
					ScienceAppImpl.class, primaryKey, nullModel);
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
	 * Returns all the science apps.
	 *
	 * @return the science apps
	 */
	@Override
	public List<ScienceApp> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the science apps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @return the range of science apps
	 */
	@Override
	public List<ScienceApp> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the science apps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of science apps
	 */
	@Override
	public List<ScienceApp> findAll(int start, int end,
		OrderByComparator<ScienceApp> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the science apps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScienceAppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of science apps
	 * @param end the upper bound of the range of science apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of science apps
	 */
	@Override
	public List<ScienceApp> findAll(int start, int end,
		OrderByComparator<ScienceApp> orderByComparator,
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

		List<ScienceApp> list = null;

		if (retrieveFromCache) {
			list = (List<ScienceApp>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SCIENCEAPP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SCIENCEAPP;

				if (pagination) {
					sql = sql.concat(ScienceAppModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScienceApp>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the science apps from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ScienceApp scienceApp : findAll()) {
			remove(scienceApp);
		}
	}

	/**
	 * Returns the number of science apps.
	 *
	 * @return the number of science apps
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SCIENCEAPP);

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
		return ScienceAppModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the science app persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ScienceAppImpl.class.getName());
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
	private static final String _SQL_SELECT_SCIENCEAPP = "SELECT scienceApp FROM ScienceApp scienceApp";
	private static final String _SQL_SELECT_SCIENCEAPP_WHERE_PKS_IN = "SELECT scienceApp FROM ScienceApp scienceApp WHERE scienceAppId IN (";
	private static final String _SQL_SELECT_SCIENCEAPP_WHERE = "SELECT scienceApp FROM ScienceApp scienceApp WHERE ";
	private static final String _SQL_COUNT_SCIENCEAPP = "SELECT COUNT(scienceApp) FROM ScienceApp scienceApp";
	private static final String _SQL_COUNT_SCIENCEAPP_WHERE = "SELECT COUNT(scienceApp) FROM ScienceApp scienceApp WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "scienceApp.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ScienceApp exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ScienceApp exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ScienceAppPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}