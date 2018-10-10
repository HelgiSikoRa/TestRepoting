package com.epam.lab;

import com.epam.lab.businessobjects.LoginPageBO;
import com.epam.lab.utils.WebDriverPool;
import com.epam.lab.utils.dataproviders.CsvDataProviderClass;
import com.epam.lab.utils.parsers.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class CheckInboxTitleTest {
    private ConfigFileReader configFileReader;

    @BeforeClass
    public void setupDriver() {
        configFileReader = new ConfigFileReader();
        System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
    }

    @Test(dataProvider = "csvDataProvider", dataProviderClass = CsvDataProviderClass.class)
    public void checkInboxTitle(String login, String password) {
        WebDriver driver = WebDriverPool.getDriver();
        driver.get(configFileReader.getWebSiteUrl());
        LoginPageBO loginPageBO = new LoginPageBO(driver);
        loginPageBO.loginToGmail(login, password);
        Assert.assertTrue(driver.getTitle().contains("Sent"));
    }

    @AfterMethod
    public void tearDown() {
        WebDriverPool.removeDriver();
    }
}