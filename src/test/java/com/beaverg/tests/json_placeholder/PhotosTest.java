package com.beaverg.tests.json_placeholder;

import com.beaverg.domain.json_placeholder.photos.Photo;
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
@Feature("'jsonplaceholder.typicode.com' API photos testing")
public class PhotosTest {
    private final String url = PropertyGetter.getProperty("jp.photos_url");
    private final String path = PropertyGetter.getProperty("jp.photos_path");

    private final String getUrl = PropertyGetter.getData("jp.get");
    private final String postUrl = PropertyGetter.getData("jp.post");
    private final String putUrl = PropertyGetter.getData("jp.put");
    private final String delUrl = PropertyGetter.getData("jp.del");
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
        Photo expectedObject = JsonReader.readFile(new File(getPath), Photo.class);
        Photo actualObject = JsonReader.readIS(response.asInputStream(), Photo.class);
        Assert.assertEquals(actualObject, expectedObject, "Objects aren't equal!");
    }

    @Test
    @Description("Verifying Status code and Response of POST method test")
    public void postTest() throws JsonValidateException {
        Photo expectedObject = JsonReader.readFile(new File(postPath), Photo.class);
        Response response = given()
                .when().contentType(ContentType.JSON)
                .and().body(ServiceActions.putRequestBody(expectedObject).toString())
                .when().post(postUrl)
                .then().statusCode(201)
                .and().extract().response();
        Photo actualObject = JsonReader.readIS(response.asInputStream(), Photo.class);
        Assert.assertEquals(actualObject, expectedObject, "Objects aren't equal!");
    }

    @Test
    @Description("Verifying Status code and Response of PUT method test")
    public void putTest() throws JsonValidateException {
        Photo expectedObject = JsonReader.readFile(new File(putPath), Photo.class);
        Response response = given()
                .when().contentType(ContentType.JSON)
                .when().body(ServiceActions.putRequestBody(expectedObject).toString())
                .when().put(putUrl)
                .then().statusCode(200)
                .and().extract().response();
        Photo actualObject = JsonReader.readIS(response.asInputStream(), Photo.class);
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
