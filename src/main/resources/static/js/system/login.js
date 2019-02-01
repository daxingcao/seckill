var login={
	URL:{
		check_login:"/system/checkLogin.do"
	},
	init:function(){
		$("#loginSubmit").validate({
            /* debug:true, */
            rules:{
                username:{
                    required: true
                },
                password:{
                    required:true
                }
            },
            messages:{
                username:{
                    required:"用户名不能为空",
                    email:"请输入正确的邮箱格式！"
                },
                password:"密码不能为空！"
            }
        })
        //自定义正则验证方法
		$.validator.addMethod("format",function(value,element,params){
		var check = /^[1-9]+$/;
		return this.optional(element) || (check.test(value));
		},"只能输入数字!");
		$("#check-login").click(function(){
			login.checkLogin();
		});
	},
	validData:function(){
		var valid = $("#loginSubmit").valid();
		return valid;
	},
	checkLogin:function(){
		if(login.validData()){
			 var formData = new FormData(document.getElementById("loginSubmit"));
			 $.ajax({
				 url:login.URL.check_login,
				 data:formData,
				 processData:false,
				 contentType:false,
				 type:"post",
				 success:function(data){
					 if(data.success){
						 window.location.href=data.result;
					 }else {
						 let error = $("#error-info");
                         error.text(data.msg);
                         error.attr("class","show");
                         error.fadeIn(500);
                         error.fadeOut(3000,function(){
                             error.attr("class","hidden");
						 });
					 }
				 }
			 })
		}
	}
}