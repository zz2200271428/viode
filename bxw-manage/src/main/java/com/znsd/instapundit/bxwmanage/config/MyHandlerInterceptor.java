package com.znsd.instapundit.bxwmanage.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.MenuBean;
import com.znsd.instapundit.bean.UserBean;
import com.znsd.instapundit.cache.tactics.CacheTactics;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.service.LoginService;
import com.znsd.instapundit.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class MyHandlerInterceptor implements HandlerInterceptor {

    @Reference
    private MenuService menuService;

    @Autowired
    private RedisServer redisServer;

    private final String className = "MyHandlerInterceptor";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Object user = request.getSession().getAttribute("userSession");
        if (user != null) {
            //已经登录
            if (request.getRequestURI().equals("/index") || request.getRequestURI().equals("/quit")) {
                return true;
            }
//            String key = CacheTactics.getKey(CacheTactics.MENU_PREFIX, className, "preHandle", user);
//            List<MenuBean> menus = redisServer.getListCache(key, MenuBean.class);
//            if (menus == null) {
            List<MenuBean> menus  = this.menuService.queryMenusByuid(((UserBean) user).getId());
//                redisServer.putListCacheWithExpireTime(key, menus, CacheTactics.MENU_EXPIRE);
//            }
            for (MenuBean menu : menus) {
                if (menu.getUrl().equals(request.getRequestURI())) {
                    return true;
                }
                for (MenuBean son : menu.getSonMen()) {
                    if (son.getUrl().equals(request.getRequestURI())) {
                        return true;
                    }
                }
            }

            throw new GlobalException(CodeMsg.QX_ERROR);
           // return true;
        }
        //未经过验证
        response.sendRedirect("/login.html");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}