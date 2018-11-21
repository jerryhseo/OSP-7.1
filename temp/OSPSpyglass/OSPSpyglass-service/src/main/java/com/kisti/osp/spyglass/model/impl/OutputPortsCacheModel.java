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

import com.kisti.osp.spyglass.model.OutputPorts;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing OutputPorts in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OutputPorts
 * @generated
 */
@ProviderType
public class OutputPortsCacheModel implements CacheModel<OutputPorts>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OutputPortsCacheModel)) {
			return false;
		}

		OutputPortsCacheModel outputPortsCacheModel = (OutputPortsCacheModel)obj;

		if (scienceAppId == outputPortsCacheModel.scienceAppId) {
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
		sb.append(", outputPorts=");
		sb.append(outputPorts);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OutputPorts toEntityModel() {
		OutputPortsImpl outputPortsImpl = new OutputPortsImpl();

		outputPortsImpl.setScienceAppId(scienceAppId);

		if (outputPorts == null) {
			outputPortsImpl.setOutputPorts("");
		}
		else {
			outputPortsImpl.setOutputPorts(outputPorts);
		}

		outputPortsImpl.resetOriginalValues();

		return outputPortsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		scienceAppId = objectInput.readLong();
		outputPorts = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(scienceAppId);

		if (outputPorts == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(outputPorts);
		}
	}

	public long scienceAppId;
	public String outputPorts;
}