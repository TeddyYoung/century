<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<form:form commandName="user" cssClass="form-horizontal" role="form">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">我的基本信息</h3>
		</div>
		<div class="panel-body">
			<div class="col-sm-9">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="name">中文名称</label>
					<div class="col-sm-4">
						<input type="text" name="name" id="name" placeholder="请输入中文名称" value="${user.name}" class="form-control required">
					</div>
					
					<label class="col-sm-2 control-label no-padding-right" for="nickName">用户昵称</label>
					<div class="col-sm-4">
						<input type="text" name="nickName" id="nickName" placeholder="请输入用户昵称" value="${user.nickName}" class="form-control required">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="userType">性别</label>
					<div class="col-sm-4">
						<form:select path="gender" items="${redis:getDictList('Gender',false)}" itemValue="basId" itemLabel="name" cssClass="form-control"></form:select>
					</div>
					
					<label class="col-sm-2 control-label no-padding-right" for="birthday">出生日期</label>
					<div class="col-sm-4">
						<div class="row">
							<div class="col-xs-8 col-sm-12">
								<div class="input-group">
									<input type="text" name="birthday" id="birthday"  class="form-control date-picker"  readonly value="${user.birthday}" />
									<span class="input-group-addon">
										<i class="fa fa-calendar bigger-110"></i>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right" for="nativeAddr">籍贯</label>
					<div class="col-sm-10">
						<input type="text" name="nativeAddr" id="nativeAddr" placeholder="籍贯" value="${user.nativeAddr}" class="form-control required">
					</div>
				</div>
			</div>
			
			<div class="col-sm-3">
				<span class="profile-picture">
					<img id="avatar" class="editable img-responsive" alt="Alex's Avatar" src="<s:url value="/api/sys/picture/downloadSmallPic/${user.image}"></s:url>" />
				</span>
			</div>
		</div>
	</div>
	
	<form:hidden path="sysId"/>
	<form:hidden path="image"/>
</form:form>
		<script type="text/javascript">
			jQuery(function($) {
				$('.date-picker').datepicker({
					inline: true,
					format:"yyyy-mm-dd",
					autoclose: true,
					todayHighlight: true,
					language: 'cn'
				})
				//show datepicker when clicking on the icon
				.next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
			});
		</script>