package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.SpecialCollectBean;
import com.znsd.instapundit.bxwserver.dao.SpecialCollectDao;
import com.znsd.instapundit.param.CollectParam;
import com.znsd.instapundit.service.SpecialCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Service
@Component
public class SpecialCollectServiceImpl implements SpecialCollectService {

    @Autowired
    private SpecialCollectDao specialCollectDao;
    @Override
    public List<SpecialCollectBean> queryList(CollectParam collectParam) {
        return specialCollectDao.queryList(collectParam);
    }
    @Override
    public int getCount() {
        return this.specialCollectDao.getCount();
    }

    @Override
    public List<SpecialCollectBean> getCollectByUserId(Integer id) {
        return specialCollectDao.getCollectByUserId(id);
    }


}
