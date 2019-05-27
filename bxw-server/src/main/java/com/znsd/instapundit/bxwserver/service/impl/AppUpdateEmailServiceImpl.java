package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.MoveUserBean;
import com.znsd.instapundit.bxwserver.dao.AppUpdateEmailDao;
import com.znsd.instapundit.service.AppUpdateEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service
@Component
public class AppUpdateEmailServiceImpl implements AppUpdateEmailService {
    @Autowired
    private AppUpdateEmailDao appUpdateEmailDao;
    @Override
    public int addEmaillogin(MoveUserBean moveUserBean) {
        appUpdateEmailDao.updateDateEmail(moveUserBean);
        return appUpdateEmailDao.addEmaillogin(moveUserBean);
    }
    @Override
    public int addPhonelogin(MoveUserBean moveUserBean) {
        appUpdateEmailDao.updateDatePhone(moveUserBean);
        return appUpdateEmailDao.addPhonelogin(moveUserBean);
    }

    @Override
    public int update_Date_Password(MoveUserBean moveUserBean) {
        return appUpdateEmailDao.update_Date_Password( moveUserBean);
    }
}
