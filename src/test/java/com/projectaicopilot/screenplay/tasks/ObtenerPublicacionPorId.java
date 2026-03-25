package com.projectaicopilot.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import com.projectaicopilot.screenplay.utils.ApiEndpoints;

public class ObtenerPublicacionPorId implements Task {

    private final Integer postId;

    public ObtenerPublicacionPorId(Integer postId) {
        this.postId = postId;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Get.resource(ApiEndpoints.getPostById(postId))
        );
    }

    public static ObtenerPublicacionPorId conId(Integer postId) {
        return new ObtenerPublicacionPorId(postId);
    }
}
