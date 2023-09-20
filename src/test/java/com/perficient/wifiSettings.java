package com.perficient;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class wifiSettings extends BaseTest {


    @Test
    public void appiumTest() {
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals("WiFi settings", alertTitle);
        driver.findElement(By.id("android:id/edit")).sendKeys("Jovan");
        driver.findElement(By.id("android:id/button1")).click();
    }

}

