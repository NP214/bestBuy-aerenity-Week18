package com.bestbuy;

import com.bestbuy.steps.ServicesSteps;
import com.bestbuy.testbase.ServicesTestBase;
import com.bestbuy.utils.TestUtils;
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
public class ServiceTest extends ServicesTestBase {
    static String name = "Strawberry" + TestUtils.getRandomValue();
    static int serviceId;
    @Steps
    ServicesSteps servicesSteps;
    @Test
    public void total(){
        servicesSteps.getAllStores().log().all();
    }
// Create Services
@Title("This will craete a new services")
    @Test
    public void test001(){
        ValidatableResponse response=   servicesSteps.createService(name);
           response.log().all().statusCode(201);
           serviceId = response.log().all().extract().path("id");
        System.out.println(serviceId);

        }
// Get By ID
    @Title("getting service information from id")
        @Test
    public void test002(){
        servicesSteps.getServiceById(serviceId).log().all().statusCode(200);
        }
// update by ID
    @Title("Update services by ID")
    @Test
    public void test003(){
        name = name + "_updated";
        servicesSteps.updateServices(serviceId,name).log().all().statusCode(200);
        HashMap<String,Object> serviceMap = servicesSteps.getServicesByIdforUpate(serviceId);
        Assert.assertThat(serviceMap,hasValue(name));
    }
    @Title("Delete the service information and verify updated information")
    @Test
    public void test004(){
        servicesSteps.deleteService(serviceId).statusCode(200);
        servicesSteps.getServiceById(serviceId).statusCode(404);
    }
    }

