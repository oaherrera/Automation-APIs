package com.projectaicopilot.screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class ObtenerCodigoDeRespuesta implements Question<Integer> {

    @Override
    public Integer answeredBy(Actor actor) {
        return LastResponse.received().answeredBy(actor).getStatusCode();
    }

    public static ObtenerCodigoDeRespuesta delServidor() {
        return new ObtenerCodigoDeRespuesta();
    }
}
