/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kisti.osp.ib.scheduler.manager.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
		category = "OSP",
		scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
		factory = true,
		id = "com.kisti.osp.ib.scheduler.manager.configuration.OSPSchedulerManagerPortletConfiguration",
		localization = "content/Language"
)
public interface OSPSchedulerManagerPortletConfiguration {
	@Meta.AD(
			deflt = "blue",
			required = false
	)
    public String backgroundColor();
	
	@Meta.AD(
			deflt = "blue | red | yellow",
			required = false
	)
    public String[] validColors();
}