<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<link rel="stylesheet" href="<s:url value="/assets/css/select2.css"></s:url>"/>
<div class="col-xs-12">
	<form:form commandName="centuryActivityFilter" cssClass="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="title">活动标题</label>
			<div class="col-sm-3">
				<input type="text" name="title" id="title"
					placeholder="请输入活动标题" value="${centuryActivityFilter.title }"
					class="form-control required" >
			</div>

			<label class="col-sm-2 control-label no-padding-right"
				for="state">活动状态</label>
			<div class="col-sm-3">
				<input type="text" name="state" id="state"
					placeholder="请输入活动状态" value="${centuryActivityFilter.state }"
					class="form-control required" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="pictureId">活动图ID</label>
			<div class="col-sm-3">
				<input type="text" name="pictureId" id="pictureId"
					placeholder="请输入活动图ID" value="${centuryActivityFilter.pictureId }"
					class="form-control required" >
			</div>

<%-- 			<label class="col-sm-2 control-label no-padding-right"
				for="activityCast">活动经费</label>
			<div class="col-sm-3">
				<input type="number" name="activityCast" id="activityCast"
					placeholder="请输入活动经费" value="${centuryActivityFilter.activityCast }"
					class="form-control required" >
			</div> --%>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right" for="reward">活动奖励</label>
			<div class="col-sm-3">
				<input type="text" name="reward" id="reward"
					placeholder="请输入活动奖励" value="${centuryActivityFilter.reward }"
					class="form-control required" >
			</div>

			<label class="col-sm-2 control-label no-padding-right"
				for="orderBy">排序</label>
			<div class="col-sm-3">
				<input type="number" name="orderBy" id="orderBy"
					placeholder="请输入排序" value="${centuryActivityFilter.orderBy }"
					class="form-control required" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right"
				for="startDate">活动开始日期</label>
			<div class="col-sm-3">
				<div class="input-group">
					<input type="text" name="startDate" id="startDateFilter"  class="form-control date-picker"   value="${centuryActivityFilter.startDate}" />
					<span class="input-group-addon">
						<i class="fa fa-calendar bigger-110"></i>
					</span>
				</div>
			</div>
			
			<label class="col-sm-2 control-label no-padding-right"
				for="startTime">活动开始时间</label>
			<div class="col-sm-3">
				<input type="text" name="startTime" id="startTimeFilter"
					placeholder="请输入活动开始时间" value="${centuryActivityFilter.startTime }"
					class="form-control required" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label no-padding-right"
				for="endDate">活动结束日期</label>
			<div class="col-sm-3">
				<div class="input-group">
					<input type="text" name="endDate" id="endDateFilter"  class="form-control date-picker"   value="${centuryActivityFilter.endDate}" />
					<span class="input-group-addon">
						<i class="fa fa-calendar bigger-110"></i>
					</span>
				</div>
			</div>	

			<label class="col-sm-2 control-label no-padding-right"
				for="endTime">活动结束时间</label>
			<div class="col-sm-3">
				<input type="text" name="endTime" id="endTimeFilter"
					placeholder="请输入活动结束时间" value="${centuryActivityFilter.endTime }"
					class="form-control required" >
			</div>
		</div>
		</div>
	</form:form>
</div>
<script type="text/javascript">

</script>