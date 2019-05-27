package com.znsd.instapundit.service;

import com.znsd.instapundit.param.AppUserParam;
import com.znsd.instapundit.result.PagingResult;

public interface AppMasterService {

    /**
     * @param pageBean
     * @return List<AppUserBean>
     * 分页查询
     */
    PagingResult listPage(AppUserParam pageBean);


}
