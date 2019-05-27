package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.TeacherApplyBean;
import com.znsd.instapundit.param.StoreParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TeacherDao {
    //查询申请的讲师信息
    List<TeacherApplyBean> pageList(StoreParam storePage);

    //总条数
    Integer counter(StoreParam storePage);

    //申请通过
    Integer save(@Param("ids") Integer[] id);

    //拒绝审核
    Integer saveDel(@Param("ids") Integer []id);
}
