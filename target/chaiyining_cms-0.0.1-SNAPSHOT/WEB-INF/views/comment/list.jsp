<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row" id="commentList">
	<c:forEach items="${pageInfo.list }" var="item">
		<div class="col-10">
		  <div style="margin-top: 10px;margin-bottom: 10px;color: #777;">
				<span><img src="${item.headimg }" style="height:28;border-radius: 100px;" alt="">${item.nickname }</span> 
				<span>${item.created }</span>
		  </div>
		  <div style="margin-top: 10px;margin-bottom: 10px;">
				${item.content }
		  </div>
		 </div>
	</c:forEach>
	<jsp:include page="../common/page.jsp"></jsp:include>
</div>
<script type="text/javascript">
	function gotoPage(pageNum) {
		$.get("/comment/list",{articleId:articleId,pageNum:pageNum},function(res){
			$("#commentList").html(res);
		},"html")
	}
</script>
