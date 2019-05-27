package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.MenuBean;
import com.znsd.instapundit.bean.RoleBean;
import com.znsd.instapundit.param.MenuParam;
import com.znsd.instapundit.result.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RoleDao {

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
    Integer deleteRoleById(@Param("ids") Integer ids,@Param("id") Integer id);

    /*
    * 查询所有子菜单
    * */
    List<MenuBean> querySonMenuList();

    /*
     * 增加角色
     * */
    Integer addRole(RoleBean roleBean);

    /*
     * 增加角色菜单
     * */
    void addRoleMenu(@Param("id") Integer id,@Param("ids") Integer[] ids);

    /*
    * 效验用户角色名称是否重复
    * */
    RoleBean checkRoleName(@Param("name") String name);

    List<MenuBean> queryMenuList();

    /*
     * 根据角色ID查询角色信息
     * */
    RoleBean queryRoleById(@Param("id") Integer id);


    /*
     * 修改保存
     * */
    Integer updateRoleById(RoleBean roleBean);

    /*
    * 删除角色菜单关联表数据
    * */
    Integer deleteRoleMenu(@Param("id") Integer id);

    /*
    * 增加修改后的角色菜单关联表数据
    * */
    void updateRoleMenu(@Param("id") Integer id,@Param("ids") Integer[] ids);

    /*
    * 更改分配
    * */
    void updateAllot(@Param("ids") Integer[] ids);

    /*
    * 冻结角色
    * *//*
    Integer updateStatus(@Param("id") Integer id);*/
}
