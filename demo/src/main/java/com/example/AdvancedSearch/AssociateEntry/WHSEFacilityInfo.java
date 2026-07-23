package com.example.AdvancedSearch.AssociateEntry;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.Setup.ScreenshotUtil;

public class WHSEFacilityInfo {
    WebDriver driver;
    ScreenshotUtil sc;

    public WHSEFacilityInfo(WebDriver driver, ScreenshotUtil sc) {
        this.driver = driver;
        this.sc = sc;
    }

    public void WHSE_Facility() throws InterruptedException, IOException {
        driver.findElement(By.xpath("//span[text()='WHSE & Facility Info']")).click();
        Thread.sleep(3000);
        sc.takeScreenshot();

        List<WebElement> giantList = driver.findElements(By.xpath(
                "//a[contains(@class,'header')]//div[contains(.,'Giant Food')]"));

        for (WebElement el : giantList) {
            if (el.isDisplayed()) {

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", el);

                sc.takeScreenshot();
                Thread.sleep(2000);
                break;
            }
        }

    }
}
