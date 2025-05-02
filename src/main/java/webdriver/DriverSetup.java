package webdriver;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverSetup {

    ReadProperties readProperties = new ReadProperties();
    String appiumServerIpAddress = readProperties.getProp("appiumServerIP");
    String appiumServerPort = readProperties.getProp("appiumServerPort");

    public IOSDriver getIOSDriver(String deviceName, String udid, String bundleId, boolean noReset) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "18.0");
        capabilities.setCapability("deviceName", "iPhone 16 Pro Max");
        capabilities.setCapability("udid","417A7383-3C5A-4CE8-A8CC-F2C1BA4C576E");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("app", "/Users/elifparlak/Library/Developer/Xcode/DerivedData/FinansDostu-dsxsswxfuouvdygxhbqduoxlybfk/Build/Products/Debug-iphonesimulator/FinansDostu.app");
        capabilities.setCapability("noReset", noReset);
        capabilities.setCapability("bundleId", "com.tunaarikaya.FinansDostu"); // 🔥 eksik olan bu

        IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        return driver;
    }
}
