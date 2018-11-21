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

package com.kisti.osp.spyglass.model;

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
 * This class is a wrapper for {@link Layout}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Layout
 * @generated
 */
@ProviderType
public class LayoutWrapper implements Layout, ModelWrapper<Layout> {
	public LayoutWrapper(Layout layout) {
		_layout = layout;
	}

	@Override
	public Class<?> getModelClass() {
		return Layout.class;
	}

	@Override
	public String getModelClassName() {
		return Layout.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("layout", getLayout());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		String layout = (String)attributes.get("layout");

		if (layout != null) {
			setLayout(layout);
		}
	}

	@Override
	public Object clone() {
		return new LayoutWrapper((Layout)_layout.clone());
	}

	@Override
	public int compareTo(Layout layout) {
		return _layout.compareTo(layout);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _layout.getExpandoBridge();
	}

	/**
	* Returns the layout of this layout.
	*
	* @return the layout of this layout
	*/
	@Override
	public String getLayout() {
		return _layout.getLayout();
	}

	/**
	* Returns the primary key of this layout.
	*
	* @return the primary key of this layout
	*/
	@Override
	public long getPrimaryKey() {
		return _layout.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _layout.getPrimaryKeyObj();
	}

	/**
	* Returns the science app ID of this layout.
	*
	* @return the science app ID of this layout
	*/
	@Override
	public long getScienceAppId() {
		return _layout.getScienceAppId();
	}

	@Override
	public int hashCode() {
		return _layout.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _layout.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _layout.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _layout.isNew();
	}

	@Override
	public void persist() {
		_layout.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_layout.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_layout.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_layout.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_layout.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the layout of this layout.
	*
	* @param layout the layout of this layout
	*/
	@Override
	public void setLayout(String layout) {
		_layout.setLayout(layout);
	}

	@Override
	public void setNew(boolean n) {
		_layout.setNew(n);
	}

	/**
	* Sets the primary key of this layout.
	*
	* @param primaryKey the primary key of this layout
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_layout.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_layout.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the science app ID of this layout.
	*
	* @param scienceAppId the science app ID of this layout
	*/
	@Override
	public void setScienceAppId(long scienceAppId) {
		_layout.setScienceAppId(scienceAppId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Layout> toCacheModel() {
		return _layout.toCacheModel();
	}

	@Override
	public Layout toEscapedModel() {
		return new LayoutWrapper(_layout.toEscapedModel());
	}

	@Override
	public String toString() {
		return _layout.toString();
	}

	@Override
	public Layout toUnescapedModel() {
		return new LayoutWrapper(_layout.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _layout.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LayoutWrapper)) {
			return false;
		}

		LayoutWrapper layoutWrapper = (LayoutWrapper)obj;

		if (Objects.equals(_layout, layoutWrapper._layout)) {
			return true;
		}

		return false;
	}

	@Override
	public Layout getWrappedModel() {
		return _layout;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _layout.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _layout.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_layout.resetOriginalValues();
	}

	private final Layout _layout;
}