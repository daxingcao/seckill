package com.caodaxing.shopseckill.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Lists;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpRequestUtil {

    private volatile static Builder builder;
    private static CloseableHttpClient httpClient;
    private static CloseableHttpResponse response;

    private static void initialize(HttpRequestBase request) throws UnsupportedEncodingException {
        request.setConfig(getConfig());
        if (builder == null) {
            return;
        }
        if (builder.headers != null) {
            request.setHeaders(builder.getHeaders());
        }
        if (request instanceof HttpPost) {
            HttpPost post = (HttpPost) request;
            if (builder.getEntities() != null) {
                post.setEntity(new UrlEncodedFormEntity(builder.getEntities(), "utf-8"));
            }
        }
    }

    private static String jointUrl(String originalUrl){
        if(builder == null || builder.getParameters() == null)
            return originalUrl;
        StringBuffer newUrl = new StringBuffer(originalUrl);
        for (Parameter parameter : builder.getParameters()) {
            if(newUrl.indexOf("?") <= 0){
                newUrl.append("?").append(parameter.getName()).append("=").append(parameter.getValue());
            }else {
                newUrl.append("&").append(parameter.getName()).append(parameter.getValue());
            }
        }
        return newUrl.toString();
    }

    private static HttpEntity getResponse(HttpRequestBase request) throws IOException {
        httpClient = HttpClients.createDefault();
        response = httpClient.execute(request);
        if(response != null){
            return response.getEntity();
        }
        return null;
    }

    private static RequestConfig getConfig() {
        //增加请求配置
        return RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(2000)
                .setSocketTimeout(15000).build();
    }

    private static void clear() {
        if(builder != null)
            builder.clear();
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static String toPost(String url) {
        String result = null;
        try {
            HttpPost post = new HttpPost(jointUrl(url));
            initialize(post);
            HttpEntity entity = getResponse(post);
            result = entity == null ? null : EntityUtils.toString(entity,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            clear();
        }
        return result;
    }

    public static String toGet(String url) {
        String result = null;
        try {
            HttpGet get = new HttpGet(jointUrl(url));
            initialize(get);
            HttpEntity entity = getResponse(get);
            result = entity == null ? null : EntityUtils.toString(entity,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            clear();
        }
        return result;
    }

    static Builder createBuilder() {
        builder = new Builder();
        return builder;
    }

    protected static class Builder {

        private List<BasicHeader> headers;
        private List<Parameter> parameters;
        private List<BasicNameValuePair> entities;

        private void clear() {
            this.headers = null;
            this.parameters = null;
            this.entities = null;
        }

        Builder setHeader(String key, String value) {
            if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
                throw new RuntimeException("key or value is null!");
            }
            return this.setHeader(new BasicHeader(key, value));
        }

        Builder setHeader(BasicHeader header) {
            if (this.headers == null) {
                this.headers = Lists.newArrayList();
            }
            this.headers.add(header);
            return this;
        }

        public void setHeaders(List<BasicHeader> headers) {
            this.headers = headers;
        }

        Builder setEntity(String name, String value) {
            if (StringUtils.isEmpty(name) || StringUtils.isEmpty(value)) {
                throw new RuntimeException("name or value is null!");
            }
            return this.setEntity(new BasicNameValuePair(name, value));
        }

        Builder setEntity(BasicNameValuePair entity) {
            if (this.headers == null) {
                this.headers = Lists.newArrayList();
            }
            this.entities.add(entity);
            return this;
        }

        Builder setParameter(String key, String value) {
            if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
                throw new RuntimeException("key or value is null!");
            }
            return this.setParameter(new Parameter(key, value));
        }

        Builder setParameter(Parameter parameter) {
            if (this.parameters == null) {
                this.parameters = Lists.newArrayList();
            }
            this.parameters.add(parameter);
            return this;
        }

        BasicHeader[] getHeaders() {
            BasicHeader[] basicHeaders = new BasicHeader[this.headers.size()];
            this.headers.toArray(basicHeaders);
            return basicHeaders;
        }

        List<BasicNameValuePair> getEntities() {
            return this.entities;
        }

        List<Parameter> getParameters() {
            return this.parameters;
        }

    }

    static class Parameter extends BasicNameValuePair {
        Parameter(String key, String value) {
            super(key, value);
        }
    }
}
