package com.example.Setup;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class setup {
    public static WebDriver driver;

    public void ini_Driver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-software-rasterizer");
        options.addArguments("--force-device-scale-factor=1");
        options.addArguments("--high-dpi-support=1");
        options.addArguments("--disable-features=VizDisplayCompositor");

        driver = new ChromeDriver(options);

        ((JavascriptExecutor) setup.driver)
                .executeScript("document.body.style.zoom='80%'");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void tearDown() {
        // driver.quit();
    }
}
