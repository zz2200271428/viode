package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SpecialColumnBean implements Serializable {

    private Integer id; // 主键自增长
    private String spe_title; // 专栏标题
    private String spe_desc; // 专栏描述
    private String cover_img; // 专栏描述
    private AppUserBean create_by; // 创建人
    private Integer spe_status; // 专栏状态（1、正常2、冻结3、删除）
    private BigDecimal price; // 专栏价格
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss", timezone = "GMT+8")
    private Date create_time; // 创建时间
    private Integer createBy; // 创建人id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpe_title() {
        return spe_title;
    }

    public void setSpe_title(String spe_title) {
        this.spe_title = spe_title;
    }

    public String getSpe_desc() {
        return spe_desc;
    }

    public void setSpe_desc(String spe_desc) {
        this.spe_desc = spe_desc;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public AppUserBean getCreate_by() {
        return create_by;
    }

    public void setCreate_by(AppUserBean create_by) {
        this.create_by = create_by;
    }

    public Integer getSpe_status() {
        return spe_status;
    }

    public void setSpe_status(Integer spe_status) {
        this.spe_status = spe_status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "SpecialColumnBean{" +
                "id=" + id +
                ", spe_title='" + spe_title + '\'' +
                ", spe_desc='" + spe_desc + '\'' +
                ", cover_img='" + cover_img + '\'' +
                ", create_by=" + create_by +
                ", spe_status=" + spe_status +
                ", price=" + price +
                ", create_time=" + create_time +
                ", createBy=" + createBy +
                '}';
    }
}
