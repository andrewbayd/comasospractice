package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    private static final String EMAIL_INPUT_XPATH = "//input[@id='EmailAddress']";
    private static final String PASSWORD_INPUT_XPATH = "//input[@id='Password']";
    private static final String SIGN_IN_BUTTON_XPATH = "//input[@id='signin']";

    @FindBy(xpath = EMAIL_INPUT_XPATH)
    private WebElement emailInput;

    @FindBy(xpath = PASSWORD_INPUT_XPATH)
    private WebElement passwordInput;

    @FindBy(xpath = SIGN_IN_BUTTON_XPATH)
    private WebElement signInButton;

    public void loginToAccount (String email, String password) {
        waitForInputAndType(emailInput, email);
        waitForInputAndType(passwordInput, password);
        waitForElementAndClick(signInButton);
    }

}
