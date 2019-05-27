package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.NoteBean;
import com.znsd.instapundit.param.MenuParam;
import com.znsd.instapundit.param.NoteParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Reference
    private NoteService noteService;

    /*
    * 返回短信管理页面
    * */
    @RequestMapping("/lists")
    public String getPate(Model model){
        List<NoteBean> noteTypeList = noteService.queryNoteType();
        model.addAttribute("type",noteTypeList);
        return "note/note-list";
    }

    /*
    * 分页查询
    * */
    @GetMapping("/list")
    @ResponseBody
    public PagingResult getList(NoteParam noteParam){
        List<NoteBean> noteBeanList = this.noteService.noteList(noteParam);
        return PagingResult.success(this.noteService.queryNoteCount(noteParam),noteBeanList);
    }

    /*
    * 返回详情页面
    * */
    @RequestMapping("/particulars")
    public String getParticulars(String note,Model model){
        model.addAttribute("note",note);
        return "note/note-particulars";
    }
}
