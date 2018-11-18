import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.testng.Assert.assertTrue;


public class AsosTest {

    private WebDriver driver;
    private final String url = "https://www.asos.com/";

    @BeforeClass
    public void setUp() {
        final String path = "/Users/andriimac/Documents/QAAutomationCourse/comasospractice/src/main/resources/chromedriver";

        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.get(url);
        driver.findElement(By.xpath("//button[@data-testid='accountIcon']")).click();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='signin-link']")));
        driver.findElement(By.xpath("//a[@data-testid='signin-link']")).click();
        driver.findElement(By.xpath("//input[@id='EmailAddress']"))
                .sendKeys("qaautomationtestuser@gmail.com");
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Thisistest");
        driver.findElement(By.xpath("//input[@id='signin']")).click();
    }

    @BeforeMethod
    public void navigateToMainPage () {
        driver.get(url);
    }

    @Test
    public void searchTest() {
        //Verify that search result equals search query
        String searchWord = "jeans";

        driver.findElement(By.xpath(".//*[@id='chrome-search']")).sendKeys(searchWord);
        driver.findElement(By.xpath("//button[@data-testid='search-button-inline']")).click();
        String searchResult = driver.findElement(By.xpath("//p[@class='NvorkQI']")).getText();
        assertThat(searchResult).contains(searchWord);
    }

    @Test
    public void addToFavoritesTest() {
        //Open New In directory
        driver.findElement(By.xpath("//a[@data-testid='men-floor']")).click();

        Actions builder = new Actions(driver);
        builder.moveToElement(driver
                .findElement(By.xpath("//button[@data-id='029c47b3-2111-43e9-9138-0d00ecf0b3db']"))).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(@href,'cid=27110&nlid=mw|new+in|new+products')]")));
        driver.findElement(By.xpath
                ("//a[contains(@href,'cid=27110&nlid=mw|new+in|new+products')]")).click();

        //Add all products on page to list, select random item and open this item's page
        List<WebElement> products = driver.findElements(By.xpath("//article[@data-auto-id='productTile']"));
        Random random = new Random();
        int index = random.nextInt(products.size());
        WebElement randomProduct = products.get(index);
        randomProduct.click();

        //Save selected product's title to a string variable
        String selectedProductName = driver.findElement(By.xpath("//*[@id='aside-content']//h1")).getText();

        //Add product to the favorites
        driver.findElement(By.xpath("//div[@id='product-save']//div[@class='save-button']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[@class='save-button-link active animate']//span[@class='heartSecondary']")));

        //Navigate to favorites page and add all product titles to list
        driver.findElement(By.xpath("//a[@data-testid='savedItemsIcon']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@data-test-selector='savedItemsHeader']")));

        //Verify that added item is present on favorites page
        List<String> favorites = driver.findElements(By.xpath("//a[contains(@data-bind,'tem.title')]"))
                .stream().map(e->e.getText()).collect(Collectors.toList());
        assertThat(favorites).contains(selectedProductName);

        //Clear favorites page
        driver.findElements(By.xpath("//button[@title='Delete this item']")).stream().forEach(e->e.click());

        //Verify that favorites page is empty
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='saved-items-no-items-header']")));
        assertTrue(driver.findElement(By.xpath("//h2[@class='saved-items-no-items-header']")).isDisplayed());
    }

    @Test
    public void shoppingCartTest() {
        //Open Outlet directory
        driver.findElement(By.xpath("//a[@data-testid='men-floor']")).click();

        Actions builder = new Actions(driver);
        builder.moveToElement(driver
                .findElement(By.xpath("//button[@data-id='c42067f4-5f16-440a-ab2f-5163739caf68']"))).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(@href,'cid=27396&nlid=mw|outlet|shop+by+product')]")));
        driver.findElement(By.xpath
                ("//a[contains(@href,'cid=27396&nlid=mw|outlet|shop+by+product')]")).click();

        //Add all products on page to list, select random item and open this item's page
        List<WebElement> products = driver.findElements(By.xpath("//article[@data-auto-id='productTile']"));
        Random random = new Random();
        int index = random.nextInt(products.size());
        WebElement randomProduct = products.get(index);
        randomProduct.click();

        //Save selected product's title to a string variable
        String selectedProductName = driver.findElement(By.xpath("//*[@id='aside-content']//h1")).getText();

        //Select first available size if need
        if (driver.findElement(By.xpath("//div[@id='product-size']//select[@data-id='sizeSelect']")).isDisplayed()) {

            driver.findElement(By.xpath("//div[@id='product-size']//select[@data-id='sizeSelect']")).click();

            List<WebElement> sizes = driver.findElements(By.xpath("//div[@id='product-size']//option"));

            for (int i = 1; i < sizes.size(); i++) {
                if (sizes.get(i).isEnabled()) {
                    sizes.get(i).click();
                    break;
                }
            }
        }

        // Add product to cart
        driver.findElement(By.xpath("//div[@id='product-add']//a[@class='add-button']")).click();

        //Go to cart
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'View Bag')]")));
        driver.findElement(By.xpath("//span[contains(text(),'View Bag')]")).click();

        //Verify that selected product is in cart
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath("//a[contains(@data-bind,'attr: { href: getLink() }, text: item.name')]")));

        List<String> productsInCartNames = driver.findElements
                (By.xpath("//a[contains(@data-bind,'attr: { href: getLink() }, text: item.name')]"))
                .stream().map(e->e.getText()).collect(Collectors.toList());

        assertThat(productsInCartNames).contains(selectedProductName);

        //Remove product from cart
        driver.findElements(By.xpath("//button[@class='bag-item-remove']")).stream().forEach(e->e.click());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='empty-bag-title']")));

        //Verify that cart is empty
        assertTrue(driver.findElement(By.xpath("//h2[@class='empty-bag-title']")).isDisplayed());
    }

    @Test
    public void priceSortTest() throws InterruptedException {
        driver.findElement(By.xpath("//a[@data-testid='women-floor']")).click();

        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement
                (By.xpath("//button[@data-id='1020946c-8949-4e9c-9719-43435002bcd4']"))).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(@href,'cid=27109&nlid=ww|new+in|new+products')]")));
        driver.findElement(By.xpath("//a[contains(@href,'cid=27109&nlid=ww|new+in|new+products')]")).click();
        driver.findElement(By.xpath("//li[@data-dropdown-id='sort']")).click();
        driver.findElement(By.xpath("//a[contains(@href,'products&sort=priceasc')]")).click();

        Thread.sleep(1000);

        List<String> pricesByAscending = driver.findElements(By.xpath("//span[@data-auto-id='productTilePrice']"))
                .stream().map(e->e.getText()).collect(Collectors.toList());
        List<String> pricesByAscendingSorted = driver.findElements(By.xpath("//span[@data-auto-id='productTilePrice']"))
                .stream().map(e->e.getText()).sorted().collect(Collectors.toList());

        assertThat(pricesByAscending).isEqualTo(pricesByAscendingSorted);
    }

    @Test
    public void renameUserTest () throws InterruptedException {
        String newUserName = "NewName";

        //Go to user's account page
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.xpath("//button[@data-testid='accountIcon']"))).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='myaccount-link']")));
        driver.findElement(By.xpath("//a[@data-testid='myaccount-link']")).click();

        //Open account settings tab
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/my-account/my-details']")));
        driver.findElement(By.xpath("//a[@href='/my-account/my-details']")).click();

        //Change username
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstName']")));
        driver.findElement(By.xpath("//input[@name='firstName']")).clear();
        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(newUserName);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Verify that username is changed
        Thread.sleep(5000);
        assertThat(driver.findElement(By.xpath("//div[@class='_2xe2-VXTX3werNloJ8nBA_']/span")).getText())
                .contains(newUserName);
    }

    @AfterClass
    public void closeUp() {
        String defaultUserName = "Test";

        driver.get(url);
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.xpath("//button[@data-testid='accountIcon']"))).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='myaccount-link']")));
        driver.findElement(By.xpath("//a[@data-testid='myaccount-link']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/my-account/my-details']")));
        driver.findElement(By.xpath("//a[@href='/my-account/my-details']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstName']")));
        driver.findElement(By.xpath("//input[@name='firstName']")).clear();
        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(defaultUserName);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
}

