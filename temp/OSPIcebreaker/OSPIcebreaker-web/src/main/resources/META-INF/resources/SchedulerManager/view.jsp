<%@page import="com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalService"%>
<%@page import="com.liferay.portal.kernel.service.PortletLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Portlet"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.kisti.osp.ib.scheduler.manager.configuration.OSPSchedulerManagerPortletConfiguration"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="javax.portlet.RenderResponse"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.kisti.osp.icebreaker.permissions.SchedulerPermission"%>
<%@page import="com.liferay.portal.kernel.security.permission.ActionKeys"%>
<%@page import="com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@ include file="../init_config.jsp" %>

<%
	Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletDisplay.getId());
	OSPSchedulerEntryLocalService schedulerEntryLocalService = (OSPSchedulerEntryLocalService)request.getAttribute("schedulerEntryLocalService");
%>

<%--
<liferay-util:html-bottom>
		<script defer="defer" src="<%= PortalUtil.getStaticResourceURL(request, PortalUtil.getPathContext(request) + "/SchedulerManager/js/scheduler.js", portlet.getTimestamp()) %>" type="text/javascript"></script>
</liferay-util:html-bottom>
<script type="text/javascript" src="<%=request.getContextPath()%>/SchedulerManager/js/scheduler.js"></script>
 --%>

<style>
.ospscheduler-manager .portlet-content{
	background-color:#dddddd;
}
</style>
<c:choose>
    <c:when test="<%= noConfig %>">
        <p>
            Please select use the portlet configuration to select a favorite color.
        </p>
    </c:when>
    <c:otherwise>
        <p style="color: <%= backgroundColor %>">
            Background Color: <%= backgroundColor %>!
        </p>
    </c:otherwise>
</c:choose>

<portlet:renderURL var="addURL">
	<portlet:param name="mvcRenderCommandName" value="/SchedulerManager/edit_scheduler"></portlet:param>
	<portlet:param name="command" value="ADD"></portlet:param>
	<portlet:param name="backURL" value="<%=currentURL%>"></portlet:param>
</portlet:renderURL>

<a href="<%=addURL%>" class="btn btn-default btn-sm" role="button">Add...</a>

<%
	PortletURL iteratorURL = renderResponse.createRenderURL();
%>

<liferay-ui:search-container 
			delta="10" 
			emptyResultsMessage="Sorry. There are no records to display." 
			iteratorURL="<%=iteratorURL %>">
	<liferay-ui:search-container-results>
		<%
			results = schedulerEntryLocalService.getSchedulers(searchContainer.getStart(), searchContainer.getEnd());
			total = schedulerEntryLocalService.countSchedulers();
			pageContext.setAttribute("results", results);
			pageContext.setAttribute("total", total);
		%>
	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row className="com.kisti.osp.icebreaker.model.OSPSchedulerEntry" modelVar="scheduler" keyProperty="schedulerEntryId" >
	
		<portlet:renderURL var="rowURL">
			<portlet:param name="backURL" value="<%=currentURL %>" />
			<portlet:param name="schedulerId" value="<%=String.valueOf(scheduler.getSchedulerEntryId()) %>" />
			<portlet:param name="mvcRenderCommandName" value="/SchedulerManager/full_content"/>
		</portlet:renderURL>
		
		<liferay-ui:search-container-column-text property="name" name="Scheduler Name" href="<%=rowURL%>"/>
		<liferay-ui:search-container-column-text property="version" name="Version" />
		<liferay-ui:search-container-column-text name="Actions" >
		<liferay-ui:icon-menu>
			<c:if test="<%= SchedulerPermission.contains(permissionChecker, scheduler.getSchedulerEntryId(), ActionKeys.UPDATE) %>">
				<portlet:renderURL var="editURL">
					<portlet:param name="schedulerId"
											value="<%= String.valueOf(scheduler.getSchedulerEntryId()) %>" ></portlet:param>
					<portlet:param name="mvcRenderCommandName" value="/SchedulerManager/edit_scheduler"></portlet:param>
					<portlet:param name="backURL" value="<%=currentURL %>"></portlet:param>
					<portlet:param name="command" value="UPDATE"></portlet:param>
				</portlet:renderURL>

				<liferay-ui:icon image="edit" message="Edit"  url="<%= editURL.toString() %>" />
			</c:if>
			<c:if test="<%=SchedulerPermission.contains(permissionChecker, scheduler.getSchedulerEntryId(), ActionKeys.DELETE) %>">
				<portlet:actionURL name="deleteOSPScheduler" var="deleteURL">
					<portlet:param name="schedulerId"
												value="<%= String.valueOf(scheduler.getSchedulerEntryId()) %>" />
					</portlet:actionURL>
				<liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />
			</c:if>
		</liferay-ui:icon-menu>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<aui:input name="currentServerTime" type="hidden" useNamespace="<%= false %>" value="<%= System.currentTimeMillis() %>" />

<script type="text/javascript">
AUI().use(
		'aui-base',
		'liferay-poller',
		function(A) {
			
			console.log('Liferay', Liferay);
			
			Liferay.namespace('OSPSchedulerPoller');
			
			Liferay.OSPSchedulerPoller.Manager = {
					init: function() {
						console.log('Poller init called.'); 
						var instance = this;
						instance.id = '<%= portletDisplay.getId() %>';
						instance.timeStamp = Date.now();
						// instance.sendTask = A.debounce(instance.send, 1000000, instance);
						console.log('init function instance: ', instance);
						Liferay.Poller.addListener(instance.id, instance.onUpdate, instance);
						Liferay.on(
								'sessionExpired',
								function(event) {
									Liferay.Poller.removeListener(instance.id);
								}
						);
						window.onpagehide = function(){
							console.log('JSP Page unloading......');
							Liferay.Poller.removeListener(instance.id);
						};
					},
					onUpdate: function(response, chunkId){
						console.log('Polling onUpdate()', response, chunkId);
					},
					send: function( options, id){
						var instance = this;
						console.log('Poller send function called...');
						Liferay.Poller.submitRequest(instance.id, options, id);
					}
			};
			
			// A.augment(Liferay.OSPSchedulerPoller.Manager, A.Attribute, true);
			
			Liferay.publish(
					'domready',
					{
						defaultFn: A.bind(Liferay.OSPSchedulerPoller.Manager.init, Liferay.OSPSchedulerPoller.Manager),
						fireOnce: true
					}
			);
		}
 );
 

</script>