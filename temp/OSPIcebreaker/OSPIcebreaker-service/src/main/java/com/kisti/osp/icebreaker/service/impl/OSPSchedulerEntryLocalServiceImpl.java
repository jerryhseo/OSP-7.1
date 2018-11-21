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

package com.kisti.osp.icebreaker.service.impl;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import com.kisti.osp.icebreaker.exception.NoSuchOSPSchedulerEntryException;
import com.kisti.osp.icebreaker.exception.OSPSchedulerException;
import com.kisti.osp.icebreaker.model.Job;
import com.kisti.osp.icebreaker.model.OSPScheduler;
import com.kisti.osp.icebreaker.model.OSPSchedulerEntry;
import com.kisti.osp.icebreaker.service.base.OSPSchedulerEntryLocalServiceBaseImpl;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * The implementation of the osp scheduler entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Jerry H. Seo
 * @see OSPSchedulerEntryLocalServiceBaseImpl
 * @see com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalServiceUtil
 */
public class OSPSchedulerEntryLocalServiceImpl
	extends OSPSchedulerEntryLocalServiceBaseImpl {
	
	@Indexable(type=IndexableType.REINDEX)
	public OSPSchedulerEntry addScheduler( 
			String name, 
			String version, 
			String serverIp,
			String sshPort,
			String schedulerClass,
			String accessMethod, 
			String authorizedUser, 
			String authorizedPassword,
			String description, 
			String templateDir,
			String templateRealDir,
			long[] permissionGroupIds,
			ServiceContext sc) throws SystemException, PortalException{
		this.validate(name, version, accessMethod, authorizedUser);

		long schedulerId = super.counterLocalService.increment();
		OSPSchedulerEntry scheduler = super.createOSPSchedulerEntry(schedulerId);
		
		scheduler.setUuid(sc.getUuid());
		scheduler.setName(name);
		scheduler.setVersion(version);
		scheduler.setServerIp(serverIp);
		scheduler.setSshPort(sshPort);
		scheduler.setSchedulerClass(schedulerClass);
		scheduler.setAccessMethod(accessMethod);
		scheduler.setAuthorizedUser(authorizedUser);
		scheduler.setAuthorizedPassword(authorizedPassword);
		scheduler.setDescription(description);
		scheduler.setStatus(WorkflowConstants.STATUS_APPROVED);
		
		Date now = new Date();
		
		scheduler.setCompanyId(sc.getCompanyId());
		scheduler.setGroupId(sc.getScopeGroupId());
		scheduler.setUserId(sc.getUserId());
		scheduler.setCreateDate(sc.getCreateDate(now));
		scheduler.setModifiedDate(sc.getModifiedDate(now));
		
		super.addOSPSchedulerEntry(scheduler);
		
		// for permisstions
		this.addSchedulerResource(scheduler);
		//this.updateSchedulerPermissions(scheduler, permissionGroupIds);
		this.updateSchedulerAsset(scheduler, sc);
		
		return scheduler;
	}
	
	@Indexable(type=IndexableType.REINDEX)
	public OSPSchedulerEntry updateScheduler( OSPSchedulerEntry scheduler, long[] permissionGroupIds, ServiceContext sc ) throws SystemException, PortalException{
		
		super.updateOSPSchedulerEntry(scheduler);
		this.updateSchedulerResource(scheduler, sc);
		
		if( permissionGroupIds != null ){
			this.updateSchedulerPermissions(scheduler, permissionGroupIds);
		}
		
		this.updateSchedulerAsset(scheduler, sc);
		
		return scheduler;
	}
	
	public OSPSchedulerEntry updateScheduler(
			long schedulerId,
			String name, 
			String version, 
			String serverIp,
			String sshPort,
			String schedulerClass,
			String accessMethod, 
			String authorizedUser, 
			String authorizedPassword,
			String description, 
			String templateDir,
			String templateRealDir,
			long[] permissionGroupIds,
			int status,
			ServiceContext sc ) throws PortalException, SystemException{

		OSPSchedulerEntry scheduler = super.getOSPSchedulerEntry(schedulerId);
		
		scheduler.setName(name);
		scheduler.setVersion(version);
		scheduler.setServerIp(serverIp);
		scheduler.setSshPort(sshPort);
		scheduler.setSchedulerClass(schedulerClass);
		scheduler.setAccessMethod(accessMethod);
		scheduler.setAuthorizedUser(authorizedUser);
		scheduler.setAuthorizedPassword(authorizedPassword);

		scheduler.setDescription(description);
		scheduler.setStatus(status);
		
		scheduler.setCompanyId(sc.getCompanyId());
		scheduler.setGroupId(sc.getScopeGroupId());
		scheduler.setUserId(sc.getUserId());
		scheduler.setModifiedDate(sc.getModifiedDate(new Date()));
		
		System.out.println("Update Scheduler: "+scheduler.getName());
		return this.updateScheduler(scheduler, permissionGroupIds, sc);
	}
	
	public void removeSchedulers( String name ) throws SystemException, PortalException{
		List<OSPSchedulerEntry> schedulers = super.ospSchedulerEntryPersistence.findByName(name);
		for( OSPSchedulerEntry scheduler : schedulers ){
			this.removeScheduler(name, scheduler.getVersion());
		}
	}
	
	@Indexable(type=IndexableType.DELETE)
	public OSPSchedulerEntry removeScheduler( String name, String version ) throws SystemException, PortalException{
		OSPSchedulerEntry scheduler = super.ospSchedulerEntryPersistence.findByNameVersion(name, version);
		
		super.resourceLocalService.deleteResource(
				scheduler.getCompanyId(), 
				OSPSchedulerEntry.class.getName(), 
				ResourceConstants.SCOPE_INDIVIDUAL, 
				scheduler.getSchedulerEntryId());
		AssetEntry assetEntry = super.assetEntryLocalService.fetchEntry(
                OSPSchedulerEntry.class.getName(), scheduler.getSchedulerEntryId());
		super.assetLinkLocalService.deleteLinks(assetEntry.getEntryId());
		super.assetEntryLocalService.deleteEntry(assetEntry);
		super.deleteOSPSchedulerEntry(scheduler);
		return scheduler;
	}
	
	@Indexable(type=IndexableType.DELETE)
	public OSPSchedulerEntry removeScheduler( long schedulerId ) throws PortalException, SystemException{
		OSPSchedulerEntry scheduler = super.getOSPSchedulerEntry(schedulerId);
		super.resourceLocalService.deleteResource(
				scheduler.getCompanyId(), 
				OSPSchedulerEntry.class.getName(), 
				ResourceConstants.SCOPE_INDIVIDUAL, 
				scheduler.getSchedulerEntryId());
		AssetEntry assetEntry = super.assetEntryLocalService.fetchEntry(
                OSPSchedulerEntry.class.getName(), scheduler.getSchedulerEntryId());
		super.assetLinkLocalService.deleteLinks(assetEntry.getEntryId());
		super.assetEntryLocalService.deleteEntry(assetEntry);
		
		super.deleteOSPSchedulerEntry(scheduler);
		
		return scheduler;
	}
	
	public JSONArray getSchedulersJson() throws SystemException{
		List<OSPSchedulerEntry> schedulers = super.ospSchedulerEntryPersistence.findAll();
		JSONArray schedulersJson = JSONFactoryUtil.createJSONArray();
		for( OSPSchedulerEntry scheduler : schedulers ){
			schedulersJson.put(this.toJsonArray(scheduler));
		}
		return schedulersJson;
	}
	
	public JSONArray getSchedulersJson(int start, int end) throws SystemException{
		List<OSPSchedulerEntry> schedulers = super.ospSchedulerEntryPersistence.findAll(start, end);
		JSONArray schedulersJson = JSONFactoryUtil.createJSONArray();
		for( OSPSchedulerEntry scheduler : schedulers ){
			schedulersJson.put(this.toJsonArray(scheduler));
		}

		return schedulersJson;
	}
	
	public int countSchedulers() throws SystemException{
		return super.ospSchedulerEntryPersistence.countAll();
	}
	
	public List<OSPSchedulerEntry> getSchedulers() throws SystemException{
		return super.ospSchedulerEntryPersistence.findAll();
	}
	
	public List<OSPSchedulerEntry> getSchedulers(int start, int end) throws SystemException{
		return super.ospSchedulerEntryPersistence.findAll( start, end );
	}
	
	public int countSchedulers( long groupId ) throws SystemException{
		return super.ospSchedulerEntryPersistence.countByGroupId(groupId);
	}
	
	public List<OSPSchedulerEntry> getSchedulers( long groupId ) throws SystemException{
		return super.ospSchedulerEntryPersistence.findByGroupId(groupId);
	}
	
	public List<OSPSchedulerEntry> getSchedulers( long groupId, int start, int end ) throws SystemException{
		return super.ospSchedulerEntryPersistence.findByGroupId(groupId, start, end);
	}
	
	public List<OSPSchedulerEntry> getSchedulers( String name ) throws SystemException{
		return  super.ospSchedulerEntryPersistence.findByName(name);
	}

	public List<OSPSchedulerEntry> getSchedulers( String name, int start, int end ) throws SystemException{
		return  super.ospSchedulerEntryPersistence.findByName(name, start, end);
	}
	
	public OSPSchedulerEntry getSchedulerEntry( String name, String version ) throws SystemException, NoSuchOSPSchedulerEntryException{
		return super.ospSchedulerEntryPersistence.findByNameVersion(name, version);
	}

	private void validate( String name, String version, String accessMethod, String authorizedUsers ) throws PortalException{
		if( Validator.isNull(name)){
			throw new OSPSchedulerException("name-must-consist-of alphnumeric.");
		}
	}
	
	public Job submitJob(
			Job job,
			ServiceContext sc) throws SystemException, PortalException, IOException {
		
		long schedulerId = job.getSchedulerId();
		if( schedulerId <= 0 ){
			throw new PortalException("Scheduler is not specified for the jab: "+job.getJobId());
		}
		OSPSchedulerEntry scheduler = super.getOSPSchedulerEntry(job.getSchedulerId());
		OSPScheduler ospScheduler = this.createOSPScheduler( 
				scheduler.getSchedulerClass(), 
				scheduler.getAuthorizedUser(),
				scheduler.getAuthorizedPassword(),
				scheduler.getServerIp(),
				scheduler.getSshPort());
		
		ospScheduler.nodes();
		
		return job;
	}
	
	public JSONArray toJsonArray( OSPSchedulerEntry scheduler ){
		JSONArray json = JSONFactoryUtil.createJSONArray();
		json.put(scheduler.getSchedulerEntryId());
		json.put(scheduler.getName());
		json.put(scheduler.getVersion());
		
		return json;
	}
	
	
	public void updateSchedulerPermissions( OSPSchedulerEntry scheduler, long[] permissionGroupIds ) throws PortalException, SystemException{
		if( permissionGroupIds == null )	return;
		
		for( long permissionGroupId : permissionGroupIds ){
			super.resourceLocalService.addResources(
					scheduler.getCompanyId(), 
					permissionGroupId, 
					scheduler.getUserId(), 
					OSPSchedulerEntry.class.getName(), 
					scheduler.getSchedulerEntryId(), 
					true, 
					true, 
					true);
		}
	}
	
	private void updateSchedulerAsset( OSPSchedulerEntry scheduler, ServiceContext sc ) throws PortalException, SystemException{
		long userId = scheduler.getUserId();
		long groupId = scheduler.getGroupId();
		Date createDate = scheduler.getCreateDate();
		Date modifiedDate = scheduler.getModifiedDate();
		String className = OSPSchedulerEntry.class.getName();
		long primaryKey = scheduler.getPrimaryKey();
		String classUuid = scheduler.getUuid();
		long classTypeId = 0; // identifies the particular variation of this class, if it has any variations. Otherwise, use 0.
		long[] categoryIds = null; // represent the categories selected for the entity.
		String[] tagNames = null; // represent the tags selected for the entity.
		boolean liatable = true; // specifies whether the entity can be shown in dynamic lists of content
		boolean visible = true; //  specifies whether the entity is approved.
	    Date startDate = null; //  the entity�셲 publish date. You can use it to specify when an Asset Publisher should show the entity�셲 content.
	    Date endDate = null; // the date the entity is taken down. You can use it to specify when an Asset Publisher should stop showing the entity�셲 content.
	    Date publishDate = null;
	    Date expirationDate = null; // the date the entity will no longer be shown.
	    String mimeType = ContentTypes.TEXT_PLAIN_UTF8; // the Multi-Purpose Internet Mail Extensions type, such as ContentTypes.TEXT_HTML, used for the content.
	    String title = scheduler.getName(); //  the entity�셲 name.
	    String description = scheduler.getDescription();
	    String summary = scheduler.getDescription();
	    String url = null;
	    String layoutUuid = null;
	    int height = 0;
	    int width = 0; 
	    Double priority = null;
		
		AssetEntry assetEntry = super.assetEntryLocalService.updateEntry(
				userId, 
				groupId, 
				createDate, 
				modifiedDate, 
				className, 
				primaryKey, 
				classUuid, 
				classTypeId, 
				categoryIds, 
				tagNames,
				liatable,
				visible, 
				startDate, 
				endDate, 
				publishDate,
				expirationDate, 
				mimeType, 
				title, 
				description, 
				summary, 
				url, 
				layoutUuid, 
				height, 
				width, 
				priority);
		
		/*
		Indexer<OSPSchedulerEntry> indexer = IndexerRegistryUtil.nullSafeGetIndexer(OSPSchedulerEntry.class);
		indexer.reindex(scheduler);
		*/
		super.assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
                 sc.getAssetLinkEntryIds(),
                 AssetLinkConstants.TYPE_RELATED);
		 
	}
	
	private void addSchedulerResource( OSPSchedulerEntry scheduler ) throws PortalException, SystemException{
		long companyId = scheduler.getCompanyId();
		long groupId = scheduler.getGroupId();
		long userId = scheduler.getUserId();
		String className = OSPSchedulerEntry.class.getName();
		long primaryKey = scheduler.getPrimaryKey(); 
		boolean portletActions = false;
		boolean addGroupPermisstions = true;
		boolean addGuestPermissions = true;
		
		super.resourceLocalService.addResources(
				companyId,
				groupId, 
				userId, 
				className, 
				primaryKey, 
				portletActions, 
				addGroupPermisstions, 
				addGuestPermissions);

	}
	
	private void updateSchedulerResource( OSPSchedulerEntry scheduler, ServiceContext sc) throws PortalException, SystemException{
		super.resourceLocalService.updateResources(
				sc.getCompanyId(),
				sc.getScopeGroupId(), 
				OSPSchedulerEntry.class.getName(), 
				scheduler.getSchedulerEntryId(),
				sc.getGroupPermissions(),
				sc.getGuestPermissions());
		
	}
	
	public OSPScheduler createOSPScheduler( String className, String user, String password, String ip, String port ){
        // Create class of type Base.
        Class<?> schedulerClass = null;
		try {
			schedulerClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        // Create constructor call with argument types.
        Constructor<?> ctr = null;
		try {
			ctr = schedulerClass.getConstructor(String.class, String.class, String.class, String.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
        // Finally create object of type Base and pass data to constructor.
        String[] arguments = {user, password, ip, port};
        Object object = null;
		try {
			object = ctr.newInstance((Object[])arguments);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
		        | InvocationTargetException e) {
			e.printStackTrace();
		}
        // Type-cast and access the data from class Base.
        return (OSPScheduler)object;
	}
	
	public OSPScheduler createOSPScheduler( long schedulerEntryId ) throws PortalException{
		OSPSchedulerEntry scheduler = super.getOSPSchedulerEntry(schedulerEntryId);
		
		return this.createOSPScheduler(
				scheduler.getSchedulerClass(), 
				scheduler.getAuthorizedUser(), 
				scheduler.getAuthorizedPassword(), 
				scheduler.getServerIp(), 
				scheduler.getSshPort()
				);
	}
}