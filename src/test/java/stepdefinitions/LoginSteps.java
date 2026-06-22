package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.InventoryPage;
import pages.LoginPage;
import utils.DriverManager;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("the user is on the Sauce Demo login page")
    public void theUserIsOnTheLoginPage() {
        loginPage = new LoginPage(DriverManager.getDriver());
        // Driver is already on the base URL from Hooks.setUp()
    }

    @When("the user enters username {string} and password {string}")
    public void theUserEntersCredentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("clicks the login button")
    public void clicksLoginButton() {
        loginPage.clickLogin();
    }

    @Then("the user should be redirected to the inventory page")
    public void userIsRedirectedToInventoryPage() {
        InventoryPage inventoryPage = new InventoryPage(DriverManager.getDriver());
        Assert.assertTrue(inventoryPage.isDisplayed(),
                "Expected inventory page to be visible after valid login");
    }

    @Then("an error message containing {string} should be displayed")
    public void errorMessageShouldBeDisplayed(String expectedFragment) {
        Assert.assertTrue(loginPage.isErrorVisible(),
                "Expected error message to be displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedFragment),
                String.format("Expected error to contain '%s' but got: '%s'",
                        expectedFragment, loginPage.getErrorMessage()));
    }
}
