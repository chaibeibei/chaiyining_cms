<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/public/css/index.css" rel="stylesheet">
<script type="text/javascript">
	var channelId = '${channelId}';
	var cateId = '${cateId}';
</script>
<title>前台首页</title>
</head>
<body>
	<nav class="nav justify-content-start" style="background-color: #222;">
		<c:if test="${USER_SESSION_ID!=null && USER_SESSION_ID.headimg!=null }">
			<a class="nav-link navbar-brand" href="#">
				<img src="${USER_SESSION_ID.headimg }" width="30" height="30" alt="">
			</a>
		</c:if>
		<c:if test="${USER_SESSION_ID==null || USER_SESSION_ID.headimg==null  }">
			<a class="nav-link navbar-brand" href="#">
				<img src="https://v4.bootcss.com/docs/4.3/assets/brand/bootstrap-solid.svg" width="30" height="30" alt="">
			</a>
		</c:if>
		<c:if test="${USER_SESSION_ID!=null }">
			<a class="nav-link" href="/user/center">发文</a> 
			<a class="nav-link" href="/user/center">个人中心</a> 
			<a class="nav-link" href="javascript:;">${USER_SESSION_ID.nickname }</a>
			<a class="nav-link" href="/user/logout">退出</a>
		</c:if>
		<c:if test="${USER_SESSION_ID==null }">
			<a class="nav-link" href="/user/login">登录</a>
		</c:if>
	</nav>
	<div class="container-fluid">
		<div class="row offset-1" style="margin-top: 15px;">
			<div class="col-1" style="padding-right:5px;padding-left:5px;">
				<!-- 左侧导航 -->
				<ul class="nav flex-column">
				   <li class="nav-item">
				    <a class="nav-link <c:if test="${channelId==null }">select</c:if>" href="/">热点</a>
				  </li>
				  <c:forEach items="${channelList }" var="item">
					  <li class="nav-item">
					    <a class="nav-link <c:if test="${channelId==item.id }">select</c:if>" href="/${item.id }/0/1.html">${item.name }</a>
					  </li>
				  </c:forEach>
				</ul>
			</div>111
			<div class="col-6">
				<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
				  <div class="carousel-inner">
				  
				    <!-- 搜索框 -->
				<form action="/articleSearch" method="get">
				   <div class="input-group mb-3">
					<input type="text" name="name" value="${name}" class="form-control"
						placeholder="请输入要搜索的内容"
						aria-label="Recipient's username" aria-describedby="button-addon2">
					<div class="input-group-append">
						<button class="btn btn-outline-secondary" id="button-addon2">搜索</button>
					</div>
				</div>
				</form>
					  <c:forEach items="${slideList }" var="item" varStatus="s">
						  <div class="carousel-item <c:if test="${s.index==0 }">active</c:if>">
						      <a href="${item.url }" target="_blank"><img src="${item.picture }" style="height: 386px;" class="d-block w-100" alt="${item.title }"></a>
						  </div>
					  </c:forEach>
				  </div>
				  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="sr-only">Previous</span>
				  </a>
				  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="sr-only">Next</span>
				  </a>
				</div>
								
				<c:if test="${cateList.size()>0 }">
					<ul class="nav nav-tabs">
						<li class="nav-item ">
						    <a class="nav-link <c:if test="${cateId==0 }">active</c:if>" href="/${channelId }/0/1.html">全部</a>
						</li>
					  <c:forEach items="${cateList }" var="item">
						  <li class="nav-item">
						    <a class="nav-link <c:if test="${cateId==item.id }">active</c:if>" href="/${channelId }/${item.id }/1.html">${item.name }</a>
						  </li>
					  </c:forEach>
					</ul>
				</c:if>
				<div style="margin-top: 18px;">
					<c:forEach items="${pageInfo.list }" var="item">
					  <div class="media">
						  <img src="${item.picture }" class="mr-3" alt="...">
						  <div class="media-body">
						    <h4 class="mt-1">
						    	<a href="/article/${item.id}.html" target="_blank">${item.title }</a>
						    </h4>
						    <p style="color: #999;">${item.nickname }  <fmt:formatDate value="${item.created }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
						  </div>
						</div>
				  </c:forEach>
				</div>
				<div style="text-align: center;">
					<jsp:include page="common/page.jsp"></jsp:include>
				</div>
			</div>
			<div class="col-3">
			  <div class="row form-row">
			    <div class="col">
			      <input type="text" class="form-control" id="search" placeholder="请输入关键词">
			    </div>
			  </div>
				<div class="right">
					<div>最新文章</div>
					<ul class="list-unstyled">
					  <c:forEach items="${newArticleList }" var="item">
						  <li class="media">
						    <a href="/article/${item.id }.html">
						    	<img style="width: 64px;height: 64px;" src="${item.picture }" class="mr-3" alt="...">
						    </a>
						    <div class="media-body">
						      <h5 class="mt-0 mb-1"><a href="/article/${item.id }.html">${item.title }</a></h5>
						    </div>
						  </li>
					  </c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<p class="nav justify-content-center" style="background-color: #222;">
		<a class="nav-link" href="javascript:;">友情链接</a>
	</p>
	<div class="justify-content-center" style="margin-bottom: 200px;text-align: center;">
			<a href="#" style="padding-right: 36px;">拉勾网</a>
			<a href="#" style="padding-right: 36px;">猎聘网</a>
			<a href="#" style="padding-right: 36px;">中华英才网</a>
			<a href="#" style="padding-right: 36px;">51Job</a>
	</div>
	<script type="text/javascript" src="/public/js/jquery.min.1.12.4.js"></script>
	<script type="text/javascript" src="/public/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function gotoPage(pageNum){
			if(channelId==''){
				window.location.href="/hot/"+pageNum+".html"
			}else{
				window.location.href="/"+channelId+"/"+cateId+"/"+pageNum+".html"
			}
		}
		$("#search").bind("keyup",function(e){
			if(e.keyCode=='13'){
				console.log($(this).val());
			}
		})
	</script>
</body>
</html>