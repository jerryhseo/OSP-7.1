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
 * This class is a wrapper for {@link LogPorts}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LogPorts
 * @generated
 */
@ProviderType
public class LogPortsWrapper implements LogPorts, ModelWrapper<LogPorts> {
	public LogPortsWrapper(LogPorts logPorts) {
		_logPorts = logPorts;
	}

	@Override
	public Class<?> getModelClass() {
		return LogPorts.class;
	}

	@Override
	public String getModelClassName() {
		return LogPorts.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("logPorts", getLogPorts());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		String logPorts = (String)attributes.get("logPorts");

		if (logPorts != null) {
			setLogPorts(logPorts);
		}
	}

	@Override
	public LogPorts toEscapedModel() {
		return new LogPortsWrapper(_logPorts.toEscapedModel());
	}

	@Override
	public LogPorts toUnescapedModel() {
		return new LogPortsWrapper(_logPorts.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _logPorts.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _logPorts.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _logPorts.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _logPorts.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LogPorts> toCacheModel() {
		return _logPorts.toCacheModel();
	}

	@Override
	public int compareTo(LogPorts logPorts) {
		return _logPorts.compareTo(logPorts);
	}

	@Override
	public int hashCode() {
		return _logPorts.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _logPorts.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LogPortsWrapper((LogPorts)_logPorts.clone());
	}

	/**
	* Returns the log ports of this log ports.
	*
	* @return the log ports of this log ports
	*/
	@Override
	public java.lang.String getLogPorts() {
		return _logPorts.getLogPorts();
	}

	@Override
	public java.lang.String toString() {
		return _logPorts.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _logPorts.toXmlString();
	}

	/**
	* Returns the primary key of this log ports.
	*
	* @return the primary key of this log ports
	*/
	@Override
	public long getPrimaryKey() {
		return _logPorts.getPrimaryKey();
	}

	/**
	* Returns the science app ID of this log ports.
	*
	* @return the science app ID of this log ports
	*/
	@Override
	public long getScienceAppId() {
		return _logPorts.getScienceAppId();
	}

	@Override
	public void persist() {
		_logPorts.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_logPorts.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_logPorts.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_logPorts.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_logPorts.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the log ports of this log ports.
	*
	* @param logPorts the log ports of this log ports
	*/
	@Override
	public void setLogPorts(java.lang.String logPorts) {
		_logPorts.setLogPorts(logPorts);
	}

	@Override
	public void setNew(boolean n) {
		_logPorts.setNew(n);
	}

	/**
	* Sets the primary key of this log ports.
	*
	* @param primaryKey the primary key of this log ports
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_logPorts.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_logPorts.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the science app ID of this log ports.
	*
	* @param scienceAppId the science app ID of this log ports
	*/
	@Override
	public void setScienceAppId(long scienceAppId) {
		_logPorts.setScienceAppId(scienceAppId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LogPortsWrapper)) {
			return false;
		}

		LogPortsWrapper logPortsWrapper = (LogPortsWrapper)obj;

		if (Objects.equals(_logPorts, logPortsWrapper._logPorts)) {
			return true;
		}

		return false;
	}

	@Override
	public LogPorts getWrappedModel() {
		return _logPorts;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _logPorts.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _logPorts.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_logPorts.resetOriginalValues();
	}

	private final LogPorts _logPorts;
}