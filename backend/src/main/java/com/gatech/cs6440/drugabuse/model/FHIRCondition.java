package com.gatech.cs6440.drugabuse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ApiModel(description = "FHIR imported condition for a client")
public class FHIRCondition {
    @Id
    @ApiModelProperty(notes = "Id of FHIR Condition")
    private Integer fhirConditionId;
    @ApiModelProperty(notes = "The Id of client in FHIR database")
    private String clientId;
    private String code;
    @ApiModelProperty(notes = "Type of code in FHIR database. (Eg. LOINC)")
    private String codeType;
    @ApiModelProperty(notes = "Clinical Status Id from Clinical Status")
    private Integer clinicalStatus;

    public FHIRCondition() {

    }

    public FHIRCondition(Integer fhirConditionId, String clientId, String code, String codeType, Integer clinicalStatus) {
        this.fhirConditionId = fhirConditionId;
        this.clientId = clientId;
        this.code = code;
        this.codeType = codeType;
        this.clinicalStatus = clinicalStatus;
    }

    @Override
    public String toString() {
        return "FHIRCondition{" +
                "fhirConditionId='" + fhirConditionId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", code='" + code + '\'' +
                ", codeType='" + codeType + '\'' +
                ", clinicalStatus=" + clinicalStatus +
                '}';
    }

    public Integer getFhirConditionId() {
        return fhirConditionId;
    }

    public void setFhirConditionId(Integer fhirConditionId) {
        this.fhirConditionId = fhirConditionId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public Integer getClinicalStatus() {
        return clinicalStatus;
    }

    public void setClinicalStatus(Integer clinicalStatus) {
        this.clinicalStatus = clinicalStatus;
    }
}
