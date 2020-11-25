package com.gatech.cs6440.drugabuse.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@ApiModel(description = "The details of the users probablistic of drug abuse")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @ApiModelProperty(notes="The Patient ID from the FHIR Patient Resource")
    private String patientId;
    private String patientLink;
    @ApiModelProperty(notes="The Observation ID from the FHIR Observation Resource")
    private String observationId;
    private String observationLink;
    @ApiModelProperty(notes="The Encounter ID from the FHIR Encounter Resource")
    private String encounterId;
    private String encounterLink;
    @ApiModelProperty(notes="Attributes we want to track for the BoreNoMore App")
    private String name;
    private String gender;
    private String birthDate;
    private String age;
    private String maritalStatus;
    private String address;
    private String encounterStatus;
    private String encounterType;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String oximeterReading;
    private boolean isNormal;

    public UserDetails(){

    }

    public UserDetails(String patientId, String patientLink, String observationId, String observationLink, String encounterId, String encounterLink, String oximeterReading, String name, String gender, String birthDate, String age, String maritalStatus, String address, String encounterStatus, String encounterType, String city, String state, String country, String postalCode, boolean isNormal) {
        this.patientId = patientId;
        this.patientLink = patientLink;
        this.observationId = observationId;
        this.observationLink = observationLink;
        this.encounterId = encounterId;
        this.encounterLink = encounterLink;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.address = address;
        this.encounterStatus = encounterStatus;
        this.encounterType = encounterType;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.oximeterReading = oximeterReading;
        this.isNormal = isNormal;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "patientId='" + patientId + '\'' +
                ", patientLink='" + patientLink + '\'' +
                ", observationId='" + observationId + '\'' +
                ", observationLink='" + observationLink + '\'' +
                ", encounterId='" + encounterId + '\'' +
                ", encounterLink='" + encounterLink + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", age='" + age + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", address='" + address + '\'' +
                ", encounterStatus='" + encounterStatus + '\'' +
                ", encounterType='" + encounterType + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", oximeterReading='" + oximeterReading + '\'' +
                ", isNormal=" + isNormal +
                '}';
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientLink() {
        return patientLink;
    }

    public void setPatientLink(String patientLink) {
        this.patientLink = patientLink;
    }

    public String getObservationId() {
        return observationId;
    }

    public void setObservationId(String observationId) {
        this.observationId = observationId;
    }

    public String getObservationLink() {
        return observationLink;
    }

    public void setObservationLink(String observationLink) {
        this.observationLink = observationLink;
    }

    public String getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(String encounterId) {
        this.encounterId = encounterId;
    }

    public String getEncounterLink() {
        return encounterLink;
    }

    public void setEncounterLink(String encounterLink) {
        this.encounterLink = encounterLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEncounterStatus() {
        return encounterStatus;
    }

    public void setEncounterStatus(String encounterStatus) {
        this.encounterStatus = encounterStatus;
    }

    public String getEncounterType() {
        return encounterType;
    }

    public void setEncounterType(String encounterType) {
        this.encounterType = encounterType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getOximeterReading() {
        return oximeterReading;
    }

    public void setOximeterReading(String oximeterReading) {
        this.oximeterReading = oximeterReading;
    }

    public boolean isNormal() {
        return isNormal;
    }

    public void setNormal(boolean normal) {
        isNormal = normal;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

