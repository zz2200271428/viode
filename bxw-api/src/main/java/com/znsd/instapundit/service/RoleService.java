package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.MenuBean;
import com.znsd.instapundit.bean.RoleBean;
import com.znsd.instapundit.bean.UserBean;
import com.znsd.instapundit.param.MenuParam;
import com.znsd.instapundit.result.Result;

import java.util.List;

public interface RoleService {

    List<RoleBean> getListAll();

    /*
    * 角色管理的分页查询
    * */
    List<RoleBean> queryList(MenuParam menuParam);

    /*
    * 角色总记录数查询
    * */
    Integer querySumCount(MenuParam menuParam);

    /*
    * 角色删除
    * */
    Integer deleteRoleById(Integer ids,Integer id);

    /*
    * 查询所有子菜单
    * */
    List<MenuBean> querySonMenuList();

    /*
    * 增加角色
    * */
    void addRole(Integer[] ids, RoleBean roleBean);

    /*
    * 效验用户角色名称是否重复
    * */
    RoleBean checkRoleName(String name);

    List<MenuBean> queryMenuList();

    /*
    * 根据角色ID查询角色信息
    * */
    RoleBean queryRoleById(Integer id);

    /*
     * 修改保存
     * */
    Integer updateRoleById(RoleBean roleBean, Integer[] ids);

   /*
    冻结角色
    * *
    Integer updateStatus(Integer id);*/
}
