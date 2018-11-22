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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.osp.spyglass.exception.NoSuchAssignedSchedulerException;
import com.osp.spyglass.model.AssignedScheduler;
import com.osp.spyglass.model.impl.AssignedSchedulerImpl;
import com.osp.spyglass.model.impl.AssignedSchedulerModelImpl;
import com.osp.spyglass.service.persistence.AssignedSchedulerPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the assigned scheduler service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jerry H. Seo
 * @see AssignedSchedulerPersistence
 * @see com.osp.spyglass.service.persistence.AssignedSchedulerUtil
 * @generated
 */
@ProviderType
public class AssignedSchedulerPersistenceImpl extends BasePersistenceImpl<AssignedScheduler>
	implements AssignedSchedulerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssignedSchedulerUtil} to access the assigned scheduler persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssignedSchedulerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssignedSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			AssignedSchedulerModelImpl.FINDER_CACHE_ENABLED,
			AssignedSchedulerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssignedSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			AssignedSchedulerModelImpl.FINDER_CACHE_ENABLED,
			AssignedSchedulerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssignedSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			AssignedSchedulerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public AssignedSchedulerPersistenceImpl() {
		setModelClass(AssignedScheduler.class);
	}

	/**
	 * Caches the assigned scheduler in the entity cache if it is enabled.
	 *
	 * @param assignedScheduler the assigned scheduler
	 */
	@Override
	public void cacheResult(AssignedScheduler assignedScheduler) {
		entityCache.putResult(AssignedSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			AssignedSchedulerImpl.class, assignedScheduler.getPrimaryKey(),
			assignedScheduler);

		assignedScheduler.resetOriginalValues();
	}

	/**
	 * Caches the assigned schedulers in the entity cache if it is enabled.
	 *
	 * @param assignedSchedulers the assigned schedulers
	 */
	@Override
	public void cacheResult(List<AssignedScheduler> assignedSchedulers) {
		for (AssignedScheduler assignedScheduler : assignedSchedulers) {
			if (entityCache.getResult(
						AssignedSchedulerModelImpl.ENTITY_CACHE_ENABLED,
						AssignedSchedulerImpl.class,
						assignedScheduler.getPrimaryKey()) == null) {
				cacheResult(assignedScheduler);
			}
			else {
				assignedScheduler.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all assigned schedulers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AssignedSchedulerImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the assigned scheduler.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssignedScheduler assignedScheduler) {
		entityCache.removeResult(AssignedSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			AssignedSchedulerImpl.class, assignedScheduler.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AssignedScheduler> assignedSchedulers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssignedScheduler assignedScheduler : assignedSchedulers) {
			entityCache.removeResult(AssignedSchedulerModelImpl.ENTITY_CACHE_ENABLED,
				AssignedSchedulerImpl.class, assignedScheduler.getPrimaryKey());
		}
	}

	/**
	 * Creates a new assigned scheduler with the primary key. Does not add the assigned scheduler to the database.
	 *
	 * @param scienceAppId the primary key for the new assigned scheduler
	 * @return the new assigned scheduler
	 */
	@Override
	public AssignedScheduler create(long scienceAppId) {
		AssignedScheduler assignedScheduler = new AssignedSchedulerImpl();

		assignedScheduler.setNew(true);
		assignedScheduler.setPrimaryKey(scienceAppId);

		return assignedScheduler;
	}

	/**
	 * Removes the assigned scheduler with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scienceAppId the primary key of the assigned scheduler
	 * @return the assigned scheduler that was removed
	 * @throws NoSuchAssignedSchedulerException if a assigned scheduler with the primary key could not be found
	 */
	@Override
	public AssignedScheduler remove(long scienceAppId)
		throws NoSuchAssignedSchedulerException {
		return remove((Serializable)scienceAppId);
	}

	/**
	 * Removes the assigned scheduler with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the assigned scheduler
	 * @return the assigned scheduler that was removed
	 * @throws NoSuchAssignedSchedulerException if a assigned scheduler with the primary key could not be found
	 */
	@Override
	public AssignedScheduler remove(Serializable primaryKey)
		throws NoSuchAssignedSchedulerException {
		Session session = null;

		try {
			session = openSession();

			AssignedScheduler assignedScheduler = (AssignedScheduler)session.get(AssignedSchedulerImpl.class,
					primaryKey);

			if (assignedScheduler == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssignedSchedulerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assignedScheduler);
		}
		catch (NoSuchAssignedSchedulerException nsee) {
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
	protected AssignedScheduler removeImpl(AssignedScheduler assignedScheduler) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(assignedScheduler)) {
				assignedScheduler = (AssignedScheduler)session.get(AssignedSchedulerImpl.class,
						assignedScheduler.getPrimaryKeyObj());
			}

			if (assignedScheduler != null) {
				session.delete(assignedScheduler);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (assignedScheduler != null) {
			clearCache(assignedScheduler);
		}

		return assignedScheduler;
	}

	@Override
	public AssignedScheduler updateImpl(AssignedScheduler assignedScheduler) {
		boolean isNew = assignedScheduler.isNew();

		Session session = null;

		try {
			session = openSession();

			if (assignedScheduler.isNew()) {
				session.save(assignedScheduler);

				assignedScheduler.setNew(false);
			}
			else {
				assignedScheduler = (AssignedScheduler)session.merge(assignedScheduler);
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

		entityCache.putResult(AssignedSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			AssignedSchedulerImpl.class, assignedScheduler.getPrimaryKey(),
			assignedScheduler, false);

		assignedScheduler.resetOriginalValues();

		return assignedScheduler;
	}

	/**
	 * Returns the assigned scheduler with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the assigned scheduler
	 * @return the assigned scheduler
	 * @throws NoSuchAssignedSchedulerException if a assigned scheduler with the primary key could not be found
	 */
	@Override
	public AssignedScheduler findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAssignedSchedulerException {
		AssignedScheduler assignedScheduler = fetchByPrimaryKey(primaryKey);

		if (assignedScheduler == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAssignedSchedulerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return assignedScheduler;
	}

	/**
	 * Returns the assigned scheduler with the primary key or throws a {@link NoSuchAssignedSchedulerException} if it could not be found.
	 *
	 * @param scienceAppId the primary key of the assigned scheduler
	 * @return the assigned scheduler
	 * @throws NoSuchAssignedSchedulerException if a assigned scheduler with the primary key could not be found
	 */
	@Override
	public AssignedScheduler findByPrimaryKey(long scienceAppId)
		throws NoSuchAssignedSchedulerException {
		return findByPrimaryKey((Serializable)scienceAppId);
	}

	/**
	 * Returns the assigned scheduler with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the assigned scheduler
	 * @return the assigned scheduler, or <code>null</code> if a assigned scheduler with the primary key could not be found
	 */
	@Override
	public AssignedScheduler fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AssignedSchedulerModelImpl.ENTITY_CACHE_ENABLED,
				AssignedSchedulerImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AssignedScheduler assignedScheduler = (AssignedScheduler)serializable;

		if (assignedScheduler == null) {
			Session session = null;

			try {
				session = openSession();

				assignedScheduler = (AssignedScheduler)session.get(AssignedSchedulerImpl.class,
						primaryKey);

				if (assignedScheduler != null) {
					cacheResult(assignedScheduler);
				}
				else {
					entityCache.putResult(AssignedSchedulerModelImpl.ENTITY_CACHE_ENABLED,
						AssignedSchedulerImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AssignedSchedulerModelImpl.ENTITY_CACHE_ENABLED,
					AssignedSchedulerImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return assignedScheduler;
	}

	/**
	 * Returns the assigned scheduler with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param scienceAppId the primary key of the assigned scheduler
	 * @return the assigned scheduler, or <code>null</code> if a assigned scheduler with the primary key could not be found
	 */
	@Override
	public AssignedScheduler fetchByPrimaryKey(long scienceAppId) {
		return fetchByPrimaryKey((Serializable)scienceAppId);
	}

	@Override
	public Map<Serializable, AssignedScheduler> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AssignedScheduler> map = new HashMap<Serializable, AssignedScheduler>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AssignedScheduler assignedScheduler = fetchByPrimaryKey(primaryKey);

			if (assignedScheduler != null) {
				map.put(primaryKey, assignedScheduler);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AssignedSchedulerModelImpl.ENTITY_CACHE_ENABLED,
					AssignedSchedulerImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AssignedScheduler)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ASSIGNEDSCHEDULER_WHERE_PKS_IN);

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

			for (AssignedScheduler assignedScheduler : (List<AssignedScheduler>)q.list()) {
				map.put(assignedScheduler.getPrimaryKeyObj(), assignedScheduler);

				cacheResult(assignedScheduler);

				uncachedPrimaryKeys.remove(assignedScheduler.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AssignedSchedulerModelImpl.ENTITY_CACHE_ENABLED,
					AssignedSchedulerImpl.class, primaryKey, nullModel);
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
	 * Returns all the assigned schedulers.
	 *
	 * @return the assigned schedulers
	 */
	@Override
	public List<AssignedScheduler> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssignedScheduler> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<AssignedScheduler> findAll(int start, int end,
		OrderByComparator<AssignedScheduler> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<AssignedScheduler> findAll(int start, int end,
		OrderByComparator<AssignedScheduler> orderByComparator,
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

		List<AssignedScheduler> list = null;

		if (retrieveFromCache) {
			list = (List<AssignedScheduler>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ASSIGNEDSCHEDULER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSIGNEDSCHEDULER;

				if (pagination) {
					sql = sql.concat(AssignedSchedulerModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AssignedScheduler>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AssignedScheduler>)QueryUtil.list(q,
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
	 * Removes all the assigned schedulers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AssignedScheduler assignedScheduler : findAll()) {
			remove(assignedScheduler);
		}
	}

	/**
	 * Returns the number of assigned schedulers.
	 *
	 * @return the number of assigned schedulers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSIGNEDSCHEDULER);

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
		return AssignedSchedulerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the assigned scheduler persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AssignedSchedulerImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ASSIGNEDSCHEDULER = "SELECT assignedScheduler FROM AssignedScheduler assignedScheduler";
	private static final String _SQL_SELECT_ASSIGNEDSCHEDULER_WHERE_PKS_IN = "SELECT assignedScheduler FROM AssignedScheduler assignedScheduler WHERE scienceAppId IN (";
	private static final String _SQL_COUNT_ASSIGNEDSCHEDULER = "SELECT COUNT(assignedScheduler) FROM AssignedScheduler assignedScheduler";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assignedScheduler.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssignedScheduler exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(AssignedSchedulerPersistenceImpl.class);
}