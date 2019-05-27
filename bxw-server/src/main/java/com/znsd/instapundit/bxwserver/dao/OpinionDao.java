package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.OpinionBean;
import com.znsd.instapundit.param.OpinionParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OpinionDao {
    List<OpinionBean> listPage(OpinionParam opinionParam);

    int getCount(OpinionParam opinionParam);
}
