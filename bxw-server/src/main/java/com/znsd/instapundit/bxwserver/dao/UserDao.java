package com.znsd.instapundit.bxwserver.dao;


import com.znsd.instapundit.bean.UserBean;
import com.znsd.instapundit.param.UserParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    /**
     * @param pageBean
     * @return List<UserBean>
     * 查询所有用户
     */
    List<UserBean> listPage(UserParam pageBean);

    /**
     * @param user 增加用户
     */
    void addUser(UserBean user);

    /**
     * @param ids
     * @param id  增加关联表角色
     */
    void addUR(@Param("ids") Integer[] ids, @Param("id") Integer id);

    /*
     * 查询总记录数
     * */
    Integer queryCount(UserParam pageBean);

    /**
     * 删除用户
     */
    Integer delete(@Param("ids") Integer[] ids);

    /**
     * 修改数据回显
     */
    UserBean getUser(@Param("id") Integer id);

    /**
     * @param id 删除中间表数据
     */
    Integer delUR(@Param("id") Integer id);

    /**
     * @param user 修改用户
     */
    void updateUser(UserBean user);

    /**
     * 冻结用户
     */
    int updateStatus(UserBean user);

    /**
     * 校验用户重复
     * @param number
     * @return
     */
    UserBean checkName(@Param("number") String number);

    /**
     * 修改默认角色状态
     * @param ids
     * @return
     */
    int updateAllot(@Param("ids") Integer[] ids);

    /**
     * 重置密码
     * @param userBean
     * @return
     */
    int updatePass(UserBean userBean);

    //修改个人密码
    Integer passUpdate(@Param("password") String  password,@Param("L_repass") String L_repass,@Param("id") String id);

    //查询个人信息
    UserBean queryUser(@Param("id")Integer id);
    //修改个人信息
    Integer userUpdate(UserBean userBean);
}
