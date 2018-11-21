package com.kisti.osp.spyglass.asset;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kisti.osp.spyglass.model.ScienceApp;
import com.kisti.osp.spyglass.permissions.ScienceAppPermission;
import com.kisti.osp.spyglass.web.constants.OSPSpyglassWebPortletKeys;
import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;

public class ScienceAppAssetRenderer extends BaseJSPAssetRenderer<ScienceApp>{
	private final ScienceApp _scienceApp;
	
	public ScienceAppAssetRenderer( ScienceApp scheduler ){
		System.out.println("OSPSchedulerAssetRenderer ---------------------");
		this._scienceApp = scheduler;
	}

	@Override
	public ScienceApp getAssetObject() {
		System.out.println("getAssetObject()");
		return this._scienceApp;
	}

	@Override
	public long getGroupId() {
		System.out.println("getGroupId(): "+this._scienceApp.getGroupId());
		return this._scienceApp.getGroupId();
	}

	@Override
	public long getUserId() {
		System.out.println("getUserId(): "+this._scienceApp.getUserId());
		return this._scienceApp.getUserId();
	}

	@Override
	public String getUserName() {
		System.out.println("getUserName(): "+this._scienceApp.getAuthorId());
		User user;
		try {
			user = UserLocalServiceUtil.getUser(this._scienceApp.getUserId());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		return user.getFullName();
	}

	@Override
	public String getUuid() {
		System.out.println("getUuid(): "+this._scienceApp.getUuid());
		return this._scienceApp.getUuid();
	}

	@Override
	public String getClassName() {
		System.out.println("getClassName(): "+ScienceApp.class.getName());
		return ScienceApp.class.getName();
	}

	@Override
	public long getClassPK() {
		System.out.println("getClassPK(): "+this._scienceApp.getPrimaryKey());
		return this._scienceApp.getPrimaryKey();
	}

	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {

	    String summary = this._scienceApp.getName() + " V."+this._scienceApp.getVersion();
	    System.out.println("getSummary(): "+summary);
	    return summary;
	}

	@Override
	public String getTitle(Locale locale) {
		System.out.println("getTitle(): "+this._scienceApp.getName() + " V."+this._scienceApp.getVersion());
		return this._scienceApp.getName() + " V."+this._scienceApp.getVersion();
	}

	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		System.out.println("getJspPath(): "+template);
		if (template.equals(BaseJSPAssetRenderer.TEMPLATE_ABSTRACT) || 
			template.equals(BaseJSPAssetRenderer.TEMPLATE_FULL_CONTENT)) {
			return "/SchedulerManager/" + template + ".jsp";
		}
		else {
			return template;
		}
	}
	
	@Override
	public boolean include(HttpServletRequest request, HttpServletResponse response, String template) throws Exception {
		System.out.println("include(): "+template);
		request.setAttribute("scheduler", this._scienceApp);
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
		        OSPSpyglassWebPortletKeys.ScienceAppManager,
		        PortletRequest.RENDER_PHASE); 

	    portletURL.setParameter("mvcRenderCommandName", "/SchedulerManager/edit_scheduler");
	    portletURL.setParameter("schedulerId", String.valueOf(this._scienceApp.getScienceAppId()));
	    portletURL.setParameter("showback", Boolean.FALSE.toString());

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
			long plid = PortalUtil.getPlidFromPortletId(this._scienceApp.getGroupId(), OSPSpyglassWebPortletKeys.ScienceAppManager);

			PortletURL portletURL;
			if (plid == LayoutConstants.DEFAULT_PLID) {
				portletURL = liferayPortletResponse.createLiferayPortletURL(
						getControlPanelPlid(liferayPortletRequest),
						OSPSpyglassWebPortletKeys.ScienceAppManager, 
						PortletRequest.RENDER_PHASE);
			} else {
				portletURL = PortletURLFactoryUtil.create(
						liferayPortletRequest,
						OSPSpyglassWebPortletKeys.ScienceAppManager, 
						plid, 
						PortletRequest.RENDER_PHASE);
			}

			portletURL.setParameter("mvcRenderCommandName", "/SchedulerManager/view");
			portletURL.setParameter("schedulerId", String.valueOf(this._scienceApp.getScienceAppId()));

			String currentUrl = PortalUtil.getCurrentURL(liferayPortletRequest);

			portletURL.setParameter("redirect", currentUrl);

			return portletURL.toString();

		} catch (PortalException e) {

		} catch (SystemException e) {
		}

		return noSuchEntryRedirect;
	}
	

	public boolean hasDeletePermission(PermissionChecker permissionChecker) throws SystemException, PortalException {
		System.out.println("hasDeletePermission(): ");
		return ScienceAppPermission.contains(permissionChecker, this._scienceApp.getScienceAppId(), 
				ActionKeys.DELETE);
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) throws PortalException {
		System.out.println("hasEditPermission(): ");
		return ScienceAppPermission.contains(permissionChecker, this._scienceApp.getScienceAppId(),  
				ActionKeys.UPDATE);
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) throws PortalException {
		System.out.println("hasViewPermission(): ");
		return ScienceAppPermission.contains(permissionChecker, this._scienceApp.getScienceAppId(),  
				ActionKeys.VIEW);
	}
}
