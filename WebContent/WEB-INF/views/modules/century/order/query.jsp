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
									<shiro:hasPermission name="modules:century:order:query">
										<button class="btn btn-sm btn-info" id="query">查询</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="modules:century:order:edit">
										<button class="btn btn-sm btn-default" id="edit">支付</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="modules:century:order:create">
										<button class="btn btn-sm btn-success" id="create">新增</button>
									</shiro:hasPermission>
								<%-- 	<shiro:hasPermission name="modules:century:order:delete">
										<button class="btn btn-sm btn-danger" id="delete">删除</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="modules:century:orders:excel">
										<button class="btn btn-sm btn-pink" id="export">导出</button>
									</shiro:hasPermission> --%>
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
			var baseUrl = "<s:url value="/modules/century/order"></s:url>/"
			
			//jqgrid的表格高度
			var height = 320;
			//jqgrid表格行头的中文
			var colNames = ['','状态','客户Id','商家Id',/* '消费项目', */'消费金额','消费方式','备注'];
			//jqgrid表格中每一行的信息模型
			var colModel = [
				{name:'sysId', width:60, hidden: true,key:true},
				{name:'state',index:'state', width:60, editable: true,
					formatter : function(state) {
						if (state == 1) {
							return '下单';
						} else if (state == 2) {
							return '成功';
						} else if (state == 3) {
							return '失败';
						} else if (state == 4) {
							return '关闭';
						} else{
							return state;
						}
					}},
				{name:'userId',index:'userId', width:60, editable: true},
				{name:'shopId',index:'shopId', width:60, editable: true},
			/* 	{name:'content',index:'content', width:60, editable: true}, */
				{name:'balance',index:'balance', width:60, editable: true},
				{name:'consumeType',index:'consumeType', width:60, editable: true,
					formatter : function(consumeType) {
						if (consumeType == 1) {
							return '线下消费';
						} else if (consumeType == 2) {
							return '充值';
						} else{
							return consumeType;
						}
					}},
				{name:'demo',index:'demo', width:60, editable: true}
			/* 	{name:'detail',index:'detail', width:60, editable: true,
					formatter : function(value, grid, rows, state) {
					var q = "["+"<a href='#'  style='color:#f60' onclick=lookThis('"
						+ rows.sysId + "')>查看</a>"+"]"
						    +"["+"<a href='#'  style='color:#f60' onclick=editThis('"
							+ rows.sysId + "')>编辑</a>"+"]"+"["+"<a href='#'  style='color:#f60' onclick=deleteThis('"
							+ rows.sysId + "')>删除</a>"+"]"
					return q;
				}	
				} */
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
			
			//显示新建对话框
			$("#create").click(function(){
				loadDialog({
					dialogTitle: '创建订单对话框',
					dialogUrl: getUrl("create"),
					dialogFoot: '<button type="button" class="btn btn-primary" onclick="create()">添加</button>' + 
					'<button type="button" class="btn btn-pink" onclick="reset()">重置</button>' + 
					'<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>'
				});
			});
			
			
			//保存数据
			function create() {
				executeSaveOrUpdate({
					formId: "#formDialog form",
					dialogId: "#formDialog",
					url: getUrl("create"),
					tableId: "#categoryGridTable",
					successMessage: "保存成功",
					successTitle: '保存对话框',
					failMessage: '保存失败',
					failTitle: '消息对话框'
				});
			}
			//删除当前记录
			function deleteThis(data){
				var data=data;
	            deleteThisDialog({
	            	gridTable: "#gridTable",
					url: getUrl("delete"),
					tableId: data,
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
					dialogFoot: 
					'<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>',
					errorTitle: "消息对话框",
					errorMutiSelectMessage: '您当前选中多行，请重新选择',
					errorUnSelectMessage: '您当前尚未选中一行，请选择后再试',
					errorOk: '确定'
				});
			}	
		
			
			/* function deleteThisDialog(obj) {
				//避免空对象
				obj.failFunc = obj.failFunc == null ? function(){} : obj.failFunc;
				obj.successFunc = obj.successFunc == null ? function(){} : obj.successFunc;
				obj.before = obj.before == null ? function(){} : obj.before;
				obj.after = obj.after == null ? function(){} : obj.after;
				obj.errorFunc = obj.errorFunc == null ? function(){} : obj.errorFunc;
				obj.confirmFunc = obj.confirmFunc == null ? function(){} : obj.confirmFunc;
				
				var ids =obj.tableId;
					obj.before();//删除之前的事件
					obj.url = obj.url + ids;
					obj.confirmFunc = function(result) {
						if (result) {//确认按钮点击事件
							obj.reload = function() {
								reload(obj.gridTable);//重载
							}
							postCallBack(obj);
						}
					}
					showConfirmDialog(obj);
					obj.after();//删除之后的事件

			} */
		</script>
		
	</body>
</html>