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

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Job}.
 * </p>
 *
 * @author Jerry H. Seo
 * @see Job
 * @generated
 */
@ProviderType
public class JobWrapper implements Job, ModelWrapper<Job> {
	public JobWrapper(Job job) {
		_job = job;
	}

	@Override
	public Class<?> getModelClass() {
		return Job.class;
	}

	@Override
	public String getModelClassName() {
		return Job.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("jobId", getJobId());
		attributes.put("jobName", getJobName());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("simulationId", getSimulationId());
		attributes.put("schedulerId", getSchedulerId());
		attributes.put("workflowId", getWorkflowId());
		attributes.put("solverExe", getSolverExe());
		attributes.put("workingDir", getWorkingDir());
		attributes.put("runType", getRunType());
		attributes.put("logFileName", getLogFileName());
		attributes.put("title", getTitle());
		attributes.put("queueName", getQueueName());
		attributes.put("command", getCommand());
		attributes.put("referenceFiles", getReferenceFiles());
		attributes.put("environmentVars", getEnvironmentVars());
		attributes.put("nodes", getNodes());
		attributes.put("processorsPerNode", getProcessorsPerNode());
		attributes.put("wallTime", getWallTime());
		attributes.put("eligibleTime", getEligibleTime());
		attributes.put("afterAny", getAfterAny());
		attributes.put("afterOk", getAfterOk());
		attributes.put("submitArgs", getSubmitArgs());
		attributes.put("status", getStatus());
		attributes.put("jobStatus", getJobStatus());
		attributes.put("reserveResults", isReserveResults());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("queuedDate", getQueuedDate());
		attributes.put("startDate", getStartDate());
		attributes.put("completeDate", getCompleteDate());
		attributes.put("usedMemory", getUsedMemory());
		attributes.put("usedCpuTime", getUsedCpuTime());
		attributes.put("usedWalltime", getUsedWalltime());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long jobId = (Long)attributes.get("jobId");

		if (jobId != null) {
			setJobId(jobId);
		}

		String jobName = (String)attributes.get("jobName");

		if (jobName != null) {
			setJobName(jobName);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long simulationId = (Long)attributes.get("simulationId");

		if (simulationId != null) {
			setSimulationId(simulationId);
		}

		Long schedulerId = (Long)attributes.get("schedulerId");

		if (schedulerId != null) {
			setSchedulerId(schedulerId);
		}

		Long workflowId = (Long)attributes.get("workflowId");

		if (workflowId != null) {
			setWorkflowId(workflowId);
		}

		String solverExe = (String)attributes.get("solverExe");

		if (solverExe != null) {
			setSolverExe(solverExe);
		}

		String workingDir = (String)attributes.get("workingDir");

		if (workingDir != null) {
			setWorkingDir(workingDir);
		}

		String runType = (String)attributes.get("runType");

		if (runType != null) {
			setRunType(runType);
		}

		String logFileName = (String)attributes.get("logFileName");

		if (logFileName != null) {
			setLogFileName(logFileName);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String queueName = (String)attributes.get("queueName");

		if (queueName != null) {
			setQueueName(queueName);
		}

		String command = (String)attributes.get("command");

		if (command != null) {
			setCommand(command);
		}

		String referenceFiles = (String)attributes.get("referenceFiles");

		if (referenceFiles != null) {
			setReferenceFiles(referenceFiles);
		}

		String environmentVars = (String)attributes.get("environmentVars");

		if (environmentVars != null) {
			setEnvironmentVars(environmentVars);
		}

		Integer nodes = (Integer)attributes.get("nodes");

		if (nodes != null) {
			setNodes(nodes);
		}

		Integer processorsPerNode = (Integer)attributes.get("processorsPerNode");

		if (processorsPerNode != null) {
			setProcessorsPerNode(processorsPerNode);
		}

		String wallTime = (String)attributes.get("wallTime");

		if (wallTime != null) {
			setWallTime(wallTime);
		}

		String eligibleTime = (String)attributes.get("eligibleTime");

		if (eligibleTime != null) {
			setEligibleTime(eligibleTime);
		}

		String afterAny = (String)attributes.get("afterAny");

		if (afterAny != null) {
			setAfterAny(afterAny);
		}

		String afterOk = (String)attributes.get("afterOk");

		if (afterOk != null) {
			setAfterOk(afterOk);
		}

		String submitArgs = (String)attributes.get("submitArgs");

		if (submitArgs != null) {
			setSubmitArgs(submitArgs);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Integer jobStatus = (Integer)attributes.get("jobStatus");

		if (jobStatus != null) {
			setJobStatus(jobStatus);
		}

		Boolean reserveResults = (Boolean)attributes.get("reserveResults");

		if (reserveResults != null) {
			setReserveResults(reserveResults);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Date queuedDate = (Date)attributes.get("queuedDate");

		if (queuedDate != null) {
			setQueuedDate(queuedDate);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date completeDate = (Date)attributes.get("completeDate");

		if (completeDate != null) {
			setCompleteDate(completeDate);
		}

		String usedMemory = (String)attributes.get("usedMemory");

		if (usedMemory != null) {
			setUsedMemory(usedMemory);
		}

		String usedCpuTime = (String)attributes.get("usedCpuTime");

		if (usedCpuTime != null) {
			setUsedCpuTime(usedCpuTime);
		}

		String usedWalltime = (String)attributes.get("usedWalltime");

		if (usedWalltime != null) {
			setUsedWalltime(usedWalltime);
		}
	}

	@Override
	public Object clone() {
		return new JobWrapper((Job)_job.clone());
	}

	@Override
	public int compareTo(Job job) {
		return _job.compareTo(job);
	}

	/**
	* Returns the after any of this job.
	*
	* @return the after any of this job
	*/
	@Override
	public String getAfterAny() {
		return _job.getAfterAny();
	}

	/**
	* Returns the after ok of this job.
	*
	* @return the after ok of this job
	*/
	@Override
	public String getAfterOk() {
		return _job.getAfterOk();
	}

	/**
	* Returns the command of this job.
	*
	* @return the command of this job
	*/
	@Override
	public String getCommand() {
		return _job.getCommand();
	}

	/**
	* Returns the company ID of this job.
	*
	* @return the company ID of this job
	*/
	@Override
	public long getCompanyId() {
		return _job.getCompanyId();
	}

	/**
	* Returns the complete date of this job.
	*
	* @return the complete date of this job
	*/
	@Override
	public Date getCompleteDate() {
		return _job.getCompleteDate();
	}

	/**
	* Returns the create date of this job.
	*
	* @return the create date of this job
	*/
	@Override
	public Date getCreateDate() {
		return _job.getCreateDate();
	}

	/**
	* Returns the eligible time of this job.
	*
	* @return the eligible time of this job
	*/
	@Override
	public String getEligibleTime() {
		return _job.getEligibleTime();
	}

	/**
	* Returns the environment vars of this job.
	*
	* @return the environment vars of this job
	*/
	@Override
	public String getEnvironmentVars() {
		return _job.getEnvironmentVars();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _job.getExpandoBridge();
	}

	/**
	* Returns the group ID of this job.
	*
	* @return the group ID of this job
	*/
	@Override
	public long getGroupId() {
		return _job.getGroupId();
	}

	/**
	* Returns the job ID of this job.
	*
	* @return the job ID of this job
	*/
	@Override
	public long getJobId() {
		return _job.getJobId();
	}

	/**
	* Returns the job name of this job.
	*
	* @return the job name of this job
	*/
	@Override
	public String getJobName() {
		return _job.getJobName();
	}

	/**
	* Returns the job status of this job.
	*
	* @return the job status of this job
	*/
	@Override
	public int getJobStatus() {
		return _job.getJobStatus();
	}

	/**
	* Returns the log file name of this job.
	*
	* @return the log file name of this job
	*/
	@Override
	public String getLogFileName() {
		return _job.getLogFileName();
	}

	/**
	* Returns the modified date of this job.
	*
	* @return the modified date of this job
	*/
	@Override
	public Date getModifiedDate() {
		return _job.getModifiedDate();
	}

	/**
	* Returns the nodes of this job.
	*
	* @return the nodes of this job
	*/
	@Override
	public int getNodes() {
		return _job.getNodes();
	}

	/**
	* Returns the primary key of this job.
	*
	* @return the primary key of this job
	*/
	@Override
	public long getPrimaryKey() {
		return _job.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _job.getPrimaryKeyObj();
	}

	/**
	* Returns the processors per node of this job.
	*
	* @return the processors per node of this job
	*/
	@Override
	public int getProcessorsPerNode() {
		return _job.getProcessorsPerNode();
	}

	/**
	* Returns the queued date of this job.
	*
	* @return the queued date of this job
	*/
	@Override
	public Date getQueuedDate() {
		return _job.getQueuedDate();
	}

	/**
	* Returns the queue name of this job.
	*
	* @return the queue name of this job
	*/
	@Override
	public String getQueueName() {
		return _job.getQueueName();
	}

	/**
	* Returns the reference files of this job.
	*
	* @return the reference files of this job
	*/
	@Override
	public String getReferenceFiles() {
		return _job.getReferenceFiles();
	}

	/**
	* Returns the reserve results of this job.
	*
	* @return the reserve results of this job
	*/
	@Override
	public boolean getReserveResults() {
		return _job.getReserveResults();
	}

	/**
	* Returns the run type of this job.
	*
	* @return the run type of this job
	*/
	@Override
	public String getRunType() {
		return _job.getRunType();
	}

	/**
	* Returns the scheduler ID of this job.
	*
	* @return the scheduler ID of this job
	*/
	@Override
	public long getSchedulerId() {
		return _job.getSchedulerId();
	}

	/**
	* Returns the simulation ID of this job.
	*
	* @return the simulation ID of this job
	*/
	@Override
	public long getSimulationId() {
		return _job.getSimulationId();
	}

	/**
	* Returns the solver exe of this job.
	*
	* @return the solver exe of this job
	*/
	@Override
	public String getSolverExe() {
		return _job.getSolverExe();
	}

	/**
	* Returns the start date of this job.
	*
	* @return the start date of this job
	*/
	@Override
	public Date getStartDate() {
		return _job.getStartDate();
	}

	/**
	* Returns the status of this job.
	*
	* @return the status of this job
	*/
	@Override
	public int getStatus() {
		return _job.getStatus();
	}

	/**
	* Returns the submit args of this job.
	*
	* @return the submit args of this job
	*/
	@Override
	public String getSubmitArgs() {
		return _job.getSubmitArgs();
	}

	/**
	* Returns the title of this job.
	*
	* @return the title of this job
	*/
	@Override
	public String getTitle() {
		return _job.getTitle();
	}

	/**
	* Returns the used cpu time of this job.
	*
	* @return the used cpu time of this job
	*/
	@Override
	public String getUsedCpuTime() {
		return _job.getUsedCpuTime();
	}

	/**
	* Returns the used memory of this job.
	*
	* @return the used memory of this job
	*/
	@Override
	public String getUsedMemory() {
		return _job.getUsedMemory();
	}

	/**
	* Returns the used walltime of this job.
	*
	* @return the used walltime of this job
	*/
	@Override
	public String getUsedWalltime() {
		return _job.getUsedWalltime();
	}

	/**
	* Returns the user ID of this job.
	*
	* @return the user ID of this job
	*/
	@Override
	public long getUserId() {
		return _job.getUserId();
	}

	/**
	* Returns the user uuid of this job.
	*
	* @return the user uuid of this job
	*/
	@Override
	public String getUserUuid() {
		return _job.getUserUuid();
	}

	/**
	* Returns the uuid of this job.
	*
	* @return the uuid of this job
	*/
	@Override
	public String getUuid() {
		return _job.getUuid();
	}

	/**
	* Returns the wall time of this job.
	*
	* @return the wall time of this job
	*/
	@Override
	public String getWallTime() {
		return _job.getWallTime();
	}

	/**
	* Returns the workflow ID of this job.
	*
	* @return the workflow ID of this job
	*/
	@Override
	public long getWorkflowId() {
		return _job.getWorkflowId();
	}

	/**
	* Returns the working dir of this job.
	*
	* @return the working dir of this job
	*/
	@Override
	public String getWorkingDir() {
		return _job.getWorkingDir();
	}

	@Override
	public int hashCode() {
		return _job.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _job.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _job.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _job.isNew();
	}

	/**
	* Returns <code>true</code> if this job is reserve results.
	*
	* @return <code>true</code> if this job is reserve results; <code>false</code> otherwise
	*/
	@Override
	public boolean isReserveResults() {
		return _job.isReserveResults();
	}

	@Override
	public void persist() {
		_job.persist();
	}

	/**
	* Sets the after any of this job.
	*
	* @param afterAny the after any of this job
	*/
	@Override
	public void setAfterAny(String afterAny) {
		_job.setAfterAny(afterAny);
	}

	/**
	* Sets the after ok of this job.
	*
	* @param afterOk the after ok of this job
	*/
	@Override
	public void setAfterOk(String afterOk) {
		_job.setAfterOk(afterOk);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_job.setCachedModel(cachedModel);
	}

	/**
	* Sets the command of this job.
	*
	* @param command the command of this job
	*/
	@Override
	public void setCommand(String command) {
		_job.setCommand(command);
	}

	/**
	* Sets the company ID of this job.
	*
	* @param companyId the company ID of this job
	*/
	@Override
	public void setCompanyId(long companyId) {
		_job.setCompanyId(companyId);
	}

	/**
	* Sets the complete date of this job.
	*
	* @param completeDate the complete date of this job
	*/
	@Override
	public void setCompleteDate(Date completeDate) {
		_job.setCompleteDate(completeDate);
	}

	/**
	* Sets the create date of this job.
	*
	* @param createDate the create date of this job
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_job.setCreateDate(createDate);
	}

	/**
	* Sets the eligible time of this job.
	*
	* @param eligibleTime the eligible time of this job
	*/
	@Override
	public void setEligibleTime(String eligibleTime) {
		_job.setEligibleTime(eligibleTime);
	}

	/**
	* Sets the environment vars of this job.
	*
	* @param environmentVars the environment vars of this job
	*/
	@Override
	public void setEnvironmentVars(String environmentVars) {
		_job.setEnvironmentVars(environmentVars);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_job.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_job.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_job.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this job.
	*
	* @param groupId the group ID of this job
	*/
	@Override
	public void setGroupId(long groupId) {
		_job.setGroupId(groupId);
	}

	/**
	* Sets the job ID of this job.
	*
	* @param jobId the job ID of this job
	*/
	@Override
	public void setJobId(long jobId) {
		_job.setJobId(jobId);
	}

	/**
	* Sets the job name of this job.
	*
	* @param jobName the job name of this job
	*/
	@Override
	public void setJobName(String jobName) {
		_job.setJobName(jobName);
	}

	/**
	* Sets the job status of this job.
	*
	* @param jobStatus the job status of this job
	*/
	@Override
	public void setJobStatus(int jobStatus) {
		_job.setJobStatus(jobStatus);
	}

	/**
	* Sets the log file name of this job.
	*
	* @param logFileName the log file name of this job
	*/
	@Override
	public void setLogFileName(String logFileName) {
		_job.setLogFileName(logFileName);
	}

	/**
	* Sets the modified date of this job.
	*
	* @param modifiedDate the modified date of this job
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_job.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_job.setNew(n);
	}

	/**
	* Sets the nodes of this job.
	*
	* @param nodes the nodes of this job
	*/
	@Override
	public void setNodes(int nodes) {
		_job.setNodes(nodes);
	}

	/**
	* Sets the primary key of this job.
	*
	* @param primaryKey the primary key of this job
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_job.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_job.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the processors per node of this job.
	*
	* @param processorsPerNode the processors per node of this job
	*/
	@Override
	public void setProcessorsPerNode(int processorsPerNode) {
		_job.setProcessorsPerNode(processorsPerNode);
	}

	/**
	* Sets the queued date of this job.
	*
	* @param queuedDate the queued date of this job
	*/
	@Override
	public void setQueuedDate(Date queuedDate) {
		_job.setQueuedDate(queuedDate);
	}

	/**
	* Sets the queue name of this job.
	*
	* @param queueName the queue name of this job
	*/
	@Override
	public void setQueueName(String queueName) {
		_job.setQueueName(queueName);
	}

	/**
	* Sets the reference files of this job.
	*
	* @param referenceFiles the reference files of this job
	*/
	@Override
	public void setReferenceFiles(String referenceFiles) {
		_job.setReferenceFiles(referenceFiles);
	}

	/**
	* Sets whether this job is reserve results.
	*
	* @param reserveResults the reserve results of this job
	*/
	@Override
	public void setReserveResults(boolean reserveResults) {
		_job.setReserveResults(reserveResults);
	}

	/**
	* Sets the run type of this job.
	*
	* @param runType the run type of this job
	*/
	@Override
	public void setRunType(String runType) {
		_job.setRunType(runType);
	}

	/**
	* Sets the scheduler ID of this job.
	*
	* @param schedulerId the scheduler ID of this job
	*/
	@Override
	public void setSchedulerId(long schedulerId) {
		_job.setSchedulerId(schedulerId);
	}

	/**
	* Sets the simulation ID of this job.
	*
	* @param simulationId the simulation ID of this job
	*/
	@Override
	public void setSimulationId(long simulationId) {
		_job.setSimulationId(simulationId);
	}

	/**
	* Sets the solver exe of this job.
	*
	* @param solverExe the solver exe of this job
	*/
	@Override
	public void setSolverExe(String solverExe) {
		_job.setSolverExe(solverExe);
	}

	/**
	* Sets the start date of this job.
	*
	* @param startDate the start date of this job
	*/
	@Override
	public void setStartDate(Date startDate) {
		_job.setStartDate(startDate);
	}

	/**
	* Sets the status of this job.
	*
	* @param status the status of this job
	*/
	@Override
	public void setStatus(int status) {
		_job.setStatus(status);
	}

	/**
	* Sets the submit args of this job.
	*
	* @param submitArgs the submit args of this job
	*/
	@Override
	public void setSubmitArgs(String submitArgs) {
		_job.setSubmitArgs(submitArgs);
	}

	/**
	* Sets the title of this job.
	*
	* @param title the title of this job
	*/
	@Override
	public void setTitle(String title) {
		_job.setTitle(title);
	}

	/**
	* Sets the used cpu time of this job.
	*
	* @param usedCpuTime the used cpu time of this job
	*/
	@Override
	public void setUsedCpuTime(String usedCpuTime) {
		_job.setUsedCpuTime(usedCpuTime);
	}

	/**
	* Sets the used memory of this job.
	*
	* @param usedMemory the used memory of this job
	*/
	@Override
	public void setUsedMemory(String usedMemory) {
		_job.setUsedMemory(usedMemory);
	}

	/**
	* Sets the used walltime of this job.
	*
	* @param usedWalltime the used walltime of this job
	*/
	@Override
	public void setUsedWalltime(String usedWalltime) {
		_job.setUsedWalltime(usedWalltime);
	}

	/**
	* Sets the user ID of this job.
	*
	* @param userId the user ID of this job
	*/
	@Override
	public void setUserId(long userId) {
		_job.setUserId(userId);
	}

	/**
	* Sets the user uuid of this job.
	*
	* @param userUuid the user uuid of this job
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_job.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this job.
	*
	* @param uuid the uuid of this job
	*/
	@Override
	public void setUuid(String uuid) {
		_job.setUuid(uuid);
	}

	/**
	* Sets the wall time of this job.
	*
	* @param wallTime the wall time of this job
	*/
	@Override
	public void setWallTime(String wallTime) {
		_job.setWallTime(wallTime);
	}

	/**
	* Sets the workflow ID of this job.
	*
	* @param workflowId the workflow ID of this job
	*/
	@Override
	public void setWorkflowId(long workflowId) {
		_job.setWorkflowId(workflowId);
	}

	/**
	* Sets the working dir of this job.
	*
	* @param workingDir the working dir of this job
	*/
	@Override
	public void setWorkingDir(String workingDir) {
		_job.setWorkingDir(workingDir);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Job> toCacheModel() {
		return _job.toCacheModel();
	}

	@Override
	public Job toEscapedModel() {
		return new JobWrapper(_job.toEscapedModel());
	}

	@Override
	public String toString() {
		return _job.toString();
	}

	@Override
	public Job toUnescapedModel() {
		return new JobWrapper(_job.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _job.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JobWrapper)) {
			return false;
		}

		JobWrapper jobWrapper = (JobWrapper)obj;

		if (Objects.equals(_job, jobWrapper._job)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _job.getStagedModelType();
	}

	@Override
	public Job getWrappedModel() {
		return _job;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _job.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _job.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_job.resetOriginalValues();
	}

	private final Job _job;
}