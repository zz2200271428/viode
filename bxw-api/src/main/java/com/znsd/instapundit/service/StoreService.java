package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.StoreBean;
import com.znsd.instapundit.param.StoreParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;

import java.util.List;

public interface StoreService {

    PagingResult pagingApplyList(StoreParam param);

    Result updateCheckStatus(Integer storeID, Integer check_status);

    Result delete(Integer[] ids);

    Result updateStatus(Integer storeID, Integer status);

    PagingResult pagingStoreList(StoreParam param);

    StoreBean getStoreByID(Integer storeID);

    List<StoreBean> getStoreByActive(Integer active);

    boolean homepageInit(Integer userID, Integer storeID);

    boolean collect(Integer userID, Integer storeID, boolean flag);

}
