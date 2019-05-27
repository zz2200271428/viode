package com.znsd.instapundit.bxwapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.bean.SpecialColumnBean;
import com.znsd.instapundit.bean.VideoBean;
import com.znsd.instapundit.service.AppTeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {


    @Reference
    private AppTeacherService teacherService;

    /**
     * 查询数据
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/homepage")
    public String homepage(Model model, HttpSession session) {
        AppUserBean user = (AppUserBean) session.getAttribute("user");
        Integer id = user.getId();
        List<VideoBean> free = teacherService.listVideo(id,1);
        List<SpecialColumnBean> column = teacherService.listColumn(id);
        List<VideoBean> pay = teacherService.listVideo(id,2);
        List<VideoBean> member = teacherService.listVideo(id,3);
        AppUserBean teacher = teacherService.userTeacher(id);
        if (teacher != null) {
            model.addAttribute("userTeacher", teacher);
        }
        model.addAttribute("member", member);
        model.addAttribute("pay", pay);
        model.addAttribute("column", column);

        model.addAttribute("free", free);
        return "teacher/teacher-homepage";
    }
}
