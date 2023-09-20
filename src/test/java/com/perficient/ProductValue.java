package com.perficient;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ProductValue extends BaseTest {
    @Test
    public void calculateProductsValue() {
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Tanjina Rashid");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"))"));

        driver.findElement(By.xpath("(//android.widget.TextView)[@text='Australia']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();


        driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(0).click();
        driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(1).click();

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
        List<WebElement> productPrice= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));


        int count = productPrice.size();
        double totalPrice = 0;
        for (int i = 0; i<count;i++) {
            String amount = productPrice.get(i).getText();
            double price = formattedAmount(amount.substring(1));

            totalPrice = totalPrice + price;
        }
        String displaySum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        double displayBill = formattedAmount(displaySum);
        Assert.assertEquals(displayBill, 280.97);

    }

}
