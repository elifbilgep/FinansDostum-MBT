package utilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.WebDriverWaits;

import java.time.Duration;

public class UIActionHelper {

    IOSDriver driver;
    WebDriverWaits waits;

    public UIActionHelper(IOSDriver driver) {
        this.driver = driver;
        this.waits = new WebDriverWaits(driver);
    }

    public void tapCenter() {
        int height = driver.manage().window().getSize().getHeight() / 2;
        int width = driver.manage().window().getSize().getWidth() / 2;
        new TouchAction<>(driver)
                .tap(PointOption.point(width, height))
                .perform();
    }

    public void scrollDown() {
        int scrollStart = (int) (driver.manage().window().getSize().getHeight() * 0.7);
        int scrollEnd = (int) (driver.manage().window().getSize().getHeight() * 0.3);
        int x = driver.manage().window().getSize().getWidth() / 2;

        new TouchAction<>(driver)
                .press(PointOption.point(x, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .moveTo(PointOption.point(x, scrollEnd))
                .release()
                .perform();
    }

    public void scrollUp() {
        int scrollStart = (int) (driver.manage().window().getSize().getHeight() * 0.3);
        int scrollEnd = (int) (driver.manage().window().getSize().getHeight() * 0.7);
        int x = driver.manage().window().getSize().getWidth() / 2;

        new TouchAction<>(driver)
                .press(PointOption.point(x, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .moveTo(PointOption.point(x, scrollEnd))
                .release()
                .perform();
    }

    public void scrollToFindElementByText(String text) {
        for (int i = 0; i < 5; i++) {
            try {
                By locator = By.xpath("//*[contains(@label,'" + text + "') or contains(@value,'" + text + "')]");
                if (driver.findElement(locator).isDisplayed()) {
                    break;
                }
            } catch (Exception e) {
                scrollDown();
            }
        }
    }

    public boolean isElementScrollable(By element) {
        try {
            waits.waitForElementToBeClickable(element, Duration.ofSeconds(5));
            return Boolean.parseBoolean(driver.findElement(element).getAttribute("scrollable"));
        } catch (Exception e) {
            return false;
        }
    }

    public By findElementText(String text) {
        return By.xpath("//*[contains(@label,'" + text + "') or contains(@value,'" + text + "')]");
    }

    public By findElementTextExact(String text) {
        return By.xpath("//*[@label='" + text + "' or @value='" + text + "']");
    }
}
