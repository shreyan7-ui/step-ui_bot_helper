package com.example.Setup;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    WebDriver driver;
    private int step = 1;

    public ScreenshotUtil(WebDriver driver) {
        this.driver = driver;
    }

    // 🔹 Basic screenshot
    public void takeScreenshot() throws IOException {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        File dest = new File("D:\\adusa ss\\" + step + ".png");

        FileUtils.copyFile(src, dest);
        step++;
    }
}