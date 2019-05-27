package com.znsd.instapundit.bxwapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.common.*;
import com.znsd.instapundit.service.AppUserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/appuser")
public class AppUserController {

    /*
    * 短信限制的常量
    * */
    public static final int NOTE_COUNT = 10;

    /*
    * 用于接收短信验证码
    * */
    public static int code;

    /*
     * 用于接收email验证码
     * */
    public static String noteEmail;


    /*
    * 用于记录验证码超时
    * */
    public static long codeOverTime;

    public static Integer USER_ID = null;

    @Reference
    private AppUserService appUserService;

    @RequestMapping(value = "/code", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
    @ResponseBody
    protected void getImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        VerifyCode verifyCode = new VerifyCode();
        //创建图片缓存区
        BufferedImage image = verifyCode.getImage();
        //拿到验证码内容并存进session中
        request.getSession().setAttribute("codeimg", verifyCode.getText());
        //将验证码通过输出流传给response传给页面
        verifyCode.output(image, response.getOutputStream());
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> getIndex(AppUserBean appUserBean,
                                        HttpSession session, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        String password = MD5Utils.getMd5(appUserBean.getPassword());
        appUserBean.setPassword(password);
        String codeimg = (String) session.getAttribute("codeimg");
        if (!codeimg.equalsIgnoreCase(appUserBean.getCode())) {
            result.put("code", 300);
            result.put("msg", "验证码错误");
            return result;
        }
        AppUserBean appUserBean1 = appUserService.loginCheck(appUserBean);
        if (appUserBean1 != null) {
            String ip = Utils.getIpAddr(request);
            /*
             * 添加登录日志
             * */
            appUserService.addAppLog(appUserBean, ip);
            session.setAttribute("user", appUserBean1);
            USER_ID = appUserBean1.getId();
            result.put("code", 200);
            result.put("msg", "登录成功");
            return result;
        }
        result.put("code", 400);
        result.put("msg", "密码或用户名错误");
        return result;
    }

    /*
     * 返回找回密码页面
     * */
    @RequestMapping("/pwd")
    public String getPwd() {
        return "login/password";
    }


    /*
     *
     * 发送短信
     * */
    @RequestMapping("/booleanCodepwd")
    @ResponseBody
    public boolean toShowCode(@RequestParam("key") String key) {
        return false;
    }

    @GetMapping("/phonecodepwd")
    @ResponseBody
    public Map<String, Object> getPhoneCode(String phone, String sss) {
        Map<String, Object> map = new HashMap<String, Object>();
        /*效验用户是否存在*/
        AppUserBean check = check(phone);
        if (check==null){
            map.put("result",404);
            map.put("msg","用户不存在");
            return map;
        }
        Map<String,Object> noteMap = MessageCodeUtil.getNote(phone);
        if (!"2".equals(noteMap.get("code"))){
            map.put("result",400);
            map.put("msg","发送失败！");
            return map;
        }
        /*查询当天发送短信的次数*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String datetime = simpleDateFormat.format(new Date());
        Integer noteCount = appUserService.sendNoteNum(phone, datetime);
        System.out.println(noteCount);
        if (noteCount>=NOTE_COUNT){
            map.put("result",500);
            map.put("msg","发送失败！");
            return map;
        }
        codeOverTime = Calendar.getInstance().getTimeInMillis();
        code = (int)noteMap.get("mobile_code");
        System.out.println("发送的验证码wei:"+code);
        String noteContent = (String) noteMap.get("content");
        map.put("result",200);
        map.put("msg","验证码发送成功！");
        appUserService.addNoteCodeRecord(phone,noteContent);
        return map;
    }


    /*
    * 效验证码是否正确
    *
    * */

    @RequestMapping("/chkinfopwd")
    @ResponseBody
    public Map<String,Object> checkCode(AppUserBean appUserBean){
        Map<String,Object> map = new HashMap<String, Object>();
        /*获取当前系统时间判断验证码是否失效*/
        if (code==0){
            map.put("code",404);
            map.put("msg","提交失败！");
            return map;
        }
        if (appUserBean.getCode()==null||appUserBean.getCode()==""){
            map.put("code",400);
            map.put("msg","验证码错误！");
            return map;
        }
        long currentTime = new Date().getTime();
        if ((currentTime-codeOverTime)/1000>=90) {
            map.put("code",500);
            map.put("msg","验证码超时！");
            return map;
        }
        /*效验输入的验证码是否正确*/
        if (appUserBean.getPhone().contains("@")){
            if (!noteEmail.equals(appUserBean.getCode())){
                map.put("code",400);
                map.put("msg","验证码错误！");
                return map;
            }
            map.put("code",200);
            map.put("msg","验证成功！");
            return map;
        }
        int notecode = Integer.parseInt(appUserBean.getCode());
        if (code!=notecode){
            map.put("code",400);
            map.put("msg","验证码错误！");
            return map;
        }
        map.put("code",200);
        map.put("msg","验证成功！");
        return map;
    }

    /*效验用户是否存在*/
    public AppUserBean check(String phone){
       return appUserService.checkPhone(phone);
    }

    /*
    * 效验成功跳转下一步
    * */
    @RequestMapping("/nextpwd")
    public String passwordUpdate(String phone, Model model){
        model.addAttribute("phone",phone);
        System.out.println("发送验证码的电话/邮箱:"+phone);
        return "/login/passwordupdate";
    }

    /*
    * 修改密码
    * */
    @RequestMapping("/checkpwd")
    @ResponseBody
    public Map<String,Object> updatePassword(AppUserBean appUserBean){
        Map<String,Object> map = new HashMap<String, Object>();
        if (!appUserBean.getPassword().equals(appUserBean.getPassword2())){
            map.put("code",500);
            map.put("msg","两次密码不一致");
        }
        String password = MD5Utils.getMd5(appUserBean.getPassword());
        appUserBean.setPassword(password);
        Integer count = appUserService.updatePasswrodByPhone(appUserBean);
        map.put("code",200);
        map.put("msg","修改成功");
        return map;
    }

    /*
    * 返回修改密码成功页面
    * */
    @RequestMapping("/successpwd")
    public String success(){
        return "/login/passwordsucces";
    }


    /*
    * email修改密码
    * */
    @RequestMapping("/emailcodepwd")
    @ResponseBody
    public Map<String,Object> emailPassword(String email){
        Map<String,Object> map = new HashMap<String, Object>();
        AppUserBean appUserBean = check(email);
        if (appUserBean==null){
            map.put("code",404);
            map.put("msg","用户不存在");
            return map;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String datetime = simpleDateFormat.format(new Date());
        System.out.println(datetime);
        Integer noteCount = appUserService.sendEmailNum(email, datetime);
        System.out.println(noteCount);
        if (noteCount>=NOTE_COUNT){
            map.put("code",500);
            map.put("msg","超出当天发送的数量！");
            return map;
        }
        noteEmail = MailUtil.getPass(email);
        System.out.println("邮箱："+email);
        System.out.println("邮箱验证码："+noteEmail);
        if (!StringUtils.isEmpty(email)){
            codeOverTime = Calendar.getInstance().getTimeInMillis();
            map.put("code",200);
            map.put("msg","发送成功！");
            String content = "尊敬的用户:您好! 您修改密码的验证码为:"+noteEmail+" (有效期为90秒)";
            appUserService.addEmail(email,content);
            return map;
        }
        map.put("code",400);
        map.put("msg","服务器错误！");
        return map;
    }
}
