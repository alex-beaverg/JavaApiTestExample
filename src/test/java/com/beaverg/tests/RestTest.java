package com.beaverg.tests;
;
import com.beaverg.domain.Product;
import com.beaverg.utils.JsonReader;
import com.beaverg.utils.PropertyGetter;
import com.beaverg.utils.RequestBodyPutting;
import com.beaverg.utils.custom_exceptions.JsonValidateException;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

@Epic("Dummy json API website testing")
@Feature("CRUD operations testing")
public class RestTest {
    private final String homeUrl;
    private final String urlGetPostfix;
    private final String urlPostPostfix;

    public RestTest() {
        homeUrl = PropertyGetter.getProperty("home_url");
        urlGetPostfix = PropertyGetter.getData("url_get_postfix");
        urlPostPostfix = PropertyGetter.getData("url_post_postfix");
    }

    @Test
    @Description("Verifying Status code and Response of GET method test")
    public void getTest() throws JsonValidateException {
        SoftAssert sa = new SoftAssert();
        Response response = RestAssured.get(homeUrl + urlGetPostfix);
        sa.assertEquals(response.getStatusCode(), 200, "Status code isn't equal to 200");

        File jsonFile = new File("src/test/resources/data_json/get.json");
        Product expectedProduct = JsonReader.validateAndReadFile(jsonFile, Product.class);
        Product actualProduct = JsonReader.validateAndReadInputStream(response.asInputStream(), Product.class);
        sa.assertEquals(actualProduct, expectedProduct, "Products aren't equal!");

        sa.assertAll();
    }

    @Test
    @Description("Verifying Status code and Response of POST method test")
    public void postTest() throws JsonValidateException {
        SoftAssert sa = new SoftAssert();
        File jsonRequestFile = new File("src/test/resources/data_json/post.json");
        Product expectedProduct = JsonReader.validateAndReadFile(jsonRequestFile, Product.class);
        JSONObject requestBody = RequestBodyPutting.putRequestBody(expectedProduct);
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.post(homeUrl + urlPostPostfix);
        sa.assertEquals(response.getStatusCode(), 200, "Status code isn't equal to 200");

        Product  actualProduct = JsonReader.validateAndReadInputStream(response.asInputStream(), Product.class);
        sa.assertEquals(actualProduct, expectedProduct, "Products aren't equal!");

        sa.assertAll();
    }
}
