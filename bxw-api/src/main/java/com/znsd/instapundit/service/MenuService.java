package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.MenuBean;
import com.znsd.instapundit.bean.PageBean;
import com.znsd.instapundit.param.MenuParam;

import java.util.List;

/*
*
* 菜单service的接口
*
* */
public interface MenuService {

    /*
    * 分页查询
    *
    * */
    public List<MenuBean> queryList(MenuParam menuParam);

    /*
    * 查询总条数
    * */
    public Integer querySumCount(MenuParam menuParam);

    /*
    * 删除
    * */
    public Integer deleteMenuById(Integer ids,Integer id);

    /*
    * 查询所有父级菜单
    * */
    public List<MenuBean> queryMenuList();

    /*
     * 菜单增加
     * */
    public Integer addMenu(MenuBean menuBean);

    /*
    * 效验菜单名称
    * */
    public MenuBean menuCheck(String name);

    /*
    修改数据回显
    * */
    public MenuBean getMenuById(Integer id);

    /*
    * 菜单修改
    * */
    public Integer editMenu(MenuBean menuBean);

    /*
    * 修改父级菜单分配状态
    * */
    void editMenuAllot(Integer parent_id);

    /*
    * 根据id查询子级菜单
    * */
    List<MenuBean> querySonMenuById(Integer id);

    List<MenuBean> queryMenusByuid(Integer id);

    List<MenuBean> queryCatalogs(Integer id);

    /*
    * 查询按钮级菜单
    * */
    List<MenuBean> querySonMenuList(Integer id);
}
