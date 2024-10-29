# ta_training-java-final-task
Final task of Test Automation Courses

# Swag Labs Login Automation Test

## Project Description

This project automates the testing of the login form on the Swag Labs website using Selenium WebDriver, JUnit, and Cucumber. The project includes three main test scenarios:

1. **Testing the login form with empty credentials**: Verifying that an error message appears when no credentials are entered.
2. **Testing the login form with a username but no password**: Verifying that an error message appears when a username is entered but no password is provided.
3. **Testing the login form with valid credentials**: Verifying that when the correct username and password are entered, the user is redirected to the main page titled "Swag Labs".

## Technologies Used

- **Testing Framework**: Selenium WebDriver
- **Build Tool**: Maven
- **Browsers**: Firefox, Edge
- **Locators**: XPath
- **Testing Framework**: JUnit
- **Test Parameterization**: Data Provider
- **Logging**: SLF4J
- **Design Patterns**: Singleton, Factory Method, Abstract Factory
- **Testing Approach**: BDD (Behavior-Driven Development)
- **Assertions**: AssertJ

### Main Classes

- **`SwagLabsLoginPage`**: Class responsible for interacting with the login form.
- **`SwagLabsHomePage`**: Class responsible for interacting with the home page.
- **`WebDriverFactory`**: Class for creating WebDriver instances based on the browser type.
- **`TestData`**: Class for storing test data (usernames, passwords, error messages).
- **`LoginPageTest`**: Class containing tests to verify login functionality.

1. Clone the repository:

   ```bash
   git clone https://github.com/Roman-Svyshch/ta_training-java-final-task.git
     cd swag-labs-login-test

2. Install Maven dependencies:   
    mvn clean install
3  Run the tests:
    mvn test
