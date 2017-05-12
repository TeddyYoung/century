/**
 * 显示确认对话框
 * @param confirmTitle 标题
 * @param confirmMessage 消息
 * @param ok 确定消息
 * @param cancel 取消消息
 * @param func 方法
 */
function showConfirmDialog(obj) {
	obj.confirmFunc = obj.confirmFunc == null ? function(){} : obj.confirmFunc;
	obj.confirmButtons = {
		confirm : {
			label : obj.confirmOk,
			className : "btn-primary btn-sm",
		},
		cancel : {
			label : obj.confirmCancel,
			className : "btn-sm",
		}
	};
	showBootboxConfirm(obj);
}

/**
 * 显示警告消息对话框
 * @param errorTitle 标题
 * @param errorMessage 内容
 * @param errorFunc 回调
 * @param errorOk 确认按钮名
 */
function showErrorDialog(obj) {
	obj.errorFunc = obj.errorFunc == null ? function(){} : obj.errorFunc;
	obj.dialogTitle = obj.errorTitle;
	obj.dialogMessage = obj.errorMessage;
	obj.dialogButtons = {
		"danger" : {
			"label" : obj.errorOk,
			"className" : "btn-sm btn-danger",
			"callback" : obj.errorFunc
		}
	};
	showBootboxDialog(obj);
}

/**
 * 显示表单模态窗
 * @param obj.dialogTitle 标题
 * @param obj.dialogBody 内容
 * @param obj.dialogFoot 按钮组
 * @param obj.dialogFunc 功能
 */
function showDialog(obj) {
	$("#formDialogBody").empty();//清空后加入
	$("#formDialogBody").append(obj.dialogBody);
	createDialog(obj);
}

/**
 * 显示表单模态窗
 * @param obj.dialogTitle 标题
 * @param obj.dialogUrl 内容
 * @param obj.dialogFoot 按钮组
 * @param obj.dialogFunc 功能
 */
function loadDialog(obj) {
	$("#formDialogBody").load(obj.dialogUrl);
	createDialog(obj);
}

/**
 * 创建表单模态窗
 * @param obj.dialogTitle 标题
 * @param obj.dialogFoot 按钮组
 * @param obj.dialogFunc 功能
 */
function createDialog(obj) {
	obj.dialogFunc = obj.dialogFunc == null ? function(){} : obj.dialogFunc;
	$("#formDialogTitle").text(obj.dialogTitle);
	$("#formDialogFooter").empty();//清空后加入
	$("#formDialogFooter").append(obj.dialogFoot);
	$('#formDialog').modal('show');
	obj.dialogFunc();//显示之后的方法
}

/**
 * 显示确认对话框
 * @param confirmTitle 确认标题
 * @param confirmMessage 确认消息
 * @param confirmButtons 确认的按钮
 * @param confirmFunc 确定之后的方法
 */
function showBootboxConfirm(obj) {
	obj.confirmButtons = obj.confirmButtons == null ? function(){} : obj.confirmButtons;
	obj.confirmFunc = obj.confirmFunc == null ? function(){} : obj.confirmFunc;
	bootbox.confirm({
		title : obj.confirmTitle,
		message : obj.confirmMessage,
		buttons : obj.confirmButtons,
		callback : obj.confirmFunc
	});
}


/**
 * 显示对话框
 * @param dialogTitle 对话框标题
 * @param dialogMessage 对话框消息
 * @param dialogButtons 对话框按钮组
 */
function showBootboxDialog(obj) {
	bootbox.dialog({
		title : obj.dialogTitle,
		message : obj.dialogMessage,
		buttons : obj.dialogButtons
	});
}