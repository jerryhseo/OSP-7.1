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
public class AssignedSchedulerSoap implements Serializable {
	public static AssignedSchedulerSoap toSoapModel(AssignedScheduler model) {
		AssignedSchedulerSoap soapModel = new AssignedSchedulerSoap();

		soapModel.setScienceAppId(model.getScienceAppId());
		soapModel.setAssignedSchedulers(model.getAssignedSchedulers());

		return soapModel;
	}

	public static AssignedSchedulerSoap[] toSoapModels(
		AssignedScheduler[] models) {
		AssignedSchedulerSoap[] soapModels = new AssignedSchedulerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssignedSchedulerSoap[][] toSoapModels(
		AssignedScheduler[][] models) {
		AssignedSchedulerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssignedSchedulerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssignedSchedulerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssignedSchedulerSoap[] toSoapModels(
		List<AssignedScheduler> models) {
		List<AssignedSchedulerSoap> soapModels = new ArrayList<AssignedSchedulerSoap>(models.size());

		for (AssignedScheduler model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssignedSchedulerSoap[soapModels.size()]);
	}

	public AssignedSchedulerSoap() {
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

	public String getAssignedSchedulers() {
		return _assignedSchedulers;
	}

	public void setAssignedSchedulers(String assignedSchedulers) {
		_assignedSchedulers = assignedSchedulers;
	}

	private long _scienceAppId;
	private String _assignedSchedulers;
}