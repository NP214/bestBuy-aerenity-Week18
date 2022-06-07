package com.bestbuy.steps;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ServicesPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ServicesSteps {
    @Step
    public ValidatableResponse getAllStores() {
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then()
                .statusCode(200);

    }

    // Create Service
    @Step("creating new services with name : {0}")
    public ValidatableResponse createService(String name) {
        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);
        return SerenityRest.given().log().all()
                .contentType("application/json")
                .body(servicesPojo)
                .when()
                .post().then();
    }
// get service by id for Update service
    @Step("getting services information by id :{0}")
    public HashMap<String ,Object> getServicesByIdforUpate(int serviceId) {
         HashMap<String,Object> serviceMap = SerenityRest.given().log().all()
                .pathParam("serviceId", serviceId)
                .when()
                .get(EndPoints.GET_SERVICES_BY_ID)
                .then()
                .statusCode(200)
                 .extract()
                 .path("");
         return serviceMap;

    }
// this method is used for update service
    @Step("Updating services ")
    public ValidatableResponse updateServices(int serviceId, String name) {
        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);
        return  SerenityRest.given().log().all()
                .contentType("application/json")
                .pathParam("serviceId",serviceId)
                .body(servicesPojo)
                .when()
                .patch(EndPoints.UPDATE_SERVICES_BY_ID).then();

    }
    // method for get service from ID
   @Step("getting product information from Id: {0}")
   public ValidatableResponse getServiceById(int serviceId){
        return SerenityRest.given().log().all()
                .pathParam("serviceId",serviceId)
                .when()
                .get(EndPoints.GET_SERVICES_BY_ID).then();
   }
// method is for delete id
    @Step("deleting services with service id : {0}")
    public ValidatableResponse deleteService(int serviceId){
        return  SerenityRest.given().log().all()
                .pathParam("serviceId",serviceId)
                .when()
                .delete(EndPoints.DELETE_SERVICES_BY_ID).then();
    }
}