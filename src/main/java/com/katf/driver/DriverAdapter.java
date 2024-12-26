package com.katf.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class DriverAdapter implements IDriver{

    private final WebDriver driver;

    // Constructor for WebDriver
    public DriverAdapter(WebDriver webDriver) {
        this.driver = webDriver;
    }

    // Constructor for AppiumDriver

    @Override
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    @Override
    public void click(By by) {
        findElement(by).click();
    }

    @Override
    public void sendKeys(By by, String keys) {
        findElement(by).sendKeys(keys);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public void quit() {
        driver.quit();
    }
}
