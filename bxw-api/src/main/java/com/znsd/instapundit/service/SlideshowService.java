package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.ImageBean;
import com.znsd.instapundit.bean.PageBean;
import com.znsd.instapundit.bean.SlideshowBean;
import com.znsd.instapundit.param.ImageParam;

import java.util.List;

public interface SlideshowService {

    /**
     *  增加图片
     * */
    public int  addSlideshow(SlideshowBean slideshowBean);
    /*
     *  删除 以及批量删除
     * */
    int deleteSlideshow(Integer[] ids);
    /*
     *  根据 id  查看信息
     * */
    SlideshowBean selectSlideshowById(Integer id);

    /*
     *   修改页面
     * */
    public int  saveSlideshow(SlideshowBean slideshowBean);

    int getCount();
    /*
     *   模糊搜索
     * */
    public List<SlideshowBean> queryLikeSlideshow(ImageParam imageParam);

    int updateStatus(SlideshowBean slideshowBean);
}
