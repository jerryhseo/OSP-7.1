package com.kisti.osp.spyglass.scienceapp.manager.portlet;

import com.kisti.osp.spyglass.web.constants.OSPSpyglassWebPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Administrator
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=hidden",
		"com.liferay.portlet.scopeable=true",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=ScienceApp Manager",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.portlet-title-based-navigation=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/ScienceAppManager/view.jsp",
		"javax.portlet.name=" + OSPSpyglassWebPortletKeys.ScienceAppManager,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator",
		"javax.portlet.supports.mime-type=text/html",
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=scienceapp-manager",
	},
	service = Portlet.class
)
public class ScienceAppManagerPortlet extends MVCPortlet {
}