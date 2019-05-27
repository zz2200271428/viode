package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.StoreBean;
import com.znsd.instapundit.param.StoreParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreDao {

    StoreBean getStoreApplyByID(@Param("storeID") Integer storeID);

    int updateCheckStatus(@Param("storeID") Integer storeID, @Param("check_status") Integer check_status);

    int insertStore(StoreBean store);

    List<StoreBean> pagingApplyList(StoreParam param);

    int getPagingApplyCount(StoreParam param);

    int delete(@Param("ids") Integer[] ids);

    int updateStatus(@Param("storeID") Integer storeID, @Param("status") Integer status);

    List<StoreBean> pagingStoreList(StoreParam param);

    int getPagingStoreCount(StoreParam param);

    StoreBean getStoreByID(@Param("storeID") Integer storeID);

    List<StoreBean> getStoreByActive(@Param("active") Integer active);

    int updateClickCount(@Param("storeID") Integer storeID);

    int insertStoreCollect(@Param("userID") Integer userID, @Param("storeID") Integer storeID);

    Integer getCollectCount(@Param("userID") Integer userID, @Param("storeID") Integer storeID);

    Integer insertCollect(@Param("userID") Integer userID, @Param("storeID") Integer storeID);

    int deleteCollect(@Param("userID") Integer userID, @Param("storeID") Integer storeID);

    Integer updateAskCount(@Param("storeID") Integer storeID);
}
