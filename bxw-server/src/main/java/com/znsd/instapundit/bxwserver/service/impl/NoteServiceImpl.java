package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.NoteBean;
import com.znsd.instapundit.bxwserver.dao.NoteDao;
import com.znsd.instapundit.param.NoteParam;
import com.znsd.instapundit.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;

    /*
     * 短信分页
     * */
    @Override
    public List<NoteBean> noteList(NoteParam noteParam) {
        return this.noteDao.noteList(noteParam);
    }


    /*
     * 查询搜索短信类型
     * */
    @Override
    public List<NoteBean> queryNoteType() {
        return this.noteDao.queryNoteType();
    }

    /*
     * 查询分页总条数
     * */

    @Override
    public Integer queryNoteCount(NoteParam noteParam) {
        return this.noteDao.queryNoteCount(noteParam);
    }
}
