package com.gatech.cs6440.drugabuse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ApiModel(description = "Metadata for Gender of User")
public class Gender {
    @Id
    @ApiModelProperty(notes = "Id of gender type")
    private Integer genderTypeId;
    private String genderTypeDesc;

    public Gender() {

    }

    public Gender(Integer genderTypeId, String genderTypeDesc) {
        this.genderTypeId = genderTypeId;
        this.genderTypeDesc = genderTypeDesc;
    }

    @Override
    public String toString() {
        return "Gender{" +
                "genderTypeId='" + genderTypeId + '\'' +
                ", genderTypeDesc=" + genderTypeDesc +
                '}';
    }

    public Integer getGenderTypeId() {
        return genderTypeId;
    }

    public void setGenderTypeId(Integer genderTypeId) {
        this.genderTypeId = genderTypeId;
    }

    public String getGenderTypeDesc() {
        return genderTypeDesc;
    }

    public void setGenderTypeDesc(String genderTypeDesc) {
        this.genderTypeDesc = genderTypeDesc;
    }
}
