package com.azki.model;

import java.util.Objects;

public class PeopleHealthInsurance implements Entity {
    private int id;
    private String relation;
    private String baseInsurance;
    private String ageRange;

    public PeopleHealthInsurance(int id, String relation, String baseInsurance, String ageRange) {
        this.id = id;
        this.relation = relation;
        this.baseInsurance = baseInsurance;
        this.ageRange = ageRange;
    }

    public PeopleHealthInsurance() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getBaseInsurance() {
        return baseInsurance;
    }

    public void setBaseInsurance(String baseInsurance) {
        this.baseInsurance = baseInsurance;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeopleHealthInsurance that = (PeopleHealthInsurance) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
