<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	
<div class="col-xs-12">	
	<form:form commandName="sysTableFilter" cssClass="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="name">物理名称</label>
			<div class="col-sm-3">
				<input type="text" name="name" id="name" placeholder="请输入物理名称" value="${sysTableFilter.name }" class="form-control">
			</div>
			
			<label class="col-sm-2 control-label no-padding-right" for="tableName">逻辑名称</label>
			<div class="col-sm-3">
				<input type="text" name="tableName" id="tableName" placeholder="请输入逻辑名称" value="${sysTableFilter.tableName }" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="tableType">类型</label>
			<div class="col-sm-3">
				<input type="text" name="tableType" id="tableType" placeholder="请输入类型" class="form-control" value="${sysTableFilter.tableType }">
			</div>
			
			<label class="col-sm-2 control-label no-padding-right" for="subSystem">子系统</label>
			<div class="col-sm-3">
				<input type="text" name="subSystem" id="subSystem" placeholder="请输入子系统" class="form-control" value="${sysTableFilter.subSystem }">
			</div>
		</div>
	</form:form>
</div>