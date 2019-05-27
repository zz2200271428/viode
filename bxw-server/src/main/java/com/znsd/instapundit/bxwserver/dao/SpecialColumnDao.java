package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.SpecialColumnBean;
import com.znsd.instapundit.param.SpecialColumnParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SpecialColumnDao {

    List<SpecialColumnBean> pagingList(SpecialColumnParam param);

    int getPagingCount(SpecialColumnParam param);

    int delete(@Param("ids") Integer[] ids);

    int clearVideoSpe(@Param("ids") Integer[] ids);

    SpecialColumnBean getSpecByID(@Param("columnID") Integer columnID);

    List<SpecialColumnBean> getColumnByUserID(@Param("userID") Integer userID);
}
