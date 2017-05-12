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
								<form class="form-horizontal" role="form" id="form">
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="title">标题</label>
										<div class="col-sm-3">
											<input type="text" name="title" id="title"
												placeholder="请输入标题" value="${article.title }"
												class="form-control required" >
										</div>
							
										<label class="col-sm-2 control-label no-padding-right"
											for="category">分类</label>
										<div class="col-sm-3">
											<input type="text" name="category" id="category"
												placeholder="请输入分类" value="${article.category }"
												class="form-control required" >
										</div>
									</div>
									
									<input type="hidden" name="content" id="content">
									<input type="hidden" name="sysId" value="${article.sysId}">
								</form>
								<form class="form-horizontal" role="form" >
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="content">文章内容</label>
										<div class="col-sm-8">
											<textarea id="container">${article.content }</textarea>
										</div>
									</div>
								</form>
								<button class="btn btn-sm btn-info" id="btn">查询</button>
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
	<link rel="stylesheet" href="<s:url value="/ueditor/themes/default/css/ueditor.css"></s:url>" />
	<script type="text/javascript"  src="<s:url value="/ueditor/ueditor.config.js"></s:url>"></script>
	<script type="text/javascript"  src="<s:url value="/ueditor/ueditor.all.min.js"></s:url>" ></script>
	<script type="text/javascript">
		var ue = UE.getEditor('container', {
			initialFrameHeight:300,
			autoHeightEnabled:false
		});
		
		$("#btn").click(function() {
			$("#content").val(ue.getContent());
			if($("#form").valid()) {
				postCallBack({
					data:serialize("#form"),
					url:'<s:url value="/modules/cms/article/create"></s:url>'
				});				
			}
		});
	</script>	
	</body>
</html>