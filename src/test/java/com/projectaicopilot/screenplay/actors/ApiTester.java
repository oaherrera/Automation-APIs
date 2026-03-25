package com.projectaicopilot.screenplay.actors;

import net.serenitybdd.screenplay.Actor;
import com.projectaicopilot.screenplay.utils.RestConfig;

public class ApiTester {

    public static Actor queInteractuaConAPIs(String nombre) {
        RestConfig.configureRestAssured();
        return Actor.named(nombre);
    }
}
