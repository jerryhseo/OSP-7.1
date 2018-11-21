/**
 * Copyright 2018-present Jerry H. Seo.
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
package com.kisti.osp.spyglass.scienceapp.manager.commands.action;

import com.kisti.osp.spyglass.model.ScienceApp;
import com.kisti.osp.spyglass.service.ScienceAppLocalServiceUtil;
import com.kisti.osp.spyglass.web.constants.OSPSpyglassWebPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
        "javax.portlet.name="+OSPSpyglassWebPortletKeys.ScienceAppManager,
        "mvc.command.name=/scienceapp/save/basic-info"
    },
    service = MVCActionCommand.class
)
public class SaveScienceAppBasicInformationActionCommand extends BaseMVCActionCommand {
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		String scienceAppName = ParamUtil.getString(actionRequest, "scienceAppName");
		String scienceAppVersion = ParamUtil.getString(actionRequest, "scienceAppVersion");
		String scienceAppType = ParamUtil.getString(actionRequest, "scienceAppType");
		String scienceAppOpenLevel = ParamUtil.getString(actionRequest, "scienceAppOpenLevel");
		
		Map<Locale, String> scienceAppTitleMap = LocalizationUtil.getLocalizationMap(actionRequest, "scienceAppTitle");
		
		ServiceContext sc = ServiceContextFactory.getInstance(ScienceApp.class.getName(), actionRequest);
		ScienceApp scienceApp = ScienceAppLocalServiceUtil.createScienceApp(scienceAppName, scienceAppVersion, sc);
		
		scienceApp.setAppType(scienceAppType);
		scienceApp.setOpenLevel(scienceAppOpenLevel);
		scienceApp.setTitleMap(scienceAppTitleMap);
		
		ScienceAppLocalServiceUtil.addScienceApp(scienceApp);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SaveScienceAppBasicInformationActionCommand.class);
}