package com.kisti.osp.spyglass.asset;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.kisti.osp.spyglass.model.ScienceApp;
import com.kisti.osp.spyglass.permissions.ScienceAppPermission;
import com.kisti.osp.spyglass.service.ScienceAppLocalService;
import com.kisti.osp.spyglass.web.constants.OSPSpyglassWebPortletKeys;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + OSPSpyglassWebPortletKeys.ScienceAppManager},
	service = AssetRendererFactory.class
)
public class ScienceAppAssetRendererFactory extends BaseAssetRendererFactory<ScienceApp> {
	public static final String TYPE = "ScienceApp";
	
	private ScienceAppLocalService _ScienceAppLocalService;
	private ServletContext _servletContext;
	private static final boolean _LINKABLE = true;
	public static final String CLASS_NAME = ScienceApp.class.getName();

	public ScienceAppAssetRendererFactory() {
		System.out.println("ScienceAppAssetRendererFactory ---------------------");
		super.setClassName(CLASS_NAME);
		super.setLinkable(true);
		super.setCategorizable(true);
		super.setPortletId(OSPSpyglassWebPortletKeys.ScienceAppManager);
		super.setSearchable(true);
		super.setSelectable(true);
	}
	
	@Reference(
			unbind = "-"
	)
	protected void setScienceAppLocalService(
	    ScienceAppLocalService scienceAppLocalService) {
		System.out.println("setScienceAppLocalService()");
		this._ScienceAppLocalService = scienceAppLocalService;
	}

	@Reference(
			target = "(osgi.web.symbolicname=com.kisti.osp.spyglass.web)",
			unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		System.out.println("setServletContext()");
		this._servletContext = servletContext;
	}

	@Override
	public AssetRenderer<ScienceApp> getAssetRenderer(long classPK, int type) throws PortalException {
		System.out.println("getAssetRenderer(): "+classPK + ", "+type);
		ScienceApp entry = this._ScienceAppLocalService.getScienceApp(classPK);

		ScienceAppAssetRenderer ospSchedulerAssetRenderer =
				new ScienceAppAssetRenderer(entry);

		ospSchedulerAssetRenderer.setAssetRendererType(type);
		ospSchedulerAssetRenderer.setServletContext(this._servletContext);

		return ospSchedulerAssetRenderer;
	}

	@Override
	public String getType() {
		System.out.println("getType(): "+TYPE);
	    return TYPE;
	}

	@Override
	public String getClassName() {
		System.out.println("getClassName(): "+ScienceApp.class.getName());
	    return ScienceApp.class.getName();
	}

	@Override
	public String getIconCssClass() {
		System.out.println("getIconCssClass(): add-cell");
	    return "add-cell";
	}
	
	
	@Override
	public PortletURL getURLAdd(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			long classTypeId) {

		System.out.println("getURLAdd(): "+classTypeId);
		PortletURL portletURL;

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);

			portletURL = liferayPortletResponse.createLiferayPortletURL(
					getControlPanelPlid(themeDisplay),
					OSPSpyglassWebPortletKeys.ScienceAppManager, PortletRequest.RENDER_PHASE);

			portletURL.setParameter("mvcRenderCommandName", "/SchedulerManager/edit_scheduler");
			portletURL.setParameter("showback", Boolean.FALSE.toString());
		} catch (PortalException e) {
			System.out.println("getURLAdd error: "+e.getMessage());
			return null;
		}

		return portletURL;
	}
	
	@Override
	public boolean isLinkable() {
		return _LINKABLE;
	}

	@Override
	public PortletURL getURLView(
	    LiferayPortletResponse liferayPortletResponse,
	    WindowState windowState) {
		System.out.println("getURLView(): ");

	    LiferayPortletURL liferayPortletURL =
	        liferayPortletResponse.createLiferayPortletURL(
	        		OSPSpyglassWebPortletKeys.ScienceAppManager, PortletRequest.RENDER_PHASE);

	    try {
	        liferayPortletURL.setWindowState(windowState);
	    }
	    catch (WindowStateException wse) {
	    }

	    return liferayPortletURL;
	}
	
	@Override
	public boolean hasAddPermission(
	        PermissionChecker permissionChecker, long groupId, long classTypeId)
	    throws Exception {

	    return super.hasAddPermission(permissionChecker, groupId, classTypeId);
	}
	
	
	
	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
					throws Exception {
		
		ScienceApp scheduler = this._ScienceAppLocalService.getScienceApp(classPK);
		return ScienceAppPermission.contains(permissionChecker, scheduler, actionId);
	}
}
