package com.beaverg.utils;

import org.json.JSONObject;

import java.lang.reflect.Field;

public class RequestBodyPutting {
    public static <T> JSONObject putRequestBody(T object) {
        JSONObject requestBody = new JSONObject();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                requestBody.put(field.getName(), field.get(object));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return requestBody;
    }
}
