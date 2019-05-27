package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.MenuBean;
import com.znsd.instapundit.bean.RoleBean;
import com.znsd.instapundit.bean.UserBean;
import com.znsd.instapundit.bxwmanage.config.RedisServer;
import com.znsd.instapundit.cache.tactics.CacheTactics;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.param.MenuParam;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.MenuService;
import com.znsd.instapundit.service.RoleService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Reference
    private MenuService menuService;

    @Reference
    private RoleService roleService;

    @Autowired
    private RedisServer redisServer;

    /*
     * 返回角色list页面
     * */
    @RequestMapping("/lists")
    public String getListPage() {
        return "role/role-list";
    }


    /*
     * 角色分页查询
     * */
    @GetMapping("/list")
    @ResponseBody
    public PagingResult getList(MenuParam menuParam) {
        if (menuParam.getStatus() == null) {
            menuParam.setStatus(1);
        }
        List<RoleBean> roleList = this.roleService.queryList(menuParam);
        return PagingResult.success(querSumCount(menuParam), roleList);
    }

    /*
     * 角色总记录数
     * */
    public Integer querSumCount(MenuParam menuParam) {
        return this.roleService.querySumCount(menuParam);
    }


    /*
     * 角色删除
     * */
    @DeleteMapping("/list")
    public Result deleteRole(Integer ids, HttpSession session) {
        UserBean userBean = (UserBean) session.getAttribute("userSession");
        Integer count = this.roleService.deleteRoleById(ids, userBean.getId());
        if (count > 0)
            return Result.success(CodeMsg.DELETE_SUCCESS);
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }

    /*
     * 返回角色增加页面
     * 列出所有菜单
     * */
    @RequestMapping("/addPage")
    public String getAddPage(Model model) {
        List<MenuBean> menuBeans = this.roleService.queryMenuList();
        model.addAttribute("menuBeans", menuBeans);
        List<MenuBean> sonMenuBeans = this.roleService.querySonMenuList();
        model.addAttribute("sonMenuBeans", sonMenuBeans);
        return "role/role-add";
    }


    /*
     * 角色添加
     * */
    @PostMapping("/list")
    @ResponseBody
    public Result addMenu(Integer[] ids, RoleBean roleBean, HttpSession session) {
        if (roleBean.getName() == null) {
            return Result.error(CodeMsg.ERROR);
        }
        if (roleCheck(roleBean.getName())) {
            return Result.error(CodeMsg.ERROR);
        }
        UserBean userBean = (UserBean) session.getAttribute("userSession");
        roleBean.setStatus(1);
        roleBean.setCreate_by(userBean.getId());
        roleBean.setUpdate_by(userBean.getId());

        redisServer.deleteCacheWithPattern(CacheTactics.MENU_PREFIX);
        roleService.addRole(ids, roleBean);
        return Result.success(CodeMsg.ADD_SUCCESS);
    }

    /*
     * 角色名称效验
     * */
    @PostMapping("/check")
    @ResponseBody
    public boolean roleCheck(String name) {
        RoleBean roleBean = this.roleService.checkRoleName(name);
        if (roleBean != null)
            return true;
        return false;
    }

    /*
     * 返回角色修改页面
     * 列出所有菜单
     * */
    @RequestMapping("/editPage/{id}")
    public String updatePage(Model model, @PathVariable Integer id) {
        List<MenuBean> menuBeans = this.roleService.queryMenuList();
        model.addAttribute("menuBeans", menuBeans);
        List<MenuBean> sonMenuBeans = this.roleService.querySonMenuList();
        model.addAttribute("sonMenuBeans", sonMenuBeans);
        RoleBean roleList = this.roleService.queryRoleById(id);
        model.addAttribute("roleList", roleList);
        return "role/role-edit";
    }

    /*
     * 修改保存
     * */
    @PutMapping("/list")
    @ResponseBody
    public Result updateRole(RoleBean roleBean, Integer[] ids, HttpSession session) {
        UserBean userBean = (UserBean) session.getAttribute("userSession");
        roleBean.setCreate_by(userBean.getId());
        Integer count = this.roleService.updateRoleById(roleBean, ids);
        if (count > 0) {
            redisServer.deleteCacheWithPattern(CacheTactics.MENU_PREFIX);
            return Result.success(CodeMsg.UPDATE_SUCCESS);
        }
        return Result.success(CodeMsg.UPDATE_ERROR);
    }

    @RequestMapping("/particulars")
    public String getParticulars(Model model, Integer id) {
        List<MenuBean> menuList = new ArrayList<MenuBean>();
        RoleBean roleBean = roleService.queryRoleById(id);
        for (MenuBean menuBean : roleBean.getMenuList()) {
            menuList.add(menuBean);
        }
        model.addAttribute("menuList", menuList);
        return "role/role-particulars";
    }

   /* //冻结角色
    @PutMapping("/freeze")
    @ResponseBody
    public boolean updateStatus(Integer id) {
        Integer integer = this.roleService.updateStatus(id);
        if (integer>0)
            return true;
        return false;
    }*/

}
