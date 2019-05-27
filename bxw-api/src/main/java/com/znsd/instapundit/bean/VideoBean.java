package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 视频表 tb_video
 */
public class VideoBean implements Serializable {

    private Integer id; // 主键
    private AppUserBean upload_by; // 上传人
    private String video_title; // 视频标题
    private String video_intro; // 视频简介
    private String video_url; // 视频地址
    private Integer video_status; // 视频状态（1：正常、2：冻结、3：删除）
    private DictBean video_type; // 视频类型
    private Integer check_status; // 审核状态
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss", timezone = "GMT+8")
    private Date upload_time; // 上传时间
    private Integer play_amount; // 播放量
    private BigDecimal price = new BigDecimal(0); // 视频价格，默认0免费或者会员
    private SpecialColumnBean specialColumnBean; // 所属栏目
    private Integer collect; // 收藏（视频收藏表数据统计冗余字段）
    private Integer video_length; // 视频长度
    private String cover_img; // 视频封面地址
    private Integer from_sc = 0; // 所属专栏id默认0没有专栏
    private Integer userID; // 用户id
    private Integer videoType; // 视频类型

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AppUserBean getUpload_by() {
        return upload_by;
    }

    public void setUpload_by(AppUserBean upload_by) {
        this.upload_by = upload_by;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public String getVideo_intro() {
        return video_intro;
    }

    public void setVideo_intro(String video_intro) {
        this.video_intro = video_intro;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public Integer getVideo_status() {
        return video_status;
    }

    public void setVideo_status(Integer video_status) {
        this.video_status = video_status;
    }

    public DictBean getVideo_type() {
        return video_type;
    }

    public void setVideo_type(DictBean video_type) {
        this.video_type = video_type;
    }

    public Integer getCheck_status() {
        return check_status;
    }

    public void setCheck_status(Integer check_status) {
        this.check_status = check_status;
    }

    public Date getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(Date upload_time) {
        this.upload_time = upload_time;
    }

    public Integer getPlay_amount() {
        return play_amount;
    }

    public void setPlay_amount(Integer play_amount) {
        this.play_amount = play_amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public SpecialColumnBean getSpecialColumnBean() {
        return specialColumnBean;
    }

    public void setSpecialColumnBean(SpecialColumnBean specialColumnBean) {
        this.specialColumnBean = specialColumnBean;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public Integer getVideo_length() {
        return video_length;
    }

    public void setVideo_length(Integer video_length) {
        this.video_length = video_length;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public Integer getFrom_sc() {
        return from_sc;
    }

    public void setFrom_sc(Integer from_sc) {
        this.from_sc = from_sc;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getVideoType() {
        return videoType;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    @Override
    public String toString() {
        return "VideoBean{" +
                "id=" + id +
                ", upload_by=" + upload_by +
                ", video_title='" + video_title + '\'' +
                ", video_intro='" + video_intro + '\'' +
                ", video_url='" + video_url + '\'' +
                ", video_status=" + video_status +
                ", video_type=" + video_type +
                ", check_status=" + check_status +
                ", upload_time=" + upload_time +
                ", play_amount=" + play_amount +
                ", price=" + price +
                ", specialColumnBean=" + specialColumnBean +
                ", collect=" + collect +
                ", video_length=" + video_length +
                ", cover_img='" + cover_img + '\'' +
                ", from_sc=" + from_sc +
                ", userID=" + userID +
                ", videoType=" + videoType +
                '}';
    }
}
