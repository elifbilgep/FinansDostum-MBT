package webdriver;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServer {
    private static AppiumDriverLocalService service;

    public AppiumServiceBuilder appServiceBuilder(){
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withArgument(GeneralServerFlag.RELAXED_SECURITY);
        appiumServiceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        return appiumServiceBuilder;
    }

    public void startServer() {
        service = AppiumDriverLocalService.buildService(appServiceBuilder());
        try {
            service.start();

            // Wait a moment for the server to fully initialize
            Thread.sleep(2000);

            // Verify the server is actually running
            if (!service.isRunning()) {
                throw new RuntimeException("Appium server not running after start command");
            }

            System.out.println("Appium server started successfully on URL: " + service.getUrl());
        } catch (Exception e) {
            System.err.println("Failed to start Appium server: " + e.getMessage());
            throw new RuntimeException("Could not start Appium server", e);
        }
    }

    public void stopServer(){
        System.out.println("Stop Appium Server");
        service.stop();
    }
}
