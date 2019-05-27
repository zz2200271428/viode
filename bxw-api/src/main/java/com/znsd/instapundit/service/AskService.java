package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.AskBean;
import com.znsd.instapundit.bean.AskRecordBean;
import com.znsd.instapundit.bean.StoreBean;
import com.znsd.instapundit.param.AskRecordParam;
import com.znsd.instapundit.result.Result;

import java.math.BigDecimal;
import java.util.List;

public interface AskService {

    List<AskBean> getAskByStoreID(Integer storeID);

    List<AskBean> getAskByUserID(Integer userID);

    Result issue(Integer userID, Integer storeID, String issue, BigDecimal reward);

    List<AskBean> getAskByStoreIDAndUserID(Integer storeID, Integer userID);

    AskBean getAskByAskId(Integer id);

    List<AskRecordBean> getRecordByAskId(Integer id);


    int addRecord(AskRecordParam askRecordParam);

    StoreBean getUserByStoreId(Integer id);
}
