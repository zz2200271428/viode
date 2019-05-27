package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.NoteBean;
import com.znsd.instapundit.param.NoteParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmailDao {

    /*
    * email分页
    * */
    List<NoteBean> noteList(NoteParam noteParam);

    /*
     * 查询搜索email类型
     * */
    List<NoteBean> queryNoteType();

    /*
     * 查询分页总条数
     * */
    Integer queryNoteCount(NoteParam noteParam);
}
