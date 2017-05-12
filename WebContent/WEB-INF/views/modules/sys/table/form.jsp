<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
		
<form:form commandName="sysTable" cssClass="form-horizontal" role="form">
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="name">逻辑名称</label>
		<div class="col-sm-4">
			<input type="text" name="name" id="name" placeholder="请输入逻辑名称" value="${sysTable.name}" class="form-control required">
		</div>
		
		<label class="col-sm-2 control-label no-padding-right" for="tableName">物理名称</label>
		<div class="col-sm-4">
			<input type="text" name="tableName" id="tableName" placeholder="请输入物理名称" value="${sysTable.tableName}" class="form-control required">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="preNum">前缀位数</label>
		<div class="col-sm-4">
			<input type="number" id="preNum" name="preNum" placeholder="请输入前缀位数"  value="${sysTable.preNum}" max="8" min="2" class="form-control required">
		</div>
		<label class="col-sm-2 control-label no-padding-right" for="serialNum">流水号位数</label>
		<div class="col-sm-4">
			<input type="number" id="serialNum" name="serialNum" placeholder="请输入流水号位数"  value="${sysTable.serialNum}" class="form-control required">
		</div>
	</div>
	
	<c:if test="${not empty sysTable.sysId }">
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="maxSysId">最大SYSID</label>
			<div class="col-sm-4">
				<input type="number" id="maxSysId" name="maxSysId" placeholder="请输入最大SYSID" readonly="readonly" value="${sysTable.maxSysId}" class="form-control">
			</div>
			
			<label class="col-sm-2 control-label no-padding-right" for="maxSerialNum">最大流水位</label>
			<div class="col-sm-4">
				<input type="number" id="maxSerialNum" name="maxSerialNum" placeholder="请输入最大流水位" readonly="readonly" value="${sysTable.maxSerialNum}" class="form-control">
			</div>
		</div>
	</c:if>
	<form:hidden path="sysId"/> 
	<form:hidden path="maxVal"/> 
	<form:hidden path="minVal"/> 
</form:form>
