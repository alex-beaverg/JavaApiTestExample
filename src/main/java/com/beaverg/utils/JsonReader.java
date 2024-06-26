package com.beaverg.utils;

import com.beaverg.utils.custom_exceptions.JsonValidateException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.beaverg.utils.Printers.*;

public class JsonReader {

    public static <T> T readFile(File jsonFile, Class<T> clazz) throws JsonValidateException {
        T value;
        String fileName = jsonFile.getName();
        ObjectMapper mapper = new ObjectMapper();
        try {
            value = mapper.readValue(jsonFile, clazz);
            PRINTLN.info(String.format("[Info]: JSON file '%s' is valid", fileName));
        } catch (JacksonException e) {
            throw new JsonValidateException(String.format("[JsonValidateException]: JSON file '%s' is not valid!", fileName));
        } catch (IOException e) {
            throw new JsonValidateException(String.format("[IOException]: JSON file '%s' was not found!", fileName));
        }
        return value;
    }

    public static <T> T readIS(InputStream json, Class<T> clazz) throws JsonValidateException {
        T value;
        ObjectMapper mapper = new ObjectMapper();
        try {
            value = mapper.readValue(json, clazz);
            PRINTLN.info("[Info]: JSON input stream is valid");
        } catch (JacksonException e) {
            throw new JsonValidateException("[JsonValidateException]: JSON input stream is not valid!");
        } catch (IOException e) {
            throw new JsonValidateException("[IOException]: JSON input stream was not found!");
        }
        return value;
    }
}
