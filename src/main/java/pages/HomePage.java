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

    private static double currentExpectedBalance = 0.0;
    private static final double INCOME_AMOUNT = 3500.0;

    public HomePage(IOSDriver driver) {
        this.driver = driver;
        this.waits = new WebDriverWaits(driver);
        this.uiActionHelper = new UIActionHelper(driver);
        System.out.println("HomePage created. Driver: " + (driver != null ? "available" : "null"));
    }

    private final By returnKeyboardKey = AppiumBy.accessibilityId("Return");
    private final By lastTransactionsText = AppiumBy.accessibilityId("Son İşlemler");
    private final By addIncomeOrExpenseButton = AppiumBy.accessibilityId("plus.circle.fill");
    private final By switchToAddExpenseButton = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Gider\"`]");
    private final By expenseSheetTitle = AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == \"Gider Ekle\"`]");
    private final By switchToAddIncomeButton = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Gelir\"`]");
    private final By incomeSheetTitle = AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == \"Gelir Ekle\"`]");
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
    private final By saveButton = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Kaydet\"`]");
    private final By cancelButton = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"İptal\"`]");
    private final By balanceText = AppiumBy.accessibilityId("balanceText");
    private final By salaryTag = AppiumBy.accessibilityId("Maaş");
    private final By date = AppiumBy.accessibilityId("Date Picker");

    private final By tagButton = AppiumBy.accessibilityId("tag");
    private final By noteField =  AppiumBy.accessibilityId("noteField");
    private final By titleTextField =  AppiumBy.accessibilityId("titleField");
    private final By amountText = AppiumBy.accessibilityId("amountText");

    private final String noteText = "Aylik maasim <3 olley";
    private final String incomeTitle = "Sirket Odemesi";
    private final String selectedTagText = "Maaş";

    // Helper method to format balance for display
    private String formatBalanceForHomeDisplay(double balance) {
        java.text.NumberFormat formatter = java.text.NumberFormat.getInstance(new java.util.Locale("tr", "TR"));
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        return formatter.format(balance) + " ₺";
    }

    // Helper method to format balance for sheet display
    private String formatBalanceForSheetDisplay(double balance) {
        return (int)balance + " ₺";
    }

    public void checkHomeView() {
        System.out.println("Checking Home Page");
        waits.checkElementVisible(lastTransactionsText, 10);
    }

    public void clickAddIncomeButton() {
        System.out.println("Clicking Add Button");
        waits.checkElementVisible(addIncomeOrExpenseButton, 10);
        driver.findElement(addIncomeOrExpenseButton).click();
    }

    public void switchToExpense() {
        System.out.println("Switching to expense sheet");
        try {
            waits.checkElementVisible(switchToAddExpenseButton, 10);
            driver.findElement(switchToAddExpenseButton).click();
        } catch (Exception e) {
            System.err.println("❌ Expense button cannot be clicked: " + e.getMessage());
            System.out.println("📱 Page Source:\n" + driver.getPageSource());
            throw e;
        }
    }

    public void checkCleanedAmountField() {
        System.out.println("Checking cleaned amount field");
        waits.checkElementVisible(amountText, 10);
        String fetchedAmountText = driver.findElement(amountText).getText();
        System.out.println("Received amount value: [" + fetchedAmountText + "]");
        Assert.assertEquals(fetchedAmountText, "0.00 ₺", "Amount field is not cleaned!");
        System.out.println("Amount field verified as clean");
    }

    public void switchToIncome() {
        System.out.println("Switching to income screen");
        waits.checkElementVisible(switchToAddIncomeButton, 10);
        driver.findElement(switchToAddIncomeButton).click();
    }

    public void fillIncomeAmount() {
        System.out.println("Filling income amount");
        waits.checkElementVisible(amountText, 10);
        driver.findElement(numberThreeButton).click();
        driver.findElement(numberFiveButton).click();
        driver.findElement(numberZeroButton).click();
        driver.findElement(numberZeroButton).click();
        System.out.println("Income amount entered as 3500");
    }

    public void cleanAmount() {
        System.out.println("Cleaning amount");
        waits.checkElementVisible(deleteButton, 10);
        driver.findElement(deleteButton).click();
        driver.findElement(deleteButton).click();
        driver.findElement(deleteButton).click();
        driver.findElement(deleteButton).click();
        System.out.println("Amount cleaned");
    }

    public void typeIncomeTitleTextField() {
        System.out.println("Typing income title");
        waits.checkElementVisible(titleTextField, 10);
        driver.findElement(titleTextField).click();
        driver.findElement(titleTextField).sendKeys(incomeTitle);
        driver.findElement(returnKeyboardKey).click();
        System.out.println("Income title written: [" + incomeTitle + "]");
    }

    public void checkIncomeTitleWritten() {
        System.out.println("Checking if income title is written");
        waits.checkElementVisible(titleTextField, 10);
        String fetchedTitleText = driver.findElement(titleTextField).getAttribute("value");
        System.out.println("Received title value: [" + fetchedTitleText + "]");
        Assert.assertEquals(incomeTitle, fetchedTitleText, "Note text is incorrect or not added!");
        System.out.println("Income title written correctly");
    }

    public void cancelAddIncome() {
        System.out.println("Canceling income addition");
        waits.checkElementVisible(cancelButton, 10);
        driver.findElement(cancelButton).click();
    }

    public void selectIncomeTag() {
        System.out.println("Selecting income type");
        waits.checkElementVisible(tagButton, 10);
        driver.findElement(tagButton).click();

        System.out.println("Scrolling down in tag list...");
        uiActionHelper.scrollDown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Error during wait: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Clicking salary tag...");
        driver.findElement(salaryTag).click();
        System.out.println("Salary tag selected.");
    }

    public void saveIncome() {
        System.out.println("Saving income");
        waits.checkElementVisible(saveButton, 10);
        driver.findElement(saveButton).click();

        currentExpectedBalance += INCOME_AMOUNT;
        System.out.println("Income saved, new expected balance: " + currentExpectedBalance);

        checkHomeView();
    }

    public void checkIncomeSaved() {
        System.out.println("Checking if income is saved");
        try {
            System.out.println("BALANCE TEXT ELEMENT PROPERTIES:");
            System.out.println("Is element visible?: " + driver.findElement(balanceText).isDisplayed());
            System.out.println("Element text: " + driver.findElement(balanceText).getText());
            System.out.println("Element value attribute: " + driver.findElement(balanceText).getAttribute("value"));
            System.out.println("Element label attribute: " + driver.findElement(balanceText).getAttribute("label"));

            String expectedBalanceHome = formatBalanceForHomeDisplay(currentExpectedBalance);

            String fetchedBalance = driver.findElement(balanceText).getAttribute("value");
            System.out.println("Received balance: [" + fetchedBalance + "]");
            System.out.println("Expected balance: [" + expectedBalanceHome + "]");

            Assert.assertEquals(fetchedBalance, expectedBalanceHome,
                    "Income is not updated correctly! Expected: " + expectedBalanceHome + ", but was: " + fetchedBalance);

            checkHomeView();
            System.out.println("Income successfully saved");
        } catch (AssertionError e) {
            System.err.println("❌ ASSERT ERROR in checkIncomeSaved method: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("❌ GENERAL ERROR in checkIncomeSaved method: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public void checkTagSelected() {
        System.out.println("Checking selected tag");
        String fetchedTagText = driver.findElement(tagButton).getAttribute("label");
        System.out.println("Received tag value: [" + fetchedTagText + "]");
        Assert.assertEquals(selectedTagText, fetchedTagText, "Tags do not match!");
        System.out.println("Tag successfully selected");
    }

    public void checkBalance() {
        System.out.println("Checking balance");
        waits.checkElementVisible(balanceText, 10);
        String actualBalance = driver.findElement(balanceText).getText();

        String expectedBalance = formatBalanceForSheetDisplay(currentExpectedBalance);

        System.out.println("Received balance: [" + actualBalance + "]");
        System.out.println("Expected balance: [" + expectedBalance + "]");

        Assert.assertEquals(actualBalance, expectedBalance, "Balance text is incorrect!");
        System.out.println("Balance is correct");
    }

    public void checkAddIncomeSheet() {
        System.out.println("Checking add income sheet");
        waits.checkElementVisible(incomeSheetTitle, 10);
        System.out.println("Add income sheet displayed");
    }

    public void checkAddExpenseSheet() {
        System.out.println("Checking add expense sheet");
        waits.checkElementVisible(expenseSheetTitle, 10);
        System.out.println("Add expense sheet displayed");
    }

    public void selectDate() {
        System.out.println("Selecting date");
        waits.checkElementVisible(date, 10);
    }

    public void addNote() {
        System.out.println("Adding note");
        waits.checkElementVisible(noteField, 10);
        driver.findElement(noteField).click();
        driver.findElement(noteField).sendKeys(noteText);
        driver.findElement(returnKeyboardKey).click();
        System.out.println("Note added: [" + noteText + "]");
    }

    public void checkNoteAdded() {
        System.out.println("Checking if note is added");
        waits.checkElementVisible(noteField, 10);
        String fetchedNoteText = driver.findElement(noteField).getAttribute("value");
        System.out.println("Received note value: [" + fetchedNoteText + "]");
        Assert.assertEquals(noteText, fetchedNoteText, "Note text is incorrect or not added!");
        System.out.println("Note successfully added");
    }

    public void checkIncomeAmountFilled() {
        System.out.println("Checking if income amount field is filled");
        waits.checkElementVisible(amountText, 10);
        String fetchedAmount = driver.findElement(amountText).getAttribute("value");
        System.out.println("Received amount value: [" + fetchedAmount + "]");

        Assert.assertTrue(fetchedAmount.contains("3500 ₺"),
                "Income amount is not filled correctly! Expected to contain 3500 ₺, but was: " + fetchedAmount);
        System.out.println("Income amount field filled correctly: " + fetchedAmount);
    }
}