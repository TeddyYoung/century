/**
 * 将空或者空字符串转换成空字段串
 * @param value
 * @returns
 */
function nullToStr(value){
	if(value == null || value == undefined) {
		return "";
	}
	return value;
}

/**
 * 判断是否不为空，空返回false，非空返回true
 * @param value
 * @returns {Boolean}
 */
function isNotBlank(value) {
	var result = true;
	value = nullToStr(value);
	if(value == "") {
		result = false;
	}
	return result;
}

/**
 * 判断是否为空，空返回true，非空返回false
 * @param value
 * @returns {Boolean}
 */
function isBlank(value){
	var result = false;
	value = nullToStr(value);
	if(value == "") {
		result = true;
	}
	return result;				
}