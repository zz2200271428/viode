package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.OpinionBean;
import com.znsd.instapundit.bxwserver.dao.OpinionDao;
import com.znsd.instapundit.param.OpinionParam;
import com.znsd.instapundit.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private OpinionDao opinionDao;

    @Override
    public List<OpinionBean> listPage(OpinionParam opinionParam) {
        return opinionDao.listPage(opinionParam);
    }

    @Override
    public int getCount(OpinionParam opinionParam) {
        return opinionDao.getCount(opinionParam);
    }
}
