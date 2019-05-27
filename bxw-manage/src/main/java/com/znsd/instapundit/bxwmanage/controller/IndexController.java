package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.MenuBean;
import com.znsd.instapundit.bean.UserBean;
import com.znsd.instapundit.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Reference
    private MenuService menuService;

    @RequestMapping("/index")
    public ModelAndView index(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        UserBean user = (UserBean) session.getAttribute("userSession");
        List<MenuBean> catalogs = menuService.queryCatalogs(user.getId());
        mv.addObject("catalogs", catalogs);
        mv.setViewName("index");
        return mv;
    }

}
