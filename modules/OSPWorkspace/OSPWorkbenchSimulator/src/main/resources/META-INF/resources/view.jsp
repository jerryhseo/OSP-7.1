<%@ include file="./init.jsp" %>
<div class="container">
	<fieldset class="row">
		<legend>Fire Common Events</legend>
		<div class="row">
			<div class="col-md-4"><input type="radio" name="events" value="OSP_HANDSHAKE">OSP_HANDSHAKE</div>
			<div class="col-md-4"><input type="radio" name="events" value="OSP_EVENT_REGISTERED">OSP_EVENT_REGISTERED</div>
		</div>
	</fieldset>

	<div class="row">
		<div class="col-md-6" id="<portlet:namespace/>firedEvents">xxxxxx</div>
		<div class="col-md-6" id="<portlet:namespace/>receivedEvents">xxxxxx</div>
	</div>
</div>

<script>
$(document).ready( function(){
	var <portlet:namespace/>path = new OSP.Path(); 
	console.log( <portlet:namespace/>path );
	$("input[name='events']").click(function(){
		alert('<%= portletDisplay.getId()%>');
	});
});
</script>
