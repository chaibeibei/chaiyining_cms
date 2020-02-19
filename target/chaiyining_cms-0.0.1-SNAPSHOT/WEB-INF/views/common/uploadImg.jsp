<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String feildName = request.getParameter("feildName");
	String feildValue = request.getParameter("feildValue");
	feildValue = (feildValue==null || "".equals(feildValue))?"/public/img/upload.jpg":feildValue;
%>
<div class="imgDive">
	<img src="<%=feildValue %>" class="img-rounded" width="80px;" id="viewImg">
	<div style="display: none;" id="imageuploadDiv"	name="imageuploadDiv">
		<input type="file" id="fileName" name="fileName" onchange="ajaxFileUpload(this,'${feild}');"><br> 
		<input	type="hidden" id="<%=feildName %>" name="<%=feildName %>" value="<%=feildValue=="/public/img/upload.jpg"?"":feildValue %>" isImg=true>
	</div>
</div>
<script type="text/javascript">
	$(".imgDive").mouseover(function () {
		$(this).find("#imageuploadDiv").css("display","block");
	});
	$(".imgDive").mouseleave(function () {
	    $(this).find("#imageuploadDiv").css("display","none");
	});
	//更新图片
	function changeImgView(obj) {
		$('#img_'+obj.id).attr("src",obj.value); 
	}
	//文件上传
	function ajaxFileUpload(obj,target) {
	    $.ajaxFileUpload({
	        url: '/file/upfile?dir=image', 
	        type: 'post',
	        secureuri: false, //一般设置为false
	        fileElementId: $(obj).attr("id"),
	        dataType: 'text',
	        success: function(responseText, status){
	        	responseText = responseText.replace(/<.*?>/ig,"");
	        	var data = JSON.parse(responseText);
	        	console.log(data);
	        	if(data.error==0){
	        		var imgUrl = data.url;
	        		console.log(data.url)
	            	$("#<%=feildName%>").val(imgUrl);
	                $('#viewImg').attr("src",imgUrl);
	        	}
	        },
	        error: function(data, status, e){
	        	alert("textStatus:"+status+";errorThrown:"+e);
				//window.location.href = basePath;
	        }
	    });
	};
</script>