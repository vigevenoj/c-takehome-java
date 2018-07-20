package com.sharkbaitextraordinaire.cayuse.integrations.timezone;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.Point;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Response;

public class TimezoneClient {

    private String apiKey = "";
    private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static String API_URL = "https://maps.googleapis.com/maps/api/timezone/json?location=%s,%s&key=%s";

    public TimezoneClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getTimezoneName(Point location) {
        // should probably fail if we can't validate the zip code
        Configuration configuration = new ClientConfig();
        JerseyClient client = JerseyClientBuilder.createClient(configuration);
        JerseyWebTarget target = client.target(
                String.format(
                API_URL,
                location.getCoordinates().getLatitude(),
                location.getCoordinates().getLongitude(),
                apiKey));

        Invocation.Builder invocationBuilder = target.request();
        Response response = invocationBuilder.get();

        return null;
    }

}
