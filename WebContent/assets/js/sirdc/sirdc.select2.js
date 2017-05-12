/**
 * 创建select2搜索下拉
 * @param obj
 * textName 显示控件ID
 * valueName 存值的控件ID
 * url 路径
 * inputLength 输入的个数
 * pageSize  搜索后加载的个数
 */
function createselect(obj) {
	var selectText = obj.textName;
	var selectValue = obj.valueName;
	var selectUrl = obj.url;
	var selectInputLength=obj.inputLength;
    var selectPageSize=obj.pageSize;
	$(selectText).select2({
		placeholder : "输入信息",
		allowClear : true,
		minimumInputLength : selectInputLength,
		width : "300px",
		ajax : {
			url : selectUrl, // {# ajax后台函数路径 #}
			dataType : "json", // {#
			type : "post", // {# POST即为前台JS向后台函数取数据，POST反之 #}
			quietMillis : 1000, // {# 延时查询毫秒数 #}
			data : function(term, page) {
				return {
					searchkey : term,// term为前台输入的查询关键字，保存到sSearch对象，即后台保存关键字的对象
					currentPage: page, 
					pageSize:selectPageSize			
				// paging :{"currentPage": page, "pageSize":10}
				// {# 每次查询的结果数 #}
				};
			},
			results : function(data, page) {
				if (data.records > 0) { // 如果没有查询到数据，将会返回空串
					// 将接收到的JSON格式的字符串转换成JSON数据
					var more = (page * selectPageSize) < data.records; // 用来判断是否还有更多数据可以加载
					return {
						results : data.rows,
						more : more
					};
				} else {
					return {
						results : data
					};
				}
			}
		},
		initSelection : function(element, callback) { // 初始化，其中doName是自定义的一个属性，用来存放text的值)
			callback(data);

		},
		createSearchChoice : function(term, data) { // 创建搜索结果（使用户可以输入匹配值以外的其它值）
			return {
				id : term.mid,
				text : term.name
			};
		},
		formatSelection : function(orgs){
			$(selectValue).attr("value", orgs.id);
			return orgs.name;
		}, // 设定查询样式
		formatResult : function(orgs){
			return '<div>' + orgs.name + '</div>';
		}
		,// 设定查询结果样式
		dropdownCssClass : "bigdrop", // 设定SELECT2下拉框样式，bigdrop样式并不在CSS里定义，暂时没深入研究
		escapeMarkup : function(m) {
			return m;
		}
	});
}