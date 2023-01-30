package com.azki.model;

import java.util.Objects;

public class Companies implements Entity {
    private int id;
    private String plan;
    private String name;
    private String logo;

    public Companies(int id, String plan, String name, String logo) {
        this.id = id;
        this.plan = plan;
        this.name = name;
        this.logo = logo;
    }

    public Companies() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Companies companies = (Companies) o;
        return id == companies.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
