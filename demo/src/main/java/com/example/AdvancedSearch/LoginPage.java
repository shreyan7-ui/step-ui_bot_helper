package com.example.AdvancedSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openURLAssociate() {
        driver.get("https://adusa-pmdm-sit.mdm.stibosystems.com/webui/AssociatePortal/?kc_idp_hint=");
    }
    public void openURLSupplier() {
        driver.get("https://adusa-pmdm-sit.mdm.stibosystems.com/webui/SupplierItemPortal/?kc_idp_hint=");
    }

    public void enterUsername(String user) {
        driver.findElement(By.id("username")).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(By.id("password")).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(By.name("login")).click();
    }

    public void login(String user, String pass) {
        
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }
}

// driver.get("https://adusa-pmdm-sit.mdm.stibosystems.com/webui/AssociatePortal/?kc_idp_hint=");