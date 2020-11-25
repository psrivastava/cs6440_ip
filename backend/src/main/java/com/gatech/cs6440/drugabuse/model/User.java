package com.gatech.cs6440.drugabuse.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "userId")
@ApiModel(description = "The details of user probablistic of drug abuse")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @ApiModelProperty(notes = "User Id of the user")
    private Integer userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String userName;
    private String password;
    private String roles;
    private String email;
    private String phone;
    @ApiModelProperty(notes = "The Gender type id from Gender Model")
    private String genderType;
    private String dateOfBirth;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    @ApiModelProperty(notes = "The marital status id from maritalStatus Model")
    private String maritalStatus;
    @ApiModelProperty(notes = "The user location information")
    private String userLatitude;
    private String userLongitude;
    private String vicinity;
    @ApiModelProperty(notes = "The FHIR Relation Patient Information obtained for the user")
    private String oximeterReading;
    private String patientId;
    private String patientLink;
    private String observationId;
    private String observationLink;
    private String encounterId;
    private String encounterLink;


    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToMany(targetEntity = Category.class,cascade = CascadeType.ALL)
    @JoinTable(name = "USER_CATEGORY",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    private Set<Category> categories;

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", genderType='" + genderType + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", userLatitude='" + userLatitude + '\'' +
                ", userLongitude='" + userLongitude + '\'' +
                ", vicinity='" + vicinity + '\'' +
                ", oximeterReading='" + oximeterReading + '\'' +
                ", patientId='" + patientId + '\'' +
                ", patientLink='" + patientLink + '\'' +
                ", observationId='" + observationId + '\'' +
                ", observationLink='" + observationLink + '\'' +
                ", encounterId='" + encounterId + '\'' +
                ", encounterLink='" + encounterLink + '\'' +
                ", categories=" + categories +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGenderType() {
        return genderType;
    }

    public void setGenderType(String genderType) {
        this.genderType = genderType;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(String userLatitude) {
        this.userLatitude = userLatitude;
    }

    public String getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(String userLongitude) {
        this.userLongitude = userLongitude;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getOximeterReading() {
        return oximeterReading;
    }

    public void setOximeterReading(String oximeterReading) {
        this.oximeterReading = oximeterReading;
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
