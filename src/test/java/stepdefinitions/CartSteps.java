package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverManager;

public class CartSteps {

    private InventoryPage inventoryPage;
    private CartPage cartPage;

    /** Shared login step reused across cart and checkout feature files */
    @Given("the user is logged in as {string}")
    public void theUserIsLoggedInAs(String username) {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.enterUsername(username);
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        inventoryPage = new InventoryPage(DriverManager.getDriver());
        Assert.assertTrue(inventoryPage.isDisplayed(), "Login failed — inventory page not shown");
    }

    @When("the user adds {string} to the cart")
    public void theUserAddsProductToCart(String productName) {
        if (inventoryPage == null) inventoryPage = new InventoryPage(DriverManager.getDriver());
        inventoryPage.addToCart(productName);
    }

    @Given("the user has {string} in the cart")
    public void theUserHasProductInCart(String productName) {
        if (inventoryPage == null) inventoryPage = new InventoryPage(DriverManager.getDriver());
        inventoryPage.addToCart(productName);
    }

    @When("the user removes {string} from the cart")
    public void theUserRemovesProductFromCart(String productName) {
        inventoryPage.removeFromCart(productName);
    }

    @When("the user opens the cart")
    public void theUserOpensTheCart() {
        if (inventoryPage == null) inventoryPage = new InventoryPage(DriverManager.getDriver());
        inventoryPage.openCart();
        cartPage = new CartPage(DriverManager.getDriver());
    }

    @Then("the cart badge should display {string}")
    public void cartBadgeShouldDisplay(String expectedCount) {
        Assert.assertEquals(String.valueOf(inventoryPage.getCartCount()), expectedCount,
                "Cart badge count mismatch");
    }

    @Then("the cart badge should not be visible")
    public void cartBadgeShouldNotBeVisible() {
        Assert.assertEquals(inventoryPage.getCartCount(), 0,
                "Expected cart badge to be gone after removing all items");
    }

    @Then("the cart should contain {string}")
    public void cartShouldContain(String productName) {
        Assert.assertTrue(cartPage.getItemNames().contains(productName),
                "Expected cart to contain: " + productName);
    }
}
