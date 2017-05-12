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
								<div class="pull-right">	
									<shiro:hasPermission name="modules:cms:article:query">
										<button class="btn btn-sm btn-info" id="query">查询</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="modules:cms:article:edit">
										<button class="btn btn-sm btn-default" id="edit">修改</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="modules:cms:article:create">
										<button class="btn btn-sm btn-success" id="create">新增</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="modules:cms:article:delete">
										<button class="btn btn-sm btn-danger" id="delete">删除</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="modules:cms:article:excel">
										<button class="btn btn-sm btn-pink" id="export">导出</button>
									</shiro:hasPermission>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
									<!-- jqGrid表格 -->
									<table id="gridTable"></table>
									<!-- jqGrid工具类 -->
									<div id="gridPager"></div>
									<!-- 查询对话框 -->
									<div style="display: none;">
										<div class="row" id="queryDialog"></div>
									</div>
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
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
			//基本路径
			var baseUrl = "<s:url value="/modules/cms/article"></s:url>/";
			
			//jqgrid的表格高度
			var height = 320;
			//jqgrid表格行头的中文
			var colNames = ['','标题','分类','查看详情'];
			//jqgrid表格中每一行的信息模型
			var colModel = [
				{name:'sysId', width:60, hidden: true,key:true},
				{name:'title',index:'title', width:60, editable: true},
				{name:'category',index:'category', width:60, editable: true},
				{name:'detail',index:'detail', width:60, editable: true,	
				}
			];
			
			//jqgrid的具体执行方法
			jqgrid({
				gridTableId : "#gridTable",
				gridPagerId : "#gridPager"
			});
			
			//加载查询对话框
			$("#queryDialog").load(getUrl("filter"));
			
			//重置表单
			function reset() {
				resetForm("#formDialog form");
			}
			
			//显示查询对话框
			var queryDialog = $("#queryDialog");
			$("#query").click(function() {
				showDialog({
					dialogTitle: '查询对话框',
					dialogBody: queryDialog,
					dialogFoot: "<button type='button' class='btn btn-primary' onclick='query()'>查询</button>" +
					"<button type='button' class='btn btn-pink' onclick='reset()'>重置</button>" +
					"<button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>"
				});
			});
			
			//具体查询方法
			function query() {
				seachJqgird("#gridTable","#formDialog form");
				$("#formDialog").modal('hide');
			}
			
			//更新操作跳转页面
			$("#edit").click(function() {
				showEditDialog({
					tableId: "#gridTable",
					dialogTitle: "更新对话框",
					dialogUrl: getUrl("edit"),
					errorTitle: "消息对话框",
					errorMutiSelectMessage: '您当前选中多行，请重新选择',
					errorUnSelectMessage: '您当前尚未选中一行，请选择后再试',
					errorOk: '确定',
					loadDialog: function(obj) {
						var name = 'articleEdit';
						window.parent.openapp(obj.dialogUrl, name, '修改信息');
                  }
				});
			});
						
			//删除
			$("#delete").click(function() {
				showDeleteView();
			});
			
			//显示删除对话框
			function showDeleteView() {
				showDeleteDialog({
					url: getUrl("delete"),
					tableId: "#gridTable",
					confirmTitle: "确认对话框",
					confirmMessage: "是否确定删除?",
					confirmOk: "确定",
					confirmCancel: "取消",
					successMessage: "删除成功",
					successTitle: "消息对话框",
					failMessage: "删除失败",
					failTitle: "消息对话框",
					errorTitle: "警告提示框",
					errorMessage: "您当前尚未选中一行，请选择后再试",
					errorOk: "确定"
				});
			}
			
			//显示创建对话框
			$("#create").click(function(){
				var name = 'articleCreate';				
				window.parent.openapp(getUrl("create"),name, '创建文章');			
			});
			
			
			
		</script>
		
	</body>
</html>