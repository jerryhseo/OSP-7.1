package com.osp.spyglass.permissions;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.osp.spyglass.constants.SpyglassConstants;

@Component(
		immediate = true
)
public class SpyglassModelPermission{
	private static PortletResourcePermission _portletResourcePermission;
	@Reference(
			target = "(resource.name=" + SpyglassConstants.RESOURCE_NAME + ")",
			unbind = "-"
	)
	protected void setPortletResourcePermission(
			PortletResourcePermission portletResourcePermission) {
			_portletResourcePermission = portletResourcePermission;
	 }

    public static boolean contains(PermissionChecker permissionChecker,
            long groupId, String actionId) {

        return _portletResourcePermission.contains(permissionChecker, groupId, actionId);
    }
}
