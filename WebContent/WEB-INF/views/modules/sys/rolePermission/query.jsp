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
										<select name="deptId" class="placeholder select2" style="width:45%" data-placeholder="请选择部门" id="deptId">
											<option></option>
											<c:forEach items="${depts }" var="dept">
												<option value="${dept.sysId }">${dept.deptName }</option>
											</c:forEach>
										</select>
										<select name="roleId" class="placeholder select2" style="width:45%" data-placeholder="请选择角色" id="roleId">
											<option></option>
											<c:forEach items="${redis:getDictList('Position',false)}" var="position" varStatus="i">
												<option value="${position.basId }">${position.name }</option>
											</c:forEach>
										</select>
									</div>
									<div class="pull-left">
									<!-- 权限判断 -->
									<shiro:hasPermission name="modules:sys:rolePermission:edit">
										<button class="btn btn-sm btn-info" id="save">保存</button>
									</shiro:hasPermission>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12" id="menuContent">
									<!-- PAGE CONTENT BEGINS -->
										请选择相应的部门和职位,然后进行操作
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
			
			<!-- dialog -->
			<%@ include file="/WEB-INF/views/include/dialog.jsp"%>
		</div><!-- /.main-container -->
		
		<!-- footer -->
		<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
	
	<script type="text/javascript">
		var deptId = "";
		var roleId = "";
		
		$("#deptId").change(function() {
			deptId = $("#deptId").val();
			loadPage(deptId, roleId);
		});
		
		$("#roleId").change(function() {
			roleId = $("#roleId").val();
			loadPage(deptId, roleId);
		});
		
		function loadPage(deptId, roleId) {
			if(isNotBlank(deptId) && isNotBlank(roleId)) {
				var url = "<s:url value="/modules/sys/rolePermission/edit"></s:url>";
				url += "?deptId=" + deptId + "&roleId=" + roleId;
				$("#menuContent").load(url);
			} else {
				$("#menuContent").text("请选择相应的部门和职位,然后进行操作");
			}
		}
		
		$(".placeholder").select2({
			allowClear: true
		});
		
		$("#save").click(function() {
			//保存数据
			var data = serialize("#form")
			postCallBack({
				url: '<s:url value="/modules/sys/rolePermission/edit"></s:url>',
				data: data,
				successMessage: "更新成功",
				successTitle: '更新对话框',
				failMessage: '更新失败',
				failTitle: '消息对话框'
			});
		});
	</script>
</html>