package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.MoveUserBean;

public interface AppUpdateEmailService {
    int addEmaillogin(MoveUserBean moveUserBean);
    int addPhonelogin(MoveUserBean moveUserBean);
    int update_Date_Password( MoveUserBean moveUserBean);
}
