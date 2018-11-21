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

import com.kisti.osp.spyglass.model.LogPorts;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LogPorts in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LogPorts
 * @generated
 */
@ProviderType
public class LogPortsCacheModel implements CacheModel<LogPorts>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LogPortsCacheModel)) {
			return false;
		}

		LogPortsCacheModel logPortsCacheModel = (LogPortsCacheModel)obj;

		if (scienceAppId == logPortsCacheModel.scienceAppId) {
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
		sb.append(", logPorts=");
		sb.append(logPorts);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LogPorts toEntityModel() {
		LogPortsImpl logPortsImpl = new LogPortsImpl();

		logPortsImpl.setScienceAppId(scienceAppId);

		if (logPorts == null) {
			logPortsImpl.setLogPorts("");
		}
		else {
			logPortsImpl.setLogPorts(logPorts);
		}

		logPortsImpl.resetOriginalValues();

		return logPortsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		scienceAppId = objectInput.readLong();
		logPorts = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(scienceAppId);

		if (logPorts == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(logPorts);
		}
	}

	public long scienceAppId;
	public String logPorts;
}