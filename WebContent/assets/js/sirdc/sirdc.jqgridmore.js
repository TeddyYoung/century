function jqgridMore(obj) {
	var gridTableId = obj.gridTableId;
	var gridPagerId = obj.gridPagerId;
	var intid=obj.intid;
	var flag=obj.listDataFlag;
    obj.cellBefore = obj.cellBefore == null ? function(){} : obj.cellBefore;
	intid=intidData(colModel,intid);
	$(gridTableId).jqGrid({
		datatype:'local',
		height : 320,
		colNames : colNames,
		colModel : colModel,
		prmNames : prmNames,
		viewrecords : true,
		altRows : true,
		multiselect : true,
		multiboxonly : true,
		loadComplete : loadComplete,
		autowidth : true,
		cellEdit : true,
		cellsubmit : 'clientArray',
		beforeEditCell : function(id, name, val, iRow, iCol) {
			lastrow = iRow;
			lastcell = iCol;		
		},
		afterEditCell : function(id, name, val, iRow, iCol) {
			listenKeydownForGrid(id, name, val, iRow, iCol, "gridTable");

		},
		afterSaveCell : function(id, name, val, iRow, iCol) {
			obj.cellBefore(gridTableId,id, name, val, iRow, iCol);
			var flag = getCellData(gridTableId, id, "flag");
			if (flag=="list") {
				setCellData(gridTableId, id, "flag", "update");
			}
			if(isBlank(obj.flagOprate)){
				for ( var inti in intid) {
					intid[inti]	=getCellData(gridTableId, id, inti);
				}
				addListFun(gridTableId, iRow,intid);
				}
		}
	});
	initListFun(gridTableId,listData,intid,flag);//初始化
	autowidth(gridTableId);
	resizeSidebar(gridTableId);
}
function intidData(colModel,intid) {
	var v = new Object();
	$(colModel).each(function() {
		for ( var inti in intid) {
			if (this.name == inti) {
				v[inti] = intid[inti];
			}
		}
	})
	v.flag ="add";
	return v;
}
// 添加条记录 jqgrid
function addListFun(gridtable, index,v) {
	var obj = $(gridtable).jqGrid("getRowData");
	if (obj.length == 0 || index == obj.length) {
		var b = obj.length + 1;
		v.sysId = b;
		$(gridtable).jqGrid('addRowData', b, v);
	}
}
// 獲取單元的值,傳入table id,列id,列名
function getCellData(tableid, rowid, name) {
	return $(tableid).jqGrid("getCell", rowid, name);
}

// 設置單元格的值,傳入table id,列id,列名,值
function setCellData(tableid, rowid, name, val) {
	$(tableid).jqGrid("setCell", rowid, name, val);
} 

//点击事件

function deleteListFun(gridtable,deleList) {
	var selectedIds = $(gridtable).jqGrid("getGridParam","selarrrow");
	$(selectedIds).each(function() {
	var flag=getCellData(gridtable, this, "flag") 
	if (flag != "add") {
		deleList.push(this);
	}
$(gridtable).jqGrid('delRowData', this);
	})
		if (selectedIds.length ==1) {
			addListFun(gridtable, 0,intid);
	}
	return deleList;
}
function initListFun(gridtable,listData,intid,flag) {
	listnew=new Array();
	$(listData).each(function() {
		var v=this;
		if(isBlank(flag)||flag==null){
			v.flag="list";
		}
 		else {
 			v.flag=flag;
		}

		listnew.push(v);
	})
		intid.sysId=listnew.length+1;
		intid.flag="add";
		listnew.push(intid);
		jQuery(gridtable).jqGrid("setGridParam", {
			datatype : 'local',
			data : listnew
		}).trigger("reloadGrid");
		return listnew;
}



function saveDatList(gridTable,lastrow,lastcell,deleList,url) {
	$(gridTable).jqGrid("saveCell",lastrow,lastcell);
	var obj = $(gridTable).jqGrid("getRowData");
	var lastData=$("#gridTable").jqGrid("getRowData",obj.length);
	if (lastData.flag == "add") {
		$(gridTable).jqGrid('delRowData', lastData.sysId);
		obj = $(gridTable).jqGrid("getRowData");
	}
	var addList = new Array();//添加List
    var updateList=	new Array();//修改记录列表ID	
	$(obj).each(function() {
			if (this["flag"] == "add") {
				var v=new Object();
				 for (tem in this)
				{
					if(tem!="flag")
						{
						    v[tem]=this[tem];
						}
				}
				 addList.push(v);
			}
			else if (this["flag"] == "update") {
				var v=new Object();
				 for (tem in this)
				{
					if(tem!="flag")
						{
						    v[tem]=this[tem];
						}
				}
				updateList.push(v);
			}
			//进行删除，修改 添加的操作 返回3个list（）;
			//成功后进行清空
		})
		if (addList.length == 0 && updateList.length == 0 &&deleList.length==0) {
			alert("没有数据改动");
			return;
		}
		$.ajax({
			url : url,
			type : "POST",
			contentType : "application/json",
			data : JSON.stringify({
				addList : addList,
				updateList:updateList,
				deleList:deleList
			}),
			success : function() {
				alert("保存成功");
				addList = new Array();
				updateList = new Array();
			}
		});
	}