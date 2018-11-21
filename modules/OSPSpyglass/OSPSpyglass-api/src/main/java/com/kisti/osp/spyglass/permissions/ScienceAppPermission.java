package com.kisti.osp.spyglass.permissions;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.kisti.osp.spyglass.model.ScienceApp;
import com.kisti.osp.spyglass.permissions.SpyglassModelPermission;
import com.kisti.osp.spyglass.service.ScienceAppLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

@Component(
	    immediate = true,
	    property = {
	    		"model.class.name=com.kisti.osp.spyglass.model.ScienceApp"}
)
public class ScienceAppPermission  implements BaseModelPermissionChecker{
	@Reference(unbind = "-")
	protected void setScienceAppLocalService(ScienceAppLocalService scienceAppLocalService) {
		_scienceAppLocalService = scienceAppLocalService;
	}
	
	@Override
	public void checkBaseModel( PermissionChecker permissionChecker, long groupId, long scienceAppId, String actionId) throws PortalException {
		check(permissionChecker, groupId, scienceAppId, actionId);
	}

    private static ScienceAppLocalService _scienceAppLocalService;
	
	public static void check(PermissionChecker permissionChecker, long entryId, String actionId) 
			throws PortalException, SystemException {

		if (!contains(permissionChecker, entryId, actionId)) {
			throw new PrincipalException();
		}
	}
	
	public static void check( PermissionChecker permissionChecker, long groupId, long scienceAppId, String actionId) 
			throws PortalException {

		if (!contains(permissionChecker, groupId, actionId)) {
			throw new PrincipalException.MustHavePermission(
					permissionChecker, 
					ScienceApp.class.getName(), 
					actionId);
		}
	}
	
	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, long scienceAppId, String actionId) 
					throws PortalException {

		return SpyglassModelPermission.contains(permissionChecker, groupId, actionId);
	}
	
	public static boolean contains( PermissionChecker permissionChecker, long scienceAppId, String actionId)
			throws PortalException, SystemException {

		ScienceApp scienceApp = _scienceAppLocalService.getScienceApp(scienceAppId);
		return contains(permissionChecker, scienceApp, actionId);
	}

	public static boolean contains(PermissionChecker permissionChecker, ScienceApp scienceApp, String actionId) 
			throws PortalException, SystemException {

		return permissionChecker.hasPermission(
				scienceApp.getGroupId(), ScienceApp.class.getName(), scienceApp.getScienceAppId(), actionId);

	}
}
