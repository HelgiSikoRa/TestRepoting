package com.epam.lab.utils;

import com.epam.lab.utils.parsers.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverPool {

    private static ThreadLocal<WebDriver> webDriverPool = ThreadLocal.withInitial(() -> {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(new ConfigFileReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    });

    public static WebDriver getDriver() {
        return webDriverPool.get();
    }

    public static void removeDriver() {
        webDriverPool.get().quit();
        webDriverPool.remove();
    }
}