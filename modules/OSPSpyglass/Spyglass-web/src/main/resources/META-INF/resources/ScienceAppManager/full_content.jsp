<%@page import="com.kisti.osp.spyglass.service.ScienceAppLocalServiceUtil"%>
<%@page import="com.kisti.osp.spyglass.model.ScienceApp"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@include file="../init.jsp"%>

<%
System.out.println("In full_content.jsp");
	long scienceAppId = ParamUtil.getLong(request, "scienceAppId");
	ScienceApp scienceApp = null;
	if( scienceAppId > 0 )
		scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
	
	if( scienceApp == null ){
		System.out.println("No Scheduler!!!!!!!!!!!!!!!!!!");
	}
	else{
		scienceApp = scienceApp.toEscapedModel();
	}
	String displayStyle = GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK));
	long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), scopeGroupId);
	
%>


<dl>
  <dt> Name</dt>  <dd>  <%=(scienceApp == null)? "":scienceApp.getName() %></dd>
  <dt> Version</dt> <dd> <%=(scienceApp == null)? "":scienceApp.getVersion() %></dd>
  <dt> Manual</dt> <dd><a target="_blank" href="http://www.edison.re.kr">View</a></dd>
</dl>


