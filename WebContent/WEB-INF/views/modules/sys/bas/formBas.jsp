<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
		
<form:form commandName="sysBas" cssClass="form-horizontal" role="form">
	<div class="form-group">
		<label class="col-sm-1 control-label no-padding-right" for="claId">大类编号</label>
		<div class="col-sm-3">
			<input type="text" name="claId" id="claId" placeholder="请输入大类编号" value="${sysBas.claId}" class="form-control required" readonly="readonly">
		</div>
		
		<label class="col-sm-1 control-label no-padding-right" for="basId">小类编号</label>
		<div class="col-sm-3">
			<input type="text" name="basId" id="basId" placeholder="请输入小类编号" value="${sysBas.basId}" class="form-control required">
		</div>
		
		<label class="col-sm-1 control-label no-padding-right" for="name">名称</label>
		<div class="col-sm-3">
			<input type="text" name="name" id="name" placeholder="请输入名称" value="${sysBas.name}" class="form-control required">
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-1 control-label no-padding-right" for="delflag">删除标记</label>
		<div class="col-sm-3">
			<label  class="control-label">
				<input type="checkbox" name="delflag" class="ace ace-switch ace-switch-3" <c:if test="${sysBas.delflag }">checked="checked"</c:if>>
				<span class="lbl"></span>
			</label>
		</div>
		<label class="col-sm-1 control-label no-padding-right" for="modflag">修改标记</label>
		<div class="col-sm-3">
			<label class="control-label">
				<input type="checkbox" name="modflag" class="ace ace-switch ace-switch-3" <c:if test="${sysBas.modflag }">checked="checked"</c:if>>
				<span class="lbl"></span>
			</label>
		</div>
		
		<label class="col-sm-1 control-label no-padding-right" for="attr1">参数1</label>
		<div class="col-sm-3">
			<input type="text" name="attr1" id="attr1" placeholder="请输入参数1" value="${sysBas.attr1}" class="form-control">
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-1 control-label no-padding-right" for="attr2">参数2</label>
		<div class="col-sm-3">
			<input type="text" name="attr2" id="attr2" placeholder="请输入参数2" value="${sysBas.attr2}" class="form-control">
		</div>
		
		<label class="col-sm-1 control-label no-padding-right" for="attr3">参数3</label>
		<div class="col-sm-3">
			<input type="text" name="attr3" id="attr3" placeholder="请输入参数3" value="${sysBas.attr3}" class="form-control">
		</div>
		
		<label class="col-sm-1 control-label no-padding-right" for="attr4">参数4</label>
		<div class="col-sm-3">
			<input type="text" name="attr4" id="attr4" placeholder="请输入参数4" value="${sysBas.attr4}" class="form-control">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-1 control-label no-padding-right" for="attr5">参数5</label>
		<div class="col-sm-3">
			<input type="text" name="attr5" id="attr5" placeholder="请输入参数2" value="${sysBas.attr5}" class="form-control">
		</div>
		
		<label class="col-sm-1 control-label no-padding-right" for="attr6">参数6</label>
		<div class="col-sm-3">
			<input type="text" name="attr6" id="attr6" placeholder="请输入参数3" value="${sysBas.attr6}" class="form-control">
		</div>
		
		<label class="col-sm-1 control-label no-padding-right" for="orderBy">排序</label>
		<div class="col-sm-3">
			<c:if test="${empty sysBas.orderBy }">
				<input type="number" name="orderBy" id="orderBy" placeholder="请输入排序" value="1" class="form-control required">
			</c:if>
			<c:if test="${not empty sysBas.orderBy }">
				<input type="number" name="orderBy" id="orderBy" placeholder="请输入排序" value="${sysBas.orderBy}" class="form-control required">
			</c:if>
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-1 control-label no-padding-right" for="demo">备注</label>	
		<div class="col-sm-11">
			<textarea  name="demo" style="width: 98%"> ${sysUser.demo}</textarea>
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
