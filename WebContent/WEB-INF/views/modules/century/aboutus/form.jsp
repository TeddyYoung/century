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
							<form class="form-horizontal"  onsubmit="return false;" role="form" id="aboutusFrom">

								<input type="hidden" name="content" id="content">
								<input type="hidden" name="sysId" id="sysId" value="${CenturyInformation.sysId}">

							</form>
							<form class="form-horizontal" role="form" id="contentForm">
								<div class="form-group">
									<label class="col-sm-1 control-label no-padding-right"
										for="content">关于我们</label>
									<div class="col-sm-11">
										<textarea id="container">${CenturyInformation.content }</textarea>
									</div>
								</div>

							</form>
							<h3 class="header smaller lighter green"></h3>
							<div class="col-xs-11" align="right">
								<button class="btn btn-sm btn-primary" id="save">
									<i class="ace-icon fa fa-pencil align-top bigger-125"></i>提交修改
								</button>
								&nbsp;&nbsp;
								<button class="btn btn-sm btn-pink" id="reset">
									<i class="ace-icon fa fa-pencil align-top bigger-125"></i>重置
								</button>
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
	<script type="text/javascript">
		var ue = UE.getEditor('container', {
			initialFrameHeight : 300,
			autoHeightEnabled : false
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
 


 $("#save").click(function(){
	 $("#content").val(ue.getContent());
 	if($("#aboutusFrom").valid()) {
			postCallBack({
				data:serialize("#aboutusFrom"),
				url:'<s:url value="/modules/century/aboutus/edit"></s:url>',
				successMessage: "更新成功",
				successTitle: "消息对话框",
				failMessage: "更新失败",
				failTitle: "消息对话框"
			});		
 		} else {
 			showGritter("操作提示", "您当前未按照要求填写数据，请查看具体的表单提示信息");//消息提示
 		}
 	});

 $("#reset").click(function(){
 	//重置表单
 	
 	$('#aboutusFrom')[0].reset();
 	$('#contentForm')[0].reset();
 });
 
 
	</script>
</body>
</html>