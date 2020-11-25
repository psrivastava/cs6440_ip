package com.gatech.cs6440.drugabuse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ApiModel(description = "Metadata for Clinical Status")
public class ClinicalStatus {
    @Id
    @ApiModelProperty(notes = "Id of clinical status")
    private Integer clinicalStatusId;
    private String clinicalStatusDesc;

    public ClinicalStatus() {

    }

    public ClinicalStatus(Integer clinicalStatusId, String clinicalStatusDesc) {
        this.clinicalStatusId = clinicalStatusId;
        this.clinicalStatusDesc = clinicalStatusDesc;
    }

    @Override
    public String toString() {
        return "ClinicalStatus{" +
                "clinicalStatusId='" + clinicalStatusId + '\'' +
                ", clinicalStatusDesc=" + clinicalStatusDesc +
                '}';
    }

    public Integer getClinicalStatusId() {
        return clinicalStatusId;
    }

    public void setClinicalStatusId(Integer clinicalStatusId) {
        this.clinicalStatusId = clinicalStatusId;
    }

    public String getClinicalStatusDesc() {
        return clinicalStatusDesc;
    }

    public void setClinicalStatusDesc(String clinicalStatusDesc) {
        this.clinicalStatusDesc = clinicalStatusDesc;
    }
}
