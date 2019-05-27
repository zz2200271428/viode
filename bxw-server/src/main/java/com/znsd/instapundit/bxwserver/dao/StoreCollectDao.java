package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.StoreCollectBean;
import com.znsd.instapundit.param.StoreCollectParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreCollectDao  {

    public List<StoreCollectBean>   queryStroeList(StoreCollectParam storeCollectParam);

    public  int  queryCount();


    List<StoreCollectBean> getCollectByUserId(@Param("id") Integer id);
}
