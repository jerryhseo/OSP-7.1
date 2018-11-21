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
package com.osp.icebreaker.scheduler.manager.commands.actions;

import com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalServiceUtil;
import com.kisti.osp.icebreaker.constants.OSPIcebreakerPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
        "javax.portlet.name="+OSPIcebreakerPortletKeys.OSPSchedulerManager,
        "mvc.command.name=deleteOSPScheduler"
    },
    service = MVCActionCommand.class
)
public class DeleteOSPSchedulerActionCommand extends BaseMVCActionCommand {
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		long schedulerId = ParamUtil.getLong(actionRequest, "schedulerId");

		System.out.println("Scheduler ID to be deleted: "+schedulerId);
		OSPSchedulerEntryLocalServiceUtil.removeScheduler(schedulerId);

		SessionMessages.add(actionRequest, "scheduler Deleted");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DeleteOSPSchedulerActionCommand.class);
}