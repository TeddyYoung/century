<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	
<div class="col-xs-12">	
	<form:form commandName="sysMsgFilter" cssClass="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="chsName">中文名称</label>
			<div class="col-sm-3">
				<input type="text" name="chsName" id="chsName" placeholder="请输入中文名称" value="${sysMsgFilter.chsName}" class="form-control">
			</div>
			
			<label class="col-sm-2 control-label no-padding-right" for="enName">英文名称</label>
			<div class="col-sm-3">
				<input type="text" name="enName" id="enName" placeholder="请输入英文名称" value="${sysMsgFilter.enName}" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="msgType">类型</label>
			<div class="col-sm-3">
				<input type="text" name="msgType" id="msgType" placeholder="请输入类型" class="form-control" value="${sysMsgFilter.msgType}">
			</div>
		</div>
	</form:form>
</div>