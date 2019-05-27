package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.ImageBean;
import com.znsd.instapundit.bean.PageBean;
import com.znsd.instapundit.bxwserver.dao.ImageDao;
import com.znsd.instapundit.param.ImageParam;
import com.znsd.instapundit.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;
    /*
    *   查看所有
    * */
    @Override
    public List<ImageBean> queryListImage(PageBean pageBean) {
       return imageDao.queryListImage(pageBean);
    }
    /*
    *   增加
    * */
    @Override
    public int addImage(ImageBean image) {
        return this.imageDao.addImage(image);
    }
    /*
    *   删除
    * */
    @Override
    public int deleteImage(Integer[] ids) {
        return this.imageDao.deleteImage(ids);
    }
    /*
    * 根据Id查看
    * */
    @Override
    public ImageBean selectImageById(Integer id) {
        return this.imageDao.selectImageById(id);
    }
    /*
    * 修改
    * */
    @Override
    public int saveImage(ImageBean image) {
        return this.imageDao.saveImage(image);
    }

    @Override
    public int getCount() {
        return  this.imageDao.getCount();
    }

    @Override
    public List<ImageBean> queryLikeImage(ImageParam imageParam) {
        return this.imageDao.queryLikeImage(imageParam);
    }

    @Override
    public int updateStatus(ImageBean imageBean) {
        return this.imageDao.updateStatus(imageBean);
    }
}
