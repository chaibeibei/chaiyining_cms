<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cms后台登录</title>
<link href="/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/public/css/cms.css" rel="stylesheet">
<link href="/public/css/index.css" rel="stylesheet">
</head>
<body>
	<nav class="nav justify-content-start" style="background-color: #222;">
		<a class="nav-link navbar-brand" href="#">
			<img src="https://v4.bootcss.com/docs/4.3/assets/brand/bootstrap-solid.svg" width="30" height="30" alt="">
		</a>
		<a class="nav-link" href="/">网站首页</a> 
	</nav>
	<div class="container-fluid">
		<div class="col-4 offset-4 loginForm">
			<h3>帐号注册</h3>
			<div class="alert alert-danger" role="alert" style="display: none">
			</div>
			<form id="registerForm">
			  <div class="form-group">
			    <input type="text" id="username" name="username" class="form-control" placeholder="请输入用户名...">
			  </div>
			  <div class="form-group">
			    <input type="text" id="nickname" name="nickname" class="form-control" placeholder="请输入昵称...">
			  </div>
			  <hr>
			  <div class="form-group">
			    <input type="password" id="password" name="password" class="form-control"  placeholder="请输入密码...">
			  </div>
			  <div class="form-group">
			    <input type="password" id="rePassword" name="rePassword" class="form-control"  placeholder="请输入确认密码...">
			  </div>
			  <button type="button" class="btn btn-primary" onclick="login();">注册</button>
			  <label for="exampleInputPassword1">已有帐号，去<a href="/user/login">登录</a></label>
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
		var rePassword = $("#rePassword").val();
		var nickname = $("#nickname").val();
		if(username==null || password==""){
			$(".alert").html("请输入用户名和密码");
			$(".alert").show();
			return;
		}
		if(nickname==null || nickname==""){
			$(".alert").html("请输入用户名昵称");
			$(".alert").show();
			return;
		}
		
		if(password!=rePassword){
			$(".alert").html("密码输入不一致");
			$(".alert").show();
			return;
		}
		
		if(password.length<8){
			$(".alert").html("密码长度不的少于8位");
			$(".alert").show();
			return;
		}
		
		$(".alert").hide();
		//后台验证
		var formData = $("#registerForm").serialize();
		$.post("/user/register",formData,function(res){
			if(res.result){
				//验证通过跳转到后台首页
				location.href="/user/login";
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