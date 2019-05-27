package com.znsd.instapundit.bxwapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.bean.MoveUserBean;
import com.znsd.instapundit.bean.StoreBean;
import com.znsd.instapundit.bean.TeacherApplyBean;
import com.znsd.instapundit.common.Appfileupload;
import com.znsd.instapundit.service.AppTSService;
import com.znsd.instapundit.service.MoveUserService;
import file.service.common.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/TS")
public class MyCenterTSController {
    @Reference
    private MoveUserService moveUserServiceTwo;
    @Reference
    private   AppTSService appTSService;

    @RequestMapping("/teach")
    public String getAddPage(Model model, HttpSession session){
      AppUserBean userBean=(AppUserBean)session.getAttribute("user");
        TeacherApplyBean teacherApplyBean=new TeacherApplyBean();
        teacherApplyBean.setId(userBean.getId());
        TeacherApplyBean teacherApplyBean1=appTSService.select(teacherApplyBean);
        model.addAttribute("bean",teacherApplyBean1);
        return teacherApplyBean1!=null?"UpdateTS/kongbaiOne":"password/teach";
    }

    @RequestMapping("/store")
    public String getSelectStore(Model model, HttpSession session){
        AppUserBean userBean=(AppUserBean)session.getAttribute("user");
        StoreBean storeBean=new StoreBean();
        storeBean.setId(userBean.getId());
        StoreBean storeBean2=appTSService.selectStoreBean(storeBean);
        if(storeBean2==null){
            storeBean2= appTSService.selectStoreBeanOne(storeBean);
            if(storeBean2!=null){
                return "UpdateTS/kongbaiOne";
            }
        }
        return  storeBean2!=null? "UpdateTS/kongbai" :"password/store";
    }


    @RequestMapping("/toStore")
    public String getToStore(Model model, HttpSession session){
        AppUserBean userBean=(AppUserBean)session.getAttribute("user");
        StoreBean storeBean=new StoreBean();
        storeBean.setId(userBean.getId());
        StoreBean storeBean2=null;
        storeBean2=appTSService.selectStoreBean(storeBean);
        if(storeBean2!=null){
            model.addAttribute("bean",storeBean2);
            return "UpdateTS/updateStore";
        }
        return "UpdateTS/TSkongbai";
    }


    @RequestMapping("/toTeach")
    public String getToTeach(Model model, HttpSession session){
        AppUserBean userBean=(AppUserBean)session.getAttribute("user");
       TeacherApplyBean teacherApplyBean=new TeacherApplyBean();
        teacherApplyBean.setId(userBean.getId());
        TeacherApplyBean teacherApplyBean1=appTSService.select(teacherApplyBean);
        if (teacherApplyBean1!=null){
            model.addAttribute("bean",teacherApplyBean1);
            return "UpdateTS/updateTeach";
        }
        return "UpdateTS/TSkongbai";
    }




    @RequestMapping(value = "/teachTwo",headers = "content-type=multipart/*", method = RequestMethod.POST)
    public String getAddTeach(TeacherApplyBean teacherApplyBean, @RequestParam("file") List<MultipartFile> files, Model model, HttpSession session, ServletRequest request){
        AppUserBean userBean=(AppUserBean)session.getAttribute("user");
        MoveUserBean userBeanTwo=new MoveUserBean();
        userBeanTwo.setId(userBean.getId());
        MoveUserBean moveUserBean=moveUserServiceTwo.queryList(userBeanTwo);
        BigDecimal monery= moveUserBean.getMoney();
        BigDecimal monery2=new BigDecimal("1000");
        if(monery.compareTo(monery2)==-1){
            return  "redirect:/pay/charge";
        }
        BigDecimal result = monery.subtract(monery2);
        String tempFile=request.getParameter("store_desc");
        teacherApplyBean.setCashdeposit(result);
        teacherApplyBean.setStore_desc(tempFile);
        List<String> list=new ArrayList<String>();
             try {
                 for (MultipartFile file : files) {
                     Map<String, Object> map = FileUpload.uploadFile(file);
                     list.add((String) map.get("url"));
                 }
             }catch (Exception e){
                 e.printStackTrace();
             }
        teacherApplyBean.setIdc_img(list.get(0));
        teacherApplyBean.setIdc_img2(list.get(1));
        teacherApplyBean.setDatum(list.get(2));
        teacherApplyBean.setCheck_status(userBean.getId().toString());
        appTSService.addTeach(teacherApplyBean);
        return "redirect:/app/MoveUser";
    }

    @RequestMapping("/UpdateTeach")
    public String getUpdateTeach(TeacherApplyBean teacherApplyBean,Model model, HttpSession session,ServletRequest request){
        AppUserBean userBean=(AppUserBean)session.getAttribute("user");
        String tempFile=request.getParameter("store_desc");
        teacherApplyBean.setStore_desc(tempFile);
        teacherApplyBean.setId(userBean.getId());
        //获取最新一次审核的记录进行修改
        TeacherApplyBean teacherApplyBean1=appTSService.select(teacherApplyBean);
        if(teacherApplyBean1.getId()!=null){
            teacherApplyBean.setId(teacherApplyBean1.getId());
            appTSService.UpdateTeach(teacherApplyBean);
        }
        return "password/setting";
    }
    @RequestMapping(value = "/StoreTwo",headers = "content-type=multipart/*", method = RequestMethod.POST)
    public String getAddStore(StoreBean storeBean, @RequestParam("file") List<MultipartFile> files, Model model, HttpSession session, ServletRequest request){
        AppUserBean userBean=(AppUserBean)session.getAttribute("user");
        MoveUserBean userBeanTwo=new MoveUserBean();
        userBeanTwo.setId(userBean.getId());
        MoveUserBean moveUserBean=moveUserServiceTwo.queryList(userBeanTwo);
        BigDecimal monery= moveUserBean.getMoney();
        BigDecimal monery2=new BigDecimal("1000");
        if(monery.compareTo(monery2)==-1){
            return  "redirect:/pay/charge";
        }
        BigDecimal result = monery.subtract(monery2);
        String tempFile=request.getParameter("store_desc");
        storeBean.setStore_desc(tempFile);
        storeBean.setCashdeposit(result);
        List<String> list=new ArrayList<String>();
        try {
            for (MultipartFile file : files) {
                Map<String, Object> map = FileUpload.uploadFile(file);
                list.add((String) map.get("url"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        storeBean.setIdc_img(list.get(0));
        storeBean.setIdc_img2(list.get(1));
        storeBean.setDatum(list.get(2));
        storeBean.setSimg(list.get(3));
        //将用户id放到审核状态里了 审核状态直接给 （1-未审核）
        storeBean.setCheck_status(userBean.getId());
        appTSService.addStore(storeBean);
        return "redirect:/app/MoveUser";
    }
    @RequestMapping("/UpdateStore")
    public String getUpdateStore(StoreBean storeBean,Model model, HttpSession session,ServletRequest request){
        AppUserBean userBean=(AppUserBean)session.getAttribute("user");
        String tempFile=request.getParameter("store_desc");
        storeBean.setStore_desc(tempFile);
        storeBean.setId(userBean.getId());
        //获取最新一次审核的记录进行修改
        StoreBean storeBean1=appTSService.selectStoreBean(storeBean);
        if (storeBean1!=null&&storeBean1.getId() !=null){
            storeBean.setId(storeBean1.getId());
            appTSService.UpdateStore(storeBean);
        }
        return "password/setting";
    }
}
