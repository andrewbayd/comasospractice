package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MenNewInAllPage extends BasePage {
    private static final String PRODUCTS_ON_PAGE_XPATH = "//article[@data-auto-id='productTile']";

    @FindBy(xpath = PRODUCTS_ON_PAGE_XPATH)
    private List<WebElement> productsOnPage;

    public void openRandomProductPage() {
        clickOnRandomElementFromList(productsOnPage);
    }
}
