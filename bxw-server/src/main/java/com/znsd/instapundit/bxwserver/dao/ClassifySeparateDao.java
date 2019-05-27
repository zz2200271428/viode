package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.ClassifySeparateBean;
import com.znsd.instapundit.param.ClassifySeparateParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassifySeparateDao {
    /**
     * 查询全部
     *
     * @param classifySeparateParam
     * @return
     */
    List<ClassifySeparateBean> listPage(ClassifySeparateParam classifySeparateParam);

    /**
     * 查询总条数
     */
    Integer count(ClassifySeparateParam classifySeparateParam);
}
