package com.znsd.instapundit.service;


import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.param.AppUserParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;

public interface AppUserService {
    /**
     * @param pageBean
     * @return List<AppUserBean>
     * 分页查询
     */
    PagingResult listPage(AppUserParam pageBean);


    /**
     * 冻结用户
     */
    Result updateStatus(AppUserBean appUserBean);

    AppUserBean getUserByid(Integer id);

    int updatePass(AppUserBean pageBean);

    boolean isMember(Integer userID);

    /*
    * app端登录效验
    * */
    AppUserBean loginCheck(AppUserBean appUserBean);

    /*
    * 添加登录日志
    * */
    void addAppLog(AppUserBean appUserBean, String ip);

    /*
    * 查询phone是否存在
    * */
    AppUserBean checkPhone(String key);

    /*
    查询当天发送短信的次数
    * */
    Integer sendNoteNum(String phone,String datatime);

    /*
    * 短信发送成功后插入发送的数据
    * */
    void addNoteCodeRecord(String phone,String noteContent);

    /*
    * 修改密码
    * */
    Integer updatePasswrodByPhone(AppUserBean appUserBean);

    /*
    * 当天邮件发送次数
    * */
    Integer sendEmailNum(String email, String datetime);

    /*
    * 记录邮件发送信息
    * */
    void addEmail(String email, String content);

    void addRegisterNote(String phone, String content);


    Integer addAppuser(AppUserBean appUserBean);
}
