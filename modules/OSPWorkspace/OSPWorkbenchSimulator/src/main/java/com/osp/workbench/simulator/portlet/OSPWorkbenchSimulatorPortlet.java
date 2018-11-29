package com.osp.workbench.simulator.portlet;

import com.osp.workbench.simulator.constants.OSPWorkbenchSimulatorPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author DELL
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=OSP Workbench",
		"com.liferay.portlet.display-name=OSPWorkbench Simulator",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.header-portlet-javascript=/js/osp/osp_basic_object.js",
		"com.liferay.portlet.header-portlet-javascript=/js/osp/osp_datatype.js",
		"com.liferay.portlet.header-portlet-javascript=/js/osp/osp_further_impl.js",
		"com.liferay.portlet.header-portlet-javascript=/js/osp/osp_scienceapp.js",
		"com.liferay.portlet.header-portlet-javascript=/js/osp/osp_simulation.js",
		"com.liferay.portlet.header-portlet-javascript=/js/osp/osp_super_class.js",
		"com.liferay.portlet.header-portlet-javascript=/js/osp/osp_workbench_layout.js",
		"com.liferay.portlet.add-default-resource=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + OSPWorkbenchSimulatorPortletKeys.OSPWorkbenchSimulator,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
		
	},
	service = Portlet.class
)
public class OSPWorkbenchSimulatorPortlet extends MVCPortlet {
}