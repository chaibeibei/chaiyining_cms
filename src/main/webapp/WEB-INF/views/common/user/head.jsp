<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="nav justify-content-start" style="background-color: #222;">
	<c:if test="${USER_SESSION_ID!=null && USER_SESSION_ID.headimg!=null }">
		<a class="nav-link navbar-brand" href="#">
			<img src="${USER_SESSION_ID.headimg }" width="30" height="30" alt="">
		</a>
	</c:if>
	
	<c:if test="${USER_SESSION_ID==null || USER_SESSION_ID.headimg==null }">
		<a class="nav-link navbar-brand" href="#">
			<img src="https://v4.bootcss.com/docs/4.3/assets/brand/bootstrap-solid.svg" width="30" height="30" alt="">
		</a>
	</c:if>
	
	<a class="nav-link" href="javascript:;" style="color: #fff;">${USER_SESSION_ID.nickname }</a> 
	<a class="nav-link" href="/" style="color: #fff;">网站首页</a> 
</nav>