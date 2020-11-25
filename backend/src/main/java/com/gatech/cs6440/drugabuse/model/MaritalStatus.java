package com.gatech.cs6440.drugabuse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ApiModel(description = "Metadata for Marital Status")
public class MaritalStatus {
    @Id
    @ApiModelProperty(notes = "Id of marital status")
    private Integer maritalStatusId;
    private String maritalStatusDesc;

    public MaritalStatus() {

    }

    public MaritalStatus(Integer maritalStatusId, String maritalStatusDesc) {
        this.maritalStatusId = maritalStatusId;
        this.maritalStatusDesc = maritalStatusDesc;
    }

    @Override
    public String toString() {
        return "MaritalStatus{" +
                "maritalStatusId='" + maritalStatusId + '\'' +
                ", maritalStatusDesc=" + maritalStatusDesc +
                '}';
    }

    public Integer getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(Integer maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public String getMaritalStatusDesc() {
        return maritalStatusDesc;
    }

    public void setMaritalStatusDesc(String maritalStatusDesc) {
        this.maritalStatusDesc = maritalStatusDesc;
    }
}
