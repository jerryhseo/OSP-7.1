package com.osp.workbench.portlet;

import com.osp.workbench.constants.OSPWorkbenchPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Administrator
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.header-portlet-css=/css/workbench.css",
		"com.liferay.portlet.header-portlet-javascript=/js/babylon.custom.js",
		"com.liferay.portlet.header-portlet-javascript=/js/pep/jquery.pep.js",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + OSPWorkbenchPortletKeys.OSPWorkbench,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class OSPWorkbenchPortlet extends MVCPortlet {
}