package pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.logging.Logger;

public class NewApplicationPage extends BasePage {

    private final AndroidDriver<WebElement> driver;
    private final WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(NewApplicationPage.class.getName());

    public NewApplicationPage(AndroidDriver<WebElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
    }

    // ---------- Helpers ----------
    private WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private void click(By locator) {
        waitForClickable(locator).click();
    }

    private void safeClick(By locator) {
        try {
            click(locator);
        } catch (WebDriverException e) {
            logger.warning("Click failed — retrying: " + e.getMessage());
            sleepShort();
            click(locator);
        }
    }

    private void type(By locator, String text) {
        WebElement el = waitForVisibility(locator);
        el.clear();
        el.sendKeys(text);
        try { driver.hideKeyboard(); } catch (Exception ignored) {}
    }

    private void sleepShort() {
        try { Thread.sleep(400); } catch (InterruptedException ignored) {}
    }

    private void swipe(int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(800),
                PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }

    // ---------- Page Actions ----------

    public NewApplicationPage clickApplyForCardInitial() {
        safeClick(MobileBy.id("com.cscsonline.cscs:id/tvTitle"));
        safeClick(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Apply for a Card\")"));
        safeClick(MobileBy.id("com.cscsonline.cscs:id/btnSkip"));
        safeClick(MobileBy.id("com.cscsonline.cscs:id/btnGetStarted"));
        logger.info("Navigated initial Apply for Card screens");
        return this;
    }

    public NewApplicationPage selectOccupation(String occupation) {
        safeClick(MobileBy.id("com.cscsonline.cscs:id/layoutSelectOccupation"));
        type(MobileBy.id("com.cscsonline.cscs:id/searchEditText"), occupation);
        waitForPresence(MobileBy.id("com.cscsonline.cscs:id/textView")).click();
        safeClick(MobileBy.id("com.cscsonline.cscs:id/btnNext"));
        logger.info("Occupation selected: " + occupation);
        return this;
    }

    public NewApplicationPage uploadCertificatePickPdf() {
        safeClick(MobileBy.id("com.cscsonline.cscs:id/tvFileInfo"));
        safeClick(MobileBy.id("com.cscsonline.cscs:id/btnPickPdf"));
        waitForPresence(MobileBy.AndroidUIAutomator(
                "new UiSelector().resourceId(\"com.google.android.documentsui:id/icon_thumb\").instance(0)")
        ).click();
        safeClick(MobileBy.id("com.cscsonline.cscs:id/btnEnterManually"));
        logger.info("Picked first PDF and switched to manual entry");
        return this;
    }

    public NewApplicationPage enterQualificationDetails(String qualificationText) {
        safeClick(MobileBy.id("com.cscsonline.cscs:id/tvAwardingBodyTitle"));
        safeClick(MobileBy.AndroidUIAutomator("new UiSelector().text(\"City And Guilds\")"));
        safeClick(MobileBy.id("com.cscsonline.cscs:id/cardLayoutQualificationTitle"));
        safeClick(MobileBy.id("com.cscsonline.cscs:id/tvSearchResult"));

        type(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Enter\").instance(0)"), qualificationText);

        safeClick(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(2)"));
        safeClick(MobileBy.id("android:id/button1"));
        safeClick(MobileBy.id("com.cscsonline.cscs:id/btnNext"));
        logger.info("Qualification entered: " + qualificationText);
        return this;
    }

    public NewApplicationPage confirmCardAndEnterManualDetails() {
        safeClick(MobileBy.id("com.cscsonline.cscs:id/confirmCardButton"));
        safeClick(MobileBy.id("com.cscsonline.cscs:id/btnEnterDetailsManually"));
        safeClick(MobileBy.id("com.cscsonline.cscs:id/hseBottomSheetTvCitbTest"));
        safeClick(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Operatives\")"));

        type(MobileBy.id("com.cscsonline.cscs:id/editTestId"), "4534314");

        safeClick(MobileBy.id("com.cscsonline.cscs:id/btnContinue"));
        logger.info("Manual test details entered");
        return this;
    }

    public NewApplicationPage enterAddressAndSave() {
        type(MobileBy.id("com.cscsonline.cscs:id/etPostcode"), "BT191AR");
        safeClick(MobileBy.id("com.cscsonline.cscs:id/spinnerSelectAddress"));
        safeClick(MobileBy.AndroidUIAutomator(
                "new UiSelector().text(\"Poundstretcher, Unit 7, Clandeboye Retail Park, Bangor, County Down\")"
        ));
        safeClick(MobileBy.id("com.cscsonline.cscs:id/btnSave"));

        safeClick(MobileBy.id("com.cscsonline.cscs:id/spinnerSelectTitle"));
        safeClick(MobileBy.id("com.cscsonline.cscs:id/mrOption"));
        safeClick(MobileBy.id("com.cscsonline.cscs:id/nextButton"));
        logger.info("Address saved and next pressed");
        return this;
    }

    public NewApplicationPage swipeAndConfirmCard() {
        swipe(532, 2003, 565, 496);
        safeClick(MobileBy.id("com.cscsonline.cscs:id/confirmCardButton"));
        return this;
    }

    public NewApplicationPage enterPaymentAndSubmit() {
        safeClick(MobileBy.AndroidUIAutomator("new UiSelector().text(\"VISA\")"));
        type(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"cardNumber\")"), "4005520000000129");
        type(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"cardholderName\")"), "Test User");

        swipe(475, 2130, 479, 1057);

        safeClick(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"expiryMonth\")"));
        safeClick(MobileBy.AndroidUIAutomator("new UiSelector().text(\"04\")"));

        safeClick(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"expiryYear\")"));
        safeClick(MobileBy.AndroidUIAutomator("new UiSelector().text(\"2029\")"));

        type(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"cardCode_masked\")"), "111");

        safeClick(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"nextBtn\")"));
        safeClick(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"yes\")"));
        logger.info("Payment entered and confirmed");
        return this;
    }
}
