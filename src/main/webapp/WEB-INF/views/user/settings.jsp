<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form>
	<input type="hidden" name="id" value="${user.id }">
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">我的昵称</label>
		<div class="col-sm-10">
			<input type="text" name="nickname" value="${user.nickname }" class="form-control" placeholder="蝈蝈">
		</div>
	</div>
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">我的头像</label>
		<div class="col-sm-10">
			<jsp:include page="../common/uploadImg.jsp">
				<jsp:param value="headimg" name="feildName"/>
				<jsp:param value="${user.headimg }" name="feildValue"/>
			</jsp:include>
		</div>
	</div>	
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">我的生日</label>
		<div class="col-sm-3">
			<input type="date" name="birthday" value="${user.birthdayStr }">
		</div>
	</div>
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">我的性别</label>
		<div class="col-sm-3">
			   <div class="form-check">
				  <input class="form-check-input" type="radio" name="gender" value="1"  <c:if test="${user.gender==1}">checked</c:if>>
				  <label class="form-check-label" for="exampleRadios1">男</label>
			   </div>
			   <div class="form-check">
				  <input class="form-check-input" type="radio" name="gender" value="2" <c:if test="${user.gender==2}">checked</c:if>>
				  <label class="form-check-label" for="exampleRadios1">女</label>
			   </div>
		</div>
	</div>
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">博客网址</label>
		<div class="col-sm-10">
			<input type="text" name="url" value="${user.url }" class="form-control" placeholder="https://www.csdn.net/nav/java">
		</div>
	</div>
	<div class="form-group row">
		<div class="col-sm-10">
			<button type="button" class="btn btn-primary" onclick="save()">保存设置</button>
		</div>
	</div>
	<div class="alert alert-danger" role="alert" style="display: none"></div>
</form>
<script type="text/javascript">
	$("#gender").val("${gender}");
	function save() {
		$.post("/user/settings",$("form").serialize(),function(res){
			if(res.result){
				$(".alert").html("设置成功");
				$(".alert").show();
			}else{
				$(".alert").html("保存失败");
				$(".alert").show();
			}
		});
	}
</script>
