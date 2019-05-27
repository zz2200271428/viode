package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.SpecialCollectBean;
import com.znsd.instapundit.param.CollectParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SpecialCollectDao  {

    /**
     *
     *   查询视图
     * */
    public List<SpecialCollectBean> queryList(CollectParam collectParam);

    /*
    *   统计页面
    * */

    public  int getCount();

    List<SpecialCollectBean> getCollectByUserId(@Param("id") Integer id);
}
