package pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

public class RegistrationPage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    public RegistrationPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
    }

    public RegistrationPage clickNewToCSCS() {
        WebElement btnNew = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.id("com.cscsonline.cscs:id/btnNewToCscs")));
        btnNew.click();
        return this;
    }

    public RegistrationPage enterFirstName(String firstName) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.xpath("//android.widget.EditText[@text='Enter your first name']")));
        el.sendKeys(firstName);
        driver.hideKeyboard();
        return this;
    }

    public RegistrationPage clickNextAfterFirstName() {
        WebElement next = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(3)")));
        next.click();
        return this;
    }

    public RegistrationPage enterLastName(String lastName) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.xpath("//android.widget.EditText[@text='Enter your last name']")));
        el.sendKeys(lastName);
        driver.hideKeyboard();
        return this;
    }

    public RegistrationPage clickNextAfterLastName() {
        WebElement btnNext = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.id("com.cscsonline.cscs:id/btnNext")));
        btnNext.click();
        return this;
    }

    public RegistrationPage selectDOB(String year, String accessibilityDay) {
        // Open DOB picker
        WebElement dob = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(1)")));
        dob.click();

        // Open year picker
        WebElement yearHeader = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.id("android:id/date_picker_header_year")));
        yearHeader.click();

        // Swipe twice (to reach the target year range)
        performSwipe(545, 811, 549, 1605);
        performSwipe(557, 827, 569, 1593);

        // Select year dynamically
        WebElement elYear = driver.findElement(
                MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + year + "\")"));
        elYear.click();

        // Navigate months (currently 3 times forward)
        WebElement nextMonthBtn = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.AccessibilityId("Next month")));
        nextMonthBtn.click();
        nextMonthBtn.click();
        nextMonthBtn.click();

        // Select day dynamically
        WebElement day = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.AccessibilityId(accessibilityDay)));
        day.click();

        // Confirm selection
        WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.id("android:id/button1")));
        okBtn.click();

        return this;
    }

    // Reusable swipe method with W3C actions
    private void performSwipe(int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }



    public RegistrationPage clickNextAfterDOB() {
        WebElement btnNext = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.id("com.cscsonline.cscs:id/btnNext")));
        btnNext.click();
        return this;
    }

    public RegistrationPage enterEmail(String email) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.className("android.widget.EditText")));
        el.sendKeys(email);
        driver.hideKeyboard();
        return this;
    }
    public RegistrationPage toggleTermsSwitch() {
        WebElement termsSwitch = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.id("com.cscsonline.cscs:id/switchTermsConditions")));
        termsSwitch.click();
        return this;
    }

    public RegistrationPage clickNextAfterEmail() {
        WebElement btnNext = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(4)")));
        btnNext.click();
        return this;
    }

    public RegistrationPage loginAfterEmailOTP() {
        WebElement btnLogin = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(3)")));
        btnLogin.click();
        return this;
    }

    public RegistrationPage enterMobile(String mobile) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.id("com.cscsonline.cscs:id/edtMobileNumber")));
        el.sendKeys(mobile);
        driver.hideKeyboard();
        return this;
    }

    public RegistrationPage clickNextAfterMobile() {
        WebElement btnNext = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(5)")));
        btnNext.click();
        return this;
    }

    public RegistrationPage loginAfterMobileOTP() {
        WebElement btnLogin = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(3)")));
        btnLogin.click();
        return this;
    }

    public RegistrationPage enterPIN(String pin) {
        WebElement pinView = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.id("com.cscsonline.cscs:id/pinView")));
        pinView.sendKeys(pin);
        driver.hideKeyboard();
        return this;
    }

    public RegistrationPage tapZeroTimes(int times) {
        WebElement zeroBtn = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.id("com.cscsonline.cscs:id/numpad_btn_zero")));
        for (int i = 0; i < times; i++) {
            zeroBtn.click();
        }
        return this;
    }

    public RegistrationPage cancelTwice() {
        WebElement cancel1 = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.id("com.cscsonline.cscs:id/btn_cancel")));
        cancel1.click();
        WebElement cancel2 = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.id("com.cscsonline.cscs:id/btn_cancel")));
        cancel2.click();
        return this;
    }
    public String getDisplayedUserName() {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(
                MobileBy.id("com.cscsonline.cscs:id/tvUserName")));
        return el.getText();
    }


}
