package com.bestbuy.steps;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class Utilitiesteps {
    @Step
    public ValidatableResponse getVersions(){
        return SerenityRest.given().log().all()
                .when()
                .get()
                .then().statusCode(200);
    }
    @Step
    public ValidatableResponse healthCheck(){
        return SerenityRest.given().log().all()
                .when()
                .get().then().statusCode(200);
    }
}
