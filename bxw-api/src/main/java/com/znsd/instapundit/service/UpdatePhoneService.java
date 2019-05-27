package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.PageBean;
import com.znsd.instapundit.bean.UpdatePhoneBean;

import java.util.List;

public interface UpdatePhoneService {
    List<UpdatePhoneBean> listPage(PageBean pageBean);
    int listPageCount();
    List<UpdatePhoneBean> seek(UpdatePhoneBean updatePhoneBean, PageBean pageBean);
    int seekCount(UpdatePhoneBean updatePhoneBean);
}
