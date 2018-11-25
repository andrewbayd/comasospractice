package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BasePage {
    private static final String ACCOUNT_LOGO_XPATH = "//button[@data-testid='accountIcon']";
    private static final String MY_ACCOUNT_LINK_XPATH = "//a[@data-testid='myaccount-link']";
    private static final String FAVORITES_BUTTON_XPATH = "//a[@data-testid='savedItemsIcon']";
    private static final String SIGN_IN_LINK_PATH = "//a[@data-testid='signin-link']";
    private static final String SEARCH_INPUT_XPATH = ".//*[@id='chrome-search']";
    private static final String SEARCH_BUTTON_XPATH = "//button[@data-testid='search-button-inline']";
    private static final String MEN_CATEGORY_BUTTON_XPATH = "//a[@data-testid='men-floor']";
    private static final String WOMEN_CATEGORY_BUTTON_XPATH = "//a[@data-testid='women-floor']";
    private static final String VIEW_BAG_BUTTON_XPATH = "//span[contains(text(),'View Bag')]";


    @FindBy(xpath = ACCOUNT_LOGO_XPATH)
    private WebElement accountLogo;

    @FindBy(xpath = MY_ACCOUNT_LINK_XPATH)
    private WebElement myAccountLink;

    @FindBy(xpath = FAVORITES_BUTTON_XPATH)
    private WebElement favoritesButton;

    @FindBy(xpath = SIGN_IN_LINK_PATH)
    private WebElement signInLink;

    @FindBy(xpath = SEARCH_INPUT_XPATH)
    private WebElement searchInput;

    @FindBy(xpath = SEARCH_BUTTON_XPATH)
    private WebElement searchButton;

    @FindBy(xpath = MEN_CATEGORY_BUTTON_XPATH)
    private WebElement menCategoryButton;

    @FindBy(xpath = WOMEN_CATEGORY_BUTTON_XPATH)
    private WebElement womenCategoryButton;

    @FindBy(xpath = VIEW_BAG_BUTTON_XPATH)
    private WebElement viewBagButton;

    public void openLoginPage() {
        moveCursorToElement(accountLogo);
        waitForElementAndClick(signInLink);
    }

    public void search(String searchWord) {
        waitForInputAndType(searchInput, searchWord);
        waitForElementAndClick(searchButton);
    }

    public void openMenCategoryMenu() {
        waitForElementAndClick(menCategoryButton);
    }

    public void openWomenCategoryMenu() {
        waitForElementAndClick(womenCategoryButton);
    }

    public void openFavoritesPage() {
        waitForElementAndClick(favoritesButton);
    }

    public void openCartPage() {
        waitForElementAndClick(viewBagButton);
    }

    public void openMyAccountPage() {
        moveCursorToElement(accountLogo);
        waitForElementAndClick(myAccountLink);
    }



}
