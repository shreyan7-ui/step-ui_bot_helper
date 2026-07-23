package com.example.AdvancedSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class AdvancedSearch {

    WebDriver driver;
    Actions action;

    public AdvancedSearch(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
    }

    public void search(String UPC) throws InterruptedException {

        driver.findElement(By.xpath("//div[@title='Name, ID and Attribute value search']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@placeholder='Enter search text']")).click();
        Thread.sleep(2000);
        

        action.sendKeys(UPC).perform();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span[text()='Search']/ancestor::button")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span[text()='Object Type']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//div[text()='None']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span[@title='Sell Item']//input")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span[text()='Apply filter']//parent::div")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//span[@title='"+UPC+"']")).click();
        Thread.sleep(3000);

    }
}