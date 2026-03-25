package com.projectaicopilot.screenplay.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestConfig {
    
    public static void configureRestAssured() {
        RestAssured.baseURI = ApiEndpoints.BASE_URL;
        RestAssured.basePath = "";
        RestAssured.defaultParser = io.restassured.parsing.Parser.JSON;
    }

    public static void resetRestAssured() {
        RestAssured.reset();
    }

    public static ContentType getDefaultContentType() {
        return ContentType.JSON;
    }
}
