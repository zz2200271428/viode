package com.znsd.instapundit.bxwapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.bean.MoveUserBean;
import com.znsd.instapundit.common.Appfileupload;
import com.znsd.instapundit.common.MD5Utils;
import com.znsd.instapundit.common.MailUtil;
import com.znsd.instapundit.common.MessageCodeUtil;
import com.znsd.instapundit.service.AppUpdateEmailService;
import com.znsd.instapundit.service.MoveUserService;
import file.service.common.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class MyCenterController {
    @Reference
    private MoveUserService moveUserService;
    @Reference
    private AppUpdateEmailService appUpdateEmailService;
    @RequestMapping("/my")
    public String getAddPage(Model model, HttpSession session){
        return "/UpdateTS/updateImg";
    }

    @RequestMapping("/setting")
    public String getSetting(Model model, HttpSession session){
        return "password/setting";
    }

    @RequestMapping("/message")
    public String getMessage(Model model, HttpSession session){
        MoveUserBean user= new MoveUserBean();
        AppUserBean userBean=(AppUserBean)session.getAttribute("user");
        user.setId(userBean.getId());
        MoveUserBean moveUserBean=moveUserService.queryList(user);
        model.addAttribute("moveUserBean",moveUserBean);
        return "password/message";
    }


    @RequestMapping("/password")
    public String getPassword(Model model, HttpSession session){
        model.addAttribute("password","password");
        return "password/password";
    }

    @RequestMapping("/phone")
    public String getPhone(Model model, HttpSession session){
        model.addAttribute("model","phone");
        return "password/phone";
    }

    @RequestMapping("/email")
    public String getEmail(Model model, HttpSession session){
        model.addAttribute("model","email");
        return "password/phone";
    }

    @RequestMapping("/MoveUser")
    public String getQueryMoveUser(Model model, HttpSession session){
        MoveUserBean user= new MoveUserBean();
        AppUserBean userBean=(AppUserBean)session.getAttribute("user");
        user.setId(userBean.getId());
      /*  user.setPhone(.toString());
        user.setPassword("9999999");*/
        MoveUserBean moveUserBean=moveUserService.queryList(user);
        model.addAttribute("moveUserBean",moveUserBean);
        return "me";
    }
    @RequestMapping("/returnGetPhone")
    @ResponseBody
    public String getPhone(String number, HttpSession session){
        MessageCodeUtil messageCodeUtil=new MessageCodeUtil();
        Map<String,Object> map= messageCodeUtil.getNote(number);
        String code=map.get("mobile_code").toString();
        session.setAttribute("code",code);
        return "验证码";
    }


    //修改手机号后增加一条记录

    @RequestMapping("/returnSettingPhone")
    @ResponseBody
    public  MoveUserBean getAddPhone(String number,String password ,Model model, HttpSession session){
        String code=(String) session.getAttribute("code");
        if (password.equals(code)){
            session.removeAttribute("email");
            MoveUserBean user= new MoveUserBean();
            AppUserBean userBean=(AppUserBean)session.getAttribute("user");
            user.setId(userBean.getId());
            MoveUserBean moveUserBean=moveUserService.queryList(user);
            if(moveUserBean!=null){
                moveUserBean.setPicture(number);
                appUpdateEmailService.addPhonelogin(moveUserBean);
                return moveUserBean;
            }
        }
        return null;
    }

    @RequestMapping("/SettingEmail")
    @ResponseBody
    public String emailPassword(String number,HttpSession session){
        MailUtil mailUtil=new MailUtil();
        String email= mailUtil.getPass(number);
        session.setAttribute("email",email);
        //session.setMaxInactiveInterval(70);
        return "123";
    }
    //修改Email后增加一条记录
    @RequestMapping("/returnSettingEmail")
    @ResponseBody
    public MoveUserBean getAddEmail(String number,String password,Model model, HttpSession session){

        MoveUserBean user= new MoveUserBean();
        AppUserBean userBean=(AppUserBean)session.getAttribute("user");
        String str=(String) session.getAttribute("email");
        user.setId(userBean.getId());
        MoveUserBean userBeanTwo=null;
        if (str.equals(password)){
            userBeanTwo= moveUserService.queryList(user);
            session.removeAttribute("email");
        }
        if(userBeanTwo!=null){
            userBeanTwo.setPicture(number);
            appUpdateEmailService.addEmaillogin(userBeanTwo);
        }
        return userBeanTwo;
    }
    @RequestMapping("/returnPassword")
    @ResponseBody
    public MoveUserBean getAddPassword(MoveUserBean user,Model model, HttpSession session){
        //phone 和password
        String password = MD5Utils.getMd5(user.getPassword());
        String NewPassword=MD5Utils.getMd5(user.getPhone());
        AppUserBean userBean=(AppUserBean)session.getAttribute("user");
        user.setId(userBean.getId());
        user.setPassword(NewPassword);
        MoveUserBean userBeanTwo=moveUserService.queryList(user);
        if (userBeanTwo!=null){
            user.setPassword(password);
            appUpdateEmailService.update_Date_Password(user);
            session.removeAttribute("user");
            return user;
        }
        return null;
    }

    @RequestMapping("/returnMessage")
    @ResponseBody
    public Integer  getAddMessage(MoveUserBean user,Model model, HttpSession session){
        AppUserBean appUserBean=(AppUserBean)session.getAttribute("user");
        user.setId(appUserBean.getId());
        return  moveUserService.addMoveUserBean(user);
    }
    @RequestMapping("/Img")
    public String img(){
        return "UpdateTS/updateImg";
    }
    @RequestMapping(value = "/UpdateImg",headers = "content-type=multipart/*", method = RequestMethod.POST)
    public String UpdateImg(@RequestParam("file")MultipartFile file, Model model, HttpSession session){
        MoveUserBean moveUserBean =new MoveUserBean();
        AppUserBean appUserBean=(AppUserBean)session.getAttribute("user");
        moveUserBean.setId(appUserBean.getId());
        Appfileupload appfileupload =new Appfileupload();
        Map<String, Object> map = FileUpload.uploadFile(file);
        String str=(String)map.get("url");
        moveUserBean.setPicture(str);
        int user=moveUserService.UpdateImg(moveUserBean);
        return "redirect:/app/MoveUser";
    }
}
