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
        "NonStandardAvailability",
        "StandardAvailability"
})
public class AvailabilityModel {

    @JsonProperty("NonStandardAvailability")
    private NonStandardAvailabilityModel nonStandardAvailability;
    @JsonProperty("StandardAvailability")
    private StandardAvailabilityModel standardAvailability;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("NonStandardAvailability")
    public NonStandardAvailabilityModel getNonStandardAvailability() {
        return nonStandardAvailability;
    }

    @JsonProperty("NonStandardAvailability")
    public void setNonStandardAvailability(NonStandardAvailabilityModel nonStandardAvailability) {
        this.nonStandardAvailability = nonStandardAvailability;
    }

    @JsonProperty("StandardAvailability")
    public StandardAvailabilityModel getStandardAvailability() {
        return standardAvailability;
    }

    @JsonProperty("StandardAvailability")
    public void setStandardAvailability(StandardAvailabilityModel standardAvailability) {
        this.standardAvailability = standardAvailability;
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
