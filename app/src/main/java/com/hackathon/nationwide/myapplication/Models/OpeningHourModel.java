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
        "ClosingTime",
        "OpeningTime"
})
public class OpeningHourModel {

    @JsonProperty("ClosingTime")
    private String closingTime;
    @JsonProperty("OpeningTime")
    private String openingTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ClosingTime")
    public String getClosingTime() {
        return closingTime;
    }

    @JsonProperty("ClosingTime")
    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    @JsonProperty("OpeningTime")
    public String getOpeningTime() {
        return openingTime;
    }

    @JsonProperty("OpeningTime")
    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
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
