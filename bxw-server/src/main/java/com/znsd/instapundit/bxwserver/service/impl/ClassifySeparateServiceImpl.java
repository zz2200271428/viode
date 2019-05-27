package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.ClassifySeparateBean;
import com.znsd.instapundit.bxwserver.dao.ClassifySeparateDao;
import com.znsd.instapundit.param.ClassifySeparateParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.service.ClassifySeparateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class ClassifySeparateServiceImpl implements ClassifySeparateService {

    @Autowired
    private ClassifySeparateDao classifySeparateDao;

    /**
     * 分页查询
     *
     * @param classifySeparateParam
     * @return
     */
    @Override
    public PagingResult listPage(ClassifySeparateParam classifySeparateParam) {
        int count = classifySeparateDao.count(classifySeparateParam);
        List<ClassifySeparateBean> list = classifySeparateDao.listPage(classifySeparateParam);
        return PagingResult.success(count, list);
    }
}
