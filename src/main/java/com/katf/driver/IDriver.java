package com.katf.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface IDriver {
    WebElement findElement(By by);
    void click(By by);
    void sendKeys(By by, String keys);
    String getCurrentUrl();
    void quit();
}
