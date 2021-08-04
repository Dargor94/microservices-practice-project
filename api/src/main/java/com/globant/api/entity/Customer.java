package com.globant.api.entity;

public class Customer {

    private Long customerId;
    private String forename;
    private String surname;
    private Long identificationNumber;
    private IdentificationType identificationType;

    public Customer() {
    }

    public Customer(Long customerId, String forename, String surname, Long identificationNumber, IdentificationType identificationType) {
        this.customerId = customerId;
        this.forename = forename;
        this.surname = surname;
        this.identificationNumber = identificationNumber;
        this.identificationType = identificationType;
    }

    public Customer(String forename, String surname, Long identificationNumber, IdentificationType identificationType) {
        this.forename = forename;
        this.surname = surname;
        this.identificationNumber = identificationNumber;
        this.identificationType = identificationType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(Long identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public IdentificationType getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(IdentificationType identificationType) {
        this.identificationType = identificationType;
    }
}
