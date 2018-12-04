<%@page import="com.kisti.osp.util.constants.OSPRepositoryTypes"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="java.util.Enumeration"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ include file="./init.jsp" %>
<%
PortletPreferences preferences = portletDisplay.getPortletSetup();

// You can use one of barebone, borderless, decorate.
// These are default decorators of liferay themes
preferences.setValue("portletSetupPortletDecoratorId", "barebone");
preferences.store();

String inputData = GetterUtil.getString(renderRequest.getAttribute("inputData"), "{}");
String connector = GetterUtil.getString(renderRequest.getAttribute("connector"), "");
boolean eventEnable = GetterUtil.getBoolean(renderRequest.getAttribute("eventEnable"), true);
String displayOptions = GetterUtil.getString(renderRequest.getAttribute("displayOptions"), "{}");
%>

<div class="container osp-editor">
	<div class="row osp-header">
		<div class="col-sm-10 osp-title"></div>
		<div class="col-sm-2 osp-menu" >
			<div class="dropdown text-right">
				<button class="btn btn-sm btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Menu<span class="caret"></span></button>
				<ul class="dropdown-menu dropdown-menu-right">
					<li id="<portlet:namespace/>sample"><a href="#"><i class="icon-file"> Sample</i></a></li>
					<li id="<portlet:namespace/>openLocal"><a href="#"><i class="icon-folder-open"> Open local...</i></a></li>
					<li><a href="javascript:<portlet:namespace/>openServerFile()"><i class="icon-folder-open"> Open server...</i></a></li>
					<li><a href="javascript:<portlet:namespace/>saveAs()"><i class="icon-save"> Save as...</i></a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="row">
		<textarea id="<portlet:namespace/>canvas" class="osp-canvas col-sm-12"></textarea>
	</div>
	<div id="<portlet:namespace/>hiddenSection" class="hidden">
		<div id="<portlet:namespace/>fileExplorer" title="Select a file" >
	              <div id="<portlet:namespace/>file-explorer-content" style="height: 95%"></div>
	              <div>
	                  <input id="<portlet:namespace/>file-explorer-ok" type="button" value="OK">
	                  <input id="<portlet:namespace/>file-explorer-cancel" type="button" value="Cancel">
	              </div>
		</div>
		<div id="<portlet:namespace/>confirmDialog">
			<input type="text" id="<portlet:namespace/>uploadFileName"/><br/>
			<p>
				File already exists. Change file name or just click 'OK' button to overlap. 
			</p>
		</div>
		<input type="file" id="<portlet:namespace/>selectFile"/>
	</div>
</div>
<script>

$(document).ready(function(){
	/***********************************************************************
	 * Global variables section
	 ***********************************************************************/
	var connector = '';
	var fileExplorerDialog = $('#<portlet:namespace/>fileExplorer');
	var fileExplorerId = 'FileExplorer_WAR_OSPFileExplorerportlet_INSTANCE_te';
	if( '<portlet:namespace/>'.lastIndexOf('_INSTANCE_') > 0)
		fileExplorerId += '<portlet:namespace/>'.substring('<portlet:namespace/>'.lastIndexOf('_INSTANCE_')+10);
	else
		fileExplorerId += '001';
	var initData;
	var currentData;
	var saveAction = false;
	var displayOptions = JSON.parse('<%=displayOptions%>');
	if( !displayOptions.menu ){
		//$('.dropdown').hide();
	}
	
	$('#portlet_<%=portletDisplay.getId()%>').find('.osp-title').text('No File Loaded');
	console.log('<%=displayOptions.toString()%>');
	/***********************************************************************
	 * Menu click events and binding functions 
	 ***********************************************************************/
	 $('#<portlet:namespace/>sample').click(function(){
			var myId = '<%=portletDisplay.getId()%>';
			var eventData = {
					portletId: myId,
					targetPortlet: connector
			};
			
			Liferay.fire( OSP.Event.OSP_REQUEST_SAMPLE_CONTENT, eventData);
	 });
	 
	 function saveAs(){
		var inputData = new OSP.InputData();
		inputData.type( OSP.Enumeration.PathType.FOLDER);
		inputData.repositoryType( '<%=OSPRepositoryTypes.USER_HOME.toString()%>' );
		inputData.parent( '' );
		inputData.name( '' );
		inputData.relative( true );
		saveAction = true;
		
		fileExplorerDialog( 'VIEW', inputData );
	 }

	 $('#<portlet:namespace/>openLocal').click(function(){
		 $('#<portlet:namespace/>selectFile').click();
	 });

	 function openServerFile(){
		var inputData;
		if(	initData && 
				initData.type() === OSP.Enumeration.PathType.FILE &&
				initData.type() === OSP.Enumeration.PathType.FOLDER &&
				initData.type() === OSP.Enumeration.PathType.EXT ){
			inputData = initData.clone();
		}
		else{
			inputData = new OSP.InputData();
			inputData.repositoryType( '<%=OSPRepositoryTypes.USER_HOME.toString()%>' );
			inputData.type( OSP.Enumeration.PathType.FOLDER );
			inputData.parent('');
			inputData.name('');
		}
		fileExplorerDialog( 'VIEW', inputData );
	 }

	$('#<portlet:namespace/>canvas').on('change', function(){
		var inputData = new OSP.InputData();
		inputData.type( OSP.Enumeration.PathType.FILE_CONTENT );
		if( $.isEmptyObject(initData) ){
			inputData.repositoryType('<%=OSPRepositoryTypes.USER_HOME.toString()%>');
		}
		else if( initData.repositoryType() )
			inputData.repositoryType(initData.repositoryType());
		else{
			inputData.repositoryType('<%=OSPRepositoryTypes.USER_HOME.toString()%>');
		}
		inputData.context( $(this).val() );
		
		var eventData = {
				portletId: '<%=portletDisplay.getId()%>',
				targetPortlet: connector,
				data: OSP.Util.toJSON(inputData)
		};
		
		Liferay.fire(OSP.Event.OSP_DATA_CHANGED, eventData );
	});

	$("#<portlet:namespace/>file-explorer-ok").click(function(e){
		  e.preventDefault();
		  var eventData = {
		      portletId : '<%=portletDisplay.getId()%>',
		      targetPortlet : fileExplorerId,
		      params: saveAction
		  };
		  Liferay.fire( OSP.Event.OSP_REQUEST_DATA, eventData);
	});

	$("#<portlet:namespace/>file-explorer-cancel").click(function(e){
		e.preventDefault();
		fileExplorerDialog.dialog( 'close' );
	});

	function fileExplorerDialog( options, inputData ){
		AUI().use('liferay-portlet-url', function(A){
			var dialogURL = Liferay.PortletURL.createRenderURL();
			dialogURL.setPortletId(fileExplorerId);
			dialogURL.setParameter('inputData', JSON.stringify(inputData));
			dialogURL.setParameter('displayOptions', options);
			dialogURL.setParameter('connector', '<%=portletDisplay.getId()%>');
			dialogURL.setWindowState('<%=LiferayWindowState.EXCLUSIVE%>');
			
			// $("#<portlet:namespace/>file-explorer-content").empty();
			if($("#<portlet:namespace/>file-explorer-content").children().length > 0){
				fileExplorerDialog.dialog("open");
			}else{
				$("#<portlet:namespace/>file-explorer-content").load( dialogURL.toString());
				fileExplorerDialog.dialog("open");
			}
		});
	} 

	$('#<portlet:namespace/>selectFile').bind(
			'change', 
			function(event){
				var reader = new FileReader();
				reader.onload = function (evt) {
					$('#<portlet:namespace/>canvas').val(reader.result);
	                $('#<portlet:namespace/>canvas').trigger('change');
				};
				var inputFile = event.target;
				reader.readAsText(inputFile.files[0]);
			}
	);

	
	$('#<portlet:namespace/>canvas').on('change', function(){
			console.log( $('#<portlet:namespace/>canvas').val());
	});
});
</script>