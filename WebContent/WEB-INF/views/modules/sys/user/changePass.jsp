<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<form:form commandName="databean" cssClass="form-horizontal" role="form">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">密码修改</h3>
		</div>
		<div class="panel-body">
			<div class="col-sm-9">
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right" for="oldPassword">旧密码</label>
					<div class="col-sm-8">
						<input type="password" name="oldPassword" id="oldPassword" placeholder="请输入旧密码" class="form-control required">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label no-padding-right" for="password">新密码</label>
					<div class="col-sm-8">
						<input type="password" name="password" id="password" placeholder="请输入新密码" class="form-control required">
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>