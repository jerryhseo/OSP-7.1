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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.kisti.osp.util.service.http.UTILServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.kisti.osp.util.service.http.UTILServiceSoap
 * @generated
 */
@ProviderType
public class UTILSoap implements Serializable {
	public static UTILSoap toSoapModel(UTIL model) {
		UTILSoap soapModel = new UTILSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setUtilId(model.getUtilId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());

		return soapModel;
	}

	public static UTILSoap[] toSoapModels(UTIL[] models) {
		UTILSoap[] soapModels = new UTILSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UTILSoap[][] toSoapModels(UTIL[][] models) {
		UTILSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UTILSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UTILSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UTILSoap[] toSoapModels(List<UTIL> models) {
		List<UTILSoap> soapModels = new ArrayList<UTILSoap>(models.size());

		for (UTIL model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UTILSoap[soapModels.size()]);
	}

	public UTILSoap() {
	}

	public long getPrimaryKey() {
		return _utilId;
	}

	public void setPrimaryKey(long pk) {
		setUtilId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getUtilId() {
		return _utilId;
	}

	public void setUtilId(long utilId) {
		_utilId = utilId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	private String _uuid;
	private long _utilId;
	private long _groupId;
	private long _companyId;
}