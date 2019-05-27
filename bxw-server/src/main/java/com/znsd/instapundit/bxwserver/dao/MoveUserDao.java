package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.MoveUserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MoveUserDao {
    MoveUserBean queryList(@Param("moveUserBean")MoveUserBean moveUserBean);
    int addMoveUserBean(@Param("moveUserBean")MoveUserBean moveUserBean);
    int UpdateImg(MoveUserBean moveUserBean);
}
