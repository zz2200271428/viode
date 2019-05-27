package com.znsd.instapundit.service;


import com.znsd.instapundit.bean.UserBean;
import com.znsd.instapundit.param.UserParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;


public interface UserService {
    /**
     *
     * @param pageBean
     * @return List<UserBean>
     * 分页查询
     */
    PagingResult listPage(UserParam pageBean);

    /**
     *
     * @param ids
     * @param user
     * 增加用户
     */
    void addUser(Integer[] ids, UserBean user);


    /**
     *@return Integer
     *  删除用户
     */
    Integer delete(Integer[] ids);

    /**
     *回显修改数据
     */
    UserBean getUser(Integer id);
    /**
     * 修改用户
     */
    void updateUser(Integer[] ids, UserBean user);

    /**
     * 冻结用户
     */
    Result updateStatus(UserBean user);

    /**
     * 校验用户重复
     */
    UserBean checkName(String number);

    /**
     * 重置密码
     */
    Integer updatePass(UserBean userBean);
    //修改个人密码
    Integer passUpdate(String  password,String L_repass,String id);

    //查询个人信息
    UserBean queryUser(Integer id);

    Integer userUpdate(UserBean userBean);
}
