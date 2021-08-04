package com.example.mockserver.entity;

import javax.validation.constraints.NotNull;

public class IdentificationType {

    @NotNull
    private Integer identificationTypeId;
    private String description;

    public IdentificationType() {
    }

    public IdentificationType(Integer identificationTypeId, String description) {
        this.identificationTypeId = identificationTypeId;
        this.description = description;
    }

    public IdentificationType(Integer identificationTypeId) {
        this.identificationTypeId = identificationTypeId;
    }

    public Integer getIdentificationTypeId() {
        return identificationTypeId;
    }

    public void setIdentificationTypeId(Integer identificationTypeId) {
        this.identificationTypeId = identificationTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
