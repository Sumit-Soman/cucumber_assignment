package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	protected static Properties prop;
	/**
	 * This method is used to load the properties from config.properties file
	 * @return it returns Properties prop object
	 */
	public static Properties initialize() {

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config.properties");
			prop.load(ip);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static Properties getConfigProperties() {
		return prop;
	}
}
