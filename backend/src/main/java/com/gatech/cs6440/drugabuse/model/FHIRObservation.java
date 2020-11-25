package com.gatech.cs6440.drugabuse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ApiModel(description = "FHIR imported observation for a client")
public class FHIRObservation {
    @Id
    @ApiModelProperty(notes = "Id of FHIR Observation")
    private Integer fhirObservationId;
    @ApiModelProperty(notes = "The Id of client in FHIR database")
    private String clientId;
    private String code;
    @ApiModelProperty(notes = "Type of code in FHIR database. (Eg. LOINC)")
    private String codeType;


    public FHIRObservation() {

    }

    public FHIRObservation(Integer fhirObservationId, String clientId, String code, String codeType, Integer clinicalStatus) {
        this.fhirObservationId = fhirObservationId;
        this.clientId = clientId;
        this.code = code;
        this.codeType = codeType;
    }

    @Override
    public String toString() {
        return "FHIRObservation{" +
                "fhirObservationId='" + fhirObservationId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", code='" + code + '\'' +
                ", codeType=" + codeType +
                '}';
    }

    public Integer getFhirObservationId() {
        return fhirObservationId;
    }

    public void setFhirObservationId(Integer fhirObservationId) {
        this.fhirObservationId = fhirObservationId;
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
}
