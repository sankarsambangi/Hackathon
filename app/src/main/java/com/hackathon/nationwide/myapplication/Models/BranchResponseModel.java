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
        "meta",
        "data"
})
public class BranchResponseModel {

    @JsonProperty("meta")
    private MetaDataModel meta;
    @JsonProperty("data")
    private BankListModel data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("meta")
    public MetaDataModel getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(MetaDataModel meta) {
        this.meta = meta;
    }

    @JsonProperty("data")
    public BankListModel getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(BankListModel data) {
        this.data = data;
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
