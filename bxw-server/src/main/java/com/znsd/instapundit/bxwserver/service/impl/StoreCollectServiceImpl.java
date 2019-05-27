package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.StoreCollectBean;
import com.znsd.instapundit.bxwserver.dao.StoreCollectDao;
import com.znsd.instapundit.param.StoreCollectParam;
import com.znsd.instapundit.service.StoreCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class StoreCollectServiceImpl implements StoreCollectService {


    @Autowired
    private StoreCollectDao storeCollectDao;


    @Override
    public List<StoreCollectBean> queryStroeList(StoreCollectParam storeCollectParam) {
        return this.storeCollectDao.queryStroeList(storeCollectParam);
    }

    @Override
    public int queryCount() {
        return this.storeCollectDao.queryCount();
    }

    @Override
    public List<StoreCollectBean> getCollectByUserId(Integer id) {
        return storeCollectDao.getCollectByUserId(id);
    }
}
