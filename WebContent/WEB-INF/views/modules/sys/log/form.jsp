<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
		
<form:form commandName="sysMsg" cssClass="form-horizontal" role="form">
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="msgId">信息ID</label>
		<div class="col-sm-4">
			<input type="text" name="msgId" id="msgId" placeholder="请输入信息ID" value="${sysMsg.msgId}" class="form-control required">
		</div>

		<label class="col-sm-2 control-label no-padding-right" for="chsName">简体名称</label>
		<div class="col-sm-4">
			<input type="text" name="chsName" id="chsName" placeholder="请输入简体名称" value="${sysMsg.chsName}" class="form-control required">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="enName">英文名称</label>
		<div class="col-sm-4">
			<input type="text" name="enName" id="enName" placeholder="请输入英文名称"
				value="${sysMsg.enName}" class="form-control required">
		</div>
		<label class="col-sm-2 control-label no-padding-right" for="chtName">繁体名称</label>
		<div class="col-sm-4">
			<input type="text" name="chtName" id="chtName" placeholder="请输入繁体名称" value="${sysMsg.chtName}" class="form-control required">
		</div>
		
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="msgType">类别</label>
		<div class="col-sm-4">
			<input type="text" name="msgType" id="msgType" placeholder="请输入类别" value="${sysMsg.msgType}" class="form-control required">
		</div>
		<label class="col-sm-2 control-label no-padding-right" for="attr1">参数1</label>
		<div class="col-sm-4">
			<input type="text" id="attr1" name="attr1" placeholder="请输入参数"  value="${sysMsg.attr1}" class="form-control required">
		</div>	
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="attr2">参数2</label>
		<div class="col-sm-4">
			<input type="text" id="attr2" name="attr2" placeholder="请输入参数"  value="${sysMsg.attr2}" class="form-control required">
		</div>	
		
		<label class="col-sm-2 control-label no-padding-right" for="demo">备注</label>
		<div class="col-sm-4">
			<input type="text" id="demo" name="demo" placeholder="请输入备注"  value="${sysMsg.demo}" class="form-control required">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="state">状态</label>
		<div class="col-sm-4">
			<input type="number" id="state" name="state" placeholder="请输入状态"  value="${sysMsg.state}" class="form-control required">
		</div>
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
