package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WomenNewInAccessoriesPage extends BasePage {
    private static final String SORT_DROPDOWN_XPATH = "//li[@data-dropdown-id='sort']";
    private static final String SORT_BY_PRICE_ASCENDING_OPTION = "//a[contains(@href,'products&sort=priceasc')]";
    private static final String PRODUCTS_PRICES_XPATH = "//span[@data-auto-id='productTilePrice']";

    @FindBy(xpath = SORT_DROPDOWN_XPATH)
    private WebElement sortDropdown;

    @FindBy(xpath = SORT_BY_PRICE_ASCENDING_OPTION)
    private WebElement sortByPriceAscOption;

    @FindBy(xpath = PRODUCTS_PRICES_XPATH)
    private List<WebElement> productsPrices;

    public void sortProductsByPriceAscending() throws InterruptedException {
        waitForElementAndClick(sortDropdown);
        waitForElementAndClick(sortByPriceAscOption);
        waitForSpinner();
    }

    public List<String> getProductsPrices() {
        return waitForSeveralElementsAndGetText(productsPrices);
    }

    public List<String> getProductsPricesSorted() {
        return waitForSeveralElementsAndGetTextSorted(productsPrices);
    }
}
