package com.azki.model;

import java.util.Objects;

public class OTP implements Entity{
    private int id;
    private int code;
    private int user_id;

    public OTP(int id, int code, int user_id) {
        this.id = id;
        this.code = code;
        this.user_id = user_id;
    }

    public OTP() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OTP otp = (OTP) o;
        return id == otp.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
