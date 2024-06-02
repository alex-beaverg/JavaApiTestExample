package com.beaverg.tests.dummy_json;

import com.beaverg.domain.dummy_json.products.Product;
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
@Feature("'dummyjson.com' API products testing")
public class ProductsTest {
    private final String url = PropertyGetter.getProperty("dj.products_url");
    private final String path = PropertyGetter.getProperty("dj.products_path");

    private final String getUrl = PropertyGetter.getData("dj.get");
    private final String postUrl = PropertyGetter.getData("dj.post");
    private final String putUrl = PropertyGetter.getData("dj.put");
    private final String delUrl = PropertyGetter.getData("dj.del");
    private final String getPath = path + PropertyGetter.getData("get_file");
    private final String postPath = path + PropertyGetter.getData("post_file");
    private final String putPath = path + PropertyGetter.getData("put_file");

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        RestAssured.baseURI = url;
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
        Response response = given()
                .when().get(getUrl)
                .then().statusCode(200)
                .and().extract().response();
        Product expectedObject = JsonReader.readFile(new File(getPath), Product.class);
        Product actualObject = JsonReader.readIS(response.asInputStream(), Product.class);
        Assert.assertEquals(actualObject, expectedObject, "Objects aren't equal!");
    }

    @Test
    @Description("Verifying Status code and Response of POST method test")
    public void postTest() throws JsonValidateException {
        Product expectedObject = JsonReader.readFile(new File(postPath), Product.class);
        Response response = given()
                .when().contentType(ContentType.JSON)
                .and().body(ServiceActions.putRequestBody(expectedObject).toString())
                .when().post(postUrl)
                .then().statusCode(201)
                .and().extract().response();
        Product actualObject = JsonReader.readIS(response.asInputStream(), Product.class);
        Assert.assertEquals(actualObject, expectedObject, "Objects aren't equal!");
    }

    @Test
    @Description("Verifying Status code and Response of PUT method test")
    public void putTest() throws JsonValidateException {
        Product expectedObject = JsonReader.readFile(new File(putPath), Product.class);
        Response response = given()
                .when().contentType(ContentType.JSON)
                .when().body(ServiceActions.putRequestBody(expectedObject).toString())
                .when().put(putUrl)
                .then().statusCode(200)
                .and().extract().response();
        Product actualObject = JsonReader.readIS(response.asInputStream(), Product.class);
        Assert.assertEquals(actualObject, expectedObject, "Objects aren't equal!");
    }

    @Test
    @Description("Verifying Status code and Response of DELETE method test")
    public void deleteTest() {
        RestAssured.given()
                .when().contentType(ContentType.JSON)
                .when().delete(delUrl)
                .then().statusCode(200);
    }
}
