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

import com.osp.spyglass.exception.NoSuchOutputPortsException;
import com.osp.spyglass.model.OutputPorts;
import com.osp.spyglass.model.impl.OutputPortsImpl;
import com.osp.spyglass.model.impl.OutputPortsModelImpl;
import com.osp.spyglass.service.persistence.OutputPortsPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the output ports service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jerry H. Seo
 * @see OutputPortsPersistence
 * @see com.osp.spyglass.service.persistence.OutputPortsUtil
 * @generated
 */
@ProviderType
public class OutputPortsPersistenceImpl extends BasePersistenceImpl<OutputPorts>
	implements OutputPortsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OutputPortsUtil} to access the output ports persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OutputPortsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OutputPortsModelImpl.ENTITY_CACHE_ENABLED,
			OutputPortsModelImpl.FINDER_CACHE_ENABLED, OutputPortsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OutputPortsModelImpl.ENTITY_CACHE_ENABLED,
			OutputPortsModelImpl.FINDER_CACHE_ENABLED, OutputPortsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OutputPortsModelImpl.ENTITY_CACHE_ENABLED,
			OutputPortsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public OutputPortsPersistenceImpl() {
		setModelClass(OutputPorts.class);
	}

	/**
	 * Caches the output ports in the entity cache if it is enabled.
	 *
	 * @param outputPorts the output ports
	 */
	@Override
	public void cacheResult(OutputPorts outputPorts) {
		entityCache.putResult(OutputPortsModelImpl.ENTITY_CACHE_ENABLED,
			OutputPortsImpl.class, outputPorts.getPrimaryKey(), outputPorts);

		outputPorts.resetOriginalValues();
	}

	/**
	 * Caches the output portses in the entity cache if it is enabled.
	 *
	 * @param outputPortses the output portses
	 */
	@Override
	public void cacheResult(List<OutputPorts> outputPortses) {
		for (OutputPorts outputPorts : outputPortses) {
			if (entityCache.getResult(
						OutputPortsModelImpl.ENTITY_CACHE_ENABLED,
						OutputPortsImpl.class, outputPorts.getPrimaryKey()) == null) {
				cacheResult(outputPorts);
			}
			else {
				outputPorts.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all output portses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OutputPortsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the output ports.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OutputPorts outputPorts) {
		entityCache.removeResult(OutputPortsModelImpl.ENTITY_CACHE_ENABLED,
			OutputPortsImpl.class, outputPorts.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<OutputPorts> outputPortses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OutputPorts outputPorts : outputPortses) {
			entityCache.removeResult(OutputPortsModelImpl.ENTITY_CACHE_ENABLED,
				OutputPortsImpl.class, outputPorts.getPrimaryKey());
		}
	}

	/**
	 * Creates a new output ports with the primary key. Does not add the output ports to the database.
	 *
	 * @param scienceAppId the primary key for the new output ports
	 * @return the new output ports
	 */
	@Override
	public OutputPorts create(long scienceAppId) {
		OutputPorts outputPorts = new OutputPortsImpl();

		outputPorts.setNew(true);
		outputPorts.setPrimaryKey(scienceAppId);

		return outputPorts;
	}

	/**
	 * Removes the output ports with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scienceAppId the primary key of the output ports
	 * @return the output ports that was removed
	 * @throws NoSuchOutputPortsException if a output ports with the primary key could not be found
	 */
	@Override
	public OutputPorts remove(long scienceAppId)
		throws NoSuchOutputPortsException {
		return remove((Serializable)scienceAppId);
	}

	/**
	 * Removes the output ports with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the output ports
	 * @return the output ports that was removed
	 * @throws NoSuchOutputPortsException if a output ports with the primary key could not be found
	 */
	@Override
	public OutputPorts remove(Serializable primaryKey)
		throws NoSuchOutputPortsException {
		Session session = null;

		try {
			session = openSession();

			OutputPorts outputPorts = (OutputPorts)session.get(OutputPortsImpl.class,
					primaryKey);

			if (outputPorts == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOutputPortsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(outputPorts);
		}
		catch (NoSuchOutputPortsException nsee) {
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
	protected OutputPorts removeImpl(OutputPorts outputPorts) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(outputPorts)) {
				outputPorts = (OutputPorts)session.get(OutputPortsImpl.class,
						outputPorts.getPrimaryKeyObj());
			}

			if (outputPorts != null) {
				session.delete(outputPorts);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (outputPorts != null) {
			clearCache(outputPorts);
		}

		return outputPorts;
	}

	@Override
	public OutputPorts updateImpl(OutputPorts outputPorts) {
		boolean isNew = outputPorts.isNew();

		Session session = null;

		try {
			session = openSession();

			if (outputPorts.isNew()) {
				session.save(outputPorts);

				outputPorts.setNew(false);
			}
			else {
				outputPorts = (OutputPorts)session.merge(outputPorts);
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

		entityCache.putResult(OutputPortsModelImpl.ENTITY_CACHE_ENABLED,
			OutputPortsImpl.class, outputPorts.getPrimaryKey(), outputPorts,
			false);

		outputPorts.resetOriginalValues();

		return outputPorts;
	}

	/**
	 * Returns the output ports with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the output ports
	 * @return the output ports
	 * @throws NoSuchOutputPortsException if a output ports with the primary key could not be found
	 */
	@Override
	public OutputPorts findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOutputPortsException {
		OutputPorts outputPorts = fetchByPrimaryKey(primaryKey);

		if (outputPorts == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOutputPortsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return outputPorts;
	}

	/**
	 * Returns the output ports with the primary key or throws a {@link NoSuchOutputPortsException} if it could not be found.
	 *
	 * @param scienceAppId the primary key of the output ports
	 * @return the output ports
	 * @throws NoSuchOutputPortsException if a output ports with the primary key could not be found
	 */
	@Override
	public OutputPorts findByPrimaryKey(long scienceAppId)
		throws NoSuchOutputPortsException {
		return findByPrimaryKey((Serializable)scienceAppId);
	}

	/**
	 * Returns the output ports with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the output ports
	 * @return the output ports, or <code>null</code> if a output ports with the primary key could not be found
	 */
	@Override
	public OutputPorts fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OutputPortsModelImpl.ENTITY_CACHE_ENABLED,
				OutputPortsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OutputPorts outputPorts = (OutputPorts)serializable;

		if (outputPorts == null) {
			Session session = null;

			try {
				session = openSession();

				outputPorts = (OutputPorts)session.get(OutputPortsImpl.class,
						primaryKey);

				if (outputPorts != null) {
					cacheResult(outputPorts);
				}
				else {
					entityCache.putResult(OutputPortsModelImpl.ENTITY_CACHE_ENABLED,
						OutputPortsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OutputPortsModelImpl.ENTITY_CACHE_ENABLED,
					OutputPortsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return outputPorts;
	}

	/**
	 * Returns the output ports with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param scienceAppId the primary key of the output ports
	 * @return the output ports, or <code>null</code> if a output ports with the primary key could not be found
	 */
	@Override
	public OutputPorts fetchByPrimaryKey(long scienceAppId) {
		return fetchByPrimaryKey((Serializable)scienceAppId);
	}

	@Override
	public Map<Serializable, OutputPorts> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OutputPorts> map = new HashMap<Serializable, OutputPorts>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OutputPorts outputPorts = fetchByPrimaryKey(primaryKey);

			if (outputPorts != null) {
				map.put(primaryKey, outputPorts);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OutputPortsModelImpl.ENTITY_CACHE_ENABLED,
					OutputPortsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (OutputPorts)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OUTPUTPORTS_WHERE_PKS_IN);

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

			for (OutputPorts outputPorts : (List<OutputPorts>)q.list()) {
				map.put(outputPorts.getPrimaryKeyObj(), outputPorts);

				cacheResult(outputPorts);

				uncachedPrimaryKeys.remove(outputPorts.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OutputPortsModelImpl.ENTITY_CACHE_ENABLED,
					OutputPortsImpl.class, primaryKey, nullModel);
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
	 * Returns all the output portses.
	 *
	 * @return the output portses
	 */
	@Override
	public List<OutputPorts> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the output portses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OutputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of output portses
	 * @param end the upper bound of the range of output portses (not inclusive)
	 * @return the range of output portses
	 */
	@Override
	public List<OutputPorts> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the output portses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OutputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of output portses
	 * @param end the upper bound of the range of output portses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of output portses
	 */
	@Override
	public List<OutputPorts> findAll(int start, int end,
		OrderByComparator<OutputPorts> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the output portses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OutputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of output portses
	 * @param end the upper bound of the range of output portses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of output portses
	 */
	@Override
	public List<OutputPorts> findAll(int start, int end,
		OrderByComparator<OutputPorts> orderByComparator,
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

		List<OutputPorts> list = null;

		if (retrieveFromCache) {
			list = (List<OutputPorts>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_OUTPUTPORTS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OUTPUTPORTS;

				if (pagination) {
					sql = sql.concat(OutputPortsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OutputPorts>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OutputPorts>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the output portses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OutputPorts outputPorts : findAll()) {
			remove(outputPorts);
		}
	}

	/**
	 * Returns the number of output portses.
	 *
	 * @return the number of output portses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OUTPUTPORTS);

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
		return OutputPortsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the output ports persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(OutputPortsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_OUTPUTPORTS = "SELECT outputPorts FROM OutputPorts outputPorts";
	private static final String _SQL_SELECT_OUTPUTPORTS_WHERE_PKS_IN = "SELECT outputPorts FROM OutputPorts outputPorts WHERE scienceAppId IN (";
	private static final String _SQL_COUNT_OUTPUTPORTS = "SELECT COUNT(outputPorts) FROM OutputPorts outputPorts";
	private static final String _ORDER_BY_ENTITY_ALIAS = "outputPorts.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OutputPorts exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(OutputPortsPersistenceImpl.class);
}