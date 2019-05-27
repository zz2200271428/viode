package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.bean.ClassifySeparateBean;
import com.znsd.instapundit.bean.SpecialColumnBean;
import com.znsd.instapundit.bean.VideoBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SearchDao {
    /**
     * 查询热键
     *
     * @return
     */
    public List<ClassifySeparateBean> getKeyword();

    /**
     * 增加热键
     *
     * @param text
     * @param count
     */
    void addContent(@Param("content") String text, @Param("count") Integer count);

    /**
     * 插入热键条数
     *
     * @param text
     */
    void updateCount(@Param("content") String text);

    /**
     * 热键模糊搜索
     *
     * @param content
     * @return
     */
    List<VideoBean> listVideo(@Param("content") String content);

    /**
     * 查询讲师所有更多视频
     *
     * @param id
     * @return
     */
    List<VideoBean> listMore(@Param("id") Integer id,@Param("video_type") Integer video_type);


    /**
     * 查询专栏所有视频
     *
     * @param specialID
     * @return
     */
    List<VideoBean> listSpecial(@Param("id") Integer specialID,@Param("video_type") Integer video_type);

    /**
     * 查询所有专栏
     *
     * @param id
     * @return
     */
    List<SpecialColumnBean> specialAll(@Param("id") Integer id);

    /**
     * 查询所属专栏
     *
     * @param id
     * @param specialID
     * @return
     */
    SpecialColumnBean querySpecial(@Param("uId") Integer id, @Param("scId") Integer specialID);


}
