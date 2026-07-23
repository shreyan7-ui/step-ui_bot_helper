package com.example.AdvancedSearch.AssociateEntry;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.Setup.ScreenshotUtil;

public class DataManagement {

    WebDriver driver;
    ScreenshotUtil sc;

    public DataManagement(WebDriver driver, ScreenshotUtil sc) {
        this.driver = driver;
        this.sc = sc;
    }

    public void DataManage() throws InterruptedException, IOException {

        // 🔹 Click Data Management tab
        driver.findElement(By.xpath("//span[text()='Data Management Info']")).click();
        Thread.sleep(3000);
        sc.takeScreenshot();

        List<WebElement> giantList = driver.findElements(By.xpath(
                "//a[contains(@class,'header')]//div[contains(.,'Giant Food')]"));

        for (WebElement el : giantList) {
            if (el.isDisplayed()) {

                // scroll to element
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView(true);", el);

                Thread.sleep(1000);

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", el);
                Thread.sleep(2000);

                sc.takeScreenshot();
                break;
            }
        }

        // 🔹 Scroll to Food Lion section
        List<WebElement> foodList = driver.findElements(By.xpath(
                "//a[contains(@class,'header')]//div[contains(.,'Food Lion')]"));

        for (WebElement el : foodList) {
            if (el.isDisplayed()) {

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block:'end'});", el);

                Thread.sleep(1000);

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", el);

                Thread.sleep(2000);
                break;
            }
        }
    }
}