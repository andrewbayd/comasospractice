package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WomenCategoryMenu extends BasePage {
    private static final String NEW_IN_CATEGORY_BUTTON_XPATH = "//button[@data-id='1020946c-8949-4e9c-9719-43435002bcd4']";
    private static final String NEW_IN_ACCESSORIES_LINK_XPATH = "//a[contains(@href,'cid=27109&nlid=ww|new+in|new+products')]";
   
    @FindBy(xpath = NEW_IN_CATEGORY_BUTTON_XPATH)
    private WebElement newInCategoryButton;

    @FindBy(xpath = NEW_IN_ACCESSORIES_LINK_XPATH)
    private WebElement newInAccessoriesLink;

    public void openNewInAccessoriesPage() {
        moveCursorToElement(newInCategoryButton);
        waitForElementAndClick(newInAccessoriesLink);
    }
}
