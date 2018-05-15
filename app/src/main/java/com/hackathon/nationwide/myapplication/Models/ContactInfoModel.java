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
        "ContactType",
        "ContactContent"
})
public class ContactInfoModel {

    @JsonProperty("ContactType")
    private String contactType;
    @JsonProperty("ContactContent")
    private String contactContent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ContactType")
    public String getContactType() {
        return contactType;
    }

    @JsonProperty("ContactType")
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    @JsonProperty("ContactContent")
    public String getContactContent() {
        return contactContent;
    }

    @JsonProperty("ContactContent")
    public void setContactContent(String contactContent) {
        this.contactContent = contactContent;
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
