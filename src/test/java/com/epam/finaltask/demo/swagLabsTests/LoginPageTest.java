package com.epam.finaltask.demo.swagLabsTests;

import com.epam.finaltask.demo.data.TestData;
import com.epam.finaltask.demo.driverFactory.WebDriverFactory;
import com.epam.finaltask.demo.swagLabsPages.SwagLabsHomePage;
import com.epam.finaltask.demo.swagLabsPages.SwagLabsLoginPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class LoginPageTest {
    private WebDriver driver;
    private SwagLabsLoginPage swagLabsLoginPage;
    private SwagLabsHomePage homePage;
    private TestData testData;
    private static final Logger log = LoggerFactory.getLogger(SwagLabsLoginPage.class);

    @BeforeEach
    void setUp() {
        log.info("Setting up WebDriver and initializing test data");
        driver = WebDriverFactory.getDriver("chrome");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        swagLabsLoginPage = new SwagLabsLoginPage(driver, wait);
        homePage = new SwagLabsHomePage(driver);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            testData = objectMapper.readValue(new File("src/test/resources/testData.json"),TestData.class);
        }catch (IOException e){
            log.error("Failed to load test data", e);
            Assertions.fail("Test data loading failed");
        }
    }


    @Test
    public void testLoginWithValidUsernameAndValidPassword(){
        swagLabsLoginPage.navigateToLoginPage();
        swagLabsLoginPage.inputUsernameValue(testData.validCredentials.username);
        swagLabsLoginPage.inputPasswordValue(testData.validCredentials.password);
        swagLabsLoginPage.clickLoginButton();
    }

    @Test
    public void testLoginWithoutUsernameAndPassword(){
        String expectedErrorMessage = testData.errorMessages.usernameRequired;

        swagLabsLoginPage.navigateToLoginPage();
        swagLabsLoginPage.inputUsernameValue(testData.validCredentials.username);
        swagLabsLoginPage.clearUsernameField();
        swagLabsLoginPage.inputPasswordValue(testData.validCredentials.password);
        swagLabsLoginPage.clearPasswordField();
        swagLabsLoginPage.clickLoginButton();

        String actualErrorMessage = swagLabsLoginPage.getErrorMessageText();

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testLoginWithoutPassword(){
        String expectedErrorMessage = testData.errorMessages.passwordRequired;

        swagLabsLoginPage.navigateToLoginPage();
        swagLabsLoginPage.inputUsernameValue(testData.validCredentials.username);
        swagLabsLoginPage.inputPasswordValue(testData.validCredentials.password);
        swagLabsLoginPage.clearPasswordField();
        swagLabsLoginPage.clickLoginButton();

        String actualErrorMessage = swagLabsLoginPage.getErrorMessageText();

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);

    }
    @Test
    public void testLoginWithValidPasswordAndUsername(){
        String expectedTitle = testData.expectedTitle.title;

        swagLabsLoginPage.navigateToLoginPage();
        swagLabsLoginPage.inputUsernameValue(testData.validCredentials.username);
        swagLabsLoginPage.inputPasswordValue(testData.validCredentials.password);
        swagLabsLoginPage.clickLoginButton();

        String actualTitle = homePage.checkTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);

        driver.quit();
    }


}
