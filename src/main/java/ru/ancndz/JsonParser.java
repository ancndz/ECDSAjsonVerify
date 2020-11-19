package ru.ancndz;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;

public class JsonParser {

    public boolean toJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(Paths.get("message.json").toFile(), object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Object getFromJson(String jsonPath, Class<?> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(Paths.get(jsonPath).toFile(), clazz);
        } catch (IOException e) {
            return null;
        }

    }
}
