import Utils.Navigator;
import Pages.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Utils.Constants.*;
import static org.assertj.core.api.Assertions.*;
import static org.testng.Assert.assertTrue;


public class AsosTest {
    private Navigator navigator = new Navigator();
    private Header header = new Header();
    private LoginPage loginPage = new LoginPage();
    private MyAccountPage myAccountPage = new MyAccountPage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private MenCategoryMenu menCategoryMenu = new MenCategoryMenu();
    private MenNewInAllPage menNewInAllPage = new MenNewInAllPage();
    private ProductPage productPage = new ProductPage();
    private FavoritesPage favoritesPage = new FavoritesPage();
    private MenOutletAllPage menOutletAllPage = new MenOutletAllPage();
    private CartPage cartPage = new CartPage();
    private WomenCategoryMenu womenCategoryMenu = new WomenCategoryMenu();
    private WomenNewInAccessoriesPage womenNewInAccessoriesPage = new WomenNewInAccessoriesPage();


    @BeforeClass
    public void setUp() {
        //Open app and login to account
        navigator.openApp();
        header.openLoginPage();
        loginPage.loginToAccount(USER_EMAIL, USER_PASSWORD);
    }

    @BeforeMethod
    public void navigateToMainPage () {
        //Navigate to home page
        navigator.navigateToHomePage();
    }

    @Test
    public void searchTest() {
        //Verify that search result equals search query
        header.search(SEARCH_WORD);
        assertThat(searchResultsPage.getSearchResultText()).contains(SEARCH_WORD);
    }

    @Test
    public void addToFavoritesTest() {
        //Add random product to favorites and verify that product was added
        String selectedProductName;

        header.openMenCategoryMenu();
        menCategoryMenu.openMenNewInViewAllPage();
        menNewInAllPage.openRandomProductPage();
        selectedProductName = productPage.getProductNameText();
        productPage.addProductToFavorites();
        header.openFavoritesPage();
        assertThat(favoritesPage.getProductsNamesText()).contains(selectedProductName);
        favoritesPage.clearFavoritesPage();
        assertTrue(favoritesPage.isPageEmpty());
    }

    @Test
    public void shoppingCartTest() {
        //Add random product to cart and verify that product was added
        String selectedProductName;

        header.openMenCategoryMenu();
        menCategoryMenu.openOutletViewAllPage();
        menOutletAllPage.openRandomProductPage();
        selectedProductName = productPage.getProductNameText();
        productPage.selectFirstAvailableSizeIfNeed();
        productPage.addProductToCart();
        header.openCartPage();
        assertThat(cartPage.getProductsNamesText()).contains(selectedProductName);
        cartPage.clearCartPage();
        assertTrue(cartPage.isPageEmpty());
    }

    @Test
    public void priceSortTest() throws InterruptedException {
        //Sort products on page by prices ascending and compare list of prices to sorted list of prices
        header.openWomenCategoryMenu();
        womenCategoryMenu.openNewInAccessoriesPage();
        womenNewInAccessoriesPage.sortProductsByPriceAscending();
        assertThat(womenNewInAccessoriesPage.getProductsPrices())
                .isEqualTo(womenNewInAccessoriesPage.getProductsPricesSorted());
    }

    @Test
    public void renameUserTest () throws InterruptedException {
        //Rename user and verify that username was changed
        header.openMyAccountPage();
        myAccountPage.openMyDetailsTab();
        myAccountPage.changeUserName(NEW_USER_NAME);
        myAccountPage.saveMyDetailsChanges();
        assertThat(myAccountPage.getUserNameText()).contains(NEW_USER_NAME);
    }

    @AfterClass
    public void closeUp() throws InterruptedException {
        //Rename user to default and close browser
        navigator.navigateToHomePage();
        header.openMyAccountPage();
        myAccountPage.openMyDetailsTab();
        myAccountPage.changeUserName(DEFAULT_USER_NAME);
        myAccountPage.saveMyDetailsChanges();
        navigator.closeBrowser();
    }
}

