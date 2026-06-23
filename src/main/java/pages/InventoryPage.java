package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
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
        By addBtn = By.id("add-to-cart-" + formattedName);
        By removeBtn = By.id("remove-" + formattedName);

        // Wait until the button is present and clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addBtn));

        // Attempt JS Click
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        
        // Wait specifically for the 'remove' button to become visible
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(removeBtn));
        } catch (TimeoutException e) {
            // Debugging: If it fails, check if the badge appeared instead
            if (isDisplayed(cartBadge)) {
                System.out.println("Add to cart successful (Badge updated), but button state didn't change.");
            } else {
                throw e; // Re-throw if even the badge didn't update
            }
        }
    }

    private boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ... rest of your methods ...
}
