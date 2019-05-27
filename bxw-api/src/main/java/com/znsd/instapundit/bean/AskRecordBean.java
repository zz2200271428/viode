package com.znsd.instapundit.bean;

import java.io.Serializable;
import java.util.Date;

public class AskRecordBean implements Serializable {

    private Integer id;
    private AskBean askBean;
    private Integer type;
    private String issue;
    private Date record_time;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AskBean getAskBean() {
        return askBean;
    }

    public void setAskBean(AskBean askBean) {
        this.askBean = askBean;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Date getRecord_time() {
        return record_time;
    }

    public void setRecord_time(Date record_time) {
        this.record_time = record_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
