<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
		
<form:form commandName="sysBas" cssClass="form-horizontal" role="form">
	<div class="form-group">
	
	
	
			 <label class="col-sm-1 control-label no-padding-right" for="name">名称</label>
		<div class="col-sm-3">
			<input type="text" name="name" id="name" placeholder="请输入名称" value="${sysBas.name}" class="form-control required">
		</div>
				<label class="col-sm-1 control-label no-padding-right" for="basId">编号</label>
		<div class="col-sm-2">
			<input type="number" name="basId" id="basId" placeholder="请输入小类编号" value="${sysBas.basId}" class="form-control required">
		</div>
		  <input type="hidden" name="claId" id="claId" placeholder="请输入大类编号" value="${sysBas.claId}" class="form-control required" readonly="readonly">    
		
		
	</div>
	
	
	
	<form:hidden path="sysId"/> 
</form:form>

<script type="text/javascript">
$('#editor1').ace_wysiwyg({
	toolbar:
	[
		'font',
		null,
		'fontSize',
		null,
		{name:'bold', className:'btn-info'},
		{name:'italic', className:'btn-info'},
		{name:'strikethrough', className:'btn-info'},
		{name:'underline', className:'btn-info'},
		null,
		{name:'createLink', className:'btn-pink'},
		{name:'unlink', className:'btn-pink'},
		null,
		{name:'insertImage', className:'btn-success'},
		null,
		'foreColor',
		null,
		{name:'undo', className:'btn-grey'},
		{name:'redo', className:'btn-grey'}
	]
});

</script>
