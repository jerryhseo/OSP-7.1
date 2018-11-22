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

import com.osp.spyglass.exception.NoSuchLogPortsException;
import com.osp.spyglass.model.LogPorts;
import com.osp.spyglass.model.impl.LogPortsImpl;
import com.osp.spyglass.model.impl.LogPortsModelImpl;
import com.osp.spyglass.service.persistence.LogPortsPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the log ports service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jerry H. Seo
 * @see LogPortsPersistence
 * @see com.osp.spyglass.service.persistence.LogPortsUtil
 * @generated
 */
@ProviderType
public class LogPortsPersistenceImpl extends BasePersistenceImpl<LogPorts>
	implements LogPortsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LogPortsUtil} to access the log ports persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LogPortsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LogPortsModelImpl.ENTITY_CACHE_ENABLED,
			LogPortsModelImpl.FINDER_CACHE_ENABLED, LogPortsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LogPortsModelImpl.ENTITY_CACHE_ENABLED,
			LogPortsModelImpl.FINDER_CACHE_ENABLED, LogPortsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LogPortsModelImpl.ENTITY_CACHE_ENABLED,
			LogPortsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public LogPortsPersistenceImpl() {
		setModelClass(LogPorts.class);
	}

	/**
	 * Caches the log ports in the entity cache if it is enabled.
	 *
	 * @param logPorts the log ports
	 */
	@Override
	public void cacheResult(LogPorts logPorts) {
		entityCache.putResult(LogPortsModelImpl.ENTITY_CACHE_ENABLED,
			LogPortsImpl.class, logPorts.getPrimaryKey(), logPorts);

		logPorts.resetOriginalValues();
	}

	/**
	 * Caches the log portses in the entity cache if it is enabled.
	 *
	 * @param logPortses the log portses
	 */
	@Override
	public void cacheResult(List<LogPorts> logPortses) {
		for (LogPorts logPorts : logPortses) {
			if (entityCache.getResult(LogPortsModelImpl.ENTITY_CACHE_ENABLED,
						LogPortsImpl.class, logPorts.getPrimaryKey()) == null) {
				cacheResult(logPorts);
			}
			else {
				logPorts.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all log portses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LogPortsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the log ports.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LogPorts logPorts) {
		entityCache.removeResult(LogPortsModelImpl.ENTITY_CACHE_ENABLED,
			LogPortsImpl.class, logPorts.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LogPorts> logPortses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LogPorts logPorts : logPortses) {
			entityCache.removeResult(LogPortsModelImpl.ENTITY_CACHE_ENABLED,
				LogPortsImpl.class, logPorts.getPrimaryKey());
		}
	}

	/**
	 * Creates a new log ports with the primary key. Does not add the log ports to the database.
	 *
	 * @param scienceAppId the primary key for the new log ports
	 * @return the new log ports
	 */
	@Override
	public LogPorts create(long scienceAppId) {
		LogPorts logPorts = new LogPortsImpl();

		logPorts.setNew(true);
		logPorts.setPrimaryKey(scienceAppId);

		return logPorts;
	}

	/**
	 * Removes the log ports with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scienceAppId the primary key of the log ports
	 * @return the log ports that was removed
	 * @throws NoSuchLogPortsException if a log ports with the primary key could not be found
	 */
	@Override
	public LogPorts remove(long scienceAppId) throws NoSuchLogPortsException {
		return remove((Serializable)scienceAppId);
	}

	/**
	 * Removes the log ports with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the log ports
	 * @return the log ports that was removed
	 * @throws NoSuchLogPortsException if a log ports with the primary key could not be found
	 */
	@Override
	public LogPorts remove(Serializable primaryKey)
		throws NoSuchLogPortsException {
		Session session = null;

		try {
			session = openSession();

			LogPorts logPorts = (LogPorts)session.get(LogPortsImpl.class,
					primaryKey);

			if (logPorts == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLogPortsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(logPorts);
		}
		catch (NoSuchLogPortsException nsee) {
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
	protected LogPorts removeImpl(LogPorts logPorts) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(logPorts)) {
				logPorts = (LogPorts)session.get(LogPortsImpl.class,
						logPorts.getPrimaryKeyObj());
			}

			if (logPorts != null) {
				session.delete(logPorts);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (logPorts != null) {
			clearCache(logPorts);
		}

		return logPorts;
	}

	@Override
	public LogPorts updateImpl(LogPorts logPorts) {
		boolean isNew = logPorts.isNew();

		Session session = null;

		try {
			session = openSession();

			if (logPorts.isNew()) {
				session.save(logPorts);

				logPorts.setNew(false);
			}
			else {
				logPorts = (LogPorts)session.merge(logPorts);
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

		entityCache.putResult(LogPortsModelImpl.ENTITY_CACHE_ENABLED,
			LogPortsImpl.class, logPorts.getPrimaryKey(), logPorts, false);

		logPorts.resetOriginalValues();

		return logPorts;
	}

	/**
	 * Returns the log ports with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the log ports
	 * @return the log ports
	 * @throws NoSuchLogPortsException if a log ports with the primary key could not be found
	 */
	@Override
	public LogPorts findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLogPortsException {
		LogPorts logPorts = fetchByPrimaryKey(primaryKey);

		if (logPorts == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLogPortsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return logPorts;
	}

	/**
	 * Returns the log ports with the primary key or throws a {@link NoSuchLogPortsException} if it could not be found.
	 *
	 * @param scienceAppId the primary key of the log ports
	 * @return the log ports
	 * @throws NoSuchLogPortsException if a log ports with the primary key could not be found
	 */
	@Override
	public LogPorts findByPrimaryKey(long scienceAppId)
		throws NoSuchLogPortsException {
		return findByPrimaryKey((Serializable)scienceAppId);
	}

	/**
	 * Returns the log ports with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the log ports
	 * @return the log ports, or <code>null</code> if a log ports with the primary key could not be found
	 */
	@Override
	public LogPorts fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LogPortsModelImpl.ENTITY_CACHE_ENABLED,
				LogPortsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LogPorts logPorts = (LogPorts)serializable;

		if (logPorts == null) {
			Session session = null;

			try {
				session = openSession();

				logPorts = (LogPorts)session.get(LogPortsImpl.class, primaryKey);

				if (logPorts != null) {
					cacheResult(logPorts);
				}
				else {
					entityCache.putResult(LogPortsModelImpl.ENTITY_CACHE_ENABLED,
						LogPortsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LogPortsModelImpl.ENTITY_CACHE_ENABLED,
					LogPortsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return logPorts;
	}

	/**
	 * Returns the log ports with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param scienceAppId the primary key of the log ports
	 * @return the log ports, or <code>null</code> if a log ports with the primary key could not be found
	 */
	@Override
	public LogPorts fetchByPrimaryKey(long scienceAppId) {
		return fetchByPrimaryKey((Serializable)scienceAppId);
	}

	@Override
	public Map<Serializable, LogPorts> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LogPorts> map = new HashMap<Serializable, LogPorts>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LogPorts logPorts = fetchByPrimaryKey(primaryKey);

			if (logPorts != null) {
				map.put(primaryKey, logPorts);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LogPortsModelImpl.ENTITY_CACHE_ENABLED,
					LogPortsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LogPorts)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOGPORTS_WHERE_PKS_IN);

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

			for (LogPorts logPorts : (List<LogPorts>)q.list()) {
				map.put(logPorts.getPrimaryKeyObj(), logPorts);

				cacheResult(logPorts);

				uncachedPrimaryKeys.remove(logPorts.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LogPortsModelImpl.ENTITY_CACHE_ENABLED,
					LogPortsImpl.class, primaryKey, nullModel);
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
	 * Returns all the log portses.
	 *
	 * @return the log portses
	 */
	@Override
	public List<LogPorts> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the log portses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LogPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of log portses
	 * @param end the upper bound of the range of log portses (not inclusive)
	 * @return the range of log portses
	 */
	@Override
	public List<LogPorts> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the log portses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LogPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of log portses
	 * @param end the upper bound of the range of log portses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of log portses
	 */
	@Override
	public List<LogPorts> findAll(int start, int end,
		OrderByComparator<LogPorts> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the log portses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LogPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of log portses
	 * @param end the upper bound of the range of log portses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of log portses
	 */
	@Override
	public List<LogPorts> findAll(int start, int end,
		OrderByComparator<LogPorts> orderByComparator, boolean retrieveFromCache) {
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

		List<LogPorts> list = null;

		if (retrieveFromCache) {
			list = (List<LogPorts>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOGPORTS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOGPORTS;

				if (pagination) {
					sql = sql.concat(LogPortsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LogPorts>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LogPorts>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the log portses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LogPorts logPorts : findAll()) {
			remove(logPorts);
		}
	}

	/**
	 * Returns the number of log portses.
	 *
	 * @return the number of log portses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOGPORTS);

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
		return LogPortsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the log ports persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LogPortsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LOGPORTS = "SELECT logPorts FROM LogPorts logPorts";
	private static final String _SQL_SELECT_LOGPORTS_WHERE_PKS_IN = "SELECT logPorts FROM LogPorts logPorts WHERE scienceAppId IN (";
	private static final String _SQL_COUNT_LOGPORTS = "SELECT COUNT(logPorts) FROM LogPorts logPorts";
	private static final String _ORDER_BY_ENTITY_ALIAS = "logPorts.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LogPorts exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(LogPortsPersistenceImpl.class);
}