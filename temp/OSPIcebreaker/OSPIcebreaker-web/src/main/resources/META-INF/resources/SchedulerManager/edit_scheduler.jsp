<%@page import="com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalServiceUtil"%>
<%@page import="com.kisti.osp.icebreaker.model.OSPSchedulerEntry"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@include file = "../init.jsp" %>

<%
	OSPSchedulerEntry scheduler = null;

	long schedulerId = ParamUtil.getLong(request, "schedulerId", 0);
	if (schedulerId > 0) {
		scheduler = OSPSchedulerEntryLocalServiceUtil.getOSPSchedulerEntry(schedulerId);
	}
	
	String backURL = ParamUtil.getString(request, "backURL", "");
	String command = ParamUtil.getString(request, "command", "UPDATE");
	System.out.println("backURL: "+backURL);
%>

<portlet:renderURL var="viewURL">
    <portlet:param name="mvcPath" value="/SchedulerManager/view.jsp"></portlet:param>
</portlet:renderURL>

<a href="<%=backURL%>">&lt;&lt; back</a>

<portlet:actionURL name="addOSPScheduler" var="addSchedulerURL">
</portlet:actionURL>

<aui:form action="<%= addSchedulerURL.toString() %>" name="<portlet:namespace />fm">
	<aui:fieldset>
		<aui:input name="name" value='<%= scheduler == null ? "" : scheduler.getName() %>' />
		<aui:input name="version" value='<%= scheduler == null ? "" : scheduler.getVersion() %>' />
		<aui:input name="serverIp" value='<%= scheduler == null ? "" : scheduler.getServerIp() %>' />
		<aui:input name="sshPort" value='<%= scheduler == null ? "" : scheduler.getSshPort() %>' />
		<aui:input name="className" value='<%= scheduler == null ? "" : scheduler.getSchedulerClass() %>' />
		<aui:input name="authorizedUser" value='<%= scheduler == null ? "" : scheduler.getAuthorizedUser() %>' />
		<aui:input name="authorizedPassword" value='<%= scheduler == null ? "" : scheduler.getAuthorizedPassword() %>' />
		<aui:input name="status" value='<%= scheduler == null ? "" : scheduler.getStatus() %>' />
		<aui:input name="desctiption" value='<%= scheduler == null ? "" : scheduler.getDescription() %>' />
		
		<aui:input name="schedulerId" type="hidden"
				 value='<%= scheduler == null ? "" : scheduler.getSchedulerEntryId() %>' />
		<aui:input name="command" type="hidden" value='<%= command %>' />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit"></aui:button>
		<aui:button type="cancel" onClick="<%= backURL %>"></aui:button>
	</aui:button-row>
</aui:form>
