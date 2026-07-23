package com.example.AdvancedSearch.AssociateEntry;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.Setup.ScreenshotUtil;

public class BrandAgnostic {

    WebDriver driver;
    ScreenshotUtil sc;

    public BrandAgnostic(WebDriver driver, ScreenshotUtil sc) {
        this.driver = driver;
        this.sc = sc;
    }

    public void bagnostic() throws InterruptedException, IOException {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        sc.takeScreenshot();
        driver.findElement(By.xpath("//div[text()='Common Product Hierarchy']")).click();
        Thread.sleep(2000);
        // ;
        WebElement cba = driver.findElement(By.xpath("//div[text()='Common Brand Agnostic']"));
        js.executeScript("arguments[0].click();", cba);
        Thread.sleep(2000);

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", cba);
        Thread.sleep(1000);

        WebElement giant = driver.findElement(By.xpath("//div[contains(.,'Giant Food, GIANT/MARTIN')]"));
        js.executeScript("arguments[0].scrollIntoView({block:'start'});", giant);
        sc.takeScreenshot();
        Thread.sleep(1000);

        WebElement bottom = driver.findElement(By.xpath(
                "//div[contains(text(),'Tax Flags')]"));

        js.executeScript("arguments[0].scrollIntoView({block:'end'});", bottom);
        sc.takeScreenshot();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[text()='Giant Food, GIANT/MARTIN’S, Stop & Shop Brand Agnostic']")).click();
        Thread.sleep(2000);

        sc.takeScreenshot();
        driver.findElement(By.xpath("//div[text()='Food Lion and Hannaford Brand Agnostic']")).click();
        Thread.sleep(2000);

    }
}