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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class LogPortsSoap implements Serializable {
	public static LogPortsSoap toSoapModel(LogPorts model) {
		LogPortsSoap soapModel = new LogPortsSoap();

		soapModel.setScienceAppId(model.getScienceAppId());
		soapModel.setLogPorts(model.getLogPorts());

		return soapModel;
	}

	public static LogPortsSoap[] toSoapModels(LogPorts[] models) {
		LogPortsSoap[] soapModels = new LogPortsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LogPortsSoap[][] toSoapModels(LogPorts[][] models) {
		LogPortsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LogPortsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LogPortsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LogPortsSoap[] toSoapModels(List<LogPorts> models) {
		List<LogPortsSoap> soapModels = new ArrayList<LogPortsSoap>(models.size());

		for (LogPorts model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LogPortsSoap[soapModels.size()]);
	}

	public LogPortsSoap() {
	}

	public long getPrimaryKey() {
		return _scienceAppId;
	}

	public void setPrimaryKey(long pk) {
		setScienceAppId(pk);
	}

	public long getScienceAppId() {
		return _scienceAppId;
	}

	public void setScienceAppId(long scienceAppId) {
		_scienceAppId = scienceAppId;
	}

	public String getLogPorts() {
		return _logPorts;
	}

	public void setLogPorts(String logPorts) {
		_logPorts = logPorts;
	}

	private long _scienceAppId;
	private String _logPorts;
}