package com.beaverg.tests;
;
import com.beaverg.domain.Product;
import com.beaverg.utils.JsonReader;
import com.beaverg.utils.PropertyGetter;
import com.beaverg.utils.custom_exceptions.JsonValidateException;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

@Epic("Dummy json API website testing")
@Feature("CRUD operations testing")
public class RestTest {
    private final String homeUrl;
    private final String urlGetPostfix;

    public RestTest() {
        homeUrl = PropertyGetter.getProperty("home_url");
        urlGetPostfix = PropertyGetter.getData("url_get_postfix");
    }

    @Test
    @Description("Verifying Status code and Response of GET method test")
    public void getTest() throws JsonValidateException {
        SoftAssert sa = new SoftAssert();
        Response response = RestAssured.get(homeUrl + urlGetPostfix);
        sa.assertEquals(response.getStatusCode(), 200, "Status code isn't equal to 200");

        File jsonFile = new File("src/test/resources/data_json/get_rs.json");
        Product expectedProduct = JsonReader.validateAndReadFile(jsonFile, Product.class);
        Product actualProduct = JsonReader.validateAndReadInputStream(response.asInputStream(), Product.class);
        sa.assertEquals(actualProduct, expectedProduct, "Products aren't equal!");

        sa.assertAll();
    }
}
