package com.katf.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.URL;
import java.time.Duration;


public class AppiumDriverUtil {

    public static AppiumDriver getDriver(String platform) throws Exception {


        if ("android".equalsIgnoreCase(platform)) {
            UiAutomator2Options androidOptions = new UiAutomator2Options()
                    .setDeviceName("Android Emulator")
                    .setPlatformVersion("11.0")
                    .setApp("/path/to/android/app.apk")
                    .setUdid("1234")
                    .setAutomationName("UiAutomator2")
                    .setNewCommandTimeout(Duration.ofSeconds(300))
                    .setNoReset(true);
            return new AndroidDriver(new URL(ConfigurationLoader.get("appium.server")),androidOptions);
        } else if ("ios".equalsIgnoreCase(platform)) {
            XCUITestOptions iosOptions = new XCUITestOptions()
                    .setDeviceName("iPhone Simulator")
                    .setPlatformVersion("14.5")
                    .setApp("/path/to/ios/app.app")
                    .setUdid("1234")
                    .setAutomationName("XCUITest")
                    .setNewCommandTimeout(Duration.ofSeconds(300))
                    .setNoReset(true);
            return new IOSDriver(new URL(ConfigurationLoader.get("appium.server")),iosOptions );
        } else {
            throw new Exception("Unsupported platform for Appium driver: " + platform);
        }


    }
}
