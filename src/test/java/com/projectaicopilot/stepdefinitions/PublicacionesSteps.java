package com.projectaicopilot.stepdefinitions;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import net.serenitybdd.screenplay.actors.OnStage;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.screenplay.GivenWhenThen.*;

import com.projectaicopilot.screenplay.tasks.*;
import com.projectaicopilot.screenplay.questions.*;
import com.projectaicopilot.screenplay.models.Post;
import com.projectaicopilot.screenplay.utils.ApiEndpoints;
import com.projectaicopilot.screenplay.enums.StatusCode;

public class PublicacionesSteps {

    @Dado("que el usuario desea obtener publicaciones")
    public void usuarioDesea_ObtenerPublicaciones() {
        OnStage.theActorCalled("Usuario1").can(CallAnApi.at(ApiEndpoints.BASE_URL));
    }

    @Dado("que el usuario desea obtener una publicación específica")
    public void usuarioDesea_ObtenerPublicacionEspecifica() {
        OnStage.theActorCalled("Usuario2").can(CallAnApi.at(ApiEndpoints.BASE_URL));
    }

    @Dado("que el usuario desea crear una nueva publicación")
    public void usuarioDesea_CrearPublicacion() {
        OnStage.theActorCalled("Usuario3").can(CallAnApi.at(ApiEndpoints.BASE_URL));
    }

    @Dado("que el usuario desea validar la estructura")
    public void usuarioDesea_ValidarEstructura() {
        OnStage.theActorCalled("Usuario4").can(CallAnApi.at(ApiEndpoints.BASE_URL));
    }

    @Dado("que el usuario desea verificar el contrato de publicaciones")
    public void usuarioDesea_VerificarContratoPublicaciones() {
        OnStage.theActorCalled("ContratoUser1").can(CallAnApi.at(ApiEndpoints.BASE_URL));
    }

    @Dado("que el usuario desea verificar el contrato de una publicación")
    public void usuarioDesea_VerificarContratoPublicacion() {
        OnStage.theActorCalled("ContratoUser2").can(CallAnApi.at(ApiEndpoints.BASE_URL));
    }

    @Dado("que el usuario desea verificar el contrato al crear una publicación")
    public void usuarioDesea_VerificarContratoCrearPublicacion() {
        OnStage.theActorCalled("ContratoUser3").can(CallAnApi.at(ApiEndpoints.BASE_URL));
    }

    @Cuando("solicito las publicaciones del usuario con id {int}")
    public void solicitoPublicacionesDelUsuario(Integer userId) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ObtenerPublicaciones.del(userId)
        );
    }

    @Cuando("solicito la publicación con id {int}")
    public void solicitoPublicacionPorId(Integer postId) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ObtenerPublicacionPorId.conId(postId)
        );
    }

    @Cuando("creo una publicación con los siguientes datos:")
    public void creoPublicacionConDatos(DataTable dataTable) {
        java.util.Map<String, String> data = dataTable.asMaps().get(0);
        Post post = new Post(
                Integer.valueOf(data.get("userId")),
                data.get("title"),
                data.get("body")
        );
        OnStage.theActorInTheSpotlight().attemptsTo(
                CrearPublicacion.con(post)
        );
    }

    @Entonces("la respuesta del servidor debe tener estado {int}")
    public void validarCodigoRespuesta(Integer statusCode) {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ObtenerCodigoDeRespuesta.delServidor(), equalTo(statusCode))
        );
    }

    @Entonces("la respuesta del servidor debe tener estado OK")
    public void validarCodigoRespuestaOK() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ObtenerCodigoDeRespuesta.delServidor(), equalTo(StatusCode.OK.getCode()))
        );
    }

    @Entonces("la respuesta del servidor debe tener estado CREATED")
    public void validarCodigoRespuestaCreated() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ObtenerCodigoDeRespuesta.delServidor(), equalTo(StatusCode.CREATED.getCode()))
        );
    }

    @Entonces("la respuesta debe contener la publicación con id {int}")
    public void validarPublicacionEnRespuesta(Integer postId) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("el id de la publicación",
                        actor -> LastResponse.received().answeredBy(actor).path("id"),
                        equalTo(postId))
        );
    }

    @Entonces("la respuesta debe tener los campos: id, userId, title, body")
    public void validarEstructuraRespuesta() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("el campo id",     actor -> LastResponse.received().answeredBy(actor).path("id"),     notNullValue()),
                seeThat("el campo userId", actor -> LastResponse.received().answeredBy(actor).path("userId"), notNullValue()),
                seeThat("el campo title",  actor -> LastResponse.received().answeredBy(actor).path("title"),  notNullValue()),
                seeThat("el campo body",   actor -> LastResponse.received().answeredBy(actor).path("body"),   notNullValue())
        );
    }

    @Entonces("el contrato de la respuesta debe coincidir con el esquema de publicación")
    public void validarContratoPublicacion() {
        ValidarEsquemaDeRespuesta.con("schemas/post-schema.json").answeredBy(OnStage.theActorInTheSpotlight());
    }

    @Entonces("el contrato de la respuesta debe coincidir con el esquema de listado de publicaciones")
    public void validarContratoListadoPublicaciones() {
        ValidarEsquemaDeRespuesta.con("schemas/posts-list-schema.json").answeredBy(OnStage.theActorInTheSpotlight());
    }
}
