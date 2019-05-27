package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class DictBean implements Serializable {

    private Integer id;             //主键自增长
    private String type;            //字段类型
    private String label;           //字段标签
    private String values;          //字段值
    private Integer status;         //字段状态
    private String create_by;       //创建者
    private String update_by;       //修改者
    private String dict_desc;       //字段描述
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date create_time;       //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date update_time;       //修改时间
    private Integer is_system;       //是否为系统字段

    public Integer getIs_system() {
        return is_system;
    }

    public void setIs_system(Integer is_system) {
        this.is_system = is_system;
    }

    public String getDict_desc() {
        return dict_desc;
    }

    public void setDict_desc(String dict_desc) {
        this.dict_desc = dict_desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
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
}
