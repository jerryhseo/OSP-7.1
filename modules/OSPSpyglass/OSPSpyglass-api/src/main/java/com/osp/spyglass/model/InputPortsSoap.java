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

package com.osp.spyglass.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Jerry H. Seo
 * @generated
 */
@ProviderType
public class InputPortsSoap implements Serializable {
	public static InputPortsSoap toSoapModel(InputPorts model) {
		InputPortsSoap soapModel = new InputPortsSoap();

		soapModel.setScienceAppId(model.getScienceAppId());
		soapModel.setInputPorts(model.getInputPorts());

		return soapModel;
	}

	public static InputPortsSoap[] toSoapModels(InputPorts[] models) {
		InputPortsSoap[] soapModels = new InputPortsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static InputPortsSoap[][] toSoapModels(InputPorts[][] models) {
		InputPortsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new InputPortsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new InputPortsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static InputPortsSoap[] toSoapModels(List<InputPorts> models) {
		List<InputPortsSoap> soapModels = new ArrayList<InputPortsSoap>(models.size());

		for (InputPorts model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new InputPortsSoap[soapModels.size()]);
	}

	public InputPortsSoap() {
	}

	public long getPrimaryKey() {
		return _scienceAppId;
	}

	public void setPrimaryKey(long pk) {
		setScienceAppId(pk);
	}

	public long getScienceAppId() {
		return _scienceAppId;
	}

	public void setScienceAppId(long scienceAppId) {
		_scienceAppId = scienceAppId;
	}

	public String getInputPorts() {
		return _inputPorts;
	}

	public void setInputPorts(String inputPorts) {
		_inputPorts = inputPorts;
	}

	private long _scienceAppId;
	private String _inputPorts;
}