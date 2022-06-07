package com.bestbuy;

import com.bestbuy.steps.StoresSteps;
import com.bestbuy.testbase.StoreTestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoreTest extends StoreTestBase {
    static String name = "BigBox";
    static String type = "String";
    static String address = "dfgdtd road";
    static String address2 = "";
    static String city = "florida";
    static String state = "florida";
    static String zip = "678904";
    static double lat = 66.789;
    static double lng = 77.789;
    static String hours = "Mon:6-7";
    static HashMap<Object, Object> services;
    static int storeId;

    @Steps
    StoresSteps storesSteps;

    @Test
    public void getAllStores() {
        storesSteps.total().log().all();
    }

    @Title("This will create a new store")
    @Test
    public void test001() {
        HashMap<Object, Object> services = new HashMap<>();

        ValidatableResponse response = storesSteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours, services).log().all().statusCode(201);
        response.log().all().statusCode(201);
        storeId = response.log().all().extract().path("id");
        System.out.println(storeId);
    }

    @Test
    public void test002() {
        HashMap<Object, Object> services = new HashMap<>();
        name = name + "_updated";
        storesSteps.updateProduct(storeId, name, type, address, address2, city, state, zip, lat, lng, hours, services).log().all().statusCode(200);
        HashMap<String, Object> storeMap = storesSteps.getStoreByIdForUpdate(storeId);
        Assert.assertThat(storeMap, hasValue(name));


    }

    @Test
    public void test003() {
        storesSteps.getStoreById(storeId).log().all().statusCode(200);

    }

    @Title("Delete the store information and verify updated information")
    @Test
    public void test004() {
        storesSteps.deleteStore(storeId).statusCode(200);
        storesSteps.getStoreById(storeId).statusCode(404);

    }
}
