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

package com.kisti.osp.util.model;

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
 * This class is a wrapper for {@link Util}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Util
 * @generated
 */
@ProviderType
public class UtilWrapper implements Util, ModelWrapper<Util> {
	public UtilWrapper(Util util) {
		_util = util;
	}

	@Override
	public Class<?> getModelClass() {
		return Util.class;
	}

	@Override
	public String getModelClassName() {
		return Util.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("utilId", getUtilId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long utilId = (Long)attributes.get("utilId");

		if (utilId != null) {
			setUtilId(utilId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}
	}

	@Override
	public Object clone() {
		return new UtilWrapper((Util)_util.clone());
	}

	@Override
	public int compareTo(Util util) {
		return _util.compareTo(util);
	}

	/**
	* Returns the company ID of this util.
	*
	* @return the company ID of this util
	*/
	@Override
	public long getCompanyId() {
		return _util.getCompanyId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _util.getExpandoBridge();
	}

	/**
	* Returns the group ID of this util.
	*
	* @return the group ID of this util
	*/
	@Override
	public long getGroupId() {
		return _util.getGroupId();
	}

	/**
	* Returns the primary key of this util.
	*
	* @return the primary key of this util
	*/
	@Override
	public long getPrimaryKey() {
		return _util.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _util.getPrimaryKeyObj();
	}

	/**
	* Returns the util ID of this util.
	*
	* @return the util ID of this util
	*/
	@Override
	public long getUtilId() {
		return _util.getUtilId();
	}

	/**
	* Returns the uuid of this util.
	*
	* @return the uuid of this util
	*/
	@Override
	public String getUuid() {
		return _util.getUuid();
	}

	@Override
	public int hashCode() {
		return _util.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _util.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _util.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _util.isNew();
	}

	@Override
	public void persist() {
		_util.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_util.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this util.
	*
	* @param companyId the company ID of this util
	*/
	@Override
	public void setCompanyId(long companyId) {
		_util.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_util.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_util.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_util.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this util.
	*
	* @param groupId the group ID of this util
	*/
	@Override
	public void setGroupId(long groupId) {
		_util.setGroupId(groupId);
	}

	@Override
	public void setNew(boolean n) {
		_util.setNew(n);
	}

	/**
	* Sets the primary key of this util.
	*
	* @param primaryKey the primary key of this util
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_util.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_util.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the util ID of this util.
	*
	* @param utilId the util ID of this util
	*/
	@Override
	public void setUtilId(long utilId) {
		_util.setUtilId(utilId);
	}

	/**
	* Sets the uuid of this util.
	*
	* @param uuid the uuid of this util
	*/
	@Override
	public void setUuid(String uuid) {
		_util.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Util> toCacheModel() {
		return _util.toCacheModel();
	}

	@Override
	public Util toEscapedModel() {
		return new UtilWrapper(_util.toEscapedModel());
	}

	@Override
	public String toString() {
		return _util.toString();
	}

	@Override
	public Util toUnescapedModel() {
		return new UtilWrapper(_util.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _util.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UtilWrapper)) {
			return false;
		}

		UtilWrapper utilWrapper = (UtilWrapper)obj;

		if (Objects.equals(_util, utilWrapper._util)) {
			return true;
		}

		return false;
	}

	@Override
	public Util getWrappedModel() {
		return _util;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _util.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _util.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_util.resetOriginalValues();
	}

	private final Util _util;
}