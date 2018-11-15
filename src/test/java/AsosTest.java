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
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertTrue;


public class AsosTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        final String url = "https://www.asos.com/";
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

    @Test
    public void searchTest() {
        //Verify that search result equals search query
        String searchWord = "jeans";

        driver.findElement(By.xpath(".//*[@id='chrome-search']")).sendKeys(searchWord);
        driver.findElement(By.xpath("//button[@data-testid='search-button-inline']")).click();
        String searchResult = driver.findElement(By.xpath("//p[@class='NvorkQI']")).getText();
        assertTrue(searchResult.contains(searchWord));
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

        List<WebElement> favorites = driver.findElements(By.xpath("//a[contains(@data-bind,'tem.title')]"));

        //Verify that our selected product is in favorites page
        boolean isProductExist = false;

        for (WebElement element: favorites) {
            if (element.getText().equals(selectedProductName)) {
                isProductExist = true;
                break;
            }
        }

        assertTrue(isProductExist);
    }

    @AfterClass
    public void closeUp() {
        driver.quit();
    }
}

