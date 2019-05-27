package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.AskBean;
import com.znsd.instapundit.bean.AskRecordBean;
import com.znsd.instapundit.bean.StoreBean;
import com.znsd.instapundit.bxwserver.dao.AskDao;
import com.znsd.instapundit.bxwserver.dao.StoreDao;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.param.AskRecordParam;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.AskService;
import com.znsd.instapundit.service.ClassifySeparateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Service
@Component
public class AskServiceImpl implements AskService {

    @Autowired
    private AskDao askDao;

    @Autowired
    private StoreDao storeDao;

    @Override
    public List<AskBean> getAskByStoreID(Integer storeID) {
        return askDao.getAskByStoreID(storeID);
    }

    @Override
    public List<AskBean> getAskByUserID(Integer userID) {
        return askDao.getAskByUserID(userID);
    }

    @Override
    public Result issue(Integer userID, Integer storeID, String issue, BigDecimal reward) {
        Integer count = askDao.insertIssue(userID, storeID, issue, reward);
        count = storeDao.updateAskCount(storeID);
        askDao.updateAskcount(storeID);
        if (count > 0) {
            return Result.success(CodeMsg.ISSUE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.ISSUE_ERROR);
    }

    @Override
    public List<AskBean> getAskByStoreIDAndUserID(Integer storeID, Integer userID) {
        return askDao.getAskByStoreIDAndUserID(storeID, userID);
    }

    @Override
    public AskBean getAskByAskId(Integer id) {
        return askDao.getAskByAskId(id);
    }

    @Override
    public List<AskRecordBean> getRecordByAskId(Integer id) {
        return askDao.getRecordByAskById(id);
    }

    @Override
    public int addRecord(AskRecordParam askRecordParam) {
        return askDao.addRecord(askRecordParam);
    }

    @Override
    public StoreBean getUserByStoreId(Integer id) {
        return askDao.getUserByStoreId(id);
    }


}
