//import io.appium.java_client.MobileBy;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//
//@Test
////    @Order(2)
//@Disabled("Skipping login test for now")
//public void testLoginAfterRegistration() {
//    // Get dynamic email and PIN from TestData
//
//    // Step 1: Navigate back / open menu
//    WebElement navigateUp = wait.until(ExpectedConditions.elementToBeClickable(
//            MobileBy.AccessibilityId("Navigate up")));
//    navigateUp.click();
//
//    // Step 2: Click Logout
//    wait.until(ExpectedConditions.elementToBeClickable(
//            MobileBy.id("com.cscsonline.cscs:id/logout"))).click();
//
//
//    // Step 3: Confirm logout
//    WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(
//            MobileBy.id("com.cscsonline.cscs:id/btn_ok")));
//    okBtn.click();
//
//    // Step 4: Click "Already have card"
//    WebElement alreadyHaveCard = wait.until(ExpectedConditions.elementToBeClickable(
//            MobileBy.id("com.cscsonline.cscs:id/btnAlreadyHaveCard")));
//    alreadyHaveCard.click();
//
//    // Step 5: Enter email
//    WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(
//            MobileBy.AndroidUIAutomator("new UiSelector().text(\"Enter your Email\")")));
//    emailField.click();
//    WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(
//            MobileBy.className("android.widget.EditText")));
//    emailInput.sendKeys(registeredUser.email);
//
//    // Step 6: Continue
//    WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
//            MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(3)")));
//    continueBtn.click();
//
//    // Step 7: Enter PIN
//    WebElement pinView = wait.until(ExpectedConditions.elementToBeClickable(
//            MobileBy.id("com.cscsonline.cscs:id/pinView")));
//    pinView.sendKeys(registeredUser.pin);
//
//    // Step 8: Confirm login
//    WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
//            MobileBy.id("com.cscsonline.cscs:id/textView")));
//    confirmBtn.click();
//
//    // Step 9: Enter passcode (0 pressed 12 times)
//    WebElement numZero = wait.until(ExpectedConditions.elementToBeClickable(
//            MobileBy.id("com.cscsonline.cscs:id/numpad_btn_zero")));
//    for (int i = 0; i < 12; i++) {
//        numZero.click();
//    }
//}
