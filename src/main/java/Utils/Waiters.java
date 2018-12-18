package Utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static Utils.WebDriverFactory.driver;

public class Waiters {
    public static final int SHORT_DELAY = 5;
    public static final int MEDIUM_DELAY = 15;
    public static final int LONG_DELAY = 30;

    public void waitForElementIsVisible(WebElement element, int delay) {
        WebDriverWait wait = new WebDriverWait(driver, delay);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForSeveralElementsAreVisible(List<WebElement> elements, int delay) {
        WebDriverWait wait = new WebDriverWait(driver, delay);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

}
