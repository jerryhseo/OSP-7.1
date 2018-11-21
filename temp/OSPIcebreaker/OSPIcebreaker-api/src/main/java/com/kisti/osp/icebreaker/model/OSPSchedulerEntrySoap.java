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

package com.kisti.osp.icebreaker.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.kisti.osp.icebreaker.service.http.OSPSchedulerEntryServiceSoap}.
 *
 * @author Jerry H. Seo
 * @see com.kisti.osp.icebreaker.service.http.OSPSchedulerEntryServiceSoap
 * @generated
 */
@ProviderType
public class OSPSchedulerEntrySoap implements Serializable {
	public static OSPSchedulerEntrySoap toSoapModel(OSPSchedulerEntry model) {
		OSPSchedulerEntrySoap soapModel = new OSPSchedulerEntrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setSchedulerEntryId(model.getSchedulerEntryId());
		soapModel.setName(model.getName());
		soapModel.setVersion(model.getVersion());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAccessMethod(model.getAccessMethod());
		soapModel.setAuthorizedUser(model.getAuthorizedUser());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setDescription(model.getDescription());
		soapModel.setServerIp(model.getServerIp());
		soapModel.setSshPort(model.getSshPort());
		soapModel.setAuthorizedPassword(model.getAuthorizedPassword());
		soapModel.setSchedulerClass(model.getSchedulerClass());

		return soapModel;
	}

	public static OSPSchedulerEntrySoap[] toSoapModels(
		OSPSchedulerEntry[] models) {
		OSPSchedulerEntrySoap[] soapModels = new OSPSchedulerEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OSPSchedulerEntrySoap[][] toSoapModels(
		OSPSchedulerEntry[][] models) {
		OSPSchedulerEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OSPSchedulerEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new OSPSchedulerEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OSPSchedulerEntrySoap[] toSoapModels(
		List<OSPSchedulerEntry> models) {
		List<OSPSchedulerEntrySoap> soapModels = new ArrayList<OSPSchedulerEntrySoap>(models.size());

		for (OSPSchedulerEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OSPSchedulerEntrySoap[soapModels.size()]);
	}

	public OSPSchedulerEntrySoap() {
	}

	public long getPrimaryKey() {
		return _schedulerEntryId;
	}

	public void setPrimaryKey(long pk) {
		setSchedulerEntryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getSchedulerEntryId() {
		return _schedulerEntryId;
	}

	public void setSchedulerEntryId(long schedulerEntryId) {
		_schedulerEntryId = schedulerEntryId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getVersion() {
		return _version;
	}

	public void setVersion(String version) {
		_version = version;
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

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getAccessMethod() {
		return _accessMethod;
	}

	public void setAccessMethod(String accessMethod) {
		_accessMethod = accessMethod;
	}

	public String getAuthorizedUser() {
		return _authorizedUser;
	}

	public void setAuthorizedUser(String authorizedUser) {
		_authorizedUser = authorizedUser;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getServerIp() {
		return _serverIp;
	}

	public void setServerIp(String serverIp) {
		_serverIp = serverIp;
	}

	public String getSshPort() {
		return _sshPort;
	}

	public void setSshPort(String sshPort) {
		_sshPort = sshPort;
	}

	public String getAuthorizedPassword() {
		return _authorizedPassword;
	}

	public void setAuthorizedPassword(String authorizedPassword) {
		_authorizedPassword = authorizedPassword;
	}

	public String getSchedulerClass() {
		return _schedulerClass;
	}

	public void setSchedulerClass(String schedulerClass) {
		_schedulerClass = schedulerClass;
	}

	private String _uuid;
	private long _schedulerEntryId;
	private String _name;
	private String _version;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _accessMethod;
	private String _authorizedUser;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _description;
	private String _serverIp;
	private String _sshPort;
	private String _authorizedPassword;
	private String _schedulerClass;
}