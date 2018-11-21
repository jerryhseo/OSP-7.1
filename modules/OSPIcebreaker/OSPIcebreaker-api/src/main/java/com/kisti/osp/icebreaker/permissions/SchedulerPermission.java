package com.kisti.osp.icebreaker.permissions;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.kisti.osp.icebreaker.model.OSPSchedulerEntry;
import com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

@Component(
	    immediate = true,
	    property = {
	    		"model.class.name=com.kisti.osp.icebreaker.model.OSPSchedulerEntry"}
	)
public class SchedulerPermission implements BaseModelPermissionChecker{
	@Reference(unbind = "-")
	protected void setOSPSchedulerEntryLocalService(OSPSchedulerEntryLocalService schedulerLocalService) {
		_schedulerLocalService = schedulerLocalService;
	}
	
	@Override
	public void checkBaseModel( PermissionChecker permissionChecker, long groupId, long schedulerId, String actionId) throws PortalException {
		check(permissionChecker, groupId, schedulerId, actionId);
	}

    private static OSPSchedulerEntryLocalService _schedulerLocalService;
	
	public static void check(PermissionChecker permissionChecker, long entryId, String actionId) 
			throws PortalException, SystemException {

		if (!contains(permissionChecker, entryId, actionId)) {
			throw new PrincipalException();
		}
	}
	
	public static void check( PermissionChecker permissionChecker, long groupId, long schedulerId, String actionId) 
			throws PortalException {

		if (!contains(permissionChecker, groupId, actionId)) {
			throw new PrincipalException.MustHavePermission(
					permissionChecker, 
					OSPSchedulerEntry.class.getName(), 
					actionId);
		}
	}
	
	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, long schedulerId, String actionId) 
					throws PortalException {

		return IcebreakerModelPermission.contains(permissionChecker, groupId, actionId);
	}
	
	public static boolean contains( PermissionChecker permissionChecker, long schedulerId, String actionId)
			throws PortalException, SystemException {

		OSPSchedulerEntry scheduler = _schedulerLocalService.getOSPSchedulerEntry(schedulerId);
		return contains(permissionChecker, scheduler, actionId);
	}

	public static boolean contains(PermissionChecker permissionChecker, OSPSchedulerEntry scheduler, String actionId) 
			throws PortalException, SystemException {

		return permissionChecker.hasPermission(
				scheduler.getGroupId(), OSPSchedulerEntry.class.getName(), scheduler.getSchedulerEntryId(), actionId);

	}
}
