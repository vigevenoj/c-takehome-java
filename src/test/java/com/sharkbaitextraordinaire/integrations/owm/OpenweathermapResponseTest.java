package com.sharkbaitextraordinaire.integrations.owm;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sharkbaitextraordinaire.cayuse.integrations.owm.OpenweathermapResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class OpenweathermapResponseTest {
    private static ObjectMapper mapper = new ObjectMapper();

    // Sample response for zipcode request, from the openweathermap API documentation
    private final String testResponseString = "{\"coord\":{\"lon\":-122.09,\"lat\":37.39},\n" +
            "\"sys\":{\"type\":3,\"id\":168940,\"message\":0.0297,\"country\":\"US\",\"sunrise\":1427723751,\"sunset\":1427768967},\n" +
            "\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"Sky is Clear\",\"icon\":\"01n\"}],\n" +
            "\"base\":\"stations\",\n" +
            "\"main\":{\"temp\":285.68,\"humidity\":74,\"pressure\":1016.8,\"temp_min\":284.82,\"temp_max\":286.48},\n" +
            "\"wind\":{\"speed\":0.96,\"deg\":285.001},\n" +
            "\"clouds\":{\"all\":0},\n" +
            "\"dt\":1427700245,\n" +
            "\"id\":0,\n" +
            "\"name\":\"Mountain View\",\n" +
            "\"cod\":200}";


    @Test
    public void getWeatherAndCityNameFromResponseTest() throws Exception {

        JsonNode root = mapper.readTree(testResponseString);
        String cityName = root.get("name").asText();
        assertEquals("Mountain View", cityName);

        Double temperature = root.get("main").get("temp").asDouble();
        assertEquals(Double.valueOf(285.68), temperature);
    }

    @Test
    public void deserializeWithCustomDeserializerTest() throws Exception {
        OpenweathermapResponse r = mapper.readValue(testResponseString, OpenweathermapResponse.class);
        assertEquals("Mountain View", r.getCityName());
        assertEquals(Double.valueOf(285.68), r.getTemperature());

        Double lon = r.getLocation().getCoordinates().getLongitude();
        Double lat = r.getLocation().getCoordinates().getLatitude();
        assertEquals(Double.valueOf(-122.09), lon);
        assertEquals(Double.valueOf(37.39), lat);
    }
}
