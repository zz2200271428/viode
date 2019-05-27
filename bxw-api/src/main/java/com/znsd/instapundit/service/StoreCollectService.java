package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.StoreCollectBean;
import com.znsd.instapundit.param.StoreCollectParam;

import java.util.List;

public interface StoreCollectService {

    public List<StoreCollectBean> queryStroeList(StoreCollectParam storeCollectParam);

    public  int  queryCount();

    List<StoreCollectBean> getCollectByUserId(Integer id);
}
