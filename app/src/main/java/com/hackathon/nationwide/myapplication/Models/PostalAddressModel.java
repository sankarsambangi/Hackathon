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
        "AddressLine",
        "BuildingNumber",
        "Country",
        "CountrySubDivision",
        "GeoLocation",
        "PostCode",
        "StreetName",
        "TownName"
})
public class PostalAddressModel {

    @JsonProperty("AddressLine")
    private List<String> addressLine = null;
    @JsonProperty("BuildingNumber")
    private String buildingNumber;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("CountrySubDivision")
    private List<String> countrySubDivision = null;
    @JsonProperty("GeoLocation")
    private GeoLocationModel geoLocation;
    @JsonProperty("PostCode")
    private String postCode;
    @JsonProperty("StreetName")
    private String streetName;
    @JsonProperty("TownName")
    private String townName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("AddressLine")
    public List<String> getAddressLine() {
        return addressLine;
    }

    @JsonProperty("AddressLine")
    public void setAddressLine(List<String> addressLine) {
        this.addressLine = addressLine;
    }

    @JsonProperty("BuildingNumber")
    public String getBuildingNumber() {
        return buildingNumber;
    }

    @JsonProperty("BuildingNumber")
    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    @JsonProperty("Country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("Country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("CountrySubDivision")
    public List<String> getCountrySubDivision() {
        return countrySubDivision;
    }

    @JsonProperty("CountrySubDivision")
    public void setCountrySubDivision(List<String> countrySubDivision) {
        this.countrySubDivision = countrySubDivision;
    }

    @JsonProperty("GeoLocation")
    public GeoLocationModel getGeoLocation() {
        return geoLocation;
    }

    @JsonProperty("GeoLocation")
    public void setGeoLocation(GeoLocationModel geoLocation) {
        this.geoLocation = geoLocation;
    }

    @JsonProperty("PostCode")
    public String getPostCode() {
        return postCode;
    }

    @JsonProperty("PostCode")
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @JsonProperty("StreetName")
    public String getStreetName() {
        return streetName;
    }

    @JsonProperty("StreetName")
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @JsonProperty("TownName")
    public String getTownName() {
        return townName;
    }

    @JsonProperty("TownName")
    public void setTownName(String townName) {
        this.townName = townName;
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
