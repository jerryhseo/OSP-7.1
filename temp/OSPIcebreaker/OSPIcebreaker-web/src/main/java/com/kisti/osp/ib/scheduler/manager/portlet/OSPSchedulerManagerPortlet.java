package com.kisti.osp.ib.scheduler.manager.portlet;

import java.io.IOException;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.kisti.osp.icebreaker.listeners.OSPSchedulerEntryModelListener;
import com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalService;
import com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalServiceUtil;
import com.kisti.osp.icebreaker.constants.OSPIcebreakerPortletKeys;
import com.kisti.osp.ib.scheduler.manager.configuration.OSPSchedulerManagerPortletConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author Jerry H. Seo
configurationPid = "com.kisti.osp.ib.scheduler.manager.configuration.OSPSchedulerManagerPortletConfiguration",
 */
@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.css-class-wrapper=ospscheduler-manager",
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.instanceable=false",
			//"com.liferay.portlet.poller-processor-class=com.kisti.osp.ib.scheduler.manager.poller.OSPSchedulerManagerPollerProcessor",
			"com.liferay.portlet.scopeable=true",
			"com.liferay.portlet.add-default-resource=true",
			"javax.portlet.display-name=OSPScheduler Manager",
			"javax.portlet.expiration-cache=0",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/SchedulerManager/view.jsp",
			//"javax.portlet.init-param.config-template=/SchedulerManager/configuration.jsp",
			"javax.portlet.name=" + OSPIcebreakerPortletKeys.OSPSchedulerManager,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user",
			"javax.portlet.supports.mime-type=text/html"
		},
		service = Portlet.class
)
public class OSPSchedulerManagerPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		System.out.println("OSPSchedulerManagerPortlet: doView()");
		renderRequest.setAttribute(
				OSPSchedulerManagerPortletConfiguration.class.getName(),
                this._configuration);
		renderRequest.setAttribute("schedulerEntryLocalService", _schedulerEntryLocalService );
		
		System.out.println("Scheduler Count: "+OSPSchedulerEntryLocalServiceUtil.countSchedulers());
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		this.modelListener.getUpdatedModel(themeDisplay.getScopeGroupId(), themeDisplay.getUserId());
		super.doView(renderRequest, renderResponse);
	}
	
	public String getBackgroundColor(Map labels){
		System.out.println("OSPSchedulerManagerPortlet: getBackgroundColor()");
		return (String) labels.get(this._configuration.backgroundColor());
	}
	
	@Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
		System.out.println("OSPSchedulerManagerPortlet: activate()");
            this._configuration = ConfigurableUtil.createConfigurable(
            		OSPSchedulerManagerPortletConfiguration.class, properties);
    }

    private volatile OSPSchedulerManagerPortletConfiguration _configuration;
    
    @Reference
    public void setOSPSchedulerEntryModelListener( OSPSchedulerEntryModelListener listener ){
    	System.out.println("setOSPSchedulerEntryModelListener");
    	this.modelListener = listener;
    }
    
    private OSPSchedulerEntryModelListener modelListener;
    
    @Reference
    private volatile OSPSchedulerEntryLocalService _schedulerEntryLocalService;
}