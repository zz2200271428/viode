package com.znsd.instapundit.bxwapp.config;

import com.znsd.instapundit.bean.AppUserBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AppUserBean user = (AppUserBean) request.getSession().getAttribute("user");
        if (user != null)
            return true;
        else {
            response.sendRedirect("/login.html");
            return false;
        }
    }
}
