package com.projectaicopilot.screenplay.enums;

public enum HttpMethods {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    HEAD("HEAD");

    private final String method;

    HttpMethods(String method) {
        this.method = method;
    }

    public String getValue() {
        return method;
    }
}
