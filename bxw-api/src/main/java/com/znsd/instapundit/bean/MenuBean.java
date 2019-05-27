package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
    菜单bean对象
*/
public class MenuBean implements Serializable {

    private Integer id;         //主键自动增长

    private String name;        //菜单名称

    private String url;         //菜单url

    private Integer parent_id; //父菜单id（0：代表父菜单、其他：父id）

    private String icon;        //菜单图标

    private Integer create_by; //创建人id

    private Integer update_by; //修改人id

    private String createname;  //创建人
    private String updatename;  //修改人

    private Integer status;     //状态（1：正常、2：删除）

    private Integer allot;      //是否被分配（1.是，2，否）

    private Integer mtype;      //菜单类型

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date create_time;   //创建时间

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date update_time;  //修改时间

    private List<MenuBean> sonMen; //子菜单

    public MenuBean() {
    }

    public Integer getMtype() {
        return mtype;
    }

    public void setMtype(Integer mtype) {
        this.mtype = mtype;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAllot() {
        return allot;
    }

    public void setAllot(Integer allot) {
        this.allot = allot;
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

    public List<MenuBean> getSonMen() {
        return sonMen;
    }

    public void setSonMen(List<MenuBean> sonMen) {
        this.sonMen = sonMen;
    }

    @Override
    public String toString() {
        return "MenuBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", parent_id=" + parent_id +
                ", icon='" + icon + '\'' +
                ", create_by=" + create_by +
                ", update_by=" + update_by +
                ", createname='" + createname + '\'' +
                ", updatename='" + updatename + '\'' +
                ", status=" + status +
                ", allot=" + allot +
                ", mtype=" + mtype +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", sonMen=" + sonMen +
                '}';
    }
}
