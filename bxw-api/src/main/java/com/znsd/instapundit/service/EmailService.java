package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.NoteBean;
import com.znsd.instapundit.param.NoteParam;

import java.util.List;

public interface EmailService {

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
