<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- header -->
		<%@ include file="/WEB-INF/views/include/header.jsp"%>
	</head>
	<body class="login-layout blur-login">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="ace-icon fa fa-leaf green"></i>
									<span class="red">世纪通</span>
									<span class="white" id="id-text2"></span>
								</h1>
								<h4 class="blue" id="id-company-text"></h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												<c:if test="${empty error }">
													请输入你的登陆信息
												</c:if>
												<c:if test="${!empty error }">
													${error }
												</c:if>
											</h4>

											<div class="space-6"></div>

											<form action="<s:url value="/login"></s:url>" id="form" method="post">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="username" class="form-control" placeholder="邮箱/员工编号/手机号" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" name="password" class="form-control" placeholder="请输入密码" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>
													
													<label class="block clearfix">
														<span class="block input-icon input-icon-right" id="validDiv">
															<img src="" id="validImage">
														</span>
													</label>
													
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="validateCode" id="validateCode" class="form-control" placeholder="请输入验证码" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" name="rememberMe" class="ace" />
															<span class="lbl"> 下次自动登录</span>
														</label>

														<button type="button" id="loginBtn" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="ace-icon fa fa-key"></i>
															<span class="bigger-110">登陆</span>
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

										</div><!-- /.widget-main -->

										<div class="toolbar clearfix">
											<div><!-- 占位符 --></div>
											<div>
												<a href="#" data-target="#forgot-box" class="user-signup-link">
													忘记密码
													<i class="ace-icon fa fa-arrow-right"></i>
												</a>
											</div>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->
							</div><!-- /.position-relative -->
								
								
							<div id="forgot-box" class="forgot-box widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header red lighter bigger">
											<i class="ace-icon fa fa-key"></i>
											找回密码
										</h4>

										<div class="space-6"></div>
										<p id="email_msg">
											通过登陆邮箱找回密码
										</p>

										<fieldset>
											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="email" id="email" class="form-control" placeholder="请输入登陆邮箱" />
													<i class="ace-icon fa fa-envelope"></i>
												</span>
											</label>

											<div class="clearfix">
												<button type="button" id="emailBtn" class="width-100 pull-right btn btn-sm btn-danger">
													<i class="ace-icon fa fa-lightbulb-o"></i>
													<span class="bigger-110">发送验证邮箱</span>
												</button>
											</div>
										</fieldset>
									</div><!-- /.widget-main -->

									<div class="toolbar center">
										<a href="#" data-target="#login-box" class="back-to-login-link">
											返回登陆
											<i class="ace-icon fa fa-arrow-right"></i>
										</a>
									</div>
								</div><!-- /.widget-body -->
							</div><!-- /.forgot-box -->
						
							
							<div class="navbar-fixed-top align-right">
								<br />
								<a>主题切换：</a>
								&nbsp;
								<a id="btn-login-dark" href="#">黑色</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-blur" href="#">蓝色</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-light" href="#">白色</a>
								&nbsp; &nbsp; &nbsp;
							</div>
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='<s:url value="/assets/js/jquery.js"></s:url>'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
			window.jQuery || document.write("<script src='<s:url value="/assets/js/jquery1x.js"></s:url>'>"+"<"+"/script>");
		</script>
		<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='<s:url value="/assets/js/jquery.mobile.custom.js"></s:url>'>"+"<"+"/script>");
		</script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			
			jQuery(function($) {
				$(document).on('click', '.toolbar a[data-target]', function(e) {
					e.preventDefault();
					var target = $(this).data('target');
					$('.widget-box.visible').removeClass('visible');//hide others
					$(target).addClass('visible');//show target
				});
			});

			//you don't need this, just used for changing background
			jQuery(function($) {
				$('#btn-login-dark').on('click', function(e) {
					$('body').attr('class', 'login-layout');
					$('#id-text2').attr('class', 'white');
					$('#id-company-text').attr('class', 'blue');

					e.preventDefault();
				});
				$('#btn-login-light').on('click', function(e) {
					$('body').attr('class', 'login-layout light-login');
					$('#id-text2').attr('class', 'grey');
					$('#id-company-text').attr('class', 'blue');

					e.preventDefault();
				});
				$('#btn-login-blur').on('click', function(e) {
					$('body').attr('class', 'login-layout blur-login');
					$('#id-text2').attr('class', 'white');
					$('#id-company-text').attr('class', 'light-blue');

					e.preventDefault();
				});

			});
			
			$("#validImage").click(function() {
				getImage();
			});
			
			$(window).resize(function(){
				getImage();
			});
			
			$("#loginBtn").click(function(){
				$("#form").submit();
			});
			
			getImage();
			
			function getImage() {
				var date = new Date().getMilliseconds();
				var validImageWidth = $("#validDiv").width();
				$("#validImage").attr("src", '<s:url value="/validateCodeServlet"></s:url>?height=40&width=' + validImageWidth + '&' + date);
			}
			
			if (this.parent != this) {
				var parent = this.parent;
				if(parent.parent) {
					parent = parent.parent;
				}
				parent.location.reload();
			}
			
			$("#validateCode").bind('keyup', function(event) {
				var keyCode = event.keyCode;
				if(keyCode == 13) {
					$("#form").submit();
				}
			});
			
			$("#emailBtn").click(function() {
				var email = $("#email").val();
				var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
				if(reg.test(email)) {
					$.post("<s:url value="/api/sys/user/searchPassword"/>", { email: email},
					function(data){
				    	if(data.code == "200") {
							$("#email_msg").text('重置密码的邮件已经发到您的邮箱“'+email+'”中，请查收');
				    	} else {
				    		$("#email_msg").text(data.message);
				    	}
				   	});
				} else {
					$("#email_msg").text('请输入正确的邮箱格式');
				}
			});
		</script>
	</body>
</html>