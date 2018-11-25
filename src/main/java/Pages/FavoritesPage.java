package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FavoritesPage extends BasePage {
    private static final String NO_ITEMS_HEADER = "//h2[@class='saved-items-no-items-header']";
    private static final String ALL_ITEMS_ON_PAGE_XPATH = "//a[contains(@data-bind,'tem.title')]";
    private static final String ALL_DELETE_BUTTONS_ON_PAGE = "//button[@title='Delete this item']";

    @FindBy(xpath = NO_ITEMS_HEADER)
    private WebElement noItemsHeader;

    @FindBy(xpath = ALL_ITEMS_ON_PAGE_XPATH)
    private List<WebElement> allItemsOnPage;

    @FindBy(xpath = ALL_DELETE_BUTTONS_ON_PAGE)
    private List<WebElement> allDeleteButtonsOnPage;

    public List<String> getProductsNamesText() {
        return waitForSeveralElementsAndGetText(allItemsOnPage);
    }

    public void clearFavoritesPage() {
        waitForSeveralElementsAndClick(allDeleteButtonsOnPage);
    }

    public boolean isPageEmpty() {
        waitForElement(noItemsHeader);
        return verifyThatElementIsDisplayed(noItemsHeader);
    }
}
