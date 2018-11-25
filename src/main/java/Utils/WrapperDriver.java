package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WrapperDriver {
    private static final String CHROME_DRIVER_PATH = "/Users/andriimac/Documents/QAAutomationCourse/comasospractice/src/main/resources/chromedriver";

    private static WebDriver getDriver(){
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        return new ChromeDriver();
    }

    public static WebDriver driver = getDriver();
}
