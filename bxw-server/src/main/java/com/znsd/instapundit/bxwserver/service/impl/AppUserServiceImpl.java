package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.bean.DictBean;
import com.znsd.instapundit.bean.LoginLogBean;
import com.znsd.instapundit.bean.NoteBean;
import com.znsd.instapundit.bxwserver.dao.AppUserDao;
import com.znsd.instapundit.param.AppUserParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Component
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserDao appUserDao;

    /**
     * @param pageBean
     * @return List<UserBean>
     * 分页查询
     */

    @Override
    public PagingResult listPage(AppUserParam pageBean) {
        int count = appUserDao.queryCount(pageBean);
        List<AppUserBean> appUserList = appUserDao.listPage(pageBean);
        return PagingResult.success(count, appUserList);
    }

    @Override
    public Result updateStatus(AppUserBean appUserBean) {
        int result = appUserDao.updateStatus(appUserBean);
        return Result.success(result);
    }

    @Override
    public AppUserBean getUserByid(Integer id) {
        return appUserDao.getUserByid(id);
    }

    @Override
    public int updatePass(AppUserBean pageBean) {
        return appUserDao.updatePass(pageBean);
    }

    @Override
    public boolean isMember(Integer userID) {
        Integer result = appUserDao.isMember(userID);
        return result > 0;
    }

    /*
    *
     * app端登录效验
     *
    * */
    @Override
    public AppUserBean loginCheck(AppUserBean appUserBean) {
        return appUserDao.loginCheck(appUserBean);
    }

    /*
    * 添加登录日志
    * */
    @Override
    public void addAppLog(AppUserBean appUserBean, String ip) {
        LoginLogBean loginLogBean = new LoginLogBean();
        loginLogBean.setUser(appUserBean.getPhone());
        loginLogBean.setLogin_system("app端");
        loginLogBean.setIp(ip);
        appUserDao.addAppLog(loginLogBean);
    }

    /*
    * 查询phone是否存在
    * */
    @Override
    public AppUserBean checkPhone(String key) {
        return appUserDao.checkPhone(key);
    }

    /*
   查询当天发送短信的次数
   * */
    @Override
    public Integer sendNoteNum(String phone,String datatime) {
        return appUserDao.sendNoteNum(phone,datatime);
    }

    /*
     * 短信发送成功后插入发送的数据
     * */
    @Override
    public void addNoteCodeRecord(String phone, String noteContent) {
        NoteBean noteBean = new NoteBean();
        noteBean.setPhone(phone);
        noteBean.setNote(noteContent);
        noteBean.setType("密码找回");
        appUserDao.addNoteCodeRecord(noteBean);
    }

    /*
     * 修改密码
     * */
    @Override
    public Integer updatePasswrodByPhone(AppUserBean appUserBean) {
        return appUserDao.updatePasswrodByPhone(appUserBean);
    }

    /*
     * 当天邮件发送次数
     * */
    @Override
    public Integer sendEmailNum(String email, String datetime) {
        return appUserDao.sendEmailNum(email,datetime);
    }

    /*
     * 记录邮件发送信息
     * */
    @Override
    public void addEmail(String email, String content) {
        NoteBean noteBean = new NoteBean();
        noteBean.setType("邮件验证码");
        noteBean.setEmail(email);
        noteBean.setNote(content);
        appUserDao.addEmail(noteBean);
    }

    @Override
    public void addRegisterNote(String phone, String content) {
        NoteBean noteBean = new NoteBean();
        noteBean.setType("注册");
        noteBean.setPhone(phone);
        noteBean.setNote(content);
        appUserDao.addNoteCodeRecord(noteBean);
    }

    @Override
    public Integer addAppuser(AppUserBean appUserBean) {
        return appUserDao.addAppuser(appUserBean);
    }
}
