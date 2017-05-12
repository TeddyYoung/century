<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
		
<form:form commandName="scheduleJob" cssClass="form-horizontal" role="form">
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="jobName">任务名称</label>
		<div class="col-sm-4">
			<input type="text" name="jobName" id="jobName" <c:if test="${not empty scheduleJob.jobName}">readonly="readonly" </c:if> placeholder="请输入任务名称" value="${scheduleJob.jobName}" class="form-control required">
		</div>
		
		<label class="col-sm-2 control-label no-padding-right" for="jobGroup">任务分组</label>
		<div class="col-sm-4">
			<input type="text" name="jobGroup" id="jobGroup" <c:if test="${not empty scheduleJob.jobName}">readonly="readonly" </c:if> placeholder="请输入任务分组" value="${scheduleJob.jobGroup}" class="form-control required">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="cronExpression">表达式</label>
		<div class="col-sm-4">
			<input type="text" id="cronExpression" name="cronExpression" placeholder="请输入表达式"  value="${scheduleJob.cronExpression}" class="form-control required">
		</div>
		
		<label class="col-sm-2 control-label no-padding-right" for="beanClass">beanClass</label>
		<div class="col-sm-4">
			<input type="text" id="beanClass" name="beanClass" <c:if test="${not empty scheduleJob.jobName}">readonly="readonly" </c:if> placeholder="请输入beanClass"  value="${scheduleJob.beanClass}" class="form-control">
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="springId">springId</label>
		<div class="col-sm-4">
			<input type="text" id="springId" name="springId" <c:if test="${not empty scheduleJob.jobName}">readonly="readonly" </c:if> placeholder="请输入springId"  value="${scheduleJob.springId}" class="form-control required">
		</div>
		
		<label class="col-sm-2 control-label no-padding-right" for="methodName">methodName</label>
		<div class="col-sm-4">
			<input type="text" id="methodName" name="methodName" <c:if test="${not empty scheduleJob.jobName}">readonly="readonly" </c:if> placeholder="请输入methodName"   value="${scheduleJob.methodName}" class="form-control required">
		</div>
	</div>
	<form:hidden path="sysId"/> 
	<form:hidden path="jobStatus"/> 
	<form:hidden path="isConcurrent"/> 
</form:form>
