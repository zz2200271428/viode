package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.UserBean;
import com.znsd.instapundit.bxwserver.dao.UserDao;
import com.znsd.instapundit.param.UserParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * @param pageBean
     * @return List<UserBean>
     * 分页查询
     */
    @Override
    public PagingResult listPage(UserParam pageBean) {
        int count = userDao.queryCount(pageBean);
        List<UserBean> list = userDao.listPage(pageBean);
        return PagingResult.success(count, list);
    }

    /**
     * @param ids
     * @param user 增加用户，角色
     */
    @Override
    public void addUser(Integer[] ids, UserBean user) {
        userDao.addUser(user);
        if (ids.length > 0) {
            userDao.addUR(ids, user.getId());
            userDao.updateAllot(ids);
        }
    }


    /**
     * @param ids
     * @return Integer
     */
    @Override
    public Integer delete(Integer[] ids) {
        return userDao.delete(ids);
    }

    /**
     * @param id
     * @return UserBean
     * 修改用户数据回显
     */
    @Override
    public UserBean getUser(Integer id) {
        return userDao.getUser(id);
    }

    /**
     * @param ids
     * @param user 修改用户
     */
    @Override
    public void updateUser(Integer[] ids, UserBean user) {
        userDao.delUR(user.getId());
        userDao.updateUser(user);
        if (ids.length > 0) {
            userDao.addUR(ids, user.getId());
            userDao.updateAllot(ids);
        }

    }

    /**
     * 冻结用户
     *
     * @param user
     * @return
     */

    public Result updateStatus(UserBean user) {
        int result = this.userDao.updateStatus(user);
        return Result.success(result);
    }

    /**
     * 校验用户重复
     * @param number
     * @return
     */
    @Override
    public UserBean checkName(String number) {
        return userDao.checkName(number);
    }

    /**
     * 重置密码
     * @param id
     * @return
     */
    @Override
    public Integer updatePass(UserBean userBean) {
        return userDao.updatePass(userBean);
    }

    //修改个人密码
    @Override
    public Integer passUpdate(String  password,String L_repass,String id) {
        return userDao.passUpdate(password,L_repass ,id);
    }

    //查询个人信息
    @Override
    public UserBean queryUser(Integer id) {
        return userDao.queryUser(id);
    }

    @Override
    public Integer userUpdate(UserBean userBean) {
        return userDao.userUpdate(userBean);
    }

}
