package com.projectaicopilot.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import com.projectaicopilot.screenplay.utils.ApiEndpoints;

public class ObtenerUsuarioPorId implements Task {

    private final Integer userId;

    public ObtenerUsuarioPorId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Get.resource(ApiEndpoints.getUserById(userId))
        );
    }

    public static ObtenerUsuarioPorId conId(Integer userId) {
        return new ObtenerUsuarioPorId(userId);
    }
}
