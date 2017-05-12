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
									<shiro:hasPermission name="modules:sys:log:query">
										<button class="btn btn-sm btn-info" id="query">查询</button>
									</shiro:hasPermission>
									
									<shiro:hasPermission name="modules:sys:log:excel">
										<button class="btn btn-sm btn-pink" id="export">导出</button>
									</shiro:hasPermission>
									
									<shiro:hasPermission name="modules:sys:log:query">
										<button class="btn btn-sm btn-info" id="chart">报表</button>
									</shiro:hasPermission>
									
									<shiro:hasPermission name="modules:sys:log:query">
										<button class="btn btn-sm btn-info" id="print">打印</button>
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
		<script src="<s:url value="/assets/js/jquery.printFinal.js"></s:url>"></script>
		
		<script type="text/javascript">
			//基本路径
			var baseUrl = "<s:url value="/modules/sys/log"></s:url>/"
			
			//jqgrid的表格高度
			var height = 320;
			//jqgrid表格行头的中文
			var colNames = ['日期','POST数', 'GET数'];
			//jqgrid表格中每一行的信息模型
			var colModel = [
				{name:'createDate',index:'createDate', width:60, editable: true},
				{name:'postCount',index:'postCount', width:60, editable: true},
				{name:'getCount',index:'getCount', width:60, editable: true}
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
			
			$("#chart").click(function() {
				var url='<s:url value="/modules/sys/log/chart"></s:url>';
			    window.parent.openapp(url,'sysLogChart','日志报表');
			});
			
			$("#print").click(function() {
				showDialog({
					dialogTitle: '打印预览',
					dialogBody: $("#gridTable").html(),
					dialogFoot: "<button type='button' class='btn btn-pink' onclick='print()'>打印</button>" +
					"<button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>"
				});
			});
			
			function print() {
				$("#gridTable").printFinal({
					preview : false,//打印预览
					impcss : true//引入css文件
				});
			}
		</script>
	</body>
</html>