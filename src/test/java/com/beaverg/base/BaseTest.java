package com.beaverg.base;

import com.beaverg.utils.PropertyGetter;
import io.qameta.allure.Allure;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class BaseTest implements ITestListener {
    protected final String homeUrl;
    protected final String urlGetPostfix;
    protected final String urlPostPostfix;

    public BaseTest() {
        homeUrl = PropertyGetter.getProperty("home_url");
        urlGetPostfix = PropertyGetter.getData("url_get_postfix");
        urlPostPostfix = PropertyGetter.getData("url_post_postfix");
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {
        if (result.getStatus() == 1) {
            Allure.addAttachment("Message", String.format("Test '%s' was completed successfully!", result.getName()));
        } else if (result.getStatus() == 2) {
            Allure.addAttachment("Message", String.format("Test '%s' was failed!", result.getName()));
        }
    }
}
