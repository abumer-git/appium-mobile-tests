package pages;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import io.appium.java_client.android.AndroidDriver;

public class BasePage {

    protected AndroidDriver<WebElement> driver;
    protected WebDriverWait wait;

    public BasePage(AndroidDriver<WebElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20); // 20 seconds
    }

    protected WebElement waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return element;
    }

    protected void sendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(text);
    }
}
