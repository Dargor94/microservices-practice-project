package com.example.mockserver.entity;

import com.example.mockserver.error.validation.customer.IValidCustomer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Customer {

    private Long customerId;
    @NotBlank
    @NotNull
    private String forename;
    @NotBlank
    private String surname;
    @NotNull
    private Long identificationNumber;
    @NotNull
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

    @IValidCustomer
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
