package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ClassifySeparateBean implements Serializable {

    private Integer id;         //主键id

    private String content;     //搜索关键字

    private Integer count;      //搜索次数

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date time;          //最后搜索时间

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date create_time;   //创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "ClassifySeparateBean{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", count=" + count +
                ", time=" + time +
                ", create_time=" + create_time +
                '}';
    }
}
