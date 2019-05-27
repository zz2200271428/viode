package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.PageBean;
import com.znsd.instapundit.bean.UpdateEmailBean;

import java.util.List;

public interface UpdateEmailService {
    List<UpdateEmailBean> listPage(PageBean pageBean);
    int listPageCount();
    List<UpdateEmailBean> seek(UpdateEmailBean updateEmailBean,PageBean pageBean);
    int seekCount(UpdateEmailBean updateEmailBean);
}
