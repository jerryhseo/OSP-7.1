package com.osp.analyzers.texteditor.configuration;

import com.liferay.configuration.admin.category.ConfigurationCategory;

import org.osgi.service.component.annotations.Component;

@Component
public class OSPTextEditorConfigurationCategory implements ConfigurationCategory {

	@Override
	public String getCategoryKey() {
		return "osp-text-editor";
	}

	@Override
	public String getCategorySection() {
		return "OSP Analyzers";
	}
}
