package com.azki.model;

import java.util.Objects;

public class LifeInsurance implements Entity {
    private int id;
    private float medicalExpenses;
    private int companies_id;
    private float deathCapital;
    private float buybackValue;
    private int user_id;
    private int contractTime;

    public LifeInsurance(int id, float medicalExpenses, int companies_id, float deathCapital, float buybackValue, int user_id, int contractTime) {
        this.id = id;
        this.medicalExpenses = medicalExpenses;
        this.companies_id = companies_id;
        this.deathCapital = deathCapital;
        this.buybackValue = buybackValue;
        this.user_id = user_id;
        this.contractTime = contractTime;
    }

    public LifeInsurance() {

    }

    public int getContractTime() {
        return contractTime;
    }

    public void setContractTime(int contractTime) {
        this.contractTime = contractTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMedicalExpenses() {
        return medicalExpenses;
    }

    public void setMedicalExpenses(float medicalExpenses) {
        this.medicalExpenses = medicalExpenses;
    }

    public int getCompanies_id() {
        return companies_id;
    }

    public void setCompanies_id(int companies_id) {
        this.companies_id = companies_id;
    }

    public float getDeathCapital() {
        return deathCapital;
    }

    public void setDeathCapital(float deathCapital) {
        this.deathCapital = deathCapital;
    }

    public float getBuybackValue() {
        return buybackValue;
    }

    public void setBuybackValue(float buybackValue) {
        this.buybackValue = buybackValue;
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
        LifeInsurance that = (LifeInsurance) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
