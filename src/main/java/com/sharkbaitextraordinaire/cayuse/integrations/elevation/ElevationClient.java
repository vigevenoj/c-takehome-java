package com.sharkbaitextraordinaire.cayuse.integrations.elevation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ElevationClient {

    private String apiKey = "";
    private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static String API_URL = "https://maps.googleapis.com/maps/api/elevation/json?locations=%s,%s&key=%s";

    public ElevationClient(String apiKey) {
        this.apiKey = apiKey;
    }


}
