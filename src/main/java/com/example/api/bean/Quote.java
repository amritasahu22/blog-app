package com.example.api.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
    private String type;

    @JsonProperty("value")
    private Value valueobj;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Value getValueobj() {
        return valueobj;
    }

    public void setValueobj(Value valueobj) {
        this.valueobj = valueobj;
    }

/*    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }*/

    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + valueobj +
                '}';
    }


}
