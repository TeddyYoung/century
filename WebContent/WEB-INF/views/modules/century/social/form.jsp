<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<%@ include file="/WEB-INF/views/include/header.jsp"%>
		
		<style type="text/css">
			body {
				background-color: #F2F2F2;
			}
			
			.message {
				height: 500px;
				position: relative;
				overflow-y: auto;
				background-color: #E5E5EA;
			}
			
			.option {
				position: relative;
				bottom: 0;
				left: 0;
				width: 100%;
				height: 52px;
				background: #f2f2f2;
				padding-top: 8px;
				padding-bottom: 8px;
				border: 1px solid #cccccc;
			}
			
			.readMore {
				position: relative;
				top: 0;
				left: 40%;
				width: 20%;
				text-align:center;
				height: 30px;
				line-height: 30px;
				font-size: 20px;
				display: block;
				cursor: pointer;
			}
			
			.message-odd {
				margin-top: 10px;
			}
			
			.message-even {
				margin-top: 10px;
			}
			
			.message-even .message-icon {
				border-radius: 50%;
				opacity: 1;
				float: right;
				max-width: 50px;
				margin-top: 20px;
			}
			
			.message-odd .message-icon {
				border-radius: 50%;
				opacity: 1;
				float: left;
				max-width: 50px;
				margin-top: 20px;
			}
			
			.message-odd .message-title {
				color: #B8A18F;
				margin-top: 10px;
				text-align: left;
			}
			
			.message-even .message-title {
				color: #B8A18F;
				margin-top: 10px;
				text-align: right;
			}
			
			.message-content {
				border: 1px solid #FCFCFC;
				padding: 0.6em;
				background-color: #FCFCFC;
				border-radius: 4px;
				box-shadow: 0px 0px 5px #CCC;
				width: 100%;
			}
			
			.message-time {
				margin-left: 20px;
				color: #AEAB8B;
			}
			
			.message-odd .message-main {
				margin-left: 60px;
			}
			
			.message-even .message-main {
				margin-right: 60px;
			}
			
			.message-odd .message-content .arrow {
				width: 0px;
				height: 0px;
				line-height: 0;
				font-size: 0px;
				border-color: transparent #FFF transparent transparent;
				border-width: 6px;
				border-style: dashed solid dashed dashed;
				display: block;
				position: absolute;
				margin-left: -20px;
			}
			
			.message-even .message-content .arrow {
				
			}
			
			.message-side {
				margin: 10px 0;
			}
			
			.message-side img {
				line-height: 50px;	
				margin-top: 10px;
				margin-bottom: 10px;
			}
			
			.message-side div {
				border-top: 1px solid #E4EEF9;
				line-height: 50px;	
			}
			
			.no-padding {
				padding: 0;
			}
		</style>	
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
						<div class="row">
							<div class="col-xs-12">
								<div class="panel panel-default">
								 	<div class="panel-heading">与 &nbsp;${sendUser.nickName }  &nbsp;聊天</div>
								  	<div class="panel-body no-padding">
										<div class="col-xs-12 no-padding">
											<div class="col-sm-10 col-md-10 col-xs-12 no-padding">
												<div class="col-xs-12 readMore center" id="readMore">
													读取更多
												</div>
												<div class="col-xs-12 message" id="message">
													<div class="row message-odd">
														<img class="message-icon" src="http://sc.itcn.it/preview/67959/2014-10-17/demo/images/avatar-1.png"/>
														<div class="message-main">
															<div class="message-title">
																面瘫男的自我修养<span class="message-time">2014年10月12日 10:10:10</span>
															</div>
															<div class="message-content">
																<span class="arrow"></span>
																为什么小鑫的名字里有三个金呢为什么小鑫的名字里有三个金呢为什么小鑫的名字里有三个金呢为什么小鑫的名字里有三个金呢为什么小鑫的名字里有三个金呢为什么小鑫的名字里有三个金呢？ 
															</div>
														</div>
													</div>
													
													<div class="row message-even">
														<img class="message-icon" src="http://sc.itcn.it/preview/67959/2014-10-17/demo/images/avatar-1.png"/>
														<div class="message-main">
															<div class="message-title">
																面瘫男的自我修养<span class="message-time">2014年10月12日 10:10:10</span>
															</div>
															<div class="message-content">
																<span class="arrow"></span>
																为什么小鑫的名字里有三个金呢为什么小鑫的名字里有三个金呢为什么小鑫的名字里有三个金呢为什么小鑫的名字里有三个金呢为什么小鑫的名字里有三个金呢为什么小鑫的名字里有三个金呢？ 
															</div>
														</div>
													</div>
													
												</div>
												
												<div class="col-xs-12 option">
													<div class="col-xs-10">
														<input type="text" class="form-control" name="message" id="input" placeholder="请输入聊天内容，回车发送"/>									
													</div>
													<button type="button" id="sendBtn" class="btn btn-primary col-xs-2">发送</button>
												</div>
											</div>
											
											<div class="col-sm-2 col-md-2 hidden-xs no-padding message-side">
												<c:if test="${not empty sendUser.image}">
													<img src="${pageContext.request.contextPath}/api/sys/picture/downloadSmallPic/${sendUser.image }" alt="..." class="img-circle center-block">
												</c:if>
												<c:if test="${empty sendUser.image }">
													<img src="<s:url value="/assets/images/gallery/image-2.jpg"/>" alt="..." class="img-circle center-block col-xs-12">
												</c:if>
												<div class="col-xs-12">
													昵称：${sendUser.nickName }
												</div>
												<c:if test="${not empty sendUser.gender}">
													<div class="col-xs-12">
														<c:if test="${sendUser.gender == 'O' }">
															性别：保密
														</c:if>
														<c:if test="${sendUser.gender == 'F' }">
															性别：女
														</c:if>
														<c:if test="${sendUser.gender == 'M' }">
															性别：男
														</c:if>
													</div>
												</c:if>
												<c:if test="${not empty sendUser.birthday}">
													<div class="col-xs-12">
														生日：${sendUser.birthday }
													</div>
												</c:if>
												<c:if test="${not empty sendUser.nativeAddr}">
													<div class="col-xs-12">
														城市：${sendUser.nativeAddr }
													</div>
												</c:if>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
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
		
		<script type="text/javascript">
			var currentPage = 0;
			var totalPage = 0;
			$("#message").html("");//清除数据区域
			function loadData() {
				var data = "sendUserId=${sendUser.sysId}&paging.currentPage=" + (currentPage+1);
				$.ajax({
					type: 'POST',
				    url: "<s:url value="/modules/century/social/readMsgList"/>",
				    data: data ,
				    success: function(data) {
				    	var rows = data.rows;//数据集合
				    	if(rows != null) {
					    	currentPage = data.page;
					    	totalPage = data.total;
				    	}
				    	
				    	for(var i=0; i < rows.length; i++) {
				    		var html = $("#message").html();
				    		if(rows[i].msgType == 3) {
				    			if(rows[i].sendUserId == '${sendUser.sysId}') {
					    			var message = '<div class="row message-odd"><img class="message-icon" src="${pageContext.request.contextPath}/api/sys/picture/downloadSmallPic/${sendUser.image }"/><div class="message-main"><div class="message-title">${sendUser.nickName }<span class="message-time">' + rows[i].createDate + ' ' + rows[i].createTime + '</span></div><div class="message-content"><span class="arrow"></span>' + rows[i].content + '</div></div></div>';
					    			$("#message").html(message + html);
					    		} else {
					    			var message = '<div class="row message-even"><img class="message-icon"  src="${pageContext.request.contextPath}/api/sys/picture/downloadSmallPic/${recvUser.image }"/><div class="message-main"><div class="message-title">${recvUser.nickName }<span class="message-time">' + rows[i].createDate + ' ' + rows[i].createTime + '</span></div><div class="message-content"><span class="arrow"></span>' + rows[i].content + '</div></div></div>';
					    			$("#message").html(message + html);
					    		}
				    		} else if(rows[i].msgType == 5) {
				    			if(rows[i].sendUserId == '${sendUser.sysId}') {
					    			var message = '<div class="row message-odd"><img class="message-icon"  src="${pageContext.request.contextPath}/api/sys/picture/downloadSmallPic/${sendUser.image }"/><div class="message-main"><div class="message-title">${sendUser.nickName }<span class="message-time">' + rows[i].createDate + ' ' + rows[i].createTime + '</span></div><div class="message-content"><span class="arrow"></span><img src="${pageContext.request.contextPath}/api/sys/file/download/' + rows[i].content + '" style="max-width=150px;"/></div></div></div>';
					    			$("#message").html(message + html);
					    		} else {
					    			var message = '<div class="row message-even"><img class="message-icon" src="${pageContext.request.contextPath}/api/sys/picture/downloadSmallPic/${recvUser.image }"/><div class="message-main"><div class="message-title">${recvUser.nickName }<span class="message-time">' + rows[i].createDate + ' ' + rows[i].createTime + '</span></div><div class="message-content"><span class="arrow"></span><img src="${pageContext.request.contextPath}/api/sys/file/download/' + rows[i].content + '" style="max-width=150px;"/></div></div></div>';
					    			$("#message").html(message + html);
					    		}
				    		} else {
				    			if(rows[i].sendUserId == '${sendUser.sysId}') {
					    			var message = '<div class="row message-odd"><img class="message-icon"  src="${pageContext.request.contextPath}/api/sys/picture/downloadSmallPic/${sendUser.image }"/><div class="message-main"><div class="message-title">${sendUser.nickName }<span class="message-time">' + rows[i].createDate + ' ' + rows[i].createTime + '</span></div><div class="message-content"><span class="arrow"></span>文件：<a href="${pageContext.request.contextPath}/api/sys/file/download/' + rows[i].content + '">点击下载</a></div></div></div>';
					    			$("#message").html(message + html);
					    		} else {
					    			var message = '<div class="row message-even"><img class="message-icon" src="${pageContext.request.contextPath}/api/sys/picture/downloadSmallPic/${recvUser.image }"/><div class="message-main"><div class="message-title">${recvUser.nickName }<span class="message-time">' + rows[i].createDate + ' ' + rows[i].createTime + '</span></div><div class="message-content"><span class="arrow"></span>文件：<a href="${pageContext.request.contextPath}/api/sys/file/download/' + rows[i].content + '">点击下载</a></div></div></div>';
					    			$("#message").html(message + html);
					    		}
				    		}
				    	}
				    }
				});
			}
			
			//初始化
			loadData();
			
			$("#readMore").click(function() {
				loadData();//继续加载数据
			});
			
			//定时取未读消息
			setInterval(function() {
				var url = "${pageContext.request.contextPath}/modules/century/social/readMsgList/${sendUser.sysId}";
				$.ajax({
					type: 'POST',
				    url: url,
				    success: function(data) {
				    	if(data.code == 200) {
				    		var rows = data.data;
				    		for(var i=0; i < rows.length; i++) {
				    			if(rows[i].msgType == 3) {
				    				var message = '<div class="row message-odd"><img class="message-icon" src="${pageContext.request.contextPath}/api/sys/picture/downloadSmallPic/${sendUser.image }"/><div class="message-main"><div class="message-title">${sendUser.nickName }<span class="message-time">' + rows[i].createDate + ' ' + rows[i].createTime + '</span></div><div class="message-content"><span class="arrow"></span>' + rows[i].content + '</div></div></div>';
					    			$("#message").append(message);				    				
				    			} else if(rows[i].msgType == 5) {
				    				var message = '<div class="row message-odd"><img class="message-icon"  src="${pageContext.request.contextPath}/api/sys/picture/downloadSmallPic/${sendUser.image }"/><div class="message-main"><div class="message-title">${sendUser.nickName }<span class="message-time">' + rows[i].createDate + ' ' + rows[i].createTime + '</span></div><div class="message-content"><span class="arrow"></span><img src="${pageContext.request.contextPath}/api/sys/file/download/' + rows[i].content + '" style="max-width=150px;"/></div></div></div>';
					    			$("#message").append(message);
				    			} else {
				    				var message = '<div class="row message-odd"><img class="message-icon"  src="${pageContext.request.contextPath}/api/sys/picture/downloadSmallPic/${sendUser.image }"/><div class="message-main"><div class="message-title">${sendUser.nickName }<span class="message-time">' + rows[i].createDate + ' ' + rows[i].createTime + '</span></div><div class="message-content"><span class="arrow"></span>文件：<a href="${pageContext.request.contextPath}/api/sys/file/download/' + rows[i].content + '">点击下载</a></div></div></div>';
					    			$("#message").append(message);
				    			}
				    		}
				    	}
				    }
				});
			}, 3000);
			
			$("#sendBtn").click(function() {
				var url = "<s:url value="/modules/century/social/sendMessage"/>";
				var val = $("#input").val();//数据
				
				var data = "msgType=3";
				data += "&recvUserId=${sendUser.sysId}";
				data += "&content=" + val;
				$.ajax({
					type: 'POST',
				    url: url,
				    data: data,
				    success: function(data) {
				    	if(data.code == 200) {
				    		$("#input").val('');
				    		var message = '<div class="row message-even"><img src="${pageContext.request.contextPath}/api/sys/picture/downloadSmallPic/${recvUser.image }"/><div class="message-main"><div class="message-title">${recvUser.nickName }<span class="message-time">' + data.data.createDate + ' ' + data.data.createTime + '</span></div><div class="message-content"><span class="arrow"></span>' + data.data.content + '</div></div></div>';
				    		$("#message").append(message);
				    	}
				    }
				});
			});
		</script>
	</body>
</html>