package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
    private static final String MY_DETAILS_TAB_XPATH = "//a[@href='/my-account/my-details']";
    private static final String FIRST_NAME_INPUT_XPATH = "//input[@name='firstName']";
    private static final String SAVE_CHANGES_BUTTON_XPATH = "//button[@type='submit']";
    private static final String USER_NAME_XPATH = "//div[@class='_2xe2-VXTX3werNloJ8nBA_']/span";

    @FindBy(xpath = MY_DETAILS_TAB_XPATH)
    private WebElement myDetailsTab;

    @FindBy(xpath = FIRST_NAME_INPUT_XPATH)
    private WebElement firstNameInput;

    @FindBy(xpath = SAVE_CHANGES_BUTTON_XPATH)
    private WebElement saveChangesButton;

    @FindBy(xpath = USER_NAME_XPATH)
    private WebElement userName;

    public void openMyDetailsTab() {
        waitForElementAndClick(myDetailsTab);
    }

    public void changeUserName(String name) {
        waitForInputClearAndType(firstNameInput, name);
    }

    public void saveMyDetailsChanges() throws InterruptedException {
        waitForElementAndClick(saveChangesButton);
        Thread.sleep(5000);
    }

    public String getUserNameText() {
        return waitForElementAndGetText(userName);
    }
}
