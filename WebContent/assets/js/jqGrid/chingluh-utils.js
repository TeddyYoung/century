var pagesizelist=[20, 40, 60,100,500,1000,2000 ];//页面大小选择
var alertError=false;//标志位，用于控制JQGRID出错时，回车可以返回原来的编辑栏位
function HashMap(){

	/** Map 大小 **/
	var size = 0;
	/** 对象 **/
	var entry = new Object();
	/** 存 **/
	this.put = function(key, value) {
		if (!this.containsKey(key)) {
			size++;
		}
		entry[key] = value;

	};

	/** 取 **/
	this.get = function(key){
		if (this.containsKey(key)){
			return entry[key];
		}else{
			return null;
		}

	};

	/** 删除 **/
	this.remove = function(key){
		if (delete entry[key]){
			size--;
		}

	};

	/** 是否包含 Key **/
	this.containsKey = function(key){
		return (key in entry);
	};

	/** 是否包含 Value **/
	this.containsValue = function(value){
		for ( var prop in entry){
			if (entry[prop] == value){
				return true;
			}
		}
		return false;
	};

	/** 所有 Value **/
	this.values = function(){

		var values = new Array();

		for ( var prop in entry){
			values.push(entry[prop]);
		}

		return values;
	};

	/** 所有 Key **/
	this.keys = function(){
		var keys = new Array();
		for ( var prop in entry){
			keys.push(prop);
		}
		return keys;

	};

	/** Map Size **/
	this.size = function(){
		return size;
	};

}// HashMap() End.

function HashSet(){

	/** Set 大小 **/
	var size = 0;
	/** 对象 **/
	var entry = new Object();
	/** 存 **/
	this.add = function(value) {
		if (!this.contains(value)) {
			size++;
			entry[value];
		}
	};

	/** 删除 **/
	this.remove = function(value){
		if (delete entry[value]){
			size--;
		}

	};

	/** 是否包含 **/
	this.contains = function(value){
		return (value in entry);
	};

	/** Set Size **/
	this.size = function(){
		return size;
	};

}// HashSet() End.


//Add by Rock begin
//设置按钮样式
$(function(){
	$("input[disabled=disabled],textarea[disabled=disabled]").addClass("chingluh-readOnly");
//	$("select").addClass("ui-widget ui-widget-content ui-state-default ui-corner-left ui-autocomplete-input"); 
	$("input[class=chingluh-table-button],button[class=chingluh-table-button],input[class=chingluh-toolbar-button],button[class=chingluh-toolbar-button]")
		.button(); 
 	});
//设置页面初始化动作
function initControl(gridName)
{
	if(gridName.length>0)
	{
		// 將 span 裡的東西加入工具列
		jQuery("#t_"+gridName).append(jQuery("#grid_toolbar").html());  
		//jQuery("#jqgridlisttable").setGridWidth($(window).width());
	}
	var showMessage =jQuery("#showMessage").val();// document.getElementById("showMessage").value;
	if(showMessage!=""&&showMessage!=null){ 
			alert(showMessage); 
	}
 }
 function setJqGridAutoHeight(gridName,baseHeight)
 {
	//Title,Menu,Toolbar,Footer 高度合计为305，在加显示区一行高度为28；
		var h=$(window).height()-baseHeight; //数据能显示的高度
		//高度小于250 则按250进行显示，数据区域太小，看起来会不舒服
		if(h<250)
		{h=250;}
		jQuery("#"+gridName).setGridHeight(h);
	 }  
 
//獲取單元的值,傳入table id,列id,列名
 function getCellData(tableid,rowid,name){
 	return $("#"+tableid).jqGrid("getCell",rowid,name);
 }

 //設置單元格的值,傳入table id,列id,列名,值
 function setCellData(tableid,rowid,name,val){
 	$("#"+tableid).jqGrid("setCell",rowid,name,val);
 }
 
//傳入不要的位數,例如需要的是第2 3位則傳入1
 function getCheckFlag(checkFlag,index){
 	if(index==1){
 		return checkFlag.substring(1);
 	}else if(index==checkFlag.length){
 		return checkFlag.substring(0,checkFlag.length-1);
 	}else{
 		return checkFlag.substring(0,index-1)+"_"+checkFlag.substring(index);
 	}
 } 
 
//檢核欄位值是否重複
 function checkColumnRepeat(tableid,id,name,val){
 	var ids = jQuery("#"+tableid).jqGrid('getDataIDs');
 	for(var i=0;i<ids.length;i++){
 		var column = getCellData(tableid,ids[i],name);
 		if(column==val){
 			if(ids[i]!=id){
 				return false;
 			}
 		}
 	}
 	return true;
 }
 
//檢核欄位值是否重複
 function checkColumnsRepeat(tableid,id,names,vals){
 	var ids = jQuery("#"+tableid).jqGrid('getDataIDs');
 	for(var i=0;i<ids.length;i++){
 		var column = "";
 		for(var j=0;j<names.length;j++){
 			column = column+getCellData(tableid,ids[i],names[j]);
 		}
 		if(column==vals){
 			if(ids[i]!=id){
 				return false;
 			}
 		}
 	}
 	return true;
 }
 //获取系统当前月份
 function getSysDateYYYYMM()
 {
	 var date = new Date(); 
	 var month = Number(date.getMonth())+1;
		if(month<10)
			month="0"+month;
		return date.getFullYear()+month.toString();
 }
 //取系統日期
 function getSysDate(){
	var date = new Date(); 
	var month = Number(date.getMonth())+1;
	var day = ""; 
	if(month<10)
		month="0"+month;
	if(date.getDate()<10){
		day="0"+date.getDate();
	}else if(date.getDate().length>2){
		day=date.getDate().subString(1);
	}else{
		day=date.getDate();
	}
	return date.getFullYear()+month.toString()+day;
 }
 
 /**
	取系統日期
	return yyyy + str + mm + str + dd
 **/
 function getSysDate2(str){
	var date = new Date(); 
	var month = Number(date.getMonth())+1;
	var day = ""; 
	if(month<10)
		month="0"+month;
	if(date.getDate()<10){
		day="0"+date.getDate();
	}else if(date.getDate().length>2){
		day=date.getDate().subString(1);
	}else{
		day=date.getDate();
	}
	return date.getFullYear()+str+month.toString()+str+day;
 }
 
//取系統日期加上days天
 function getSysDateAdd(days){
	var date = new Date(); 
	date.setDate(date.getDate()+days);
	var month = Number(date.getMonth())+1;
	var day = ""; 
	if(month<10)
		month="0"+month;
	if(date.getDate()<10){
		day="0"+date.getDate();
	}else if(date.getDate().length>2){
		day=date.getDate().subString(1);
	}else{
		day=date.getDate();
	}
	return date.getFullYear()+month.toString()+day;
 }
 
 //jqgrid複選框全選/反選
function checkAll(tableid){
	var ids = jQuery("#"+tableid).jqGrid('getDataIDs');
	var allCheckFlag = false;
	for(var i=0;i<ids.length;i++){
		if(getCellData(tableid,ids[i],"delFlag")=="No"){
			setCellData(tableid,ids[i],"delFlag",true);
			allCheckFlag = true;
		}
	}
	if(!allCheckFlag){
		for(var i=0;i<ids.length;i++){
			setCellData(tableid,ids[i],"delFlag",false);
		}
	}
}

//jqgrid複選框全選/反選
function checkAllDetl(tableid,delFlag){
	var ids = jQuery("#"+tableid).jqGrid('getDataIDs');
	var allCheckFlag = false;
	for(var i=0;i<ids.length;i++){
		if(getCellData(tableid,ids[i],delFlag)=="No"){
			setCellData(tableid,ids[i],delFlag,true);
			allCheckFlag = true;
		}
	}
	if(!allCheckFlag){
		for(var i=0;i<ids.length;i++){
			setCellData(tableid,ids[i],delFlag,false);
		}
	}
}
//下来选单绑定方法
//id :Select 的id
//data : 需要绑定的数据源
//blankRow : 空白行，如不需要则传""
//selectValue ：选择的值的id
function bindSelect(id,data,blankRow,selectValue)
{
	var option="";
	if(blankRow!="") {
		if(selectValue=="")
		{
			option+="<option selected='selected' value=''>"+blankRow+"</option>";			
		}
		else
		{
			option+="<option value=''>"+blankRow+"</option>";
		}
	} 
	for(var i=0;i<data.length;i++){
		if(selectValue==data[i].key)
		{
			option+="<option selected='selected' value='"+data[i].key+"'>"+data[i].value+"</option>";
		}
		else
		{
			option+="<option value='"+data[i].key+"'>"+data[i].value+"</option>";
		}
	}
	$("#"+id).html(option);
}
//Add by Rock end

// 獲取當月的最後一天 
//Add by lpp
function getLastDay(dateStr){
	//先取得下一個月的第一天日期，再把這個日期-1天 
	var year=new Number(dateStr.substring(1,4)); //年份
	var new_month=new Number(dateStr.substring(4,6));//下一個月的月份
	var new_year=year;
	if(new_month>=12){
		new_month-=12;
		new_year++;
	}
	var new_date=new Date(new_year,new_month,1);  //下一個月的第一天日期
	return dateStr+(new Date(new_date.getTime()-1000*60*60*24)).getDate();
}

//獲取下一個月 的第一天
function getNextMonth(date){
	var year=date.substring(0,4);
	var month= date.substring(4,6);
	var month2=Number(month)+1;
	var year2=year;
	if(month2==13){
		year2=Number(year2)+1;
		month2=1;
	}
	if(month2<10){
		month2='0'+month2;
	}
	var t2=year2+month2+'01';
	return t2;
}

//數值保留四舍五入
function round(val,roundprecision) {
	return Math.round(val*Math.pow(10,roundprecision))/Math.pow(10,roundprecision);
}

//獲取當月的后幾天
//Add by hgw
function addByTransDate(dateParameter,num){
	var translateDate="";
	var dataString="";
	var monthString="";
	var dayString="";
	translateDate=dateParameter.replace("-","/").replace("-","/");
	translateDate=formatDate(translateDate);
	var newDate=new Date(translateDate);
	newDate=newDate.valueOf();
	newDate=newDate+num*24*60*60*1000;
	newDate=new Date(newDate);
	if((newDate.getMonth()+1).toString().length==1){
		monthString="0"+(newDate.getMonth()+1).toString();
	}else{
		monthString=(newDate.getMonth()+1).toString();
	}
	
	if((newDate.getDate()+1).toString().length==1){
		dayString="0"+(newDate.getDate()).toString();
	}else{
		dayString=(newDate.getDate()).toString();
	}
	dataString=newDate.getFullYear()+"/"+monthString+"/"+dayString;
	return dataString;
}
//设置默认为Grid的第一个可编辑的单元格进行编辑
function setFistCellEdit(gridName){
	 var colmodel=$("#"+gridName).get(0).p.colModel;
     var lastRowInd = jQuery("#"+gridName).jqGrid("getGridParam","reccount");//获取总行数
     var lastColInd=colmodel.length;
     var nextCol=1;
     var nextRow=1;
     for( var i=1;i>0&&i<lastColInd;i++)
         if((!colmodel[i].hidden===true)&&colmodel[i].editable===true) {
       	  nextCol=i;  
       	  break;
         }
     if(nextRow===0||nextRow>lastRowInd)
 		  nextRow=1; 
     if(lastRowInd<=0){
    	 return;
     }
     jQuery("#"+gridName).jqGrid("editCell",nextRow,nextCol,true); 
     
}
//监听回车事件，使用回车控制Grid的输入
function listenKeydownForGrid(id,name,val,iRow,iCol,gridName){
	// Listen for enter (and shift-enter)
    var inputControl = jQuery('#' + (iRow) + '_' + name);  
    inputControl.focus().select();
    inputControl.unbind("keydown");
    inputControl.bind("keydown",function(e) {  
      if (e.keyCode === 13) {  // Enter key:
	        var increment = e.shiftKey ? -1 : 1; 
	        // Do not go out of bounds
	        var colmodel=$("#"+gridName).get(0).p.colModel;
	        var lastRowInd = jQuery("#"+gridName).jqGrid("getGridParam","reccount");//获取总行数
	        var lastColInd=colmodel.length;
	        var nextCol=iCol;
	        var nextRow=iRow;
	        for( var i=iCol+increment;i>0&&i<lastColInd;i=i+increment)
	          if((!colmodel[i].hidden===true)&&colmodel[i].editable===true) {
	        	  nextCol=i; 
	        	  break;
	          }
	        if(nextCol===iCol){ 
	        	nextRow=iRow+increment;
	        	for( var i=1;i>0&&i<lastColInd;i=i+increment)
			          if((!colmodel[i].hidden===true)&&colmodel[i].editable===true) {
			        	  nextCol=i;  
			        	  break;
			          }
	        }
	        if(nextRow===0||nextRow>lastRowInd)
      		  nextRow=1; 
	        $("#"+gridName).jqGrid("saveCell",iRow,iCol);
	         if($("div#msg label#msgLabel").text()==="Error"||alertError){
	        	 jQuery("#"+gridName).jqGrid("editCell",iRow,iCol,true); 
	        	 $("div#msg label#msgLabel").text("");
	        	 alertError=false;
	         }else{
	        	 jQuery("#"+gridName).jqGrid("editCell",nextRow,nextCol,true); 
	         }
	      } 
    }); 
}   
//设置文本框的自动完成功能
function autoCompleteInputKey(id,listData) {
	var projects =listData;
	$( "#"+id ).autocomplete({
	minLength: 0,
	source: projects,
	selectFirst: true, 
	autoFill:true,
	focus: function( event, ui ) {
	$( "#"+id ).val( ui.item.value );
	$( "#"+id ).attr("key",ui.item.key );
	$( "#"+id+"-id" ).val( ui.item.key ); 
	return false;
	},
	select: function( event, ui ) {
	$( "#"+id ).val( ui.item.value);
	$( "#"+id ).attr("key",ui.item.key );
	$( "#"+id+"-id" ).val( ui.item.key ); 
	return false;
	}
	});
	$( "#"+id ).blur(function(event){
		var flag=false;
		var selectValue=this.key;
		var c = new RegExp("^"+selectValue+".*$");
		for(var i=0;selectValue!=""&&i<listData.length;i++){ 
			if(c.test(listData[i].value)){ 
				$( "#"+id ).val( listData[i].value);
				$( "#"+id ).attr("key",listData[i].key );
				flag=true;
				break;
			} 
		}
		if(!flag){  
			setTimeout(function(){$( "#"+id ).focus().select(); },10);
		}
	});
}

//设置文本框的自动完成功能
function autoCompleteInput(id,listData) {
	var projects =listData;
	$( "#"+id ).autocomplete({
	minLength: 0,
	source: projects,
	selectFirst: true, 
	autoFill:true,
	focus: function( event, ui ) {
	$( "#"+id ).val( ui.item.value );
	$( "#"+id ).attr("key",ui.item.key );
	$( "#"+id+"-id" ).val( ui.item.key ); 
	return false;
	},
	select: function( event, ui ) {
	$( "#"+id ).val( ui.item.value);
	$( "#"+id ).attr("key",ui.item.key );
	$( "#"+id+"-id" ).val( ui.item.key ); 
	return false;
	}
	});
	$( "#"+id ).blur(function(event){
		var flag=false;
		var selectValue=this.value;
		var c = new RegExp("^"+selectValue+".*$");
		for(var i=0;selectValue!=""&&i<listData.length;i++){ 
			if(c.test(listData[i].value)){ 
				$( "#"+id ).val( listData[i].value);
				$( "#"+id ).attr("key",listData[i].key );
				flag=true;
				break;
			} 
		}
		if(!flag){  
			setTimeout(function(){$( "#"+id ).focus().select(); },10);
		}
	});
	$("#"+id).after("<span style='display:inline-block'>▼</span>");
}

//设置文本框的自动完成功能
function autoCompleteForGrid(id,listData) {
	var projects =listData;
	$( "#"+id ).autocomplete({
	minLength: 0,
	source: projects,
	selectFirst: true, 
	autoFill:true,
	multiple:true, 
	select: function( event, ui ) {
		if(!isEmpty(ui.item)){
			$( "#"+id ).val( ui.item.value);  
		} 
	return false;
	}
	});  
	if($( "#"+id).next().val()==null){
		$("#"+id ).after("<span id='ddl1' style='display:inline-block'>▼</span>");
		$("#"+id ).removeAttr("style");
		$("#"+id ).attr("style","width:80%");
	}
	
}
//设置通过Key找到Value
function keyFindValue(key,listData){
	var selectValue=key;
	var c = new RegExp("^"+selectValue+".*$");
	for(var i=0;selectValue!=""&&i<listData.length;i++){ 
		if(c.test(listData[i].value)){ 
			return listData[i].value; 
			break;
		} 
	}
	return "";
} 
//通过value 找到key
function valueFindKey(value,listData){
	var selectValue=value;
	 var c = new RegExp("^"+selectValue+".*$");
	for(var i=0;selectValue!=""&&i<listData.length;i++){ 
		 if(c.test(listData[i].value)){ 
		//if(selectValue===listData[i].value){ 
			return listData[i].key; 
			break;
		} 
	}
	return valueLikeFindKey(value,listData); 
	return "";
}

//通过value 找到key
function valueLikeFindKey(value,listData){
	var selectValue=value;
	//var c = new RegExp("^"+selectValue+".*$");
	for(var i=0;selectValue!=""&&i<listData.length;i++){ 
		//if(c.test(listData[i].value)){ 
		if(selectValue===listData[i].value){ 
			return listData[i].key; 
			break;
		} 
	}
	return "";
}
//value :操作的值
//direction ：L左补空格，R右补空格
//str: 需要填补的字符，例如0或 ' '
//num： 补几个
function addLRStr(value,direction,str,num){
	if(direction==='R')
		return value+new Array(num).join(str);
	else{
		return new Array(num).join(str)+value;
	}
}
//取消返回初始頁 
function cancelAdd(){
	location = "back";
}

//取消返回初始頁 
function cancelModify(){
	document.filterform.action="back";
	document.filterform.submit();
	//location = "back";
}

//執行Ajax返回data
function doAjaxPost(ajaxUrl,sendData){
	var returnData=null;
	$.ajax({
		type: "POST",
		url:ajaxUrl,
		data: sendData,
		dataType:"json",
		async:false,
		success:function(result){
			returnData=result;
 		}
	});
	return returnData;
}

function toUpper(obj){
	obj.value=obj.value.toUpperCase();  
}
   
function toLower(obj){
	obj.value=obj.value.toLowerCase(); 
}

//数字补位0
function leftPad(num,n)
{
	return Array(n>(''+num).length?(n-(''+num).length+1):0).join(0)+num;
}
//判断select中是否存在值为value的项
function isExistOption(id,value){
	var isExist=false;
	var count=$("#"+id).find('option').length;
	for(var i=0;i<count;i++){
		if($("#"+id).get(0).options[i].value==value){
			isExist=true;
			break;
		}
	}
	return isExist;
}
//增加select项
function addOptionValue(id,value,text){
	if(!isExistOption(id,value)){
		$("#"+id).append("<option value="+value+">"+text+"</option>");
	}
}
//删除select项
function delOptionValue(id,value){
    if(isExistOption(id,value)){
    	$("#"+id+" option[value="+value+"]").remove();
	}
}

//检核必要的栏位，并返回提示信息
function checkRequiredColumn(tableid,column,name,format){
	var allIds = jQuery("#"+tableid).jqGrid('getDataIDs');
	var msg = "";
	if(allIds == null || column.length != name.length){
		msg += "It is not correct to call this method.";
	}else{
		var selectIds = jQuery("#"+tableid).jqGrid("getGridParam","selarrrow");
		for(var i=0; i<allIds.length; i++){
			for(var j=0; j<selectIds.length; j++){
				if(allIds[i] == selectIds[j]){
					break;
				}
			}
			if(j >= selectIds.length){
				continue;
			}
			var first = true;
			for(var j=0; j < column.length; j++){
				var value = jQuery("#"+tableid).jqGrid("getCell", allIds[i], column[j]);
				if(value == null || value == "" || (format[j] == "N" && parseInt(value) == 0)){
					if(first){
						msg += (i+1)+":";
						first = false;
					}else{
						msg += ",";
					}
					msg += name[j];
				}
			}
			if(!first){
				msg += "\n";
			}
		}
	}
	
	return msg;
}
//检核必要的栏位，并返回提示信息
function checkRequiredColumnById(tableid,id,column,name,format){
	var msg = "";
	if(id == null || column.length != name.length){
		msg += "It is not correct to call this method.";
	}else{
		var first = true;
		for(var j=0; j < column.length; j++){
			var value = jQuery("#"+tableid).jqGrid("getCell", id, column[j]);
			if(value == null || value == "" || (format[j] == "N" && parseInt(value) == 0)){
				if(first){
					first = false;
				}else{
					msg += ",";
				}
				msg += name[j];
			}
		}
	}
	
	return msg;
}
//检核jqGrid中当前行是否被选中，true - 被选中，false - 没有被选中
function checkCurrentRowSelectStatus(tableid,id){
	var result = false;
	var selectIds = jQuery("#"+tableid).jqGrid("getGridParam","selarrrow");
	
	for(var i=0; i < selectIds.length; i++){
		if(selectIds[i] == id){
			result = true;
			break;
		}
	}
	
	return result;
}


//如果止的值为空，则赋值为起的值
function startToEnd(startId,endId){ 
	if(isEmpty($("#"+endId).val())){
		$("#"+endId).val($("#"+startId).val());
	}
}

//錯誤返回本欄位并全選
function focusselectAll(id){
	window.setTimeout(function(){
		$( "#"+id).focus().select();
	},200);
}