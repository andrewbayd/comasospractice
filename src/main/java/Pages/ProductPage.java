package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends BasePage {
    private static final String ADD_TO_FAVORITES_BUTTON_XPATH = "//div[@id='product-save']//div[@class='save-button']";
    private static final String ADDED_TO_FAVORITES_BUTTON_XPATH = "//a[@class='save-button-link active animate']//span[@class='heartSecondary']";
    private static final String PRODUCT_NAME_XPATH = "//*[@id='aside-content']//h1";
    private static final String SIZES_DROPDOWN_XPATH = "//div[@id='product-size']//select[@data-id='sizeSelect']";
    private static final String SIZES_DROPDOWN_OPTIONS_XPATH = "//div[@id='product-size']//option";
    private static final String ADD_TO_CART_BUTTON_XPATH = "//div[@id='product-add']//a[@class='add-button']";

    @FindBy(xpath = PRODUCT_NAME_XPATH)
    private WebElement productName;

    @FindBy(xpath = SIZES_DROPDOWN_XPATH)
    private WebElement sizesDropdown;

    @FindBy(xpath = SIZES_DROPDOWN_OPTIONS_XPATH)
    private List<WebElement> sizesDropdownOptions;

    @FindBy(xpath = ADD_TO_CART_BUTTON_XPATH)
    private WebElement addToCartButton;

    @FindBy(xpath = ADD_TO_FAVORITES_BUTTON_XPATH)
    private WebElement addToFavoritesButton;

    @FindBy(xpath = ADDED_TO_FAVORITES_BUTTON_XPATH)
    private WebElement addedToFavoritesButton;

    public void addProductToFavorites() {
        waitForElementAndClick(addToFavoritesButton);
        waitForElement(addedToFavoritesButton);
    }

    public void addProductToCart() {
        waitForElementAndClick(addToCartButton);
    }

    public String getProductNameText() {
        return waitForElementAndGetText(productName);
    }

    public void selectFirstAvailableSizeIfNeed() {
        if (verifyThatElementIsDisplayed(sizesDropdown)) {
            waitForElementAndClick(sizesDropdown);
            selectFirstAvailableOption(sizesDropdownOptions);
        }
    }
}
