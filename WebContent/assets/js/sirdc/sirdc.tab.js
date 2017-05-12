﻿$task_content_inner = null;
$mainiframe=null;
var tabwidth=118;
$loading=null;
$nav_wraper=$("#nav_wraper");
$(function () {
	$mainiframe=$("#mainiframe");
	$content=$("#content");
	$loading=$("#loading");
	var headerheight=120;
	$content.height($(window).height()-headerheight);
	
	
	$nav_wraper.height($(window).height()-headerheight);
	//$nav_wraper.niceScroll();
	$(window).resize(function(){
		$content.height($(window).height()-headerheight);
		 calcTaskitemsWidth();
	});
	$("#content iframe").load(function(){
    	$loading.hide();
    });
	
    $task_content_inner = $("#task-content-inner");
   

    $("#searchMenuKeyWord").keyup(function () {
        var wd = $(this).val();
        //searchedmenus
        var $tmp = $("<div></div>");
        if (wd != "") {
            $("#allmenus a:contains('" + wd + "')").each(
        function () {
            $clone = $(this).clone().prepend('<img src="/images/left/01/note.png">');

            $clone.wrapAll('<div class="menuitemsbig"></div>').parent().attr("onclick", $clone.attr("onclick")).appendTo($tmp);

        }
        );
            $("#searchedmenus").html($tmp.html());
            $("#searchedmenus").show();
            $("#allmenus").hide();
            $("#defaultstartmenu").hide();
            $("#allmenuslink .menu_item_linkbutton").html("返回");
            isAllDefault = false;
            // $("#searchedmenus").html($tmp).show();

        }

    });

    

    $("#appbox  li .delete").click(function (e) {
        $(this).parent().remove();
        return false;
    });

   

    ///

    $(".apps_container li").on("click", function () {
        var app = '<li><span class="delete" style="display:inline">×</span><img src="" class="icon"><a href="#" class="title"></a></li>';
        var $app = $(app);
        $app.attr("data-appname", $(this).attr("data-appname"));
        $app.attr("data-appid", $(this).attr("data-appid"));
        $app.attr("data-appurl", $(this).attr("data-appurl"));
        $app.find(".icon").attr("src", $(this).attr("data-icon"));
        $app.find(".title").html($(this).attr("data-appname"));
        $app.appendTo("#appbox");
        $("#appbox  li .delete").off("click");
        $("#appbox  li .delete").click(function () {
            $(this).parent().remove();
            return false;
        });
    });

    ///
    $("#tdshortcutsmor1").click(function () {
        $(".window").hide();
    });

    $(".task-item").on("click", function () {
        var appid = $(this).attr("app-id");
        var $app = $('#' + appid);
        showTopWindow($app);
    });

    $("#task-content-inner li").on("click", function () {
    	openapp($(this).attr("app-url"), $(this).attr("app-id"), $(this).attr("app-name"), $(this));
    	return false;
    });
    
    $("#task-content-inner li").on("dblclick", function () {
    	closeapp($(this));
    	return false;
    	
    });
    $(".macro-component-tabclose").on("click", function () {
    	closeapp($(this).parent());
        return false;
    });
    
    $("#task-next").click(function () {
        var marginleft = $task_content_inner.css("margin-left");
        marginleft = marginleft.replace("px", "");
        var width = $("#task-content-inner li").length * tabwidth;
        var content_width = $("#task-content").width();
        var lesswidth = content_width - width;
        
        if(width == 118) lesswidth = 0;
        
        marginleft = marginleft - tabwidth <= lesswidth ? lesswidth : marginleft - tabwidth;

        $task_content_inner.stop();
        $task_content_inner.animate({ "margin-left": marginleft + "px" }, 300, 'swing');
    });
    $("#task-pre").click(function () {
        var marginleft = $task_content_inner.css("margin-left");
        marginleft = parseInt(marginleft.replace("px", ""));
        marginleft = marginleft + tabwidth > 0 ? 0 : marginleft + tabwidth;
        // $task_content_inner.css("margin-left", marginleft + "px");
        $task_content_inner.stop();
        $task_content_inner.animate({ "margin-left": marginleft + "px" }, 300, 'swing');
    });
    
    $("#refresh_wrapper").click(function(){
    	var $current_iframe=$("#content iframe:visible");
    	$loading.show();
    	//$current_iframe.attr("src",$current_iframe.attr("src"));
    	$current_iframe[0].contentWindow.location.reload();
    	return false;
    });

    calcTaskitemsWidth();
});
function calcTaskitemsWidth() {
    var width = $("#task-content-inner li").length * tabwidth;
    $("#task-content-inner").width(width);
    if (($(document).width()- 12) < width) {
        //$("#task-content").width($(document).width() -tabwidth- 30 * 2);
        $("#task-content").width($(document).width()  - 30 * 2 - 21);
        $("#task-next,#task-pre").show();
    } else {
        $("#task-next,#task-pre").hide();
        $("#task-content").width(width);
    }
}

function close_current_app(){
	closeapp($("#task-content-inner .current"));
}

function closeapp($this){
	if(!$this.is(".noclose")){
		var appId = $this.attr("app-id");
		$this.prev().click();
    	$this.remove();
    	calcTaskitemsWidth();
    	$("#task-next").click();
    	$("#appiframe-" + appId).remove();
	}
	 
}





var task_item_tpl ='<li class="macro-component-tabitem">'+
'<span class="macro-tabs-item-text"></span>'+
'<a class="macro-component-tabclose" href="javascript:void(0)" title="点击关闭标签"><span></span><i class="ace-icon glyphicon glyphicon-remove" ></i></a>'+
'</li>';

var appiframe_tpl='<iframe style="width:100%;height: 100%;" frameborder="0" class="appiframe"></iframe>';

function openapp(url, appid, appname, selectObj) {
    var $app = $("#task-content-inner li[app-id='"+appid+"']");
    $("#task-content-inner .current").removeClass("current");
    if ($app.length == 0) {
        var task = $(task_item_tpl).attr("app-id", appid).attr("app-url",url).attr("app-name",appname).addClass("current");
        task.find(".macro-tabs-item-text").html(appname);
        $task_content_inner.append(task);
        $(".appiframe").hide();
        $loading.show();
        $appiframe=$(appiframe_tpl).attr("src",url).attr("id","appiframe-"+appid);
        $appiframe.appendTo("#content");
        $appiframe.load(function(){
        	$loading.hide();
        });
        calcTaskitemsWidth();
        
        
        $(".macro-component-tabclose").click(function(){
        	closeapp($(this).parent());
        });
        $(".task-item").click(function () {
            var appid = $(this).attr("app-id");
            var $app = $('#' + appid);
            showTopWindow($app);
        });

        $("#task-content-inner li").click(function () {
        	openapp($(this).attr("app-url"), $(this).attr("app-id"), $(this).attr("app-name"), $(this));
        	return false;
        });
        
        $("#task-content-inner li").dblclick(function () {
        	closeapp($(this));
        	return false;
        	
        });
        
        
    } else {
    	$app.addClass("current");
    	$(".appiframe").hide();
    	var $iframe=$("#appiframe-"+appid);
    	var src=$iframe.get(0).contentWindow.location.href;
    	src=src.substr(src.indexOf("://")+3);
    	/*if(src!=GV.HOST+url){
    		$loading.show();
    		$iframe.attr("src",url);
    		$appiframe.load(function(){
            	$loading.hide();
            });
    	}*/
    	$iframe.show();
    	$mainiframe.attr("src",url);
    }
    
    //
    var itemoffset= $("#task-content-inner li[app-id='"+appid+"']").index()* tabwidth;
    var width = $("#task-content-inner li").length * tabwidth;
   
    var content_width = $("#task-content").width();
    var offset=itemoffset+tabwidth-content_width;
    
    var lesswidth = content_width - width;
    
    var marginleft = $task_content_inner.css("margin-left");
   
    marginleft =parseInt( marginleft.replace("px", "") );
    var copymarginleft=marginleft;
    if(offset>0){
    	marginleft=marginleft>-offset?-offset:marginleft;
    }else{
    	marginleft=itemoffset+marginleft>=0?marginleft:-itemoffset;
    }
    
    if(-itemoffset==marginleft){
    	marginleft = marginleft + tabwidth > 0 ? 0 : marginleft + tabwidth;
    }
    
    //alert("cddd:"+(content_width-copymarginleft)+" dddd:"+(-itemoffset));
    if(content_width-copymarginleft-tabwidth==itemoffset){
    	marginleft = marginleft - tabwidth <= lesswidth ? lesswidth : marginleft - tabwidth;
    }
    
	$task_content_inner.animate({ "margin-left": marginleft + "px" }, 300, 'swing');
    
    
    
  
}
