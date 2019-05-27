package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MoveUserBean implements Serializable {
        private Integer id;//主键
        private String phone;//手机号或账号
        private String password;//密码
        private String username;//用户名称
        private String id_card;//身份证号
        private String email;//用户邮箱
        private String birthday;//生日
        private String sex;//性别
        private Integer member;//会员
        private BigDecimal  money;//用户金额
        private Integer ro_id;//匹配所属角色
        private String picture;//头像
        @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date member_time;//会员到期时间
        private Integer status;//状态
        @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date created;//创建时间
        @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date updated;//修改信息时间

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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
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

        @Override
        public String toString() {
            return "MoveUserBean{" +
                    "id=" + id +
                    ", phone='" + phone + '\'' +
                    ", password='" + password + '\'' +
                    ", username='" + username + '\'' +
                    ", id_card='" + id_card + '\'' +
                    ", email='" + email + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", sex='" + sex + '\'' +
                    ", member=" + member +
                    ", money=" + money +
                    ", ro_id=" + ro_id +
                    ", picture='" + picture + '\'' +
                    ", member_time=" + member_time +
                    ", status=" + status +
                    ", created=" + created +
                    ", updated=" + updated +
                    '}';
        }
}
