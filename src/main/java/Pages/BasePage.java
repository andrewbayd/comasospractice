package Pages;

import Utils.Waiters;
import Utils.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static Utils.Waiters.MEDIUM_DELAY;
import static Utils.WebDriverFactory.driver;

public class BasePage extends PageFactory {
    private Waiters waiter = new Waiters();

    public BasePage() {
        initElements(WebDriverFactory.driver, this);
    }

    void moveCursorToElement(WebElement element) {
        Actions builder = new Actions(driver);
        builder.moveToElement(element).build().perform();
    }
    
    void clickOnRandomElementFromList(List<WebElement> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        WebElement randomElement = list.get(index);
        randomElement.click();
    }

    void waitForElementAndClick(WebElement element) {
        waiter.waitForElementIsVisible(element, MEDIUM_DELAY);
        element.click();
    }

    void waitForElement(WebElement element) {
        waiter.waitForElementIsVisible(element, MEDIUM_DELAY);
    }

    void waitForSeveralElementsAndClick(List<WebElement> elements) {
        waiter.waitForSeveralElementsAreVisible(elements, MEDIUM_DELAY);
        elements.stream().forEach(e->e.click());
    }

    String waitForElementAndGetText(WebElement element) {
        waiter.waitForElementIsVisible(element, MEDIUM_DELAY);
        return element.getText();
    }

    void waitForSeveralElements(List<WebElement> elements) {
        waiter.waitForSeveralElementsAreVisible(elements, MEDIUM_DELAY);
    }

    List<String> waitForSeveralElementsAndGetText(List<WebElement> elements) {
        waiter.waitForSeveralElementsAreVisible(elements, MEDIUM_DELAY);
        return elements.stream().map(e->e.getText()).collect(Collectors.toList());
    }

    List<String> waitForSeveralElementsAndGetTextSorted(List<WebElement> elements) {
        waiter.waitForSeveralElementsAreVisible(elements, MEDIUM_DELAY);
        return elements.stream().map(e->e.getText()).sorted().collect(Collectors.toList());
    }

    void waitForInputClearAndType(WebElement input, String text) {
        waiter.waitForElementIsVisible(input, MEDIUM_DELAY);
        input.clear();
        input.sendKeys(text);
    }

    void waitForInputAndType(WebElement input, String text) {
        waiter.waitForElementIsVisible(input, MEDIUM_DELAY);
        input.sendKeys(text);
    }

    void waitForSpinner() throws InterruptedException {
        Thread.sleep(5000);
    }

    boolean verifyThatElementIsDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    void selectFirstAvailableOption(List<WebElement> options) {
        for (int i = 1; i < options.size(); i++) {
            if (options.get(i).isEnabled()) {
                options.get(i).click();
                break;
            }
        }
    }
}
