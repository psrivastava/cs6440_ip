package com.gatech.cs6440.drugabuse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ApiModel(description = "Link between User and FHIR")
public class UserFHIR {
    @Id
    @ApiModelProperty(notes = "Id of User FHIR Link")
    private Integer userFHIRId;
    private String clientId;
    @ApiModelProperty(notes = "User Id of the User")
    private Integer userId;

    public UserFHIR() {

    }

    public UserFHIR(Integer userFHIRId, String clientId, Integer userId) {
        this.userFHIRId = userFHIRId;
        this.clientId = clientId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserFHIR{" +
                "userFHIRId='" + userFHIRId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", userId=" + userId +
                '}';
    }

    public Integer getUserFHIRId() {
        return userFHIRId;
    }

    public void setUserFHIRId(Integer userFHIRId) {
        this.userFHIRId = userFHIRId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
