package com.znsd.instapundit.bxwapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.*;
import com.znsd.instapundit.service.AppIndexService;
import com.znsd.instapundit.service.AppTeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {


    /*
    *   model  模型
    *   put  请求
    * */
    @Reference
    private AppIndexService appIndexService;

    @Reference
    private AppTeacherService teacherService;



    //查询分页数据（模糊搜索，默认）

    @RequestMapping("/index")
    public String PageLikelist(Model model){
        List<ImageBean> imageBeanList = appIndexService.queryListImageIndex(); // 图文
        List<AppUserBean> appUserBeans= appIndexService.queryListMaster();  // 大咖
        List<SlideshowBean> slideshowBeanList=appIndexService.queryListSlideshow(); //轮播
        List<VideoBean> videoBeans=appIndexService.queryListVideo(1);  // 视频( 会员视频，专栏视频，免费视频)
        List<VideoBean> videos=appIndexService.queryListVideo(3); // 会员视频
        List<VideoBean> videoBeans1=appIndexService.queryListVideo(2);// 付费 视频

        List<VideoBean> specialCollectBeans=appIndexService.queryListVideo(4);// 专栏视频

        List<AppUserBean> ListLecturer=appIndexService.queryListLecturer(); // 讲师列表

        List<VideoBean> videoListLecturer=appIndexService.queryListMasterVideo(2);  // 查看讲师所有的视频

        List<VideoBean> videoListMaster=appIndexService.queryListMasterVideo(3);  // 查看讲师所有的视频

        model.addAttribute("lecturer",videoListLecturer);  // 讲师列表
        model.addAttribute("master",videoListMaster);   //  大咖
        model.addAttribute("image",imageBeanList);      // 图文列表
        model.addAttribute("slide",slideshowBeanList);  // 首页轮播
        model.addAttribute("freevideo",videoBeans);       //免费
        model.addAttribute("membervideo",videos);         //会员
        model.addAttribute("payvideo",videoBeans1);   // 付费视频
        model.addAttribute("specialvideo",specialCollectBeans); // 专栏 视频

        return "index/index";
    }

    /*
        视频id  点击播放相应的视频
    * */
   /* @RequestMapping("/veido")
    @ResponseBody
    public String Page(Integer id){
        VideoBean video=appIndexService.getVideoById(id);
        return null;
    }*/

   /*
   *    免费
   * */
   @RequestMapping("/free/all")
   public String  getQueryfreeAll(Model model){
        List<VideoBean> videoBeans=appIndexService.queryListVideo(1);
       model.addAttribute("listVideo",videoBeans);
        return "search";
   }

   /*
   *    会员
   * */
    @RequestMapping("/member/all")
    public String  getQueryMemberAll(Model model){
        List<VideoBean> videoBeans=appIndexService.queryListVideo(3);
        model.addAttribute("listVideo",videoBeans);
        return "search";
    }

    /*
    *   付费
    * */
    @RequestMapping("/pay/all")
    public String  getQueryPayAll(Model model){
        List<VideoBean> videoBeans=appIndexService.queryListVideo(2);
        model.addAttribute("listVideo",videoBeans);
        return "search";
    }

    /**
     *  专栏
     * */
    @RequestMapping("/special/all")
    public String  getQuerySpecialAll(Model model){
        List<VideoBean> videoBeans=appIndexService.queryListVideo(4);
        model.addAttribute("listVideo",videoBeans);
        return "search";
    }


    /*
    *   讲师
    * */
    @RequestMapping("/lecturer/all")
    public String  getQueryMasterAll(Model model){
        List<VideoBean> videoBeans=appIndexService.queryListMasterVideo(2);
        model.addAttribute("listVideo",videoBeans);
        return "search";
    }


    /*
    *   /*
     *   大咖列表
     *
         public List<AppUserBean>  queryListLecturer();
    * */

    @RequestMapping("/master/all")
    public String  getQueryLecturerAll(Model model){
        List<VideoBean> videoBeans=appIndexService.queryListMasterVideo(3);
        model.addAttribute("listVideo",videoBeans);
        return "search";
    }




    @RequestMapping("/store/master")
    public String homepage(Model model, HttpSession session,@RequestParam(value = "userID") Integer id) {
        List<VideoBean> free = teacherService.listVideo(id,1);
        List<SpecialColumnBean> column = teacherService.listColumn(id);   // 专栏
        List<VideoBean> pay = teacherService.listVideo(id,2);
        List<VideoBean> member = teacherService.listVideo(id,3);
        AppUserBean teacher = appIndexService.userTeacher(id);  // 大咖
        if (teacher != null) {
            model.addAttribute("userTeacher", teacher);  //  老师
        }
        model.addAttribute("member", member);   //  会员
        model.addAttribute("pay", pay);     //      付费
        model.addAttribute("column", column);    //  专栏
        model.addAttribute("free", free);  // 免费
        return "teacher/teacher-homepage";
    }




}

