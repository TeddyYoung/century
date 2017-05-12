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
						<form action="#" method="post" id="form" onsubmit="return false;">
							<!-- /section:settings.box -->
							<div class="row">
								<div class="col-xs-12">
									<div class="pull-left col-xs-6">
										<select name="deptId" class="placeholder" style="width:45%" data-placeholder="请选择部门" id="deptId">
											<c:forEach items="${depts }" var="dept">
												<option value="${dept.sysId }">${dept.deptName }</option>
											</c:forEach>
										</select>
										<select name="roleId" class="placeholder" style="width:45%;" data-placeholder="请选择角色" id="roleId">
											<c:forEach items="${redis:getDictList('Position',false)}" var="position" varStatus="i">
												<option value="${position.basId }">${position.name }</option>
											</c:forEach>
										</select>
									</div>
									<div class="pull-right">
										<shiro:hasPermission name="modules:sys:userPermission:edit">
											<button class="btn btn-sm btn-info" id="delete">删除</button>
										</shiro:hasPermission>
										<shiro:hasPermission name="modules:sys:userPermission:edit">
											<button class="btn btn-sm btn-info" id="create">添加</button>
										</shiro:hasPermission>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12" id="menuContent">
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
							
							<div class="row">
								<div class="col-xs-12" id="menuContent">
									<!-- PAGE CONTENT BEGINS -->
										<!-- jqGrid表格 -->
										<table id="gridTable"></table>
										<!-- jqGrid工具类 -->
										<div id="gridPager"></div>
									<!-- PAGE CONTENT ENDS -->
								</div><!-- /.col -->
							</div><!-- /.row -->
						</form>
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
			
			<!-- gridDialog -->
			<!--  Modal content for the above example -->
			<div class="modal fade" id="gridDialog" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title" id="gridDialogTitle"></h4>
						</div>
						<div class="modal-body" id="gridDialogBody">
							<!-- PAGE CONTENT BEGINS -->
								<!-- jqGrid表格 -->
								<table id="addTables"></table>
								<!-- jqGrid工具类 -->
								<div id="addPagers"></div>
							<!-- PAGE CONTENT ENDS -->
						</div>
					</div><!-- /.modal-content -->
					<div class="modal-footer" id="gridDialogFooter">
			        	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			        	<button type="button" class="btn btn-primary">保存</button>
			      	</div>
				</div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
			
			<!-- dialog -->
			<%@ include file="/WEB-INF/views/include/dialog.jsp"%>
		</div><!-- /.main-container -->
		
		<!-- footer -->
		<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
	
	<script type="text/javascript">
		//基本路径
		var baseUrl = "<s:url value="/modules/sys/userPermission"></s:url>/"
		//jqgrid的表格高度
		var height = 320;
		//jqgrid表格行头的中文
		var colNames = ['','员工编号','中文名称','性别'];
		
		//jqgrid表格中每一行的信息模型
		var colModel = [
			{name:'sysId', width:60, hidden: true,key:true},
			{name:'sysId',index:'sysId', width:60, editable: true},
			{name:'name',index:'name', width:60, editable: true},
			{name:'gender',index:'gender', width:60, editable: true,
				formatter:function(data){
					var array = ${redis:getDictListJson('Gender', false)};
					return getName(array, 'basId', data, 'name');
				}
			}
		];
		
		//jqgrid的具体执行方法
		jqgrid({
			gridTableId : "#gridTable",
			gridPagerId : "#gridPager",
			postData : {
				roleId : $("#roleId").val(),
				deptId : $("#deptId").val()
			}
		});
		
		//jqgrid的具体执行方法
		jqgrid({
			gridTableId : "#addTables",
			gridPagerId : "#addPagers",
			postData : {
				roleId : $("#roleId").val(),
				deptId : $("#deptId").val()
			},
			url : '<s:url value="/modules/sys/userPermission/create"></s:url>'
		});
		
		var deptId = "";
		var roleId = "";
		
		$("#deptId").change(function() {
			roleId = $("#roleId").val();
			deptId = $("#deptId").val();
			loadPage(deptId, roleId);
		});
		
		$("#roleId").change(function() {
			roleId = $("#roleId").val();
			deptId = $("#deptId").val();
			loadPage(deptId, roleId);
		});
		
		function loadPage(deptId, roleId) {
			//具体查询方法
			seachJqgirdData("#gridTable", {
				roleId : roleId,
				deptId : deptId
			});
		}
		
		$(".placeholder").select2({
			allowClear: true
		});
		
		//删除
		$("#delete").click(function() {
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
		});
		
		$("#save").click(function() {
			//保存数据
			var data = serialize("#form")
			postCallBack({
				url: '<s:url value="/modules/sys/userPermission/edit"></s:url>',
				data: data,
				successMessage: "更新成功",
				successTitle: '更新对话框',
				failMessage: '更新失败',
				failTitle: '消息对话框'
			});
		});
		
		//显示创建对话框
		$("#create").click(function(){
			$("#gridDialogTitle").text('添加用户权限');
			$("#gridDialogFooter").empty();
			$("#gridDialogFooter").append(
					 '<button type="button" class="btn btn-primary" onclick="save()">添加</button>' 
					+ '<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>');
			$('#gridDialog').modal('show');
			seachJqgirdData("#addTables", {
				roleId : $("#roleId").val(),
				deptId : $("#deptId").val()
			});
			$('#addTables').jqGrid('setGridWidth', $('.modal-dialog').width()-30);// 设置随屏幕位置变化
		});
		
		//保存方法
		function save() {
			var ids = getSelectIds('#addTables');
			if(ids.length > 0) {
				var data = {
					roleId : $("#roleId").val(),
					deptId : $("#deptId").val(),
					userIds : ids
				};
				postCallBack({
					data : data,
					reload : function() {
						seachJqgirdData("#addTables", {
							roleId : $("#roleId").val(),
							deptId : $("#deptId").val()
						});
						seachJqgirdData("#gridTable", {
							roleId : $("#roleId").val(),
							deptId : $("#deptId").val()
						});
					},url : '<s:url value="/modules/sys/userPermission/savePermission"></s:url>',
					successTitle : '消息提示',
					successMessage : '保存成功',
					failTitle : '消息提示',
					message : '保存失败',
					successFunc : function(data) {
						seachJqgirdData("#gridTable", {
							roleId : $("#roleId").val(),
							deptId : $("#deptId").val()
						});
					}
				});
			} else {
				showGritter('消息提示', '您当前尚未选中一行，请选择后再试');//消息提示
			}
		}
		
		$(window).resize(function(){
			$('#addTables').jqGrid('setGridWidth', $('.modal-dialog').width()-30);// 设置随屏幕位置变化
		});
	</script>
</html>