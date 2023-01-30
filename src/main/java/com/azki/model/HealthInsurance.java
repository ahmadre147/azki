package com.azki.model;

import java.util.Objects;

public class HealthInsurance implements Entity {
    private int id;
    private int visitAndDrug;
    private int companies_id;
    private int optic;
    private int hospitalization;
    private int eeg;
    private int ambulance;
    private int dental;
    private int hearingAid;
    private int surgery;
    private float discount;
    private float price;
    private int fracture;
    private int childBirth;
    private int infertility;
    private int sonography;
    private int labServices;
    private int compare;
    private int ordered;
    private int numOfPeople;
    private String type;
    private int user_id;
    private int peopleHealthInsurance_id;

    public HealthInsurance(int id, int visitAndDrug, int companies_id, int optic, int hospitalization, int eeg, int ambulance, int dental, int hearingAid, int surgery, float discount, float price, int fracture, int childBirth, int infertility, int sonography, int labServices, int compare, int ordered, int numOfPeople, String type, int user_id, int peopleHealthInsurance_id) {
        this.id = id;
        this.visitAndDrug = visitAndDrug;
        this.companies_id = companies_id;
        this.optic = optic;
        this.hospitalization = hospitalization;
        this.eeg = eeg;
        this.ambulance = ambulance;
        this.dental = dental;
        this.hearingAid = hearingAid;
        this.surgery = surgery;
        this.discount = discount;
        this.price = price;
        this.fracture = fracture;
        this.childBirth = childBirth;
        this.infertility = infertility;
        this.sonography = sonography;
        this.labServices = labServices;
        this.compare = compare;
        this.ordered = ordered;
        this.numOfPeople = numOfPeople;
        this.type = type;
        this.user_id = user_id;
        this.peopleHealthInsurance_id = peopleHealthInsurance_id;
    }

    public HealthInsurance() {

    }

    public int getPeopleHealthInsurance_id() {
        return peopleHealthInsurance_id;
    }

    public void setPeopleHealthInsurance_id(int peopleHealthInsurance_id) {
        this.peopleHealthInsurance_id = peopleHealthInsurance_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVisitAndDrug() {
        return visitAndDrug;
    }

    public void setVisitAndDrug(int visitAndDrug) {
        this.visitAndDrug = visitAndDrug;
    }

    public int getCompanies_id() {
        return companies_id;
    }

    public void setCompanies_id(int companies_id) {
        this.companies_id = companies_id;
    }

    public int getOptic() {
        return optic;
    }

    public void setOptic(int optic) {
        this.optic = optic;
    }

    public int getHospitalization() {
        return hospitalization;
    }

    public void setHospitalization(int hospitalization) {
        this.hospitalization = hospitalization;
    }

    public int getEeg() {
        return eeg;
    }

    public void setEeg(int eeg) {
        this.eeg = eeg;
    }

    public int getAmbulance() {
        return ambulance;
    }

    public void setAmbulance(int ambulance) {
        this.ambulance = ambulance;
    }

    public int getDental() {
        return dental;
    }

    public void setDental(int dental) {
        this.dental = dental;
    }

    public int getHearingAid() {
        return hearingAid;
    }

    public void setHearingAid(int hearingAid) {
        this.hearingAid = hearingAid;
    }

    public int getSurgery() {
        return surgery;
    }

    public void setSurgery(int surgery) {
        this.surgery = surgery;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getFracture() {
        return fracture;
    }

    public void setFracture(int fracture) {
        this.fracture = fracture;
    }

    public int getChildBirth() {
        return childBirth;
    }

    public void setChildBirth(int childBirth) {
        this.childBirth = childBirth;
    }

    public int getInfertility() {
        return infertility;
    }

    public void setInfertility(int infertility) {
        this.infertility = infertility;
    }

    public int getSonography() {
        return sonography;
    }

    public void setSonography(int sonography) {
        this.sonography = sonography;
    }

    public int getLabServices() {
        return labServices;
    }

    public void setLabServices(int labServices) {
        this.labServices = labServices;
    }

    public int getCompare() {
        return compare;
    }

    public void setCompare(int compare) {
        this.compare = compare;
    }

    public int getOrdered() {
        return ordered;
    }

    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        HealthInsurance that = (HealthInsurance) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
