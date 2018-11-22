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

package com.osp.spyglass.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link AssignedScheduler}.
 * </p>
 *
 * @author Jerry H. Seo
 * @see AssignedScheduler
 * @generated
 */
@ProviderType
public class AssignedSchedulerWrapper implements AssignedScheduler,
	ModelWrapper<AssignedScheduler> {
	public AssignedSchedulerWrapper(AssignedScheduler assignedScheduler) {
		_assignedScheduler = assignedScheduler;
	}

	@Override
	public Class<?> getModelClass() {
		return AssignedScheduler.class;
	}

	@Override
	public String getModelClassName() {
		return AssignedScheduler.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("assignedSchedulers", getAssignedSchedulers());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		String assignedSchedulers = (String)attributes.get("assignedSchedulers");

		if (assignedSchedulers != null) {
			setAssignedSchedulers(assignedSchedulers);
		}
	}

	@Override
	public Object clone() {
		return new AssignedSchedulerWrapper((AssignedScheduler)_assignedScheduler.clone());
	}

	@Override
	public int compareTo(AssignedScheduler assignedScheduler) {
		return _assignedScheduler.compareTo(assignedScheduler);
	}

	/**
	* Returns the assigned schedulers of this assigned scheduler.
	*
	* @return the assigned schedulers of this assigned scheduler
	*/
	@Override
	public String getAssignedSchedulers() {
		return _assignedScheduler.getAssignedSchedulers();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _assignedScheduler.getExpandoBridge();
	}

	/**
	* Returns the primary key of this assigned scheduler.
	*
	* @return the primary key of this assigned scheduler
	*/
	@Override
	public long getPrimaryKey() {
		return _assignedScheduler.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assignedScheduler.getPrimaryKeyObj();
	}

	/**
	* Returns the science app ID of this assigned scheduler.
	*
	* @return the science app ID of this assigned scheduler
	*/
	@Override
	public long getScienceAppId() {
		return _assignedScheduler.getScienceAppId();
	}

	@Override
	public int hashCode() {
		return _assignedScheduler.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _assignedScheduler.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _assignedScheduler.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _assignedScheduler.isNew();
	}

	@Override
	public void persist() {
		_assignedScheduler.persist();
	}

	/**
	* Sets the assigned schedulers of this assigned scheduler.
	*
	* @param assignedSchedulers the assigned schedulers of this assigned scheduler
	*/
	@Override
	public void setAssignedSchedulers(String assignedSchedulers) {
		_assignedScheduler.setAssignedSchedulers(assignedSchedulers);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_assignedScheduler.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_assignedScheduler.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_assignedScheduler.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_assignedScheduler.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_assignedScheduler.setNew(n);
	}

	/**
	* Sets the primary key of this assigned scheduler.
	*
	* @param primaryKey the primary key of this assigned scheduler
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_assignedScheduler.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_assignedScheduler.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the science app ID of this assigned scheduler.
	*
	* @param scienceAppId the science app ID of this assigned scheduler
	*/
	@Override
	public void setScienceAppId(long scienceAppId) {
		_assignedScheduler.setScienceAppId(scienceAppId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AssignedScheduler> toCacheModel() {
		return _assignedScheduler.toCacheModel();
	}

	@Override
	public AssignedScheduler toEscapedModel() {
		return new AssignedSchedulerWrapper(_assignedScheduler.toEscapedModel());
	}

	@Override
	public String toString() {
		return _assignedScheduler.toString();
	}

	@Override
	public AssignedScheduler toUnescapedModel() {
		return new AssignedSchedulerWrapper(_assignedScheduler.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _assignedScheduler.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssignedSchedulerWrapper)) {
			return false;
		}

		AssignedSchedulerWrapper assignedSchedulerWrapper = (AssignedSchedulerWrapper)obj;

		if (Objects.equals(_assignedScheduler,
					assignedSchedulerWrapper._assignedScheduler)) {
			return true;
		}

		return false;
	}

	@Override
	public AssignedScheduler getWrappedModel() {
		return _assignedScheduler;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _assignedScheduler.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _assignedScheduler.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_assignedScheduler.resetOriginalValues();
	}

	private final AssignedScheduler _assignedScheduler;
}