<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cms后台登录</title>
<link href="/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/public/css/cms.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="col-4 offset-4 loginForm">
			<h3>欢迎使用CMS系统</h3>
			<div class="alert alert-danger" role="alert" style="display: none">
			</div>
			<form id="loginForm">
			  <div class="form-group">
			    <input type="text" id="username" name="username" class="form-control" placeholder="请输入用户名...">
			    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
			  </div>
			  <div class="form-group">
			    <!-- <label for="exampleInputPassword1">密码</label> -->
			    <input type="password" id="password" name="password" class="form-control"  placeholder="请输入密码...">
			  </div>
			  <button type="button" class="btn btn-primary" onclick="login();">登录</button>
			  <!-- <label for="exampleInputPassword1">没有帐号，去<a href="/">注册</a></label> -->
			</form>
		</div>
	</div>
<script type="text/javascript" src="/public/js/jquery.min.1.12.4.js"></script>
<script type="text/javascript" src="/public/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function login(){
		//空判断
		var username = $("#username").val();
		var password = $("#password").val();
		if(username==null || password==""){
			$(".alert").html("请输入用户名和密码");
			$(".alert").show();
			return;
		}
		$(".alert").hide();
		//后台验证
		var formData = $("#loginForm").serialize();
		$.post("/admin/user/login",formData,function(res){
			if(res.result){
				//验证通过跳转到后台首页
				location.href="/admin/home";
			}else{
				//否则提示错误信息
				$(".alert").html(res.message);
				$(".alert").show();
			}
		});
	}
</script>
</body>
</html>