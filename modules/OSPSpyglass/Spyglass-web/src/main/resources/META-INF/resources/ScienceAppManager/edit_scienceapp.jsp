<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.kisti.osp.spyglass.model.ScienceApp"%>
<%@page import="com.kisti.osp.spyglass.service.ScienceAppLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@include file = "../init.jsp" %>

<%
	ScienceApp scienceApp = null;

	long scienceAppId = GetterUtil.getLong( renderRequest.getAttribute("scienceAppId"), 0);
	boolean isEdit;
	if (scienceAppId > 0) {
		scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
		isEdit = true;
	}
	else{
		isEdit = false;
	}
	
	String backURL = ParamUtil.getString(request, "backURL", "");
	System.out.println("backURL: "+backURL);
%>

<a href="<%=backURL%>">&lt;&lt; back</a>

<div aria-multiselectable="true" class="panel-group" id="accordion" role="tablist">
	<div class="panel panel-default">
		<div class="panel-heading" id="headingOne" role="tab">
			<div class="panel-title">
				<a aria-controls="basicInformation" aria-expanded="true" data-toggle="collapse" data-parent="#accordion" href="#basicInformation" role="button">
					Basic Information
				</a>
			</div>
		</div>
		<div aria-labelledby="headingOne" class="panel-collapse collapse in" id="basicInformation" role="tabpanel">
			<div class="panel-body">
				<%@ include file="jspf/basic.jspf" %>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading" id="headingTwo" role="tab">
			<div class="panel-title">
				<a aria-controls="executtionInformation" aria-expanded="false" class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#executtionInformation" role="button">
					Execution Information
				</a>
			</div>
		</div>
		<div aria-labelledby="headingTwo" class="panel-collapse collapse" id="executtionInformation" role="tabpanel">
			<div class="panel-body">
				<%@ include file="jspf/execute.jspf" %>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading" id="headingThree" role="tab">
			<div class="panel-title">
				<a aria-controls="definePorts" aria-expanded="false" class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#definePorts" role="button">
					Define In-Out Ports
				</a>
			</div>
		</div>
		<div aria-labelledby="headingThree" class="panel-collapse collapse" id="definePorts" role="tabpanel">
			<div class="panel-body">
				<%@ include file="jspf/ports.jspf" %>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading" id="headingFour" role="tab">
			<div class="panel-title">
				<a aria-controls="defineSimulationEvironment" aria-expanded="false" class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#defineSimulationEvironment" role="button">
					Define Simulation Environment
				</a>
			</div>
		</div>
		<div aria-labelledby="headingFour" class="panel-collapse collapse" id="defineSimulationEvironment" role="tabpanel">
			<div class="panel-body">
				<%@ include file="jspf/simulation_env.jspf" %>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading" id="headingFive" role="tab">
			<div class="panel-title">
				<a aria-controls="requestForService" aria-expanded="false" class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#requestForService" role="button">
					Request For Service
				</a>
			</div>
		</div>
		<div aria-labelledby="headingFive" class="panel-collapse collapse" id="requestForService" role="tabpanel">
			<div class="panel-body">
				<%@ include file="jspf/request_service.jspf" %>
			</div>
		</div>
	</div>
	
</div>

<script>
(function($) {
	
})(jQuery);
</script>