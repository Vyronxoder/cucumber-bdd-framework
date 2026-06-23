package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InventoryPage extends BasePage {
    private final By inventoryContainer = By.id("inventory_container");
    private final By cartBadge          = By.className("shopping_cart_badge");
    private final By cartIcon           = By.className("shopping_cart_link");
    private final By sortDropdown       = By.className("product_sort_container");
    private final By productNames       = By.className("inventory_item_name");

    public InventoryPage(WebDriver driver) { super(driver); }

    public boolean isDisplayed() { return isVisible(inventoryContainer); }

    public void addToCart(String productName) {
        String formattedName = productName.toLowerCase().replace(" ", "-");
        String id = "add-to-cart-" + formattedName;
        click(By.id(id));
        
        // Wait for the button to change to "Remove" to confirm the action succeeded
        String removeId = "remove-" + formattedName;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(removeId)));
    }

    public void removeFromCart(String productName) {
        String formattedName = productName.toLowerCase().replace(" ", "-");
        String id = "remove-" + formattedName;
        click(By.id(id));
        
        // Wait for the button to change back to "Add to cart"
        String addId = "add-to-cart-" + formattedName;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(addId)));
    }

    public int getCartCount() {
        return isVisible(cartBadge) ? Integer.parseInt(getText(cartBadge)) : 0;
    }

    public void openCart() { 
        click(cartIcon); 
        // Wait for the browser to navigate to the cart page
        wait.until(ExpectedConditions.urlContains("cart"));
    }

    public void sortBy(String option) {
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown))).selectByVisibleText(option);
    }

    public List<String> getProductNames() { return getAllTexts(productNames); }
}
