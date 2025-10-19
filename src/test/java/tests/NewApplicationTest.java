package tests;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;
import pages.NewApplicationPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NewApplicationTest {

    private NewApplicationPage appPage;
    private static final Logger logger = LoggerFactory.getLogger(NewApplicationTest.class);

    @BeforeAll
    void initPageObjects() {
        // DO NOT re-initialize driver; just get existing singleton
        appPage = new NewApplicationPage(BasePage.getDriver());
        logger.info("NewApplicationPage initialized successfully");
    }
    @Test
    @Order(2)
    public void testCreateNewApplication() {
        logger.info("Starting new application creation test");

        appPage
                .clickApplyForCardInitial()
                .selectOccupation("Precast Concrete Installer")
                .uploadCertificatePickPdf()
                .enterQualificationDetails("City and Guilds")
                .confirmCardAndEnterManualDetails()
                .enterAddressAndSave()
                .swipeAndConfirmCard()
                .enterPaymentAndSubmit();


        logger.info("✅ New application test completed successfully");
    }
}
