package Utils;

import static Utils.Constants.SITE_URL;
import static Utils.WebDriverFactory.driver;

public class Navigator {

    public void openApp () {
        driver.get(SITE_URL);
        System.out.println("App was opened");
    }

    public void navigateToHomePage () {
        driver.navigate().to(SITE_URL);
    }

    public void closeBrowser() {
        driver.quit();
    }

}
