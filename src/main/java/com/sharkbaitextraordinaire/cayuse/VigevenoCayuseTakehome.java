package com.sharkbaitextraordinaire.cayuse;

import com.sharkbaitextraordinaire.cayuse.integrations.owm.OpenweathermapClient;
import com.sharkbaitextraordinaire.cayuse.integrations.owm.OpenweathermapResponse;

import java.io.*;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class VigevenoCayuseTakehome {

    private static Properties configurationProperties = new Properties();

    public static void main( String[] args ) {
        // Load up configuration (secrets like api keys, etc)
        loadProperties();
        // Create object to look up city and weather for zip code
        OpenweathermapClient weather = new OpenweathermapClient(configurationProperties.getProperty("openweathermap.apikey"));
        // Create object to look up timezone for location
        // Create object to look up elevation at location
        // Get input zip code
        if (args.length > 0) {
            String zipcode = args[0];
            System.out.println(zipcode);
            // Validate zip code before we look up city and weather
            if(!weather.isValidZipCode(zipcode)) {
                System.out.printf("%s is not a valid zip code", zipcode);
                return;
            }
            OpenweathermapResponse owmResponse = weather.getInfoForZipCode(zipcode);
            if (null == owmResponse) {
                System.err.println("There was a problem fetching the weather data");
                return;
            }
            System.out.printf("At the location %s, the temperature is %s\n", owmResponse.getCityName(), owmResponse.getTemperature());
            // Look up timezone
            // Look up location
            // Assemble and print output
        } else {
            // TODO print usage message when no arguments
            System.out.println("No zip codes provided to check");
        }
    }

    private static void loadDefaultProperties() {
        String DEFAULT_PROPERTIES_FILE_NAME = "com/sharkbaitextraordinaire/default.properties";
        InputStream defaultPropertyStream = VigevenoCayuseTakehome.class.getClassLoader().getResourceAsStream(DEFAULT_PROPERTIES_FILE_NAME);
        try {
            configurationProperties.load(defaultPropertyStream);
        } catch (IOException e) {
            System.err.println("Unable to load default configuration");
            e.printStackTrace();
        }
    }

    private static boolean validateConfigurationProperties() {
        // this returns true, fix it tomorrow
        return (configurationProperties.containsKey("openweathermap.apikey") &&
                configurationProperties.getProperty("openweathermap.apikey") != "" &&
                configurationProperties.containsKey("google.timezone.apikey") &&
                configurationProperties.getProperty("google.timezone.apikey") != "" &&
                configurationProperties.containsKey("google.elevation.apikey") &&
                configurationProperties.getProperty("google.elevation.apikey") != "");
    }

    private static void loadProperties() {
        // Look for a vm property named "configFile" for a path to a configuration file
        String configFilePath = System.getProperty("configFile");
        System.out.printf("Using %s as configuration file\n", configFilePath);
        if (configFilePath != null){
            try {
                FileInputStream configStream = new FileInputStream(new File(configFilePath));
                configurationProperties.load(configStream);
            } catch (IOException e) {
                System.err.println(String.format("Failed to load specified properties file at %s, loading default properties\n", configFilePath));
                loadDefaultProperties();
            }
        }
        // TODO validate that we have all three API keys and fail if not present
        System.out.println(validateConfigurationProperties());
    }
}
