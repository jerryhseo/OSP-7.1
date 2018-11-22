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

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.osp.spyglass.model.ScienceApp;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ScienceApp in entity cache.
 *
 * @author Jerry H. Seo
 * @see ScienceApp
 * @generated
 */
@ProviderType
public class ScienceAppCacheModel implements CacheModel<ScienceApp>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ScienceAppCacheModel)) {
			return false;
		}

		ScienceAppCacheModel scienceAppCacheModel = (ScienceAppCacheModel)obj;

		if (scienceAppId == scienceAppCacheModel.scienceAppId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, scienceAppId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(49);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", scienceAppId=");
		sb.append(scienceAppId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", version=");
		sb.append(version);
		sb.append(", title=");
		sb.append(title);
		sb.append(", descriptionId=");
		sb.append(descriptionId);
		sb.append(", previousVersionId=");
		sb.append(previousVersionId);
		sb.append(", iconId=");
		sb.append(iconId);
		sb.append(", manualId=");
		sb.append(manualId);
		sb.append(", exeFileName=");
		sb.append(exeFileName);
		sb.append(", appType=");
		sb.append(appType);
		sb.append(", runType=");
		sb.append(runType);
		sb.append(", authorId=");
		sb.append(authorId);
		sb.append(", registerStage=");
		sb.append(registerStage);
		sb.append(", status=");
		sb.append(status);
		sb.append(", openLevel=");
		sb.append(openLevel);
		sb.append(", license=");
		sb.append(license);
		sb.append(", srcFileName=");
		sb.append(srcFileName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ScienceApp toEntityModel() {
		ScienceAppImpl scienceAppImpl = new ScienceAppImpl();

		if (uuid == null) {
			scienceAppImpl.setUuid("");
		}
		else {
			scienceAppImpl.setUuid(uuid);
		}

		scienceAppImpl.setScienceAppId(scienceAppId);
		scienceAppImpl.setCompanyId(companyId);
		scienceAppImpl.setGroupId(groupId);
		scienceAppImpl.setUserId(userId);

		if (userName == null) {
			scienceAppImpl.setUserName("");
		}
		else {
			scienceAppImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			scienceAppImpl.setCreateDate(null);
		}
		else {
			scienceAppImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			scienceAppImpl.setModifiedDate(null);
		}
		else {
			scienceAppImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			scienceAppImpl.setName("");
		}
		else {
			scienceAppImpl.setName(name);
		}

		if (version == null) {
			scienceAppImpl.setVersion("");
		}
		else {
			scienceAppImpl.setVersion(version);
		}

		if (title == null) {
			scienceAppImpl.setTitle("");
		}
		else {
			scienceAppImpl.setTitle(title);
		}

		if (descriptionId == null) {
			scienceAppImpl.setDescriptionId("");
		}
		else {
			scienceAppImpl.setDescriptionId(descriptionId);
		}

		scienceAppImpl.setPreviousVersionId(previousVersionId);

		if (iconId == null) {
			scienceAppImpl.setIconId("");
		}
		else {
			scienceAppImpl.setIconId(iconId);
		}

		if (manualId == null) {
			scienceAppImpl.setManualId("");
		}
		else {
			scienceAppImpl.setManualId(manualId);
		}

		if (exeFileName == null) {
			scienceAppImpl.setExeFileName("");
		}
		else {
			scienceAppImpl.setExeFileName(exeFileName);
		}

		if (appType == null) {
			scienceAppImpl.setAppType("");
		}
		else {
			scienceAppImpl.setAppType(appType);
		}

		if (runType == null) {
			scienceAppImpl.setRunType("");
		}
		else {
			scienceAppImpl.setRunType(runType);
		}

		scienceAppImpl.setAuthorId(authorId);

		if (registerStage == null) {
			scienceAppImpl.setRegisterStage("");
		}
		else {
			scienceAppImpl.setRegisterStage(registerStage);
		}

		scienceAppImpl.setStatus(status);

		if (openLevel == null) {
			scienceAppImpl.setOpenLevel("");
		}
		else {
			scienceAppImpl.setOpenLevel(openLevel);
		}

		if (license == null) {
			scienceAppImpl.setLicense("");
		}
		else {
			scienceAppImpl.setLicense(license);
		}

		if (srcFileName == null) {
			scienceAppImpl.setSrcFileName("");
		}
		else {
			scienceAppImpl.setSrcFileName(srcFileName);
		}

		scienceAppImpl.resetOriginalValues();

		return scienceAppImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		scienceAppId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		version = objectInput.readUTF();
		title = objectInput.readUTF();
		descriptionId = objectInput.readUTF();

		previousVersionId = objectInput.readLong();
		iconId = objectInput.readUTF();
		manualId = objectInput.readUTF();
		exeFileName = objectInput.readUTF();
		appType = objectInput.readUTF();
		runType = objectInput.readUTF();

		authorId = objectInput.readLong();
		registerStage = objectInput.readUTF();

		status = objectInput.readInt();
		openLevel = objectInput.readUTF();
		license = objectInput.readUTF();
		srcFileName = objectInput.readUTF();
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

		objectOutput.writeLong(scienceAppId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

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

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (descriptionId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(descriptionId);
		}

		objectOutput.writeLong(previousVersionId);

		if (iconId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(iconId);
		}

		if (manualId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(manualId);
		}

		if (exeFileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(exeFileName);
		}

		if (appType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(appType);
		}

		if (runType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(runType);
		}

		objectOutput.writeLong(authorId);

		if (registerStage == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(registerStage);
		}

		objectOutput.writeInt(status);

		if (openLevel == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(openLevel);
		}

		if (license == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(license);
		}

		if (srcFileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(srcFileName);
		}
	}

	public String uuid;
	public long scienceAppId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String version;
	public String title;
	public String descriptionId;
	public long previousVersionId;
	public String iconId;
	public String manualId;
	public String exeFileName;
	public String appType;
	public String runType;
	public long authorId;
	public String registerStage;
	public int status;
	public String openLevel;
	public String license;
	public String srcFileName;
}