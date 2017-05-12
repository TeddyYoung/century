<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- header -->
		<%@ include file="/WEB-INF/views/include/header.jsp"%>
		<link rel="stylesheet" href="<s:url value="/ueditor/themes/default/css/ueditor.css"></s:url>" />
	</head>
	<body class="no-skin">
		<!-- /section:basics/navbar.layout -->
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<!-- /section:basics/sidebar -->
			<div class="main-content">
				<div class="main-content-inner">
					<div class="page-content">
						<!-- /section:settings.box -->
						<div class="row">
							<div class="col-xs-12">
								<form class="form-horizontal" onsubmit="return false;" role="form" id="form">
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="title">活动标题</label>
										<div class="col-sm-3">
											<input type="text" name="title" id="title"
												placeholder="请输入活动标题" value="${CenturyActivity.title }"
												class="form-control required" >
										</div>
							
										<label class="col-sm-2 control-label no-padding-right"
											for="state">活动状态</label>
										<div class="col-sm-3">
											<select  name="state" id="state" >
											<option value="打开" <c:if test = "${CenturyActivity.state eq '打开' }">selected="selected"</c:if> >打开</option>
											<option value="关闭" <c:if test = "${CenturyActivity.state eq '关闭' }">selected="selected"</c:if> >关闭</option>
											</select>
										</div>	
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="pictureId">活动图ID</label>
										<div class="col-sm-3">
											<input class="col-xs-8" type="text" name="pictureId" id="image"
												placeholder="请输入链接" readonly="readonly"
												value="${CenturyActivity.pictureId}"
												class="form-control required">
								
											<button class="btn btn-sm btn-success col-xs-4"
												onclick="upImage()">图片修改</button>
										</div>
							
										<label class="col-sm-2 control-label no-padding-right"
											for="CenturyActivityCast">活动经费</label>
										<div class="col-sm-3">
											<input type="number" name="activityCast" id="activityCast"
												placeholder="请输入活动经费" value="${CenturyActivity.activityCast }"
												class="form-control required" >
										</div>
									</div>
																		<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="reward">活动奖励</label>
										<div class="col-sm-3">
											<input type="number" name="reward" id="reward"
												placeholder="请输入活动奖励" value="${CenturyActivity.reward }"
												class="form-control required" >
										</div>
							
										<label class="col-sm-2 control-label no-padding-right"
											for="orderBy">排序</label>
										<div class="col-sm-3">
											<input type="number" name="orderBy" id="orderBy" min="1"
												placeholder="请输入排序" value="${CenturyActivity.orderBy }"
												class="form-control required" >
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="startDate">活动开始日期</label>
										<div class="col-sm-3">
											<div class="input-group">
												<input type="text" name="startDate" id="startDate"  class="form-control date-picker"   value="${CenturyActivity.startDate}" />
												<span class="input-group-addon">
													<i class="fa fa-calendar bigger-110"></i>
												</span>
											</div>
										</div>
										
										<label class="col-sm-2 control-label no-padding-right"
											for="startTime">活动开始时间</label>
										<div class="col-sm-3">
											<div class="input-group">
												<input type="text" name="startTime" id="startTime"  class="form-control date-time"   value="${CenturyActivity.startTime}" />
												<span class="input-group-addon">
																<i class="fa fa-clock-o bigger-110"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="endDate">活动结束日期</label>
										<div class="col-sm-3">
											<div class="input-group">
												<input type="text" name="endDate" id="endDate"  class="form-control date-picker"   value="${CenturyActivity.endDate}" />
												<span class="input-group-addon">
													<i class="fa fa-calendar bigger-110"></i>
												</span>
											</div>
										</div>	
							
										<label class="col-sm-2 control-label no-padding-right"
											for="endTime">活动结束时间</label>
										<div class="col-sm-3">
											<div class="input-group">
												<input type="text" name="endTime" id="endTime"  class="form-control date-time"   value="${CenturyActivity.endTime}" />
												<span class="input-group-addon">
																<i class="fa fa-clock-o bigger-110"></i>
												</span>
											</div>
										</div>
									</div>
									
									<input type="hidden" name="content" id="content">
									<input type="hidden" name="sysId" value="${CenturyActivity.sysId}">
								</form>
								<form class="form-horizontal" role="form" >
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="content">活动内容</label>
										<div class="col-sm-8">
											<textarea id="container">${CenturyActivity.content }</textarea>
										</div>
									</div>
								</form>
								<div class="form-group">
									<div class="col-sm-2">
									</div>
									<div class="col-sm-8">
										<div class="pull-right">
											<c:if test="${empty CenturyActivity}">
												<button class="btn btn-info" id="add">提交</button>
											</c:if>
											<c:if test="${!empty CenturyActivity}">
												<button class="btn btn-info" id="edit">修改</button>
											</c:if>
												<button class="btn btn-pink" id="clear">重置</button>
												<button class="btn btn-default" id="btn">关闭</button>
										</div>
									</div>
								</div>	
							</div>
						</div>
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
			
			<!-- dialog -->
			<%@ include file="/WEB-INF/views/include/dialog.jsp"%>
		</div><!-- /.main-container -->
	</div>	
	<!-- footer -->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	<script type="text/plain" id="upload_ue"></script>
	<link rel="stylesheet" href="<s:url value="/ueditor/themes/default/css/ueditor.css"></s:url>" />
	<script type="text/javascript"  src="<s:url value="/ueditor/ueditor.config.js"></s:url>"></script>
	<script type="text/javascript"  src="<s:url value="/ueditor/ueditor.all.min.js"></s:url>" ></script>
	<script type="text/javascript">
		var ue = UE.getEditor('container', {
			initialFrameHeight:300,
			autoHeightEnabled:false
		});
		var entity='${CenturyActivity.sysId}';
		if(entity!=""){  //判断是否是修改
			$("#edit").click(function() {
				$("#content").val(ue.getContent());
				if($("#form").valid()) {
					postCallBack({
						data:serialize("#form"),
						url:'<s:url value="/modules/century/activity/edit"></s:url>',
						successFunc:function(data){
							if(data.code=="200"){
								/* window.parent.frames['appiframe-activity'].query();//实现跳窗口更新数据 并且带参数值的查询 */
								var appid = "activityEdit";
								window.parent.closeapp($("#task-content li[app-id='" + appid + "']", parent.document));
							}
						}
					});	
				}
			});
		}
		else{		
				$("#add").click(function() {
				$("#content").val(ue.getContent());
				if($("#form").valid()) {
					postCallBack({
						data:serialize("#form"),
						url:'<s:url value="/modules/century/activity/create"></s:url>',
						successFunc:function(data){
							if(data.code=="200"){
								/* window.parent.frames['appiframe-activity'].query();//实现跳窗口更新数据 并且带参数值的查询 */
								var appid = 'activityCreate';
								window.parent.closeapp($("#task-content li[app-id='" + appid + "']", parent.document));
							
							}
						}
					});	
				}
			});
		}
		$("#clear").click(function(){
			//var queryWindows = window.parent.frames['appiframe-activity'].document.getElementById('formDialogBody');
			//alert(queryWindows);
			window.parent.frames['appiframe-activity'].query();
		});
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
		
		$('.date-time').timepicker({
			minuteStep: 1,
			showSeconds: true,
			showMeridian: false
		}).next().on(ace.click_event, function(){
			$(this).prev().focus();
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
		 
		 $("#btn").click(function() {
			 var appid = 'activityCreate';
			 window.parent.closeapp($("#task-content li[app-id='" + appid + "']", parent.document));
		 });
	</script>	
	</body>
</html>