package com.katf.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;

public class WebDriverUtil {
    public static WebDriver getDriver(String browser) throws Exception {
        WebDriver driver;
        switch (browser) {
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setAcceptInsecureCerts(true);
                if (ConfigurationLoader.get("chrome.headless").equals("true"))
                    chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
            }
            case "firefox" -> driver = new FirefoxDriver();
            case "safari" -> driver = new SafariDriver();
            case "remote" -> {
                ChromeOptions options = new ChromeOptions();
                driver = new RemoteWebDriver(new URL(ConfigurationLoader.get("remote.grid.url")), options);
            }
            default -> throw new IllegalArgumentException("Invalid browser name");
        }
        driver.manage().window().maximize();
        return driver;

    }
}
