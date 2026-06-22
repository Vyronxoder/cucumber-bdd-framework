package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CartPage;
import pages.CheckoutPage;
import utils.DriverManager;

public class CheckoutSteps {

    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @And("proceeds to checkout")
    public void proceedsToCheckout() {
        cartPage = new CartPage(DriverManager.getDriver());
        cartPage.clickCheckout();
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
    }

    @And("fills in first name {string}, last name {string}, and postal code {string}")
    public void fillsInCheckoutDetails(String firstName, String lastName, String postalCode) {
        checkoutPage.fillInfo(firstName, lastName, postalCode);
    }

    @And("clicks Finish")
    public void clicksFinish() {
        checkoutPage.finish();
    }

    @Then("the order confirmation message {string} should be displayed")
    public void orderConfirmationShouldBeDisplayed(String expectedMessage) {
        Assert.assertEquals(checkoutPage.getConfirmation(), expectedMessage,
                "Order confirmation message did not match");
    }

    @Then("a checkout error message should be displayed")
    public void checkoutErrorShouldBeDisplayed() {
        Assert.assertTrue(checkoutPage.isErrorVisible(),
                "Expected a validation error on empty checkout form");
    }
}
