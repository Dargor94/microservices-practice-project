package com.globant.api.entity;

public class IdentificationType {

    private Long identificationTypeId;
    private String description;

    public IdentificationType() {
    }

    public IdentificationType(Long identificationTypeId, String description) {
        this.identificationTypeId = identificationTypeId;
        this.description = description;
    }

    public IdentificationType(Long identificationTypeId) {
        this.identificationTypeId = identificationTypeId;
    }

    public Long getIdentificationTypeId() {
        return identificationTypeId;
    }

    public void setIdentificationTypeId(Long identificationTypeId) {
        this.identificationTypeId = identificationTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
