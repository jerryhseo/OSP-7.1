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

package com.osp.spyglass.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.osp.spyglass.model.LogPorts;
import com.osp.spyglass.model.LogPortsModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the LogPorts service. Represents a row in the &quot;SPYGLASS_LogPorts&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link LogPortsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LogPortsImpl}.
 * </p>
 *
 * @author Jerry H. Seo
 * @see LogPortsImpl
 * @see LogPorts
 * @see LogPortsModel
 * @generated
 */
@ProviderType
public class LogPortsModelImpl extends BaseModelImpl<LogPorts>
	implements LogPortsModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a log ports model instance should use the {@link LogPorts} interface instead.
	 */
	public static final String TABLE_NAME = "SPYGLASS_LogPorts";
	public static final Object[][] TABLE_COLUMNS = {
			{ "scienceAppId", Types.BIGINT },
			{ "logPorts", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("scienceAppId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("logPorts", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table SPYGLASS_LogPorts (scienceAppId LONG not null primary key,logPorts VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table SPYGLASS_LogPorts";
	public static final String ORDER_BY_JPQL = " ORDER BY logPorts.scienceAppId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY SPYGLASS_LogPorts.scienceAppId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.osp.spyglass.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.osp.spyglass.model.LogPorts"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.osp.spyglass.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.osp.spyglass.model.LogPorts"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.osp.spyglass.service.util.ServiceProps.get(
				"lock.expiration.time.com.osp.spyglass.model.LogPorts"));

	public LogPortsModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _scienceAppId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setScienceAppId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _scienceAppId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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
	public long getScienceAppId() {
		return _scienceAppId;
	}

	@Override
	public void setScienceAppId(long scienceAppId) {
		_scienceAppId = scienceAppId;
	}

	@Override
	public String getLogPorts() {
		if (_logPorts == null) {
			return "";
		}
		else {
			return _logPorts;
		}
	}

	@Override
	public void setLogPorts(String logPorts) {
		_logPorts = logPorts;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			LogPorts.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LogPorts toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (LogPorts)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		LogPortsImpl logPortsImpl = new LogPortsImpl();

		logPortsImpl.setScienceAppId(getScienceAppId());
		logPortsImpl.setLogPorts(getLogPorts());

		logPortsImpl.resetOriginalValues();

		return logPortsImpl;
	}

	@Override
	public int compareTo(LogPorts logPorts) {
		long primaryKey = logPorts.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LogPorts)) {
			return false;
		}

		LogPorts logPorts = (LogPorts)obj;

		long primaryKey = logPorts.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
	}

	@Override
	public CacheModel<LogPorts> toCacheModel() {
		LogPortsCacheModel logPortsCacheModel = new LogPortsCacheModel();

		logPortsCacheModel.scienceAppId = getScienceAppId();

		logPortsCacheModel.logPorts = getLogPorts();

		String logPorts = logPortsCacheModel.logPorts;

		if ((logPorts != null) && (logPorts.length() == 0)) {
			logPortsCacheModel.logPorts = null;
		}

		return logPortsCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{scienceAppId=");
		sb.append(getScienceAppId());
		sb.append(", logPorts=");
		sb.append(getLogPorts());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(10);

		sb.append("<model><model-name>");
		sb.append("com.osp.spyglass.model.LogPorts");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>scienceAppId</column-name><column-value><![CDATA[");
		sb.append(getScienceAppId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>logPorts</column-name><column-value><![CDATA[");
		sb.append(getLogPorts());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = LogPorts.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			LogPorts.class, ModelWrapper.class
		};
	private long _scienceAppId;
	private String _logPorts;
	private LogPorts _escapedModel;
}