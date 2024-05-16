package com.beaverg.tests;

import com.beaverg.domain.json_placeholder.User;
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

import static io.restassured.RestAssured.given;

@Epic("API CRUD operations testing")
@Feature("'jsonplaceholder.typicode.com' API testing")
public class JsonPlaceHolderTest {
    private final String url = PropertyGetter.getProperty("json_placeholder_url");
    private final String path = PropertyGetter.getProperty("json_placeholder_filepath");

    private final String urlGetPostfix = PropertyGetter.getData("json_placeholder_get");
    private final String urlPostPostfix = PropertyGetter.getData("json_placeholder_post");
    private final String urlPutPostfix = PropertyGetter.getData("json_placeholder_put");
    private final String urlDelPostfix = PropertyGetter.getData("json_placeholder_del");
    private final String getPath = path + PropertyGetter.getData("get_name");
    private final String postPath = path + PropertyGetter.getData("post_name");
    private final String putPath = path + PropertyGetter.getData("put_name");

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
                .when().get(urlGetPostfix)
                .then().statusCode(200)
                .and().extract().response();
        User expectedObject = JsonReader.readFile(new File(getPath), User.class);
        User actualObject = JsonReader.readIS(response.asInputStream(), User.class);
        Assert.assertEquals(actualObject, expectedObject, "Objects aren't equal!");
    }

    @Test
    @Description("Verifying Status code and Response of POST method test")
    public void postTest() throws JsonValidateException {
        User expectedObject = JsonReader.readFile(new File(postPath), User.class);
        Response response = given()
                .when().contentType(ContentType.JSON)
                .and().body(ServiceActions.putRequestBody(expectedObject).toString())
                .when().post(urlPostPostfix)
                .then().statusCode(201)
                .and().extract().response();
        User actualObject = JsonReader.readIS(response.asInputStream(), User.class);
        Assert.assertEquals(actualObject, expectedObject, "Objects aren't equal!");
    }

    @Test
    @Description("Verifying Status code and Response of PUT method test")
    public void putTest() throws JsonValidateException {
        User expectedObject = JsonReader.readFile(new File(putPath), User.class);
        Response response = given()
                .when().contentType(ContentType.JSON)
                .when().body(ServiceActions.putRequestBody(expectedObject).toString())
                .when().put(urlPutPostfix)
                .then().statusCode(200)
                .and().extract().response();
        User actualObject = JsonReader.readIS(response.asInputStream(), User.class);
        Assert.assertEquals(actualObject, expectedObject, "Objects aren't equal!");
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
