package com.giovannicaggianella.toon.processor;

import com.felipestanzani.jtoon.JToon;

public class ToonAnnotationProcessor {

    public String encode(Object object) {
        return JToon.encode(object);
    }

    public <T> T decode(String toon, Class<T> clazz) {
        //return JToon.decode(toon, clazz);
        return null; // TODO
    }

    public String encodeJson(String json) {
        return JToon.encodeJson(json);
    }

    public String decodeToJson(String toon) {
        return JToon.decodeToJson(toon);
    }
}
