package nl.rufino.util;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Collection;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class AudioFunctionsTest {
	static final Logger LOGGER = LogManager.getLogger(AudioFunctionsTest.class);

	private Properties retrieveProperties;

	@Before
	public void setUp() throws Exception {
		retrieveProperties = WindowsFunctions.retrieveProperties("config.properties");
	}

	@Test
	public void test() {
		assertNotNull(retrieveProperties);
	    Collection<Object> values = retrieveProperties.values();
		for (Object value : values) {
			System.out.println((String) value);
		}
		
		Integer determineHighestBeatNumber = AudioFunctions.determineNewBeatNumber(new File(retrieveProperties.getProperty("audio.searchdirectory")));
		
		System.out.println("Highest beat number: " + determineHighestBeatNumber);
	}
}
