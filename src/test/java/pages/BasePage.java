package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class BasePage {

    protected static AndroidDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    private static boolean initialized = false;

    /**
     * Initialize driver once. Safe to call from any test's @BeforeAll.
     */
    public static synchronized void initializeDriver() {
        if (initialized && driver != null) {
            logger.info("Reusing existing Appium driver session.");
            return;
        }

        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("platformVersion", "12.0");
            caps.setCapability("deviceName", "sdk_gphone64_arm64");
            caps.setCapability("automationName", "UiAutomator2");
            caps.setCapability("app", "/Users/abumer/Downloads/MyCSCS-QA-CR39A.apk");

            // Keep app state between tests (persistent session)
            caps.setCapability("noReset", false);
            caps.setCapability("fullReset", false);

            caps.setCapability("autoGrantPermissions", true);
            caps.setCapability("appium:appWaitForLaunch", true);
            caps.setCapability("unicodeKeyboard", false);
            caps.setCapability("resetKeyboard", false);
            caps.setCapability("ignoreHiddenApiPolicyError", true);
            caps.setCapability("disableWindowAnimation", true);



            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
            initialized = true;
            logger.info("Appium driver initialized successfully (single shared session).");

            // Add JVM shutdown hook as a safety net to close driver if tests or CI exit
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    if (driver != null) {
                        logger.info("JVM shutdown: quitting Appium driver.");
                        driver.quit();
                    }
                } catch (Exception e) {
                    logger.warn("Exception during JVM shutdown driver quit: {}", e.getMessage());
                }
            }));

        } catch (MalformedURLException e) {
            logger.error("Failed to initialize Appium driver: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Getter for tests/page objects.
     */
    public static AndroidDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    /**
     * Quit driver manually (call once at the end of the suite).
     */
    public static synchronized void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
                logger.info("Appium driver closed successfully.");
            } catch (Exception e) {
                logger.warn("Error while quitting driver: {}", e.getMessage());
            } finally {
                driver = null;
                initialized = false;
            }
        } else {
            logger.info("quitDriver called but driver was already null.");
        }
    }
}
