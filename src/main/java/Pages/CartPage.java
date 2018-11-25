package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {
    private static final String PRODUCTS_IN_CART_NAMES_XPATH = "//a[contains(@data-bind,'attr: { href: getLink() }, text: item.name')]";
    private static final String ALL_DELETE_BUTTONS_ON_PAGE_XPATH = "//button[@class='bag-item-remove']";
    private static final String EMPTY_CART_TITLE_XPATH = "//h2[@class='empty-bag-title']";

    @FindBy(xpath = PRODUCTS_IN_CART_NAMES_XPATH)
    private List<WebElement> productsInCartNames;

    @FindBy(xpath = ALL_DELETE_BUTTONS_ON_PAGE_XPATH)
    private List<WebElement> allDeleteButtonsOnPage;

    @FindBy(xpath = EMPTY_CART_TITLE_XPATH)
    private WebElement emptyCartTitle;

    public List<String> getProductsNamesText() {
        return waitForSeveralElementsAndGetText(productsInCartNames);
    }

    public void clearCartPage() {
        waitForSeveralElementsAndClick(allDeleteButtonsOnPage);
    }

    public boolean isPageEmpty() {
        waitForElement(emptyCartTitle);
        return verifyThatElementIsDisplayed(emptyCartTitle);
    }

}
