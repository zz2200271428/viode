package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.bxwserver.dao.AppMasterDao;
import com.znsd.instapundit.param.AppUserParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.service.AppMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class AppMasterServiceImpl implements AppMasterService {

    @Autowired
    private AppMasterDao appMasterDao;

    /**
     * @param pageBean
     * @return List<UserBean>
     * 分页查询
     */

    @Override
    public PagingResult listPage(AppUserParam pageBean) {
        int count = appMasterDao.queryCount(pageBean);
        List<AppUserBean> appUserList = appMasterDao.listPage(pageBean);
        return PagingResult.success(count, appUserList);
    }

}
