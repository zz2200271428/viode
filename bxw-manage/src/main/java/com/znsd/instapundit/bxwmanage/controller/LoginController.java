package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.LoginLogBean;
import com.znsd.instapundit.bean.UserBean;
import com.znsd.instapundit.common.MD5Utils;
import com.znsd.instapundit.common.Utils;
import com.znsd.instapundit.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private static final String PREFIX = "login";
    @Reference
    private LoginService loginService;

    @RequestMapping("/loginUser")
    @ResponseBody
    public Map<String, Object> login(UserBean user, HttpSession session,HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        String password = MD5Utils.getMd5Simple(user.getPassword());
        user.setPassword(password);
        UserBean user1 = loginService.login(user);
        if (user1 != null) {
            String ip=Utils.getIpAddr(request);
            this.loginService.insertLoginInfo(user1,ip);
            session.setAttribute("userSession", user1);
            result.put("code", 200);
            result.put("msg", "登录成功！");
            return result;
        }
        result.put("code", 400);
        result.put("msg", "登录失败！密码或用户名错误");

        return result;
    }
    @RequestMapping("/quit")
    public String quit(HttpServletRequest request) {
        request.getSession().removeAttribute("userSession");
        return "login/login";
    }
}
