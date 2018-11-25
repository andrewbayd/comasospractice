package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenCategoryMenu extends BasePage {
    private static final String NEW_IN_CATEGORY_BUTTON_XPATH = "//button[@data-id='029c47b3-2111-43e9-9138-0d00ecf0b3db']";
    private static final String NEW_IN_VIEW_ALL_LINK_XPATH = "//a[contains(@href,'cid=27110&nlid=mw|new+in|new+products')]";
    private static final String OUTLET_CATEGORY_BUTTON_XPATH = "//button[@data-id='c42067f4-5f16-440a-ab2f-5163739caf68']";
    private static final String OUTLET_VIEW_ALL_LINK_XPATH = "//a[contains(@href,'cid=27396&nlid=mw|outlet|shop+by+product')]";

    @FindBy(xpath = NEW_IN_CATEGORY_BUTTON_XPATH)
    private WebElement newInCategoryButton;

    @FindBy(xpath = NEW_IN_VIEW_ALL_LINK_XPATH)
    private WebElement newInViewAllLink;

    @FindBy(xpath = OUTLET_CATEGORY_BUTTON_XPATH)
    private WebElement outletCategoryButton;

    @FindBy(xpath = OUTLET_VIEW_ALL_LINK_XPATH)
    private WebElement outletViewAllLink;

    public void openMenNewInViewAllPage() {
        moveCursorToElement(newInCategoryButton);
        waitForElementAndClick(newInViewAllLink);
    }

    public void openOutletViewAllPage() {
        moveCursorToElement(outletCategoryButton);
        waitForElementAndClick(outletViewAllLink);
    }



}
