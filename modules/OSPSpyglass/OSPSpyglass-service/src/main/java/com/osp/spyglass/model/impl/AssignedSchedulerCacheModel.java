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

import com.osp.spyglass.model.AssignedScheduler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AssignedScheduler in entity cache.
 *
 * @author Jerry H. Seo
 * @see AssignedScheduler
 * @generated
 */
@ProviderType
public class AssignedSchedulerCacheModel implements CacheModel<AssignedScheduler>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssignedSchedulerCacheModel)) {
			return false;
		}

		AssignedSchedulerCacheModel assignedSchedulerCacheModel = (AssignedSchedulerCacheModel)obj;

		if (scienceAppId == assignedSchedulerCacheModel.scienceAppId) {
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
		StringBundler sb = new StringBundler(5);

		sb.append("{scienceAppId=");
		sb.append(scienceAppId);
		sb.append(", assignedSchedulers=");
		sb.append(assignedSchedulers);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AssignedScheduler toEntityModel() {
		AssignedSchedulerImpl assignedSchedulerImpl = new AssignedSchedulerImpl();

		assignedSchedulerImpl.setScienceAppId(scienceAppId);

		if (assignedSchedulers == null) {
			assignedSchedulerImpl.setAssignedSchedulers("");
		}
		else {
			assignedSchedulerImpl.setAssignedSchedulers(assignedSchedulers);
		}

		assignedSchedulerImpl.resetOriginalValues();

		return assignedSchedulerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		scienceAppId = objectInput.readLong();
		assignedSchedulers = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(scienceAppId);

		if (assignedSchedulers == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(assignedSchedulers);
		}
	}

	public long scienceAppId;
	public String assignedSchedulers;
}