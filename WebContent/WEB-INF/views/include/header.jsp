<%@ page contentType="text/html;charset=UTF-8" language="java" %>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<meta name="description" content="世纪通管理系统">
		<meta name="author" content="世纪通">
		<title>世纪通管理系统</title>

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="<s:url value="/assets/css/bootstrap.css"></s:url>" />
		<link rel="stylesheet" href="<s:url value="/assets/css/font-awesome.css"></s:url>" />

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="<s:url value="/assets/css/jquery-ui.css"></s:url>" />
		
		<!-- page date-time -->
		<link rel="stylesheet" href="<s:url value="/assets/css/datepicker.css"></s:url>" />
		<link rel="stylesheet" href="<s:url value="/assets/css/bootstrap-timepicker.css"></s:url>" />
		
		<link rel="stylesheet" href="<s:url value="/assets/css/ui.jqgrid.css"></s:url>" />
		<link rel="stylesheet" href="<s:url value="/assets/css/jquery.gritter.css"></s:url>" />
		
		<!-- ztree树 -->
		<link rel="stylesheet" href="<s:url value="/assets/js/zTree_v3/css/zTreeStyle.css"></s:url>" />
		
		<!-- text fonts -->
		<link rel="stylesheet" href="<s:url value="/assets/css/ace-fonts.css"></s:url>" />

		<!-- ace styles -->
		<link rel="stylesheet" href="<s:url value="/assets/css/ace.css"></s:url>" class="ace-main-stylesheet" id="main-ace-style" />
		<link rel="stylesheet" href="<s:url value="/assets/css/sirdc/sirdc.tab.css"></s:url>" />
		
		<!-- select2组件 -->
		<link rel="stylesheet" href="<s:url value="/assets/css/select2.css"></s:url>"/>
		
		<!--[if lte IE 9]>
			<link rel="stylesheet" href="<s:url value="/assets/css/ace-part2.css"></s:url>" class="ace-main-stylesheet" />
		<![endif]-->

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="<s:url value="/assets/css/ace-ie.css"></s:url>" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="<s:url value="/assets/js/ace-extra.js"></s:url>"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
			<script src="<s:url value="/assets/js/html5shiv.js"></s:url>"></script>
			<script src="<s:url value="/assets/js/respond.js"></s:url>"></script>
		<![endif]-->

		<!-- basic scripts -->

		<!--[if !IE]> -->
			<script type="text/javascript">
				window.jQuery || document.write("<script src='<s:url value="/assets/js/jquery.js"></s:url>'>" + "<"+"/script>");
			</script>
		<!-- <![endif]-->

		<!--[if IE]>
			<script type="text/javascript">
				window.jQuery || document.write("<script src='<s:url value="/assets/js/jquery1x.js"></s:url>'>"+"<"+"/script>");
			</script>
		<![endif]-->

		<!-- ckfinder组件 -->
		<script type="text/javascript" src="<s:url value="/ckeditor/ckeditor.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/ckfinder/ckfinder.js"/>"></script>