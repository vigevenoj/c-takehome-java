package com.sharkbaitextraordinaire.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        HashMap<String, Object> r = mapper.readValue(testResponseString, HashMap.class);
        assertNotNull(r);

        String cityName = (String) r.get("name");
        assertNotNull(cityName);
        assertEquals(cityName, "Mountain View");

        HashMap<String, Object> weatherMap  = (HashMap<String, Object>) r.get("main");
        Double temperature = (Double) weatherMap.get("temp");
        assertNotNull(temperature);
        assertEquals(temperature, Double.valueOf(285.68));
    }
}
