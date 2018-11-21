<%@page import="com.kisti.osp.spyglass.model.ScienceApp"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@include file="../init.jsp"%>

<%
	ScienceApp scienceApp = (ScienceApp)renderRequest.getAttribute( "scienceApp" );
	scienceApp = scienceApp.toEscapedModel();
	
	String displayStyle = GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK));
	long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), scopeGroupId);
	
%>


<dl>
  <dt> Name</dt>  <dd>  <%=scienceApp.getName() %></dd>
  <dt> Version</dt> <dd> <%=scienceApp.getVersion() %></dd>
  <dt> Manual</dt> <dd><a target="_blank" href="http://www.edison.re.kr">Torque</a></dd>
</dl>


