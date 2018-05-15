package com.hackathon.nationwide.myapplication.Models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "LastUpdated",
        "TotalResults",
        "Agreement",
        "License",
        "TermsOfUse"
})
public class MetaDataModel {

    @JsonProperty("LastUpdated")
    private String lastUpdated;
    @JsonProperty("TotalResults")
    private Integer totalResults;
    @JsonProperty("Agreement")
    private String agreement;
    @JsonProperty("License")
    private String license;
    @JsonProperty("TermsOfUse")
    private String termsOfUse;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("LastUpdated")
    public String getLastUpdated() {
        return lastUpdated;
    }

    @JsonProperty("LastUpdated")
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @JsonProperty("TotalResults")
    public Integer getTotalResults() {
        return totalResults;
    }

    @JsonProperty("TotalResults")
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @JsonProperty("Agreement")
    public String getAgreement() {
        return agreement;
    }

    @JsonProperty("Agreement")
    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    @JsonProperty("License")
    public String getLicense() {
        return license;
    }

    @JsonProperty("License")
    public void setLicense(String license) {
        this.license = license;
    }

    @JsonProperty("TermsOfUse")
    public String getTermsOfUse() {
        return termsOfUse;
    }

    @JsonProperty("TermsOfUse")
    public void setTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
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
