//获取导航菜单数组
var navArray = $(".row .nav .nav-link");
//console.log(navArray[0]);
//保存当前菜单的url
var curMenuUrl = $(navArray[0]).attr("url");
console.log("page init address:"+curMenuUrl);
//给导航菜单添加click事件
$(".row .nav .nav-link").click(function(){
	var url = $(this).attr("url");
	selectedMenu(url);
});
//选中当前菜单，根据url，通过ajax加载页面主页面
function selectedMenu(url,params){
	console.log("checked menu url:"+url,"params:"+params);
	navArray.each(function(index,value){
		//取消选中的状态
		$(value).removeClass("active");
		//判断当前url和参数url是否一致
		if($(value).attr("url") == url){
			curMenuUrl = url;
			//菜单状态变为选中
			$(value).addClass("active");
			//加载url返回html，赋值给class=tab-content的div
			$.get(url,params,function(res){
				//console.log(res);
				$(".tab-content").html(res);
			},"html")
		}
	})
}
//初始化当前页面
selectedMenu(curMenuUrl);
//刷新当前菜单的方法
//主页查询、删除、新增、修改时，需要刷新列表页，调用该方法。
function reload(params){
	console.log("reload:"+curMenuUrl);
	//selectedMenu(curMenuUrl,params);
	$.get(curMenuUrl,params,function(res){
		//console.log(res);
		$(".tab-content").html(res);
	},"html")
}
//打开新页面
function openPage(url){
	$.get(url,{},function(res){
		$(".tab-content").html(res);
	},"html")
}