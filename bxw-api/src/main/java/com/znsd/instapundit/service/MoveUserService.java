package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.MoveUserBean;

import java.util.List;

public interface MoveUserService {
    MoveUserBean queryList(MoveUserBean moveUserBean);
    int addMoveUserBean(MoveUserBean moveUserBean);
    int UpdateImg(MoveUserBean moveUserBean);
}
