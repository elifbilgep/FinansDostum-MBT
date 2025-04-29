package fd.base;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;

public class IOSBasePage {

    protected static IOSDriver driver;

    public void setWebDriver(WebDriver driver) {
        IOSBasePage.driver = (IOSDriver) driver;
    }
}
