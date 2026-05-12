package com.projectaicopilot.stepdefinitions;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import net.serenitybdd.screenplay.actors.OnStage;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.*;

import com.projectaicopilot.screenplay.tasks.ObtenerUsuarioPorId;
import com.projectaicopilot.screenplay.tasks.EliminarUsuario;
import com.projectaicopilot.screenplay.tasks.ActualizarUsuario;
import com.projectaicopilot.screenplay.questions.ValidarEsquemaDeRespuesta;
import com.projectaicopilot.screenplay.models.User;
import com.projectaicopilot.screenplay.utils.ApiEndpoints;

public class UsuariosSteps {

    @Dado("que el usuario desea obtener información de un usuario específico")
    public void usuarioDesea_ObtenerInfoUsuario() {
        OnStage.theActorCalled("TestUser").can(CallAnApi.at(ApiEndpoints.BASE_URL));
    }

    @Dado("que el usuario desea validar información del usuario")
    public void usuarioDesea_ValidarInfoUsuario() {
        OnStage.theActorCalled("ValidatorUser").can(CallAnApi.at(ApiEndpoints.BASE_URL));
    }

    @Cuando("solicito la información del usuario con id {int}")
    public void solicitoInfoUsuario(Integer userId) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ObtenerUsuarioPorId.conId(userId)
        );
    }

    @Entonces("la respuesta debe contener los datos del usuario")
    public void validarDatosUsuario() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("el nombre del usuario",
                        actor -> LastResponse.received().answeredBy(actor).path("name"),
                        notNullValue())
        );
    }

    @Entonces("la respuesta debe incluir el email del usuario")
    public void validarEmailEnRespuesta() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("el email del usuario",
                        actor -> LastResponse.received().answeredBy(actor).path("email"),
                        notNullValue())
        );
    }

    @Dado("que el usuario desea eliminar un usuario")
    public void usuarioDesea_EliminarUsuario() {
        OnStage.theActorCalled("DeleteUser").can(CallAnApi.at(ApiEndpoints.BASE_URL));
    }

    @Cuando("elimino el usuario con id {int}")
    public void elimimoUsuario(Integer userId) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                EliminarUsuario.conId(userId)
        );
    }

    @Dado("que el usuario desea verificar el contrato de un usuario")
    public void usuarioDesea_VerificarContratoUsuario() {
        OnStage.theActorCalled("ContratoUserValidator").can(CallAnApi.at(ApiEndpoints.BASE_URL));
    }

    @Entonces("el contrato de la respuesta debe coincidir con el esquema de usuario")
    public void validarContratoUsuario() {
        ValidarEsquemaDeRespuesta.con("schemas/user-schema.json")
                .answeredBy(OnStage.theActorInTheSpotlight());
    }

    // --- PUT /users/{id} ---

    @Dado("que el usuario desea actualizar un usuario")
    public void usuarioDesea_ActualizarUsuario() {
        OnStage.theActorCalled("ActualizarUsuarioUser").can(CallAnApi.at(ApiEndpoints.BASE_URL));
    }

    @Dado("que el usuario desea verificar el contrato de un usuario actualizado")
    public void usuarioDesea_VerificarContratoActualizarUsuario() {
        OnStage.theActorCalled("ContratoActualizarUsuario").can(CallAnApi.at(ApiEndpoints.BASE_URL));
    }

    @Cuando("actualizo el usuario con id {int} con los siguientes datos:")
    public void actualizoUsuario(Integer userId, DataTable dataTable) {
        java.util.Map<String, String> data = dataTable.asMaps().get(0);
        User user = new User();
        user.setName(data.get("name"));
        user.setUsername(data.get("username"));
        user.setEmail(data.get("email"));
        OnStage.theActorInTheSpotlight().attemptsTo(
                ActualizarUsuario.conId(userId, user)
        );
    }

    @Entonces("el contrato de la respuesta debe coincidir con el esquema de usuario actualizado")
    public void validarContratoActualizarUsuario() {
        ValidarEsquemaDeRespuesta.con("schemas/user-update-schema.json")
                .answeredBy(OnStage.theActorInTheSpotlight());
    }
}
