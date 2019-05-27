package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.MenuBean;
import com.znsd.instapundit.bean.RoleBean;
import com.znsd.instapundit.bean.UserBean;
import com.znsd.instapundit.bxwserver.dao.RoleDao;
import com.znsd.instapundit.param.MenuParam;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Override
    public List<RoleBean> getListAll() {
        return roleDao.getListAll();
    }

    /*
     * 角色管理的分页查询
     * */
    @Override
    public List<RoleBean> queryList(MenuParam menuParam) {
        return this.roleDao.queryList(menuParam);
    }

    /*
     * 角色总记录数查询
     * */
    @Override
    public Integer querySumCount(MenuParam menuParam) {
        return this.roleDao.querySumCount(menuParam);
    }

    /*
    * 角色删除
    * */
    @Override
    public Integer deleteRoleById(Integer ids,Integer id) {
        return this.roleDao.deleteRoleById(ids,id);
    }

    /*
    * 查询所有子菜单
    * */
    @Override
    public List<MenuBean> querySonMenuList() {
        return this.roleDao.querySonMenuList();
    }

    /*
     * 增加角色
     * */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public void addRole(Integer[] ids, RoleBean roleBean) {
        Integer count = this.roleDao.addRole(roleBean);
        if (count > 0 && ids.length>0){
            this.roleDao.updateAllot(ids);
            this.roleDao.addRoleMenu(roleBean.getId(),ids);
        }
    }

    /*
    * 效验用户角色名称是否重复
    * */
    @Override
    public RoleBean checkRoleName(String name) {
        return this.roleDao.checkRoleName(name);
    }

    @Override
    public List<MenuBean> queryMenuList() {
        return this.roleDao.queryMenuList();
    }

    /*
     * 根据角色ID查询角色信息
     * */
    @Override
    public RoleBean queryRoleById(Integer id) {
        return this.roleDao.queryRoleById(id);
    }

    /*
     * 修改保存
     * */
    @Override
    public Integer updateRoleById(RoleBean roleBean, Integer[] ids) {
       Integer count = this.roleDao.deleteRoleMenu(roleBean.getId());
       if (count>0){
           if (ids.length>0){
               this.roleDao.updateRoleMenu(roleBean.getId(),ids);
           }
       }
       return this.roleDao.updateRoleById(roleBean);
    }

    /*冻结角色*//*
    @Override
    public Integer updateStatus(Integer id) {
        return this.roleDao.updateStatus(id);
    }*/
}
