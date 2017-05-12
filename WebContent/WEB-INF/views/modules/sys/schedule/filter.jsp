<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	
<div class="col-xs-12">	
	<form:form commandName="filter" cssClass="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="name-filter">任务名称</label>
			<div class="col-sm-3">
				<input type="text" name="name" id="name-filter" placeholder="请输入任务名称" value="${filter.jobName }" class="form-control">
			</div>
			
			<label class="col-sm-2 control-label no-padding-right" for="tableName-filter">任务分组</label>
			<div class="col-sm-3">
				<input type="text" name="jobGroup" id="jobGroup-filter" placeholder="请输入任务分组" value="${filter.jobGroup }" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="springId-filter">springId</label>
			<div class="col-sm-3">
				<input type="text" name="springId" id="springId-filter" placeholder="请输入springId" class="form-control" value="${filter.springId }">
			</div>
			
			<label class="col-sm-2 control-label no-padding-right" for="beanClass-filter">子系统</label>
			<div class="col-sm-3">
				<input type="text" name="beanClass" id="beanClass-filter" placeholder="请输入beanClass" class="form-control" value="${filter.beanClass }">
			</div>
		</div>
	</form:form>
</div>