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

package com.kisti.osp.util.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.kisti.osp.util.model.UTIL;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing UTIL in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UTIL
 * @generated
 */
@ProviderType
public class UTILCacheModel implements CacheModel<UTIL>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UTILCacheModel)) {
			return false;
		}

		UTILCacheModel utilCacheModel = (UTILCacheModel)obj;

		if (utilId == utilCacheModel.utilId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, utilId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", utilId=");
		sb.append(utilId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UTIL toEntityModel() {
		UTILImpl utilImpl = new UTILImpl();

		if (uuid == null) {
			utilImpl.setUuid("");
		}
		else {
			utilImpl.setUuid(uuid);
		}

		utilImpl.setUtilId(utilId);
		utilImpl.setGroupId(groupId);
		utilImpl.setCompanyId(companyId);

		utilImpl.resetOriginalValues();

		return utilImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		utilId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
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

		objectOutput.writeLong(utilId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
	}

	public String uuid;
	public long utilId;
	public long groupId;
	public long companyId;
}