<%@page import="com.kisti.osp.spyglass.permissions.ScienceAppPermission"%>
<%@page import="com.kisti.osp.spyglass.model.ScienceApp"%>
<%@page import="com.liferay.portal.kernel.security.permission.ActionKeys"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@include file="../init.jsp"%>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
ScienceApp scienceApp = (ScienceApp)row.getObject();

System.out.println("Name: "+scienceApp.getName());

boolean updatePermisstion = ScienceAppPermission.contains(permissionChecker, scienceApp, "ADD_SCIENCEAPP");
//System.out.println("Update Permission: "+updatePermisstion);
%>

<%--
<liferay-ui:icon-menu>
    <c:if
        test="<%= ScienceAppPermission.contains(permissionChecker, scienceApp.getScienceAppId(), "UPDATE_SCIENCEAPP") %>">
        <portlet:renderURL var="editURL">
            <portlet:param name="scienceAppId"
                value="<%= String.valueOf(scienceApp.getScienceAppId()) %>" />
            <portlet:param name="mvcCommandName" value="/spyglass/scienceapp/edit" />
        </portlet:renderURL>

        <liferay-ui:icon image="edit" message="Edit"  url="<%= editURL.toString() %>" />
    </c:if>
    
	<c:if
        test="<%=ScienceAppPermission.contains(permissionChecker, scienceApp.getScienceAppId(), "DELETE_SCIENCEAPP") %>">
        <portlet:actionURL name="deleteScienceApp" var="deleteURL">
            <portlet:param name="scienceAppId"
                value="<%= String.valueOf(scienceApp.getScienceAppId()) %>" />
        </portlet:actionURL>

        <liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />
    </c:if>
</liferay-ui:icon-menu>
 --%>