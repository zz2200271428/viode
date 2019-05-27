package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AppUserBean implements Serializable {
    private static final long serialVersionUID = -1895315026189815936L;
    private Integer id;         //用户id主键自动增长

    private String phone;      //手机号码

    private String password;//密码

    private String password2;    //密码

    private String username;    //用户名称
    private String id_card;     //身份证
    private String email;       //邮箱
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;      //生日
    private Integer sex;        //性别
    private Integer member;      //会员
    private BigDecimal money;   //用户金额
    private Integer ro_id;     //所属角色
    private String picture;     //头像
    private String code;        //验证码

    private TeacherApplyBean teacherApplyBean;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date member_time;   //会员到期时间
    private Integer status;     //状态（1:正常、2：冻结、3：删除）
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date created;   //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updated;   //修改时间
    private DictBean dictBean;  //字典表对象
    private Integer columnCount; // 拥有的专栏数
    private Integer videoCount; // 拥有的视频数

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getRo_id() {
        return ro_id;
    }

    public void setRo_id(Integer ro_id) {
        this.ro_id = ro_id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TeacherApplyBean getTeacherApplyBean() {
        return teacherApplyBean;
    }

    public void setTeacherApplyBean(TeacherApplyBean teacherApplyBean) {
        this.teacherApplyBean = teacherApplyBean;
    }

    public Date getMember_time() {
        return member_time;
    }

    public void setMember_time(Date member_time) {
        this.member_time = member_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public DictBean getDictBean() {
        return dictBean;
    }

    public void setDictBean(DictBean dictBean) {
        this.dictBean = dictBean;
    }

    public Integer getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(Integer columnCount) {
        this.columnCount = columnCount;
    }

    public Integer getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
    }

    @Override
    public String toString() {
        return "AppUserBean{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                ", username='" + username + '\'' +
                ", id_card='" + id_card + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", sex=" + sex +
                ", member=" + member +
                ", money=" + money +
                ", ro_id=" + ro_id +
                ", picture='" + picture + '\'' +
                ", code='" + code + '\'' +
                ", teacherApplyBean=" + teacherApplyBean +
                ", member_time=" + member_time +
                ", status=" + status +
                ", created=" + created +
                ", updated=" + updated +
                ", dictBean=" + dictBean +
                ", columnCount=" + columnCount +
                ", videoCount=" + videoCount +
                '}';
    }
}
