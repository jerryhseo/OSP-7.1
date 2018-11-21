<%@page import="com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.kisti.osp.icebreaker.model.OSPSchedulerEntry"%>
<%@include file="../init.jsp"%>

<%
	OSPSchedulerEntry scheduler = (OSPSchedulerEntry)renderRequest.getAttribute( "scheduler" );
	scheduler = scheduler.toEscapedModel();
	
	String displayStyle = GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK));
	long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), scopeGroupId);
	
%>


<dl>
  <dt> Name</dt>  <dd>  <%=scheduler.getName() %></dd>
  <dt> Version</dt> <dd> <%=scheduler.getVersion() %></dd>
  <dt> Manual</dt> <dd><a target="_blank" href="http://www.adaptivecomputing.com/products/open-source/torque">Torque</a></dd>
</dl>


