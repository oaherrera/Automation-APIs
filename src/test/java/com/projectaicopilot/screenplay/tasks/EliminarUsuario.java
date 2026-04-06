package com.projectaicopilot.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import com.projectaicopilot.screenplay.utils.ApiEndpoints;

public class EliminarUsuario implements Task {

    private final Integer userId;

    public EliminarUsuario(Integer userId) {
        this.userId = userId;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Delete.from(ApiEndpoints.getUserById(userId))
        );
    }

    public static EliminarUsuario conId(Integer userId) {
        return new EliminarUsuario(userId);
    }
}
