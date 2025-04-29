package utils;
import fd.base.IOSBasePage;

import io.appium.java_client.ios.IOSDriver;
import org.graphwalker.core.machine.ExecutionContext;
import webdriver.DriverSetup;


import java.io.IOException;

public class IOSBaseTestClass extends ExecutionContext {

    protected static IOSBasePage basePage;
    public static IOSDriver driver;

    DriverSetup driverSetup = new DriverSetup();

    public void initiateDriver(String deviceName, String udid, String bundleId, Boolean noReset) throws IOException {

        driver = driverSetup.getIOSDriver(deviceName, udid, bundleId, noReset);
        basePage = new IOSBasePage();
        basePage.setWebDriver(driver);
    }
}
