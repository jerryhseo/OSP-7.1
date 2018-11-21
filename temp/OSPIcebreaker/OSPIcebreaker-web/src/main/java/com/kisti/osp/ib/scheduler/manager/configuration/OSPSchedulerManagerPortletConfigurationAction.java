package com.kisti.osp.ib.scheduler.manager.configuration;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import com.kisti.osp.icebreaker.constants.OSPIcebreakerPortletKeys;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

@Component(
	    configurationPid = "com.kisti.osp.ib.scheduler.manager.configuration.OSPSchedulerManagerPortletConfiguration",
	    configurationPolicy = ConfigurationPolicy.OPTIONAL,
	    immediate = true,
	    property = {
	        "javax.portlet.name="+OSPIcebreakerPortletKeys.OSPSchedulerManager
	    },
	    service = ConfigurationAction.class
)
public class OSPSchedulerManagerPortletConfigurationAction extends DefaultConfigurationAction {
	@Override
    public void processAction(
            PortletConfig portletConfig, ActionRequest actionRequest,
            ActionResponse actionResponse)
        throws Exception {

		System.out.println("OSPSchedulerManagerPortletConfigurationAction: processAction()");
        String backgroundColor = ParamUtil.getString(actionRequest, "backgroundColor");
        super.setPreference(actionRequest, "backgroundColor", backgroundColor);

        super.processAction(portletConfig, actionRequest, actionResponse);
    }

    @Override
    public void include(
        PortletConfig portletConfig, HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse) throws Exception {
    	System.out.println("OSPSchedulerManagerPortletConfigurationAction: include()");

        httpServletRequest.setAttribute(
        		OSPSchedulerManagerPortletConfiguration.class.getName(),
            this._configuration);

        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }

    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
    	System.out.println("OSPSchedulerManagerPortletConfigurationAction: activate()");
        this._configuration = ConfigurableUtil.createConfigurable(
        		OSPSchedulerManagerPortletConfiguration.class, properties);
        System.out.println("OSPSchedulerManagerPortletConfigurationAction");
        System.out.println("BackgroundColor: "+ this._configuration.backgroundColor());
    }

    private volatile OSPSchedulerManagerPortletConfiguration _configuration;

}
