package com.sharkbaitextraordinaire;

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
        // Create object to look up timezone for location
        // Create object to look up elevation at location
        // Get input zip code
        if (args.length > 0) {
            System.out.println(args[0]);
        }
        // Look up city and weather
        // Look up timezone
        // Look up location
        // Assemble and print output
        System.out.println( "Hello World!" );
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

    private static void loadProperties() {
        // Look for a vm property named "configFile" for a path to a configuration file
        String configFilePath = System.getProperty("configFile");
        if (configFilePath != null){
            try {
                FileInputStream configStream = new FileInputStream(new File(configFilePath));
                configurationProperties.load(configStream);
            } catch (IOException e) {
                System.err.println(String.format("Failed to load specified properties file at {0}, loading default properties", configFilePath));
                loadDefaultProperties();
            }
        }
    }
}
