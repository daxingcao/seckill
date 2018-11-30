<@cdx.html5 title="商品详情页">
	<div class="panel panel-success shop-panel-main">
		<div class="panel-heading text-center">
			<h3 class="panel-title">${shop.shopName}</h3>
		</div>
		<div class="panel-body text-center">
			<div>
				<img alt="" src="">
			</div>
			<div>
				<h3 id="shopOperate">
					<i class="glyphicon glyphicon-time" style="color: red;">&nbsp;&nbsp;</i>
					<span id="shopCountDown">${shop.startTime?string('yyyy-MM-dd HH:mm:ss')}</span>
				</h3>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title text-center" id="myModalLabel">
						请输入手机号
					</h4>
				</div>
				<div class="modal-body text-center">
					<i class="glyphicon glyphicon-earphone">&nbsp;:&nbsp;</i>
					<input class="form-control" name="mobile" type="text" placeholder="输入手机号" style="display: inline;width: 200px;" />
					<span id="errorMsg"></span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="saveMobile">
						保存
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 引入jQuery-cookie.js插件 -->
	<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
	<!-- 计时js插件 -->
	<script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
	<script src="/static/js/shop/shop.js"></script>
	<script type="text/javascript">
	$(function(){
		shop.detail.init({
			shopCode:"${shop.shopCode}",
			startTime:"${shop.startTime?datetime}",
			endTime:"${shop.endTime?datetime}"
		})
	})
	</script>
</@cdx.html5>