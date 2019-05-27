package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.bean.LoginLogBean;
import com.znsd.instapundit.bean.NoteBean;
import com.znsd.instapundit.param.AppUserParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface AppUserDao {
    /**
     * @param pageBean
     * @return List<UserBean>
     * 查询所有用户
     */
    List<AppUserBean> listPage(AppUserParam pageBean);


    /*
     * 查询总记录数
     * */
    Integer queryCount(AppUserParam pageBean);

    /**
     * 冻结用户
     */
    Integer updateStatus(AppUserBean appUserBean);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    AppUserBean getUserByid(Integer id);

    /**
     * 重置密码
     * @param pageBean
     * @return
     */
    int updatePass(AppUserBean pageBean);

    Integer isMember(@Param("userID") Integer userID);


    /*
     *
     * app端登录效验
     *
     * */
    AppUserBean loginCheck(AppUserBean appUserBean);

    /*
    * app端的登录日志
    * */
    void addAppLog(LoginLogBean loginLogBean);

    /*
    * 查询phone是否存在
    * */
    AppUserBean checkPhone(@Param("key") String key);

    /*
   查询当天发送短信的次数
   * */
    Integer sendNoteNum(@Param("phone") String phone,@Param("datatime") String datatime);

    /*
     * 短信发送成功后插入发送的数据
     * */
    void addNoteCodeRecord(NoteBean noteBean);

    /*
     * 修改密码
     * */
    Integer updatePasswrodByPhone(AppUserBean appUserBean);

    /*
     * 当天邮件发送次数
     * */
    Integer sendEmailNum(@Param("email") String email, @Param("datetime") String datetime);

    /*
     * 记录邮件发送信息
     * */
    void addEmail(NoteBean noteBean);

    Integer addAppuser(AppUserBean appUserBean);
}
