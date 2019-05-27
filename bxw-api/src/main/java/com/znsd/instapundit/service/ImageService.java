package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.ImageBean;
import com.znsd.instapundit.bean.PageBean;
import com.znsd.instapundit.param.ImageParam;

import java.util.List;

public interface ImageService {

    //  查询所有的 图文列表
    public List<ImageBean> queryListImage(PageBean pageBean);

    /**
     *  增加图片
     * */
    public int  addImage(ImageBean image);
    /*
     *  删除 以及批量删除
     * */
    int deleteImage(Integer[] ids);
    /*
     *  根据 id  查看信息
     * */
    ImageBean selectImageById(Integer id);

    /*
     *   修改页面
     * */
    public int  saveImage(ImageBean image);


    int getCount();


    /*
     *   模糊搜索
     * */
    public List<ImageBean> queryLikeImage(ImageParam imageParam);

    int updateStatus(ImageBean imageBean);
}
