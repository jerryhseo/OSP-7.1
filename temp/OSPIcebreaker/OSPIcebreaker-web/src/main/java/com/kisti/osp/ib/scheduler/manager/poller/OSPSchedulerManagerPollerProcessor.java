package com.kisti.osp.ib.scheduler.manager.poller;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.kisti.osp.icebreaker.listeners.OSPSchedulerEntryModelListener;
import com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalService;
import com.kisti.osp.icebreaker.constants.OSPIcebreakerPortletKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.poller.BasePollerProcessor;
import com.liferay.portal.kernel.poller.PollerHeader;
import com.liferay.portal.kernel.poller.PollerProcessor;
import com.liferay.portal.kernel.poller.PollerRequest;
import com.liferay.portal.kernel.poller.PollerResponse;
import com.liferay.portal.kernel.poller.PollerResponseClosedException;

@Component(
		immediate = true,
		property = {"javax.portlet.name="+OSPIcebreakerPortletKeys.OSPSchedulerManager},
		service = PollerProcessor.class
)
public class OSPSchedulerManagerPollerProcessor extends BasePollerProcessor {

	@Override
	protected PollerResponse doReceive(PollerRequest pollerRequest) throws Exception {
		System.out.println("Polling requested: "+pollerRequest.getUserId());
		PollerResponse pollerResponse = pollerRequest.createPollerResponse();
		
		
		
		String portletId = getString(pollerRequest, "id");
		System.out.println("Polling requested by: "+portletId);
		pollerResponse.setParameter("count", String.valueOf(OSPSchedulerEntryModelListener.updatedQueue.size()) );
		
		return pollerResponse;
	}

	@Override
	protected void doSend(PollerRequest pollerRequest) throws Exception {
		System.out.println("Polling doSend: "+pollerRequest.getPortletId());
	}
	
	@Reference
	private OSPSchedulerEntryLocalService _ospSchedulerEntryLocalService;
}
