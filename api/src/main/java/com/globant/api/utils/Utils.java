package com.globant.api.utils;

import net.minidev.json.JSONObject;

public class Utils {
    public static String convertObjectToJson(Object o) {
        JSONObject body = new JSONObject();

        body.appendField("error", o);
        return body.toJSONString();
    }
}
