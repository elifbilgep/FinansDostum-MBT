import org.graphwalker.java.annotation.AfterElement;
import org.graphwalker.java.annotation.BeforeElement;
import org.graphwalker.java.annotation.GraphWalker;
import org.graphwalker.java.annotation.BeforeExecution;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.HomePage;
import utils.CoverageValue;
import utils.IOSBaseTestClass;
import webdriver.AppiumServer;
import webdriver.ReadProperties;
import webdriver.WebDriverWaits;

import java.io.IOException;

@GraphWalker(value = "random(edge_coverage(100))", start = "v_Home")
public class AddIncomeTestModel extends IOSBaseTestClass implements AddIncome {
    private static final Logger logger = LoggerFactory.getLogger(AddIncomeTestModel.class);

    @Rule
    public TestWatcher testWatcher = new TestResultHandler();

    private String appBundleId;
    private HomePage homePage;
    private AssertionError assertionError = null;
    private long stepStartTime; // To track individual step execution time

    @BeforeExecution
    public void setup() throws IOException, InterruptedException {
        logger.info("🚀 [Setup] Appium server başlatılıyor...");
        TestLogger.logInfo("Setting up test environment");
        stepStartTime = System.currentTimeMillis();

//        AppiumServer appiumServer = new AppiumServer();
//        appiumServer.startServer();

        logger.info("✅ [Setup] Appium server başlatıldı!");
        TestLogger.logInfo("Appium server started");

        ReadProperties readProperties = new ReadProperties();
        String appLaunchActivity = readProperties.getProp("iosAppActivity");
        appBundleId = "com.tunaarikaya.FinansDostu"; // Using the bundleId from DriverSetup.java
        TestLogger.logInfo("App bundle ID: " + appBundleId);

        logger.info("🧠 [Setup] Driver başlatılıyor...");
        TestLogger.logInfo("Initializing iOS driver");
        initiateDriver("iPhone 16 Pro", appBundleId, appLaunchActivity, true);

        logger.info("📲 [Setup] Uygulama aktive ediliyor...");
        TestLogger.logInfo("Activating application");
        if (appBundleId != null && !appBundleId.isEmpty()) {
            driver.activateApp(appBundleId);
            // Homepage nesnesini setup'ta başlat
            this.homePage = new HomePage(driver);
            TestLogger.logInfo("HomePage initialized");
        } else {
            String errorMsg = "Bundle ID is null or empty. Cannot activate app.";
            TestLogger.logError("Setup", errorMsg, null);
            throw new RuntimeException(errorMsg);
        }

        logger.info("🎉 [Setup] Kurulum tamamlandı!");
        long setupTime = System.currentTimeMillis() - stepStartTime;
        TestLogger.logInfo("Setup completed in " + setupTime + " ms");
    }

    @BeforeElement
    public void beforeElement() {
        String elementName = getCurrentElement().getName();
        logger.info("➡️ Başlamadan önce: " + elementName);

        // Log the start of this test step
        TestLogger.logStepStart(elementName);
        stepStartTime = System.currentTimeMillis();

        // Eğer önceki adımda bir assertion hatası oluştuysa, testi durdur
        if (assertionError != null) {
            logger.error("❌ Önceki adımda assertion hatası oluştu! Test durduruluyor...");
            TestLogger.logError(elementName, "Previous step had an assertion error. Stopping test.", assertionError);
            throw assertionError;
        }
    }

    @AfterElement
    public void afterElement() {
        String elementName = getCurrentElement().getName();
        logger.info("✅ Bitti: " + elementName);

        // Calculate and log step execution time
        long stepTime = System.currentTimeMillis() - stepStartTime;
        TestLogger.logStepEnd(elementName);

        // Only log the most basic, safe information
        try {
            // Just log the completed element name
            TestLogger.logInfo("Completed element: " + elementName);
        } catch (Exception e) {
            // Suppress any exceptions from logging - we don't want the test to fail
        }
    }

    @Override
    public void e_CleanIncomeAmount() {
        logger.info("📱 Miktar temizleniyor...");
        TestLogger.logInfo("Cleaning income amount field");

        try {
            homePage.cleanAmount();
            logger.info("📱 Miktar temizlendi");
            TestLogger.logInfo("Income amount field cleaned successfully");
        } catch (Exception e) {
            TestLogger.logError("e_CleanIncomeAmount", "Error while cleaning amount", e);
            throw e;
        }
    }

    @Override
    public void v_CleanedAmountFielde() {
        try {
            TestLogger.logInfo("Verifying cleaned amount field");
            homePage.checkCleanedAmountField();
            TestLogger.logInfo("Amount field is clean as expected");
        } catch (AssertionError e) {
            logger.error("❌ ASSERT HATASI in v_CleanedAmountFielde: " + e.getMessage());
            TestLogger.logError("v_CleanedAmountFielde", "Assertion error: Amount field is not cleaned properly", e);
            this.assertionError = e;
            throw e;
        }
    }

    @Override
    public void v_ExpenseSheetOpened() {
        try {
            TestLogger.logInfo("Verifying expense sheet is open");
            homePage.checkAddExpenseSheet();
            TestLogger.logInfo("Expense sheet is open as expected");
        } catch (AssertionError e) {
            logger.error("❌ ASSERT HATASI in v_ExpenseSheetOpened: " + e.getMessage());
            TestLogger.logError("v_ExpenseSheetOpened", "Assertion error: Expense sheet is not open", e);
            this.assertionError = e;
            throw e;
        }
    }

    @Override
    public void v_Home() {
        // homePage.checkHomeView();
        TestLogger.logInfo("At home view");
    }

    @Override
    public void v_NoteAdded() {
        try {
            TestLogger.logInfo("Verifying note was added");
            homePage.checkNoteAdded();
            TestLogger.logInfo("Note was added successfully");
        } catch (AssertionError e) {
            logger.error("❌ ASSERT HATASI in v_NoteAdded: " + e.getMessage());
            TestLogger.logError("v_NoteAdded", "Assertion error: Note was not added properly", e);
            this.assertionError = e;
            throw e;
        }
    }

    @Override
    public void e_SystemRedirect() {
        TestLogger.logInfo("System redirect to home view");
        homePage.checkHomeView();
    }

    @Override
    public void e_Cancelncome() {
        TestLogger.logInfo("Cancelling income addition");
        homePage.cancelAddIncome();
    }

    @Override
    public void v_IncomeAmountFieldFilled() {
        try {
            TestLogger.logInfo("Verifying income amount field is filled");
            homePage.checkIncomeAmountFilled();
            TestLogger.logInfo("Income amount field is filled as expected");
        } catch (AssertionError e) {
            logger.error("❌ ASSERT HATASI in v_IncomeAmountFieldFilled: " + e.getMessage());
            TestLogger.logError("v_IncomeAmountFieldFilled", "Assertion error: Income amount field is not properly filled", e);
            this.assertionError = e;
            throw e;
        }
    }

    @Override
    public void e_SelectIncomeType() {
        TestLogger.logInfo("Selecting income type");
        homePage.selectIncomeTag();
    }

    @Override
    public void v_IncomeTitleWritten() {
        try {
            TestLogger.logInfo("Verifying income title is written");
            homePage.checkIncomeTitleWritten();
            TestLogger.logInfo("Income title is written as expected");
        } catch (AssertionError e) {
            logger.error("❌ ASSERT HATASI in v_IncomeTitleWritten: " + e.getMessage());
            TestLogger.logError("v_IncomeTitleWritten", "Assertion error: Income title is not written properly", e);
            this.assertionError = e;
            throw e;
        }
    }

    @Override
    public void e_SaveIncome() {
        TestLogger.logInfo("Saving income");
        homePage.saveIncome();
    }

    @Override
    public void v_IncomeSaved() {
        try {
            TestLogger.logInfo("Verifying income has been saved");
            homePage.checkIncomeSaved();
            TestLogger.logInfo("Income saved successfully");
        } catch (AssertionError e) {
            logger.error("❌ ASSERT HATASI in v_IncomeSaved: " + e.getMessage());
            TestLogger.logError("v_IncomeSaved", "Assertion error: Income was not saved properly", e);
            this.assertionError = e;
            throw e;
        }
    }

    @Override
    public void e_SwitchToExpense() {
        TestLogger.logInfo("Switching to expense view");
        homePage.switchToExpense();
    }

    @Override
    public void e_TypeIncomeTitle() {
        TestLogger.logInfo("Typing income title");
        homePage.typeIncomeTitleTextField();
    }

    @Override
    public void v_IncomeSheetOpened() {
        try {
            TestLogger.logInfo("Verifying income sheet is open");
            homePage.checkAddIncomeSheet();
            TestLogger.logInfo("Income sheet is open as expected");
        } catch (AssertionError e) {
            logger.error("❌ ASSERT HATASI in v_IncomeSheetOpened: " + e.getMessage());
            TestLogger.logError("v_IncomeSheetOpened", "Assertion error: Income sheet is not open", e);
            this.assertionError = e;
            throw e;
        }
    }

    @Override
    public void e_CancelIncome() {
        TestLogger.logInfo("Cancelling income addition");
        homePage.cancelAddIncome();
    }

    @Override
    public void e_SwitchToIncome() {
        TestLogger.logInfo("Switching to income view");
        homePage.switchToIncome();
    }

    @Override
    public void e_FillIncomeAmountField() {
        TestLogger.logInfo("Filling income amount field");
        homePage.fillIncomeAmount();
    }

    @Override
    public void e_ClickAddButton() {
        TestLogger.logInfo("Clicking add button");
        new HomePage(driver).clickAddIncomeButton();
    }

    @Override
    public void v_TagSelected() {
        try {
            TestLogger.logInfo("Verifying tag is selected");
            homePage.checkTagSelected();
            TestLogger.logInfo("Tag selected as expected");
        } catch (AssertionError e) {
            logger.error("❌ ASSERT HATASI in v_TagSelected: " + e.getMessage());
            TestLogger.logError("v_TagSelected", "Assertion error: Tag is not selected properly", e);
            this.assertionError = e;
            throw e;
        }
    }

    @Override
    public void e_AddNote() {
        TestLogger.logInfo("Adding note");
        homePage.addNote();
    }
}