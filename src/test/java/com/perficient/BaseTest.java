package com.perficient;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService appiumService;

    @BeforeClass
    public void configureAppium() {
        String appiumLogPath = "./appium.log";
        //String appiumJSPath = "C:\\Users\\sarkerm.rashid\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
        String appiumJSPath = "//Users//smhrash//node_modules//appium//build//lib//main.js";

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
            appiumService.start();

            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("JovanEmulator");
            //options.setChromedriverExecutable("C:\\Users\\sarkerm.rashid\\Documents\\chromedriver_win32");
            //options.setApp("C:\\Users\\sarkerm.rashid\\IdeaProjects\\appium-demo\\src\\test\\java\\com\\perficient\\rosources\\ApiDemos-debug.apk");
            //options.setApp("C:\\Users\\sarkerm.rashid\\IdeaProjects\\appium-demo\\src\\test\\java\\com\\perficient\\rosources\\General-Store.apk");
            options.setApp("/Users/smhrash/IdeaProjects/appium-demo/src/test/java/com/perficient/rosources/General-Store.apk");
            // Initialize AndroidDriver
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Your test logic goes here

        } catch (MalformedURLException e) {
            e.printStackTrace();

        }
    }

    public void longPressAction(WebElement webElement) {

        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId", ((RemoteWebElement)webElement).getId(), "duration", 2000));
    }

    public void swipeAction(WebElement webElement, String direction) {
        ((JavascriptExecutor)driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId", ((RemoteWebElement)webElement).getId(),"direction",direction,"percent", 0.75));
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
