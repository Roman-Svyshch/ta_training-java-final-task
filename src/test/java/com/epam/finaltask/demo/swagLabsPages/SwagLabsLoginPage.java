package com.epam.finaltask.demo.swagLabsPages;

import io.cucumber.java.an.E;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


public class SwagLabsLoginPage {

    private final WebDriver driver ;
    private final FluentWait<WebDriver> wait;
    private final JavascriptExecutor javascriptExecutor;
    private static final Logger log = LoggerFactory.getLogger(SwagLabsLoginPage.class);



    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement usernameElement;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordElement;
    @FindBy(xpath = "//h3[@data-test='error']")

    private WebElement errorUserNameMessage;
    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButtonElement;

    public SwagLabsLoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        this.javascriptExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver,this);
    }

    @Given("User is on the SwagLabs login page Url : https://www.saucedemo.com/")
    public void navigateToLoginPage(){
        log.info("Navigating to the login page");
        try {
            driver.get("https://www.saucedemo.com/");
        }catch (Exception e){
            log.error("the link \"https://www.saucedemo.com/\" cannot be open , check link.");
        }
    }

    @Then("Input username value into field username")
    public void inputUsernameValue(String username){
        log.info("Input username :{}",username);
        try {
            wait.until(ExpectedConditions.visibilityOf(usernameElement)).sendKeys(username);
        }catch (Exception e){
            log.error("Something went wrong with the field username");
        }
    }

    @Then("Input username value into field username")
    public void inputPasswordValue(String password){
        log.info("Input password :{}",password);
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordElement)).sendKeys(password);
        }catch (Exception e){
            log.error("Something went wrong with the field username");
        }
    }

    @When("Username & Password is entered press login button")
    public void clickLoginButton(){
        log.info("clicking on the login button");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButtonElement)).click();
        }catch (Exception e){
            log.error("The button ' Login ' cannot be clickable .");
        }
    }

    @Then("If Username not entered the error message will appear")
    public String getErrorMessageText(){
        log.info("Return the error message if username was not entered in a field");
      return wait.until(ExpectedConditions.visibilityOf(errorUserNameMessage)).getText();
    }


    @When("The field username will be cleared")
    public void clearUsernameField() {
        log.info("Cleaning the username field for reason error check");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(usernameElement));
            usernameElement.click();
            usernameElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE); // очищення поля
            log.info("Username field cleared successfully");
        } catch (Exception e) {
            log.error("Error while clearing the username field: {}", e.getMessage());
            throw e;
        }
    }

    @When("When you need to clear field password")
    public void clearPasswordField() {
        log.info("Cleaning the password field for reason error check");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(passwordElement));
            passwordElement.click();
            passwordElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE); // очищення поля
            log.info("Password field cleared successfully");
        } catch (Exception e) {
            log.error("Error while clearing the password field: {}", e.getMessage());
            throw e;
        }
    }




}
