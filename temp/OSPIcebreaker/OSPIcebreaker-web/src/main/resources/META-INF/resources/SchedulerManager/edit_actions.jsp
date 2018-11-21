<%@page import="com.liferay.portal.kernel.security.permission.ActionKeys"%>
<%@page import="com.kisti.osp.icebreaker.model.OSPSchedulerEntry"%>
<%@page import="com.kisti.osp.icebreaker.permissions.SchedulerPermission"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@include file="../init.jsp"%>

<%
String mvcPath = ParamUtil.getString(request, "mvcPath");
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
OSPSchedulerEntry scheduler = (OSPSchedulerEntry)row.getObject(); 
%>

<liferay-ui:icon-menu>
    <c:if
        test="<%= SchedulerPermission.contains(permissionChecker, scheduler.getSchedulerEntryId(), ActionKeys.UPDATE) %>">
        <portlet:renderURL var="editURL">
            <portlet:param name="schedulerId"
                value="<%= String.valueOf(scheduler.getSchedulerEntryId()) %>" />
            <portlet:param name="mvcPath" value="/html/edit_scheduler.jsp" />
        </portlet:renderURL>

        <liferay-ui:icon image="edit" message="Edit"  url="<%= editURL.toString() %>" />
    </c:if>
    
	<c:if
        test="<%=SchedulerPermission.contains(permissionChecker, scheduler.getSchedulerEntryId(), ActionKeys.DELETE) %>">
        <portlet:actionURL name="deleteOSPScheduler" var="deleteURL">
            <portlet:param name="schedulerId"
                value="<%= String.valueOf(scheduler.getSchedulerEntryId()) %>" />
        </portlet:actionURL>

        <liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />
    </c:if>
</liferay-ui:icon-menu>