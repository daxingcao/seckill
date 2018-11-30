<@cdx.html5 title="欢迎来到登录页">
	<div id="loginForm" class="login_submit">
		<div class="login-icon">
			<img style="width: 100px;height: 100px;border-radius: 50px;box-shadow: #666 0px 0px 20px;" src="/static/image/tubiao.png">
		</div>
		<form id="loginSubmit" class="form-horizontal form-login">
			<div class="form-group">
			    <div class="col-sm-12" style="position: relative;">
			    	<i class="glyphicon glyphicon-user" style="position: absolute;top: 10px;left: 22px;"></i>
			    	<input type="text" class="form-control" name="username" placeholder="Email" style="padding-left: 30px;">
			    </div>
			</div>
			<div class="form-group">
			    <div class="col-sm-12" style="position: relative;">
			    	<i class="glyphicon glyphicon-lock" style="position: absolute;top: 10px;left: 22px;"></i>
			    	<input type="password" class="form-control" name="password" placeholder="Password" style="padding-left: 30px;">
			    </div>
			</div>
			<p id="error-info" class="hidden" style="color: red;">错误信息:</p>
		</form>
		<div class="button-submit">
			<button id="check-login" class="btn btn-success">登录</button><br>
			<button class="btn btn-link" style="padding-top: 10px;">没有账号?立即注册</button>
		</div>
	</div>
</@cdx.html5>
<script src="/static/js/system/login.js"></script>
<script type="text/javascript">
$(function(){        
	login.init();
})
</script>