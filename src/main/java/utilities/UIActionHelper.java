package utilities;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import webdriver.WebDriverWaits;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class UIActionHelper {

    IOSDriver driver;
    WebDriverWaits waits;

    public UIActionHelper(IOSDriver driver) {
        this.driver = driver;
        this.waits = new WebDriverWaits(driver);
        System.out.println("UIActionHelper oluşturuldu. Driver: " + (driver != null ? "mevcut" : "null"));
    }

    public void tapCenter() {
        int height = driver.manage().window().getSize().getHeight() / 2;
        int width = driver.manage().window().getSize().getWidth() / 2;

        Map<String, Object> params = new HashMap<>();
        params.put("x", width);
        params.put("y", height);
        params.put("duration", 0.1);

        driver.executeScript("mobile: tap", params);
        System.out.println("Ekran ortasına dokunuldu: " + width + "x" + height);
    }

    public void scrollDown() {
        try {
            System.out.println("Aşağı kaydırma başlatılıyor...");

            Map<String, Object> params = new HashMap<>();
            params.put("direction", "down");
            driver.executeScript("mobile: scroll", params);

            System.out.println("Aşağı kaydırma tamamlandı.");
        } catch (Exception e) {
            System.out.println("Aşağı kaydırma sırasında hata: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void scrollUp() {
        try {
            System.out.println("Yukarı kaydırma başlatılıyor...");

            Map<String, Object> params = new HashMap<>();
            params.put("direction", "up");
            driver.executeScript("mobile: scroll", params);

            System.out.println("Yukarı kaydırma tamamlandı.");
        } catch (Exception e) {
            System.out.println("Yukarı kaydırma sırasında hata: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void scrollToFindElementByText(String text) {
        System.out.println("\"" + text + "\" metni aranıyor...");
        for (int i = 0; i < 5; i++) {
            try {
                By locator = By.xpath("//*[contains(@label,'" + text + "') or contains(@value,'" + text + "')]");
                if (driver.findElement(locator).isDisplayed()) {
                    System.out.println("\"" + text + "\" metni bulundu!");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Denemede bulunamadı, aşağı kaydırılıyor... Deneme: " + (i + 1));
                scrollDown();
            }
        }
        System.out.println("Arama tamamlandı.");
    }

    public void scrollToElement(WebElement element) {
        try {
            System.out.println("Elemente kaydırma başlatılıyor...");

            Map<String, Object> params = new HashMap<>();
            params.put("element", ((RemoteWebElement) element).getId());
            params.put("toVisible", true);
            driver.executeScript("mobile: scroll", params);

            System.out.println("Elemente kaydırma tamamlandı.");
        } catch (Exception e) {
            System.out.println("Elemente kaydırma sırasında hata: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean isElementScrollable(By element) {
        try {
            waits.waitForElementToBeClickable(element, Duration.ofSeconds(5));
            boolean scrollable = Boolean.parseBoolean(driver.findElement(element).getAttribute("scrollable"));
            System.out.println("Element kaydırılabilir mi: " + scrollable);
            return scrollable;
        } catch (Exception e) {
            System.out.println("Element kaydırılabilirliği kontrol edilirken hata: " + e.getMessage());
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