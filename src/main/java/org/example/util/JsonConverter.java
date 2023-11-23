package org.example.util;

import com.google.gson.Gson;

public abstract class JsonConverter {

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return new Gson().fromJson(json, classOfT);
    }

}
