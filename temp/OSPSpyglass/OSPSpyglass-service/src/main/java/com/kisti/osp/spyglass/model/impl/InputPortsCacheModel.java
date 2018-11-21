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

import com.kisti.osp.spyglass.model.InputPorts;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing InputPorts in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see InputPorts
 * @generated
 */
@ProviderType
public class InputPortsCacheModel implements CacheModel<InputPorts>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InputPortsCacheModel)) {
			return false;
		}

		InputPortsCacheModel inputPortsCacheModel = (InputPortsCacheModel)obj;

		if (scienceAppId == inputPortsCacheModel.scienceAppId) {
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
		sb.append(", inputPorts=");
		sb.append(inputPorts);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public InputPorts toEntityModel() {
		InputPortsImpl inputPortsImpl = new InputPortsImpl();

		inputPortsImpl.setScienceAppId(scienceAppId);

		if (inputPorts == null) {
			inputPortsImpl.setInputPorts("");
		}
		else {
			inputPortsImpl.setInputPorts(inputPorts);
		}

		inputPortsImpl.resetOriginalValues();

		return inputPortsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		scienceAppId = objectInput.readLong();
		inputPorts = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(scienceAppId);

		if (inputPorts == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(inputPorts);
		}
	}

	public long scienceAppId;
	public String inputPorts;
}