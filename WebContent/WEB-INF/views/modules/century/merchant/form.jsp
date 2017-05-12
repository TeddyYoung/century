<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- header -->
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<link rel="stylesheet"
	href="<s:url value="/ueditor/themes/default/css/ueditor.css"></s:url>" />
<script type="text/javascript"
	src="<s:url value="/ueditor/ueditor.config.js"></s:url>"></script>
<script type="text/javascript"
	src="<s:url value="/ueditor/ueditor.all.min.js"></s:url>"></script>
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
							<form:form commandName="merchant" cssClass="form-horizontal"
								role="form" onsubmit="return false;" id="form">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">商家信息</h3>
									</div>
									<div class="panel-body">
										<div class="col-sm-12">
											<div class="form-group">
												<label class="col-sm-2 control-label no-padding-right"
													for="name">*商家名称</label>
												<div class="col-sm-4">
													<input type="text" name="merchantName" id="merchantName"
														placeholder="请输入商家名称" value="${merchant.merchantName}"
														class="form-control required">
												</div>

												<label class="col-sm-2 control-label no-padding-right"
													for="email">商家简称</label>
												<div class="col-sm-4">
													<input type="text" name="shortName" id="shortName"
														placeholder="请输入商家简称" value="${merchant.shortName}"
														class="form-control required">
												</div>

											</div>

											<div class="form-group">
												<label class="col-sm-2 control-label no-padding-right"
													for="mobile">*手机号码</label>
												<div class="col-sm-4">
													<input type="text" name="mobile" id="mobile" disable="true"
														placeholder="请输入手机号码" value="${merchant.mobile}"
														class="form-control required" readonly="true">
												</div>

												<label class="col-sm-2 control-label no-padding-right"
													for="password">*城市</label>
												<div class="col-sm-4">
													<input name="city" id="city" placeholder="请输入城市"
														value="${merchant.city}" class="form-control required">
												</div>
											</div>

											<div class="form-group">
												<label class="col-sm-2 control-label no-padding-right"
													for="password">*地址</label>
												<div class="col-sm-4">
													<input name="address" id="address" placeholder="请输入地址"
														value="${merchant.address}" class="form-control required">
												</div>
												<label class="col-sm-2 control-label no-padding-right"
													for="demo">*电话号码</label>
												<div class="col-sm-4">
													<input type="text" name="telephone" id="telephone"
														placeholder="请输入电话号码" value="${merchant.telephone}"
														class="form-control required">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label no-padding-right"
													for="password">电话号码1</label>
												<div class="col-sm-4">
													<input name="telephoneOne" id="telephoneOne" placeholder="请输入电话号码"
														value="${merchant.telephoneOne}"
														class="form-control required">
												</div>
												<label class="col-sm-2 control-label no-padding-right"
													for="demo">折扣</label>
												<div class="col-sm-4">
													<input type="text" name="discount" id="discount"
														placeholder="请输入折扣" value="${merchant.discount}"
														class="form-control required">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label no-padding-right"
													for="logo">logo</label>
												<div class="col-sm-3">
													<input class="col-xs-8" type="text" name="logo" id="logo"
														placeholder="请输入链接" readonly="readonly"
														value="${merchant.logo}" class="form-control required">

													<button class="btn btn-sm btn-success col-xs-4"
														onclick="upImage()">图片修改</button>
												</div>
											</div>
										</div>
									</div>
								</div>
								<input type="hidden" name="sysId" value="${merchant.sysId}">
							</form:form>
							<h3 class="header smaller lighter green"></h3>
							<div class="col-xs-9" align="right">
								<c:if test="${empty merchant.sysId}">
									<button class="btn btn-info" id="add">保存</button>
								</c:if>
								<c:if test="${!empty merchant.sysId}">
									<button class="btn btn-info" id="edit">修改</button>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->
	</div>
	<a href="#" id="btn-scroll-up"
		class="btn-scroll-up btn btn-sm btn-inverse"> <i
		class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>

	<!-- dialog -->
	<%@ include file="/WEB-INF/views/include/dialog.jsp"%>
	<!-- /.main-container -->
	<!-- footer -->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	<script type="text/plain" id="upload_ue"></script>
	<script type="text/javascript">
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
		var ue = UE.getEditor('container', {
			initialFrameHeight : 300,
			autoHeightEnabled : false
		});

		//上传独立使用
		var _editor = UE.getEditor('upload_ue');
		_editor.ready(function() {
			_editor.setDisabled();
			_editor.hide();
			_editor.addListener('beforeInsertImage', function(t, arg) { //侦听图片上传
				//将地址赋值给相应的input
				$("#logo").attr("value", arg[0].src);
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
			var myFiles = _editor.getDialog("merchant");
			myFiles.open();
		}

		var entity = '${merchant.sysId}';
		if (entity != "") { //判断是否是修改
			$("#edit")
					.click(
							function() {
								if ($("#form").valid()) {
									$.ajax({
											type : "post",
											url : "<s:url value="/modules/century/merchant/edit"></s:url>",
											data : serialize("#form"),
											async : false,
											success : function(data) {
												if (data.code == "200") {
													alert("修改成功");
													var appid = "merchantEdit";
													window.parent
															.closeapp($(
																	"#task-content li[app-id='"
																			+ appid
																			+ "']",
																	parent.document))
												} else {
													alert(data);
												}

											}
									});
								}
							});
		} else {
			$("#add")
					.click(
							function() {
								var flag = false;
								var mobile = $("#mobile").val();
								if (mobile != "") {
									$.ajax({
											type : "post",
											url : "<s:url value="/api/merchant/checkMobileMerchant"/>",
											data : {
												mobile : mobile
											},
											async : false,
											success : function(data) {
												if (data == false) {
													showGritter('消息提示',
															'该号码未注册或已经成为商家');//消息提示
												} else {
													flag = true;
												}
											}
									});
								}
								if (flag) {
									if ($("#form").valid()) {
										$.ajax({
												type : "post",
												url : "<s:url value="/modules/century/merchant/create"></s:url>",
												data : serialize("#form"),
												async : false,
												success : function(data) {
													if (data.code == "200") {
														alert("保存成功");
														var appid = "merchantCreate";
														window.parent
																.closeapp($(
																		"#task-content li[app-id='"
																				+ appid
																				+ "']",
																		parent.document))
													} else {
														alert(data);
													}

												}
										});
									}
								}
							});
		}

		$("#btn").click(
				function() {
					var appid = 'merchantCreate';
					window.parent.closeapp($("#task-content li[app-id='"
							+ appid + "']", parent.document));
				});
	</script>