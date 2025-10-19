package tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;
import pages.RegistrationPage;
import utils.TestData;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistrationTest {

    private RegistrationPage registrationPage;
    private TestData.User registeredUser;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationTest.class);

    @BeforeAll
    void initPageObjects() {
        BasePage.initializeDriver(); // initialize singleton driver
        registrationPage = new RegistrationPage(BasePage.getDriver());
        logger.info("RegistrationPage initialized successfully");
    }
    @Test
    @Order(1)
    public void testRegistrationFlow() {
        logger.info("Starting registration flow test");
        Allure.addAttachment("Registration Log", "Starting Registration Flow");

        registeredUser = TestData.getRandomUser();
        registeredUser.pin = "ABC123";

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

        String expectedName = registeredUser.firstName + " " + registeredUser.lastName;
        String actualName = registrationPage.getDisplayedUserName();

        logger.info("Asserting Home page username... Expected: {} Actual: {}", expectedName, actualName);
        assertEquals(expectedName, actualName, "Displayed username should match registration details");

        Allure.addAttachment("Assertion Log", "Expected: " + expectedName + ", Actual: " + actualName);
        Allure.addAttachment("Registration Log", "Completed Registration Flow");

        logger.info("✅ Registration flow completed successfully");
    }
}
