package com.osp.icebreaker.scheduler.manager.asset.renderer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kisti.osp.icebreaker.model.OSPSchedulerEntry;
import com.kisti.osp.icebreaker.permissions.SchedulerPermission;
import com.kisti.osp.icebreaker.constants.OSPIcebreakerPortletKeys;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.trash.TrashRenderer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.osp.icebreaker.scheduler.manager.constants.OSPSchedulerManagerConstants;

public class OSPSchedulerAssetRenderer extends BaseJSPAssetRenderer<OSPSchedulerEntry>{
	private final OSPSchedulerEntry _schedulerEntry;
	
	public OSPSchedulerAssetRenderer( OSPSchedulerEntry scheduler ){
		System.out.println("OSPSchedulerAssetRenderer ---------------------");
		this._schedulerEntry = scheduler;
	}

	@Override
	public OSPSchedulerEntry getAssetObject() {
		System.out.println("getAssetObject()");
		return this._schedulerEntry;
	}

	@Override
	public long getGroupId() {
		System.out.println("getGroupId(): "+this._schedulerEntry.getGroupId());
		return this._schedulerEntry.getGroupId();
	}

	@Override
	public long getUserId() {
		System.out.println("getUserId(): "+this._schedulerEntry.getUserId());
		return this._schedulerEntry.getUserId();
	}

	@Override
	public String getUserName() {
		System.out.println("getUserName(): "+this._schedulerEntry.getAuthorizedUser());
		User user;
		try {
			user = UserLocalServiceUtil.getUser(this._schedulerEntry.getUserId());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		return user.getFullName();
	}

	@Override
	public String getUuid() {
		System.out.println("getUuid(): "+this._schedulerEntry.getUuid());
		return this._schedulerEntry.getUuid();
	}

	@Override
	public String getClassName() {
		System.out.println("getClassName(): "+OSPSchedulerEntry.class.getName());
		return OSPSchedulerEntry.class.getName();
	}

	@Override
	public long getClassPK() {
		System.out.println("getClassPK(): "+this._schedulerEntry.getPrimaryKey());
		return this._schedulerEntry.getPrimaryKey();
	}

	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {

	    String summary = this._schedulerEntry.getName() + " V."+this._schedulerEntry.getVersion();
	    System.out.println("getSummary(): "+summary);
	    return summary;
	}

	@Override
	public String getTitle(Locale locale) {
		System.out.println("getTitle(): "+this._schedulerEntry.getName() + " V."+this._schedulerEntry.getVersion());
		return this._schedulerEntry.getName() + " V."+this._schedulerEntry.getVersion();
	}

	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		System.out.println("getJspPath(): "+template);
		if (template.equals(BaseJSPAssetRenderer.TEMPLATE_ABSTRACT) || 
			template.equals(BaseJSPAssetRenderer.TEMPLATE_FULL_CONTENT)) {
			return "/jsps/" + template + ".jsp";
		}
		else {
			return template;
		}
	}
	
	@Override
	public boolean include(HttpServletRequest request, HttpServletResponse response, String template) throws Exception {
		System.out.println("include(): "+template);
		request.setAttribute("scheduler", this._schedulerEntry);
	    request.setAttribute("HtmlUtil", HtmlUtil.getHtml());
	    request.setAttribute("StringUtil", new StringUtil());
		return super.include(request, response, template);
	}

	@Override
	public PortletURL getURLEdit(LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse) throws Exception {
		System.out.println("getURLEdit(): ");

		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
		        getControlPanelPlid(liferayPortletRequest), 
		        OSPIcebreakerPortletKeys.OSPSchedulerManager,
		        PortletRequest.RENDER_PHASE); 

	    portletURL.setProperty("mvcRenderCommandName", OSPSchedulerManagerConstants.MVC_RENDER_EDIT_SCHEDULER);
	    portletURL.setProperty("schedulerId", String.valueOf(this._schedulerEntry.getSchedulerEntryId()));
	    portletURL.setProperty("showback", Boolean.FALSE.toString());

	    return portletURL;
	}

	@Override
	public String getURLView(LiferayPortletResponse liferayPortletResponse, WindowState windowState) throws Exception {
		return super.getURLView(liferayPortletResponse, windowState);
	}

	@Override
	public String getURLViewInContext(LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, String noSuchEntryRedirect) throws Exception {
		System.out.println("getURLViewInContext(): ");
		try {
			long plid = PortalUtil.getPlidFromPortletId(this._schedulerEntry.getGroupId(), OSPIcebreakerPortletKeys.OSPSchedulerManager);

			PortletURL portletURL;
			if (plid == LayoutConstants.DEFAULT_PLID) {
				portletURL = liferayPortletResponse.createLiferayPortletURL(
						getControlPanelPlid(liferayPortletRequest),
						OSPIcebreakerPortletKeys.OSPSchedulerManager, 
						PortletRequest.RENDER_PHASE);
			} else {
				portletURL = PortletURLFactoryUtil.create(
						liferayPortletRequest,
						OSPIcebreakerPortletKeys.OSPSchedulerManager, 
						plid, 
						PortletRequest.RENDER_PHASE);
			}

			portletURL.setProperty("mvcRenderCommandName", OSPSchedulerManagerConstants.MVC_RENDER_VIEW_SCHEDULERS);
			portletURL.setProperty("schedulerId", String.valueOf(this._schedulerEntry.getSchedulerEntryId()));

			String currentUrl = PortalUtil.getCurrentURL(liferayPortletRequest);

			portletURL.setProperty("redirect", currentUrl);

			return portletURL.toString();

		} catch (PortalException e) {

		} catch (SystemException e) {
		}

		return noSuchEntryRedirect;
	}
	

	public boolean hasDeletePermission(PermissionChecker permissionChecker) throws SystemException, PortalException {
		System.out.println("hasDeletePermission(): ");
		return SchedulerPermission.contains(permissionChecker, this._schedulerEntry.getSchedulerEntryId(), 
				ActionKeys.DELETE);
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) throws PortalException {
		System.out.println("hasEditPermission(): ");
		return SchedulerPermission.contains(permissionChecker, this._schedulerEntry.getSchedulerEntryId(),  
				ActionKeys.UPDATE);
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) throws PortalException {
		System.out.println("hasViewPermission(): ");
		return SchedulerPermission.contains(permissionChecker, this._schedulerEntry.getSchedulerEntryId(),  
				ActionKeys.VIEW);
	}
}
