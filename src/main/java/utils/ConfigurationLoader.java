package utils;

import constants.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton
 * Created by hu on 2014/10/19 0019.
 */
public class ConfigurationLoader {
    private static ConfigurationLoader configurationLoader;
    private static String configFileName = "config.properties";
    private static Properties properties;

    private ConfigurationLoader() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(configFileName);
        properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigurationLoader getInstance(){
        if(configurationLoader == null){
            configurationLoader = new ConfigurationLoader();
        }
        return configurationLoader;
    }

    public String getValue(String key){
        return (String)properties.get(key);
    }
}
