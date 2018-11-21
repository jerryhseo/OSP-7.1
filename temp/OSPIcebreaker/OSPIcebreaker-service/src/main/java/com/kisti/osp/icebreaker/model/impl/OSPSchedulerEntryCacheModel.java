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

package com.kisti.osp.icebreaker.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.kisti.osp.icebreaker.model.OSPSchedulerEntry;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing OSPSchedulerEntry in entity cache.
 *
 * @author Jerry H. Seo
 * @see OSPSchedulerEntry
 * @generated
 */
@ProviderType
public class OSPSchedulerEntryCacheModel implements CacheModel<OSPSchedulerEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OSPSchedulerEntryCacheModel)) {
			return false;
		}

		OSPSchedulerEntryCacheModel ospSchedulerEntryCacheModel = (OSPSchedulerEntryCacheModel)obj;

		if (schedulerEntryId == ospSchedulerEntryCacheModel.schedulerEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, schedulerEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", schedulerEntryId=");
		sb.append(schedulerEntryId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", version=");
		sb.append(version);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accessMethod=");
		sb.append(accessMethod);
		sb.append(", authorizedUser=");
		sb.append(authorizedUser);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", description=");
		sb.append(description);
		sb.append(", serverIp=");
		sb.append(serverIp);
		sb.append(", sshPort=");
		sb.append(sshPort);
		sb.append(", authorizedPassword=");
		sb.append(authorizedPassword);
		sb.append(", schedulerClass=");
		sb.append(schedulerClass);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OSPSchedulerEntry toEntityModel() {
		OSPSchedulerEntryImpl ospSchedulerEntryImpl = new OSPSchedulerEntryImpl();

		if (uuid == null) {
			ospSchedulerEntryImpl.setUuid("");
		}
		else {
			ospSchedulerEntryImpl.setUuid(uuid);
		}

		ospSchedulerEntryImpl.setSchedulerEntryId(schedulerEntryId);

		if (name == null) {
			ospSchedulerEntryImpl.setName("");
		}
		else {
			ospSchedulerEntryImpl.setName(name);
		}

		if (version == null) {
			ospSchedulerEntryImpl.setVersion("");
		}
		else {
			ospSchedulerEntryImpl.setVersion(version);
		}

		ospSchedulerEntryImpl.setGroupId(groupId);
		ospSchedulerEntryImpl.setCompanyId(companyId);
		ospSchedulerEntryImpl.setUserId(userId);

		if (userName == null) {
			ospSchedulerEntryImpl.setUserName("");
		}
		else {
			ospSchedulerEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ospSchedulerEntryImpl.setCreateDate(null);
		}
		else {
			ospSchedulerEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ospSchedulerEntryImpl.setModifiedDate(null);
		}
		else {
			ospSchedulerEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (accessMethod == null) {
			ospSchedulerEntryImpl.setAccessMethod("");
		}
		else {
			ospSchedulerEntryImpl.setAccessMethod(accessMethod);
		}

		if (authorizedUser == null) {
			ospSchedulerEntryImpl.setAuthorizedUser("");
		}
		else {
			ospSchedulerEntryImpl.setAuthorizedUser(authorizedUser);
		}

		ospSchedulerEntryImpl.setStatus(status);
		ospSchedulerEntryImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			ospSchedulerEntryImpl.setStatusByUserName("");
		}
		else {
			ospSchedulerEntryImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			ospSchedulerEntryImpl.setStatusDate(null);
		}
		else {
			ospSchedulerEntryImpl.setStatusDate(new Date(statusDate));
		}

		if (description == null) {
			ospSchedulerEntryImpl.setDescription("");
		}
		else {
			ospSchedulerEntryImpl.setDescription(description);
		}

		if (serverIp == null) {
			ospSchedulerEntryImpl.setServerIp("");
		}
		else {
			ospSchedulerEntryImpl.setServerIp(serverIp);
		}

		if (sshPort == null) {
			ospSchedulerEntryImpl.setSshPort("");
		}
		else {
			ospSchedulerEntryImpl.setSshPort(sshPort);
		}

		if (authorizedPassword == null) {
			ospSchedulerEntryImpl.setAuthorizedPassword("");
		}
		else {
			ospSchedulerEntryImpl.setAuthorizedPassword(authorizedPassword);
		}

		if (schedulerClass == null) {
			ospSchedulerEntryImpl.setSchedulerClass("");
		}
		else {
			ospSchedulerEntryImpl.setSchedulerClass(schedulerClass);
		}

		ospSchedulerEntryImpl.resetOriginalValues();

		return ospSchedulerEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		schedulerEntryId = objectInput.readLong();
		name = objectInput.readUTF();
		version = objectInput.readUTF();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		accessMethod = objectInput.readUTF();
		authorizedUser = objectInput.readUTF();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
		description = objectInput.readUTF();
		serverIp = objectInput.readUTF();
		sshPort = objectInput.readUTF();
		authorizedPassword = objectInput.readUTF();
		schedulerClass = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(schedulerEntryId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (version == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(version);
		}

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (accessMethod == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(accessMethod);
		}

		if (authorizedUser == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(authorizedUser);
		}

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (serverIp == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serverIp);
		}

		if (sshPort == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sshPort);
		}

		if (authorizedPassword == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(authorizedPassword);
		}

		if (schedulerClass == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(schedulerClass);
		}
	}

	public String uuid;
	public long schedulerEntryId;
	public String name;
	public String version;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String accessMethod;
	public String authorizedUser;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public String description;
	public String serverIp;
	public String sshPort;
	public String authorizedPassword;
	public String schedulerClass;
}