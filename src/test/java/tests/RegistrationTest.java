package tests;

import org.junit.jupiter.api.*;
import utils.TestData;
import pages.RegistrationPage;
import io.appium.java_client.android.AndroidDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationTest {

    private static AndroidDriver driver;
    private static TestData.User registeredUser;
    private static RegistrationPage registrationPage;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationTest.class);

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "12.0");
        caps.setCapability("deviceName", "sdk_gphone64_arm64");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("app", "/Users/abumer/Downloads/CR-39D-Final_QA_1.apk");
        caps.setCapability("noReset", true);
        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("dontStopAppOnReset", true);


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.resetApp();

        registrationPage = new RegistrationPage(driver);
        logger.info("Driver and RegistrationPage initialized successfully");
    }

    @Test
    @Order(1)
    public void testRegistrationFlow() {
        logger.info("Starting registration flow test");
        registeredUser = TestData.getRandomUser();
        registeredUser.pin = "ABC123"; // hardcode pin

        registrationPage
                .clickNewToCSCS()
                .enterFirstName(registeredUser.firstName)
                .clickNextAfterFirstName()
                .enterLastName(registeredUser.lastName)
                .clickNextAfterLastName()
                .selectDOB("1993", "03 December 1993")
                .clickNextAfterDOB()
                .enterEmail(registeredUser.email)
                .toggleTermsSwitch()
                .clickNextAfterEmail()
                .enterPIN(registeredUser.pin)
                .loginAfterEmailOTP()
                .enterMobile(registeredUser.mobile)
                .clickNextAfterMobile()
                .enterPIN(registeredUser.pin)
                .loginAfterMobileOTP()
                .tapZeroTimes(12)
                .cancelTwice();
        //  Final assertion
        String expectedName = registeredUser.firstName + " " + registeredUser.lastName;
        String actualName = registrationPage.getDisplayedUserName();

        logger.info("Asserting Home page username...");
        logger.info("Expected: " + expectedName);
        logger.info("Actual: " + actualName);

        assertEquals(expectedName, actualName,
                "The displayed user name on Home page should match registration details");

        logger.info("Assertion passed: Home page username is correct ✅");

        logger.info("Registration flow completed successfully");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Driver closed");
        }
    }
}
