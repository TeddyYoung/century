<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
		
<form:form commandName="centuryConsume" cssClass="form-horizontal" role="form">
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="userId">客户Id</label>
		<div class="col-sm-6">
			<input type="text" name="userId" id="userId" placeholder="请输入客户Id" value="" class="form-control required">
		</div>
	</div>
	<!-- <div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="content">消费项目</label>
		<div class="col-sm-6">
			<input type="text" name="content" id="content" placeholder="请输消费项目" value="" class="form-control required">
		</div>
	</div> -->
	
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="balance">消费金额</label>
		<div class="col-sm-6">
			<input type="text" name="balance" id="balance" placeholder="请输入消费金额" value="" class="form-control required">
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="consumeType">消费方式</label>
		<div class="col-sm-6">
			<form:select path="consumeType" items="${redis:getDictList('ConsumeType',false)}" itemLabel="name" itemValue="basId" cssClass="form-control required"></form:select>
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="demo">备注</label>
		<div class="col-sm-6">
			<textarea  name="demo" rows="5" class="form-control required" ></textarea>
		</div>
	</div>
	
	<form:hidden path="shopId"/> 
</form:form>

<script type="text/javascript">

</script>
