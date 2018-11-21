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

import com.kisti.osp.icebreaker.exception.NoSuchJobException;
import com.kisti.osp.icebreaker.model.Job;
import com.kisti.osp.icebreaker.model.impl.JobImpl;
import com.kisti.osp.icebreaker.model.impl.JobModelImpl;
import com.kisti.osp.icebreaker.service.persistence.JobPersistence;

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
 * The persistence implementation for the job service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jerry H. Seo
 * @see JobPersistence
 * @see com.kisti.osp.icebreaker.service.persistence.JobUtil
 * @generated
 */
@ProviderType
public class JobPersistenceImpl extends BasePersistenceImpl<Job>
	implements JobPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link JobUtil} to access the job persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = JobImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			JobModelImpl.UUID_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the jobs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByUuid(String uuid, int start, int end,
		OrderByComparator<Job> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByUuid(String uuid, int start, int end,
		OrderByComparator<Job> orderByComparator, boolean retrieveFromCache) {
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

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if (!Objects.equals(uuid, job.getUuid())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

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
				query.append(JobModelImpl.ORDER_BY_JPQL);
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
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByUuid_First(String uuid,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByUuid_First(uuid, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByUuid_First(String uuid,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByUuid_Last(String uuid,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByUuid_Last(uuid, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByUuid_Last(String uuid,
		OrderByComparator<Job> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where uuid = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByUuid_PrevAndNext(long jobId, String uuid,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByUuid_PrevAndNext(session, job, uuid,
					orderByComparator, true);

			array[1] = job;

			array[2] = getByUuid_PrevAndNext(session, job, uuid,
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

	protected Job getByUuid_PrevAndNext(Session session, Job job, String uuid,
		OrderByComparator<Job> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Job job : findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching jobs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JOB_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "job.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "job.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(job.uuid IS NULL OR job.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			JobModelImpl.UUID_COLUMN_BITMASK |
			JobModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the job where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchJobException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByUUID_G(String uuid, long groupId)
		throws NoSuchJobException {
		Job job = fetchByUUID_G(uuid, groupId);

		if (job == null) {
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

			throw new NoSuchJobException(msg.toString());
		}

		return job;
	}

	/**
	 * Returns the job where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the job where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Job) {
			Job job = (Job)result;

			if (!Objects.equals(uuid, job.getUuid()) ||
					(groupId != job.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_JOB_WHERE);

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

				List<Job> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Job job = list.get(0);

					result = job;

					cacheResult(job);
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
			return (Job)result;
		}
	}

	/**
	 * Removes the job where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the job that was removed
	 */
	@Override
	public Job removeByUUID_G(String uuid, long groupId)
		throws NoSuchJobException {
		Job job = findByUUID_G(uuid, groupId);

		return remove(job);
	}

	/**
	 * Returns the number of jobs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching jobs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOB_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "job.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "job.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(job.uuid IS NULL OR job.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "job.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			JobModelImpl.UUID_COLUMN_BITMASK |
			JobModelImpl.COMPANYID_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the jobs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Job> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Job> orderByComparator,
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

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if (!Objects.equals(uuid, job.getUuid()) ||
							(companyId != job.getCompanyId())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

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
				query.append(JobModelImpl.ORDER_BY_JPQL);
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
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByUuid_C(uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Job> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByUuid_C_PrevAndNext(long jobId, String uuid,
		long companyId, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, job, uuid, companyId,
					orderByComparator, true);

			array[1] = job;

			array[2] = getByUuid_C_PrevAndNext(session, job, uuid, companyId,
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

	protected Job getByUuid_C_PrevAndNext(Session session, Job job,
		String uuid, long companyId, OrderByComparator<Job> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Job job : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching jobs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOB_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "job.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "job.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(job.uuid IS NULL OR job.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "job.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			JobModelImpl.GROUPID_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the jobs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Job> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Job> orderByComparator, boolean retrieveFromCache) {
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

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((groupId != job.getGroupId())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByGroupId_First(long groupId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByGroupId_First(groupId, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByGroupId_First(long groupId,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByGroupId_Last(long groupId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByGroupId_Last(groupId, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByGroupId_Last(long groupId,
		OrderByComparator<Job> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where groupId = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByGroupId_PrevAndNext(long jobId, long groupId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, job, groupId,
					orderByComparator, true);

			array[1] = job;

			array[2] = getByGroupId_PrevAndNext(session, job, groupId,
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

	protected Job getByGroupId_PrevAndNext(Session session, Job job,
		long groupId, OrderByComparator<Job> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (Job job : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching jobs
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JOB_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "job.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			JobModelImpl.USERID_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the jobs where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByUserId(long userId, int start, int end,
		OrderByComparator<Job> orderByComparator) {
		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByUserId(long userId, int start, int end,
		OrderByComparator<Job> orderByComparator, boolean retrieveFromCache) {
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

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((userId != job.getUserId())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByUserId_First(long userId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByUserId_First(userId, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByUserId_First(long userId,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByUserId_Last(long userId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByUserId_Last(userId, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByUserId_Last(long userId,
		OrderByComparator<Job> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where userId = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByUserId_PrevAndNext(long jobId, long userId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByUserId_PrevAndNext(session, job, userId,
					orderByComparator, true);

			array[1] = job;

			array[2] = getByUserId_PrevAndNext(session, job, userId,
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

	protected Job getByUserId_PrevAndNext(Session session, Job job,
		long userId, OrderByComparator<Job> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (Job job : findByUserId(userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching jobs
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JOB_WHERE);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "job.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID_G = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId_G",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_G =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId_G",
			new String[] { Long.class.getName(), Long.class.getName() },
			JobModelImpl.GROUPID_COLUMN_BITMASK |
			JobModelImpl.USERID_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID_G = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId_G",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the jobs where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByUserId_G(long groupId, long userId) {
		return findByUserId_G(groupId, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByUserId_G(long groupId, long userId, int start,
		int end) {
		return findByUserId_G(groupId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByUserId_G(long groupId, long userId, int start,
		int end, OrderByComparator<Job> orderByComparator) {
		return findByUserId_G(groupId, userId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByUserId_G(long groupId, long userId, int start,
		int end, OrderByComparator<Job> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_G;
			finderArgs = new Object[] { groupId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID_G;
			finderArgs = new Object[] {
					groupId, userId,
					
					start, end, orderByComparator
				};
		}

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((groupId != job.getGroupId()) ||
							(userId != job.getUserId())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_USERID_G_GROUPID_2);

			query.append(_FINDER_COLUMN_USERID_G_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
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
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByUserId_G_First(long groupId, long userId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByUserId_G_First(groupId, userId, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByUserId_G_First(long groupId, long userId,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByUserId_G(groupId, userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByUserId_G_Last(long groupId, long userId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByUserId_G_Last(groupId, userId, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByUserId_G_Last(long groupId, long userId,
		OrderByComparator<Job> orderByComparator) {
		int count = countByUserId_G(groupId, userId);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByUserId_G(groupId, userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByUserId_G_PrevAndNext(long jobId, long groupId,
		long userId, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByUserId_G_PrevAndNext(session, job, groupId, userId,
					orderByComparator, true);

			array[1] = job;

			array[2] = getByUserId_G_PrevAndNext(session, job, groupId, userId,
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

	protected Job getByUserId_G_PrevAndNext(Session session, Job job,
		long groupId, long userId, OrderByComparator<Job> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		query.append(_FINDER_COLUMN_USERID_G_GROUPID_2);

		query.append(_FINDER_COLUMN_USERID_G_USERID_2);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId_G(long groupId, long userId) {
		for (Job job : findByUserId_G(groupId, userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching jobs
	 */
	@Override
	public int countByUserId_G(long groupId, long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID_G;

		Object[] finderArgs = new Object[] { groupId, userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_USERID_G_GROUPID_2);

			query.append(_FINDER_COLUMN_USERID_G_USERID_2);

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

	private static final String _FINDER_COLUMN_USERID_G_GROUPID_2 = "job.groupId = ? AND ";
	private static final String _FINDER_COLUMN_USERID_G_USERID_2 = "job.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONID =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySimulationId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySimulationId",
			new String[] { Long.class.getName() },
			JobModelImpl.SIMULATIONID_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SIMULATIONID = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySimulationId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the jobs where simulationId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findBySimulationId(long simulationId) {
		return findBySimulationId(simulationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where simulationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationId the simulation ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findBySimulationId(long simulationId, int start, int end) {
		return findBySimulationId(simulationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where simulationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationId the simulation ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findBySimulationId(long simulationId, int start, int end,
		OrderByComparator<Job> orderByComparator) {
		return findBySimulationId(simulationId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the jobs where simulationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulationId the simulation ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findBySimulationId(long simulationId, int start, int end,
		OrderByComparator<Job> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID;
			finderArgs = new Object[] { simulationId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONID;
			finderArgs = new Object[] {
					simulationId,
					
					start, end, orderByComparator
				};
		}

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((simulationId != job.getSimulationId())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONID_SIMULATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(simulationId);

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where simulationId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findBySimulationId_First(long simulationId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchBySimulationId_First(simulationId, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationId=");
		msg.append(simulationId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where simulationId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchBySimulationId_First(long simulationId,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findBySimulationId(simulationId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where simulationId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findBySimulationId_Last(long simulationId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchBySimulationId_Last(simulationId, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulationId=");
		msg.append(simulationId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where simulationId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchBySimulationId_Last(long simulationId,
		OrderByComparator<Job> orderByComparator) {
		int count = countBySimulationId(simulationId);

		if (count == 0) {
			return null;
		}

		List<Job> list = findBySimulationId(simulationId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where simulationId = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param simulationId the simulation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findBySimulationId_PrevAndNext(long jobId, long simulationId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getBySimulationId_PrevAndNext(session, job,
					simulationId, orderByComparator, true);

			array[1] = job;

			array[2] = getBySimulationId_PrevAndNext(session, job,
					simulationId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Job getBySimulationId_PrevAndNext(Session session, Job job,
		long simulationId, OrderByComparator<Job> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		query.append(_FINDER_COLUMN_SIMULATIONID_SIMULATIONID_2);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(simulationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where simulationId = &#63; from the database.
	 *
	 * @param simulationId the simulation ID
	 */
	@Override
	public void removeBySimulationId(long simulationId) {
		for (Job job : findBySimulationId(simulationId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where simulationId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @return the number of matching jobs
	 */
	@Override
	public int countBySimulationId(long simulationId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SIMULATIONID;

		Object[] finderArgs = new Object[] { simulationId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONID_SIMULATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(simulationId);

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

	private static final String _FINDER_COLUMN_SIMULATIONID_SIMULATIONID_2 = "job.simulationId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONID_G =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySimulationId_G",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID_G =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySimulationId_G",
			new String[] { Long.class.getName(), Long.class.getName() },
			JobModelImpl.GROUPID_COLUMN_BITMASK |
			JobModelImpl.SIMULATIONID_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SIMULATIONID_G = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySimulationId_G",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the jobs where groupId = &#63; and simulationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param simulationId the simulation ID
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findBySimulationId_G(long groupId, long simulationId) {
		return findBySimulationId_G(groupId, simulationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where groupId = &#63; and simulationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param simulationId the simulation ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findBySimulationId_G(long groupId, long simulationId,
		int start, int end) {
		return findBySimulationId_G(groupId, simulationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and simulationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param simulationId the simulation ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findBySimulationId_G(long groupId, long simulationId,
		int start, int end, OrderByComparator<Job> orderByComparator) {
		return findBySimulationId_G(groupId, simulationId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and simulationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param simulationId the simulation ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findBySimulationId_G(long groupId, long simulationId,
		int start, int end, OrderByComparator<Job> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID_G;
			finderArgs = new Object[] { groupId, simulationId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONID_G;
			finderArgs = new Object[] {
					groupId, simulationId,
					
					start, end, orderByComparator
				};
		}

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((groupId != job.getGroupId()) ||
							(simulationId != job.getSimulationId())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONID_G_GROUPID_2);

			query.append(_FINDER_COLUMN_SIMULATIONID_G_SIMULATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(simulationId);

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where groupId = &#63; and simulationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param simulationId the simulation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findBySimulationId_G_First(long groupId, long simulationId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchBySimulationId_G_First(groupId, simulationId,
				orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", simulationId=");
		msg.append(simulationId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where groupId = &#63; and simulationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param simulationId the simulation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchBySimulationId_G_First(long groupId, long simulationId,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findBySimulationId_G(groupId, simulationId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and simulationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param simulationId the simulation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findBySimulationId_G_Last(long groupId, long simulationId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchBySimulationId_G_Last(groupId, simulationId,
				orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", simulationId=");
		msg.append(simulationId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and simulationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param simulationId the simulation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchBySimulationId_G_Last(long groupId, long simulationId,
		OrderByComparator<Job> orderByComparator) {
		int count = countBySimulationId_G(groupId, simulationId);

		if (count == 0) {
			return null;
		}

		List<Job> list = findBySimulationId_G(groupId, simulationId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where groupId = &#63; and simulationId = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param groupId the group ID
	 * @param simulationId the simulation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findBySimulationId_G_PrevAndNext(long jobId, long groupId,
		long simulationId, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getBySimulationId_G_PrevAndNext(session, job, groupId,
					simulationId, orderByComparator, true);

			array[1] = job;

			array[2] = getBySimulationId_G_PrevAndNext(session, job, groupId,
					simulationId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Job getBySimulationId_G_PrevAndNext(Session session, Job job,
		long groupId, long simulationId,
		OrderByComparator<Job> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		query.append(_FINDER_COLUMN_SIMULATIONID_G_GROUPID_2);

		query.append(_FINDER_COLUMN_SIMULATIONID_G_SIMULATIONID_2);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(simulationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where groupId = &#63; and simulationId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param simulationId the simulation ID
	 */
	@Override
	public void removeBySimulationId_G(long groupId, long simulationId) {
		for (Job job : findBySimulationId_G(groupId, simulationId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where groupId = &#63; and simulationId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param simulationId the simulation ID
	 * @return the number of matching jobs
	 */
	@Override
	public int countBySimulationId_G(long groupId, long simulationId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SIMULATIONID_G;

		Object[] finderArgs = new Object[] { groupId, simulationId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONID_G_GROUPID_2);

			query.append(_FINDER_COLUMN_SIMULATIONID_G_SIMULATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(simulationId);

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

	private static final String _FINDER_COLUMN_SIMULATIONID_G_GROUPID_2 = "job.groupId = ? AND ";
	private static final String _FINDER_COLUMN_SIMULATIONID_G_SIMULATIONID_2 = "job.simulationId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_QUEUENAME =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByQueueName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUEUENAME =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByQueueName",
			new String[] { String.class.getName() },
			JobModelImpl.QUEUENAME_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_QUEUENAME = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByQueueName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the jobs where queueName = &#63;.
	 *
	 * @param queueName the queue name
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByQueueName(String queueName) {
		return findByQueueName(queueName, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the jobs where queueName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param queueName the queue name
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByQueueName(String queueName, int start, int end) {
		return findByQueueName(queueName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where queueName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param queueName the queue name
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByQueueName(String queueName, int start, int end,
		OrderByComparator<Job> orderByComparator) {
		return findByQueueName(queueName, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where queueName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param queueName the queue name
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByQueueName(String queueName, int start, int end,
		OrderByComparator<Job> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUEUENAME;
			finderArgs = new Object[] { queueName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_QUEUENAME;
			finderArgs = new Object[] { queueName, start, end, orderByComparator };
		}

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if (!Objects.equals(queueName, job.getQueueName())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			boolean bindQueueName = false;

			if (queueName == null) {
				query.append(_FINDER_COLUMN_QUEUENAME_QUEUENAME_1);
			}
			else if (queueName.equals("")) {
				query.append(_FINDER_COLUMN_QUEUENAME_QUEUENAME_3);
			}
			else {
				bindQueueName = true;

				query.append(_FINDER_COLUMN_QUEUENAME_QUEUENAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindQueueName) {
					qPos.add(queueName);
				}

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where queueName = &#63;.
	 *
	 * @param queueName the queue name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByQueueName_First(String queueName,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByQueueName_First(queueName, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("queueName=");
		msg.append(queueName);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where queueName = &#63;.
	 *
	 * @param queueName the queue name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByQueueName_First(String queueName,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByQueueName(queueName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where queueName = &#63;.
	 *
	 * @param queueName the queue name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByQueueName_Last(String queueName,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByQueueName_Last(queueName, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("queueName=");
		msg.append(queueName);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where queueName = &#63;.
	 *
	 * @param queueName the queue name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByQueueName_Last(String queueName,
		OrderByComparator<Job> orderByComparator) {
		int count = countByQueueName(queueName);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByQueueName(queueName, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where queueName = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param queueName the queue name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByQueueName_PrevAndNext(long jobId, String queueName,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByQueueName_PrevAndNext(session, job, queueName,
					orderByComparator, true);

			array[1] = job;

			array[2] = getByQueueName_PrevAndNext(session, job, queueName,
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

	protected Job getByQueueName_PrevAndNext(Session session, Job job,
		String queueName, OrderByComparator<Job> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		boolean bindQueueName = false;

		if (queueName == null) {
			query.append(_FINDER_COLUMN_QUEUENAME_QUEUENAME_1);
		}
		else if (queueName.equals("")) {
			query.append(_FINDER_COLUMN_QUEUENAME_QUEUENAME_3);
		}
		else {
			bindQueueName = true;

			query.append(_FINDER_COLUMN_QUEUENAME_QUEUENAME_2);
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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindQueueName) {
			qPos.add(queueName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where queueName = &#63; from the database.
	 *
	 * @param queueName the queue name
	 */
	@Override
	public void removeByQueueName(String queueName) {
		for (Job job : findByQueueName(queueName, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where queueName = &#63;.
	 *
	 * @param queueName the queue name
	 * @return the number of matching jobs
	 */
	@Override
	public int countByQueueName(String queueName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_QUEUENAME;

		Object[] finderArgs = new Object[] { queueName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JOB_WHERE);

			boolean bindQueueName = false;

			if (queueName == null) {
				query.append(_FINDER_COLUMN_QUEUENAME_QUEUENAME_1);
			}
			else if (queueName.equals("")) {
				query.append(_FINDER_COLUMN_QUEUENAME_QUEUENAME_3);
			}
			else {
				bindQueueName = true;

				query.append(_FINDER_COLUMN_QUEUENAME_QUEUENAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindQueueName) {
					qPos.add(queueName);
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

	private static final String _FINDER_COLUMN_QUEUENAME_QUEUENAME_1 = "job.queueName IS NULL";
	private static final String _FINDER_COLUMN_QUEUENAME_QUEUENAME_2 = "job.queueName = ?";
	private static final String _FINDER_COLUMN_QUEUENAME_QUEUENAME_3 = "(job.queueName IS NULL OR job.queueName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatus",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus",
			new String[] { Integer.class.getName() },
			JobModelImpl.STATUS_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the jobs where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByStatus(int status) {
		return findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByStatus(int status, int start, int end) {
		return findByStatus(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByStatus(int status, int start, int end,
		OrderByComparator<Job> orderByComparator) {
		return findByStatus(status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByStatus(int status, int start, int end,
		OrderByComparator<Job> orderByComparator, boolean retrieveFromCache) {
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

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((status != job.getStatus())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByStatus_First(int status,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByStatus_First(status, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByStatus_First(int status,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByStatus(status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByStatus_Last(int status,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByStatus_Last(status, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByStatus_Last(int status,
		OrderByComparator<Job> orderByComparator) {
		int count = countByStatus(status);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByStatus(status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where status = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByStatus_PrevAndNext(long jobId, int status,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByStatus_PrevAndNext(session, job, status,
					orderByComparator, true);

			array[1] = job;

			array[2] = getByStatus_PrevAndNext(session, job, status,
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

	protected Job getByStatus_PrevAndNext(Session session, Job job, int status,
		OrderByComparator<Job> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	@Override
	public void removeByStatus(int status) {
		for (Job job : findByStatus(status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching jobs
	 */
	@Override
	public int countByStatus(int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STATUS;

		Object[] finderArgs = new Object[] { status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JOB_WHERE);

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

	private static final String _FINDER_COLUMN_STATUS_STATUS_2 = "job.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS_G = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatus_G",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_G =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus_G",
			new String[] { Long.class.getName(), Integer.class.getName() },
			JobModelImpl.GROUPID_COLUMN_BITMASK |
			JobModelImpl.STATUS_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS_G = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus_G",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the jobs where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByStatus_G(long groupId, int status) {
		return findByStatus_G(groupId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByStatus_G(long groupId, int status, int start, int end) {
		return findByStatus_G(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByStatus_G(long groupId, int status, int start,
		int end, OrderByComparator<Job> orderByComparator) {
		return findByStatus_G(groupId, status, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByStatus_G(long groupId, int status, int start,
		int end, OrderByComparator<Job> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_G;
			finderArgs = new Object[] { groupId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS_G;
			finderArgs = new Object[] {
					groupId, status,
					
					start, end, orderByComparator
				};
		}

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((groupId != job.getGroupId()) ||
							(status != job.getStatus())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_STATUS_G_GROUPID_2);

			query.append(_FINDER_COLUMN_STATUS_G_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
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
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByStatus_G_First(long groupId, int status,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByStatus_G_First(groupId, status, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByStatus_G_First(long groupId, int status,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByStatus_G(groupId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByStatus_G_Last(long groupId, int status,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByStatus_G_Last(groupId, status, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByStatus_G_Last(long groupId, int status,
		OrderByComparator<Job> orderByComparator) {
		int count = countByStatus_G(groupId, status);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByStatus_G(groupId, status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByStatus_G_PrevAndNext(long jobId, long groupId,
		int status, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByStatus_G_PrevAndNext(session, job, groupId, status,
					orderByComparator, true);

			array[1] = job;

			array[2] = getByStatus_G_PrevAndNext(session, job, groupId, status,
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

	protected Job getByStatus_G_PrevAndNext(Session session, Job job,
		long groupId, int status, OrderByComparator<Job> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		query.append(_FINDER_COLUMN_STATUS_G_GROUPID_2);

		query.append(_FINDER_COLUMN_STATUS_G_STATUS_2);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 */
	@Override
	public void removeByStatus_G(long groupId, int status) {
		for (Job job : findByStatus_G(groupId, status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching jobs
	 */
	@Override
	public int countByStatus_G(long groupId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STATUS_G;

		Object[] finderArgs = new Object[] { groupId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_STATUS_G_GROUPID_2);

			query.append(_FINDER_COLUMN_STATUS_G_STATUS_2);

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

	private static final String _FINDER_COLUMN_STATUS_G_GROUPID_2 = "job.groupId = ? AND ";
	private static final String _FINDER_COLUMN_STATUS_G_STATUS_2 = "job.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS_U = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatus_U",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_U =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus_U",
			new String[] { Long.class.getName(), Integer.class.getName() },
			JobModelImpl.USERID_COLUMN_BITMASK |
			JobModelImpl.STATUS_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS_U = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus_U",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the jobs where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByStatus_U(long userId, int status) {
		return findByStatus_U(userId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByStatus_U(long userId, int status, int start, int end) {
		return findByStatus_U(userId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByStatus_U(long userId, int status, int start,
		int end, OrderByComparator<Job> orderByComparator) {
		return findByStatus_U(userId, status, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the jobs where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByStatus_U(long userId, int status, int start,
		int end, OrderByComparator<Job> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_U;
			finderArgs = new Object[] { userId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS_U;
			finderArgs = new Object[] {
					userId, status,
					
					start, end, orderByComparator
				};
		}

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((userId != job.getUserId()) ||
							(status != job.getStatus())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_STATUS_U_USERID_2);

			query.append(_FINDER_COLUMN_STATUS_U_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
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
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByStatus_U_First(long userId, int status,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByStatus_U_First(userId, status, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByStatus_U_First(long userId, int status,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByStatus_U(userId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByStatus_U_Last(long userId, int status,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByStatus_U_Last(userId, status, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByStatus_U_Last(long userId, int status,
		OrderByComparator<Job> orderByComparator) {
		int count = countByStatus_U(userId, status);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByStatus_U(userId, status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByStatus_U_PrevAndNext(long jobId, long userId,
		int status, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByStatus_U_PrevAndNext(session, job, userId, status,
					orderByComparator, true);

			array[1] = job;

			array[2] = getByStatus_U_PrevAndNext(session, job, userId, status,
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

	protected Job getByStatus_U_PrevAndNext(Session session, Job job,
		long userId, int status, OrderByComparator<Job> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		query.append(_FINDER_COLUMN_STATUS_U_USERID_2);

		query.append(_FINDER_COLUMN_STATUS_U_STATUS_2);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where userId = &#63; and status = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param status the status
	 */
	@Override
	public void removeByStatus_U(long userId, int status) {
		for (Job job : findByStatus_U(userId, status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching jobs
	 */
	@Override
	public int countByStatus_U(long userId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STATUS_U;

		Object[] finderArgs = new Object[] { userId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_STATUS_U_USERID_2);

			query.append(_FINDER_COLUMN_STATUS_U_STATUS_2);

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

	private static final String _FINDER_COLUMN_STATUS_U_USERID_2 = "job.userId = ? AND ";
	private static final String _FINDER_COLUMN_STATUS_U_STATUS_2 = "job.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS_G_U =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatus_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_G_U =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			JobModelImpl.GROUPID_COLUMN_BITMASK |
			JobModelImpl.USERID_COLUMN_BITMASK |
			JobModelImpl.STATUS_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS_G_U = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the jobs where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByStatus_G_U(long groupId, long userId, int status) {
		return findByStatus_G_U(groupId, userId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByStatus_G_U(long groupId, long userId, int status,
		int start, int end) {
		return findByStatus_G_U(groupId, userId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByStatus_G_U(long groupId, long userId, int status,
		int start, int end, OrderByComparator<Job> orderByComparator) {
		return findByStatus_G_U(groupId, userId, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByStatus_G_U(long groupId, long userId, int status,
		int start, int end, OrderByComparator<Job> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_G_U;
			finderArgs = new Object[] { groupId, userId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS_G_U;
			finderArgs = new Object[] {
					groupId, userId, status,
					
					start, end, orderByComparator
				};
		}

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((groupId != job.getGroupId()) ||
							(userId != job.getUserId()) ||
							(status != job.getStatus())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_STATUS_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_STATUS_G_U_USERID_2);

			query.append(_FINDER_COLUMN_STATUS_G_U_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
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
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByStatus_G_U_First(long groupId, long userId, int status,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByStatus_G_U_First(groupId, userId, status,
				orderByComparator);

		if (job != null) {
			return job;
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

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByStatus_G_U_First(long groupId, long userId, int status,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByStatus_G_U(groupId, userId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByStatus_G_U_Last(long groupId, long userId, int status,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByStatus_G_U_Last(groupId, userId, status,
				orderByComparator);

		if (job != null) {
			return job;
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

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByStatus_G_U_Last(long groupId, long userId, int status,
		OrderByComparator<Job> orderByComparator) {
		int count = countByStatus_G_U(groupId, userId, status);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByStatus_G_U(groupId, userId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByStatus_G_U_PrevAndNext(long jobId, long groupId,
		long userId, int status, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByStatus_G_U_PrevAndNext(session, job, groupId,
					userId, status, orderByComparator, true);

			array[1] = job;

			array[2] = getByStatus_G_U_PrevAndNext(session, job, groupId,
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

	protected Job getByStatus_G_U_PrevAndNext(Session session, Job job,
		long groupId, long userId, int status,
		OrderByComparator<Job> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		query.append(_FINDER_COLUMN_STATUS_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_STATUS_G_U_USERID_2);

		query.append(_FINDER_COLUMN_STATUS_G_U_STATUS_2);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where groupId = &#63; and userId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 */
	@Override
	public void removeByStatus_G_U(long groupId, long userId, int status) {
		for (Job job : findByStatus_G_U(groupId, userId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching jobs
	 */
	@Override
	public int countByStatus_G_U(long groupId, long userId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STATUS_G_U;

		Object[] finderArgs = new Object[] { groupId, userId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_STATUS_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_STATUS_G_U_USERID_2);

			query.append(_FINDER_COLUMN_STATUS_G_U_STATUS_2);

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

	private static final String _FINDER_COLUMN_STATUS_G_U_GROUPID_2 = "job.groupId = ? AND ";
	private static final String _FINDER_COLUMN_STATUS_G_U_USERID_2 = "job.userId = ? AND ";
	private static final String _FINDER_COLUMN_STATUS_G_U_STATUS_2 = "job.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBSTATUS =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByJobStatus",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByJobStatus",
			new String[] { Integer.class.getName() },
			JobModelImpl.JOBSTATUS_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JOBSTATUS = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByJobStatus",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the jobs where jobStatus = &#63;.
	 *
	 * @param jobStatus the job status
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByJobStatus(int jobStatus) {
		return findByJobStatus(jobStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the jobs where jobStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jobStatus the job status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByJobStatus(int jobStatus, int start, int end) {
		return findByJobStatus(jobStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where jobStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jobStatus the job status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByJobStatus(int jobStatus, int start, int end,
		OrderByComparator<Job> orderByComparator) {
		return findByJobStatus(jobStatus, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where jobStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jobStatus the job status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByJobStatus(int jobStatus, int start, int end,
		OrderByComparator<Job> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS;
			finderArgs = new Object[] { jobStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBSTATUS;
			finderArgs = new Object[] { jobStatus, start, end, orderByComparator };
		}

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((jobStatus != job.getJobStatus())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_JOBSTATUS_JOBSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jobStatus);

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where jobStatus = &#63;.
	 *
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByJobStatus_First(int jobStatus,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByJobStatus_First(jobStatus, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jobStatus=");
		msg.append(jobStatus);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where jobStatus = &#63;.
	 *
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByJobStatus_First(int jobStatus,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByJobStatus(jobStatus, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where jobStatus = &#63;.
	 *
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByJobStatus_Last(int jobStatus,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByJobStatus_Last(jobStatus, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jobStatus=");
		msg.append(jobStatus);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where jobStatus = &#63;.
	 *
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByJobStatus_Last(int jobStatus,
		OrderByComparator<Job> orderByComparator) {
		int count = countByJobStatus(jobStatus);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByJobStatus(jobStatus, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where jobStatus = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByJobStatus_PrevAndNext(long jobId, int jobStatus,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByJobStatus_PrevAndNext(session, job, jobStatus,
					orderByComparator, true);

			array[1] = job;

			array[2] = getByJobStatus_PrevAndNext(session, job, jobStatus,
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

	protected Job getByJobStatus_PrevAndNext(Session session, Job job,
		int jobStatus, OrderByComparator<Job> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		query.append(_FINDER_COLUMN_JOBSTATUS_JOBSTATUS_2);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(jobStatus);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where jobStatus = &#63; from the database.
	 *
	 * @param jobStatus the job status
	 */
	@Override
	public void removeByJobStatus(int jobStatus) {
		for (Job job : findByJobStatus(jobStatus, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where jobStatus = &#63;.
	 *
	 * @param jobStatus the job status
	 * @return the number of matching jobs
	 */
	@Override
	public int countByJobStatus(int jobStatus) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_JOBSTATUS;

		Object[] finderArgs = new Object[] { jobStatus };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_JOBSTATUS_JOBSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jobStatus);

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

	private static final String _FINDER_COLUMN_JOBSTATUS_JOBSTATUS_2 = "job.jobStatus = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBSTATUS_G =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByJobStatus_G",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_G =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByJobStatus_G",
			new String[] { Long.class.getName(), Integer.class.getName() },
			JobModelImpl.GROUPID_COLUMN_BITMASK |
			JobModelImpl.JOBSTATUS_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JOBSTATUS_G = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByJobStatus_G",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the jobs where groupId = &#63; and jobStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobStatus the job status
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByJobStatus_G(long groupId, int jobStatus) {
		return findByJobStatus_G(groupId, jobStatus, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where groupId = &#63; and jobStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param jobStatus the job status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByJobStatus_G(long groupId, int jobStatus, int start,
		int end) {
		return findByJobStatus_G(groupId, jobStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and jobStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param jobStatus the job status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByJobStatus_G(long groupId, int jobStatus, int start,
		int end, OrderByComparator<Job> orderByComparator) {
		return findByJobStatus_G(groupId, jobStatus, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and jobStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param jobStatus the job status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByJobStatus_G(long groupId, int jobStatus, int start,
		int end, OrderByComparator<Job> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_G;
			finderArgs = new Object[] { groupId, jobStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBSTATUS_G;
			finderArgs = new Object[] {
					groupId, jobStatus,
					
					start, end, orderByComparator
				};
		}

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((groupId != job.getGroupId()) ||
							(jobStatus != job.getJobStatus())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_JOBSTATUS_G_GROUPID_2);

			query.append(_FINDER_COLUMN_JOBSTATUS_G_JOBSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(jobStatus);

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where groupId = &#63; and jobStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByJobStatus_G_First(long groupId, int jobStatus,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByJobStatus_G_First(groupId, jobStatus, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", jobStatus=");
		msg.append(jobStatus);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where groupId = &#63; and jobStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByJobStatus_G_First(long groupId, int jobStatus,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByJobStatus_G(groupId, jobStatus, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and jobStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByJobStatus_G_Last(long groupId, int jobStatus,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByJobStatus_G_Last(groupId, jobStatus, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", jobStatus=");
		msg.append(jobStatus);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and jobStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByJobStatus_G_Last(long groupId, int jobStatus,
		OrderByComparator<Job> orderByComparator) {
		int count = countByJobStatus_G(groupId, jobStatus);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByJobStatus_G(groupId, jobStatus, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where groupId = &#63; and jobStatus = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param groupId the group ID
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByJobStatus_G_PrevAndNext(long jobId, long groupId,
		int jobStatus, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByJobStatus_G_PrevAndNext(session, job, groupId,
					jobStatus, orderByComparator, true);

			array[1] = job;

			array[2] = getByJobStatus_G_PrevAndNext(session, job, groupId,
					jobStatus, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Job getByJobStatus_G_PrevAndNext(Session session, Job job,
		long groupId, int jobStatus, OrderByComparator<Job> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		query.append(_FINDER_COLUMN_JOBSTATUS_G_GROUPID_2);

		query.append(_FINDER_COLUMN_JOBSTATUS_G_JOBSTATUS_2);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(jobStatus);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where groupId = &#63; and jobStatus = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param jobStatus the job status
	 */
	@Override
	public void removeByJobStatus_G(long groupId, int jobStatus) {
		for (Job job : findByJobStatus_G(groupId, jobStatus, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where groupId = &#63; and jobStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobStatus the job status
	 * @return the number of matching jobs
	 */
	@Override
	public int countByJobStatus_G(long groupId, int jobStatus) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_JOBSTATUS_G;

		Object[] finderArgs = new Object[] { groupId, jobStatus };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_JOBSTATUS_G_GROUPID_2);

			query.append(_FINDER_COLUMN_JOBSTATUS_G_JOBSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(jobStatus);

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

	private static final String _FINDER_COLUMN_JOBSTATUS_G_GROUPID_2 = "job.groupId = ? AND ";
	private static final String _FINDER_COLUMN_JOBSTATUS_G_JOBSTATUS_2 = "job.jobStatus = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBSTATUS_U =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByJobStatus_U",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_U =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByJobStatus_U",
			new String[] { Long.class.getName(), Integer.class.getName() },
			JobModelImpl.USERID_COLUMN_BITMASK |
			JobModelImpl.JOBSTATUS_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JOBSTATUS_U = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByJobStatus_U",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the jobs where userId = &#63; and jobStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByJobStatus_U(long userId, int jobStatus) {
		return findByJobStatus_U(userId, jobStatus, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where userId = &#63; and jobStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByJobStatus_U(long userId, int jobStatus, int start,
		int end) {
		return findByJobStatus_U(userId, jobStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where userId = &#63; and jobStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByJobStatus_U(long userId, int jobStatus, int start,
		int end, OrderByComparator<Job> orderByComparator) {
		return findByJobStatus_U(userId, jobStatus, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where userId = &#63; and jobStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByJobStatus_U(long userId, int jobStatus, int start,
		int end, OrderByComparator<Job> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_U;
			finderArgs = new Object[] { userId, jobStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBSTATUS_U;
			finderArgs = new Object[] {
					userId, jobStatus,
					
					start, end, orderByComparator
				};
		}

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((userId != job.getUserId()) ||
							(jobStatus != job.getJobStatus())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_JOBSTATUS_U_USERID_2);

			query.append(_FINDER_COLUMN_JOBSTATUS_U_JOBSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(jobStatus);

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where userId = &#63; and jobStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByJobStatus_U_First(long userId, int jobStatus,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByJobStatus_U_First(userId, jobStatus, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", jobStatus=");
		msg.append(jobStatus);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where userId = &#63; and jobStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByJobStatus_U_First(long userId, int jobStatus,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByJobStatus_U(userId, jobStatus, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where userId = &#63; and jobStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByJobStatus_U_Last(long userId, int jobStatus,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByJobStatus_U_Last(userId, jobStatus, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", jobStatus=");
		msg.append(jobStatus);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where userId = &#63; and jobStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByJobStatus_U_Last(long userId, int jobStatus,
		OrderByComparator<Job> orderByComparator) {
		int count = countByJobStatus_U(userId, jobStatus);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByJobStatus_U(userId, jobStatus, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where userId = &#63; and jobStatus = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByJobStatus_U_PrevAndNext(long jobId, long userId,
		int jobStatus, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByJobStatus_U_PrevAndNext(session, job, userId,
					jobStatus, orderByComparator, true);

			array[1] = job;

			array[2] = getByJobStatus_U_PrevAndNext(session, job, userId,
					jobStatus, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Job getByJobStatus_U_PrevAndNext(Session session, Job job,
		long userId, int jobStatus, OrderByComparator<Job> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		query.append(_FINDER_COLUMN_JOBSTATUS_U_USERID_2);

		query.append(_FINDER_COLUMN_JOBSTATUS_U_JOBSTATUS_2);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(jobStatus);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where userId = &#63; and jobStatus = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param jobStatus the job status
	 */
	@Override
	public void removeByJobStatus_U(long userId, int jobStatus) {
		for (Job job : findByJobStatus_U(userId, jobStatus, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where userId = &#63; and jobStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @return the number of matching jobs
	 */
	@Override
	public int countByJobStatus_U(long userId, int jobStatus) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_JOBSTATUS_U;

		Object[] finderArgs = new Object[] { userId, jobStatus };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_JOBSTATUS_U_USERID_2);

			query.append(_FINDER_COLUMN_JOBSTATUS_U_JOBSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(jobStatus);

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

	private static final String _FINDER_COLUMN_JOBSTATUS_U_USERID_2 = "job.userId = ? AND ";
	private static final String _FINDER_COLUMN_JOBSTATUS_U_JOBSTATUS_2 = "job.jobStatus = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBSTATUS_G_U =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByJobStatus_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_G_U =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByJobStatus_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			JobModelImpl.GROUPID_COLUMN_BITMASK |
			JobModelImpl.USERID_COLUMN_BITMASK |
			JobModelImpl.JOBSTATUS_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JOBSTATUS_G_U = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByJobStatus_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the jobs where groupId = &#63; and userId = &#63; and jobStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByJobStatus_G_U(long groupId, long userId,
		int jobStatus) {
		return findByJobStatus_G_U(groupId, userId, jobStatus,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where groupId = &#63; and userId = &#63; and jobStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByJobStatus_G_U(long groupId, long userId,
		int jobStatus, int start, int end) {
		return findByJobStatus_G_U(groupId, userId, jobStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and userId = &#63; and jobStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByJobStatus_G_U(long groupId, long userId,
		int jobStatus, int start, int end,
		OrderByComparator<Job> orderByComparator) {
		return findByJobStatus_G_U(groupId, userId, jobStatus, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and userId = &#63; and jobStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByJobStatus_G_U(long groupId, long userId,
		int jobStatus, int start, int end,
		OrderByComparator<Job> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_G_U;
			finderArgs = new Object[] { groupId, userId, jobStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBSTATUS_G_U;
			finderArgs = new Object[] {
					groupId, userId, jobStatus,
					
					start, end, orderByComparator
				};
		}

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((groupId != job.getGroupId()) ||
							(userId != job.getUserId()) ||
							(jobStatus != job.getJobStatus())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_JOBSTATUS_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_JOBSTATUS_G_U_USERID_2);

			query.append(_FINDER_COLUMN_JOBSTATUS_G_U_JOBSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(jobStatus);

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where groupId = &#63; and userId = &#63; and jobStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByJobStatus_G_U_First(long groupId, long userId,
		int jobStatus, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = fetchByJobStatus_G_U_First(groupId, userId, jobStatus,
				orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", jobStatus=");
		msg.append(jobStatus);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where groupId = &#63; and userId = &#63; and jobStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByJobStatus_G_U_First(long groupId, long userId,
		int jobStatus, OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByJobStatus_G_U(groupId, userId, jobStatus, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and userId = &#63; and jobStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByJobStatus_G_U_Last(long groupId, long userId,
		int jobStatus, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = fetchByJobStatus_G_U_Last(groupId, userId, jobStatus,
				orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", jobStatus=");
		msg.append(jobStatus);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and userId = &#63; and jobStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByJobStatus_G_U_Last(long groupId, long userId,
		int jobStatus, OrderByComparator<Job> orderByComparator) {
		int count = countByJobStatus_G_U(groupId, userId, jobStatus);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByJobStatus_G_U(groupId, userId, jobStatus,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where groupId = &#63; and userId = &#63; and jobStatus = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByJobStatus_G_U_PrevAndNext(long jobId, long groupId,
		long userId, int jobStatus, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByJobStatus_G_U_PrevAndNext(session, job, groupId,
					userId, jobStatus, orderByComparator, true);

			array[1] = job;

			array[2] = getByJobStatus_G_U_PrevAndNext(session, job, groupId,
					userId, jobStatus, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Job getByJobStatus_G_U_PrevAndNext(Session session, Job job,
		long groupId, long userId, int jobStatus,
		OrderByComparator<Job> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		query.append(_FINDER_COLUMN_JOBSTATUS_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_JOBSTATUS_G_U_USERID_2);

		query.append(_FINDER_COLUMN_JOBSTATUS_G_U_JOBSTATUS_2);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		qPos.add(jobStatus);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where groupId = &#63; and userId = &#63; and jobStatus = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param jobStatus the job status
	 */
	@Override
	public void removeByJobStatus_G_U(long groupId, long userId, int jobStatus) {
		for (Job job : findByJobStatus_G_U(groupId, userId, jobStatus,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where groupId = &#63; and userId = &#63; and jobStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param jobStatus the job status
	 * @return the number of matching jobs
	 */
	@Override
	public int countByJobStatus_G_U(long groupId, long userId, int jobStatus) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_JOBSTATUS_G_U;

		Object[] finderArgs = new Object[] { groupId, userId, jobStatus };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_JOBSTATUS_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_JOBSTATUS_G_U_USERID_2);

			query.append(_FINDER_COLUMN_JOBSTATUS_G_U_JOBSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(jobStatus);

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

	private static final String _FINDER_COLUMN_JOBSTATUS_G_U_GROUPID_2 = "job.groupId = ? AND ";
	private static final String _FINDER_COLUMN_JOBSTATUS_G_U_USERID_2 = "job.userId = ? AND ";
	private static final String _FINDER_COLUMN_JOBSTATUS_G_U_JOBSTATUS_2 = "job.jobStatus = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_SIMULATIONJOBID = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchBySimulationJobId",
			new String[] { Long.class.getName(), Long.class.getName() },
			JobModelImpl.SIMULATIONID_COLUMN_BITMASK |
			JobModelImpl.JOBID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SIMULATIONJOBID = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySimulationJobId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the job where simulationId = &#63; and jobId = &#63; or throws a {@link NoSuchJobException} if it could not be found.
	 *
	 * @param simulationId the simulation ID
	 * @param jobId the job ID
	 * @return the matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findBySimulationJobId(long simulationId, long jobId)
		throws NoSuchJobException {
		Job job = fetchBySimulationJobId(simulationId, jobId);

		if (job == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("simulationId=");
			msg.append(simulationId);

			msg.append(", jobId=");
			msg.append(jobId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchJobException(msg.toString());
		}

		return job;
	}

	/**
	 * Returns the job where simulationId = &#63; and jobId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param simulationId the simulation ID
	 * @param jobId the job ID
	 * @return the matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchBySimulationJobId(long simulationId, long jobId) {
		return fetchBySimulationJobId(simulationId, jobId, true);
	}

	/**
	 * Returns the job where simulationId = &#63; and jobId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param simulationId the simulation ID
	 * @param jobId the job ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchBySimulationJobId(long simulationId, long jobId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { simulationId, jobId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_SIMULATIONJOBID,
					finderArgs, this);
		}

		if (result instanceof Job) {
			Job job = (Job)result;

			if ((simulationId != job.getSimulationId()) ||
					(jobId != job.getJobId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONJOBID_SIMULATIONID_2);

			query.append(_FINDER_COLUMN_SIMULATIONJOBID_JOBID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(simulationId);

				qPos.add(jobId);

				List<Job> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_SIMULATIONJOBID,
						finderArgs, list);
				}
				else {
					Job job = list.get(0);

					result = job;

					cacheResult(job);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_SIMULATIONJOBID,
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
			return (Job)result;
		}
	}

	/**
	 * Removes the job where simulationId = &#63; and jobId = &#63; from the database.
	 *
	 * @param simulationId the simulation ID
	 * @param jobId the job ID
	 * @return the job that was removed
	 */
	@Override
	public Job removeBySimulationJobId(long simulationId, long jobId)
		throws NoSuchJobException {
		Job job = findBySimulationJobId(simulationId, jobId);

		return remove(job);
	}

	/**
	 * Returns the number of jobs where simulationId = &#63; and jobId = &#63;.
	 *
	 * @param simulationId the simulation ID
	 * @param jobId the job ID
	 * @return the number of matching jobs
	 */
	@Override
	public int countBySimulationJobId(long simulationId, long jobId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SIMULATIONJOBID;

		Object[] finderArgs = new Object[] { simulationId, jobId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONJOBID_SIMULATIONID_2);

			query.append(_FINDER_COLUMN_SIMULATIONJOBID_JOBID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(simulationId);

				qPos.add(jobId);

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

	private static final String _FINDER_COLUMN_SIMULATIONJOBID_SIMULATIONID_2 = "job.simulationId = ? AND ";
	private static final String _FINDER_COLUMN_SIMULATIONJOBID_JOBID_2 = "job.jobId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTitle",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTitle",
			new String[] { String.class.getName() });

	/**
	 * Returns all the jobs where title LIKE &#63;.
	 *
	 * @param title the title
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByTitle(String title) {
		return findByTitle(title, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByTitle(String title, int start, int end) {
		return findByTitle(title, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByTitle(String title, int start, int end,
		OrderByComparator<Job> orderByComparator) {
		return findByTitle(title, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByTitle(String title, int start, int end,
		OrderByComparator<Job> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE;
		finderArgs = new Object[] { title, start, end, orderByComparator };

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if (!StringUtil.wildcardMatches(job.getTitle(), title, '_',
								'%', '\\', true)) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

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
				query.append(JobModelImpl.ORDER_BY_JPQL);
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
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByTitle_First(String title,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByTitle_First(title, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByTitle_First(String title,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByTitle(title, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByTitle_Last(String title,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByTitle_Last(title, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("title=");
		msg.append(title);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where title LIKE &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByTitle_Last(String title,
		OrderByComparator<Job> orderByComparator) {
		int count = countByTitle(title);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByTitle(title, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where title LIKE &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByTitle_PrevAndNext(long jobId, String title,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByTitle_PrevAndNext(session, job, title,
					orderByComparator, true);

			array[1] = job;

			array[2] = getByTitle_PrevAndNext(session, job, title,
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

	protected Job getByTitle_PrevAndNext(Session session, Job job,
		String title, OrderByComparator<Job> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where title LIKE &#63; from the database.
	 *
	 * @param title the title
	 */
	@Override
	public void removeByTitle(String title) {
		for (Job job : findByTitle(title, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where title LIKE &#63;.
	 *
	 * @param title the title
	 * @return the number of matching jobs
	 */
	@Override
	public int countByTitle(String title) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE;

		Object[] finderArgs = new Object[] { title };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JOB_WHERE);

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

	private static final String _FINDER_COLUMN_TITLE_TITLE_1 = "job.title IS NULL";
	private static final String _FINDER_COLUMN_TITLE_TITLE_2 = "job.title LIKE ?";
	private static final String _FINDER_COLUMN_TITLE_TITLE_3 = "(job.title IS NULL OR job.title LIKE '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE_G = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTitle_G",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE_G = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTitle_G",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the jobs where groupId = &#63; and title LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByTitle_G(long groupId, String title) {
		return findByTitle_G(groupId, title, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where groupId = &#63; and title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByTitle_G(long groupId, String title, int start,
		int end) {
		return findByTitle_G(groupId, title, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByTitle_G(long groupId, String title, int start,
		int end, OrderByComparator<Job> orderByComparator) {
		return findByTitle_G(groupId, title, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByTitle_G(long groupId, String title, int start,
		int end, OrderByComparator<Job> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE_G;
		finderArgs = new Object[] { groupId, title, start, end, orderByComparator };

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((groupId != job.getGroupId()) ||
							!StringUtil.wildcardMatches(job.getTitle(), title,
								'_', '%', '\\', true)) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_TITLE_G_GROUPID_2);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_G_TITLE_1);
			}
			else if (title.equals("")) {
				query.append(_FINDER_COLUMN_TITLE_G_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_G_TITLE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTitle) {
					qPos.add(title);
				}

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where groupId = &#63; and title LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByTitle_G_First(long groupId, String title,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByTitle_G_First(groupId, title, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", title=");
		msg.append(title);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where groupId = &#63; and title LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByTitle_G_First(long groupId, String title,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByTitle_G(groupId, title, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and title LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByTitle_G_Last(long groupId, String title,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByTitle_G_Last(groupId, title, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", title=");
		msg.append(title);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and title LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByTitle_G_Last(long groupId, String title,
		OrderByComparator<Job> orderByComparator) {
		int count = countByTitle_G(groupId, title);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByTitle_G(groupId, title, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where groupId = &#63; and title LIKE &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByTitle_G_PrevAndNext(long jobId, long groupId,
		String title, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByTitle_G_PrevAndNext(session, job, groupId, title,
					orderByComparator, true);

			array[1] = job;

			array[2] = getByTitle_G_PrevAndNext(session, job, groupId, title,
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

	protected Job getByTitle_G_PrevAndNext(Session session, Job job,
		long groupId, String title, OrderByComparator<Job> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		query.append(_FINDER_COLUMN_TITLE_G_GROUPID_2);

		boolean bindTitle = false;

		if (title == null) {
			query.append(_FINDER_COLUMN_TITLE_G_TITLE_1);
		}
		else if (title.equals("")) {
			query.append(_FINDER_COLUMN_TITLE_G_TITLE_3);
		}
		else {
			bindTitle = true;

			query.append(_FINDER_COLUMN_TITLE_G_TITLE_2);
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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindTitle) {
			qPos.add(title);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where groupId = &#63; and title LIKE &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 */
	@Override
	public void removeByTitle_G(long groupId, String title) {
		for (Job job : findByTitle_G(groupId, title, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where groupId = &#63; and title LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the number of matching jobs
	 */
	@Override
	public int countByTitle_G(long groupId, String title) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE_G;

		Object[] finderArgs = new Object[] { groupId, title };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_TITLE_G_GROUPID_2);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_G_TITLE_1);
			}
			else if (title.equals("")) {
				query.append(_FINDER_COLUMN_TITLE_G_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_G_TITLE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_TITLE_G_GROUPID_2 = "job.groupId = ? AND ";
	private static final String _FINDER_COLUMN_TITLE_G_TITLE_1 = "job.title IS NULL";
	private static final String _FINDER_COLUMN_TITLE_G_TITLE_2 = "job.title LIKE ?";
	private static final String _FINDER_COLUMN_TITLE_G_TITLE_3 = "(job.title IS NULL OR job.title LIKE '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE_U = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTitle_U",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE_U = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTitle_U",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the jobs where userId = &#63; and title LIKE &#63;.
	 *
	 * @param userId the user ID
	 * @param title the title
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByTitle_U(long userId, String title) {
		return findByTitle_U(userId, title, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where userId = &#63; and title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param title the title
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByTitle_U(long userId, String title, int start, int end) {
		return findByTitle_U(userId, title, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where userId = &#63; and title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param title the title
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByTitle_U(long userId, String title, int start,
		int end, OrderByComparator<Job> orderByComparator) {
		return findByTitle_U(userId, title, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where userId = &#63; and title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param title the title
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByTitle_U(long userId, String title, int start,
		int end, OrderByComparator<Job> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE_U;
		finderArgs = new Object[] { userId, title, start, end, orderByComparator };

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((userId != job.getUserId()) ||
							!StringUtil.wildcardMatches(job.getTitle(), title,
								'_', '%', '\\', true)) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_TITLE_U_USERID_2);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_U_TITLE_1);
			}
			else if (title.equals("")) {
				query.append(_FINDER_COLUMN_TITLE_U_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_U_TITLE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindTitle) {
					qPos.add(title);
				}

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where userId = &#63; and title LIKE &#63;.
	 *
	 * @param userId the user ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByTitle_U_First(long userId, String title,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByTitle_U_First(userId, title, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", title=");
		msg.append(title);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where userId = &#63; and title LIKE &#63;.
	 *
	 * @param userId the user ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByTitle_U_First(long userId, String title,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByTitle_U(userId, title, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where userId = &#63; and title LIKE &#63;.
	 *
	 * @param userId the user ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByTitle_U_Last(long userId, String title,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByTitle_U_Last(userId, title, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", title=");
		msg.append(title);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where userId = &#63; and title LIKE &#63;.
	 *
	 * @param userId the user ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByTitle_U_Last(long userId, String title,
		OrderByComparator<Job> orderByComparator) {
		int count = countByTitle_U(userId, title);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByTitle_U(userId, title, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where userId = &#63; and title LIKE &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param userId the user ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByTitle_U_PrevAndNext(long jobId, long userId,
		String title, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByTitle_U_PrevAndNext(session, job, userId, title,
					orderByComparator, true);

			array[1] = job;

			array[2] = getByTitle_U_PrevAndNext(session, job, userId, title,
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

	protected Job getByTitle_U_PrevAndNext(Session session, Job job,
		long userId, String title, OrderByComparator<Job> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		query.append(_FINDER_COLUMN_TITLE_U_USERID_2);

		boolean bindTitle = false;

		if (title == null) {
			query.append(_FINDER_COLUMN_TITLE_U_TITLE_1);
		}
		else if (title.equals("")) {
			query.append(_FINDER_COLUMN_TITLE_U_TITLE_3);
		}
		else {
			bindTitle = true;

			query.append(_FINDER_COLUMN_TITLE_U_TITLE_2);
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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (bindTitle) {
			qPos.add(title);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where userId = &#63; and title LIKE &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param title the title
	 */
	@Override
	public void removeByTitle_U(long userId, String title) {
		for (Job job : findByTitle_U(userId, title, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where userId = &#63; and title LIKE &#63;.
	 *
	 * @param userId the user ID
	 * @param title the title
	 * @return the number of matching jobs
	 */
	@Override
	public int countByTitle_U(long userId, String title) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE_U;

		Object[] finderArgs = new Object[] { userId, title };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_TITLE_U_USERID_2);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_U_TITLE_1);
			}
			else if (title.equals("")) {
				query.append(_FINDER_COLUMN_TITLE_U_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_U_TITLE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_TITLE_U_USERID_2 = "job.userId = ? AND ";
	private static final String _FINDER_COLUMN_TITLE_U_TITLE_1 = "job.title IS NULL";
	private static final String _FINDER_COLUMN_TITLE_U_TITLE_2 = "job.title LIKE ?";
	private static final String _FINDER_COLUMN_TITLE_U_TITLE_3 = "(job.title IS NULL OR job.title LIKE '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE_G_U =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTitle_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE_G_U =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByTitle_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the jobs where groupId = &#63; and userId = &#63; and title LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByTitle_G_U(long groupId, long userId, String title) {
		return findByTitle_G_U(groupId, userId, title, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where groupId = &#63; and userId = &#63; and title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByTitle_G_U(long groupId, long userId, String title,
		int start, int end) {
		return findByTitle_G_U(groupId, userId, title, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and userId = &#63; and title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByTitle_G_U(long groupId, long userId, String title,
		int start, int end, OrderByComparator<Job> orderByComparator) {
		return findByTitle_G_U(groupId, userId, title, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and userId = &#63; and title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByTitle_G_U(long groupId, long userId, String title,
		int start, int end, OrderByComparator<Job> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TITLE_G_U;
		finderArgs = new Object[] {
				groupId, userId, title,
				
				start, end, orderByComparator
			};

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((groupId != job.getGroupId()) ||
							(userId != job.getUserId()) ||
							!StringUtil.wildcardMatches(job.getTitle(), title,
								'_', '%', '\\', true)) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_TITLE_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_TITLE_G_U_USERID_2);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_G_U_TITLE_1);
			}
			else if (title.equals("")) {
				query.append(_FINDER_COLUMN_TITLE_G_U_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_G_U_TITLE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (bindTitle) {
					qPos.add(title);
				}

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where groupId = &#63; and userId = &#63; and title LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByTitle_G_U_First(long groupId, long userId, String title,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByTitle_G_U_First(groupId, userId, title,
				orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", title=");
		msg.append(title);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where groupId = &#63; and userId = &#63; and title LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByTitle_G_U_First(long groupId, long userId, String title,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByTitle_G_U(groupId, userId, title, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and userId = &#63; and title LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByTitle_G_U_Last(long groupId, long userId, String title,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByTitle_G_U_Last(groupId, userId, title,
				orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", title=");
		msg.append(title);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and userId = &#63; and title LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByTitle_G_U_Last(long groupId, long userId, String title,
		OrderByComparator<Job> orderByComparator) {
		int count = countByTitle_G_U(groupId, userId, title);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByTitle_G_U(groupId, userId, title, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where groupId = &#63; and userId = &#63; and title LIKE &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByTitle_G_U_PrevAndNext(long jobId, long groupId,
		long userId, String title, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByTitle_G_U_PrevAndNext(session, job, groupId,
					userId, title, orderByComparator, true);

			array[1] = job;

			array[2] = getByTitle_G_U_PrevAndNext(session, job, groupId,
					userId, title, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Job getByTitle_G_U_PrevAndNext(Session session, Job job,
		long groupId, long userId, String title,
		OrderByComparator<Job> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		query.append(_FINDER_COLUMN_TITLE_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_TITLE_G_U_USERID_2);

		boolean bindTitle = false;

		if (title == null) {
			query.append(_FINDER_COLUMN_TITLE_G_U_TITLE_1);
		}
		else if (title.equals("")) {
			query.append(_FINDER_COLUMN_TITLE_G_U_TITLE_3);
		}
		else {
			bindTitle = true;

			query.append(_FINDER_COLUMN_TITLE_G_U_TITLE_2);
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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (bindTitle) {
			qPos.add(title);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where groupId = &#63; and userId = &#63; and title LIKE &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 */
	@Override
	public void removeByTitle_G_U(long groupId, long userId, String title) {
		for (Job job : findByTitle_G_U(groupId, userId, title,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where groupId = &#63; and userId = &#63; and title LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @return the number of matching jobs
	 */
	@Override
	public int countByTitle_G_U(long groupId, long userId, String title) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_TITLE_G_U;

		Object[] finderArgs = new Object[] { groupId, userId, title };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_TITLE_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_TITLE_G_U_USERID_2);

			boolean bindTitle = false;

			if (title == null) {
				query.append(_FINDER_COLUMN_TITLE_G_U_TITLE_1);
			}
			else if (title.equals("")) {
				query.append(_FINDER_COLUMN_TITLE_G_U_TITLE_3);
			}
			else {
				bindTitle = true;

				query.append(_FINDER_COLUMN_TITLE_G_U_TITLE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_TITLE_G_U_GROUPID_2 = "job.groupId = ? AND ";
	private static final String _FINDER_COLUMN_TITLE_G_U_USERID_2 = "job.userId = ? AND ";
	private static final String _FINDER_COLUMN_TITLE_G_U_TITLE_1 = "job.title IS NULL";
	private static final String _FINDER_COLUMN_TITLE_G_U_TITLE_2 = "job.title LIKE ?";
	private static final String _FINDER_COLUMN_TITLE_G_U_TITLE_3 = "(job.title IS NULL OR job.title LIKE '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCHEDULERID =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySchedulerId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySchedulerId",
			new String[] { Long.class.getName() },
			JobModelImpl.SCHEDULERID_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCHEDULERID = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySchedulerId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the jobs where schedulerId = &#63;.
	 *
	 * @param schedulerId the scheduler ID
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findBySchedulerId(long schedulerId) {
		return findBySchedulerId(schedulerId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where schedulerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param schedulerId the scheduler ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findBySchedulerId(long schedulerId, int start, int end) {
		return findBySchedulerId(schedulerId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where schedulerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param schedulerId the scheduler ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findBySchedulerId(long schedulerId, int start, int end,
		OrderByComparator<Job> orderByComparator) {
		return findBySchedulerId(schedulerId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the jobs where schedulerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param schedulerId the scheduler ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findBySchedulerId(long schedulerId, int start, int end,
		OrderByComparator<Job> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID;
			finderArgs = new Object[] { schedulerId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SCHEDULERID;
			finderArgs = new Object[] { schedulerId, start, end, orderByComparator };
		}

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((schedulerId != job.getSchedulerId())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_SCHEDULERID_SCHEDULERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(schedulerId);

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where schedulerId = &#63;.
	 *
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findBySchedulerId_First(long schedulerId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchBySchedulerId_First(schedulerId, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("schedulerId=");
		msg.append(schedulerId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where schedulerId = &#63;.
	 *
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchBySchedulerId_First(long schedulerId,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findBySchedulerId(schedulerId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where schedulerId = &#63;.
	 *
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findBySchedulerId_Last(long schedulerId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchBySchedulerId_Last(schedulerId, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("schedulerId=");
		msg.append(schedulerId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where schedulerId = &#63;.
	 *
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchBySchedulerId_Last(long schedulerId,
		OrderByComparator<Job> orderByComparator) {
		int count = countBySchedulerId(schedulerId);

		if (count == 0) {
			return null;
		}

		List<Job> list = findBySchedulerId(schedulerId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where schedulerId = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findBySchedulerId_PrevAndNext(long jobId, long schedulerId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getBySchedulerId_PrevAndNext(session, job, schedulerId,
					orderByComparator, true);

			array[1] = job;

			array[2] = getBySchedulerId_PrevAndNext(session, job, schedulerId,
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

	protected Job getBySchedulerId_PrevAndNext(Session session, Job job,
		long schedulerId, OrderByComparator<Job> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		query.append(_FINDER_COLUMN_SCHEDULERID_SCHEDULERID_2);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(schedulerId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where schedulerId = &#63; from the database.
	 *
	 * @param schedulerId the scheduler ID
	 */
	@Override
	public void removeBySchedulerId(long schedulerId) {
		for (Job job : findBySchedulerId(schedulerId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where schedulerId = &#63;.
	 *
	 * @param schedulerId the scheduler ID
	 * @return the number of matching jobs
	 */
	@Override
	public int countBySchedulerId(long schedulerId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SCHEDULERID;

		Object[] finderArgs = new Object[] { schedulerId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_SCHEDULERID_SCHEDULERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(schedulerId);

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

	private static final String _FINDER_COLUMN_SCHEDULERID_SCHEDULERID_2 = "job.schedulerId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCHEDULERID_G =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySchedulerId_G",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_G =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySchedulerId_G",
			new String[] { Long.class.getName(), Long.class.getName() },
			JobModelImpl.GROUPID_COLUMN_BITMASK |
			JobModelImpl.SCHEDULERID_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCHEDULERID_G = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySchedulerId_G",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the jobs where groupId = &#63; and schedulerId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param schedulerId the scheduler ID
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findBySchedulerId_G(long groupId, long schedulerId) {
		return findBySchedulerId_G(groupId, schedulerId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where groupId = &#63; and schedulerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param schedulerId the scheduler ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findBySchedulerId_G(long groupId, long schedulerId,
		int start, int end) {
		return findBySchedulerId_G(groupId, schedulerId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and schedulerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param schedulerId the scheduler ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findBySchedulerId_G(long groupId, long schedulerId,
		int start, int end, OrderByComparator<Job> orderByComparator) {
		return findBySchedulerId_G(groupId, schedulerId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and schedulerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param schedulerId the scheduler ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findBySchedulerId_G(long groupId, long schedulerId,
		int start, int end, OrderByComparator<Job> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_G;
			finderArgs = new Object[] { groupId, schedulerId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SCHEDULERID_G;
			finderArgs = new Object[] {
					groupId, schedulerId,
					
					start, end, orderByComparator
				};
		}

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((groupId != job.getGroupId()) ||
							(schedulerId != job.getSchedulerId())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_SCHEDULERID_G_GROUPID_2);

			query.append(_FINDER_COLUMN_SCHEDULERID_G_SCHEDULERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(schedulerId);

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where groupId = &#63; and schedulerId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findBySchedulerId_G_First(long groupId, long schedulerId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchBySchedulerId_G_First(groupId, schedulerId,
				orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", schedulerId=");
		msg.append(schedulerId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where groupId = &#63; and schedulerId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchBySchedulerId_G_First(long groupId, long schedulerId,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findBySchedulerId_G(groupId, schedulerId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and schedulerId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findBySchedulerId_G_Last(long groupId, long schedulerId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchBySchedulerId_G_Last(groupId, schedulerId,
				orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", schedulerId=");
		msg.append(schedulerId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and schedulerId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchBySchedulerId_G_Last(long groupId, long schedulerId,
		OrderByComparator<Job> orderByComparator) {
		int count = countBySchedulerId_G(groupId, schedulerId);

		if (count == 0) {
			return null;
		}

		List<Job> list = findBySchedulerId_G(groupId, schedulerId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where groupId = &#63; and schedulerId = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param groupId the group ID
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findBySchedulerId_G_PrevAndNext(long jobId, long groupId,
		long schedulerId, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getBySchedulerId_G_PrevAndNext(session, job, groupId,
					schedulerId, orderByComparator, true);

			array[1] = job;

			array[2] = getBySchedulerId_G_PrevAndNext(session, job, groupId,
					schedulerId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Job getBySchedulerId_G_PrevAndNext(Session session, Job job,
		long groupId, long schedulerId,
		OrderByComparator<Job> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		query.append(_FINDER_COLUMN_SCHEDULERID_G_GROUPID_2);

		query.append(_FINDER_COLUMN_SCHEDULERID_G_SCHEDULERID_2);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(schedulerId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where groupId = &#63; and schedulerId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param schedulerId the scheduler ID
	 */
	@Override
	public void removeBySchedulerId_G(long groupId, long schedulerId) {
		for (Job job : findBySchedulerId_G(groupId, schedulerId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where groupId = &#63; and schedulerId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param schedulerId the scheduler ID
	 * @return the number of matching jobs
	 */
	@Override
	public int countBySchedulerId_G(long groupId, long schedulerId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SCHEDULERID_G;

		Object[] finderArgs = new Object[] { groupId, schedulerId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_SCHEDULERID_G_GROUPID_2);

			query.append(_FINDER_COLUMN_SCHEDULERID_G_SCHEDULERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(schedulerId);

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

	private static final String _FINDER_COLUMN_SCHEDULERID_G_GROUPID_2 = "job.groupId = ? AND ";
	private static final String _FINDER_COLUMN_SCHEDULERID_G_SCHEDULERID_2 = "job.schedulerId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCHEDULERID_U =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySchedulerId_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_U =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySchedulerId_U",
			new String[] { Long.class.getName(), Long.class.getName() },
			JobModelImpl.USERID_COLUMN_BITMASK |
			JobModelImpl.SCHEDULERID_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCHEDULERID_U = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySchedulerId_U",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the jobs where userId = &#63; and schedulerId = &#63;.
	 *
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findBySchedulerId_U(long userId, long schedulerId) {
		return findBySchedulerId_U(userId, schedulerId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where userId = &#63; and schedulerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findBySchedulerId_U(long userId, long schedulerId,
		int start, int end) {
		return findBySchedulerId_U(userId, schedulerId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where userId = &#63; and schedulerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findBySchedulerId_U(long userId, long schedulerId,
		int start, int end, OrderByComparator<Job> orderByComparator) {
		return findBySchedulerId_U(userId, schedulerId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where userId = &#63; and schedulerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findBySchedulerId_U(long userId, long schedulerId,
		int start, int end, OrderByComparator<Job> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_U;
			finderArgs = new Object[] { userId, schedulerId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SCHEDULERID_U;
			finderArgs = new Object[] {
					userId, schedulerId,
					
					start, end, orderByComparator
				};
		}

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((userId != job.getUserId()) ||
							(schedulerId != job.getSchedulerId())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_SCHEDULERID_U_USERID_2);

			query.append(_FINDER_COLUMN_SCHEDULERID_U_SCHEDULERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(schedulerId);

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where userId = &#63; and schedulerId = &#63;.
	 *
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findBySchedulerId_U_First(long userId, long schedulerId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchBySchedulerId_U_First(userId, schedulerId,
				orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", schedulerId=");
		msg.append(schedulerId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where userId = &#63; and schedulerId = &#63;.
	 *
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchBySchedulerId_U_First(long userId, long schedulerId,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findBySchedulerId_U(userId, schedulerId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where userId = &#63; and schedulerId = &#63;.
	 *
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findBySchedulerId_U_Last(long userId, long schedulerId,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchBySchedulerId_U_Last(userId, schedulerId,
				orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", schedulerId=");
		msg.append(schedulerId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where userId = &#63; and schedulerId = &#63;.
	 *
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchBySchedulerId_U_Last(long userId, long schedulerId,
		OrderByComparator<Job> orderByComparator) {
		int count = countBySchedulerId_U(userId, schedulerId);

		if (count == 0) {
			return null;
		}

		List<Job> list = findBySchedulerId_U(userId, schedulerId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where userId = &#63; and schedulerId = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findBySchedulerId_U_PrevAndNext(long jobId, long userId,
		long schedulerId, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getBySchedulerId_U_PrevAndNext(session, job, userId,
					schedulerId, orderByComparator, true);

			array[1] = job;

			array[2] = getBySchedulerId_U_PrevAndNext(session, job, userId,
					schedulerId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Job getBySchedulerId_U_PrevAndNext(Session session, Job job,
		long userId, long schedulerId,
		OrderByComparator<Job> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		query.append(_FINDER_COLUMN_SCHEDULERID_U_USERID_2);

		query.append(_FINDER_COLUMN_SCHEDULERID_U_SCHEDULERID_2);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(schedulerId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where userId = &#63; and schedulerId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 */
	@Override
	public void removeBySchedulerId_U(long userId, long schedulerId) {
		for (Job job : findBySchedulerId_U(userId, schedulerId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where userId = &#63; and schedulerId = &#63;.
	 *
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @return the number of matching jobs
	 */
	@Override
	public int countBySchedulerId_U(long userId, long schedulerId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SCHEDULERID_U;

		Object[] finderArgs = new Object[] { userId, schedulerId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_SCHEDULERID_U_USERID_2);

			query.append(_FINDER_COLUMN_SCHEDULERID_U_SCHEDULERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(schedulerId);

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

	private static final String _FINDER_COLUMN_SCHEDULERID_U_USERID_2 = "job.userId = ? AND ";
	private static final String _FINDER_COLUMN_SCHEDULERID_U_SCHEDULERID_2 = "job.schedulerId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCHEDULERID_G_U =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySchedulerId_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_G_U =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySchedulerId_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			JobModelImpl.GROUPID_COLUMN_BITMASK |
			JobModelImpl.USERID_COLUMN_BITMASK |
			JobModelImpl.SCHEDULERID_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCHEDULERID_G_U = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySchedulerId_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the jobs where groupId = &#63; and userId = &#63; and schedulerId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findBySchedulerId_G_U(long groupId, long userId,
		long schedulerId) {
		return findBySchedulerId_G_U(groupId, userId, schedulerId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where groupId = &#63; and userId = &#63; and schedulerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findBySchedulerId_G_U(long groupId, long userId,
		long schedulerId, int start, int end) {
		return findBySchedulerId_G_U(groupId, userId, schedulerId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and userId = &#63; and schedulerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findBySchedulerId_G_U(long groupId, long userId,
		long schedulerId, int start, int end,
		OrderByComparator<Job> orderByComparator) {
		return findBySchedulerId_G_U(groupId, userId, schedulerId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and userId = &#63; and schedulerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findBySchedulerId_G_U(long groupId, long userId,
		long schedulerId, int start, int end,
		OrderByComparator<Job> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_G_U;
			finderArgs = new Object[] { groupId, userId, schedulerId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SCHEDULERID_G_U;
			finderArgs = new Object[] {
					groupId, userId, schedulerId,
					
					start, end, orderByComparator
				};
		}

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((groupId != job.getGroupId()) ||
							(userId != job.getUserId()) ||
							(schedulerId != job.getSchedulerId())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

			query.append(_FINDER_COLUMN_SCHEDULERID_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_SCHEDULERID_G_U_USERID_2);

			query.append(_FINDER_COLUMN_SCHEDULERID_G_U_SCHEDULERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JobModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(schedulerId);

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where groupId = &#63; and userId = &#63; and schedulerId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findBySchedulerId_G_U_First(long groupId, long userId,
		long schedulerId, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = fetchBySchedulerId_G_U_First(groupId, userId, schedulerId,
				orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", schedulerId=");
		msg.append(schedulerId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where groupId = &#63; and userId = &#63; and schedulerId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchBySchedulerId_G_U_First(long groupId, long userId,
		long schedulerId, OrderByComparator<Job> orderByComparator) {
		List<Job> list = findBySchedulerId_G_U(groupId, userId, schedulerId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and userId = &#63; and schedulerId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findBySchedulerId_G_U_Last(long groupId, long userId,
		long schedulerId, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = fetchBySchedulerId_G_U_Last(groupId, userId, schedulerId,
				orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", schedulerId=");
		msg.append(schedulerId);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and userId = &#63; and schedulerId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchBySchedulerId_G_U_Last(long groupId, long userId,
		long schedulerId, OrderByComparator<Job> orderByComparator) {
		int count = countBySchedulerId_G_U(groupId, userId, schedulerId);

		if (count == 0) {
			return null;
		}

		List<Job> list = findBySchedulerId_G_U(groupId, userId, schedulerId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where groupId = &#63; and userId = &#63; and schedulerId = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findBySchedulerId_G_U_PrevAndNext(long jobId, long groupId,
		long userId, long schedulerId, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getBySchedulerId_G_U_PrevAndNext(session, job, groupId,
					userId, schedulerId, orderByComparator, true);

			array[1] = job;

			array[2] = getBySchedulerId_G_U_PrevAndNext(session, job, groupId,
					userId, schedulerId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Job getBySchedulerId_G_U_PrevAndNext(Session session, Job job,
		long groupId, long userId, long schedulerId,
		OrderByComparator<Job> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

		query.append(_FINDER_COLUMN_SCHEDULERID_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_SCHEDULERID_G_U_USERID_2);

		query.append(_FINDER_COLUMN_SCHEDULERID_G_U_SCHEDULERID_2);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		qPos.add(schedulerId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where groupId = &#63; and userId = &#63; and schedulerId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 */
	@Override
	public void removeBySchedulerId_G_U(long groupId, long userId,
		long schedulerId) {
		for (Job job : findBySchedulerId_G_U(groupId, userId, schedulerId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where groupId = &#63; and userId = &#63; and schedulerId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param schedulerId the scheduler ID
	 * @return the number of matching jobs
	 */
	@Override
	public int countBySchedulerId_G_U(long groupId, long userId,
		long schedulerId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SCHEDULERID_G_U;

		Object[] finderArgs = new Object[] { groupId, userId, schedulerId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_JOB_WHERE);

			query.append(_FINDER_COLUMN_SCHEDULERID_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_SCHEDULERID_G_U_USERID_2);

			query.append(_FINDER_COLUMN_SCHEDULERID_G_U_SCHEDULERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(schedulerId);

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

	private static final String _FINDER_COLUMN_SCHEDULERID_G_U_GROUPID_2 = "job.groupId = ? AND ";
	private static final String _FINDER_COLUMN_SCHEDULERID_G_U_USERID_2 = "job.userId = ? AND ";
	private static final String _FINDER_COLUMN_SCHEDULERID_G_U_SCHEDULERID_2 = "job.schedulerId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RUNTYPE = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRunType",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRunType",
			new String[] { String.class.getName() },
			JobModelImpl.RUNTYPE_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RUNTYPE = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRunType",
			new String[] { String.class.getName() });

	/**
	 * Returns all the jobs where runType = &#63;.
	 *
	 * @param runType the run type
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByRunType(String runType) {
		return findByRunType(runType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param runType the run type
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByRunType(String runType, int start, int end) {
		return findByRunType(runType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param runType the run type
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByRunType(String runType, int start, int end,
		OrderByComparator<Job> orderByComparator) {
		return findByRunType(runType, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param runType the run type
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByRunType(String runType, int start, int end,
		OrderByComparator<Job> orderByComparator, boolean retrieveFromCache) {
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

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if (!Objects.equals(runType, job.getRunType())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

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
				query.append(JobModelImpl.ORDER_BY_JPQL);
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
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where runType = &#63;.
	 *
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByRunType_First(String runType,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByRunType_First(runType, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("runType=");
		msg.append(runType);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where runType = &#63;.
	 *
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByRunType_First(String runType,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByRunType(runType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where runType = &#63;.
	 *
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByRunType_Last(String runType,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByRunType_Last(runType, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("runType=");
		msg.append(runType);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where runType = &#63;.
	 *
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByRunType_Last(String runType,
		OrderByComparator<Job> orderByComparator) {
		int count = countByRunType(runType);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByRunType(runType, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where runType = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByRunType_PrevAndNext(long jobId, String runType,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByRunType_PrevAndNext(session, job, runType,
					orderByComparator, true);

			array[1] = job;

			array[2] = getByRunType_PrevAndNext(session, job, runType,
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

	protected Job getByRunType_PrevAndNext(Session session, Job job,
		String runType, OrderByComparator<Job> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where runType = &#63; from the database.
	 *
	 * @param runType the run type
	 */
	@Override
	public void removeByRunType(String runType) {
		for (Job job : findByRunType(runType, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where runType = &#63;.
	 *
	 * @param runType the run type
	 * @return the number of matching jobs
	 */
	@Override
	public int countByRunType(String runType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RUNTYPE;

		Object[] finderArgs = new Object[] { runType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JOB_WHERE);

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

	private static final String _FINDER_COLUMN_RUNTYPE_RUNTYPE_1 = "job.runType IS NULL";
	private static final String _FINDER_COLUMN_RUNTYPE_RUNTYPE_2 = "job.runType = ?";
	private static final String _FINDER_COLUMN_RUNTYPE_RUNTYPE_3 = "(job.runType IS NULL OR job.runType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RUNTYPE_G =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRunType_G",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRunType_G",
			new String[] { Long.class.getName(), String.class.getName() },
			JobModelImpl.GROUPID_COLUMN_BITMASK |
			JobModelImpl.RUNTYPE_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RUNTYPE_G = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRunType_G",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the jobs where groupId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByRunType_G(long groupId, String runType) {
		return findByRunType_G(groupId, runType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where groupId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByRunType_G(long groupId, String runType, int start,
		int end) {
		return findByRunType_G(groupId, runType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByRunType_G(long groupId, String runType, int start,
		int end, OrderByComparator<Job> orderByComparator) {
		return findByRunType_G(groupId, runType, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByRunType_G(long groupId, String runType, int start,
		int end, OrderByComparator<Job> orderByComparator,
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

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((groupId != job.getGroupId()) ||
							!Objects.equals(runType, job.getRunType())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

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
				query.append(JobModelImpl.ORDER_BY_JPQL);
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
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where groupId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByRunType_G_First(long groupId, String runType,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByRunType_G_First(groupId, runType, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", runType=");
		msg.append(runType);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where groupId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByRunType_G_First(long groupId, String runType,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByRunType_G(groupId, runType, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByRunType_G_Last(long groupId, String runType,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByRunType_G_Last(groupId, runType, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", runType=");
		msg.append(runType);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByRunType_G_Last(long groupId, String runType,
		OrderByComparator<Job> orderByComparator) {
		int count = countByRunType_G(groupId, runType);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByRunType_G(groupId, runType, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where groupId = &#63; and runType = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param groupId the group ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByRunType_G_PrevAndNext(long jobId, long groupId,
		String runType, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByRunType_G_PrevAndNext(session, job, groupId,
					runType, orderByComparator, true);

			array[1] = job;

			array[2] = getByRunType_G_PrevAndNext(session, job, groupId,
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

	protected Job getByRunType_G_PrevAndNext(Session session, Job job,
		long groupId, String runType, OrderByComparator<Job> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where groupId = &#63; and runType = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 */
	@Override
	public void removeByRunType_G(long groupId, String runType) {
		for (Job job : findByRunType_G(groupId, runType, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where groupId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param runType the run type
	 * @return the number of matching jobs
	 */
	@Override
	public int countByRunType_G(long groupId, String runType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RUNTYPE_G;

		Object[] finderArgs = new Object[] { groupId, runType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOB_WHERE);

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

	private static final String _FINDER_COLUMN_RUNTYPE_G_GROUPID_2 = "job.groupId = ? AND ";
	private static final String _FINDER_COLUMN_RUNTYPE_G_RUNTYPE_1 = "job.runType IS NULL";
	private static final String _FINDER_COLUMN_RUNTYPE_G_RUNTYPE_2 = "job.runType = ?";
	private static final String _FINDER_COLUMN_RUNTYPE_G_RUNTYPE_3 = "(job.runType IS NULL OR job.runType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RUNTYPE_U =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRunType_U",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_U =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRunType_U",
			new String[] { Long.class.getName(), String.class.getName() },
			JobModelImpl.USERID_COLUMN_BITMASK |
			JobModelImpl.RUNTYPE_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RUNTYPE_U = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRunType_U",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the jobs where userId = &#63; and runType = &#63;.
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByRunType_U(long userId, String runType) {
		return findByRunType_U(userId, runType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where userId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByRunType_U(long userId, String runType, int start,
		int end) {
		return findByRunType_U(userId, runType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where userId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByRunType_U(long userId, String runType, int start,
		int end, OrderByComparator<Job> orderByComparator) {
		return findByRunType_U(userId, runType, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the jobs where userId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByRunType_U(long userId, String runType, int start,
		int end, OrderByComparator<Job> orderByComparator,
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

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((userId != job.getUserId()) ||
							!Objects.equals(runType, job.getRunType())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

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
				query.append(JobModelImpl.ORDER_BY_JPQL);
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
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where userId = &#63; and runType = &#63;.
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByRunType_U_First(long userId, String runType,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByRunType_U_First(userId, runType, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", runType=");
		msg.append(runType);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where userId = &#63; and runType = &#63;.
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByRunType_U_First(long userId, String runType,
		OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByRunType_U(userId, runType, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where userId = &#63; and runType = &#63;.
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByRunType_U_Last(long userId, String runType,
		OrderByComparator<Job> orderByComparator) throws NoSuchJobException {
		Job job = fetchByRunType_U_Last(userId, runType, orderByComparator);

		if (job != null) {
			return job;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", runType=");
		msg.append(runType);

		msg.append("}");

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where userId = &#63; and runType = &#63;.
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByRunType_U_Last(long userId, String runType,
		OrderByComparator<Job> orderByComparator) {
		int count = countByRunType_U(userId, runType);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByRunType_U(userId, runType, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where userId = &#63; and runType = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByRunType_U_PrevAndNext(long jobId, long userId,
		String runType, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByRunType_U_PrevAndNext(session, job, userId,
					runType, orderByComparator, true);

			array[1] = job;

			array[2] = getByRunType_U_PrevAndNext(session, job, userId,
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

	protected Job getByRunType_U_PrevAndNext(Session session, Job job,
		long userId, String runType, OrderByComparator<Job> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where userId = &#63; and runType = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 */
	@Override
	public void removeByRunType_U(long userId, String runType) {
		for (Job job : findByRunType_U(userId, runType, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where userId = &#63; and runType = &#63;.
	 *
	 * @param userId the user ID
	 * @param runType the run type
	 * @return the number of matching jobs
	 */
	@Override
	public int countByRunType_U(long userId, String runType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RUNTYPE_U;

		Object[] finderArgs = new Object[] { userId, runType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOB_WHERE);

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

	private static final String _FINDER_COLUMN_RUNTYPE_U_USERID_2 = "job.userId = ? AND ";
	private static final String _FINDER_COLUMN_RUNTYPE_U_RUNTYPE_1 = "job.runType IS NULL";
	private static final String _FINDER_COLUMN_RUNTYPE_U_RUNTYPE_2 = "job.runType = ?";
	private static final String _FINDER_COLUMN_RUNTYPE_U_RUNTYPE_3 = "(job.runType IS NULL OR job.runType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RUNTYPE_G_U =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRunType_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G_U =
		new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, JobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRunType_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			JobModelImpl.GROUPID_COLUMN_BITMASK |
			JobModelImpl.USERID_COLUMN_BITMASK |
			JobModelImpl.RUNTYPE_COLUMN_BITMASK |
			JobModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RUNTYPE_G_U = new FinderPath(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRunType_G_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the jobs where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @return the matching jobs
	 */
	@Override
	public List<Job> findByRunType_G_U(long groupId, long userId, String runType) {
		return findByRunType_G_U(groupId, userId, runType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of matching jobs
	 */
	@Override
	public List<Job> findByRunType_G_U(long groupId, long userId,
		String runType, int start, int end) {
		return findByRunType_G_U(groupId, userId, runType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByRunType_G_U(long groupId, long userId,
		String runType, int start, int end,
		OrderByComparator<Job> orderByComparator) {
		return findByRunType_G_U(groupId, userId, runType, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jobs
	 */
	@Override
	public List<Job> findByRunType_G_U(long groupId, long userId,
		String runType, int start, int end,
		OrderByComparator<Job> orderByComparator, boolean retrieveFromCache) {
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

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Job job : list) {
					if ((groupId != job.getGroupId()) ||
							(userId != job.getUserId()) ||
							!Objects.equals(runType, job.getRunType())) {
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

			query.append(_SQL_SELECT_JOB_WHERE);

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
				query.append(JobModelImpl.ORDER_BY_JPQL);
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
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first job in the ordered set where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByRunType_G_U_First(long groupId, long userId,
		String runType, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = fetchByRunType_G_U_First(groupId, userId, runType,
				orderByComparator);

		if (job != null) {
			return job;
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

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the first job in the ordered set where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByRunType_G_U_First(long groupId, long userId,
		String runType, OrderByComparator<Job> orderByComparator) {
		List<Job> list = findByRunType_G_U(groupId, userId, runType, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job
	 * @throws NoSuchJobException if a matching job could not be found
	 */
	@Override
	public Job findByRunType_G_U_Last(long groupId, long userId,
		String runType, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = fetchByRunType_G_U_Last(groupId, userId, runType,
				orderByComparator);

		if (job != null) {
			return job;
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

		throw new NoSuchJobException(msg.toString());
	}

	/**
	 * Returns the last job in the ordered set where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job, or <code>null</code> if a matching job could not be found
	 */
	@Override
	public Job fetchByRunType_G_U_Last(long groupId, long userId,
		String runType, OrderByComparator<Job> orderByComparator) {
		int count = countByRunType_G_U(groupId, userId, runType);

		if (count == 0) {
			return null;
		}

		List<Job> list = findByRunType_G_U(groupId, userId, runType, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jobs before and after the current job in the ordered set where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * @param jobId the primary key of the current job
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job[] findByRunType_G_U_PrevAndNext(long jobId, long groupId,
		long userId, String runType, OrderByComparator<Job> orderByComparator)
		throws NoSuchJobException {
		Job job = findByPrimaryKey(jobId);

		Session session = null;

		try {
			session = openSession();

			Job[] array = new JobImpl[3];

			array[0] = getByRunType_G_U_PrevAndNext(session, job, groupId,
					userId, runType, orderByComparator, true);

			array[1] = job;

			array[2] = getByRunType_G_U_PrevAndNext(session, job, groupId,
					userId, runType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Job getByRunType_G_U_PrevAndNext(Session session, Job job,
		long groupId, long userId, String runType,
		OrderByComparator<Job> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_JOB_WHERE);

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
			query.append(JobModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(job);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Job> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jobs where groupId = &#63; and userId = &#63; and runType = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 */
	@Override
	public void removeByRunType_G_U(long groupId, long userId, String runType) {
		for (Job job : findByRunType_G_U(groupId, userId, runType,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs where groupId = &#63; and userId = &#63; and runType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param runType the run type
	 * @return the number of matching jobs
	 */
	@Override
	public int countByRunType_G_U(long groupId, long userId, String runType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RUNTYPE_G_U;

		Object[] finderArgs = new Object[] { groupId, userId, runType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_JOB_WHERE);

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

	private static final String _FINDER_COLUMN_RUNTYPE_G_U_GROUPID_2 = "job.groupId = ? AND ";
	private static final String _FINDER_COLUMN_RUNTYPE_G_U_USERID_2 = "job.userId = ? AND ";
	private static final String _FINDER_COLUMN_RUNTYPE_G_U_RUNTYPE_1 = "job.runType IS NULL";
	private static final String _FINDER_COLUMN_RUNTYPE_G_U_RUNTYPE_2 = "job.runType = ?";
	private static final String _FINDER_COLUMN_RUNTYPE_G_U_RUNTYPE_3 = "(job.runType IS NULL OR job.runType = '')";

	public JobPersistenceImpl() {
		setModelClass(Job.class);

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
	 * Caches the job in the entity cache if it is enabled.
	 *
	 * @param job the job
	 */
	@Override
	public void cacheResult(Job job) {
		entityCache.putResult(JobModelImpl.ENTITY_CACHE_ENABLED, JobImpl.class,
			job.getPrimaryKey(), job);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { job.getUuid(), job.getGroupId() }, job);

		finderCache.putResult(FINDER_PATH_FETCH_BY_SIMULATIONJOBID,
			new Object[] { job.getSimulationId(), job.getJobId() }, job);

		job.resetOriginalValues();
	}

	/**
	 * Caches the jobs in the entity cache if it is enabled.
	 *
	 * @param jobs the jobs
	 */
	@Override
	public void cacheResult(List<Job> jobs) {
		for (Job job : jobs) {
			if (entityCache.getResult(JobModelImpl.ENTITY_CACHE_ENABLED,
						JobImpl.class, job.getPrimaryKey()) == null) {
				cacheResult(job);
			}
			else {
				job.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all jobs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(JobImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the job.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Job job) {
		entityCache.removeResult(JobModelImpl.ENTITY_CACHE_ENABLED,
			JobImpl.class, job.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((JobModelImpl)job, true);
	}

	@Override
	public void clearCache(List<Job> jobs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Job job : jobs) {
			entityCache.removeResult(JobModelImpl.ENTITY_CACHE_ENABLED,
				JobImpl.class, job.getPrimaryKey());

			clearUniqueFindersCache((JobModelImpl)job, true);
		}
	}

	protected void cacheUniqueFindersCache(JobModelImpl jobModelImpl) {
		Object[] args = new Object[] {
				jobModelImpl.getUuid(), jobModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args, jobModelImpl,
			false);

		args = new Object[] {
				jobModelImpl.getSimulationId(), jobModelImpl.getJobId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_SIMULATIONJOBID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_SIMULATIONJOBID, args,
			jobModelImpl, false);
	}

	protected void clearUniqueFindersCache(JobModelImpl jobModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					jobModelImpl.getUuid(), jobModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((jobModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					jobModelImpl.getOriginalUuid(),
					jobModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					jobModelImpl.getSimulationId(), jobModelImpl.getJobId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONJOBID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SIMULATIONJOBID, args);
		}

		if ((jobModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SIMULATIONJOBID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					jobModelImpl.getOriginalSimulationId(),
					jobModelImpl.getOriginalJobId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONJOBID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SIMULATIONJOBID, args);
		}
	}

	/**
	 * Creates a new job with the primary key. Does not add the job to the database.
	 *
	 * @param jobId the primary key for the new job
	 * @return the new job
	 */
	@Override
	public Job create(long jobId) {
		Job job = new JobImpl();

		job.setNew(true);
		job.setPrimaryKey(jobId);

		String uuid = PortalUUIDUtil.generate();

		job.setUuid(uuid);

		job.setCompanyId(companyProvider.getCompanyId());

		return job;
	}

	/**
	 * Removes the job with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jobId the primary key of the job
	 * @return the job that was removed
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job remove(long jobId) throws NoSuchJobException {
		return remove((Serializable)jobId);
	}

	/**
	 * Removes the job with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the job
	 * @return the job that was removed
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job remove(Serializable primaryKey) throws NoSuchJobException {
		Session session = null;

		try {
			session = openSession();

			Job job = (Job)session.get(JobImpl.class, primaryKey);

			if (job == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJobException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(job);
		}
		catch (NoSuchJobException nsee) {
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
	protected Job removeImpl(Job job) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(job)) {
				job = (Job)session.get(JobImpl.class, job.getPrimaryKeyObj());
			}

			if (job != null) {
				session.delete(job);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (job != null) {
			clearCache(job);
		}

		return job;
	}

	@Override
	public Job updateImpl(Job job) {
		boolean isNew = job.isNew();

		if (!(job instanceof JobModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(job.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(job);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in job proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Job implementation " +
				job.getClass());
		}

		JobModelImpl jobModelImpl = (JobModelImpl)job;

		if (Validator.isNull(job.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			job.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (job.getCreateDate() == null)) {
			if (serviceContext == null) {
				job.setCreateDate(now);
			}
			else {
				job.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!jobModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				job.setModifiedDate(now);
			}
			else {
				job.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (job.isNew()) {
				session.save(job);

				job.setNew(false);
			}
			else {
				job = (Job)session.merge(job);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!JobModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { jobModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					jobModelImpl.getUuid(), jobModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { jobModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] { jobModelImpl.getUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
				args);

			args = new Object[] {
					jobModelImpl.getGroupId(), jobModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID_G, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_G,
				args);

			args = new Object[] { jobModelImpl.getSimulationId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID,
				args);

			args = new Object[] {
					jobModelImpl.getGroupId(), jobModelImpl.getSimulationId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONID_G, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID_G,
				args);

			args = new Object[] { jobModelImpl.getQueueName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_QUEUENAME, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUEUENAME,
				args);

			args = new Object[] { jobModelImpl.getStatus() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
				args);

			args = new Object[] {
					jobModelImpl.getGroupId(), jobModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS_G, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_G,
				args);

			args = new Object[] {
					jobModelImpl.getUserId(), jobModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS_U, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_U,
				args);

			args = new Object[] {
					jobModelImpl.getGroupId(), jobModelImpl.getUserId(),
					jobModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS_G_U, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_G_U,
				args);

			args = new Object[] { jobModelImpl.getJobStatus() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_JOBSTATUS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS,
				args);

			args = new Object[] {
					jobModelImpl.getGroupId(), jobModelImpl.getJobStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_JOBSTATUS_G, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_G,
				args);

			args = new Object[] {
					jobModelImpl.getUserId(), jobModelImpl.getJobStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_JOBSTATUS_U, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_U,
				args);

			args = new Object[] {
					jobModelImpl.getGroupId(), jobModelImpl.getUserId(),
					jobModelImpl.getJobStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_JOBSTATUS_G_U, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_G_U,
				args);

			args = new Object[] { jobModelImpl.getSchedulerId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SCHEDULERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID,
				args);

			args = new Object[] {
					jobModelImpl.getGroupId(), jobModelImpl.getSchedulerId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SCHEDULERID_G, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_G,
				args);

			args = new Object[] {
					jobModelImpl.getUserId(), jobModelImpl.getSchedulerId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SCHEDULERID_U, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_U,
				args);

			args = new Object[] {
					jobModelImpl.getGroupId(), jobModelImpl.getUserId(),
					jobModelImpl.getSchedulerId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SCHEDULERID_G_U, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_G_U,
				args);

			args = new Object[] { jobModelImpl.getRunType() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE,
				args);

			args = new Object[] {
					jobModelImpl.getGroupId(), jobModelImpl.getRunType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_G, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G,
				args);

			args = new Object[] {
					jobModelImpl.getUserId(), jobModelImpl.getRunType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_U, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_U,
				args);

			args = new Object[] {
					jobModelImpl.getGroupId(), jobModelImpl.getUserId(),
					jobModelImpl.getRunType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_G_U, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G_U,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { jobModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { jobModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobModelImpl.getOriginalUuid(),
						jobModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						jobModelImpl.getUuid(), jobModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { jobModelImpl.getOriginalGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { jobModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { jobModelImpl.getOriginalUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { jobModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobModelImpl.getOriginalGroupId(),
						jobModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_G,
					args);

				args = new Object[] {
						jobModelImpl.getGroupId(), jobModelImpl.getUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID_G,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobModelImpl.getOriginalSimulationId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID,
					args);

				args = new Object[] { jobModelImpl.getSimulationId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobModelImpl.getOriginalGroupId(),
						jobModelImpl.getOriginalSimulationId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONID_G,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID_G,
					args);

				args = new Object[] {
						jobModelImpl.getGroupId(),
						jobModelImpl.getSimulationId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONID_G,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID_G,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUEUENAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { jobModelImpl.getOriginalQueueName() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_QUEUENAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUEUENAME,
					args);

				args = new Object[] { jobModelImpl.getQueueName() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_QUEUENAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUEUENAME,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { jobModelImpl.getOriginalStatus() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);

				args = new Object[] { jobModelImpl.getStatus() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobModelImpl.getOriginalGroupId(),
						jobModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_G,
					args);

				args = new Object[] {
						jobModelImpl.getGroupId(), jobModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_G,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobModelImpl.getOriginalUserId(),
						jobModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_U,
					args);

				args = new Object[] {
						jobModelImpl.getUserId(), jobModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_U,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_G_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobModelImpl.getOriginalGroupId(),
						jobModelImpl.getOriginalUserId(),
						jobModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS_G_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_G_U,
					args);

				args = new Object[] {
						jobModelImpl.getGroupId(), jobModelImpl.getUserId(),
						jobModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS_G_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS_G_U,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { jobModelImpl.getOriginalJobStatus() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_JOBSTATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS,
					args);

				args = new Object[] { jobModelImpl.getJobStatus() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_JOBSTATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobModelImpl.getOriginalGroupId(),
						jobModelImpl.getOriginalJobStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_JOBSTATUS_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_G,
					args);

				args = new Object[] {
						jobModelImpl.getGroupId(), jobModelImpl.getJobStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_JOBSTATUS_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_G,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobModelImpl.getOriginalUserId(),
						jobModelImpl.getOriginalJobStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_JOBSTATUS_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_U,
					args);

				args = new Object[] {
						jobModelImpl.getUserId(), jobModelImpl.getJobStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_JOBSTATUS_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_U,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_G_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobModelImpl.getOriginalGroupId(),
						jobModelImpl.getOriginalUserId(),
						jobModelImpl.getOriginalJobStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_JOBSTATUS_G_U,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_G_U,
					args);

				args = new Object[] {
						jobModelImpl.getGroupId(), jobModelImpl.getUserId(),
						jobModelImpl.getJobStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_JOBSTATUS_G_U,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBSTATUS_G_U,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobModelImpl.getOriginalSchedulerId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SCHEDULERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID,
					args);

				args = new Object[] { jobModelImpl.getSchedulerId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SCHEDULERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobModelImpl.getOriginalGroupId(),
						jobModelImpl.getOriginalSchedulerId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SCHEDULERID_G,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_G,
					args);

				args = new Object[] {
						jobModelImpl.getGroupId(), jobModelImpl.getSchedulerId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SCHEDULERID_G,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_G,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobModelImpl.getOriginalUserId(),
						jobModelImpl.getOriginalSchedulerId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SCHEDULERID_U,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_U,
					args);

				args = new Object[] {
						jobModelImpl.getUserId(), jobModelImpl.getSchedulerId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SCHEDULERID_U,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_U,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_G_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobModelImpl.getOriginalGroupId(),
						jobModelImpl.getOriginalUserId(),
						jobModelImpl.getOriginalSchedulerId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SCHEDULERID_G_U,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_G_U,
					args);

				args = new Object[] {
						jobModelImpl.getGroupId(), jobModelImpl.getUserId(),
						jobModelImpl.getSchedulerId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SCHEDULERID_G_U,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCHEDULERID_G_U,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { jobModelImpl.getOriginalRunType() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE,
					args);

				args = new Object[] { jobModelImpl.getRunType() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobModelImpl.getOriginalGroupId(),
						jobModelImpl.getOriginalRunType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G,
					args);

				args = new Object[] {
						jobModelImpl.getGroupId(), jobModelImpl.getRunType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobModelImpl.getOriginalUserId(),
						jobModelImpl.getOriginalRunType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_U,
					args);

				args = new Object[] {
						jobModelImpl.getUserId(), jobModelImpl.getRunType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_U,
					args);
			}

			if ((jobModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobModelImpl.getOriginalGroupId(),
						jobModelImpl.getOriginalUserId(),
						jobModelImpl.getOriginalRunType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_G_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G_U,
					args);

				args = new Object[] {
						jobModelImpl.getGroupId(), jobModelImpl.getUserId(),
						jobModelImpl.getRunType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RUNTYPE_G_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RUNTYPE_G_U,
					args);
			}
		}

		entityCache.putResult(JobModelImpl.ENTITY_CACHE_ENABLED, JobImpl.class,
			job.getPrimaryKey(), job, false);

		clearUniqueFindersCache(jobModelImpl, false);
		cacheUniqueFindersCache(jobModelImpl);

		job.resetOriginalValues();

		return job;
	}

	/**
	 * Returns the job with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the job
	 * @return the job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job findByPrimaryKey(Serializable primaryKey)
		throws NoSuchJobException {
		Job job = fetchByPrimaryKey(primaryKey);

		if (job == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchJobException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return job;
	}

	/**
	 * Returns the job with the primary key or throws a {@link NoSuchJobException} if it could not be found.
	 *
	 * @param jobId the primary key of the job
	 * @return the job
	 * @throws NoSuchJobException if a job with the primary key could not be found
	 */
	@Override
	public Job findByPrimaryKey(long jobId) throws NoSuchJobException {
		return findByPrimaryKey((Serializable)jobId);
	}

	/**
	 * Returns the job with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the job
	 * @return the job, or <code>null</code> if a job with the primary key could not be found
	 */
	@Override
	public Job fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(JobModelImpl.ENTITY_CACHE_ENABLED,
				JobImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Job job = (Job)serializable;

		if (job == null) {
			Session session = null;

			try {
				session = openSession();

				job = (Job)session.get(JobImpl.class, primaryKey);

				if (job != null) {
					cacheResult(job);
				}
				else {
					entityCache.putResult(JobModelImpl.ENTITY_CACHE_ENABLED,
						JobImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(JobModelImpl.ENTITY_CACHE_ENABLED,
					JobImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return job;
	}

	/**
	 * Returns the job with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jobId the primary key of the job
	 * @return the job, or <code>null</code> if a job with the primary key could not be found
	 */
	@Override
	public Job fetchByPrimaryKey(long jobId) {
		return fetchByPrimaryKey((Serializable)jobId);
	}

	@Override
	public Map<Serializable, Job> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Job> map = new HashMap<Serializable, Job>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Job job = fetchByPrimaryKey(primaryKey);

			if (job != null) {
				map.put(primaryKey, job);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(JobModelImpl.ENTITY_CACHE_ENABLED,
					JobImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Job)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_JOB_WHERE_PKS_IN);

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

			for (Job job : (List<Job>)q.list()) {
				map.put(job.getPrimaryKeyObj(), job);

				cacheResult(job);

				uncachedPrimaryKeys.remove(job.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(JobModelImpl.ENTITY_CACHE_ENABLED,
					JobImpl.class, primaryKey, nullModel);
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
	 * Returns all the jobs.
	 *
	 * @return the jobs
	 */
	@Override
	public List<Job> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jobs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @return the range of jobs
	 */
	@Override
	public List<Job> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the jobs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of jobs
	 */
	@Override
	public List<Job> findAll(int start, int end,
		OrderByComparator<Job> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jobs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of jobs
	 * @param end the upper bound of the range of jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of jobs
	 */
	@Override
	public List<Job> findAll(int start, int end,
		OrderByComparator<Job> orderByComparator, boolean retrieveFromCache) {
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

		List<Job> list = null;

		if (retrieveFromCache) {
			list = (List<Job>)finderCache.getResult(finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_JOB);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_JOB;

				if (pagination) {
					sql = sql.concat(JobModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Job>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Removes all the jobs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Job job : findAll()) {
			remove(job);
		}
	}

	/**
	 * Returns the number of jobs.
	 *
	 * @return the number of jobs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_JOB);

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
		return JobModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the job persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(JobImpl.class.getName());
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
	private static final String _SQL_SELECT_JOB = "SELECT job FROM Job job";
	private static final String _SQL_SELECT_JOB_WHERE_PKS_IN = "SELECT job FROM Job job WHERE jobId IN (";
	private static final String _SQL_SELECT_JOB_WHERE = "SELECT job FROM Job job WHERE ";
	private static final String _SQL_COUNT_JOB = "SELECT COUNT(job) FROM Job job";
	private static final String _SQL_COUNT_JOB_WHERE = "SELECT COUNT(job) FROM Job job WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "job.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Job exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Job exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(JobPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}