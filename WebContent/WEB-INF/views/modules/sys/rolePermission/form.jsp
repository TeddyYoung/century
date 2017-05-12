<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th class="center">
				<label class="position-relative">
					<input type="checkbox" class="ace" id="all"/>
					<span class="lbl"></span>
				</label>
			</th>
			<th>菜单名称</th>
			<th>功能集合</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="index" value="0"></c:set>
		<c:forEach items="${results}" var="sysMenu" varStatus="j">
			<tr>
				<td class="center">
					<label class="position-relative">
						<input type="checkbox" class="ace"/>
						<span class="lbl"></span>
					</label>
				</td>
				<td>
					${sysMenu.menuName}
				</td>
				<td>
					<c:forEach items="${sysMenu.funs}" var="fun">
						<label class="position-relative">
							<input type="checkbox" name="rolePermissions[${index}].func" class="ace" value="${fun.basId }"
								<c:if test="${fun.checked}">
									checked="checked"
								</c:if>
							> 
							<span class="lbl"></span> ${fun.funName }
						</label>
						<input type="hidden" name="rolePermissions[${index}].roleId" value="${roleId}">
						<input type="hidden" name="rolePermissions[${index}].deptId" value="${deptId}">
						<input type="hidden" name="rolePermissions[${index}].menuId" value="${sysMenu.menuId}">
						<input type="hidden" name="rolePermissions[${index}].permission" value="${sysMenu.permissionPre}:${fun.basId}">
						<input type="hidden" name="rolePermissions[${index}].permissionPre" value="${sysMenu.permissionPre}">
						
						<c:set var="index" value="${index+1 }"></c:set>
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script type="text/javascript">
	$("table tbody tr td:first-child .ace").click(function() {
		var checkboxs = $(this).parent().parent().parent().find(".ace");
		checkboxs.click();
	});
	
	$("#all").click(function() { 
		var checkboxs = $(this).parent().parent().parent().parent().parent().find("tr td:first-child .ace");
		checkboxs.click();
	});
</script>