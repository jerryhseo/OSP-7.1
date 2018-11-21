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

package com.kisti.osp.icebreaker.service.base;

import com.kisti.osp.icebreaker.model.OSPSchedulerEntry;
import com.kisti.osp.icebreaker.service.OSPSchedulerEntryService;
import com.kisti.osp.icebreaker.service.persistence.JobDataPersistence;
import com.kisti.osp.icebreaker.service.persistence.JobPersistence;
import com.kisti.osp.icebreaker.service.persistence.OSPSchedulerEntryPersistence;

import com.liferay.asset.kernel.service.persistence.AssetEntryPersistence;
import com.liferay.asset.kernel.service.persistence.AssetLinkPersistence;
import com.liferay.asset.kernel.service.persistence.AssetTagPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the osp scheduler entry remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.kisti.osp.icebreaker.service.impl.OSPSchedulerEntryServiceImpl}.
 * </p>
 *
 * @author Jerry H. Seo
 * @see com.kisti.osp.icebreaker.service.impl.OSPSchedulerEntryServiceImpl
 * @see com.kisti.osp.icebreaker.service.OSPSchedulerEntryServiceUtil
 * @generated
 */
public abstract class OSPSchedulerEntryServiceBaseImpl extends BaseServiceImpl
	implements OSPSchedulerEntryService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.kisti.osp.icebreaker.service.OSPSchedulerEntryServiceUtil} to access the osp scheduler entry remote service.
	 */

	/**
	 * Returns the job local service.
	 *
	 * @return the job local service
	 */
	public com.kisti.osp.icebreaker.service.JobLocalService getJobLocalService() {
		return jobLocalService;
	}

	/**
	 * Sets the job local service.
	 *
	 * @param jobLocalService the job local service
	 */
	public void setJobLocalService(
		com.kisti.osp.icebreaker.service.JobLocalService jobLocalService) {
		this.jobLocalService = jobLocalService;
	}

	/**
	 * Returns the job remote service.
	 *
	 * @return the job remote service
	 */
	public com.kisti.osp.icebreaker.service.JobService getJobService() {
		return jobService;
	}

	/**
	 * Sets the job remote service.
	 *
	 * @param jobService the job remote service
	 */
	public void setJobService(
		com.kisti.osp.icebreaker.service.JobService jobService) {
		this.jobService = jobService;
	}

	/**
	 * Returns the job persistence.
	 *
	 * @return the job persistence
	 */
	public JobPersistence getJobPersistence() {
		return jobPersistence;
	}

	/**
	 * Sets the job persistence.
	 *
	 * @param jobPersistence the job persistence
	 */
	public void setJobPersistence(JobPersistence jobPersistence) {
		this.jobPersistence = jobPersistence;
	}

	/**
	 * Returns the job data local service.
	 *
	 * @return the job data local service
	 */
	public com.kisti.osp.icebreaker.service.JobDataLocalService getJobDataLocalService() {
		return jobDataLocalService;
	}

	/**
	 * Sets the job data local service.
	 *
	 * @param jobDataLocalService the job data local service
	 */
	public void setJobDataLocalService(
		com.kisti.osp.icebreaker.service.JobDataLocalService jobDataLocalService) {
		this.jobDataLocalService = jobDataLocalService;
	}

	/**
	 * Returns the job data remote service.
	 *
	 * @return the job data remote service
	 */
	public com.kisti.osp.icebreaker.service.JobDataService getJobDataService() {
		return jobDataService;
	}

	/**
	 * Sets the job data remote service.
	 *
	 * @param jobDataService the job data remote service
	 */
	public void setJobDataService(
		com.kisti.osp.icebreaker.service.JobDataService jobDataService) {
		this.jobDataService = jobDataService;
	}

	/**
	 * Returns the job data persistence.
	 *
	 * @return the job data persistence
	 */
	public JobDataPersistence getJobDataPersistence() {
		return jobDataPersistence;
	}

	/**
	 * Sets the job data persistence.
	 *
	 * @param jobDataPersistence the job data persistence
	 */
	public void setJobDataPersistence(JobDataPersistence jobDataPersistence) {
		this.jobDataPersistence = jobDataPersistence;
	}

	/**
	 * Returns the osp scheduler entry local service.
	 *
	 * @return the osp scheduler entry local service
	 */
	public com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalService getOSPSchedulerEntryLocalService() {
		return ospSchedulerEntryLocalService;
	}

	/**
	 * Sets the osp scheduler entry local service.
	 *
	 * @param ospSchedulerEntryLocalService the osp scheduler entry local service
	 */
	public void setOSPSchedulerEntryLocalService(
		com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalService ospSchedulerEntryLocalService) {
		this.ospSchedulerEntryLocalService = ospSchedulerEntryLocalService;
	}

	/**
	 * Returns the osp scheduler entry remote service.
	 *
	 * @return the osp scheduler entry remote service
	 */
	public OSPSchedulerEntryService getOSPSchedulerEntryService() {
		return ospSchedulerEntryService;
	}

	/**
	 * Sets the osp scheduler entry remote service.
	 *
	 * @param ospSchedulerEntryService the osp scheduler entry remote service
	 */
	public void setOSPSchedulerEntryService(
		OSPSchedulerEntryService ospSchedulerEntryService) {
		this.ospSchedulerEntryService = ospSchedulerEntryService;
	}

	/**
	 * Returns the osp scheduler entry persistence.
	 *
	 * @return the osp scheduler entry persistence
	 */
	public OSPSchedulerEntryPersistence getOSPSchedulerEntryPersistence() {
		return ospSchedulerEntryPersistence;
	}

	/**
	 * Sets the osp scheduler entry persistence.
	 *
	 * @param ospSchedulerEntryPersistence the osp scheduler entry persistence
	 */
	public void setOSPSchedulerEntryPersistence(
		OSPSchedulerEntryPersistence ospSchedulerEntryPersistence) {
		this.ospSchedulerEntryPersistence = ospSchedulerEntryPersistence;
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
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService getClassNameService() {
		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.kernel.service.ClassNameService classNameService) {
		this.classNameService = classNameService;
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
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {
		this.userService = userService;
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

	/**
	 * Returns the asset entry local service.
	 *
	 * @return the asset entry local service
	 */
	public com.liferay.asset.kernel.service.AssetEntryLocalService getAssetEntryLocalService() {
		return assetEntryLocalService;
	}

	/**
	 * Sets the asset entry local service.
	 *
	 * @param assetEntryLocalService the asset entry local service
	 */
	public void setAssetEntryLocalService(
		com.liferay.asset.kernel.service.AssetEntryLocalService assetEntryLocalService) {
		this.assetEntryLocalService = assetEntryLocalService;
	}

	/**
	 * Returns the asset entry remote service.
	 *
	 * @return the asset entry remote service
	 */
	public com.liferay.asset.kernel.service.AssetEntryService getAssetEntryService() {
		return assetEntryService;
	}

	/**
	 * Sets the asset entry remote service.
	 *
	 * @param assetEntryService the asset entry remote service
	 */
	public void setAssetEntryService(
		com.liferay.asset.kernel.service.AssetEntryService assetEntryService) {
		this.assetEntryService = assetEntryService;
	}

	/**
	 * Returns the asset entry persistence.
	 *
	 * @return the asset entry persistence
	 */
	public AssetEntryPersistence getAssetEntryPersistence() {
		return assetEntryPersistence;
	}

	/**
	 * Sets the asset entry persistence.
	 *
	 * @param assetEntryPersistence the asset entry persistence
	 */
	public void setAssetEntryPersistence(
		AssetEntryPersistence assetEntryPersistence) {
		this.assetEntryPersistence = assetEntryPersistence;
	}

	/**
	 * Returns the asset link local service.
	 *
	 * @return the asset link local service
	 */
	public com.liferay.asset.kernel.service.AssetLinkLocalService getAssetLinkLocalService() {
		return assetLinkLocalService;
	}

	/**
	 * Sets the asset link local service.
	 *
	 * @param assetLinkLocalService the asset link local service
	 */
	public void setAssetLinkLocalService(
		com.liferay.asset.kernel.service.AssetLinkLocalService assetLinkLocalService) {
		this.assetLinkLocalService = assetLinkLocalService;
	}

	/**
	 * Returns the asset link persistence.
	 *
	 * @return the asset link persistence
	 */
	public AssetLinkPersistence getAssetLinkPersistence() {
		return assetLinkPersistence;
	}

	/**
	 * Sets the asset link persistence.
	 *
	 * @param assetLinkPersistence the asset link persistence
	 */
	public void setAssetLinkPersistence(
		AssetLinkPersistence assetLinkPersistence) {
		this.assetLinkPersistence = assetLinkPersistence;
	}

	/**
	 * Returns the asset tag local service.
	 *
	 * @return the asset tag local service
	 */
	public com.liferay.asset.kernel.service.AssetTagLocalService getAssetTagLocalService() {
		return assetTagLocalService;
	}

	/**
	 * Sets the asset tag local service.
	 *
	 * @param assetTagLocalService the asset tag local service
	 */
	public void setAssetTagLocalService(
		com.liferay.asset.kernel.service.AssetTagLocalService assetTagLocalService) {
		this.assetTagLocalService = assetTagLocalService;
	}

	/**
	 * Returns the asset tag remote service.
	 *
	 * @return the asset tag remote service
	 */
	public com.liferay.asset.kernel.service.AssetTagService getAssetTagService() {
		return assetTagService;
	}

	/**
	 * Sets the asset tag remote service.
	 *
	 * @param assetTagService the asset tag remote service
	 */
	public void setAssetTagService(
		com.liferay.asset.kernel.service.AssetTagService assetTagService) {
		this.assetTagService = assetTagService;
	}

	/**
	 * Returns the asset tag persistence.
	 *
	 * @return the asset tag persistence
	 */
	public AssetTagPersistence getAssetTagPersistence() {
		return assetTagPersistence;
	}

	/**
	 * Sets the asset tag persistence.
	 *
	 * @param assetTagPersistence the asset tag persistence
	 */
	public void setAssetTagPersistence(AssetTagPersistence assetTagPersistence) {
		this.assetTagPersistence = assetTagPersistence;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return OSPSchedulerEntryService.class.getName();
	}

	protected Class<?> getModelClass() {
		return OSPSchedulerEntry.class;
	}

	protected String getModelClassName() {
		return OSPSchedulerEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = ospSchedulerEntryPersistence.getDataSource();

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

	@BeanReference(type = com.kisti.osp.icebreaker.service.JobLocalService.class)
	protected com.kisti.osp.icebreaker.service.JobLocalService jobLocalService;
	@BeanReference(type = com.kisti.osp.icebreaker.service.JobService.class)
	protected com.kisti.osp.icebreaker.service.JobService jobService;
	@BeanReference(type = JobPersistence.class)
	protected JobPersistence jobPersistence;
	@BeanReference(type = com.kisti.osp.icebreaker.service.JobDataLocalService.class)
	protected com.kisti.osp.icebreaker.service.JobDataLocalService jobDataLocalService;
	@BeanReference(type = com.kisti.osp.icebreaker.service.JobDataService.class)
	protected com.kisti.osp.icebreaker.service.JobDataService jobDataService;
	@BeanReference(type = JobDataPersistence.class)
	protected JobDataPersistence jobDataPersistence;
	@BeanReference(type = com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalService.class)
	protected com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalService ospSchedulerEntryLocalService;
	@BeanReference(type = OSPSchedulerEntryService.class)
	protected OSPSchedulerEntryService ospSchedulerEntryService;
	@BeanReference(type = OSPSchedulerEntryPersistence.class)
	protected OSPSchedulerEntryPersistence ospSchedulerEntryPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameService.class)
	protected com.liferay.portal.kernel.service.ClassNameService classNameService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserService.class)
	protected com.liferay.portal.kernel.service.UserService userService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = com.liferay.asset.kernel.service.AssetEntryLocalService.class)
	protected com.liferay.asset.kernel.service.AssetEntryLocalService assetEntryLocalService;
	@ServiceReference(type = com.liferay.asset.kernel.service.AssetEntryService.class)
	protected com.liferay.asset.kernel.service.AssetEntryService assetEntryService;
	@ServiceReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;
	@ServiceReference(type = com.liferay.asset.kernel.service.AssetLinkLocalService.class)
	protected com.liferay.asset.kernel.service.AssetLinkLocalService assetLinkLocalService;
	@ServiceReference(type = AssetLinkPersistence.class)
	protected AssetLinkPersistence assetLinkPersistence;
	@ServiceReference(type = com.liferay.asset.kernel.service.AssetTagLocalService.class)
	protected com.liferay.asset.kernel.service.AssetTagLocalService assetTagLocalService;
	@ServiceReference(type = com.liferay.asset.kernel.service.AssetTagService.class)
	protected com.liferay.asset.kernel.service.AssetTagService assetTagService;
	@ServiceReference(type = AssetTagPersistence.class)
	protected AssetTagPersistence assetTagPersistence;
}