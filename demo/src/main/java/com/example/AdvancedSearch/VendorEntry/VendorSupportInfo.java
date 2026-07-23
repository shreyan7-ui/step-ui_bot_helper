package com.example.AdvancedSearch.VendorEntry;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.example.Setup.ScreenshotUtil;

public class VendorSupportInfo {
    WebDriver driver;
    ScreenshotUtil sc;
    Actions actions;

     public VendorSupportInfo(WebDriver driver, ScreenshotUtil sc) {
        this.driver = driver;
        this.sc = sc;
    }

    public void vendorSupport() throws InterruptedException, IOException {

        sc.takeScreenshot();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//span[text()='Additional GDSN Information']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()='FSMA Info']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()='Display Shipper Details']")).click();
        Thread.sleep(3000); 
        driver.findElement(By.xpath("//span[text()='Component Details']")).click();
        Thread.sleep(3000); 


    }
}
