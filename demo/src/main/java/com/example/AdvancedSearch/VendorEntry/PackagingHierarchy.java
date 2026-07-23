package com.example.AdvancedSearch.VendorEntry;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.example.Setup.ScreenshotUtil;

public class PackagingHierarchy {
    WebDriver driver;
    ScreenshotUtil sc;


    public PackagingHierarchy(WebDriver driver) {
        this.driver = driver;
        this.sc = sc;
    }

    public void Package() throws InterruptedException, IOException {

        Actions actions = new Actions(driver);
        Thread.sleep(5000);
        driver.findElement(By.xpath("(//*[@id='PropertySheetTable']//i[@title='Flip table'])[2]")).click();
        Thread.sleep(4000);

        driver.findElement(
                By.xpath("//*[@id='PropertySheetTable']//tr/th/div[1]//span[@class='stb-NodeDetails-unselected']"))
                .click();

        Thread.sleep(5000);

        driver.findElement(By.xpath("(//*[@id='PropertySheetTable']//tbody/tr/td[1]//div//span)[2]")).click();
        Thread.sleep(3000);

        for (int i = 0; i < 14; i++) {
            actions.sendKeys(Keys.TAB).perform();
            Thread.sleep(1000);
        }
        Thread.sleep(3000);

        driver.findElement(By.xpath("//span[text()='Item Info']")).click();
        Thread.sleep(3000);

    }
}
