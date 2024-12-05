package com.katf.utils;

import javax.imageio.IIOException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationLoader {
    private static Properties properties = new Properties();

    static {
        try (FileInputStream fileInputStream=new FileInputStream("src/main/resources/config.properties")){
            properties.load(fileInputStream);
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }
     public static String get(String key){
        return properties.getProperty(key);
     }
}
