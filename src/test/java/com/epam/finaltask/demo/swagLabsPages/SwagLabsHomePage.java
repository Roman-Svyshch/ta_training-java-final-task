package com.epam.finaltask.demo.swagLabsPages;

import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLabsHomePage {

    private final WebDriver driver;

    public SwagLabsHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//div[@class='app_logo']")
    WebElement titleElement;

    @When("Home page is available check  if 'Swag Labs' title is present")
    public String checkTitle(){
      return titleElement.getText();
    }
}
