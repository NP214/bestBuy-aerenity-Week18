package com.bestbuy;

import com.bestbuy.steps.ProductsSteps;
import com.bestbuy.testbase.ProductTestBase;
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
public class ProductsTest extends ProductTestBase {
    static String name = "java" + TestUtils.getRandomValue();
    static String type = "selenium";
    static int price = 100;
    static int shipping = 10;
    static String upc = "UPC";
    static String description = "describe";
    static String manufacturer = "any";
    static String model = "mode";
    static String url = "URL";
    static String image = "IMAGE";
    static int productId;
    @Steps
    ProductsSteps productsSteps;

    @Test
    public void getAllProducts() {
        productsSteps.total().log().all();
    }

    @Title("This will create a new product")
    @Test
    public void test001() {
        ValidatableResponse response = productsSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image).log().all().statusCode(201);
        response.log().all().statusCode(201);
        productId = response.log().all().extract().path("id");
        System.out.println(productId);
    }

    @Test
    public void test002() {
        HashMap<String, Object> productMap = productsSteps.getProductInfoByid(productId);
        Assert.assertThat(productMap, hasValue(name));
    }

    @Title("Update the product information and verify updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        productsSteps.updateProduct(productId, name, type, price, shipping, upc, description, manufacturer, model, url, image).log().all().statusCode(200);
        HashMap<String, Object> productMap = productsSteps.getProductInfoByid(productId);
        Assert.assertThat(productMap, hasValue(name));
    }

    @Title("Delete the product information and verify updated information")
    @Test
    public void test004() {
        productsSteps.deleteProductById(productId).statusCode(200);
        productsSteps.getProductById(productId).statusCode(404);
    }
}
