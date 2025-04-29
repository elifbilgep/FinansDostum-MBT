import org.graphwalker.java.annotation.AfterElement;
import org.graphwalker.java.annotation.BeforeElement;
import org.graphwalker.java.annotation.GraphWalker;
import pages.HomePage;
import utils.CoverageValue;
import utils.IOSBaseTestClass;
import org.graphwalker.java.annotation.BeforeExecution;

import webdriver.AppiumServer;
import webdriver.ReadProperties;

import java.io.IOException;

@GraphWalker(value = CoverageValue.WeightedRandomEdgeCoverage60, groups= "nhs")
public class AddIncomeTestModel extends IOSBaseTestClass implements AddIncome {

    @BeforeExecution
    public void setup() throws IOException, InterruptedException {
        System.out.println("🚀 [Setup] Appium server başlatılıyor...");

        AppiumServer appiumServer = new AppiumServer();
        appiumServer.startServer();

        System.out.println("✅ [Setup] Appium server başlatıldı!");

        ReadProperties readProperties = new ReadProperties();
        String appPackage = readProperties.getProp("iosAppPackage");
        String appLaunchActivity = readProperties.getProp("iosAppActivity");

        System.out.println("🧠 [Setup] Driver başlatılıyor...");
        initiateDriver("iPhone 16 Pro", appPackage, appLaunchActivity, true);

        System.out.println("📲 [Setup] Uygulama aktive ediliyor...");
        driver.activateApp(appPackage);

        System.out.println("🎉 [Setup] Kurulum tamamlandı!");
    }


    @BeforeElement
    public void beforeElement() {
        System.out.println("➡️ Başlamadan önce: " + getCurrentElement().getName());
    }

    @AfterElement
    public void afterElement() {
        System.out.println("✅ Bitti: " + getCurrentElement().getName());
    }

    @Override
    public void e_ClickedAddIncomeButton() {
        new HomePage(driver).clickAddIncomeButton();

    }

    @Override
    public void e_UpdateBalance() {
       // new pages.HomePage(driver).
    }

    @Override
    public void e_ClickAddIncome() {

    }

    @Override
    public void v_IncomeSaved() {

    }

    @Override
    public void e_TypeIncomeTitle() {
        new HomePage(driver).typeIncomeTitleTextField();
    }

    @Override
    public void v_IncomeSheetOpened() {

    }

    @Override
    public void v_ExpenseSheetOpened() {

    }

    @Override
    public void e_CancelIncome() {
        new HomePage(driver).cancelAddIncome();
    }

    @Override
    public void e_AmountCleaned() {
        new HomePage(driver).checkAddIncomeSheet();
    }

    @Override
    public void e_BackToHome() {
        new HomePage(driver).checkHomeView();
    }

    @Override
    public void v_Home() {

    }

    @Override
    public void e_FillIncomeAmountField() {
        new HomePage(driver).fillIncomeAmount();
    }

    @Override
    public void e_ClickedAddExpenseButton() {
        HomePage home = new HomePage(driver);
        home.clickAddIncomeButton(); // income sheet açılmış olsun
        home.switchToExpense();      // sonra expense'e geçilsin
        home.switchToIncome();       // tekrar geri dön
    }


    @Override
    public void v_TagSelected() {

    }

    @Override
    public void v_CleanedAmountField() {

    }

    @Override
    public void v_IncomeAmountFieldFilled() {

    }

    @Override
    public void v_BalanceUpdated() {

    }

    @Override
    public void e_IncomeSaved() {
        new HomePage(driver).saveIncome();
    }

    @Override
    public void e_SelectIncomeType() {
        new HomePage(driver).selectIncomeTag();
    }

    @Override
    public void v_IncomeTitleWritten() {

    }
}
