package com.projectaicopilot.screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ValidarEsquemaDeRespuesta implements Question<Boolean> {

    private final String schemaPath;

    private ValidarEsquemaDeRespuesta(String schemaPath) {
        this.schemaPath = schemaPath;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            LastResponse.received().answeredBy(actor)
                    .then()
                    .assertThat()
                    .body(matchesJsonSchemaInClasspath(schemaPath));
            return true;
        } catch (AssertionError e) {
            throw new AssertionError("El contrato no coincide con el schema '" + schemaPath + "': " + e.getMessage(), e);
        }
    }

    public static ValidarEsquemaDeRespuesta con(String schemaPath) {
        return new ValidarEsquemaDeRespuesta(schemaPath);
    }
}
