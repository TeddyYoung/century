<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	
<div class="col-xs-12">	
	<form:form commandName="sysMenuFilter" cssClass="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="menuId">程序代号</label>
			<div class="col-sm-3">
				<input type="text" name="menuId" id="menuId" placeholder="请输入程序代号" value="${sysMenuFilter.menuId }" class="form-control">
			</div>
			
			<label class="col-sm-2 control-label no-padding-right" for="menuName">程序名称</label>
			<div class="col-sm-3">
				<input type="text" name="menuName" id="menuName" placeholder="请输入程序名称" value="${sysMenuFilter.menuName }" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="menuRelation">程序层级</label>
			<div class="col-sm-3">
				<input id="menuRelation" name="menuRelation" placeholder="请选择关联层级"  value="${sysMenuFilter.menuRelation}" readonly="readonly" class="form-control" />
				<div id="menuRelationContent" class="col-xs-3 ztreeContent">
					<ul id="menuRelationTree" class="ztree ztreebox"></ul>
				</div>
			</div>
		</div>
	</form:form>
</div>

<script type="text/javascript">
<!--
	settingOneCheckTreeParam({
		inputObjKey : '#menuRelation',
		treeObjName : 'menuRelationTree',
		treeContentKey : '#menuRelationContent',
		url : '<s:url value="/api/sys/menu"></s:url>',
		sysId : '${sysMenu.sysId}'
	});
//-->
</script>