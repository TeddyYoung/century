/**
 * ajax post方法
 * @param ajax.url 路径
 * @param ajax.data 参数对象，为空的时候输入{}
 * @param ajax.func
 * @param ajax.error 错误的执行方法
 */
function post(obj) {
	obj.data = obj.data == null ? {} : obj.data;
	$.ajax({
		type : "POST",
		url : obj.url,
		data : obj.data,
		success : obj.func,
		error: obj.error
	});
}

/**
 * 执行确认方法
 * @param obj.url 路径
 * @param obj.data 数据
 * @param obj.successTitle 成功之后的标题
 * @param obj.successMessage 成功之后的消息
 * @param obj.successFunc 成功之后的事件
 * @param obj.failMessage 失败消息
 * @param obj.failTitle 失败标题
 * @param obj.failFunc 失败之后的事件
 * @param obj.reload 重载事件，可以不填，默认什么都不执行
 */
function postCallBack(obj) {
	obj.data = obj.data == null ? {} : obj.data;
	obj.successFunc = obj.successFunc == null ? function(){} : obj.successFunc;
	obj.failFunc = obj.failFunc == null ? function(){} : obj.failFunc;
	obj.reload = obj.reload == null ? function(){} : obj.reload;
	post({
		url: obj.url,
		data: obj.data,
		func: function(data) {
			if(data.code == "200") {
				obj.reload();//重载事件
				var successMessage = obj.successMessage != null ? obj.successMessage : data.message;
				successMessage = successMessage == null ? "操作成功" : successMessage;
				showGritter(obj.successTitle, successMessage);//消息提示
				obj.successFunc(data);
			} else {
				var failMessage = obj.failMessage != null ? obj.failMessage : data.message;
				failMessage = failMessage == null ? "操作失败" : failMessage;
				showGritter(obj.failTitle, failMessage);//消息提示
				obj.failFunc();
			}
		},
		error: function() {
			showGritter(obj.failTitle, obj.failMessage);//消息提示
			obj.failFunc();
		}
	});
}