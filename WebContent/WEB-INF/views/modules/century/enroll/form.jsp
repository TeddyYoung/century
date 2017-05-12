<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
		
<form:form commandName="centuryEnroll" onsubmit="return false;"  cssClass="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="activityId">活动</label>
			<div class="col-sm-3">
				<form:select id="activities" path="activityId" items="${activities}"
					itemLabel="title" itemValue="sysId"></form:select>
			</div>
			<label class="col-sm-2 control-label no-padding-right" for="userId">用户</label>
			<div class="col-sm-3">
				<form:select id="users" path="userId" items="${users}"
					itemLabel="userName" itemValue="userId"></form:select>
			</div>
		</div>
		
		<div class="form-group">	
			<label class="col-sm-2 control-label no-padding-right"
				for="activityImg">活动图</label>
			<div class="col-sm-3">
				<input class="col-xs-8" type="text" name="image" id="image"
					placeholder="请输入链接" readonly="readonly"
					value="${centuryNewscast.image}"
					class="form-control required">
	
				<button class="btn btn-sm btn-success col-xs-4"
					onclick="upImage()">图片修改</button>
			</div>
		</div>	
	
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="state"> 状态</label>
			<div class="col-sm-3">
				<input type="text" name="state" id="state" placeholder="请输入 状态" value="${centuryEnroll.state}" class="form-control required">
			</div>
			<label class="col-sm-2 control-label no-padding-right" for="receiced"> 领取电子币状态</label>
			<div class="col-sm-3">
				<input type="text" name="receiced" id="receiced" placeholder="请输入 领取电子币状态" value="${centuryEnroll.receiced}" class="form-control required">
			
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="receicedDate">电子币领取日期</label>
			<div class="col-sm-3">
				<div class="input-group">
					<input type="text" name="receicedDate" id="receicedDate"  class="form-control date-picker"   value="${centuryEnroll.receicedDate}" />
					<span class="input-group-addon">
						<i class="fa fa-calendar bigger-110"></i>
					</span>
				</div>
			</div>
			<label class="col-sm-2 control-label no-padding-right" for="receicedTime">电子币领取时间</label>
			<div class="col-sm-3">
				<input type="text" name="receicedTime" id="receicedTime" placeholder="请输入电子币领取时间" value="${centuryEnroll.receicedTime}" class="form-control required">
			
			</div>
		</div>
	<form:hidden path="sysId"/> 
</form:form>
	<script type="text/plain" id="upload_ue" style="z-index:999999"></script>
	<link rel="stylesheet" href="<s:url value="/ueditor/themes/default/css/ueditor.css"></s:url>" />
	<script type="text/javascript"  src="<s:url value="/ueditor/ueditor.config.js"></s:url>"></script>
	<script type="text/javascript"  src="<s:url value="/ueditor/ueditor.all.js"></s:url>" ></script>
<script type="text/javascript">
jQuery(function($) {
	$('.date-picker').datepicker({
		inline: true,
		format:"yyyy-mm-dd",
		autoclose: true,
		todayHighlight: true,
		language: 'cn'
	})
	//show datepicker when clicking on the icon
	.next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
});

//上传独立使用
var _editor = UE.getEditor('upload_ue');
_editor.ready(function () {
    _editor.setDisabled();
    _editor.hide();
    _editor.addListener('beforeInsertImage', function (t, arg) {     //侦听图片上传
    	//将地址赋值给相应的input
        $("#image").attr("value", arg[0].src);
        //图片预览
        $("#preview").attr("src", arg[0].src);
    })
    _editor.addListener('afterUpfile', function (t, arg) {
        $("#file").attr("value", arg[0].url);
    })
});
function upImage() {
    var myImage = _editor.getDialog("insertimage");
    myImage.open();
}
function upFiles() {
    var myFiles = _editor.getDialog("attachment");
    myFiles.open();
}


</script>
