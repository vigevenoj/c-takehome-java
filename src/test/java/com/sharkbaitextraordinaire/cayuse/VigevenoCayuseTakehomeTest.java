package com.sharkbaitextraordinaire.cayuse;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Properties;

/**
 * Unit test for simple VigevenoCayuseTakehome.
 */
public class VigevenoCayuseTakehomeTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public VigevenoCayuseTakehomeTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( VigevenoCayuseTakehomeTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }


    public void testValidateConfiguration() throws Exception {
        Properties badProperties = new Properties();
        badProperties.setProperty(VigevenoCayuseTakehome.OpenweatherMapApiKeyPropertyName, "");
        badProperties.setProperty(VigevenoCayuseTakehome.GoogleElevationApiKeyPropertyName, "fakekey");
        assertFalse(VigevenoCayuseTakehome.validateConfigurationProperties(badProperties));

        Properties goodProperties = new Properties();
        goodProperties.setProperty(VigevenoCayuseTakehome.OpenweatherMapApiKeyPropertyName, "fake key");
        goodProperties.setProperty(VigevenoCayuseTakehome.GoogleTimezoneApiKeyPropertyName, "fake key");
        goodProperties.setProperty(VigevenoCayuseTakehome.GoogleElevationApiKeyPropertyName, "fake key");
        assertTrue(VigevenoCayuseTakehome.validateConfigurationProperties(goodProperties));
    }
}
