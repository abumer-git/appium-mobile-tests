package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestSuiteTearDown {

    private static final Logger logger = LoggerFactory.getLogger(TestSuiteTearDown.class);

    @AfterAll
    public void closeDriverAfterSuite() {
        logger.info("TestSuiteTearDown: closing shared Appium driver.");
        BasePage.quitDriver();
    }
}
