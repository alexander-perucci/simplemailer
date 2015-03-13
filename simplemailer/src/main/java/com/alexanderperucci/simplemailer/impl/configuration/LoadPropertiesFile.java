package com.alexanderperucci.simplemailer.impl.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author alexander
 */
public class LoadPropertiesFile {
    
    private Properties properties;

    public LoadPropertiesFile(String propertyFile) {
        properties = new Properties();
        try {
            final ClassLoader loader = this.getClass().getClassLoader();
            final InputStream propFile = loader.getResourceAsStream(propertyFile);
            if (propFile != null) {
                properties.load(propFile);
                propFile.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String get(String key){
        if (key == null) {
            throw new IllegalArgumentException();
        }
        String value = properties.getProperty(key);
        if (value != null) {
            value.trim();
        }
        return value;
    }

    public void set(String key, String value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        properties.setProperty(key, value);
    }

    public Properties getProperties() {
        return properties;
    }
    
    

}
