package com.sharkbaitextraordinaire.cayuse.integrations.elevation;

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

public class ElevationClient {

    private String apiKey = "";
    private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static String API_URL = "https://maps.googleapis.com/maps/api/elevation/json?locations=%s,%s&key=%s";

    public ElevationClient(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     *
     * @param location A geojson point with latitude & longitude
     * @return the elevation at the given point
     */
    public Double getElevationAtLocation(Point location) {
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

        Double elevation = null;

        if (response.getStatus() == 200) {
            JsonNode root = null;
            try {
                root = mapper.readTree(response.readEntity(String.class));
                elevation = root.get("results").get(0).get("elevation").asDouble();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.err.println("There was a problem with the request to the elevation API");
        }

        return elevation;
    }
}
