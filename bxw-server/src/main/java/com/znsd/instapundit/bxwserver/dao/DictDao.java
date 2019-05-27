package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.DictBean;
import com.znsd.instapundit.param.DictParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictDao {
    List<DictBean> listPage(DictParam dictParam);

    int addDict(DictBean dictBean);

    DictBean getDictById(@Param("id") Integer id);

    int updateDict(DictBean dictBean);

    int delete(@Param("ids") Integer[] ids);

    List<DictBean> getLabel();

    int getCount(DictParam dictParam);

    List<DictBean> getListType(String type);

}
