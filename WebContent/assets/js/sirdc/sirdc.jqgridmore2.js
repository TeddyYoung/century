			//改变数据进行存储
			function changeMastList(mastList, id, name, val) {
				var hasFlag = false;//判斷mastList中是否存在對應記錄
				var changeFlag = false;//判斷記錄是否被修改
				var obj = new Object();//新增mastList用的obj
				if (mastList.length > 0) {//若mastList已有数据
					for (var i = 0; i < mastList.length; i++) {
						if (mastList[i].sysId == id) {//若mastList中已经存在该行的异动记录
							hasFlag = true;
							if (name == "chsName") {
								if (mastList[i].chsName != val) {
									mastList[i].chsName = val;
									changeFlag = true;
								}
							}
						}
					}
					if (!hasFlag) {//若mastList中不存在當前行的異動記錄
						obj.sysId = id;
						if (name == "chsName") {
							obj.chsName = val;
							changeFlag = true;
						}
						mastList.push(obj);
					}
				} else {//若為初始狀態
					if (name == "chsName") {
						obj.sysId = id;
						obj.chsName = val;
						changeFlag = true;
						mastList.push(obj);
					}
				}
			}
			
			function saveDatList() {
				if (mastList.length == 0 && delList.length == 0) {
					alert("没有数据改动");
					return;
				}
				$.ajax({
					url : '<s:url value="/modules/sys/msg/editmore"></s:url>/',
					type : "POST",
					contentType : "application/json",
					data : JSON.stringify({
						mastList : mastList
					}),
					success : function() {
						alert("保存成功");
						mastList = new Array();
						delList = new Array();
					}
				});

			}
			
			
			