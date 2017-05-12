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
								<div class="pull-right">
									<shiro:hasPermission name="modules:sys:bas:query">
										<button class="btn btn-sm btn-info" id="query">查询</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="modules:sys:bas:edit">
										<button class="btn btn-sm btn-default" id="edit">修改</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="modules:sys:bas:create">
										<button class="btn btn-sm btn-success" id="createCla">新增大类</button>
										<button class="btn btn-sm btn-warning" id="createBas">新增小类</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="modules:sys:bas:delete">
										<button class="btn btn-sm btn-danger" id="delete">删除</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="modules:sys:bas:excel">
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
		
		<!-- footer -->
		<%@ include file="/WEB-INF/views/include/footer.jsp"%>
		
		<script type="text/javascript">
			//基本路径
			var baseUrl = "<s:url value="/modules/sys/bas"></s:url>/"
			
			//jqgrid的表格高度
			var height = 320;
			//jqgrid表格行头的中文
			var colNames = ['','大类编号','小类编号', '名称', '修改权限','删除权限','排序','参数1','参数2','参数3','参数4','参数5','参数6'];
			//jqgrid表格中每一行的信息模型
			var colModel = [
				{name:'sysId', width:60, hidden: true,key:true},
				{name:'claId',index:'claId', width:60, editable: true},
				{name:'basId',index:'basId', width:60, editable: true},
				{name:'name',index:'name', width:60, editable: true},
				{name:'delflag',index:'delflag', width:60, editable: true,
					formatter:function(data){
						if(data) {
							return '是';
						}
						return '否';
					}
				},
				{name:'modflag',index:'modflag', width:60, editable: true,
					formatter:function(data){
						if(data) {
							return '是';
						}
						return '否';
					}	
				},
				{name:'orderBy',index:'orderBy', width:60, editable: true},
				{name:'attr1',index:'attr1', width:60, editable: true},
				{name:'attr2',index:'attr2', width:60, editable: true},
				{name:'attr3',index:'attr3', width:60, editable: true},
				{name:'attr4',index:'attr4', width:60, editable: true},
				{name:'attr5',index:'attr5', width:60, editable: true},
				{name:'attr6',index:'attr6', width:60, editable: true},
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
			
			//更新操作
			$("#edit").click(function() {
				showEditView();
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
			
			//显示编辑对话框
			function showEditView() {
				showEditDialog({
					tableId: "#gridTable",
					dialogTitle: "更新对话框",
					dialogUrl: getUrl("edit"),
					dialogFoot: '<button type="button" class="btn btn-primary" onclick="edit()">更新</button>' + 
					'<button type="button" class="btn btn-pink" onclick="reset()">重置</button>' + 
					'<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>',
					errorTitle: "消息对话框",
					errorMutiSelectMessage: '您当前选中多行，请重新选择',
					errorUnSelectMessage: '您当前尚未选中一行，请选择后再试',
					errorOk: '确定'
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
			
			//显示新建大类对话框
			$("#createCla").click(function(){
				loadDialog({
					dialogTitle: '创建对话框',
					dialogUrl: getUrl("createCla"),
					dialogFoot: '<button type="button" class="btn btn-primary" onclick="create()">添加</button>' + 
					'<button type="button" class="btn btn-pink" onclick="reset()">重置</button>' + 
					'<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>'
				});
			});
			
			//显示新建小类对话框
			$("#createBas").click(function(){
				showEditDialog({
					tableId: "#gridTable",
					dialogTitle: "创建对话框",
					dialogUrl: getUrl("createBas"),
					dialogFoot: '<button type="button" class="btn btn-primary" onclick="create()">添加</button>' + 
					'<button type="button" class="btn btn-pink" onclick="reset()">重置</button>' + 
					'<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>',
					errorTitle: "消息对话框",
					errorMutiSelectMessage: '您当前选中多行，请重新选择',
					errorUnSelectMessage: '您当前尚未选中一行，请选择后再试',
					errorOk: '确定'
				});
			});
			
			//保存数据
			function create() {
				executeSaveOrUpdate({
					formId: "#formDialog form",
					dialogId: "#formDialog",
					url: getUrl("create"),
					tableId: "#gridTable",
					successMessage: "保存成功",
					successTitle: '保存对话框',
					failMessage: '保存失败',
					failTitle: '消息对话框'
				});
			}
		</script>
		
	</body>
</html>