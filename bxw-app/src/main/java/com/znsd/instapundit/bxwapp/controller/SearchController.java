package com.znsd.instapundit.bxwapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.bean.ClassifySeparateBean;
import com.znsd.instapundit.bean.SpecialColumnBean;
import com.znsd.instapundit.bean.VideoBean;
import com.znsd.instapundit.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Reference
    private SearchService searchService;

    /**
     * 查询热键
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/keyword")
    @ResponseBody
    public List<ClassifySeparateBean> getKeyword(Model model, HttpServletRequest request) {
        List<ClassifySeparateBean> search = searchService.getKeyword();
        model.addAttribute("search", search);
        return search;
    }

    /**
     * 输入关键字模糊搜索
     *
     * @param text
     * @param model
     * @return
     */
    @RequestMapping("/history")
    public String getHistory(String text, Model model) {
        searchService.addContent(text);
        List<VideoBean> video = searchService.listVideo(text);
        model.addAttribute("listVideo", video);
        return "search";
    }

    /**
     * 讲师的更多视频
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/listVideo")
    public String getMore(Model model, HttpSession session,Integer video_type) {
        AppUserBean user = (AppUserBean) session.getAttribute("user");
        Integer id = user.getId();
        List<VideoBean> video = searchService.listMore(id,video_type);
        model.addAttribute("listVideo", video);
        return "search";
    }

    /**
     * 讲师的所有专栏
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/listspecial")
    public String getSpecial(Model model, Integer specialID, HttpSession session) {
        AppUserBean user = (AppUserBean) session.getAttribute("user");
        Integer id = user.getId();
        SpecialColumnBean special = searchService.querySpecial(id, specialID);
        if (special != null) {
            model.addAttribute("special", special);
        }
        boolean isCollect = searchService.isCollect(specialID, id);
        model.addAttribute("isCollect", isCollect);
        List<VideoBean> video = searchService.listSpecial(specialID,4);
        model.addAttribute("listVideo", video);
        return "teacher/special-homepage";
    }

    /**
     * 讲师的所有专栏视频
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/specialAll")
    public String specialAll(Model model, HttpSession session) {
        AppUserBean user = (AppUserBean) session.getAttribute("user");
        Integer id = user.getId();
        List<SpecialColumnBean> special = searchService.specialAll(id);
        model.addAttribute("special", special);
        return "teacher/special-list";
    }

    @PostMapping("/collect")
    @ResponseBody
    public boolean collect(Integer specialID,boolean flag,HttpSession session) {
        AppUserBean user = (AppUserBean) session.getAttribute("user");
        Integer id = user.getId();
        return searchService.collect(specialID, id, flag);
    }
}
