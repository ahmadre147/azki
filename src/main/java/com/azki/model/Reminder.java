package com.azki.model;

import java.sql.Date;
import java.util.Objects;

public class Reminder implements Entity{
    private int id;
    private String type;
    private Date time;
    private int remindPeriod;
    private int user_id;

    public Reminder(int id, String type, Date time, int remindPeriod, int user_id) {
        this.id = id;
        this.type = type;
        this.time = time;
        this.remindPeriod = remindPeriod;
        this.user_id = user_id;
    }

    public Reminder(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getRemindPeriod() {
        return remindPeriod;
    }

    public void setRemindPeriod(int remindPeriod) {
        this.remindPeriod = remindPeriod;
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
        Reminder reminder = (Reminder) o;
        return id == reminder.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
