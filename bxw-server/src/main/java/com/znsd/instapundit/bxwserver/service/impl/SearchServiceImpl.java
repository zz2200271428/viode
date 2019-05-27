package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.ClassifySeparateBean;
import com.znsd.instapundit.bean.SpecialColumnBean;
import com.znsd.instapundit.bean.VideoBean;
import com.znsd.instapundit.bxwserver.dao.SearchDao;
import com.znsd.instapundit.bxwserver.dao.VideoDao;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;
    @Autowired
    private VideoDao videoDao;
    /**
     * 查询热键
     *
     * @return
     */
    @Override
    public List<ClassifySeparateBean> getKeyword() {
        return searchDao.getKeyword();
    }

    /**
     * 增加关键字
     *
     * @param
     */
    @Override
    public void addContent(String text) {
        Integer count = 1;
        try {
            searchDao.addContent(text, count);
        } catch (Exception e) {
            searchDao.updateCount(text);
        }
    }

    /**
     * 模糊搜索热键
     *
     * @param text
     * @return
     */
    @Override
    public List<VideoBean> listVideo(String text) {
        return searchDao.listVideo(text);
    }

    /**
     * 查询讲师更多视频
     *
     * @param id
     * @return
     */
    @Override
    public List<VideoBean> listMore(Integer id,Integer video_type) {
        return searchDao.listMore(id,video_type);
    }



    /**
     * 查询讲师专栏
     *
     * @param
     * @return
     */
    @Override
    public List<VideoBean> listSpecial(Integer specialID,Integer video_type) {
        return searchDao.listSpecial(specialID,video_type);
    }

    /**
     * 查询讲师专栏封面
     *
     * @param
     * @return
     */
    @Override
    public List<SpecialColumnBean> specialAll(Integer id) {
        return searchDao.specialAll(id);
    }

    /**
     * 查询讲师专栏里的视频
     *
     * @param id
     * @param specialID
     * @return
     */
    @Override
    public SpecialColumnBean querySpecial(Integer id, Integer specialID) {
        return searchDao.querySpecial(id, specialID);
    }

    @Override
    public boolean collect(Integer specialID, Integer id, boolean flag) {
        int count = 0;
        if (flag) {
                count = videoDao.insertColumnCollect(specialID, id);
        } else {
                count = videoDao.deleteColumnCollect(specialID, id);
        }
        if (count == 0) {
            throw new GlobalException(CodeMsg.COLLECT_ERROR);
        }
        return flag;
    }

    @Override
    public boolean isCollect(Integer specialID, Integer id) {
        int count = 0;
        count = videoDao.isColumnCollect(specialID,id);
        return count > 0;
    }


}
