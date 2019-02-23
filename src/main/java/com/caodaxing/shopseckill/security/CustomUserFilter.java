package com.caodaxing.shopseckill.security;

import com.caodaxing.shopseckill.utils.SessionUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author daxing.cao
 */
public class CustomUserFilter extends UserFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (this.isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = this.getSubject(request, response);
            if(subject.getPrincipal() != null && SessionUtil.getUserInfo() != null){
                return true;
            }
            return false;
        }
    }
}
