/**
 * 重置表单
 * @param id
 */
function resetForm(formId) {
	$(formId)[0].reset();
}

//设定baseUrl
var baseUrl = "";

/**
 * 通过此方法获取url
 * @param url
 * @returns {String}
 */
function getUrl(url) {
	return baseUrl + url + "/";
}

/**
 * 序列化数据
 * @param formId
 */
function serialize(formId) {
	return $(formId).serialize();
}

/**
 * 执行保存方法，并根据信息进行提示
 * @param obj.formId 表单Id *
 * @param obj.dialogId 对话框Id *
 * @param obj.url 更新/保存的路径 *
 * @param obj.data 传送至服务端的数据
 * @param obj.tableId 表格的id *
 * ***************************************** *
 * @param obj.successMessage 成功消息 *
 * @param obj.successTitle 成功标题 *
 * @param obj.successFunc 成功之后的回调
 * @param obj.failMessage 失败消息 *
 * @param obj.failTitle 失败标题 *
 * @param obj.failFunc 失败之后的回调
 * ***************************************** *
 * @param obj.before 删除之前的回调
 * @param obj.after 删除之后的回调
 */
function executeSaveOrUpdate(obj) {
	//初始化
	obj.before = obj.before == null ? function(){} : obj.before;
	obj.after = obj.after == null ? function(){} : obj.after;
	obj.failFunc = obj.failFunc == null ? function(){} : obj.failFunc;
	obj.successFunc = obj.successFunc == null ? function(){} : obj.successFunc;
	
	if($(obj.formId).valid()) {
		obj.before();
		obj.data = serialize(obj.formId);
		obj.successFunc = function() {
			$(obj.dialogId).modal('hide');
		}
		obj.reload = function() {
			reload(obj.tableId);//重载
		}
		postCallBack(obj);
		obj.after();
	}
}

/**
 * 显示删除对话框
 * @param obj.url 删除的路径 *
 * @param obj.data 传送至服务端的数据
 * @param obj.tableId 表格的id *
 * @param obj.before 删除之前的回调
 * @param obj.after 删除之后的回调
 * ***************************************** *
 * @param obj.confirmTitle 提示确认的标题 *
 * @param obj.confirmMessage 提示确认的消息 *
 * @param obj.confirmOk 确定按钮文字信息 *
 * @param obj.confirmCancel 取消按钮信息 *
 * @param obj.confirmFunc 确定之后的事件
 * ***************************************** * 
 * @param obj.successMessage 成功消息 *
 * @param obj.successTitle 成功标题 *
 * @param obj.successFunc 成功之后的回调
 * @param obj.failMessage 失败消息 *
 * @param obj.failTitle 失败标题 *
 * @param obj.failFunc 失败之后的回调
 * ***************************************** *
 * @param obj.errorTitle 未选中的消息标题*
 * @param obj.errorMessage 未选中的消息内容*
 * @param obj.errorFunc 未选中之后的事件*
 * @param obj.errorOk 未选中之后提示的按钮消息*
 */
function showDeleteDialog(obj) {
	//避免空对象
	obj.data = obj.data == null ? {} : obj.data;
	obj.failFunc = obj.failFunc == null ? function(){} : obj.failFunc;
	obj.successFunc = obj.successFunc == null ? function(){} : obj.successFunc;
	obj.before = obj.before == null ? function(){} : obj.before;
	obj.after = obj.after == null ? function(){} : obj.after;
	obj.errorFunc = obj.errorFunc == null ? function(){} : obj.errorFunc;
	obj.confirmFunc = obj.confirmFunc == null ? function(){} : obj.confirmFunc;
	
	var ids = getSelectIds(obj.tableId);
	if(isNotBlank(ids)) {
		obj.before();//删除之前的事件
		obj.url = obj.url + ids;
		obj.confirmFunc = function(result) {
			if (result) {//确认按钮点击事件
				obj.reload = function() {
					reload(obj.tableId);//重载
				}
				postCallBack(obj);
			}
		}
		showConfirmDialog(obj);
		obj.after();//删除之后的事件
	} else {
		showErrorDialog(obj);
	}
}

/**
 * 显示编辑对话框
 * @param obj.tableId 表格id
 * @param obj.before 显示编辑之前的回调
 * @param obj.after 显示编辑之后的回调
 * ***************************************** *
 * @param obj.dialogTitle 标题*
 * @param obj.dialogUrl 显示编辑的路径 *
 * @param obj.dialogFoot 按钮组*
 * @param obj.dialogFunc 功能
 * ***************************************** *
 * @param obj.errorTitle 未选中的消息标题*
 * @param obj.errorMutiSelectMessage 未选中的消息内容*
 * @param obj.errorUnSelectMessage 未选中的消息内容*
 * @param obj.errorFunc 未选中之后的事件*
 * @param obj.errorOk 未选中之后提示的按钮消息*
 */
function showEditDialog(obj) {
	//避免空对象	
	obj.before = obj.before == null ? function(){} : obj.before;
	obj.after = obj.after == null ? function(){} : obj.after;
	obj.dialogFunc = obj.dialogFunc == null ? function(){} : obj.dialogFunc;
	obj.dialogFoot = obj.dialogFoot == null ? function(){} : obj.dialogFoot;
	if(obj.loadDialog == null) {
		obj.loadDialog = loadDialog;
	}
	
	var ids = getSelectIds(obj.tableId);
	if(isNotBlank(ids)) {
		if(ids.length >1) {
			obj.errorMessage = obj.errorMutiSelectMessage;
			showErrorDialog(obj);
		} else {
			obj.before();//之前的动作
			obj.id = ids;
			obj.dialogUrl = obj.dialogUrl + ids;
			obj.loadDialog(obj);
			obj.after();//之后的动作
		}
	} else {
		obj.errorMessage = obj.errorUnSelectMessage;
		showErrorDialog(obj);
	}
}