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

package com.kisti.osp.util.service.base;

import aQute.bnd.annotation.ProviderType;

import com.kisti.osp.util.model.Util;
import com.kisti.osp.util.service.UtilLocalService;
import com.kisti.osp.util.service.persistence.UtilPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the util local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.kisti.osp.util.service.impl.UtilLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.kisti.osp.util.service.impl.UtilLocalServiceImpl
 * @see com.kisti.osp.util.service.UtilLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class UtilLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements UtilLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.kisti.osp.util.service.UtilLocalServiceUtil} to access the util local service.
	 */

	/**
	 * Adds the util to the database. Also notifies the appropriate model listeners.
	 *
	 * @param util the util
	 * @return the util that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Util addUtil(Util util) {
		util.setNew(true);

		return utilPersistence.update(util);
	}

	/**
	 * Creates a new util with the primary key. Does not add the util to the database.
	 *
	 * @param utilId the primary key for the new util
	 * @return the new util
	 */
	@Override
	@Transactional(enabled = false)
	public Util createUtil(long utilId) {
		return utilPersistence.create(utilId);
	}

	/**
	 * Deletes the util with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param utilId the primary key of the util
	 * @return the util that was removed
	 * @throws PortalException if a util with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Util deleteUtil(long utilId) throws PortalException {
		return utilPersistence.remove(utilId);
	}

	/**
	 * Deletes the util from the database. Also notifies the appropriate model listeners.
	 *
	 * @param util the util
	 * @return the util that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Util deleteUtil(Util util) {
		return utilPersistence.remove(util);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Util.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return utilPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.util.model.impl.UtilModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return utilPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.util.model.impl.UtilModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return utilPersistence.findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return utilPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return utilPersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public Util fetchUtil(long utilId) {
		return utilPersistence.fetchByPrimaryKey(utilId);
	}

	/**
	 * Returns the util matching the UUID and group.
	 *
	 * @param uuid the util's UUID
	 * @param groupId the primary key of the group
	 * @return the matching util, or <code>null</code> if a matching util could not be found
	 */
	@Override
	public Util fetchUtilByUuidAndGroupId(String uuid, long groupId) {
		return utilPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the util with the primary key.
	 *
	 * @param utilId the primary key of the util
	 * @return the util
	 * @throws PortalException if a util with the primary key could not be found
	 */
	@Override
	public Util getUtil(long utilId) throws PortalException {
		return utilPersistence.findByPrimaryKey(utilId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(utilLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Util.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("utilId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(utilLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Util.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("utilId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(utilLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Util.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("utilId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return utilLocalService.deleteUtil((Util)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return utilPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the utils matching the UUID and company.
	 *
	 * @param uuid the UUID of the utils
	 * @param companyId the primary key of the company
	 * @return the matching utils, or an empty list if no matches were found
	 */
	@Override
	public List<Util> getUtilsByUuidAndCompanyId(String uuid, long companyId) {
		return utilPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of utils matching the UUID and company.
	 *
	 * @param uuid the UUID of the utils
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of utils
	 * @param end the upper bound of the range of utils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching utils, or an empty list if no matches were found
	 */
	@Override
	public List<Util> getUtilsByUuidAndCompanyId(String uuid, long companyId,
		int start, int end, OrderByComparator<Util> orderByComparator) {
		return utilPersistence.findByUuid_C(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	 * Returns the util matching the UUID and group.
	 *
	 * @param uuid the util's UUID
	 * @param groupId the primary key of the group
	 * @return the matching util
	 * @throws PortalException if a matching util could not be found
	 */
	@Override
	public Util getUtilByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {
		return utilPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the utils.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.util.model.impl.UtilModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of utils
	 * @param end the upper bound of the range of utils (not inclusive)
	 * @return the range of utils
	 */
	@Override
	public List<Util> getUtils(int start, int end) {
		return utilPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of utils.
	 *
	 * @return the number of utils
	 */
	@Override
	public int getUtilsCount() {
		return utilPersistence.countAll();
	}

	/**
	 * Updates the util in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param util the util
	 * @return the util that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Util updateUtil(Util util) {
		return utilPersistence.update(util);
	}

	/**
	 * Returns the util local service.
	 *
	 * @return the util local service
	 */
	public UtilLocalService getUtilLocalService() {
		return utilLocalService;
	}

	/**
	 * Sets the util local service.
	 *
	 * @param utilLocalService the util local service
	 */
	public void setUtilLocalService(UtilLocalService utilLocalService) {
		this.utilLocalService = utilLocalService;
	}

	/**
	 * Returns the util persistence.
	 *
	 * @return the util persistence
	 */
	public UtilPersistence getUtilPersistence() {
		return utilPersistence;
	}

	/**
	 * Sets the util persistence.
	 *
	 * @param utilPersistence the util persistence
	 */
	public void setUtilPersistence(UtilPersistence utilPersistence) {
		this.utilPersistence = utilPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.kisti.osp.util.model.Util",
			utilLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.kisti.osp.util.model.Util");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return UtilLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Util.class;
	}

	protected String getModelClassName() {
		return Util.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = utilPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = UtilLocalService.class)
	protected UtilLocalService utilLocalService;
	@BeanReference(type = UtilPersistence.class)
	protected UtilPersistence utilPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}