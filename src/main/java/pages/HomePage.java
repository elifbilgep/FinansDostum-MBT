package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import utilities.UIActionHelper;
import webdriver.WebDriverWaits;

public class HomePage {

    IOSDriver driver;
    WebDriverWaits waits;
    UIActionHelper uiActionHelper;

    public HomePage(IOSDriver driver) {
        this.driver = driver;
        this.waits = new WebDriverWaits(driver);
        this.uiActionHelper = new UIActionHelper(driver);
    }

    private final By returnKeyboardKey = AppiumBy.accessibilityId("Return");
    private final By lastTransactionsText = AppiumBy.accessibilityId("Son İşlemler");
    private final By addIncomeOrExpenseButton = AppiumBy.accessibilityId("plus.circle.fill");
    private final By switchToAddExpenseButton = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Gider\"`]");
    private final By switchToAddIncomeButton = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Gelir\"`]");
    private final By noneAmountText =  AppiumBy.accessibilityId("0.00 ₺");
    private final By numberOneButton =  AppiumBy.accessibilityId("1");
    private final By numberTwoButton =  AppiumBy.accessibilityId("2");
    private final By numberThreeButton =  AppiumBy.accessibilityId("3");
    private final By numberFourButton =  AppiumBy.accessibilityId("4");
    private final By numberFiveButton =  AppiumBy.accessibilityId("5");
    private final By numberSixButton =  AppiumBy.accessibilityId("6");
    private final By numberSevenButton =  AppiumBy.accessibilityId("7");
    private final By numberEightButton =  AppiumBy.accessibilityId("8");
    private final By numberNineButton =  AppiumBy.accessibilityId("9");
    private final By numberZeroButton =  AppiumBy.accessibilityId("0");
    private final By pointButton =  AppiumBy.accessibilityId(".");
    private final By deleteButton =  AppiumBy.accessibilityId("⌫");
    private final By titleTextField =  AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == \"Başlık\"`]");
    private final By selectTagButton = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Diğer\"`]");
    private final By saveButton = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Kaydet\"`]");
    private final By cancelButton = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"İptal\"`]");
    private final By balanceText = AppiumBy.accessibilityId("balanceText");
    private final By salaryTag = AppiumBy.accessibilityId("Maaş");


    public void checkHomeView() {
        System.out.println("Check in Home Page");
        waits.checkElementVisible(lastTransactionsText, 10);
    }

    public void clickAddIncomeButton() {
        System.out.println("Click add income or expense button");
        waits.checkElementVisible(addIncomeOrExpenseButton, 10);
        driver.findElement(addIncomeOrExpenseButton).click();
    }

    public void switchToExpense() {
        System.out.println("Switch to expense");
        try {
            waits.checkElementVisible(switchToAddExpenseButton, 10);
            driver.findElement(switchToAddExpenseButton).click();
        } catch (Exception e) {
            System.out.println("❌ Gider butonu bulunamadı veya görünür değil: " + e.getMessage());
            System.out.println("📱 Page Source:\n" + driver.getPageSource());
            throw e;
        }
    }


    public void switchToIncome() {
        System.out.println("Switch to income");
        waits.checkElementVisible(switchToAddIncomeButton, 10);
        driver.findElement(switchToAddIncomeButton).click();
    }

    public void fillIncomeAmount() {
        System.out.println("Fill income amount");
        waits.checkElementVisible(noneAmountText, 10);
        driver.findElement(numberThreeButton).click();
        driver.findElement(numberFiveButton).click();
        driver.findElement(numberZeroButton).click();
        driver.findElement(numberZeroButton).click();
    }

    public void cleanAmount() {
        System.out.println("Clean amount");
        waits.checkElementVisible(deleteButton, 10);
        driver.findElement(deleteButton).click();
        driver.findElement(deleteButton).click();
        driver.findElement(deleteButton).click();
        driver.findElement(deleteButton).click();
    }

    public void typeIncomeTitleTextField() {
        System.out.println("Type income title");
        waits.checkElementVisible(titleTextField, 10);
        driver.findElement(titleTextField).click();
        driver.findElement(titleTextField).sendKeys("Sirket Odemesi");
        driver.findElement(returnKeyboardKey).click();
    }

    public void cancelAddIncome() {
        System.out.println("Cancel add income");
        waits.checkElementVisible(cancelButton, 10);
        driver.findElement(cancelButton).click();
    }

    public void selectIncomeTag() {
        System.out.println("Select income tag");
        waits.checkElementVisible(selectTagButton, 10);
        driver.findElement(selectTagButton).click();
        uiActionHelper.scrollDown();
        driver.findElement(salaryTag).click();
    }

    public void saveIncome() {
        System.out.println("Save income");
        waits.checkElementVisible(saveButton, 10);
        driver.findElement(saveButton).click();
        checkHomeView();
    }

    public void checkBalance() {
        System.out.println("Check balance");
        waits.checkElementVisible(balanceText, 10);

        String actualBalance = driver.findElement(balanceText).getText();
        String expectedBalance = "3.500 ₺";

        Assert.assertEquals(actualBalance, expectedBalance, "Balance text is incorrect!");
    }

    public void checkAddIncomeSheet() {
        System.out.println("Check income sheet");
        waits.checkElementVisible(numberOneButton, 10);
    }

}
