package com.katf.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
public class JsonUtil {
    private static final Logger logger = LogManager.getLogger(JsonUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static String subfolderPath; // Holds the subfolder path (without file name)

    static {
        objectMapper.findAndRegisterModules();
    }

    private JsonUtil() {
        // Prevent instantiation
    }

    // Set the subfolder path from the properties file and optionally the file name
    public static void setFolderPathConfig(String propertyKey) {
        String path = ConfigurationLoader.get(propertyKey); // Load subfolder path from properties file
        if (path == null || path.isEmpty()) {
            throw new RuntimeException("Subfolder path not found for property key: " + propertyKey);
        }
        subfolderPath = path;

        // Ensure the directory exists
        File dir = new File(subfolderPath);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (created) {
                logger.info("Created directories for path: {}", dir.getAbsolutePath());
            } else {
                logger.warn("Failed to create directories for path: {}", dir.getAbsolutePath());
            }
        }
    }


    // Helper method to generate the full file path
    private static String getFullFilePath(String filename) {
        if (subfolderPath == null || subfolderPath.isEmpty()) {
            throw new RuntimeException("Subfolder path is not set. Please call setSubfolderPathFromPropertyKey() first.");
        }
        if (filename == null || filename.isEmpty()) {
            throw new RuntimeException("File name is not set. Please call setFileName() first.");
        }
        return subfolderPath + File.separator + filename;
    }

    // Serialize object to JSON (uses dynamic full file path)
    public static <T> void toJsonFile(String file,T object, boolean prettyPrint) {
        try {
            String fullFilePath = getFullFilePath(file);

            if (prettyPrint) {
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            } else {
                objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
            }
            objectMapper.writeValue(new File(fullFilePath), object);
            logger.info("Serialized object to JSON file at {}", fullFilePath);
        } catch (IOException e) {
            logger.error("Error writing object to JSON file", e);
            throw new RuntimeException("Failed to write object to JSON file", e);
        }
    }

    // Deserialize object from JSON (uses dynamic full file path)
    public static <T> T fromJsonFile(String file,Class<T> clazz) {
        try {
            String fullFilePath = getFullFilePath(file);
            return objectMapper.readValue(new File(fullFilePath), clazz);
        } catch (IOException e) {
            logger.error("Error reading JSON file", e);
            throw new RuntimeException("Failed to read JSON file", e);
        }
    }

    // Serialize object to JSON string
    public static <T> String toJson(T object, boolean prettyPrint) {
        try {
            if (prettyPrint) {
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            } else {
                objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
            }
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            logger.error("Error serializing object to JSON", e);
            throw new RuntimeException("Failed to serialize object to JSON", e);
        }
    }

    // Deserialize object from JSON string
    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            logger.error("Error deserializing JSON string", e);
            throw new RuntimeException("Failed to deserialize JSON string", e);
        }
    }
}

