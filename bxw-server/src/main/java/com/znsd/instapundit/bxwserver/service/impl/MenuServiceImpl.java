package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.MenuBean;
import com.znsd.instapundit.bean.PageBean;
import com.znsd.instapundit.bxwserver.dao.MenuDao;
import com.znsd.instapundit.param.MenuParam;
import com.znsd.instapundit.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/*
*菜单service的接口的实现类
* */

@Service
@Component
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;
    /*
     * 分页查询
     *
     * */
    @Override
    public List<MenuBean> queryList(MenuParam menuParam) {
        return  this.menuDao.queryList(menuParam);
    }

    /*
    * 查询总记录数
    * */
    @Override
    public Integer querySumCount(MenuParam menuParam) {
        return this.menuDao.querySumCount(menuParam);
    }

    /*
    * 删除
    * */
    @Override
    public Integer deleteMenuById(Integer ids,Integer id) {
        return this.menuDao.deleteMenuById(ids,id);
    }

    /*
    * 查询所有父级菜单
    * */
    @Override
    public List<MenuBean> queryMenuList() {
        return this.menuDao.queryMenuList();
    }


    /*
     * 菜单增加
     * */
    @Override
    public Integer addMenu(MenuBean menuBean) {
        if (StringUtils.isEmpty(menuBean.getUrl())){
            menuBean.setUrl("#");
        }
        return this.menuDao.addMenu(menuBean);
    }

    /*
    * 效验菜单名称
    * */
    @Override
    public MenuBean menuCheck(String name) {
        return this.menuDao.menuCheck(name);
    }

    /*
   修改数据回显
   * */
    @Override
    public MenuBean getMenuById(Integer id) {
        return this.menuDao.getMenuById(id);
    }

    /*
    * 菜单修改
    * */
    @Override
    public Integer editMenu(MenuBean menuBean) {
        return this.menuDao.editMenu(menuBean);
    }


    /*
     * 修改父级菜单分配状态
     * */
    @Override
    public void editMenuAllot(Integer parent_id) {
        this.menuDao.editMenuAllot(parent_id);
    }

    /*
    * 根据id查询子级菜单
    * */
    @Override
    public List<MenuBean> querySonMenuById(Integer id) {
        return menuDao.querySonMenuById(id);
    }

    @Override
    public List<MenuBean> queryMenusByuid(Integer id) {
        return this.menuDao.queryMenusByuid(id);
    }

    @Override
    public List<MenuBean> queryCatalogs(Integer id) {
        return menuDao.queryCatalogs(id);
    }

    /*
     * 查询按钮级菜单
     * */
    @Override
    public List<MenuBean> querySonMenuList(Integer id) {
        return menuDao.querySonMenuList(id);
    }
}
