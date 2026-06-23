package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {
    private final By inventoryContainer = By.id("inventory_container");
    private final By cartBadge          = By.className("shopping_cart_badge");
    private final By cartIcon           = By.className("shopping_cart_link");
    private final By sortDropdown       = By.className("product_sort_container");
    private final By productNames       = By.className("inventory_item_name");

    public InventoryPage(WebDriver driver) { super(driver); }

    public boolean isDisplayed() { return isVisible(inventoryContainer); }

    public void addToCart(String productName) {
    String id = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
    click(By.id(id));
}

public void removeFromCart(String productName) {
    String id = "remove-" + productName.toLowerCase().replace(" ", "-");
    click(By.id(id));
}

    public int getCartCount() {
        return isVisible(cartBadge) ? Integer.parseInt(getText(cartBadge)) : 0;
    }

    public void openCart() { click(cartIcon); }

    public void sortBy(String option) {
        new Select(wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(sortDropdown))).selectByVisibleText(option);
    }

    public List<String> getProductNames() { return getAllTexts(productNames); }
}
