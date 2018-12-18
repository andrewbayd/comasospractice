package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    private static final String CHROME_DRIVER_PATH = "/Users/andriimac/Documents/QAAutomationCourse/comasospractice/src/main/resources/chromedriver";

    private static WebDriver getDriver(String name) {
        if (name.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
            return new ChromeDriver();
        } else if (name.equalsIgnoreCase("firefox")) return new FirefoxDriver();
        else {
            System.out.println("Browser doesn't exist");
            return null;
        }
    }

    public static WebDriver driver = getDriver("chrome");
}
