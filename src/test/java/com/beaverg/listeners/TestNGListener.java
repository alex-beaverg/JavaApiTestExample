package com.beaverg.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.beaverg.utils.Printers.*;

public class TestNGListener implements ITestListener {
    public static final Logger LOGGER = LogManager.getLogger(TestNGListener.class);

    // Colors:
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    @Override
    public void onTestStart(ITestResult result) {
        PRINT2LN.info(String.format(ANSI_BLUE + "Test: '%s'", result.getName()));
        PRINTLN.info(String.format("Group: '%s'", result.getTestContext().getName()));
        PRINTLN.info(String.format("Suite: '%s'", result.getTestContext().getSuite().getName()));
        PRINTLN.info("Test was started!" + ANSI_RESET);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        PRINTLN.info(String.format(ANSI_GREEN + "Test '%s' was completed successfully!", result.getName()));
        PRINTLN.info(ANSI_RESET);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.error(String.format("Test '%s' was failed!", result.getName()));
        PRINTLN.info("");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        PRINTLN.info(String.format(ANSI_YELLOW + "Test '%s' was skipped!", result.getName()));
        PRINTLN.info(ANSI_RESET);
    }
}
