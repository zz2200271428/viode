package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.PageBean;
import com.znsd.instapundit.bean.UpdatePhoneBean;
import com.znsd.instapundit.bxwserver.dao.UpdatePhoneDao;
import com.znsd.instapundit.service.UpdatePhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Service
@Component
public class UpdatePhoneServiceImpl implements UpdatePhoneService {
    @Autowired
    UpdatePhoneDao updatePhoneDao;
    @Override
    public List<UpdatePhoneBean> listPage(PageBean pageBean) {
        return updatePhoneDao.listPage(pageBean);
    }

    @Override
    public int listPageCount() {
        return updatePhoneDao.listPageCount();
    }

    @Override
    public List<UpdatePhoneBean> seek(UpdatePhoneBean updatePhoneBean, PageBean pageBean) {
        return updatePhoneDao.seek(updatePhoneBean,pageBean);
    }

    @Override
    public int seekCount(UpdatePhoneBean updatePhoneBean) {
        return updatePhoneDao.seekCount(updatePhoneBean);
    }
}
