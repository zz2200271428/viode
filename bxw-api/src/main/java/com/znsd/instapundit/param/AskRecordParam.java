package com.znsd.instapundit.param;

import java.io.Serializable;

public class AskRecordParam implements Serializable {

    private Integer askId ;
    private String issue;
    private Integer type;

    public Integer getAskId() {
        return askId;
    }

    public void setAskId(Integer askId) {
        this.askId = askId;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
