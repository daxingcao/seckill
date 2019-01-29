package com.caodaxing.shopseckill.security;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		String loginUrl = "/message/login.jhtml";
        WebUtils.issueRedirect(request, response, loginUrl);
	}

	@Override
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String successUrl = null;
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
        if (savedRequest != null) {
            successUrl = savedRequest.getRequestUrl();
            if(successUrl == null || "".equals(successUrl)) {
                successUrl = getSuccessUrl();
            }
        }

        if (successUrl == null) {
            throw new IllegalStateException("Success URL not available via saved request or via the " +
                    "successUrlFallback method parameter. One of these must be non-null for " +
                    "issueSuccessRedirect() to work.");
        }
		WebUtils.issueRedirect(request, response, successUrl,null,true);
	}
	
	

}
