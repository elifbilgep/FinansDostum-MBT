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
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("app", "/Users/elifparlak/Library/Developer/Xcode/DerivedData/FinansDostu-dsxsswxfuouvdygxhbqduoxlybfk/Build/Products/Debug-iphonesimulator/FinansDostu.app");
        capabilities.setCapability("noReset", noReset);
        String url = "http://" + appiumServerIpAddress + ":" + appiumServerPort + "/wd/hub";

        return new IOSDriver(new URL(url), capabilities);
    }
}
