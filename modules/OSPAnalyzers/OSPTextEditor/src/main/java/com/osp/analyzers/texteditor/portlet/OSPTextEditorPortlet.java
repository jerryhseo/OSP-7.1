package com.osp.analyzers.texteditor.portlet;

import com.osp.analyzers.texteditor.constants.OSPTextEditorPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Administrator
 */
@Component(
	immediate = true,
	configurationPid = "com.osp.analyzers.texteditor.OSPTextEditorConfiguration",
	property = {
		"com.liferay.portlet.display-category=OSP Analyzers",
		"com.liferay.portlet.display-name=OSPTextEditor",
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.header-portlet-css=/css/osp-analyzer.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + OSPTextEditorPortletKeys.OSPTextEditor,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class OSPTextEditorPortlet extends MVCPortlet {
}