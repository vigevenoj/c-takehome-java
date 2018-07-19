package com.sharkbaitextraordinaire.cayuse.integrations.owm;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = OwmDeserializer.class)
public class OpenweathermapResponse {
    private Double temperature;
    private String cityName;

    public OpenweathermapResponse() { }

    public OpenweathermapResponse(String cityName, Double temperature) {
        this.cityName = cityName;
        this.temperature = temperature;
    }

    public Double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
