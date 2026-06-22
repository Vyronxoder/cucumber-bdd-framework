package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class CartPage extends BasePage {
    private final By cartItems     = By.className("inventory_item_name");
    private final By checkoutBtn   = By.id("checkout");
    private final By continueBtn   = By.id("continue-shopping");

    public CartPage(WebDriver driver) { super(driver); }

    public List<String> getItemNames()   { return getAllTexts(cartItems); }
    public void clickCheckout()          { click(checkoutBtn); }
    public void continueShopping()       { click(continueBtn); }
}
