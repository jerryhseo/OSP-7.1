package com.kisti.osp.ib.scheduler.manager.adt;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.kisti.osp.icebreaker.model.OSPSchedulerEntry;
import com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalService;
import com.kisti.osp.icebreaker.service.OSPSchedulerEntryService;
import com.kisti.osp.icebreaker.constants.OSPIcebreakerPortletKeys;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.display.template.PortletDisplayTemplateConstants;

@Component(
		immediate = true, 
		property = {"javax.portlet.name=" + OSPIcebreakerPortletKeys.OSPSchedulerManager},
		service = TemplateHandler.class
)
public class OSPSchedulerManagerDisplayTemplateHandler extends BasePortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return OSPSchedulerEntry.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				"content.Language", locale, getClass());

		String portletTitle = _portal.getPortletTitle(
				OSPIcebreakerPortletKeys.OSPSchedulerManager, resourceBundle);
		
		System.out.println("Template Handler: "+ portletTitle);
		
		return portletTitle.concat(StringPool.SPACE).concat(
				LanguageUtil.get(locale, "template"));
	}

	@Override
	public String getResourceName() {
		System.out.println("Template Handler: getResourceName()");
		return OSPIcebreakerPortletKeys.OSPSchedulerManager;
	}
	
	@Override
	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
			long classPK, String language, Locale locale)
		throws Exception {

		Map<String, TemplateVariableGroup> templateVariableGroups =
			super.getTemplateVariableGroups(classPK, language, locale);

		TemplateVariableGroup fieldsTemplateVariableGroup =
			templateVariableGroups.get("fields");

		fieldsTemplateVariableGroup.empty();

		fieldsTemplateVariableGroup.addVariable(
			"asset-entry", AssetEntry.class, "assetEntry");
		fieldsTemplateVariableGroup.addVariable(
			"OSPSchedulerEntry", OSPSchedulerEntry.class, PortletDisplayTemplateConstants.ENTRY);
		fieldsTemplateVariableGroup.addVariable(
			"ospShedulerType", String.class, "formattedContent");

		String[] restrictedVariables = getRestrictedVariables(language);

		TemplateVariableGroup ospSchedulerEntryServicesTemplateVariableGroup =
			new TemplateVariableGroup("ospscheduler-services", restrictedVariables);

		ospSchedulerEntryServicesTemplateVariableGroup.setAutocompleteEnabled(false);

		ospSchedulerEntryServicesTemplateVariableGroup.addServiceLocatorVariables(
			OSPSchedulerEntryLocalService.class, OSPSchedulerEntryService.class);

		templateVariableGroups.put(
				ospSchedulerEntryServicesTemplateVariableGroup.getLabel(),
				ospSchedulerEntryServicesTemplateVariableGroup);

		return templateVariableGroups;
	}

	private Portal _portal;
	
	@Reference(
		target = "(&(release.bundle.symbolic.name=com.kisti.osp.icebreaker.service)(release.schema.version=1.0.0))"
	)
	private Release _release;

}
