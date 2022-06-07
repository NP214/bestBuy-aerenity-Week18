package com.bestbuy.steps;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoresSteps {
    // total
    @Step
    public ValidatableResponse total() {
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_STORES).then().statusCode(200);
    }
// create
    @Step("creating neww store with name : {0},  Address: {1}, Address2: {2}, City: {3}, State: {4}, Zip: {5}, lat: {6}, lng: {7} and hours: {8}")
    public ValidatableResponse createStore(String name,String type, String address, String address2, String city, String state, String zip, double lat, double lng, String hours,HashMap<Object,Object> services) {
        StorePojo storesPojo = new StorePojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        storesPojo.setServices(services);
        return SerenityRest.given().log().all()
                .contentType("application/json")
                .body(storesPojo)
                .when()
                .post().then();
    }

    //get service by id for update store
    @Step("getting stores by id : {0}")
    public HashMap<String, Object> getStoreByIdForUpdate(int storeId) {
        HashMap<String, Object> storeMap = SerenityRest.given().log().all()
                .pathParam("storeId", storeId)
                .when()
                .get(EndPoints.GET_STORES_BY_ID)
                .then().statusCode(200)
                .extract()
                .path("");
        return storeMap;

    }
// update
    @Step("Updating stores with name : {0} address : {1}, address2 : {2}, city : {3}, state : {4},  zip : {5},lat : {6},  lng : {7} and hours : {8}")
    public ValidatableResponse updateProduct(int storeId, String name, String type, String address, String address2, String city, String state, String zip, double lat, double lng, String hours,HashMap<Object,Object> services) {
        StorePojo storesPojo = new StorePojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        storesPojo.setServices(services);
        return SerenityRest.given().log().all()
                .contentType("application/json")
                .pathParam("storeId", storeId)
                .body(storesPojo)
                .when()
                .put(EndPoints.UPDATE_STORES_BY_ID).then();

    }
    //  get store by id
    @Step("getting product information from Id: {0}")
    public ValidatableResponse getStoreById(int storeId){
        return SerenityRest.given().log().all()
                .pathParam("storeId",storeId)
                .when()
                .get(EndPoints.GET_STORES_BY_ID).then();
    }

    // method is for delete id
    @Step("deleting stores with store id : {0}")
    public ValidatableResponse deleteStore(int storeId){
        return  SerenityRest.given().log().all()
                .pathParam("storeId",storeId)
                .when()
                .delete(EndPoints.DELETE_STORES_BY_ID).then();
    }

}
