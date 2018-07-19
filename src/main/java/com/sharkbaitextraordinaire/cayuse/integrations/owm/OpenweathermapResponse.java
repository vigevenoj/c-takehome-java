package com.sharkbaitextraordinaire.cayuse.integrations.owm;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geojson.Point;

@JsonDeserialize(using = OwmDeserializer.class)
public class OpenweathermapResponse {
    private Double temperature;
    private String cityName;
    private Point location;

    public OpenweathermapResponse() { }

    public OpenweathermapResponse(String cityName, Double temperature, Point location) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.location = location;
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

    public Point getLocation() {
        return this.location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
