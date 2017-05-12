<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<form:form commandName="merchant" cssClass="form-horizontal" role="form">
	<div class="form-group">
		<label class="col-sm-1 control-label no-padding-right" for="mobile">手机号码</label>
		<div class="col-sm-3">
			<input type="text" name="mobile" id="mobile" placeholder="请输入会员手机号码"
				value="${merchant.mobile}" class="form-control required">
		</div>
	</div>
</form:form>

<script type="text/javascript">
	$('#editor1').ace_wysiwyg({
		toolbar : [ 'font', null, 'fontSize', null, {
			name : 'bold',
			className : 'btn-info'
		}, {
			name : 'italic',
			className : 'btn-info'
		}, {
			name : 'strikethrough',
			className : 'btn-info'
		}, {
			name : 'underline',
			className : 'btn-info'
		}, null, {
			name : 'createLink',
			className : 'btn-pink'
		}, {
			name : 'unlink',
			className : 'btn-pink'
		}, null, {
			name : 'insertImage',
			className : 'btn-success'
		}, null, 'foreColor', null, {
			name : 'undo',
			className : 'btn-grey'
		}, {
			name : 'redo',
			className : 'btn-grey'
		} ]
	});
</script>
