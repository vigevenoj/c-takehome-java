package com.sharkbaitextraordinaire.cayuse;

import com.sharkbaitextraordinaire.cayuse.integrations.owm.OpenweathermapFetcher;

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
        OpenweathermapFetcher weather = new OpenweathermapFetcher(configurationProperties.getProperty("openweathermap.apikey"));
        // Create object to look up timezone for location
        // Create object to look up elevation at location
        // Get input zip code
        if (args.length > 0) {
            System.out.println(args[0]);
            // Look up city and weather
            // Look up timezone
            // Look up location
            // Assemble and print output
        } else {
            // TODO print usage message when no arguments
            System.out.println("Hello World!");
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
                System.err.println(String.format("Failed to load specified properties file at %s, loading default properties", configFilePath));
                loadDefaultProperties();
            }
        }
        // TODO validate that we have all three API keys and fail if not present
        System.out.println(validateConfigurationProperties());
    }
}
