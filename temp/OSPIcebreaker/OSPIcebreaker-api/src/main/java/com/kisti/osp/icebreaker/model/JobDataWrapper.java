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

package com.kisti.osp.icebreaker.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link JobData}.
 * </p>
 *
 * @author Jerry H. Seo
 * @see JobData
 * @generated
 */
@ProviderType
public class JobDataWrapper implements JobData, ModelWrapper<JobData> {
	public JobDataWrapper(JobData jobData) {
		_jobData = jobData;
	}

	@Override
	public Class<?> getModelClass() {
		return JobData.class;
	}

	@Override
	public String getModelClassName() {
		return JobData.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jobId", getJobId());
		attributes.put("inputData", getInputData());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long jobId = (Long)attributes.get("jobId");

		if (jobId != null) {
			setJobId(jobId);
		}

		String inputData = (String)attributes.get("inputData");

		if (inputData != null) {
			setInputData(inputData);
		}
	}

	@Override
	public Object clone() {
		return new JobDataWrapper((JobData)_jobData.clone());
	}

	@Override
	public int compareTo(JobData jobData) {
		return _jobData.compareTo(jobData);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _jobData.getExpandoBridge();
	}

	/**
	* Returns the input data of this job data.
	*
	* @return the input data of this job data
	*/
	@Override
	public String getInputData() {
		return _jobData.getInputData();
	}

	/**
	* Returns the job ID of this job data.
	*
	* @return the job ID of this job data
	*/
	@Override
	public long getJobId() {
		return _jobData.getJobId();
	}

	/**
	* Returns the primary key of this job data.
	*
	* @return the primary key of this job data
	*/
	@Override
	public long getPrimaryKey() {
		return _jobData.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _jobData.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _jobData.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _jobData.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _jobData.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _jobData.isNew();
	}

	@Override
	public void persist() {
		_jobData.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_jobData.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_jobData.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_jobData.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_jobData.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the input data of this job data.
	*
	* @param inputData the input data of this job data
	*/
	@Override
	public void setInputData(String inputData) {
		_jobData.setInputData(inputData);
	}

	/**
	* Sets the job ID of this job data.
	*
	* @param jobId the job ID of this job data
	*/
	@Override
	public void setJobId(long jobId) {
		_jobData.setJobId(jobId);
	}

	@Override
	public void setNew(boolean n) {
		_jobData.setNew(n);
	}

	/**
	* Sets the primary key of this job data.
	*
	* @param primaryKey the primary key of this job data
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_jobData.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_jobData.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<JobData> toCacheModel() {
		return _jobData.toCacheModel();
	}

	@Override
	public JobData toEscapedModel() {
		return new JobDataWrapper(_jobData.toEscapedModel());
	}

	@Override
	public String toString() {
		return _jobData.toString();
	}

	@Override
	public JobData toUnescapedModel() {
		return new JobDataWrapper(_jobData.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _jobData.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JobDataWrapper)) {
			return false;
		}

		JobDataWrapper jobDataWrapper = (JobDataWrapper)obj;

		if (Objects.equals(_jobData, jobDataWrapper._jobData)) {
			return true;
		}

		return false;
	}

	@Override
	public JobData getWrappedModel() {
		return _jobData;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _jobData.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _jobData.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_jobData.resetOriginalValues();
	}

	private final JobData _jobData;
}