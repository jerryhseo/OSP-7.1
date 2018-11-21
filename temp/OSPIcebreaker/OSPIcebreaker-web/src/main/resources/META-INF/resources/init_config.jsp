<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="com.kisti.osp.ib.scheduler.manager.configuration.OSPSchedulerManagerPortletConfiguration"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
    OSPSchedulerManagerPortletConfiguration configuration =
        (OSPSchedulerManagerPortletConfiguration)
        renderRequest.getAttribute(OSPSchedulerManagerPortletConfiguration.class.getName());

    String backgroundColor = StringPool.BLANK;

    if (Validator.isNotNull(configuration)) {
    	backgroundColor =
            portletPreferences.getValue(
                "backgroundColor", configuration.backgroundColor());
    }
    System.out.println("Background Color: "+backgroundColor);
	String currentURL = PortalUtil.getCurrentURL(request);

    boolean noConfig = Validator.isNull(backgroundColor);
    System.out.println("Validator: "+noConfig);
%>
