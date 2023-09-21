package com.perficient;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IOSBasic extends IOSBaseTest{

    @Test
    public void iOSBasicTest() {

        //Xpath, IOS, classPath, className, iOSClassChain, iOSPredicateString, accessibilityId, id
        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label='Text Entry'`]")).click();
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeChain")).sendKeys("Hello! Appium");
        driver.findElement(AppiumBy.accessibilityId("OK")).click();

        driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value == 'Confirm / Cancel'")).click();
        String textMessage = driver.findElement(AppiumBy.iOSNsPredicateString("value BEGINSWITH[c] 'A message'")).getText();
        Assert.assertTrue(textMessage.startsWith("A message"));
        driver.findElement(AppiumBy.accessibilityId("Confirm")).click();

    }
}
