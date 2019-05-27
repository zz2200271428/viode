package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.MenuBean;
import com.znsd.instapundit.bean.PageBean;
import com.znsd.instapundit.param.MenuParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
* 菜单dao接口
* */
@Mapper
public interface MenuDao {

    /*
    * 分页查询
    * */
    public List<MenuBean> queryList(MenuParam menuParam);

    /*
    * 查询总记录数
    * */
    public Integer querySumCount(MenuParam menuParam);

    /*
        删除
    * */
    public Integer deleteMenuById(@Param("ids") Integer ids,@Param("id") Integer id);

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
    public MenuBean menuCheck(@Param("name") String name);


    /*
   修改数据回显
   * */
    public MenuBean getMenuById(@Param("id") Integer id);

    /*
    * 菜单修改
    * */
    public Integer editMenu(MenuBean menuBean);

    /*
     * 修改父级菜单分配状态
     * */
    void editMenuAllot(@Param("parent_id") Integer parent_id);

    /*
    * 根据id查询子级菜单
    * */
    List<MenuBean> querySonMenuById(@Param("id") Integer id);

    /**
     * 根据用户id查询所有菜单
     *
     * @param
     * @return
     */
    List<MenuBean> queryMenusByuid(@Param("id") Integer id);


    List<MenuBean> queryCatalogs(@Param("id") Integer id);

    /*
     * 查询按钮级菜单
     * */
    List<MenuBean> querySonMenuList(@Param("id") Integer id);
}
