package com.projectaicopilot.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import io.restassured.http.ContentType;
import com.projectaicopilot.screenplay.models.Post;
import com.projectaicopilot.screenplay.utils.ApiEndpoints;

public class CrearPublicacion implements Task {

    private final Post publicacion;

    public CrearPublicacion(Post publicacion) {
        this.publicacion = publicacion;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            net.serenitybdd.screenplay.rest.interactions.Post.to(ApiEndpoints.POSTS)
                .with(request -> request
                        .contentType(ContentType.JSON)
                        .body(publicacion))
        );
    }

    public static CrearPublicacion con(Post publicacion) {
        return new CrearPublicacion(publicacion);
    }
}
