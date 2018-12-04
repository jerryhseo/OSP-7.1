package com.osp.analyzers.texteditor.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.osp.analyzers.texteditor.constants.OSPTextEditorProperties;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	    category = "osp-text-editor",
	    scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE 
	)
@Meta.OCD(
	    id = "com.osp.analyzers.texteditor.configuration.OSPTextEditorConfiguration",
	    localization = "content/Language", 
	    name = "javax.portlet.display-name.osptexteditor"
	)
public interface OSPTextEditorConfiguration {
	@Meta.AD(
	        deflt = "1", 
	        description = "autosave-interval-description",
	        name = "autosave-interval-name", 
	        required = false
	)
	public int autosaveInterval();

	@Meta.AD(
			deflt = "descriptive", 
			name = "default-display-view",
			optionLabels = {"Descriptive", "List"},
			optionValues = {"descriptive", "list"}, 
			required = false
	)
	public String defaultDisplayView();
}
