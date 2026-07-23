package com.example.AdvancedSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StepPopup {

    WebDriver driver;

    public StepPopup(WebDriver driver) {
        this.driver = driver;
    }

    public void handlePopup() throws InterruptedException {
        driver.findElement(By.xpath("//label[contains(text(),\"Don't show this again\")]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span[text()='OK']")).click();
        Thread.sleep(2000);

    }

    public void goToAdvancedSearch() throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='Advanced Search']")).click();
        Thread.sleep(2000);

    }
}