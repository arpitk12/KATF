package com.katf.driver;

import com.katf.utils.AppiumDriverUtil;
import com.katf.utils.ConfigurationLoader;
import com.katf.utils.WebDriverUtil;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

public class DriverFactory {
    public static IDriver createDriver() throws Exception {
        String platform= ConfigurationLoader.get("test.platform").toLowerCase();
        if ("web".equalsIgnoreCase(platform)) {
            WebDriver driver=WebDriverUtil.getDriver(ConfigurationLoader.get("web.browser"));
            return new DriverAdapter(driver);
        } else if ("android".equalsIgnoreCase(platform)) {
            AppiumDriver driver= AppiumDriverUtil.getDriver(platform);
            return new DriverAdapter(driver);
        } else if ("ios".equalsIgnoreCase(platform)) {
            AppiumDriver driver= AppiumDriverUtil.getDriver(platform);
            return new DriverAdapter(driver);
        } else {
            throw new Exception("Unsupported platform: " + platform);
        }
    }
}
