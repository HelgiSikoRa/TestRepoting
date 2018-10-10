package com.epam.lab;

import com.epam.lab.businessobjects.InboxPageBO;
import com.epam.lab.businessobjects.LoginPageBO;
import com.epam.lab.utils.dataproviders.ExcelDataProviderClass;
import com.epam.lab.utils.parsers.ConfigFileReader;
import com.epam.lab.utils.WebDriverPool;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import org.testng.annotations.*;

@Listeners(TestNGListener.class)
public class DeleteInboxMessageTest {
    private ConfigFileReader configFileReader;
    private static int initialInboxEmailQuantity;

    @BeforeClass
    public void setupDriver() {
        configFileReader = new ConfigFileReader();
        System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
    }

    @Test(dataProvider = "ExcelDataProvider", dataProviderClass = ExcelDataProviderClass.class)
    public void deleteInboxMessage(String login, String password) {
        WebDriver driver = WebDriverPool.getDriver();
        driver.get(configFileReader.getWebSiteUrl());
        LoginPageBO loginPageBO = new LoginPageBO(driver);
        loginPageBO.loginToGmail(login, password);
        InboxPageBO inboxPageBO = new InboxPageBO(driver);
        initialInboxEmailQuantity = inboxPageBO.checkInboxEmailQaontity();
        inboxPageBO.selectInboxEmail();
        inboxPageBO.deleteEmail();
        inboxPageBO.cancelDeletingEmail();
        Assert.assertEquals(initialInboxEmailQuantity, inboxPageBO.checkInboxEmailQaontity());
    }

    @AfterMethod
    public void tearDown() {
        WebDriverPool.removeDriver();
    }
}