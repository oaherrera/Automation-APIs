package com.projectaicopilot.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import io.restassured.http.ContentType;
import com.projectaicopilot.screenplay.models.Post;
import com.projectaicopilot.screenplay.utils.ApiEndpoints;

public class ActualizarPublicacion implements Task {

    private final Integer postId;
    private final Post publicacion;

    public ActualizarPublicacion(Integer postId, Post publicacion) {
        this.postId = postId;
        this.publicacion = publicacion;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            net.serenitybdd.screenplay.rest.interactions.Put.to(ApiEndpoints.getPostById(postId))
                .with(request -> request
                        .contentType(ContentType.JSON)
                        .body(publicacion))
        );
    }

    public static ActualizarPublicacion conId(Integer postId, Post publicacion) {
        return new ActualizarPublicacion(postId, publicacion);
    }
}
