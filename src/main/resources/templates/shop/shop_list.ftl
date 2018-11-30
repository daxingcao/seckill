<@cdx.html5 title="商品列表">
	<div class="container">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>商品名称</th>
					<th>商品编号</th>
					<th>商品数量</th>
					<th>是否秒杀</th>
					<th>商品价格</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<#list shopList as item>
					<tr>
						<td>${item.shopName}</td>
						<td>${item.shopCode}</td>
						<td>${item.shopNumber}</td>
						<td>
							<#if item.isSeckill = 0>
								<p>不参与秒杀</p>
							<#else>
								<p>参与秒杀</p>
							</#if>
						</td>
						<td>${item.shopPrice}</td>
						<td><a href="/seckill/${item.shopCode}/detail">详情</a></td>
					</tr>
				</#list>
			</tbody>
		</table>
	</div>
</@cdx.html5>