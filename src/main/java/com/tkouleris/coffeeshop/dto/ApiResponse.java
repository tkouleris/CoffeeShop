package com.tkouleris.coffeeshop.dto;

import java.util.LinkedHashMap;
import java.util.Map;

public class ApiResponse {

    private final Map<String, Object> body = new LinkedHashMap<>();

    public ApiResponse(boolean success, String message, Object data) {
        body.put("success", success);
        body.put("message", message);
        body.put("data", data);
    }

    public Map<String, Object> getBodyResponse() {
        return body;
    }
}
