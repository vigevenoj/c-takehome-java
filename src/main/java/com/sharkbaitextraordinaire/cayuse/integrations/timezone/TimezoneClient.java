package com.sharkbaitextraordinaire.cayuse.integrations.timezone;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.Point;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.Instant;

public class TimezoneClient {

    private String apiKey = "";
    private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static String API_URL = "https://maps.googleapis.com/maps/api/timezone/json?location=%s,%s&timestamp=%s&key=%s";

    public TimezoneClient(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Fetch a response from the Google Timezone API and parse out the name of the timezone
     * @param location A geojson point with latitude & longitude
     * @return The name of the time zone containing the point
     */
    public String getTimezoneNameForLocation(Point location) {

        Configuration configuration = new ClientConfig();
        JerseyClient client = JerseyClientBuilder.createClient(configuration);
        JerseyWebTarget target = client.target(
                String.format(
                API_URL,
                location.getCoordinates().getLatitude(),
                location.getCoordinates().getLongitude(),
                Instant.now().getEpochSecond(),
                apiKey));

        Invocation.Builder invocationBuilder = target.request();
        Response response = invocationBuilder.get();

        String timeZoneName = "Unknown";

        if(response.getStatus() != 200) {
            System.err.println("There was a problem with the request to the timezone API");
        }
        try {
            JsonNode root = mapper.readTree(response.readEntity(String.class));
            timeZoneName = root.get("timeZoneName").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return timeZoneName;
    }

}
