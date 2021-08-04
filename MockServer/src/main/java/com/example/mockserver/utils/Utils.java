package com.example.mockserver.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;

public class Utils {
    public static String convertErrorToJson(Object o) {
        JSONObject body = new JSONObject();
        body.appendField("error", o);
        return body.toJSONString();
    }

    public static String convertObjectToString(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

}
