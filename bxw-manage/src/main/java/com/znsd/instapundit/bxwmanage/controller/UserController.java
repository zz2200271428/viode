package com.znsd.instapundit.bxwmanage.controller;


import com.alibaba.dubbo.config.annotation.Reference;

import com.znsd.instapundit.bean.*;
import com.znsd.instapundit.common.MD5Utils;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.param.UserParam;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.RoleService;
import com.znsd.instapundit.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private  static final String PREFIX ="user";

    @Reference
    private UserService userService;

    @Reference
    private RoleService roleService;
    //跳转查询页面
    @RequestMapping("/lists")
    public String queryList(){
        return  PREFIX+"/user-list";
    }
    //用户查询
    @GetMapping("/list")
    @ResponseBody
    public PagingResult getList(UserParam pageBean){
       return userService.listPage(pageBean);
    }
    //跳转增加页面
    @RequestMapping("/addPage")
    public String getAddPage(Model model, HttpSession session){
        List<RoleBean> roles = roleService.getListAll();
        model.addAttribute("role", roles);
        return PREFIX+"/user-add";
    }

    //增加用户
    @PostMapping("/save")
    @ResponseBody
    public  Map<String,Object> addDict(Integer[] ids ,UserBean user){
        String password = MD5Utils.getMd5Simple(user.getPassword());
        user.setPassword(password);
        Map<String,Object> result = new HashMap<String, Object>();
        userService.addUser(ids,user);
        result.put("code",200);
        result.put("msg","添加成功！");
        return result;
    }
    //删除用户
    @DeleteMapping("/delete")
    @ResponseBody
    public Result delUser(Integer []ids){
        Map<String,Object> result = new HashMap<String, Object>();
        int delCount = userService.delete(ids);
        if (delCount>0){
            return Result.success(CodeMsg.DELETE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }
    //跳转修改页面
    @GetMapping("/editPage/{id}")
    public String updatePage(@PathVariable("id") Integer id,Model model){
        UserBean user =userService.getUser(id);
        model.addAttribute("user",user);
        List<RoleBean> roles = roleService.getListAll();
        model.addAttribute("role", roles);
        model.addAttribute("roleson",user.getRoles());
        return PREFIX+"/user-add";
    }

    //修改用户
    @PutMapping("/save")
    @ResponseBody
    public   Map<String,Object> updateDict(Integer[] ids,UserBean user){
        Map<String,Object> result = new HashMap<String, Object>();
        userService.updateUser(ids,user);
        result.put("code",200);
        result.put("msg","修改成功！");
        return result;
    }
    //冻结用户
    @PutMapping("/freeze")
    @ResponseBody
    public Result updateStatus(UserBean user) {
        return this.userService.updateStatus(user);
    }

    //校验账号重复
    @RequestMapping("/checkName")
    @ResponseBody
    public boolean checkName(String number) {
        UserBean userBean = this.userService.checkName(number);
        if (userBean==null)
            return true;
        return false;
    }
    /**
     * 重置密码
     */
    @DeleteMapping("/updatePass")
    @ResponseBody
    public Result updatePass(UserBean user){
        String password = MD5Utils.getMd5Simple(user.getPassword());
        user.setPassword(password);
        Map<String,Object> result = new HashMap<String, Object>();

        int delCount = userService.updatePass(user);
        if (delCount>0){
            return Result.success(CodeMsg.DELETE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }

    @RequestMapping("/passPage")
    public String passPage(){
        return  PREFIX+"/passwordUpdate";
    }

    /**
     * 修改个人密码
     */
    @RequestMapping("/passUpdater")
    @ResponseBody
    public Result passUpdate(@RequestParam("password")String  password,@RequestParam("L_repass")String L_repass,@RequestParam("id") String id){
        Map<String,Object> result = new HashMap<String, Object>();
        String passwordMd5 = MD5Utils.getMd5Simple(password);
        password=passwordMd5;
        String L_repassMd5=MD5Utils.getMd5Simple(L_repass);
        L_repass=L_repassMd5;
        int delCount = userService.passUpdate(password,L_repass,id);
        if (delCount>0){
            return Result.success(CodeMsg.UPDATE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.UPDATE_ERROR);
    }
    /**
     * 查看个人信息
     */
    @RequestMapping("/queryUser")
    public String queryUser(Model model,HttpSession session){
        UserBean userBean=(UserBean) session.getAttribute("userSession");
        Integer id=userBean.getId();
        model.addAttribute("user",userService.queryUser(id));
        return  PREFIX+"/userUpdate";
    }

    /**
     * 修改个人密码
     */
    @RequestMapping("/userUpdater")
    @ResponseBody
    public Result userUpdate(UserBean userBean){
        Map<String,Object> result = new HashMap<String, Object>();
     ;
        int delCount = userService.userUpdate(userBean);
        if (delCount>0){
            return Result.success(CodeMsg.UPDATE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.UPDATE_ERROR);
    }

}
