package com.kisti.osp.spyglass.scienceapp.manager.commands.render;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.kisti.osp.spyglass.web.constants.OSPSpyglassWebPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderConstants;
import com.liferay.portal.kernel.util.PortalUtil;

@Component(
		immediate = true,
		property = {
	        "javax.portlet.name="+OSPSpyglassWebPortletKeys.ScienceAppManager,
	        "mvc.command.name=/spyglass/scienceapp/edit"
	    },
	    service = MVCRenderCommand.class
)
public class EditScienceAppRender implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		System.out.println("EditScienceAppRender");

		renderRequest.setAttribute("scienceAppId", 0);
		
		RequestDispatcher requestDispatcher =
	            this.servletContext.getRequestDispatcher("/ScienceAppManager/edit_scienceapp.jsp");

	        try {
	            HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);
	            HttpServletResponse httpServletResponse = PortalUtil.getHttpServletResponse(renderResponse);

	            requestDispatcher.include
	                (httpServletRequest, httpServletResponse);
	        } catch (Exception e) {
	            throw new PortletException
	                ("Unable to include edit_scienceapp.jsp", e);
	        }

		
		return MVCRenderConstants.MVC_PATH_VALUE_SKIP_DISPATCH;
	}

	@Reference(target = "(osgi.web.symbolicname=com.kisti.osp.spyglass.web)")
    protected ServletContext servletContext;

	private static final Log _log = LogFactoryUtil.getLog(EditScienceAppRender.class);
}
