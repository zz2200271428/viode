package com.znsd.instapundit.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class NoteParam extends BaseParam implements Serializable {

    private String phone;
    private String type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;
    private String email;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "NoteParam{" +
                "phone='" + phone + '\'' +
                ", type='" + type + '\'' +
                ", time=" + time +
                ", email='" + email + '\'' +
                '}';
    }
}
