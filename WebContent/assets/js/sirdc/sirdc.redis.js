/**
 * redis工具类，用于获取常用的显示数据
 */

/**
 * 常常用于显示部分，如显示性别，显示部门名称，角色名称
 * @param array 数据
 * @param key 比较栏位
 * @param val 比较数据
 * @param name 返回栏位
 */
function getName(array, key, val, name) {
	for(var i=0; i<array.length; i++) {
		if (array[i][key] == val) {
			return array[i][name];
		}
	}
	return '';
}

/**
 * 获取整个对象，主要用于其他处理方面
 * @param array
 * @param key
 * @param val
 */
function getObj(array, key, val) {
	for(var i=0; i<array.length; i++) {
		if (array[i][key] == val) {
			return array[i];
		}
	}
	return '';
}