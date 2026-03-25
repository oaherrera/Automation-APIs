package com.projectaicopilot.screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import com.projectaicopilot.screenplay.models.Post;

public class ValidarPublicacionCreada implements Question<Post> {

    @Override
    public Post answeredBy(Actor actor) {
        return LastResponse.received().answeredBy(actor).as(Post.class);
    }

    public static ValidarPublicacionCreada enLaRespuesta() {
        return new ValidarPublicacionCreada();
    }
}
