package com.azki.model;

import java.util.Objects;

public class PropertyInsurance implements Entity {
    private int id;
    private int branches;
    private int companies_id;
    private int user_id;
    private int ability;
    private float price;
    private float discount;
    private String city;
    private String province;
    private float score;
    private float contractTime;
    private String type;

    public PropertyInsurance(int id, int branches, int companies_id, int user_id, int ability, float price, float discount, String city, String province, float score, float contractTime, String type) {
        this.id = id;
        this.branches = branches;
        this.companies_id = companies_id;
        this.user_id = user_id;
        this.ability = ability;
        this.price = price;
        this.discount = discount;
        this.city = city;
        this.province = province;
        this.score = score;
        this.contractTime = contractTime;
        this.type = type;
    }

    public PropertyInsurance() {

    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getContractTime() {
        return contractTime;
    }

    public void setContractTime(float contractTime) {
        this.contractTime = contractTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBranches() {
        return branches;
    }

    public void setBranches(int branches) {
        this.branches = branches;
    }

    public int getCompanies_id() {
        return companies_id;
    }

    public void setCompanies_id(int companies_id) {
        this.companies_id = companies_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAbility() {
        return ability;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyInsurance that = (PropertyInsurance) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}