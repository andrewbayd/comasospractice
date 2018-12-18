package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends BasePage {
    private static final String SEARCH_RESULT_XPATH = "//p[@class='NvorkQI']";

    @FindBy(xpath = SEARCH_RESULT_XPATH)
    private WebElement searchResult;

    public String getSearchResultText () {
        return waitForElementAndGetText(searchResult);
    }

}
