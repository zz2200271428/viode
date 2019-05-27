package com.znsd.instapundit.param;

import java.io.Serializable;

public class StoreCollectParam implements Serializable {

    private Integer page; // 显示总页数
    private Integer limit; // 显示条数
    private String sname; // 店铺名称
    private String username; // 用户真实姓名

    private Integer click_amount;       // 访问量

    @Override
    public String toString() {
        return "StoreCollectParam{" +
                "page=" + page +
                ", limit=" + limit +
                ", sname='" + sname + '\'' +
                ", username='" + username + '\'' +
                ", click_amount=" + click_amount +
                '}';
    }

    public Integer getClick_amount() {
        return click_amount;
    }

    public void setClick_amount(Integer click_amount) {
        this.click_amount = click_amount;
    }

    public Integer getPage() {
        return page;
    }




    public void setPage(Integer page) {
        this.page = (page - 1) * limit;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }


}
