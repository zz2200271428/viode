package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.AskBean;
import com.znsd.instapundit.bean.AskRecordBean;
import com.znsd.instapundit.bean.StoreBean;
import com.znsd.instapundit.param.AskRecordParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface AskDao {

    List<AskBean> getAskByStoreID(@Param("storeID") Integer storeID);

    List<AskBean> getAskByUserID(@Param("userID") Integer userID);

    Integer insertIssue(@Param("userID") Integer userID, @Param("storeID") Integer storeID, @Param("issue") String issue, @Param("reward") BigDecimal reward);

    List<AskBean> getAskByStoreIDAndUserID(@Param("storeID") Integer storeID, @Param("userID") Integer userID);

    Integer updateAskcount(@Param("storeID") Integer storeID);

    AskBean getAskByAskId(@Param("id") Integer id);

    List<AskRecordBean> getRecordByAskById(@Param("id")Integer id);

    int addRecord(AskRecordParam askRecordParam);

    StoreBean getUserByStoreId(@Param("id") Integer id);
}
