package com.azki.model;

import java.util.Objects;

public class User implements Entity {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String referralCode;
    private String remainingInvitations;
    private String password;

    public User(int id, String firstName, String lastName, String phoneNumber, String referralCode, String remainingInvitations, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.referralCode = referralCode;
        this.remainingInvitations = remainingInvitations;
        this.password = password;
    }

    public User() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getRemainingInvitations() {
        return remainingInvitations;
    }

    public void setRemainingInvitations(String remainingInvitations) {
        this.remainingInvitations = remainingInvitations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
