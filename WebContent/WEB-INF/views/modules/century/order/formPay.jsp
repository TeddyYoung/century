<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="row" >
	<div class="col-md-7">
		<form:form commandName="centuryConsume" cssClass="form-horizontal" role="form">
			
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="sysId">订单Id</label>
				<div class="col-sm-10">
					<input type="text" name="sysId" id="sysId" placeholder="订单Id" value="${centuryConsume.sysId }" disabled class="form-control required">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="userId">客户Id</label>
				<div class="col-sm-10">
					<input type="text" name="userId" id="userId" placeholder="请输入客户Id" value="${centuryConsume.userId }" disabled class="form-control required">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="shopId">商家Id</label>
				<div class="col-sm-10">
					<input type="text" name="shopId" id="shopId" placeholder="商家Id" value="${centuryConsume.shopId }" disabled class="form-control required">
				</div>
			</div>
			
			<%-- <div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="content">消费项目</label>
				<div class="col-sm-10">
					<input type="text" name="content" id="content" placeholder="请输消费项目" value="${centuryConsume.content }" disabled class="form-control required">
				</div>
			</div> --%>
			
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="balance">消费金额</label>
				<div class="col-sm-10">
					<input type="text" name="balance" id="balance" placeholder="请输入消费金额" value="${centuryConsume.balance }" disabled class="form-control required">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="consumeType">消费方式</label>
				<div class="col-sm-10">
					<form:select path="consumeType" items="${redis:getDictList('ConsumeType',false)}" itemLabel="name" itemValue="basId"  cssClass="form-control required"></form:select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="demo">备注</label>
				<div class="col-sm-10">
					<textarea  name="demo" rows="5" disabled  class="form-control required" >${centuryConsume.demo }</textarea>
				</div>
			</div>
			
		</form:form>
	</div>
	<div class="col-md-5">
		<img  class="img-responsive center-block" alt="二维码" src="<s:url value="/modules/century/order/qrcode/${centuryConsume.sysId }"></s:url>" >
	</div>
</div>	
<script type="text/javascript">

</script>
