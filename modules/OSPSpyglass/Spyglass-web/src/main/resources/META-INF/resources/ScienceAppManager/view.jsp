<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.kisti.osp.spyglass.web.constants.OSPSpyglassWebPortletKeys"%>
<%@page import="com.kisti.osp.spyglass.service.ScienceAppLocalServiceUtil"%>
<%@ include file="../init.jsp" %>

<h4>ScienceApps</h4>

<portlet:renderURL var="addScienceAppURL">
	<portlet:param name="mvcRenderCommandName" value="/spyglass/scienceapp/edit"></portlet:param>
	<portlet:param name="backURL" value="<%=currentURL%>"></portlet:param>
</portlet:renderURL>

<a href="<%=addScienceAppURL%>" class="btn btn-default btn-sm" role="button">Add...</a>

<%
	PortletURL iteratorURL = renderResponse.createRenderURL();
%>

<liferay-ui:search-container
	emptyResultsMessage="Sorry. There are no science apps to display."
	iteratorURL="<%=iteratorURL %>" >
    <liferay-ui:search-container-results>
    	<%
			results = ScienceAppLocalServiceUtil.getScienceApps(searchContainer.getStart(), searchContainer.getEnd());
			total = ScienceAppLocalServiceUtil.countAllScienceApps();
			pageContext.setAttribute("results", results);
			pageContext.setAttribute("total", total);
		%>
	</liferay-ui:search-container-results>

    <liferay-ui:search-container-row
        className="com.kisti.osp.spyglass.model.ScienceApp" modelVar="scienceApp">

        <liferay-ui:search-container-column-text property="name" />
        <liferay-ui:search-container-column-text property="version" />
        <liferay-ui:search-container-column-text property="title" />
        <liferay-ui:search-container-column-text property="authorId" />

        <liferay-ui:search-container-column-jsp
            align="right" 
            path="/ScienceAppManager/scienceapp_actions.jsp" />

    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>

<script>
</script>
