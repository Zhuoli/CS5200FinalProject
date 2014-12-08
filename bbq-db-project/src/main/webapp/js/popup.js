var popwindowFirst = true;
function popup(title,content,_width,_height,cssName){
	if(popwindowFirst == true){
		
		var divString = new String;
		divString = "<div id=\"divBg\" style=\"filter:alpha(opacity=0);opacity:0;\"></div>";
		divString += "<div id=\"popWindow\" class=\"popWindow\">";
		divString += "<div class=\"title\"><h4></h4><span style=\"display:none\">关闭</span></div>";
		divString += "<div class=\"content\">正文文字</div>";
		divString += "</div>";
		$("body").append(divString);
		popwindowFirst = false;
	}
	
	$("#popWindow .title span").click(function(){
		$(this).hide();
		$("#popWindow").animate({top:(-(_height=="auto"?300:parseInt(_height)))+"px"},0);
		$("#divBg").animate({opacity:"0"},0,function(){$(this).hide();$("#bt1").show();});
	});
	
	$("#popWindow .title h4").html(title);
	$("#popWindow .content").html(content);
	$("#divBg").show();
	$("#divBg").css({height:(document.body.scrollHeight+50) + "px"});
	//如果是ie6
	//$("#divBg").css({height:(document.body.scrollHeight+50) + "px",width:(document.body.scrollWidth+20)+"px"});
	$("#divBg").animate({opacity:"0.5"},0);
	$("#popWindow").css({left:(($(document).width())/2-(parseInt(_width)/2))+"px",top:($(document).scrollTop()-(_height=="auto"?300:parseInt(_height)))+"px",width:parseInt(_width),height:parseInt(_height)});
	
	$("#popWindow .content").height(_height-$("#popWindow .title").height() - 47);
	
	$("#popWindow").animate({top:($(document).scrollTop() + 50) + "px"},0,function(){
		$("#popWindow .title span").css({display:"block"});
	});
}
