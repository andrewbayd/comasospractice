package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static Utils.WrapperDriver.driver;

public class SearchResultsPage extends BasePage {
    private static final String SEARCH_RESULT_XPATH = "//p[@class='NvorkQI']";

    @FindBy(xpath = SEARCH_RESULT_XPATH)
    private WebElement searchResult;

    public String getSearchResultText () {
        return waitForElementAndGetText(searchResult);
    }

}
