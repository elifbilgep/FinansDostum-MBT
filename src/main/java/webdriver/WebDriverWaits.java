package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.asserts.SoftAssert;


import java.time.Duration;

public class WebDriverWaits {

    WebDriver driver;

    public WebDriverWaits(WebDriver driver){
        this.driver = driver;
    }

    public void waitForElementToBeClickable(By locator, Duration timeOutInSeconds){
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementTextToBe(By locator, Duration timeOutInSeconds, String elementText){
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.attributeToBe(locator, "text", elementText));
    }

    public boolean waitForVisibilityOfElement(By locator, Duration timeOutInSeconds){
        try {

            new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public boolean isElementVisible(By locator, int timeoutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void checkElementVisible(By locator, int timeout) {
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertTrue(
                isElementVisible(locator, timeout),
                locator.toString() + " element visibility is."
        );
        softAssertion.assertAll(); // Optional: tekli testte hemen patlamasını istiyorsan
    }

    public void waitForInvisibilityOfElement(WebElement locator, Duration timeOutInSeconds){
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.invisibilityOf(locator));
    }

    public void waitForInvisibilityOfElement(By locator, Duration timeOutInSeconds){
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void fastWait(By locator, int timeOutInSeconds){
        long timeout = System.currentTimeMillis() + (timeOutInSeconds * 1000);
        boolean timedOut = true;
        while(System.currentTimeMillis() < timeout){
            try {
                driver.findElement(locator);
                timedOut = false;
                break;
            }catch (Exception ignore){}
        }
        if(timedOut){
            // throw new ElementNotVisibleException("Locator Not Found");
        }
    }

    public void fastWaitForObjectToVanish(By locator, int timeOutInSeconds){
        long timeout = System.currentTimeMillis() + (timeOutInSeconds * 1000);

        driver.findElement(locator);

        while(System.currentTimeMillis() < timeout){
            try {
                driver.findElement(locator);
            }catch (Exception ignore){
                break;
            }
        }

        if (System.currentTimeMillis() > timeout) {
            throw new TimeoutException("Element not visible within timeout");
        }
    }

    public void fluentWait(By locator, int timeOutInSeconds, int pollingInMilliSeconds, Runnable method){

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeOutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingInMilliSeconds))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(TimeoutException.class);

        WebElement foo = wait.until((driver) -> {
            method.run();
            return driver.findElement(locator);
        });
    }

    public void fluentWait(By locator, int timeOutInSeconds, int pollingInMilliSeconds){

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeOutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingInMilliSeconds))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(TimeoutException.class);

        WebElement foo = wait.until((driver) -> {

            return driver.findElement(locator);
        });
    }


}
