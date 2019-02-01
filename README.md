# seckill-shop
### 1 oauth2授权
&emsp;当外部调用应用对外的http接口时,需进行oauth2授权,通过调用授权接口获得access_token,在每次http请求中<br>
带上access_token进行权限验证

#### 1.1 授权接口

**接口描述:** 获取认证token,用于访问资源URL进行权限认证,token有限时间默认 10 minutes;
另外还会返回一个refresh_token,用于在token过期后进行更新<br>
**请求方式:** POST<br>
**接口地址:** http://localhost:8080/oauth/authorization<br>
**接口参数:**

参数名称|参数类型|是否必须|备注
:--:|:--:|:--:|:--:
client_id|String|是|应用ID
client_secre|String|是|应用安全key
grant_type|String|是|参数值:client_credentials,拼接于URL后面

**java例子**
```java
public String getAccessToken(String client_id, String client_secret)throws Exception{
	CloseableHttpClient httpClient = HttpClients.createDefault();
    String newUrl = url + "?grant_type=client_credentials";
    HttpPost post = new HttpPost(newUrl);
    //增加请求配置
	RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(2000)
                    .setSocketTimeout(15000).build();
	post.setConfig(config);
    //拼接验证信息
    String authInfo = clientId + ":" + clientSecret;
    //进行base64编码
    String encodeInfo = Base64.encodeBase64String(authInfo.getBytes());
    // 进行授权
    // 注意:Basic后面有一个空格,不然识别不了clientId和clientSecret,会报错
    post.setHeader("Authorization", "Basic " + encodeInfo);
    //设置参数
    List<NameValuePair> entryList = new ArrayList<>();
    Set<Entry<String, Object>> entrys = param.entrySet();
    for (Entry<String, Object> entry : entrys) {
        entryList.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
    }
    post.setEntity(new UrlEncodedFormEntity(entryList, "utf-8"));
    CloseableHttpResponse response = httpClient.execute(post);
    String returnData = EntityUtils.toString(response.getEntity(),"utf-8");
    return returnData;
}
```
**返回参数**

```json
{
	"access_token":"2400eebdb43a64a2507aa537a06af4ab",
	"refresh_token":"7986417b3e209ff493396c5ec2836593"
}
```

**错误**
```json
{
	"error":"错误信息"
}
```
---
#### 1.2 刷新token接口
**接口描述:** 当access_token过期后,通过refresh_token可以对access_token进行更新<br>
**请求方式:** GET
**接口地址:** http://localhost:8080/oauth/refreshToken<br>
**接口参数:**

参数名称|参数类型|是否必须|备注
:--:|:--:|:--:|:--:
client_id|String|是|应用ID
refresh_token|String|是|刷新token

**java例子**
```java
public String getAccessToken(String client_id, String refresh_token)throws Exception{
	CloseableHttpClient httpClient = HttpClients.createDefault();
    String newUrl = url + "?client_id="+client_id+"&refresh_token="+refresh_token;
    HttpGet get = new HttpGet(newUrl);
    //增加请求配置
	RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(2000)
                    .setSocketTimeout(15000).build();
	get.setConfig(config);
    CloseableHttpResponse response = httpClient.execute(get);
    String returnData = EntityUtils.toString(response.getEntity(),"utf-8");
    return returnData;
}
```
**返回参数**

```json
{
	"access_token":"2400eebdb43a64a2507aa537a06af4ab"
}
```

**错误**
```json
{
	"error":"错误信息"
}
```

---
### 2 seckill
!!!
#### 2.1 查询商品信息接口
!!!
#### 2.2 查询商品列表接口
!!!
#### 2.3 获取秒杀商品token接口
!!!
#### 2.4 秒杀商品接口
!!!
