package tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class RegistrationTest {
    private static AndroidDriver driver;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "12.0");
        caps.setCapability("deviceName", "sdk_gphone64_arm64");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("app", "/Users/abumer/Downloads/CR-39D-Final_QA 1.apk");
        caps.setCapability("noReset", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
    }

    @Test
    public void testRegistrationFlow() {
        // Your recorded steps:
        List<WebElement> els2 = driver.findElements(AppiumBy.id("com.cscsonline.cscs:id/btnNewToCscs"));
        els2.get(0).click();

        List<WebElement> els3 = driver.findElements(AppiumBy.xpath("//android.widget.EditText[@text=\"Enter your first name\"]"));
        els3.get(0).sendKeys("Tom");

        List<WebElement> els4 = driver.findElements(AppiumBy.xpath("//androidx.cardview.widget.CardView[@resource-id=\"com.cscsonline.cscs:id/cardView\"]/android.view.ViewGroup"));
        els4.get(0).click();

        List<WebElement> els5 = driver.findElements(AppiumBy.xpath("//android.widget.EditText[@text=\"Enter your last name\"]"));
        els5.get(0).sendKeys("Khan");

        List<WebElement> els6 = driver.findElements(AppiumBy.id("com.cscsonline.cscs:id/btnNext"));
        els6.get(0).click();

        List<WebElement> els7 = driver.findElements(AppiumBy.id("com.cscsonline.cscs:id/tvSelectedDob"));
        els7.get(0).click();

        List<WebElement> els8 = driver.findElements(AppiumBy.id("android:id/date_picker_header_year"));
        els8.get(0).click();

        List<WebElement> els9 = driver.findElements(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"2005\"]"));
        els9.get(0).click();

        List<WebElement> els10 = driver.findElements(AppiumBy.accessibilityId("21 September 2005"));
        els10.get(0).click();

        List<WebElement> els11 = driver.findElements(AppiumBy.id("android:id/button1"));
        els11.get(0).click();

        WebElement el1 = driver.findElement(AppiumBy.id("com.cscsonline.cscs:id/btnNext"));
        el1.click();

        WebElement el2 = driver.findElement(AppiumBy.className("android.widget.EditText"));
        el2.sendKeys("tom.khan@yopmail.com");

        WebElement el3 = driver.findElement(AppiumBy.id("com.cscsonline.cscs:id/switchTermsConditions"));
        el3.click();

        WebElement el4 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(4)"));
        el4.click();

        WebElement el5 = driver.findElement(AppiumBy.id("com.cscsonline.cscs:id/pinView"));
        el5.sendKeys("ABC123");

        WebElement el6 = driver.findElement(AppiumBy.id("com.cscsonline.cscs:id/textView"));
        el6.click();

        WebElement el7 = driver.findElement(AppiumBy.id("com.cscsonline.cscs:id/edtMobileNumber"));
        el7.sendKeys("7889900667");

        WebElement el8 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(5)"));
        el8.click();

        WebElement el9 = driver.findElement(AppiumBy.id("com.cscsonline.cscs:id/pinView"));
        el9.sendKeys("ABC123");

        WebElement el10 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(3)"));
        el10.click();

        WebElement el11 = driver.findElement(AppiumBy.id("com.cscsonline.cscs:id/numpad_btn_zero"));
        for (int i = 0; i < 12; i++) {
            el11.click();
        }

        WebElement el12 = driver.findElement(AppiumBy.id("com.cscsonline.cscs:id/btn_cancel"));
        el12.click();
        WebElement el13 = driver.findElement(AppiumBy.id("com.cscsonline.cscs:id/btn_cancel"));
        el13.click();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
