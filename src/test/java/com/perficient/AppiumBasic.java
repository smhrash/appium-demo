package com.perficient;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumBasic {

    @Test
    public void appiumTest() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel XL API 34");
        options.setApp("");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), null);
    }
}
