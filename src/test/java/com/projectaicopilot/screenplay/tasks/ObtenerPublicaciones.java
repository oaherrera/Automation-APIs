package com.projectaicopilot.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import com.projectaicopilot.screenplay.utils.ApiEndpoints;

public class ObtenerPublicaciones implements Task {

    private final Integer userId;

    public ObtenerPublicaciones(Integer userId) {
        this.userId = userId;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Get.resource(ApiEndpoints.getUserPosts(userId))
        );
    }

    public static ObtenerPublicaciones del(Integer userId) {
        return new ObtenerPublicaciones(userId);
    }
}
