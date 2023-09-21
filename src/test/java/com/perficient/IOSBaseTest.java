package com.perficient;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class IOSBaseTest {

    public IOSDriver driver;
    public AppiumDriverLocalService appiumService;
    @BeforeClass
    public void configureAppium() {
        String appiumLogPath = "./appium.log";
        String appiumJSPath = "/Users/smhrash/node_modules/appium/build/lib/main.js";

        // Define the AppiumServiceBuilder with the desired configuration
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withAppiumJS(new File(appiumJSPath))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                .withLogFile(new File(appiumLogPath));


        appiumService = AppiumDriverLocalService.buildService(builder);

        try {
            // Start the Appium server
            appiumService. start();

            XCUITestOptions options = new XCUITestOptions();
            options.setDeviceName("iPhone 15 Pro Max");
            options.setApp("/Users/smhrash/Library/Developer/Xcode/DerivedData/UIKitCatalog-bqykmcgbjspneoftqexlwifdpgfq/Build/Products/Debug-iphonesimulator/UIKitCatalog.app");
            options.setPlatformVersion("17.0");
            options.setWdaLaunchTimeout(Duration.ofSeconds(20));

            // Initialize IOSDriver
            driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        } catch (MalformedURLException e) {
            e.printStackTrace();

        }
    }

    public double formattedAmount(String amount) {
        double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    @AfterClass
    public void tearDown() {
        // Stop the Appium server and quit the driver (ensure these actions are performed)
        driver.quit();
        appiumService.stop();
    }
}


