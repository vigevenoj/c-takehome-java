package com.sharkbaitextraordinaire.cayuse.integrations.elevation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GoogleElevationResponseTest {

    private static ObjectMapper mapper = new ObjectMapper();

    String testResponseString = "{\n" +
            "   \"results\" : [\n" +
            "      {\n" +
            "         \"elevation\" : 1608.637939453125,\n" +
            "         \"location\" : {\n" +
            "            \"lat\" : 39.73915360,\n" +
            "            \"lng\" : -104.98470340\n" +
            "         },\n" +
            "         \"resolution\" : 4.771975994110107\n" +
            "      }\n" +
            "   ],\n" +
            "   \"status\" : \"OK\"\n" +
            "}";
    @Test
    public void getElevationFromResponseTest() throws Exception {
        JsonNode root = mapper.readTree(testResponseString);
        Double elevation = root.get("results").get(0).get("elevation").asDouble();
        assertEquals(Double.valueOf(1608.637939453125), elevation);
    }
}
