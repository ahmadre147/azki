package com.azki.model;

import java.util.Objects;

public class Comments implements Entity {
    private int id;
    private int premium_rate;
    private int damage_rate;
    private int coverage_rate;
    private int support_rate;
    private String text;
    private int typeOfInsurance;
    private int companies_id;

    public Comments(int id, int premium_rate, int damage_rate, int coverage_rate, int support_rate, String text, int typeOfInsurance, int companies_id) {
        this.id = id;
        this.premium_rate = premium_rate;
        this.damage_rate = damage_rate;
        this.coverage_rate = coverage_rate;
        this.support_rate = support_rate;
        this.text = text;
        this.typeOfInsurance = typeOfInsurance;
        this.companies_id = companies_id;
    }

    public Comments() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPremium_rate() {
        return premium_rate;
    }

    public void setPremium_rate(int premium_rate) {
        this.premium_rate = premium_rate;
    }

    public int getDamage_rate() {
        return damage_rate;
    }

    public void setDamage_rate(int damage_rate) {
        this.damage_rate = damage_rate;
    }

    public int getCoverage_rate() {
        return coverage_rate;
    }

    public void setCoverage_rate(int coverage_rate) {
        this.coverage_rate = coverage_rate;
    }

    public int getSupport_rate() {
        return support_rate;
    }

    public void setSupport_rate(int support_rate) {
        this.support_rate = support_rate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTypeOfInsurance() {
        return typeOfInsurance;
    }

    public void setTypeOfInsurance(int typeOfInsurance) {
        this.typeOfInsurance = typeOfInsurance;
    }

    public int getCompanies_id() {
        return companies_id;
    }

    public void setCompanies_id(int companies_id) {
        this.companies_id = companies_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return id == comments.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
