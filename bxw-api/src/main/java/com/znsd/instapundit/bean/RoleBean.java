package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
    角色bean对象
*/

public class RoleBean implements Serializable {

    private Integer id;              //主键自动增长

    private String name;            //角色名称

    private String desc;        //角色描述

    private Integer create_by;       //创建人id

    private Integer update_by;       //更新人id

    private Integer status;          //状态（1：正常、2：删除）

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date create_time;       //创建时间

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date update_time;        //修改时间

    private List<MenuBean> menuList;  //角色对应的菜单集合

    private String createname;  //创建人
    private String updatename;  //修改人
    private Integer allot;      //是否被分配（1.是，2，否）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCreate_by() {
        return create_by;
    }

    public void setCreate_by(Integer create_by) {
        this.create_by = create_by;
    }

    public Integer getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(Integer update_by) {
        this.update_by = update_by;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public List<MenuBean> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuBean> menuList) {
        this.menuList = menuList;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname;
    }

    public String getUpdatename() {
        return updatename;
    }

    public void setUpdatename(String updatename) {
        this.updatename = updatename;
    }

    public Integer getAllot() {
        return allot;
    }

    public void setAllot(Integer allot) {
        this.allot = allot;
    }

    @Override
    public String toString() {
        return "RoleBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", create_by=" + create_by +
                ", update_by=" + update_by +
                ", status=" + status +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", menuList=" + menuList +
                ", createname='" + createname + '\'' +
                ", updatename='" + updatename + '\'' +
                ", allot=" + allot +
                '}';
    }
}
