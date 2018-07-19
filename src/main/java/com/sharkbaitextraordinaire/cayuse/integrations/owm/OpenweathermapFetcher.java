package com.sharkbaitextraordinaire.cayuse.integrations.owm;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.*;
import org.glassfish.jersey.client.ClientConfig;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;


public class OpenweathermapFetcher {

    private String ApiKey = "";
    private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static String API_URL = "api.openweathermap.org/data/2.5/weather?zip=%s,us&APPID=%s";

    public OpenweathermapFetcher(String ApiKey) {
        this.ApiKey = ApiKey;

    }

    boolean validZipCode(String zipcode) {
        // validate that string is five numeric digits
        return (zipcode.matches("[0-9]+") && zipcode.length() == 5);
    }

    public void fetch(String zipcode) {
        // should probably fail if we can't validate the zip code
        Configuration configuration = new ClientConfig();
        JerseyClient client = JerseyClientBuilder.createClient(configuration);
        JerseyWebTarget target = client.target(String.format(API_URL, zipcode, ApiKey));

        Invocation.Builder invocationBuilder = target.request();
        Response response = invocationBuilder.get();

        // We are interested in two fields of the response:
        // response["name"]
        // response["main"["temp"]]
    }
}
