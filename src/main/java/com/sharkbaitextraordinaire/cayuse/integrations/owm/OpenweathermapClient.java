package com.sharkbaitextraordinaire.cayuse.integrations.owm;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.*;
import org.glassfish.jersey.client.ClientConfig;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;
import java.io.IOException;


public class OpenweathermapClient {

    private String apiKey = "";
    private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static String API_URL = "https://api.openweathermap.org/data/2.5/weather?zip=%s,us&APPID=%s";

    public OpenweathermapClient(String ApiKey) {
        this.apiKey = ApiKey;

    }

    public boolean isValidZipCode(String zipcode) {
        // validate that string is five numeric digits
        return (zipcode.matches("[0-9]+") && zipcode.length() == 5);
    }

    /**
     * Fetch a response from OpenWeatherMap,
     * from which we can get the city name and temperature
     * @param zipcode
     * @return An OpenweathermapResponse or null
     */
    public OpenweathermapResponse getInfoForZipCode(String zipcode) {
        // should probably fail if we can't validate the zip code
        Configuration configuration = new ClientConfig();
        JerseyClient client = JerseyClientBuilder.createClient(configuration);
        JerseyWebTarget target = client.target(String.format(API_URL, zipcode, apiKey));

        System.out.printf("Using %s as uri\n", target.getUri());

        Invocation.Builder invocationBuilder = target.request();
        Response response = invocationBuilder.get();

        OpenweathermapResponse owmResponse = null;

        if (response.getStatus() == 200) {
            try {
                owmResponse = mapper.readValue(response.readEntity(String.class), OpenweathermapResponse.class);
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return owmResponse;
    }
}
