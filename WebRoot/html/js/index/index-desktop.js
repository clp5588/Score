$(function(){
		dockInit();
	});
	function dockInit(){
		$.ajax({
			type:"POST",
			url:"/dock/docks.json",
			dataType:"json",
			async:false,
			success:function(data){
				desktopInit(data);
			}
		});
	}
	function desktopInit(data){
		var $desktop = $("#desktop");
		
		$.each(data,function(index,val){
			var $dock = $("<div class='dock btn'></div>").bind("click",function(){
				
			});
			var $dockIcon = $("<div id='dockicon' class='icon-2x dock-icon'>");
			var $dockName=$("<div id='dockname'></div>");
			$dockIcon.addClass(val.icon);
			$dockName.text(val.name);
			$dock.append($dockIcon).append($dockName);
			$desktop.append($("#dockul").append($("<li></li>").append($dock)));
		});
	}