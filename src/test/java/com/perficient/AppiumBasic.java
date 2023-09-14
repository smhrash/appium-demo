package com.perficient;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumBasic {

    @Test
    public void appiumTest() throws MalformedURLException {
        String appiumJSPath = "C:\\Users\\sarkerm.rashid\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";

        // Define the AppiumServiceBuilder with the desired configuration
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withAppiumJS(new File(appiumJSPath))
                .withIPAddress("127.0.0.1")
                .usingPort(4723);

        // Start the Appium server
        AppiumDriverLocalService service = builder.build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("JovanEmulator");
        options.setApp("C:\\Users\\sarkerm.rashid\\IdeaProjects\\appium-demo\\src\\test\\java\\com\\perficient\\rosources\\ApiDemos-debug.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        driver.quit();
        service.stop();
    }

}
