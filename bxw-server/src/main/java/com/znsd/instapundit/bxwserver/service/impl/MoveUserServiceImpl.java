package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.MoveUserBean;
import com.znsd.instapundit.bxwserver.dao.MoveUserDao;
import com.znsd.instapundit.service.MoveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service
@Component
public class MoveUserServiceImpl implements MoveUserService {

    @Autowired
    private MoveUserDao moveUserDao;
    @Override
    public MoveUserBean queryList(MoveUserBean moveUserBean) {
        return moveUserDao.queryList(moveUserBean);
    }

    @Override
    public int addMoveUserBean(MoveUserBean moveUserBean) {
        return moveUserDao.addMoveUserBean(moveUserBean);
    }

    @Override
    public int UpdateImg(MoveUserBean moveUserBean) {
        return moveUserDao.UpdateImg(moveUserBean);
    }
}
