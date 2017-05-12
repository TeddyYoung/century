<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<link rel="stylesheet" href="<s:url value="/assets/css/select2.css"></s:url>"/>
<div class="col-xs-12">
	<form:form commandName="articleFilter" cssClass="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="title">标题</label>
			<div class="col-sm-3">
				<input type="text" name="title" id="title"
					placeholder="请输入标题" value="${articleFilter.title }"
					class="form-control">
			</div>

			<label class="col-sm-2 control-label no-padding-right"
				for="category">分类</label>
			<div class="col-sm-3">
				<input type="text" name="category" id="category"
					placeholder="请输入分类" value="${articleFilter.category }"
					class="form-control">
			</div>
		</div>
	</form:form>
</div>
<script src="<s:url value="/assets/js/select2.js"></s:url>"></script>
<script src="<s:url value="/assets/js/sirdc/sirdc.select2.js"></s:url>"></script>
<script type="text/javascript">
createselect({
	textName : '#boosName',
	valueName :"#boosId",
	url :'<s:url value="/modules/api/sys/user/boosname"></s:url>',
	inputLength : 2,
    pageSize : 10
});
</script>