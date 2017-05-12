<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<link rel="stylesheet" href="<s:url value="/assets/css/select2.css"></s:url>"/>
<div class="col-xs-12">
	<form:form commandName="centuryNewscastFilter" cssClass="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="title">标题</label>
			<div class="col-sm-3">
				<input type="text" name="title" id="title"
					placeholder="请输入标题" value="${centuryNewscastFilter.title }"
					class="form-control">
			</div>

		
		</div>
	</form:form>
</div>
<script type="text/javascript">

</script>