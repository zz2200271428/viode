package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.PageBean;
import com.znsd.instapundit.bean.UpdateEmailBean;
import com.znsd.instapundit.bxwserver.dao.UpdateEmailDao;
import com.znsd.instapundit.service.UpdateEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Service
@Component
public class UpdateEmailServiceImpl implements UpdateEmailService {
    @Autowired
    UpdateEmailDao updateEmailDao;
    @Override
    public List<UpdateEmailBean> listPage(PageBean pageBean) {
        return updateEmailDao.listPage(pageBean);
    }

    @Override
    public int listPageCount() {
        return updateEmailDao.listPageCount();
    }

    @Override
    public List<UpdateEmailBean> seek(UpdateEmailBean updateEmailBean, PageBean pageBean) {
        return updateEmailDao.seek(updateEmailBean,pageBean);
    }

    @Override
    public int seekCount(UpdateEmailBean updateEmailBean) {
        return updateEmailDao.seekCount(updateEmailBean);
    }
}
