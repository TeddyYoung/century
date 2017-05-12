<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- header -->
		<%@ include file="/WEB-INF/views/include/header.jsp"%>
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
								<div class="pull-right" style="margin-right: 20px;margin-bottom: 10px;">
									<!-- 权限按钮拦截部分 -->
										<button class="btn btn-sm btn-danger" id="renovate">刷新</button>
									<shiro:hasPermission name="modules:century:merchant:query">
										<button class="btn btn-sm btn-info" id="query">查询</button>
									 </shiro:hasPermission>
									<shiro:hasPermission name="modules:century:merchant:edit"> 
									 <button class="btn btn-sm btn-default" id="edit">修改</button> 
								 </shiro:hasPermission>
									<shiro:hasPermission name="modules:century:merchant:create"> 
										<button class="btn btn-sm btn-success" id="create">新增</button>
									</shiro:hasPermission>
								<shiro:hasPermission name="modules:century:merchant:delete">
										<button class="btn btn-sm btn-danger" id="delete">删除</button>
									</shiro:hasPermission> 
									<!-- 权限按钮拦截部分 -->
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
		
		<!-- footer -->
		<%@ include file="/WEB-INF/views/include/footer.jsp"%>
		
		<script type="text/javascript">
			//基本路径
			var baseUrl = "<s:url value="/modules/century/merchant"></s:url>/"
			
			//jqgrid的表格高度
			var height = 320;
			//jqgrid表格行头的中文
			var colNames = ['','商家名称','手机号码','商家简称','地址','电话','电话1','城市'];
			//jqgrid表格中每一行的信息模型address
			var colModel = [
				{name:'sysId', width:60, hidden: true,key:true},
				{name:'merchantName', width:60, editable: true},
				{name:'mobile', width:60, editable: false},
				{name:'shortName', width:60, editable: true},
				{name:'address', width:60, editable: true},
				{name:'telephone', width:60, editable: true},
				{name:'telephoneOne', width:60, editable: true},
				{name:'city', width:60, editable: true}
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
						var name = 'merchantEdit';
						window.parent.openapp(obj.dialogUrl, name, '修改商家信息');
                  }
				});
			});
			
			//执行更新操作
			function edit() {
				executeSaveOrUpdate({
					formId: "#formDialog form",
					dialogId: "#formDialog",
					url: getUrl("edit"),
					tableId: "#gridTable",
					successMessage: "更新成功",
					successTitle: '更新对话框',
					failMessage: '更新失败',
					failTitle: '消息对话框'
				});
			}
			
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
				loadDialog({
					dialogTitle: '手机验证',
					dialogUrl: getUrl("mobileQuery"),
					dialogFoot: '<button type="button" class="btn btn-primary" onclick="create()">下一步</button>' + 
					'<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>'
				});
			}); 
			
			//显示创建（标签）
	/* 		$("#create").click(function(){	
				var name = 'merchantCreate';				
				window.parent.openapp(getUrl("create"),name, '创建商户');			
				
			}); */
			//保存数据
			function create() {
				var flag = false;
				var mobile = $("#mobile").val();
				if(mobile != ""){
					$.ajax({
						   type: "post",
						   url: "<s:url value="/api/merchant/checkMobileMerchant"/>",
						   data: {mobile : mobile},
						   async: false,
						   success: function(data){
							   if(data == false) {
								   showGritter('消息提示', '该号码未注册或已经成为商家');//消息提示
							   }else{
								   flag = true;
							   }
						   }
						});
				}
				
				if(flag){
					var name = 'merchantCreate';
					window.parent.openapp(getUrl("create")+"?mobile="+mobile,name, '创建商户');		
				}
				
			}
			
			//执行刷新操作
			$("#renovate").click(function(){
				reload("#gridTable");
			});
		</script>
	</body>
</html>