package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Properties;

public class PropertiesManager {
    private final static String path = "client.properties";
    private final Properties properties;

    public PropertiesManager() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        File file = new File(path);
        if (file.exists()) {
            try (FileInputStream input = new FileInputStream(file)) {
                properties.load(input);
            } catch (IOException e) {
                System.out.println("Error loading properties file");
                System.exit(1);
            }
        } else {
            setDefaultProperties();
            saveProperties();
        }
    }

    private void setDefaultProperties() {
        properties.setProperty("ip", "127.0.0.1");
        properties.setProperty("port", "1234");
    }

    private void saveProperties() {
        try (FileOutputStream output = new FileOutputStream(path)) {
            properties.store(output, null);
        } catch (IOException e) {
            System.out.println("Error saving properties file");
            System.exit(1);
        }
    }

    public String getAddress() {
        return properties.getProperty("ip");
    }
    public int getPort() {
        try{
            return Integer.parseInt(properties.getProperty("port"));
        }catch (NumberFormatException ex){
            throw new RuntimeException("Error parsing port number in config");
        }
    }
}