<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	
<div class="col-xs-12">	
	<form:form commandName="sysUserFilter" cssClass="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="name-filter">商家名称</label>
			<div class="col-sm-6">
				<input type="text" name="name" id="name-filter" placeholder="请输入商家名称" class="form-control" value="${sysUserFilter.name }">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="name-filter">电话号码</label>
			<div class="col-sm-6">
				<input type="text" name="mobile" id="name-filter" placeholder="请输入手机号码" class="form-control" value="${sysUserFilter.mobile }">
			</div>
		</div>
		
	</form:form>
</div>