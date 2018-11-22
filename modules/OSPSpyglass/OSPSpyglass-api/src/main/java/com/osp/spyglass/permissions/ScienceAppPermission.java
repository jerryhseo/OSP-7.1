package com.osp.spyglass.permissions;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.osp.spyglass.model.ScienceApp;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

@Component(
	    immediate = true
)
public class ScienceAppPermission{
    private static ModelResourcePermission<ScienceApp> _modelResourcePermission;
	
	@Reference(
	        target = "(model.class.name=com.osp.spyglass.model.ScienceApp)",
	        unbind = "-"
	)
	protected void setScienceAppModelPermission(
			ModelResourcePermission<ScienceApp> modelResourcePermission) {

	        _modelResourcePermission = modelResourcePermission;
	}
	
	public static boolean contains(
			PermissionChecker permissionChecker, ScienceApp scienceApp, String actionId) 
					throws PortalException {

		return _modelResourcePermission.contains(permissionChecker, scienceApp, actionId);
	}
	
	public static boolean contains( PermissionChecker permissionChecker, long scienceAppId, String actionId)
			throws PortalException, SystemException {

		return _modelResourcePermission.contains(permissionChecker, scienceAppId, actionId);
	}
}
