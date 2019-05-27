package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.ImageBean;
import com.znsd.instapundit.bean.SlideshowBean;
import com.znsd.instapundit.param.ImageParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SlideshowDao {

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
