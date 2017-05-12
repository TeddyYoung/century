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
									<shiro:hasPermission name="modules:sys:msg:edit">
										<button class="btn btn-sm btn-info" id="saveMore">保存维护数据</button>
									</shiro:hasPermission>
									<button class="btn btn-sm btn-danger" id="delete">删除</button>
									<button class="btn btn-sm btn-default" id="rest">重置</button>
									<button class="btn btn-sm btn-pink" id="export">导出</button>
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
		<script src="<s:url value="/assets/js/jqGrid/chingluh-utils.js"></s:url>"></script>
		<script src="<s:url value="/assets/js/sirdc/sirdc.jqgridmore.js"></s:url>"></script>
		<script type="text/javascript">
			//基本路径
			var baseUrl = "<s:url value="/modules/sys/msg"></s:url>/"
			var rowNum ;
			//jqgrid的表格高度
			var height = 320;
			//jqgrid表格行头的中文
			var colNames = ['','简体名称','英文名称', '繁体名称', '类别','备注','状态','',''];
			//jqgrid表格中每一行的信息模型
			var colModel = [
				{name:'sysId', width:60, hidden: true,key:true},
				{name:'chsName',index:'chsName', width:60, editable: true},
				{name:'enName',index:'enName', width:60, editable: true},
				{name:'chtName',index:'chtName', width:60, editable: true},
				{name:'msgType',index:'msgType', width:60, editable: true},
				{name:'demo',index:'demo', width:60, editable: true},
				{name:'state',index:'state', width:60, editable: true},
				{name:'addFlag', index:'addFlag', editable:true, width:10,hidden:true,sortable:false},
				{name:'updateFlag', index:'updateFlag', editable:true, width:10,hidden:true,sortable:false}
			];
			
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
			/**
			 * 自定义参数
			 */
				var colNames = ['','信号ID','简体名称','英文名称', '繁体名称', '类别','备注','状态',''];
				//jqgrid表格中每一行的信息模型
				var colModel = [
					{name:'sysId', width:60, hidden: true,key:true},
					{name:'msgId',index:'msgId', width:60, editable: true},
					{name:'chsName',index:'chsName', width:60, editable: true},
					{name:'enName',index:'enName', width:60, editable: true},
					{name:'chtName',index:'chtName', width:60, editable: true},
					{name:'msgType',index:'msgType', width:60, editable: true},
					{name:'demo',index:'demo', width:60, editable: true},
					{name:'state',index:'state', width:60, editable: true},
					{name:'flag',index:'flag',width:10,editable:true,hidden:true}//添加标记位
				];
				
				//jqgrid的具体执行方法
			var lastrow,lastcell;//最后点击的行
			var intid={chsName:"111",msgType:"2222"};//默认值
			var listData = eval('${sysMsgJson}');//原始数据
			var deleList=new Array();//删除记录列表
			var url= '<s:url value="/modules/sys/msg/editMore"></s:url>'; //修改路径
			jqgridMore({
				gridTableId : "#gridTable",
				gridPagerId : "#gridPager",
				intid:intid,
			    
			});
			//保存编辑多笔
			$("#saveMore").click(function() {
				saveDatList("#gridTable",lastrow,lastcell,deleList,url);
				$("#gridTable").jqGrid("clearGridData");
				deleList=new Array();
			});
			//删除
			$("#delete").click(function() {
				deleList=deleteListFun("#gridTable",deleList)
			});
			//重置
			$("#rest").click(function() {
				$("#gridTable").jqGrid("clearGridData");
				var intidd=intidData(colModel,intid);
				deleList=new Array();
				initListFun("#gridTable",listData,intidd);
			});
		</script>
		
	</body>
</html>