<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<link rel="stylesheet" href="<s:url value="/assets/css/select2.css"></s:url>"/>
<div class="col-xs-12">
	<form:form commandName="sysDeptFilter" cssClass="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="deptName">部门名称</label>
			<div class="col-sm-3">
				<input type="text" name="deptName" id="deptName"
					placeholder="请输入部门名称" value="${sysDeptFilter.deptName }"
					class="form-control">
			</div>

			<label class="col-sm-2 control-label no-padding-right"
				for="deptEnname">英文名称</label>
			<div class="col-sm-3">
				<input type="text" name="deptEnname" id="deptEnname"
					placeholder="请输入英文名称" value="${sysDeptFilter.deptEnname }"
					class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right"
				for="deptLevel">部门级别</label>
			<div class="col-sm-3">
				<input type="text" name="deptLevel" id="deptLevel"
					placeholder="请输入部门级别" class="form-control"
					value="${sysDeptFilter.deptLevel }">
			</div>
			<label class="col-sm-2 control-label no-padding-right" for="boosId">领导姓名</label>
				<input id="boosName" type="hidden" >
				 <input id="boosId" type="hidden" />
					<!-- footer -->
			</div>
	</form:form>
</div>
<script src="<s:url value="/assets/js/select2.js"></s:url>"></script>
<script src="<s:url value="/assets/js/sirdc/sirdc.select2.js"></s:url>"></script>
<script type="text/javascript">
createselect({
	textName : '#boosName',
	valueName :"#boosId",
	url :'<s:url value="/api/sys/user/boosname"></s:url>',
	inputLength : 2,
    pageSize : 10
});
</script>