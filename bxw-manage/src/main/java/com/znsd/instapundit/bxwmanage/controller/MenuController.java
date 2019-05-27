package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.MenuBean;
import com.znsd.instapundit.bean.UserBean;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.param.MenuParam;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.MenuService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/*
 * 菜单控制器
 * */
@Controller
@RequestMapping("/menu")
public class MenuController {

    //public static String PREFIX = "menu";

    @Reference
    private MenuService menuService;

    /*
     * 返回菜单list页面
     * */
    @RequestMapping("/lists")
    public String returnMenuList() {
        return "menu/menu-list";
    }

    /*
     * 分页查询
     * */
    @GetMapping("/list")
    @ResponseBody
    public PagingResult getList(MenuParam menuParam) {

        if (menuParam.getStatus() == null) {
            menuParam.setStatus(1);
        }
        List<MenuBean> menuList = this.menuService.queryList(menuParam);
        return PagingResult.success(querySumCount(menuParam), menuList);
    }
    /*public Map<String, Object> queryList(PageBean pageBean) {
        System.out.println(pageBean.getPage() + " :" + pageBean.getLimit());
        List<MenuBean> menuList = this.menuService.queryList(pageBean);
        Map<String, Object> map = new HashMap<String, Object>();
        if (menuList != null && menuList.size() > 0) {
            map.put("code", 0);
            map.put("msg", "查询成功");
            map.put("data", menuList);
            map.put("count", querSumCount());
            return map;
        }
        map.put("code", 1);
        map.put("msg", "查询失败");
        map.put("data", null);
        map.put("count", 0);
        return map;
    }*/

    /*
     * 查询总记录数
     * */
    public Integer querySumCount(MenuParam menuParam) {
        return this.menuService.querySumCount(menuParam);
    }

    /*
     * 菜单删除
     * */

    @DeleteMapping("/delete")
    @ResponseBody
    public Result deleteMenu(Integer ids, HttpSession session) {
        UserBean userBean = (UserBean) session.getAttribute("userSession");
        Integer count = this.menuService.deleteMenuById(ids, userBean.getId());
        if (count > 0)
            return Result.success(CodeMsg.DELETE_SUCCESS);
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }

    /*
     * 用户显示菜单图标
     * */
    @RequestMapping("/icon")
    public String icon() {
        return "menu/icon";
    }

    /*
     * 返回增加页面
     * 列出所有父级菜单
     * */
    @RequestMapping("/addPage")
    public String getAddPage(Model model) {
        List<MenuBean> menuList = this.menuService.queryMenuList();
        model.addAttribute("menu", menuList);
        return "menu/menu-add";
    }

    /*
     * 效验菜单名称是否重复
     * */
    @PostMapping("/check")
    @ResponseBody
    public boolean menuCheck(String name) {
        MenuBean menuBean = this.menuService.menuCheck(name);
        if (menuBean != null) {
            return true;
        }
        return false;
    }

    /*
     * 添加保存
     * */
    @PostMapping("/save")
    @ResponseBody
    public Result addMenu(MenuBean menuBean, HttpSession session) {
        if (menuBean.getName()==null) {
            return Result.error(CodeMsg.ADD_ERROR);
        }
        if (menuCheck(menuBean.getName())){
            return Result.error(CodeMsg.ADD_ERROR);
        }
        UserBean userBean = (UserBean) session.getAttribute("userSession");
        Integer create_by = userBean.getId();
        menuBean.setCreate_by(create_by);
        menuBean.setUpdate_by(create_by);
        menuBean.setStatus(1);
        Integer insertCount = this.menuService.addMenu(menuBean);
        if (insertCount > 0) {
            if (menuBean.getParent_id() != 0) {
                this.menuService.editMenuAllot(menuBean.getParent_id());
            }
            return Result.success(CodeMsg.ADD_SUCCESS);
        }
        throw new GlobalException(CodeMsg.ADD_ERROR);
    }

    /*
     * 返回菜单修改页面
     * */
    @RequestMapping("/editPage/{id}")
    public String editPage(Model model, @PathVariable("id") Integer id) {

        List<MenuBean> menuList = this.menuService.queryMenuList();
        model.addAttribute("menu", menuList);

        MenuBean menuBean = this.menuService.getMenuById(id);
        model.addAttribute("menuBean", menuBean);
        return "menu/menu-edit";
    }

    /*
     * 修改保存
     * */
    @PutMapping("/save")
    @ResponseBody
    public Result editMenu(MenuBean menuBean, HttpSession session) {
        if (menuBean.getName() == null) {
            return Result.error(CodeMsg.UPDATE_ERROR);
        }
        MenuBean menucheck = this.menuService.menuCheck(menuBean.getName());
       /* if (menucheck.getName().equals(menuBean.getName()) && menucheck.getId() != menuBean.getId()) {
            return Result.error(CodeMsg.UPDATE_ERROR);
        }*/
        UserBean userBean = (UserBean) session.getAttribute("userSession");
        menuBean.setUpdate_by(userBean.getId());
        Integer count = this.menuService.editMenu(menuBean);
        if (count > 0)
            return Result.success(CodeMsg.UPDATE_SUCCESS);
        return Result.success(CodeMsg.UPDATE_ERROR);
    }

    /*
     * 父级菜单详情回显
     * */
    @RequestMapping("/parentbutto")
    public String getParentBtton(Model model, Integer id) {
        List<MenuBean> menuBean = menuService.querySonMenuById(id);
        model.addAttribute("menu", menuBean);
        return "menu/menu-parentbutton";
    }

    /*
     * 子类的详情页面回显
     * */
    @RequestMapping("/sonbutton")
    public String getBtton(Model model, Integer id) {
        model.addAttribute("id",id);
        return "menu/menu-sonbutton";
    }

    /*
    * 子菜单数据查询
    * */
    @RequestMapping("/listson")
    @ResponseBody
    public PagingResult getListSon(Integer id){
        List<MenuBean> menuList = menuService.querySonMenuList(id);
        return PagingResult.success(menuList.size(),menuList);
    }

    /*
    * 返回修改页面
    * */
    @RequestMapping("/editsonPage/{id}")
    public String getSonPage(Model model,@PathVariable Integer id){
        MenuBean menuBean = this.menuService.getMenuById(id);
        System.out.println(menuBean);
        model.addAttribute("menuBean", menuBean);
        return "menu/menu-editbutton";
    }
}
