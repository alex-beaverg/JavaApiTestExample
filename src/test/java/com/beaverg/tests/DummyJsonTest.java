package com.beaverg.tests;

import com.beaverg.domain.dummy_json.Product;
import com.beaverg.utils.JsonReader;
import com.beaverg.utils.PropertyGetter;
import com.beaverg.utils.ServiceActions;
import com.beaverg.utils.custom_exceptions.JsonValidateException;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

@Epic("API CRUD operations testing")
@Feature("'dummyjson.com' API testing")
public class DummyJsonTest {
    private final String dummyJsonUrl = PropertyGetter.getProperty("dummy_json_url");
    private final String urlGetPostfix = PropertyGetter.getData("dummy_json_get");
    private final String urlPostPostfix = PropertyGetter.getData("dummy_json_post");
    private final String urlPutPostfix = PropertyGetter.getData("dummy_json_put");
    private final String urlDelPostfix = PropertyGetter.getData("dummy_json_del");

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        RestAssured.baseURI = dummyJsonUrl;
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {
        if (result.getStatus() == 1) {
            Allure.addAttachment("Message", String.format("Test '%s' was completed successfully!", result.getName()));
        } else if (result.getStatus() == 2) {
            Allure.addAttachment("Message", String.format("Test '%s' was failed!", result.getName()));
        }
    }

    @Test
    @Description("Verifying Status code and Response of GET method test")
    public void getTest() throws JsonValidateException {
        String path = "src/test/resources/data_json/get.json";
        Response response = given()
                .when().get(urlGetPostfix)
                .then().statusCode(200)
                .and().extract().response();
        Product expectedObject = JsonReader.readFile(new File(path), Product.class);
        Product actualObject = JsonReader.readIS(response.asInputStream(), Product.class);
        Assert.assertEquals(actualObject, expectedObject, "Products aren't equal!");
    }

    @Test
    @Description("Verifying Status code and Response of POST method test")
    public void postTest() throws JsonValidateException {
        String path = "src/test/resources/data_json/post.json";
        Product expectedObject = JsonReader.readFile(new File(path), Product.class);
        Response response = given()
                .when().contentType(ContentType.JSON)
                .and().body(ServiceActions.putRequestBody(expectedObject).toString())
                .when().post(urlPostPostfix)
                .then().statusCode(200)
                .and().extract().response();
        Product actualObject = JsonReader.readIS(response.asInputStream(), Product.class);
        Assert.assertEquals(actualObject, expectedObject, "Products aren't equal!");
    }

    @Test
    @Description("Verifying Status code and Response of PUT method test")
    public void putTest() throws JsonValidateException {
        String path = "src/test/resources/data_json/put.json";
        Product expectedObject = JsonReader.readFile(new File(path), Product.class);
        Response response = given()
                .when().body(ServiceActions.putRequestBody(expectedObject).toString())
                .when().put(urlPutPostfix)
                .then().statusCode(200)
                .and().extract().response();
        Product actualObject = JsonReader.readIS(response.asInputStream(), Product.class);
        Assert.assertEquals(actualObject, expectedObject, "Products aren't equal!");
    }

    @Test
    @Description("Verifying Status code and Response of DELETE method test")
    public void deleteTest() {
        RestAssured.given()
                .when().contentType(ContentType.JSON)
                .when().delete(urlDelPostfix)
                .then().statusCode(200)
                .and().extract().response();
    }
}
