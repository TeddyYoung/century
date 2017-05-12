<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	
<div class="col-xs-12">	
	<form:form commandName="sysBasFilter" cssClass="form-horizontal" role="form">
		<div class="form-group">
			
			
			<label class="col-sm-2 control-label no-padding-right" for="tableName">小类编号</label>
			<div class="col-sm-3">
				<input type="text" name="basId" id="basId" placeholder="请输入小类名称" value="${sysBasFilter.basId }" class="form-control">
			</div>
			
			
			<label class="col-sm-2 control-label no-padding-right" for="tableType">名称</label>
			<div class="col-sm-3">
				<input type="text" name="name" id="name" placeholder="请输入名称" class="form-control" value="${sysBasFilter.name }">
			</div>
		</div>
		
	</form:form>
</div>