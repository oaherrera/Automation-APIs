package com.projectaicopilot.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import io.restassured.http.ContentType;
import com.projectaicopilot.screenplay.models.User;
import com.projectaicopilot.screenplay.utils.ApiEndpoints;

public class ActualizarUsuario implements Task {

    private final Integer userId;
    private final User usuario;

    public ActualizarUsuario(Integer userId, User usuario) {
        this.userId = userId;
        this.usuario = usuario;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            net.serenitybdd.screenplay.rest.interactions.Put.to(ApiEndpoints.getUserById(userId))
                .with(request -> request
                        .contentType(ContentType.JSON)
                        .body(usuario))
        );
    }

    public static ActualizarUsuario conId(Integer userId, User usuario) {
        return new ActualizarUsuario(userId, usuario);
    }
}
