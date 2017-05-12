<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- header -->
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<link rel="stylesheet"
	href="<s:url value="/ueditor/themes/default/css/ueditor.css"></s:url>" />
</head>
<body class="no-skin">
	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<!-- /section:settings.box -->
					<div class="row">
						<div class="col-xs-12">
							<form class="form-horizontal" onsubmit="return false;"
								role="form" id="form">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right"
										for="title">标题</label>
									<div class="col-sm-3">
										<input type="text" name="title" id="title"
											placeholder="请输入活动标题" value="${centuryBanner.title }"
											class="form-control required">
									</div>

									<label class="col-sm-1 control-label no-padding-right"
										for="content">备注</label>
									<div class="col-sm-3">
										<input type="text" name="memo" id="memo" placeholder="请输入内容"
											value="${centuryBanner.memo}" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right"
										for="pictureId">图片</label>
									<div class="col-sm-3">
										<input class="col-xs-8" type="text" name="image"
											id="image" placeholder="请输入链接" readonly="readonly"
											value="${centuryBanner.image}"
											class="form-control required">

										<button class="btn btn-sm btn-success col-xs-4"
											onclick="upImage()">图片修改</button>
									</div>

									<label class="col-sm-1 control-label no-padding-right"
										for="orderBy">排序</label>
									<div class="col-sm-3">
										<input type="number" name="orderBy" id="orderBy" min="1"
											placeholder="请输入排序" value="${centuryBanner.orderBy }"
											class="form-control required">
									</div>
								</div>
								
								<input type="hidden" name="content" id="content">
								<input type="hidden" name="sysId" value="${centuryBanner.sysId}">
							</form>
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-8">
									<div class="pull-right">
										<c:if test="${empty centuryBanner.sysId}">
											<button class="btn btn-info" id="add">提交</button>
										</c:if>
										<c:if test="${!empty centuryBanner.sysId}">
											<button class="btn btn-info" id="edit">修改</button>
										</c:if>
										<button class="btn btn-pink" id="clear">重置</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

		<!-- dialog -->
		<%@ include file="/WEB-INF/views/include/dialog.jsp"%>
	</div>
	<!-- /.main-container -->
	</div>
	<!-- footer -->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	<script type="text/plain" id="upload_ue"></script>
	<link rel="stylesheet"
		href="<s:url value="/ueditor/themes/default/css/ueditor.css"></s:url>" />
	<script type="text/javascript"
		src="<s:url value="/ueditor/ueditor.config.js"></s:url>"></script>
	<script type="text/javascript"
		src="<s:url value="/ueditor/ueditor.all.min.js"></s:url>"></script>
	<script type="text/javascript">
		var ue = UE.getEditor('container', {
			initialFrameHeight : 300,
			autoHeightEnabled : false
		});
		var entity = '${centuryBanner.sysId}';
		if (entity != "") { //判断是否是修改
			$("#edit").click(
							function() {
								//$("#content").val(ue.getContent());
								if ($("#form").valid()) {
									postCallBack({
										data : serialize("#form"),
										url : '<s:url value="/modules/century/banner/edit"></s:url>',
										successFunc : function(data) {
											if (data.code == "200") {
												/* window.parent.frames['appiframe-activity'].query();//实现跳窗口更新数据 并且带参数值的查询 */
												var appid = "bannerEdit";
												window.parent.closeapp($(
														"#task-content li[app-id='"
																+ appid + "']",
														parent.document));
											}
										}
									});
								}
							});
		} else {
			$("#add").click(
							function() {
								//$("#content").val(ue.getContent());
								if ($("#form").valid()) {
									postCallBack({
										data : serialize("#form"),
										url : '<s:url value="/modules/century/banner/create"></s:url>',
										successFunc : function(data) {
											if (data.code == "200") {
												/* window.parent.frames['appiframe-activity'].query();//实现跳窗口更新数据 并且带参数值的查询 */
												var appid = 'bannerCreate';
												window.parent.closeapp($(
														"#task-content li[app-id='"
																+ appid + "']",
														parent.document));

											}
										}
									});
								}
							});
		}
		$("#clear").click(function() {
			//var queryWindows = window.parent.frames['appiframe-activity'].document.getElementById('formDialogBody');
			//alert(queryWindows);
			window.parent.frames['appiframe-banner'].query();
		});
		jQuery(function($) {
			$('.date-picker').datepicker({
				inline : true,
				format : "yyyy-mm-dd",
				autoclose : true,
				todayHighlight : true,
				language : 'cn'
			})
			//show datepicker when clicking on the icon
			.next().on(ace.click_event, function() {
				$(this).prev().focus();
			});
		});

		$('.date-time').timepicker({
			minuteStep : 1,
			showSeconds : true,
			showMeridian : false
		}).next().on(ace.click_event, function() {
			$(this).prev().focus();
		});

		//上传独立使用
		var _editor = UE.getEditor('upload_ue');
		_editor.ready(function() {
			_editor.setDisabled();
			_editor.hide();
			_editor.addListener('beforeInsertImage', function(t, arg) { //侦听图片上传
				//将地址赋值给相应的input
				$("#image").attr("value", arg[0].src);
				//图片预览
				$("#preview").attr("src", arg[0].src);
			})
			_editor.addListener('afterUpfile', function(t, arg) {
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

		$("#btn").click(
				function() {
					var appid = 'bannerCreate';
					window.parent.closeapp($("#task-content li[app-id='"
							+ appid + "']", parent.document));
				});
	</script>
</body>
</html>