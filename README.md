# Instructions to run the program
Running the application requires Java 8 and Maven to be installed.

1. Clone repository, `git clone https://code.sharkbaitextraordinaire.com/jacob/c-takehome-java.git`
1. Create a Java properties file with API keys for the services. A sample without keys is present as `default.properties`
1. Build the project via `mvn clean package`
1. Run the jar. Provide the configuration file as a VM argument and the zip code as an argument to the program, ie, `java -DconfigFile=default.properties target/cayuse-takehome-1.0-SNAPSHOT-jar-with-dependencies.jar 97206`

# Assignment
Create a utility using the language/tools of your choice that takes a ZIP-code, then outputs the city name, current temperature, time zone, and general elevation at the location with a user-friendly message. For example, “At the location $CITY\_NAME, the temperature is $TEMPERATURE, the timezone is $TIMEZONE, and the elevation is $ELEVATION”.


Use the Open WeatherMap current weather API to retrieve the current temperature and city name. You will be required to sign up for a free API key.
Use the Google Time Zone API to get the current timezone for a location. You will again need to register a “project” and sign up for a free API key * with Google.
Use the Google Elevation API to retrieve elevation data for a location.

# Backend developers
Your utility can accept ZIP-code as a command line parameter and simply write its output to the console/STDOUT/STDERR once retrieved. Node or Java are preferred but not required.
