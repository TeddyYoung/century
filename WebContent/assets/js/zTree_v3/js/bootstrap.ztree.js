/**
 * 树的多选按钮
 * @param obj 采取对象传值 inputObjKey (必选), valueObjKey,（可选，不选择则以inputObjkey为值） 
 * treeObjName （必选） ,url（可选）,levelKey （可选）,sysId （可选）
 */
function settingTreeParam(obj){
	var setting = {
		check: {
			enable: true,
			chkboxType: {"Y" : "p", "N" : "p"}
		},
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj(treeId);
				zTree.checkNode(treeNode, !treeNode.checked, null, true);
				return false;
			},
			onCheck: function(e, treeId, treeNode) {
				onZtreeCheck({
					e:e,
					treeId:treeId,
					treeNode:treeNode, 
					inputObjKey:obj.inputObjKey,
					valueObjKey:obj.valueObjKey,
					levelKey:obj.levelKey
				});
			}
		}
	};
	$.ajax({
		type: "POST",
	   	url: obj.url,
	   	data:{'sysId':obj.sysId},
	   	success: function(zNodes){
	   		if (zNodes == "[object Object]") {
	        } else {
	        	zNodes = eval(zNodes);
	        }
			$.fn.zTree.init($("#"+obj.treeObjName), setting, zNodes);
			
			//赋值
			var treeObj = $.fn.zTree.getZTreeObj(obj.treeObjName);
			var values;
			if (isNotBlank(obj.valueObjKey)) {
				values = $(obj.valueObjKey).val().split(',');
			}
 			else {
 				 values = $(obj.inputObjKey).val().split(',');

			}
			for(var i=0; i<values.length; i++) {
				var node = treeObj.getNodeByParam("id", values[i]);
				treeObj.checkNode(node,true,true);
				onZtreeCheck({
					e:'',
					treeId:obj.treeObjName,
					treeNode:node,
					inputObjKey:obj.inputObjKey,
					valueObjKey:obj.valueObjKey});
			}
		}
	});
	
	var inputObj = $(obj.inputObjKey);
	inputObj.bind({
		click: function(){
			showTreeContent(obj.inputObjKey, obj.treeContentKey);
		}
	});
}


/**
 * 树的单选按钮
 * @param obj 采取对象传值 inputObjKey (必选), valueObjKey,（可选，不选择则以inputObjkey为值） 
 * treeObjName （必选） ,url（可选）,levelKey （可选）,sysId （可选）
 */
function settingOneCheckTreeParam(obj){
	var setting = {
		check: {
			enable: true,
			chkStyle: "radio",
			radioType:'all'
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj(treeId);
				zTree.checkNode(treeNode, !treeNode.checked, null, true);
				return false;
			},
			onCheck: function(e, treeId, treeNode) {
				onZtreeCheck({
					e:e,
					treeId:treeId,
					treeNode:treeNode, 
					inputObjKey:obj.inputObjKey,
					valueObjKey:obj.valueObjKey,
					levelKey:obj.levelKey
				});
			}
		}
	};
	$.ajax({
		type: "POST",
	   	url: obj.url,
	   	data:{'sysId':obj.sysId},
	   	success: function(zNodes){
	   		if (zNodes == "[object Object]") {
	        } else {
	        	zNodes = eval(zNodes);
	        }
			$.fn.zTree.init($("#"+obj.treeObjName), setting, zNodes);
			//赋值
			var treeObj = $.fn.zTree.getZTreeObj(obj.treeObjName);
			if (isNotBlank(obj.valueObjKey)) {
				var values = $(obj.valueObjKey).val().split(',');
			}
 			else {
 				var values = $(obj.inputObjKey).val().split(',');

			}
			for(var i=0; i<values.length; i++) {
				var node = treeObj.getNodeByParam("id", values[i]);
				treeObj.checkNode(node,true,true);
				onZtreeCheck({
					e:'',
					treeId:obj.treeObjName,
					treeNode:node,
					inputObjKey:obj.inputObjKey,
					valueObjKey:obj.valueObjKey});
				
			}
		}
	});
	
	var inputObj = $(obj.inputObjKey);
	inputObj.bind({
		click: function(){
			showTreeContent(obj.inputObjKey, obj.treeContentKey);
		}
	});
}
 
function onZtreeCheck(obj){
	var zTree = $.fn.zTree.getZTreeObj(obj.treeId),
	nodes = zTree.getCheckedNodes(true),
	names = "";
	values = "";
	for (var i=0, l=nodes.length; i<l; i++) {
		names += nodes[i].name + ",";
		values += nodes[i].id + ",";
	}
	if (names.length > 0 ) names = names.substring(0, names.length-1);
	if (values.length > 0 ) values = values.substring(0, values.length-1);
	if (isNotBlank(obj.valueObjeKey)) {
		var inputNameObj = $(obj.inputObjKey);
		inputNameObj.attr("value", names);
		var inputValObj = $(obj.valueObjeKey);
		inputValObj.attr("value",values);
	}
	else
		{
		var inputValObj = $(obj.inputObjKey);
		inputValObj.attr("value",values);
		}
	
	if(isNotBlank(obj.levelKey)){
		var levelKeyobj = $(obj.levelKey);
		var count=stringcount(values);
		levelKeyobj.attr("value",count);
	}
}
function stringcount(str)
{
	var seach="/";
	var count = 0;
	while (str!=null&&str.length > seach.length||str.length==seach.length) {
		var index = str.indexOf(seach);
		if (index == -1)
			break;
		    count++;
		str = str.substring(index+1);
	}
	return count;
}

function showTreeContent(inputObjKey, treeContentKey) {
	$(treeContentKey).css({width:$(inputObjKey).outerWidth()+'px'}).slideDown("fast");
	$("body").bind("mousedown", function(event){
		if (!(event.target == $(treeContentKey) || $(event.target).parents(treeContentKey).length>0)) {
			hideTreeContent(treeContentKey);
		}
	});
}

function hideTreeContent(treeContentKey) {
	$(treeContentKey).fadeOut("fast");
	$("body").unbind("mousedown");
}