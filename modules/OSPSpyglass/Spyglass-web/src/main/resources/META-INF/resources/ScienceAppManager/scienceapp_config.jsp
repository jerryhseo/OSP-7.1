<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.kisti.osp.icebreaker.model.OSPSchedulerEntry"%>
<%@page import="com.liferay.portal.kernel.template.TemplateHandlerRegistryUtil"%>
<%@page import="com.liferay.portal.kernel.template.TemplateHandler"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@include file="../init.jsp"%>

<%
String displayStyle = GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK));
long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), scopeGroupId);


System.out.println("displayStyle: "+displayStyle);
System.out.println("displayStyleGroupId: "+displayStyleGroupId);
%>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
	<input name="<portlet:namespace /><%=Constants.CMD%>" type="hidden" value="<%=Constants.UPDATE%>" /> 

	<aui:fieldset>
    <div class="display-template">

        <%
        TemplateHandler templateHandler = TemplateHandlerRegistryUtil.getTemplateHandler(OSPSchedulerEntry.class.getName());
        %>

        <liferay-ui:ddm-template-selector
            classNameId="<%= PortalUtil.getClassNameId(templateHandler.getClassName()) %>"
            displayStyle="<%= displayStyle %>"
            displayStyleGroupId="<%= displayStyleGroupId %>"
            refreshURL="<%= PortalUtil.getCurrentURL(request) %>"
                    showEmptyOption="<%= true %>"
        />
    </div>
</aui:fieldset>

	<input type="button" value="Save" onClick="submitForm(document.<portlet:namespace />fm);" /> 
</form>

