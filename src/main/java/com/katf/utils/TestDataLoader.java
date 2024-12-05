package com.katf.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class TestDataLoader {


    // Load JSON test data
    public static <T> T loadJson(String filePath, Class<T> type) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(filePath), type);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load JSON file: " + filePath);
        }
    }

    // Example: Load data from JSON file
    public static String getTestData(String key, String jsonFile) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/test/resources/testdata/" + jsonFile);
            return mapper.readTree(file).get(key).asText();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch test data from JSON file");
        }
    }
}

