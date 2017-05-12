<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
		
<form:form commandName="sysMenu" cssClass="form-horizontal" role="form">
	<div class="form-group">
		<label class="col-sm-1 control-label no-padding-right" for="menuId">程序代号</label>
		<div class="col-sm-3">
			<input type="text" name="menuId" id="menuId" placeholder="请输入程序代号" value="${sysMenu.menuId}" class="form-control required">
		</div>
	
		<label class="col-sm-1 control-label no-padding-right" for="menuEnname">英文名称</label>
		<div class="col-sm-3">
			<input type="text" name="menuEnname" id="menuEnname" placeholder="请输入英文名称" value="${sysMenu.menuEnname}" class="form-control required">
		</div>
		
		<label class="col-sm-1 control-label no-padding-right" for="menuName">程序名称</label>
		<div class="col-sm-3">
			<input type="text" name="menuName" id="menuName" placeholder="请输入程序名称" value="${sysMenu.menuName}" class="form-control required">
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-1 control-label no-padding-right" for="menuRelation">关联层级</label>
		<div class="col-sm-3">
			<input id="menuRelationForm" name="menuRelation" placeholder="请选择关联层级"  value="${sysMenu.menuRelation}" readonly="readonly" class="form-control" />
			<div id="menuRelationFormContent" class="col-xs-3 ztreeContent">
				<ul id="menuRelationFormTree" class="ztree ztreebox"></ul>
			</div>
		</div>
	
		<label class="col-sm-1 control-label no-padding-right" for=orderBy>排序</label>
		<div class="col-sm-3">
			<input type="number" name="orderBy" id="orderBy" placeholder="请输入排序" value="${sysMenu.orderBy}" class="form-control required digits">
		</div>
		<label class="col-sm-1 control-label no-padding-right" for="state">状态</label>
		<div class="col-sm-3">
			<form:select path="state" items="${redis:getDictList('MenuState',false)}" itemLabel="name" itemValue="basId" cssClass="form-control required"></form:select>
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-1 control-label no-padding-right" for="url">URL</label>
		<div class="col-sm-5">
			<input type="text" name="url" id="url" placeholder="请输入URL" value="${sysMenu.url}" class="form-control required">
		</div>
		
		<label class="col-sm-1 control-label no-padding-right" for="msgId">多语系ID</label>
		<div class="col-sm-5">
			<input type="text" name="msgId" id="msgId" placeholder="请输入msgId" value="${sysMenu.msgId}" class="form-control required">
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-1 control-label no-padding-right" for="menuName">程序名称</label>
		<div class="col-sm-5">
			<input type="text" name="permissionPre" id="permissionPre" placeholder="请输入权限前缀字符" value="${sysMenu.permissionPre}" class="form-control required">
		</div>

		<label class="col-sm-1 control-label no-padding-right" for="icon">程序图标</label>
		<div class="col-sm-5">
			<form:select path="icon" items="${redis:getDictList('MenuIcon',false)}" itemLabel="name" itemValue="basId" cssClass="form-control required"></form:select>
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-1 control-label no-padding-right">功能集合</label>
	</div>
	<c:forEach items="${redis:getDictList('FUN',false)}" var="fun" varStatus="i">
		<c:if test="${i.index % 6 == 0}">
			<div class="form-group">
		</c:if>
				<div class="checkbox col-sm-2">
					<label>
						<input name="funs" type="checkbox" class="ace" value="${fun.basId }"
							<c:forEach items="${fn:split(sysMenu.funs,',') }" var="function">
								<c:if test="${function == fun.basId}">
									checked="checked"
								</c:if>
							</c:forEach>
						/>
						<span class="lbl">${fun.name }</span>
					</label>
				</div>
		<c:if test="${i.index % 6 == 5}">
			</div>
		</c:if>
	</c:forEach>
	<form:hidden path="sysId"/> 
</form:form>

<script type="text/javascript">
$('#editor1').ace_wysiwyg({
	toolbar:
	[
		'font',
		null,
		'fontSize',
		null,
		{name:'bold', className:'btn-info'},
		{name:'italic', className:'btn-info'},
		{name:'strikethrough', className:'btn-info'},
		{name:'underline', className:'btn-info'},
		null,
		{name:'createLink', className:'btn-pink'},
		{name:'unlink', className:'btn-pink'},
		null,
		{name:'insertImage', className:'btn-success'},
		null,
		'foreColor',
		null,
		{name:'undo', className:'btn-grey'},
		{name:'redo', className:'btn-grey'}
	]
});
settingOneCheckTreeParam({
		inputObjKey : '#menuRelationForm',
		treeObjName : 'menuRelationFormTree',
		treeContentKey : '#menuRelationFormContent',
	url : '<s:url value="/api/sys/menu"></s:url>',
	sysId : '${sysMenu.sysId}'
});
</script>
