package com.hackathon.nationwide.myapplication.Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Accessibility",
        "Availability",
        "CustomerSegment",
        "Identification",
        "Name",
        "ContactInfo",
        "Photo",
        "PostalAddress",
        "SequenceNumber",
        "ServiceAndFacility",
        "Type"
})
public class BranchModel {

    @JsonProperty("Accessibility")
    private List<String> accessibility = null;
    @JsonProperty("Availability")
    private AvailabilityModel availability;
    @JsonProperty("CustomerSegment")
    private List<String> customerSegment = null;
    @JsonProperty("Identification")
    private String identification;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("ContactInfo")
    private List<ContactInfoModel> contactInfo = null;
    @JsonProperty("Photo")
    private String photo;
    @JsonProperty("PostalAddress")
    private PostalAddressModel postalAddress;
    @JsonProperty("SequenceNumber")
    private String sequenceNumber;
    @JsonProperty("ServiceAndFacility")
    private List<String> serviceAndFacility = null;
    @JsonProperty("Type")
    private String type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Accessibility")
    public List<String> getAccessibility() {
        return accessibility;
    }

    @JsonProperty("Accessibility")
    public void setAccessibility(List<String> accessibility) {
        this.accessibility = accessibility;
    }

    @JsonProperty("Availability")
    public AvailabilityModel getAvailability() {
        return availability;
    }

    @JsonProperty("Availability")
    public void setAvailability(AvailabilityModel availability) {
        this.availability = availability;
    }

    @JsonProperty("CustomerSegment")
    public List<String> getCustomerSegment() {
        return customerSegment;
    }

    @JsonProperty("CustomerSegment")
    public void setCustomerSegment(List<String> customerSegment) {
        this.customerSegment = customerSegment;
    }

    @JsonProperty("Identification")
    public String getIdentification() {
        return identification;
    }

    @JsonProperty("Identification")
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("ContactInfo")
    public List<ContactInfoModel> getContactInfo() {
        return contactInfo;
    }

    @JsonProperty("ContactInfo")
    public void setContactInfo(List<ContactInfoModel> contactInfo) {
        this.contactInfo = contactInfo;
    }

    @JsonProperty("Photo")
    public String getPhoto() {
        return photo;
    }

    @JsonProperty("Photo")
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @JsonProperty("PostalAddress")
    public PostalAddressModel getPostalAddress() {
        return postalAddress;
    }

    @JsonProperty("PostalAddress")
    public void setPostalAddress(PostalAddressModel postalAddress) {
        this.postalAddress = postalAddress;
    }

    @JsonProperty("SequenceNumber")
    public String getSequenceNumber() {
        return sequenceNumber;
    }

    @JsonProperty("SequenceNumber")
    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    @JsonProperty("ServiceAndFacility")
    public List<String> getServiceAndFacility() {
        return serviceAndFacility;
    }

    @JsonProperty("ServiceAndFacility")
    public void setServiceAndFacility(List<String> serviceAndFacility) {
        this.serviceAndFacility = serviceAndFacility;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

