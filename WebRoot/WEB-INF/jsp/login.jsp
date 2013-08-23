<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/include.jsp"%>
<%@ include file="../include/main.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="/html/css/login/login.css">
<script type="text/javascript" src="/html/js/login/login.js"></script>
</head>
<body>
	<div class="container">
		<h2 class="form-signin-heading" align="center">成绩管理系统</h2>
		<form id="form-signin" name="form-signin" action="/login"
			method="post">
			<div class="div-center">
				<div class="input-prepend control-group">
					<span class="add-on"><i class="icon-envelope"></i> </span> <input
						class="span2.5" type="text" placeholder="邮箱/Email" name="email"
						id="email" />
				</div>
				<div class="input-prepend control-group">
					<span class="add-on"><i class="icon-key"></i> </span> <input
						class="span2.5" type="password" placeholder="密码/Password"
						name="password" id="password"/>
				</div>
			</div>
			<button class="btn btn-small btn-warning" type="button" name="lost"
				id="lost">忘记密码?</button>
			<button class="btn btn-small btn-success span1" style="float:right;"
				type="button" name="login" id="login">登陆</button>
		</form>
		<form id="form-lostpassword">
			<div align="center">
				<div class="alert alert-success" align="center">
					<span class="add-on"><i class="icon-exclamation-sign"></i></span>
					&nbsp;我们将会以邮件的方式帮助您恢复密码&nbsp;!
				</div>
				<div class="input-prepend control-group">
					<span class="add-on"><i class="icon-envelope"></i> </span> <input
						class="span2.5" type="text" placeholder="邮箱/Email"
						name="lostemail" id="lostemail"/>
				</div>
			</div>
			<button class="btn btn-small btn-warning span1" type="button" name="back"
				id="back">返回</button>
			<button class="btn btn-small btn-success span1" type="button"
				style="float:right;" name="send" id="send">发送</button>
		</form>
	</div>
</body>
</html>
