<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
		
<form:form commandName="sysDept" cssClass="form-horizontal" role="form">
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="deptName">部门名称</label>
		<div class="col-sm-4">
			<input type="text" name="deptName" id="deptName"
				placeholder="请输入部门名称" value="${sysDept.deptName}"
				class="form-control required">
		</div>
		<label class="col-sm-2 control-label no-padding-right"
			for="deptEnname">英文名称</label>
		<div class="col-sm-4">
			<input type="text" name="deptEnname" id="deptEnname"
				placeholder="请输入英文名称" value="${sysDept.deptEnname}"
				class="form-control required">
		</div>
	</div>
	<div class="form-group">
		<c:if test="${empty sysDept.deptId}">
			<label class="col-sm-2 control-label no-padding-right" for="deptId">部门编号</label>
			<div class="col-sm-4">
				<input type="text" name="deptId" id="deptId" placeholder="请输入部门编号"
					value="${sysDept.deptId}" class="form-control required">
			</div>
		</c:if>
			<%-- 	<label class="col-sm-2 control-label no-padding-right" for="bossId">领导</label>
		<div class="col-sm-4">
			<input type="text" id="deptLevel" name="bossId" placeholder="请输入领导"  value="${sysDept.bossId}" class="form-control ">
		</div> --%>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="deptRelation">关联部门</label>
		<div class="col-sm-4">
			<input id="deptRelation" name="deptRelation" placeholder="请选择关联部门"  value="${sysDept.deptRelation}" readonly="readonly" class="form-control" />
			<div id="resourceContent" class="col-xs-3 ztreeContent">
				<ul id="resourceTree" class="ztree ztreebox"></ul>
			</div>
		</div>
		
		<label class="col-sm-2 control-label no-padding-right" for="deptLevelForm">部门级别</label>
		<div class="col-sm-4">
			<input type="number" id="deptLevelForm" name="deptLevel" readonly="readonly" placeholder="请输入部门级别"  value="${sysDept.deptLevel}" class="form-control required">
		</div>	
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="orderBy">排序</label>
		<div class="col-sm-4">	
			<input type="text" id="orderBy" name="orderBy" placeholder="请输入排序"  value="${sysDept.orderBy}" class="form-control required">
		</div>
		<label class="col-sm-2 control-label no-padding-right" for="state">状态</label>
		<div class="col-sm-4">
			<form:select path="state" items="${redis:getDictList('DeptState',false)}" itemLabel="name" itemValue="basId" cssClass="form-control required"></form:select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="msgId">多语系ID</label>
		<div class="col-sm-4">
			<input type="text" name="msgId" id="msgId" placeholder="请输入msgId" value="${sysMenu.msgId}" class="form-control required">
		</div>
	</div>
	<form:hidden path="sysId"/> 
</form:form>
	<script src="<s:url value="/assets/js/chosen.jquery.js"></s:url>"></script>
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
//settingOneCheckTreeParam('#deptRelation','#deptRelation','resourceTree','#resourceTree','#resourceContent','<s:url value="/modules/sys/dept/json"></s:url>','#deptLevel','${sysDept.sysId}');

 	settingOneCheckTreeParam({
 		inputObjKey : '#deptRelation',
 		treeObjName : 'resourceTree',
 		treeContentKey : '#resourceContent',
		url : '<s:url value="/modules/sys/dept/json"></s:url>',
		levelKey : '#deptLevelForm',
		sysId : '${sysDept.sysId}'
	});

</script>
