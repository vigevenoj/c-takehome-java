package com.sharkbaitextraordinaire.integrations.timezone;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GoogleTimezoneResponseTest {

    private static ObjectMapper mapper = new ObjectMapper();

    String testResponseString = "{\n" +
            "   \"dstOffset\" : 0,\n" +
            "   \"rawOffset\" : -28800,\n" +
            "   \"status\" : \"OK\",\n" +
            "   \"timeZoneId\" : \"America/Los_Angeles\",\n" +
            "   \"timeZoneName\" : \"Pacific Standard Time\"\n" +
            "}";

    @Test
    public void getTimeZoneNameFromResponseTest() throws Exception {
        JsonNode root = mapper.readTree(testResponseString);
        String timeZoneName = root.get("timeZoneName").asText();
        assertEquals("Pacific Standard Time", timeZoneName);
    }
}
