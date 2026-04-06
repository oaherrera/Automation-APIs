package com.projectaicopilot.screenplay.interactions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import io.restassured.http.ContentType; 

public class ApiInteractions {

    public static Get performGet(String endpoint) {
        return Get.resource(endpoint);
    }

    public static Performable performPost(String endpoint, Object body) {
        return Post.to(endpoint)
                .with(request -> request
                        .contentType(ContentType.JSON)
                        .body(body));
    }

    public static Performable performPut(String endpoint, Object body) {
        return Put.to(endpoint)
                .with(request -> request
                        .contentType(ContentType.JSON)
                        .body(body));
    }

    public static Delete performDelete(String endpoint) {
        return Delete.from(endpoint);
    }
}
