package com.caodaxing.shopseckill.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

public class HttpRequestUtil {

	private static Builder builder;

	public static void toPost(String url, Map<String, Object> params) {
		//TODO
	}

	public static void toGet() {
		//TODO
	}

	public static Builder createBuilder() {
		builder = new Builder();
		return builder;
	}

	protected static class Builder {

		private List<Header> headers;
		private List<Cookie> cookiese;

		public Builder setHeader(String key, String value) {
			if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
				throw new RuntimeException("key or value is null!");
			}
			return this.setHeader(new Header(key, value));
		}

		public Builder setHeader(Header header) {
			if (this.headers == null) {
				this.headers = Lists.newArrayList();
			}
			this.headers.add(header);
			return this;
		}

		public Builder setCookie(String name, String value) {
			if (StringUtils.isBlank(name) || StringUtils.isBlank(value)) {
				throw new RuntimeException("key or value is null!");
			}
			return this.setCookie(new Cookie(name, value));
		}

		public Builder setCookie(Cookie cookie) {
			if (this.cookiese == null) {
				this.cookiese = Lists.newArrayList();
			}
			this.cookiese.add(cookie);
			return this;
		}

		public List<Header> getHeaders() {
			return headers;
		}

		public void setHeaders(List<Header> headers) {
			this.headers = headers;
		}

		public List<Cookie> getCookiese() {
			return cookiese;
		}

		public void setCookiese(List<Cookie> cookiese) {
			this.cookiese = cookiese;
		}

	}

	static class Cookie {

		private String name;
		private String value;

		public Cookie() {
		}

		public Cookie(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

	static class Header {

		private String key;
		private String value;

		public Header() {
		}

		public Header(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
