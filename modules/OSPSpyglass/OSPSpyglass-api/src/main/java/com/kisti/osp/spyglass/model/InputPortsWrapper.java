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
 * This class is a wrapper for {@link InputPorts}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see InputPorts
 * @generated
 */
@ProviderType
public class InputPortsWrapper implements InputPorts, ModelWrapper<InputPorts> {
	public InputPortsWrapper(InputPorts inputPorts) {
		_inputPorts = inputPorts;
	}

	@Override
	public Class<?> getModelClass() {
		return InputPorts.class;
	}

	@Override
	public String getModelClassName() {
		return InputPorts.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("inputPorts", getInputPorts());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		String inputPorts = (String)attributes.get("inputPorts");

		if (inputPorts != null) {
			setInputPorts(inputPorts);
		}
	}

	@Override
	public InputPorts toEscapedModel() {
		return new InputPortsWrapper(_inputPorts.toEscapedModel());
	}

	@Override
	public InputPorts toUnescapedModel() {
		return new InputPortsWrapper(_inputPorts.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _inputPorts.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _inputPorts.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _inputPorts.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _inputPorts.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<InputPorts> toCacheModel() {
		return _inputPorts.toCacheModel();
	}

	@Override
	public int compareTo(InputPorts inputPorts) {
		return _inputPorts.compareTo(inputPorts);
	}

	@Override
	public int hashCode() {
		return _inputPorts.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _inputPorts.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new InputPortsWrapper((InputPorts)_inputPorts.clone());
	}

	/**
	* Returns the input ports of this input ports.
	*
	* @return the input ports of this input ports
	*/
	@Override
	public java.lang.String getInputPorts() {
		return _inputPorts.getInputPorts();
	}

	@Override
	public java.lang.String toString() {
		return _inputPorts.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _inputPorts.toXmlString();
	}

	/**
	* Returns the primary key of this input ports.
	*
	* @return the primary key of this input ports
	*/
	@Override
	public long getPrimaryKey() {
		return _inputPorts.getPrimaryKey();
	}

	/**
	* Returns the science app ID of this input ports.
	*
	* @return the science app ID of this input ports
	*/
	@Override
	public long getScienceAppId() {
		return _inputPorts.getScienceAppId();
	}

	@Override
	public void persist() {
		_inputPorts.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_inputPorts.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_inputPorts.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_inputPorts.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_inputPorts.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the input ports of this input ports.
	*
	* @param inputPorts the input ports of this input ports
	*/
	@Override
	public void setInputPorts(java.lang.String inputPorts) {
		_inputPorts.setInputPorts(inputPorts);
	}

	@Override
	public void setNew(boolean n) {
		_inputPorts.setNew(n);
	}

	/**
	* Sets the primary key of this input ports.
	*
	* @param primaryKey the primary key of this input ports
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_inputPorts.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_inputPorts.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the science app ID of this input ports.
	*
	* @param scienceAppId the science app ID of this input ports
	*/
	@Override
	public void setScienceAppId(long scienceAppId) {
		_inputPorts.setScienceAppId(scienceAppId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InputPortsWrapper)) {
			return false;
		}

		InputPortsWrapper inputPortsWrapper = (InputPortsWrapper)obj;

		if (Objects.equals(_inputPorts, inputPortsWrapper._inputPorts)) {
			return true;
		}

		return false;
	}

	@Override
	public InputPorts getWrappedModel() {
		return _inputPorts;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _inputPorts.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _inputPorts.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_inputPorts.resetOriginalValues();
	}

	private final InputPorts _inputPorts;
}