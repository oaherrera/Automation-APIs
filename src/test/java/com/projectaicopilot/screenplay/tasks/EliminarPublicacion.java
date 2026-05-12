package com.projectaicopilot.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import com.projectaicopilot.screenplay.utils.ApiEndpoints;

public class EliminarPublicacion implements Task {

    private final Integer postId;

    public EliminarPublicacion(Integer postId) {
        this.postId = postId;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            net.serenitybdd.screenplay.rest.interactions.Delete.from(ApiEndpoints.getPostById(postId))
        );
    }

    public static EliminarPublicacion conId(Integer postId) {
        return new EliminarPublicacion(postId);
    }
}
