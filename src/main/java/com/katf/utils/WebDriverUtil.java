package com.katf.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverUtil {
    public static WebDriver getDriver(String browser){
        WebDriver driver=null;
        switch (browser){
            case "chrome":
                ChromeOptions chromeOptions=new ChromeOptions();
                chromeOptions.setAcceptInsecureCerts(true);
                if(ConfigurationLoader.get("headless").equals("true"))
                    chromeOptions.addArguments("--headless=new");
                driver=new ChromeDriver(chromeOptions);
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name");

        }
        driver.manage().window().maximize();
        return driver;

    }
}
