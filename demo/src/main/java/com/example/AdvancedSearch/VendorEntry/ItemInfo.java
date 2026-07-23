package com.example.AdvancedSearch.VendorEntry;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.example.Setup.ScreenshotUtil;

public class ItemInfo {
    WebDriver driver;
    ScreenshotUtil sc;
    Actions actions;

     public ItemInfo(WebDriver driver, ScreenshotUtil sc) {
        this.driver = driver;
        this.sc = sc;
    }

    public void iteminfo() throws InterruptedException, IOException {

        sc.takeScreenshot();
        driver.findElement(By.xpath("//div[text()='Product and Image Information']")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//div[text()='Item Details']")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//div[text()='Scan Unit Information']")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//div[text()='Conversion Information']")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//span[text()='Vendor Support Info']")).click();
        Thread.sleep(3000);


    }
}
