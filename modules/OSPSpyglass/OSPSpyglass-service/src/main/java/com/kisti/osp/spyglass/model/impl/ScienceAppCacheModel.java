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

package com.kisti.osp.spyglass.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.kisti.osp.spyglass.model.ScienceApp;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ScienceApp in entity cache.
 *
 * @author Brian Wing Shun Chan
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
		StringBundler sb = new StringBundler(53);

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
		sb.append(", stage=");
		sb.append(stage);
		sb.append(", status=");
		sb.append(status);
		sb.append(", parallelModule=");
		sb.append(parallelModule);
		sb.append(", openLevel=");
		sb.append(openLevel);
		sb.append(", license=");
		sb.append(license);
		sb.append(", srcFileName=");
		sb.append(srcFileName);
		sb.append(", languages=");
		sb.append(languages);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ScienceApp toEntityModel() {
		ScienceAppImpl scienceAppImpl = new ScienceAppImpl();

		if (uuid == null) {
			scienceAppImpl.setUuid(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setUuid(uuid);
		}

		scienceAppImpl.setScienceAppId(scienceAppId);
		scienceAppImpl.setCompanyId(companyId);
		scienceAppImpl.setGroupId(groupId);
		scienceAppImpl.setUserId(userId);

		if (userName == null) {
			scienceAppImpl.setUserName(StringPool.BLANK);
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
			scienceAppImpl.setName(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setName(name);
		}

		if (version == null) {
			scienceAppImpl.setVersion(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setVersion(version);
		}

		if (title == null) {
			scienceAppImpl.setTitle(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setTitle(title);
		}

		if (descriptionId == null) {
			scienceAppImpl.setDescriptionId(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setDescriptionId(descriptionId);
		}

		scienceAppImpl.setPreviousVersionId(previousVersionId);

		if (iconId == null) {
			scienceAppImpl.setIconId(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setIconId(iconId);
		}

		if (manualId == null) {
			scienceAppImpl.setManualId(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setManualId(manualId);
		}

		if (exeFileName == null) {
			scienceAppImpl.setExeFileName(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setExeFileName(exeFileName);
		}

		if (appType == null) {
			scienceAppImpl.setAppType(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setAppType(appType);
		}

		if (runType == null) {
			scienceAppImpl.setRunType(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setRunType(runType);
		}

		scienceAppImpl.setAuthorId(authorId);

		if (stage == null) {
			scienceAppImpl.setStage(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setStage(stage);
		}

		scienceAppImpl.setStatus(status);

		if (parallelModule == null) {
			scienceAppImpl.setParallelModule(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setParallelModule(parallelModule);
		}

		if (openLevel == null) {
			scienceAppImpl.setOpenLevel(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setOpenLevel(openLevel);
		}

		if (license == null) {
			scienceAppImpl.setLicense(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setLicense(license);
		}

		if (srcFileName == null) {
			scienceAppImpl.setSrcFileName(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setSrcFileName(srcFileName);
		}

		if (languages == null) {
			scienceAppImpl.setLanguages(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setLanguages(languages);
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
		stage = objectInput.readUTF();

		status = objectInput.readInt();
		parallelModule = objectInput.readUTF();
		openLevel = objectInput.readUTF();
		license = objectInput.readUTF();
		srcFileName = objectInput.readUTF();
		languages = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(scienceAppId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (version == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(version);
		}

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (descriptionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(descriptionId);
		}

		objectOutput.writeLong(previousVersionId);

		if (iconId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(iconId);
		}

		if (manualId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(manualId);
		}

		if (exeFileName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(exeFileName);
		}

		if (appType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(appType);
		}

		if (runType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(runType);
		}

		objectOutput.writeLong(authorId);

		if (stage == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(stage);
		}

		objectOutput.writeInt(status);

		if (parallelModule == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parallelModule);
		}

		if (openLevel == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(openLevel);
		}

		if (license == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(license);
		}

		if (srcFileName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(srcFileName);
		}

		if (languages == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(languages);
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
	public String stage;
	public int status;
	public String parallelModule;
	public String openLevel;
	public String license;
	public String srcFileName;
	public String languages;
}