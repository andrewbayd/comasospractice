import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;


public class AsosTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        final String url = "https://www.asos.com/";
        final String path = "/Users/andriimac/Documents/QAAutomationCourse/comasospractice/src/main/resources/chromedriver";

        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
    }

    @Test
    public void successLoginTest() {
        //Verify that user can login with existing credentials
        driver.findElement(By.xpath("//button[@data-testid='accountIcon']")).click();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='signin-link']")));

        driver.findElement(By.xpath("//a[@data-testid='signin-link']")).click();
        driver.findElement(By.xpath("//input[@id='EmailAddress']"))
                .sendKeys("qaautomationtestuser@gmail.com");
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Thisistest");
        driver.findElement(By.xpath("//input[@id='signin']")).click();
        driver.findElement(By.xpath("//button[@data-testid='accountIcon']")).click();
        assertTrue(driver.findElement(By.xpath("//a[@data-testid='signout-link']")).isDisplayed());
//        assertEquals(driver.findElement(By.xpath("//button[@data-testid='accountIcon']"))
//                .getAttribute("icon"), "accountFilled");
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

    @AfterClass
    public void closeUp() {
        driver.quit();
    }
}

