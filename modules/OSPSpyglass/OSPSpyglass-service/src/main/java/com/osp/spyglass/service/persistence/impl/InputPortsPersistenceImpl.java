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

import com.osp.spyglass.exception.NoSuchInputPortsException;
import com.osp.spyglass.model.InputPorts;
import com.osp.spyglass.model.impl.InputPortsImpl;
import com.osp.spyglass.model.impl.InputPortsModelImpl;
import com.osp.spyglass.service.persistence.InputPortsPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the input ports service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jerry H. Seo
 * @see InputPortsPersistence
 * @see com.osp.spyglass.service.persistence.InputPortsUtil
 * @generated
 */
@ProviderType
public class InputPortsPersistenceImpl extends BasePersistenceImpl<InputPorts>
	implements InputPortsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link InputPortsUtil} to access the input ports persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = InputPortsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(InputPortsModelImpl.ENTITY_CACHE_ENABLED,
			InputPortsModelImpl.FINDER_CACHE_ENABLED, InputPortsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(InputPortsModelImpl.ENTITY_CACHE_ENABLED,
			InputPortsModelImpl.FINDER_CACHE_ENABLED, InputPortsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(InputPortsModelImpl.ENTITY_CACHE_ENABLED,
			InputPortsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public InputPortsPersistenceImpl() {
		setModelClass(InputPorts.class);
	}

	/**
	 * Caches the input ports in the entity cache if it is enabled.
	 *
	 * @param inputPorts the input ports
	 */
	@Override
	public void cacheResult(InputPorts inputPorts) {
		entityCache.putResult(InputPortsModelImpl.ENTITY_CACHE_ENABLED,
			InputPortsImpl.class, inputPorts.getPrimaryKey(), inputPorts);

		inputPorts.resetOriginalValues();
	}

	/**
	 * Caches the input portses in the entity cache if it is enabled.
	 *
	 * @param inputPortses the input portses
	 */
	@Override
	public void cacheResult(List<InputPorts> inputPortses) {
		for (InputPorts inputPorts : inputPortses) {
			if (entityCache.getResult(
						InputPortsModelImpl.ENTITY_CACHE_ENABLED,
						InputPortsImpl.class, inputPorts.getPrimaryKey()) == null) {
				cacheResult(inputPorts);
			}
			else {
				inputPorts.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all input portses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(InputPortsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the input ports.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(InputPorts inputPorts) {
		entityCache.removeResult(InputPortsModelImpl.ENTITY_CACHE_ENABLED,
			InputPortsImpl.class, inputPorts.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<InputPorts> inputPortses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (InputPorts inputPorts : inputPortses) {
			entityCache.removeResult(InputPortsModelImpl.ENTITY_CACHE_ENABLED,
				InputPortsImpl.class, inputPorts.getPrimaryKey());
		}
	}

	/**
	 * Creates a new input ports with the primary key. Does not add the input ports to the database.
	 *
	 * @param scienceAppId the primary key for the new input ports
	 * @return the new input ports
	 */
	@Override
	public InputPorts create(long scienceAppId) {
		InputPorts inputPorts = new InputPortsImpl();

		inputPorts.setNew(true);
		inputPorts.setPrimaryKey(scienceAppId);

		return inputPorts;
	}

	/**
	 * Removes the input ports with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scienceAppId the primary key of the input ports
	 * @return the input ports that was removed
	 * @throws NoSuchInputPortsException if a input ports with the primary key could not be found
	 */
	@Override
	public InputPorts remove(long scienceAppId)
		throws NoSuchInputPortsException {
		return remove((Serializable)scienceAppId);
	}

	/**
	 * Removes the input ports with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the input ports
	 * @return the input ports that was removed
	 * @throws NoSuchInputPortsException if a input ports with the primary key could not be found
	 */
	@Override
	public InputPorts remove(Serializable primaryKey)
		throws NoSuchInputPortsException {
		Session session = null;

		try {
			session = openSession();

			InputPorts inputPorts = (InputPorts)session.get(InputPortsImpl.class,
					primaryKey);

			if (inputPorts == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInputPortsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(inputPorts);
		}
		catch (NoSuchInputPortsException nsee) {
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
	protected InputPorts removeImpl(InputPorts inputPorts) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(inputPorts)) {
				inputPorts = (InputPorts)session.get(InputPortsImpl.class,
						inputPorts.getPrimaryKeyObj());
			}

			if (inputPorts != null) {
				session.delete(inputPorts);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (inputPorts != null) {
			clearCache(inputPorts);
		}

		return inputPorts;
	}

	@Override
	public InputPorts updateImpl(InputPorts inputPorts) {
		boolean isNew = inputPorts.isNew();

		Session session = null;

		try {
			session = openSession();

			if (inputPorts.isNew()) {
				session.save(inputPorts);

				inputPorts.setNew(false);
			}
			else {
				inputPorts = (InputPorts)session.merge(inputPorts);
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

		entityCache.putResult(InputPortsModelImpl.ENTITY_CACHE_ENABLED,
			InputPortsImpl.class, inputPorts.getPrimaryKey(), inputPorts, false);

		inputPorts.resetOriginalValues();

		return inputPorts;
	}

	/**
	 * Returns the input ports with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the input ports
	 * @return the input ports
	 * @throws NoSuchInputPortsException if a input ports with the primary key could not be found
	 */
	@Override
	public InputPorts findByPrimaryKey(Serializable primaryKey)
		throws NoSuchInputPortsException {
		InputPorts inputPorts = fetchByPrimaryKey(primaryKey);

		if (inputPorts == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchInputPortsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return inputPorts;
	}

	/**
	 * Returns the input ports with the primary key or throws a {@link NoSuchInputPortsException} if it could not be found.
	 *
	 * @param scienceAppId the primary key of the input ports
	 * @return the input ports
	 * @throws NoSuchInputPortsException if a input ports with the primary key could not be found
	 */
	@Override
	public InputPorts findByPrimaryKey(long scienceAppId)
		throws NoSuchInputPortsException {
		return findByPrimaryKey((Serializable)scienceAppId);
	}

	/**
	 * Returns the input ports with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the input ports
	 * @return the input ports, or <code>null</code> if a input ports with the primary key could not be found
	 */
	@Override
	public InputPorts fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(InputPortsModelImpl.ENTITY_CACHE_ENABLED,
				InputPortsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		InputPorts inputPorts = (InputPorts)serializable;

		if (inputPorts == null) {
			Session session = null;

			try {
				session = openSession();

				inputPorts = (InputPorts)session.get(InputPortsImpl.class,
						primaryKey);

				if (inputPorts != null) {
					cacheResult(inputPorts);
				}
				else {
					entityCache.putResult(InputPortsModelImpl.ENTITY_CACHE_ENABLED,
						InputPortsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(InputPortsModelImpl.ENTITY_CACHE_ENABLED,
					InputPortsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return inputPorts;
	}

	/**
	 * Returns the input ports with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param scienceAppId the primary key of the input ports
	 * @return the input ports, or <code>null</code> if a input ports with the primary key could not be found
	 */
	@Override
	public InputPorts fetchByPrimaryKey(long scienceAppId) {
		return fetchByPrimaryKey((Serializable)scienceAppId);
	}

	@Override
	public Map<Serializable, InputPorts> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, InputPorts> map = new HashMap<Serializable, InputPorts>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			InputPorts inputPorts = fetchByPrimaryKey(primaryKey);

			if (inputPorts != null) {
				map.put(primaryKey, inputPorts);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(InputPortsModelImpl.ENTITY_CACHE_ENABLED,
					InputPortsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (InputPorts)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_INPUTPORTS_WHERE_PKS_IN);

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

			for (InputPorts inputPorts : (List<InputPorts>)q.list()) {
				map.put(inputPorts.getPrimaryKeyObj(), inputPorts);

				cacheResult(inputPorts);

				uncachedPrimaryKeys.remove(inputPorts.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(InputPortsModelImpl.ENTITY_CACHE_ENABLED,
					InputPortsImpl.class, primaryKey, nullModel);
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
	 * Returns all the input portses.
	 *
	 * @return the input portses
	 */
	@Override
	public List<InputPorts> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the input portses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of input portses
	 * @param end the upper bound of the range of input portses (not inclusive)
	 * @return the range of input portses
	 */
	@Override
	public List<InputPorts> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the input portses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of input portses
	 * @param end the upper bound of the range of input portses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of input portses
	 */
	@Override
	public List<InputPorts> findAll(int start, int end,
		OrderByComparator<InputPorts> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the input portses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InputPortsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of input portses
	 * @param end the upper bound of the range of input portses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of input portses
	 */
	@Override
	public List<InputPorts> findAll(int start, int end,
		OrderByComparator<InputPorts> orderByComparator,
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

		List<InputPorts> list = null;

		if (retrieveFromCache) {
			list = (List<InputPorts>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_INPUTPORTS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_INPUTPORTS;

				if (pagination) {
					sql = sql.concat(InputPortsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<InputPorts>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<InputPorts>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the input portses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (InputPorts inputPorts : findAll()) {
			remove(inputPorts);
		}
	}

	/**
	 * Returns the number of input portses.
	 *
	 * @return the number of input portses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_INPUTPORTS);

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
		return InputPortsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the input ports persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(InputPortsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_INPUTPORTS = "SELECT inputPorts FROM InputPorts inputPorts";
	private static final String _SQL_SELECT_INPUTPORTS_WHERE_PKS_IN = "SELECT inputPorts FROM InputPorts inputPorts WHERE scienceAppId IN (";
	private static final String _SQL_COUNT_INPUTPORTS = "SELECT COUNT(inputPorts) FROM InputPorts inputPorts";
	private static final String _ORDER_BY_ENTITY_ALIAS = "inputPorts.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No InputPorts exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(InputPortsPersistenceImpl.class);
}