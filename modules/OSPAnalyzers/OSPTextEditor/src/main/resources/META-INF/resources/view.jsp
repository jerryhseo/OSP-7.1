<%@page import="java.util.Enumeration"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ include file="./init.jsp" %>
<%
PortletPreferences preferences = portletDisplay.getPortletSetup();

// You can use one of barebone, borderless, decorate.
// These are default decorators of liferay themes
preferences.setValue("portletSetupPortletDecoratorId", "borderless");
preferences.store();

String inputData = GetterUtil.getString(renderRequest.getAttribute("inputData"), "{}");
String connector = GetterUtil.getString(renderRequest.getAttribute("connector"), "");
boolean eventEnable = GetterUtil.getBoolean(renderRequest.getAttribute("eventEnable"), true);
String mode = (String)renderRequest.getAttribute("mode");
%>

<div class="container">
	<div class="row">
		<div id="<portlet:namespace/>Canvas" contenteditable="true">xxxxxxx</div>
	</div>
</div>

<script>
$(document).ready(function(){
	$('#portlet_<%=portletDisplay.getId()%>').find('.portlet-title-text').text('No File Loaded');
});
</script>