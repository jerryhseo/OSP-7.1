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

import com.kisti.osp.icebreaker.exception.NoSuchJobDataException;
import com.kisti.osp.icebreaker.model.JobData;
import com.kisti.osp.icebreaker.model.impl.JobDataImpl;
import com.kisti.osp.icebreaker.model.impl.JobDataModelImpl;
import com.kisti.osp.icebreaker.service.persistence.JobDataPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the job data service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jerry H. Seo
 * @see JobDataPersistence
 * @see com.kisti.osp.icebreaker.service.persistence.JobDataUtil
 * @generated
 */
@ProviderType
public class JobDataPersistenceImpl extends BasePersistenceImpl<JobData>
	implements JobDataPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link JobDataUtil} to access the job data persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = JobDataImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(JobDataModelImpl.ENTITY_CACHE_ENABLED,
			JobDataModelImpl.FINDER_CACHE_ENABLED, JobDataImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(JobDataModelImpl.ENTITY_CACHE_ENABLED,
			JobDataModelImpl.FINDER_CACHE_ENABLED, JobDataImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JobDataModelImpl.ENTITY_CACHE_ENABLED,
			JobDataModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public JobDataPersistenceImpl() {
		setModelClass(JobData.class);
	}

	/**
	 * Caches the job data in the entity cache if it is enabled.
	 *
	 * @param jobData the job data
	 */
	@Override
	public void cacheResult(JobData jobData) {
		entityCache.putResult(JobDataModelImpl.ENTITY_CACHE_ENABLED,
			JobDataImpl.class, jobData.getPrimaryKey(), jobData);

		jobData.resetOriginalValues();
	}

	/**
	 * Caches the job datas in the entity cache if it is enabled.
	 *
	 * @param jobDatas the job datas
	 */
	@Override
	public void cacheResult(List<JobData> jobDatas) {
		for (JobData jobData : jobDatas) {
			if (entityCache.getResult(JobDataModelImpl.ENTITY_CACHE_ENABLED,
						JobDataImpl.class, jobData.getPrimaryKey()) == null) {
				cacheResult(jobData);
			}
			else {
				jobData.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all job datas.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(JobDataImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the job data.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JobData jobData) {
		entityCache.removeResult(JobDataModelImpl.ENTITY_CACHE_ENABLED,
			JobDataImpl.class, jobData.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<JobData> jobDatas) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (JobData jobData : jobDatas) {
			entityCache.removeResult(JobDataModelImpl.ENTITY_CACHE_ENABLED,
				JobDataImpl.class, jobData.getPrimaryKey());
		}
	}

	/**
	 * Creates a new job data with the primary key. Does not add the job data to the database.
	 *
	 * @param jobId the primary key for the new job data
	 * @return the new job data
	 */
	@Override
	public JobData create(long jobId) {
		JobData jobData = new JobDataImpl();

		jobData.setNew(true);
		jobData.setPrimaryKey(jobId);

		return jobData;
	}

	/**
	 * Removes the job data with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jobId the primary key of the job data
	 * @return the job data that was removed
	 * @throws NoSuchJobDataException if a job data with the primary key could not be found
	 */
	@Override
	public JobData remove(long jobId) throws NoSuchJobDataException {
		return remove((Serializable)jobId);
	}

	/**
	 * Removes the job data with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the job data
	 * @return the job data that was removed
	 * @throws NoSuchJobDataException if a job data with the primary key could not be found
	 */
	@Override
	public JobData remove(Serializable primaryKey)
		throws NoSuchJobDataException {
		Session session = null;

		try {
			session = openSession();

			JobData jobData = (JobData)session.get(JobDataImpl.class, primaryKey);

			if (jobData == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJobDataException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(jobData);
		}
		catch (NoSuchJobDataException nsee) {
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
	protected JobData removeImpl(JobData jobData) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(jobData)) {
				jobData = (JobData)session.get(JobDataImpl.class,
						jobData.getPrimaryKeyObj());
			}

			if (jobData != null) {
				session.delete(jobData);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (jobData != null) {
			clearCache(jobData);
		}

		return jobData;
	}

	@Override
	public JobData updateImpl(JobData jobData) {
		boolean isNew = jobData.isNew();

		Session session = null;

		try {
			session = openSession();

			if (jobData.isNew()) {
				session.save(jobData);

				jobData.setNew(false);
			}
			else {
				jobData = (JobData)session.merge(jobData);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(JobDataModelImpl.ENTITY_CACHE_ENABLED,
			JobDataImpl.class, jobData.getPrimaryKey(), jobData, false);

		jobData.resetOriginalValues();

		return jobData;
	}

	/**
	 * Returns the job data with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the job data
	 * @return the job data
	 * @throws NoSuchJobDataException if a job data with the primary key could not be found
	 */
	@Override
	public JobData findByPrimaryKey(Serializable primaryKey)
		throws NoSuchJobDataException {
		JobData jobData = fetchByPrimaryKey(primaryKey);

		if (jobData == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchJobDataException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return jobData;
	}

	/**
	 * Returns the job data with the primary key or throws a {@link NoSuchJobDataException} if it could not be found.
	 *
	 * @param jobId the primary key of the job data
	 * @return the job data
	 * @throws NoSuchJobDataException if a job data with the primary key could not be found
	 */
	@Override
	public JobData findByPrimaryKey(long jobId) throws NoSuchJobDataException {
		return findByPrimaryKey((Serializable)jobId);
	}

	/**
	 * Returns the job data with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the job data
	 * @return the job data, or <code>null</code> if a job data with the primary key could not be found
	 */
	@Override
	public JobData fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(JobDataModelImpl.ENTITY_CACHE_ENABLED,
				JobDataImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		JobData jobData = (JobData)serializable;

		if (jobData == null) {
			Session session = null;

			try {
				session = openSession();

				jobData = (JobData)session.get(JobDataImpl.class, primaryKey);

				if (jobData != null) {
					cacheResult(jobData);
				}
				else {
					entityCache.putResult(JobDataModelImpl.ENTITY_CACHE_ENABLED,
						JobDataImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(JobDataModelImpl.ENTITY_CACHE_ENABLED,
					JobDataImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return jobData;
	}

	/**
	 * Returns the job data with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jobId the primary key of the job data
	 * @return the job data, or <code>null</code> if a job data with the primary key could not be found
	 */
	@Override
	public JobData fetchByPrimaryKey(long jobId) {
		return fetchByPrimaryKey((Serializable)jobId);
	}

	@Override
	public Map<Serializable, JobData> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, JobData> map = new HashMap<Serializable, JobData>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			JobData jobData = fetchByPrimaryKey(primaryKey);

			if (jobData != null) {
				map.put(primaryKey, jobData);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(JobDataModelImpl.ENTITY_CACHE_ENABLED,
					JobDataImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (JobData)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_JOBDATA_WHERE_PKS_IN);

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

			for (JobData jobData : (List<JobData>)q.list()) {
				map.put(jobData.getPrimaryKeyObj(), jobData);

				cacheResult(jobData);

				uncachedPrimaryKeys.remove(jobData.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(JobDataModelImpl.ENTITY_CACHE_ENABLED,
					JobDataImpl.class, primaryKey, nullModel);
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
	 * Returns all the job datas.
	 *
	 * @return the job datas
	 */
	@Override
	public List<JobData> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the job datas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of job datas
	 * @param end the upper bound of the range of job datas (not inclusive)
	 * @return the range of job datas
	 */
	@Override
	public List<JobData> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the job datas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of job datas
	 * @param end the upper bound of the range of job datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of job datas
	 */
	@Override
	public List<JobData> findAll(int start, int end,
		OrderByComparator<JobData> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the job datas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of job datas
	 * @param end the upper bound of the range of job datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of job datas
	 */
	@Override
	public List<JobData> findAll(int start, int end,
		OrderByComparator<JobData> orderByComparator, boolean retrieveFromCache) {
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

		List<JobData> list = null;

		if (retrieveFromCache) {
			list = (List<JobData>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_JOBDATA);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_JOBDATA;

				if (pagination) {
					sql = sql.concat(JobDataModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<JobData>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JobData>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the job datas from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (JobData jobData : findAll()) {
			remove(jobData);
		}
	}

	/**
	 * Returns the number of job datas.
	 *
	 * @return the number of job datas
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_JOBDATA);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return JobDataModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the job data persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(JobDataImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_JOBDATA = "SELECT jobData FROM JobData jobData";
	private static final String _SQL_SELECT_JOBDATA_WHERE_PKS_IN = "SELECT jobData FROM JobData jobData WHERE jobId IN (";
	private static final String _SQL_COUNT_JOBDATA = "SELECT COUNT(jobData) FROM JobData jobData";
	private static final String _ORDER_BY_ENTITY_ALIAS = "jobData.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No JobData exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(JobDataPersistenceImpl.class);
}