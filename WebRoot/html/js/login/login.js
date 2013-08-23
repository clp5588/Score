$(function() {
		$._messengerDefaults = {
			extraClasses : 'messenger-fixed messenger-theme-air messenger-on-bottom'
		};
		$("#lost").click(function() {
			$("#form-signin").hide("blind", {}, 500);
			$("#form-lostpassword").show("blind", {}, 500);
		});
		$("#back").click(function() {
			$("#form-signin").show("blind", {}, 500);
			$("#form-lostpassword").hide("blind", {}, 500);
		});
		$("#form-lostpassword").validate({
			rules : {
				lostemail : {
					required : true,
					email : true
				}
			},
			messages : {
				lostemail : {
					required : "请输入邮箱!",
					email : "请输入正确的邮箱!"
				}
			},
			highlight : function(element, errorClass, validClass) {
				$(element).parents('.control-group').addClass('error');
			},
			unhighlight : function(element, errorClass, validClass) {
				$(element).parents('.control-group').removeClass('error');
				$(element).parents('.control-group').addClass('success');
			},
			errorPlacement : function(error, element) {
				var id = $(element).attr("id");
				$.globalMessenger().post({
					message : $(error).html(),
					type : "error",
					id : id
				});
			}
		});
		$("#form-signin").validate({
			rules : {
				email : {
					required : true,
					email : true
				},
				password : {
					required : true,
					minlength : 5
				}
			},
			messages : {
				email : {
					required : "请输入邮箱!",
					email : "请输入正确的邮箱!"
				},
				password : {
					required : "请输入密码!",
					minlength : "密码长度不能少于{0}个字符!"
				}
			},
			highlight : function(element, errorClass, validClass) {
				$(element).parents('.control-group').addClass('error');
			},
			unhighlight : function(element, errorClass, validClass) {
				$(element).parents('.control-group').removeClass('error');
				$(element).parents('.control-group').addClass('success');
			},
			errorPlacement : function(error, element) {
				var id = $(element).attr("id");
				$.globalMessenger().post({
					message : $(error).html(),
					type : "error",
					id : id
				});
			}
		});
		$("#login").click(function() {
			if ($("#form-signin").valid()) {
				$("#login").attr("disabled", true);
				$.globalMessenger().post({
					message : "正在登陆,请稍后...",
					type : "success",
					id : "logining"
				});
				$.ajax({
					type : "POST",
					url : "/login.json",
					data : {
						username : $("#email").val(),
						password : $("#password").val()
					},
					success : function(data) {
						if (data.success == "false") {
							$.globalMessenger().post({
								message : data.msg,
								type : "error",
								id : "logining"
							});
							$("#login").attr("disabled", false);
							$("#password").val("");
						} else {
							window.location.replace(data.url);
						}
					},
					dataType : "json",
					timeout : 5000,
					error : function(jqXHR, textStatus, errorThrown) {
						var msg = "登陆失败,请稍后重试...";
						if (textStatus == "timeout") {
							msg = "登陆超时,请稍后重试...";
						}
						$.globalMessenger().post({
							message : msg,
							type : "error",
							id : "logining"
						});
						$("#login").attr("disabled", false);
						$("#password").val("");
					}
				});
			}
		});
		$("#send").click(function() {
			if ($("#form-lostpassword").valid()) {
				$.post("/sendemail", {
					username : $("#lostemail").val()
				}, function(data) {
					alert(data.success);
				}, "json");
			}
		});
	});