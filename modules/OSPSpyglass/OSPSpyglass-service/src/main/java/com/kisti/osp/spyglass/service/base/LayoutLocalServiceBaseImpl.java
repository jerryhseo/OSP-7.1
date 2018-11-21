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

package com.kisti.osp.spyglass.service.base;

import aQute.bnd.annotation.ProviderType;

import com.kisti.osp.spyglass.model.Layout;
import com.kisti.osp.spyglass.service.LayoutLocalService;
import com.kisti.osp.spyglass.service.persistence.InputPortsPersistence;
import com.kisti.osp.spyglass.service.persistence.LayoutPersistence;
import com.kisti.osp.spyglass.service.persistence.LogPortsPersistence;
import com.kisti.osp.spyglass.service.persistence.OutputPortsPersistence;
import com.kisti.osp.spyglass.service.persistence.ScienceAppPersistence;

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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the layout local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.kisti.osp.spyglass.service.impl.LayoutLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.kisti.osp.spyglass.service.impl.LayoutLocalServiceImpl
 * @see com.kisti.osp.spyglass.service.LayoutLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class LayoutLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements LayoutLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.kisti.osp.spyglass.service.LayoutLocalServiceUtil} to access the layout local service.
	 */

	/**
	 * Adds the layout to the database. Also notifies the appropriate model listeners.
	 *
	 * @param layout the layout
	 * @return the layout that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Layout addLayout(Layout layout) {
		layout.setNew(true);

		return layoutPersistence.update(layout);
	}

	/**
	 * Creates a new layout with the primary key. Does not add the layout to the database.
	 *
	 * @param scienceAppId the primary key for the new layout
	 * @return the new layout
	 */
	@Override
	public Layout createLayout(long scienceAppId) {
		return layoutPersistence.create(scienceAppId);
	}

	/**
	 * Deletes the layout with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scienceAppId the primary key of the layout
	 * @return the layout that was removed
	 * @throws PortalException if a layout with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Layout deleteLayout(long scienceAppId) throws PortalException {
		return layoutPersistence.remove(scienceAppId);
	}

	/**
	 * Deletes the layout from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layout the layout
	 * @return the layout that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Layout deleteLayout(Layout layout) {
		return layoutPersistence.remove(layout);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Layout.class,
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
		return layoutPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.spyglass.model.impl.LayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return layoutPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.spyglass.model.impl.LayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return layoutPersistence.findWithDynamicQuery(dynamicQuery, start, end,
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
		return layoutPersistence.countWithDynamicQuery(dynamicQuery);
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
		return layoutPersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public Layout fetchLayout(long scienceAppId) {
		return layoutPersistence.fetchByPrimaryKey(scienceAppId);
	}

	/**
	 * Returns the layout with the primary key.
	 *
	 * @param scienceAppId the primary key of the layout
	 * @return the layout
	 * @throws PortalException if a layout with the primary key could not be found
	 */
	@Override
	public Layout getLayout(long scienceAppId) throws PortalException {
		return layoutPersistence.findByPrimaryKey(scienceAppId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(layoutLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Layout.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("scienceAppId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(layoutLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Layout.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"scienceAppId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(layoutLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Layout.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("scienceAppId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return layoutLocalService.deleteLayout((Layout)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return layoutPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the layouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.spyglass.model.impl.LayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layouts
	 * @param end the upper bound of the range of layouts (not inclusive)
	 * @return the range of layouts
	 */
	@Override
	public List<Layout> getLayouts(int start, int end) {
		return layoutPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of layouts.
	 *
	 * @return the number of layouts
	 */
	@Override
	public int getLayoutsCount() {
		return layoutPersistence.countAll();
	}

	/**
	 * Updates the layout in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param layout the layout
	 * @return the layout that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Layout updateLayout(Layout layout) {
		return layoutPersistence.update(layout);
	}

	/**
	 * Returns the input ports local service.
	 *
	 * @return the input ports local service
	 */
	public com.kisti.osp.spyglass.service.InputPortsLocalService getInputPortsLocalService() {
		return inputPortsLocalService;
	}

	/**
	 * Sets the input ports local service.
	 *
	 * @param inputPortsLocalService the input ports local service
	 */
	public void setInputPortsLocalService(
		com.kisti.osp.spyglass.service.InputPortsLocalService inputPortsLocalService) {
		this.inputPortsLocalService = inputPortsLocalService;
	}

	/**
	 * Returns the input ports persistence.
	 *
	 * @return the input ports persistence
	 */
	public InputPortsPersistence getInputPortsPersistence() {
		return inputPortsPersistence;
	}

	/**
	 * Sets the input ports persistence.
	 *
	 * @param inputPortsPersistence the input ports persistence
	 */
	public void setInputPortsPersistence(
		InputPortsPersistence inputPortsPersistence) {
		this.inputPortsPersistence = inputPortsPersistence;
	}

	/**
	 * Returns the layout local service.
	 *
	 * @return the layout local service
	 */
	public LayoutLocalService getLayoutLocalService() {
		return layoutLocalService;
	}

	/**
	 * Sets the layout local service.
	 *
	 * @param layoutLocalService the layout local service
	 */
	public void setLayoutLocalService(LayoutLocalService layoutLocalService) {
		this.layoutLocalService = layoutLocalService;
	}

	/**
	 * Returns the layout persistence.
	 *
	 * @return the layout persistence
	 */
	public LayoutPersistence getLayoutPersistence() {
		return layoutPersistence;
	}

	/**
	 * Sets the layout persistence.
	 *
	 * @param layoutPersistence the layout persistence
	 */
	public void setLayoutPersistence(LayoutPersistence layoutPersistence) {
		this.layoutPersistence = layoutPersistence;
	}

	/**
	 * Returns the log ports local service.
	 *
	 * @return the log ports local service
	 */
	public com.kisti.osp.spyglass.service.LogPortsLocalService getLogPortsLocalService() {
		return logPortsLocalService;
	}

	/**
	 * Sets the log ports local service.
	 *
	 * @param logPortsLocalService the log ports local service
	 */
	public void setLogPortsLocalService(
		com.kisti.osp.spyglass.service.LogPortsLocalService logPortsLocalService) {
		this.logPortsLocalService = logPortsLocalService;
	}

	/**
	 * Returns the log ports persistence.
	 *
	 * @return the log ports persistence
	 */
	public LogPortsPersistence getLogPortsPersistence() {
		return logPortsPersistence;
	}

	/**
	 * Sets the log ports persistence.
	 *
	 * @param logPortsPersistence the log ports persistence
	 */
	public void setLogPortsPersistence(LogPortsPersistence logPortsPersistence) {
		this.logPortsPersistence = logPortsPersistence;
	}

	/**
	 * Returns the output ports local service.
	 *
	 * @return the output ports local service
	 */
	public com.kisti.osp.spyglass.service.OutputPortsLocalService getOutputPortsLocalService() {
		return outputPortsLocalService;
	}

	/**
	 * Sets the output ports local service.
	 *
	 * @param outputPortsLocalService the output ports local service
	 */
	public void setOutputPortsLocalService(
		com.kisti.osp.spyglass.service.OutputPortsLocalService outputPortsLocalService) {
		this.outputPortsLocalService = outputPortsLocalService;
	}

	/**
	 * Returns the output ports persistence.
	 *
	 * @return the output ports persistence
	 */
	public OutputPortsPersistence getOutputPortsPersistence() {
		return outputPortsPersistence;
	}

	/**
	 * Sets the output ports persistence.
	 *
	 * @param outputPortsPersistence the output ports persistence
	 */
	public void setOutputPortsPersistence(
		OutputPortsPersistence outputPortsPersistence) {
		this.outputPortsPersistence = outputPortsPersistence;
	}

	/**
	 * Returns the science app local service.
	 *
	 * @return the science app local service
	 */
	public com.kisti.osp.spyglass.service.ScienceAppLocalService getScienceAppLocalService() {
		return scienceAppLocalService;
	}

	/**
	 * Sets the science app local service.
	 *
	 * @param scienceAppLocalService the science app local service
	 */
	public void setScienceAppLocalService(
		com.kisti.osp.spyglass.service.ScienceAppLocalService scienceAppLocalService) {
		this.scienceAppLocalService = scienceAppLocalService;
	}

	/**
	 * Returns the science app persistence.
	 *
	 * @return the science app persistence
	 */
	public ScienceAppPersistence getScienceAppPersistence() {
		return scienceAppPersistence;
	}

	/**
	 * Sets the science app persistence.
	 *
	 * @param scienceAppPersistence the science app persistence
	 */
	public void setScienceAppPersistence(
		ScienceAppPersistence scienceAppPersistence) {
		this.scienceAppPersistence = scienceAppPersistence;
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
		persistedModelLocalServiceRegistry.register("com.kisti.osp.spyglass.model.Layout",
			layoutLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.kisti.osp.spyglass.model.Layout");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return LayoutLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Layout.class;
	}

	protected String getModelClassName() {
		return Layout.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = layoutPersistence.getDataSource();

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

	@BeanReference(type = com.kisti.osp.spyglass.service.InputPortsLocalService.class)
	protected com.kisti.osp.spyglass.service.InputPortsLocalService inputPortsLocalService;
	@BeanReference(type = InputPortsPersistence.class)
	protected InputPortsPersistence inputPortsPersistence;
	@BeanReference(type = LayoutLocalService.class)
	protected LayoutLocalService layoutLocalService;
	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;
	@BeanReference(type = com.kisti.osp.spyglass.service.LogPortsLocalService.class)
	protected com.kisti.osp.spyglass.service.LogPortsLocalService logPortsLocalService;
	@BeanReference(type = LogPortsPersistence.class)
	protected LogPortsPersistence logPortsPersistence;
	@BeanReference(type = com.kisti.osp.spyglass.service.OutputPortsLocalService.class)
	protected com.kisti.osp.spyglass.service.OutputPortsLocalService outputPortsLocalService;
	@BeanReference(type = OutputPortsPersistence.class)
	protected OutputPortsPersistence outputPortsPersistence;
	@BeanReference(type = com.kisti.osp.spyglass.service.ScienceAppLocalService.class)
	protected com.kisti.osp.spyglass.service.ScienceAppLocalService scienceAppLocalService;
	@BeanReference(type = ScienceAppPersistence.class)
	protected ScienceAppPersistence scienceAppPersistence;
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