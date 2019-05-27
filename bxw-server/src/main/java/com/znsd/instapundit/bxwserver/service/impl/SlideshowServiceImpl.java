package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.ImageBean;
import com.znsd.instapundit.bean.SlideshowBean;
import com.znsd.instapundit.bxwserver.dao.SlideshowDao;
import com.znsd.instapundit.param.ImageParam;
import com.znsd.instapundit.service.SlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class SlideshowServiceImpl implements SlideshowService {

    @Autowired
    private SlideshowDao slideshowDao;

    @Override
    public int addSlideshow(SlideshowBean slideshowBean) {
        return this.slideshowDao.addSlideshow(slideshowBean);
    }


    @Override
    public int deleteSlideshow(Integer[] ids) {
        return this.slideshowDao.deleteSlideshow(ids);
    }


    @Override
    public SlideshowBean selectSlideshowById(Integer id) {
        return this.slideshowDao.selectSlideshowById(id);
    }

    @Override
    public int saveSlideshow(SlideshowBean slideshowBean) {
        return this.slideshowDao.saveSlideshow(slideshowBean);
    }

    @Override
    public int getCount() {
        return this.slideshowDao.getCount();
    }

    @Override
    public List<SlideshowBean> queryLikeSlideshow(ImageParam imageParam) {
        return this.slideshowDao.queryLikeSlideshow(imageParam);
    }

    @Override
    public int updateStatus(SlideshowBean slideshowBean) {
        return this.slideshowDao.updateStatus(slideshowBean);
    }
}
