package com.kisti.osp.icebreaker.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.osgi.service.component.annotations.Component;

import com.kisti.osp.icebreaker.model.OSPSchedulerEntry;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;


/**
 * 
 * @author Jerry H. Seo
 *
 *  Every CRUD Actions to the OSPSchedulerEntry arrive to this instance.
 */
@Component(
	    immediate = true,
	    service = ModelListener.class
)
public class OSPSchedulerEntryModelListener extends BaseModelListener<OSPSchedulerEntry> {
	static public List<OSPSchedulerEntry> createdQueue = new ArrayList<OSPSchedulerEntry>();
	static public List<OSPSchedulerEntry> updatedQueue = new ArrayList<OSPSchedulerEntry>();
	static public List<OSPSchedulerEntry> deletedQueue = new ArrayList<OSPSchedulerEntry>();
	
	static public List<OSPSchedulerEntry> getUpdatedModel( long groupId, long userId ){
		System.out.println("getUpdatedModel: "+groupId+", "+userId);
		List<OSPSchedulerEntry> list = new ArrayList<>();

		OSPSchedulerEntry entry = null;
		while( (entry = popUpdatedModel(updatedQueue, groupId, userId)) != null ){
			list.add(entry);
			System.out.println("Scheduler Name: "+entry.getName());
			System.out.println("Scheduler Version: "+entry.getVersion());
			System.out.println("Scheduler GroupId: "+entry.getGroupId());
			System.out.println("Scheduler User ID: "+entry.getUserId());
		}

		return list;
	}
	
	static private OSPSchedulerEntry popUpdatedModel( List<OSPSchedulerEntry> list, long groupId, long userId ){
		for( OSPSchedulerEntry entry : list ){
			if( entry.getGroupId() == groupId && entry.getUserId() == userId ){
				updatedQueue.remove(entry);
				return entry;
			}
		}
		
		return null;
	}
	
	@Override
	public void onAfterAddAssociation(Object classPK, String associationClassName, Object associationClassPK)
			throws ModelListenerException {
		System.out.println("onAfterAddAssociation");
		super.onAfterAddAssociation(classPK, associationClassName, associationClassPK);
	}

	@Override
	public void onAfterCreate(OSPSchedulerEntry model) throws ModelListenerException {
		System.out.println("onAfterCreate");
		super.onAfterCreate(model);
		
		this.createdQueue.add(model);
	}

	@Override
	public void onAfterRemove(OSPSchedulerEntry model) throws ModelListenerException {
		System.out.println("onAfterRemove");
		super.onAfterRemove(model);
		
		this.deletedQueue.add(model);
	}

	@Override
	public void onAfterRemoveAssociation(Object classPK, String associationClassName, Object associationClassPK)
			throws ModelListenerException {
		System.out.println("onAfterRemoveAssociation");
		super.onAfterRemoveAssociation(classPK, associationClassName, associationClassPK);
	}

	@Override
	public void onAfterUpdate(OSPSchedulerEntry model) throws ModelListenerException {
		System.out.println("onAfterUpdate");
		super.onAfterUpdate(model);
		
		this.updatedQueue.add(model);
		System.out.println("Updated Count: "+this.updatedQueue.size());
	}

	@Override
	public void onBeforeAddAssociation(Object classPK, String associationClassName, Object associationClassPK)
			throws ModelListenerException {
		System.out.println("onBeforeAddAssociation");
		super.onBeforeAddAssociation(classPK, associationClassName, associationClassPK);
	}

	@Override
	public void onBeforeCreate(OSPSchedulerEntry model) throws ModelListenerException {
		System.out.println("onBeforeCreate");
		super.onBeforeCreate(model);
	}

	@Override
	public void onBeforeRemove(OSPSchedulerEntry model) throws ModelListenerException {
		System.out.println("onBeforeRemove");
		super.onBeforeRemove(model);
	}

	@Override
	public void onBeforeRemoveAssociation(Object classPK, String associationClassName, Object associationClassPK)
			throws ModelListenerException {
		System.out.println("onBeforeRemoveAssociation");
		super.onBeforeRemoveAssociation(classPK, associationClassName, associationClassPK);
	}

	@Override
	public void onBeforeUpdate(OSPSchedulerEntry model) throws ModelListenerException {
		System.out.println("onBeforeUpdate");
		super.onBeforeUpdate(model);
	}
}
