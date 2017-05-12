var rowList=[10,20,30]; //定义可以选择的行数	

/**
 * 自定义参数
 */
var prmNames = {
	page : "paging.currentPage", // 表示请求页码的参数名称
	rows : "paging.pageSize", // 表示请求行数的参数名称
	sort : "paging.sortCol", // 表示用于排序的列名的参数名称
	order : "paging.sortVal", // 表示采用的排序方式的参数名称
};

/**
 * 将from表单序列化成json格式
 */
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

/**
 * 拼接俩个json
 * @param postData
 * @param data
 * @returns postData
 */
function appendJson(postData, data) {
	$.extend(postData, data);
	return postData;
}

/**
 * 往postdata中添加参数
 * @param tableId
 * @param data
 * @returns {postData}
 */
function appendPostDataJson(tableId, data) {
	var postData = getPostData(tableId);
	return appendJson(postData, data);
}

/**
 * 获取getpostData
 * @param tableId
 * @returns
 */
function getPostData(tableId) {
	var postData = $(tableId).jqGrid("getGridParam", "postData");
	return postData;
}

/**
 * 发送postData数据更新jqgrid
 * @param tableId
 * @param postData
 */
function seachJqgirdData(tableId, postData) {
	$(tableId).jqGrid('setGridParam', {
		postData : postData,// 发送数据
		search : true
	}).trigger("reloadGrid", [{
		page : 1
	}]);
}

/**
 * 
 * @param tableId
 * @param url
 */
function seachJqgirdUrl(tableId, url) {
	$(tableId).jqGrid('setGridParam', {
		url : url,// 发送数据
		search : true
	}).trigger("reloadGrid", [{
		page : 1
	}]);
}

/**
 * 发送表单数据
 * @param tableId
 * @param formId
 */
function seachJqgird(tableId, formId) {
	var data = serializeObjectData(formId);
	var postData = appendPostDataJson(tableId, data)
	seachJqgirdData(tableId, postData);
}

/**
 * 将表单转化为json对象
 * @param id
 * @returns
 */
function serializeObjectData(id) {
	var data = $(id).serializeObject()
	return data;
}
//resize to fit page size
function autowidth(obj) {
	$(window).on('resize.jqGrid', function() {
		if(obj.widthId == null || obj.widthId == "") {
			$(obj.gridTableId).jqGrid('setGridWidth', $(".page-content").width());// 设置随屏幕位置变化
		} else {
			$(obj.gridTableId).jqGrid('setGridWidth', $(obj.widthId).width());// 设置随屏幕位置变化
		}
	}) 
}

//resize on sidebar collapse/expand
function resizeSidebar(tableId) {
	var parent_column = $(tableId).closest('[class*="col-"]');
	$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
		if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
			//setTimeout is for webkit only to give time for DOM changes and then redraw!!!
			setTimeout(function() {
				$(tableId).jqGrid( 'setGridWidth', parent_column.width() );
			}, 0);
		}
	}) 
}

//jqgrid的checkbox
function aceSwitch(cellvalue, options, cell) {
	setTimeout(function() {
		$(cell).find('input[type=checkbox]')
		.addClass('ace ace-switch ace-switch-5')
		.after('<span class="lbl"></span>');
	}, 0);
}
//日期插件
function pickDate(cellvalue, options, cell) {
	setTimeout(function() {
		$(cell).find('input[type=text]').datepicker({
			format : 'yyyy-mm-dd',
			autoclose : true
		});
	}, 0);
}
//or go back to default browser checkbox styles for the grid
function styleCheckbox(table) {
/**
	$(table).find('input:checkbox').addClass('ace')
	.wrap('<label />')
	.after('<span class="lbl align-top" />')


	$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
	.find('input.cbox[type=checkbox]').addClass('ace')
	.wrap('<label />').after('<span class="lbl align-top" />');
*/
}


//unlike navButtons icons, action icons in rows seem to be hard-coded
//you can change them like this in here if you want
function updateActionIcons(table) {
	/**
	var replacement = 
	{
		'ui-ace-icon fa fa-pencil' : 'ace-icon fa fa-pencil blue',
		'ui-ace-icon fa fa-trash-o' : 'ace-icon fa fa-trash-o red',
		'ui-icon-disk' : 'ace-icon fa fa-check green',
		'ui-icon-cancel' : 'ace-icon fa fa-times red'
	};
	$(table).find('.ui-pg-div span.ui-icon').each(function(){
		var icon = $(this);
		var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
		if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
	})
	*/
}

//replace icons with FontAwesome icons like above
function updatePagerIcons(table) {
	var replacement = {
		'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
		'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
		'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
		'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
	};
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
		var icon = $(this);
		var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
		if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
	})
}

function enableTooltips(table) {
	$('.navtable .ui-pg-button').tooltip({container:'body'});
	$(table).find('.ui-pg-div').tooltip({container:'body'});
}

//获取所有选中的行ids
function getSelectIds(tableId) {
	return $(tableId).jqGrid('getGridParam','selarrrow');
}

//重载
function reload(tableId) {
	$(tableId).jqGrid().trigger("reloadGrid"); //重新载入  
}

function loadComplete() {
	var table = this;
	setTimeout(function(){
		styleCheckbox(table);
		updateActionIcons(table);
		updatePagerIcons(table);
		enableTooltips(table);
	}, 0);
}

function jqgrid(obj) {
	var gridTableId = obj.gridTableId;
	var gridPagerId = obj.gridPagerId;
	obj.url = obj.url == null ? baseUrl:obj.url;
	obj.postData = obj.postData == null ? {} : obj.postData;
	jQuery(gridTableId).jqGrid({
		datatype: "json",
		url :obj.url,
		mtype : 'post',
		height: 320,
		colNames:colNames,
		colModel:colModel, 
        prmNames :prmNames,
		viewrecords : true,
		rowNum:rowList[0], 
		rowList:rowList,
		pager : gridPagerId,
		postData: obj.postData,
		altRows: true,
		multiselect: true,
        multiboxonly: true,
		loadComplete: loadComplete, 
		autowidth: true,
	});
    autowidth(obj);
    $(window).triggerHandler('resize.jqGrid');//刷新
    resizeSidebar(gridTableId);
}
