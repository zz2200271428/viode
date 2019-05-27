package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.SpecialCollectBean;
import com.znsd.instapundit.param.CollectParam;

import java.util.List;

public interface SpecialCollectService {


    /**
     *
     *   查询视图
     * */
    public List<SpecialCollectBean> queryList( CollectParam collectParam);



    /*
     *   统计页面
     * */

    public  int getCount();


    List<SpecialCollectBean> getCollectByUserId(Integer id);
}
