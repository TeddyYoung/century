/**
 * 显示提示框
 * @param title 标题
 * @param text 内容
 */
function showGritter(title, text) {
	$.gritter.add({
		title: title,
		text: text
	});
}