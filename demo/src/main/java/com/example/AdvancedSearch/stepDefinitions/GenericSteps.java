package com.example.AdvancedSearch.stepDefinitions;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;

public class GenericSteps {
    WebDriver driver;

    @Given("I navigate to {string}")
    public void navigate(String url) {
        driver.get(url);
    }
}
