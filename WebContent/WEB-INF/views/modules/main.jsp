<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- header -->
		<%@ include file="/WEB-INF/views/include/header.jsp"%>
		<link rel="stylesheet" href="<s:url value="/ueditor/themes/default/css/ueditor.css"></s:url>" />
		<script type="text/javascript"  src="<s:url value="/ueditor/ueditor.config.js"></s:url>"></script>
    	<script type="text/javascript"  src="<s:url value="/ueditor/ueditor.all.min.js"></s:url>" ></script>
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
						<div class="page-header">
							<h1>
								系统首页
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									世纪通后台首页
								</small>
							</h1>
						</div>
						<!--<div class="row">
							<div class="col-xs-12">
								<h1>技术选型</h1>
								<p>
									<h2>1、后端</h2>
									<h5>核心框架：Spring Framework 4.0</h5>
	    							<h5>安全框架：Apache Shiro 1.2</h5>
	    							<h5>视图框架：Spring MVC 4.0</h5>
	    							<h5>服务端验证：Hibernate Validator 5.1</h5>
	    							<h5>任务调度：Spring Task 4.0</h5>
	    							<h5>持久层框架：Hibernate 4.0</h5>
	    							<h5>数据库连接池：Alibaba Druid 1.0</h5>
	    							<h5>缓存框架：Ehcache 2.6、Redis</h5>
	    							<h5>日志管理：SLF4J 1.7、Log4j</h5>
	    							<h5>工具类：Apache Commons、Jackson 2.2、Xstream 1.4、Dozer 5.3、POI 3.9</h5>
	    						</p>
								<p>
									<h2>2、前端</h2>
								    <h5>JS框架：jQuery 1.9。</h5>
								    <h5>CSS框架：Twitter Bootstrap 2.3.1。</h5>
								    <h5>客户端验证：JQuery Validation Plugin 1.11。</h5>
								    <h5>富文本：CKEcitor</h5>
								    <h5>文件管理：CKFinder</h5>
								    <h5>数据表格：jqGrid</h5>
									<h5>下拉选择框：jQuery Select2</h5>
								    <h5>树结构控件：jQuery zTree</h5>
								</p>
								<p>
									<h2>3、平台</h2>
    								<h5>服务器中间件：在Java EE 5规范（Servlet 2.5、JSP 2.1）下开发，支持应用服务器中间件 有Tomcat 6、Jboss 7、WebLogic 10、WebSphere 8。</h5>
    								<h5>数据库支持：目前仅提供MySql数据库的支持，但不限于数据库，平台留有其它数据库支持接口， 可方便更改为其它数据库，如：SqlServer 2008、Oracle、H2等</h5>
    								<h5>开发环境：Java EE、Eclipse、Maven、Git</h5>								
								</p>
							</div>
							<div class="col-xs-12">
								<textarea name="container" id="container" rows="40">这里写你的初始化内容</textarea>
								<script type="text/javascript">
									var ue = UE.getEditor('container', {
										initialFrameHeight:300,
										autoHeightEnabled:false
									});
								</script>
								
								<div class="col-xs-12">
									<input type="text" id="picture" name="cover" />
									<button onclick="upImage();">上传图片</button>
									<input type="text" id="file" name="cover" />
									<button onclick="upFiles();">上传图片</button>
								</div>
								<img id="preview" width="400" height="400" src="images/nopic.gif">
								<script type="text/plain" id="upload_ue"></script>
								<script type="text/javascript">
									//上传独立使用
							        var _editor = UE.getEditor('upload_ue');
							        _editor.ready(function () {
							            _editor.setDisabled();
							            _editor.hide();
							            _editor.addListener('beforeInsertImage', function (t, arg) {     //侦听图片上传
							            	alert(JSON.stringify(arg[0]));
							            	//将地址赋值给相应的input
							                $("#picture").attr("value", arg[0].src);
							                //图片预览
							                $("#preview").attr("src", arg[0].src);
							            })
							            _editor.addListener('afterUpfile', function (t, arg) {
							                $("#file").attr("value", arg[0].url);
							            })
							        });
							        function upImage() {
							            var myImage = _editor.getDialog("insertimage");
							            myImage.open();
							        }
							        function upFiles() {
							            var myFiles = _editor.getDialog("attachment");
							            myFiles.open();
							        }
								</script>
							</div>
						</div> -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
			
			<!-- dialog -->
			<%@ include file="/WEB-INF/views/include/dialog.jsp"%>
		</div><!-- /.main-container -->
	</div>	
		<!-- footer -->
		<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	</body>
</html>