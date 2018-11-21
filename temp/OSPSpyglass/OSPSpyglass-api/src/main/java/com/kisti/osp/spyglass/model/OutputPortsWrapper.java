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
 * This class is a wrapper for {@link OutputPorts}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OutputPorts
 * @generated
 */
@ProviderType
public class OutputPortsWrapper implements OutputPorts,
	ModelWrapper<OutputPorts> {
	public OutputPortsWrapper(OutputPorts outputPorts) {
		_outputPorts = outputPorts;
	}

	@Override
	public Class<?> getModelClass() {
		return OutputPorts.class;
	}

	@Override
	public String getModelClassName() {
		return OutputPorts.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("outputPorts", getOutputPorts());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		String outputPorts = (String)attributes.get("outputPorts");

		if (outputPorts != null) {
			setOutputPorts(outputPorts);
		}
	}

	@Override
	public Object clone() {
		return new OutputPortsWrapper((OutputPorts)_outputPorts.clone());
	}

	@Override
	public int compareTo(OutputPorts outputPorts) {
		return _outputPorts.compareTo(outputPorts);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _outputPorts.getExpandoBridge();
	}

	/**
	* Returns the output ports of this output ports.
	*
	* @return the output ports of this output ports
	*/
	@Override
	public String getOutputPorts() {
		return _outputPorts.getOutputPorts();
	}

	/**
	* Returns the primary key of this output ports.
	*
	* @return the primary key of this output ports
	*/
	@Override
	public long getPrimaryKey() {
		return _outputPorts.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _outputPorts.getPrimaryKeyObj();
	}

	/**
	* Returns the science app ID of this output ports.
	*
	* @return the science app ID of this output ports
	*/
	@Override
	public long getScienceAppId() {
		return _outputPorts.getScienceAppId();
	}

	@Override
	public int hashCode() {
		return _outputPorts.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _outputPorts.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _outputPorts.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _outputPorts.isNew();
	}

	@Override
	public void persist() {
		_outputPorts.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_outputPorts.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_outputPorts.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_outputPorts.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_outputPorts.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_outputPorts.setNew(n);
	}

	/**
	* Sets the output ports of this output ports.
	*
	* @param outputPorts the output ports of this output ports
	*/
	@Override
	public void setOutputPorts(String outputPorts) {
		_outputPorts.setOutputPorts(outputPorts);
	}

	/**
	* Sets the primary key of this output ports.
	*
	* @param primaryKey the primary key of this output ports
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_outputPorts.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_outputPorts.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the science app ID of this output ports.
	*
	* @param scienceAppId the science app ID of this output ports
	*/
	@Override
	public void setScienceAppId(long scienceAppId) {
		_outputPorts.setScienceAppId(scienceAppId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<OutputPorts> toCacheModel() {
		return _outputPorts.toCacheModel();
	}

	@Override
	public OutputPorts toEscapedModel() {
		return new OutputPortsWrapper(_outputPorts.toEscapedModel());
	}

	@Override
	public String toString() {
		return _outputPorts.toString();
	}

	@Override
	public OutputPorts toUnescapedModel() {
		return new OutputPortsWrapper(_outputPorts.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _outputPorts.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OutputPortsWrapper)) {
			return false;
		}

		OutputPortsWrapper outputPortsWrapper = (OutputPortsWrapper)obj;

		if (Objects.equals(_outputPorts, outputPortsWrapper._outputPorts)) {
			return true;
		}

		return false;
	}

	@Override
	public OutputPorts getWrappedModel() {
		return _outputPorts;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _outputPorts.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _outputPorts.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_outputPorts.resetOriginalValues();
	}

	private final OutputPorts _outputPorts;
}