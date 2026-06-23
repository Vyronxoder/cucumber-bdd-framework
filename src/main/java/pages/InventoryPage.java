package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        By addBtn = By.id("add-to-cart-" + formattedName);
        By removeBtn = By.id("remove-" + formattedName);

        // Wait for the button to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(addBtn));

        // Use JavascriptExecutor to ensure the click registers even if elements overlap
        WebElement button = driver.findElement(addBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        
        // Wait for the UI to update to "Remove" button
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeBtn));
    }

    public void removeFromCart(String productName) {
        String formattedName = productName.toLowerCase().replace(" ", "-");
        By removeBtn = By.id("remove-" + formattedName);
        By addBtn = By.id("add-to-cart-" + formattedName);
        
        wait.until(ExpectedConditions.elementToBeClickable(removeBtn));
        WebElement button = driver.findElement(removeBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(addBtn));
    }

    public int getCartCount() {
        return isVisible(cartBadge) ? Integer.parseInt(getText(cartBadge)) : 0;
    }

    public void openCart() { 
        click(cartIcon); 
        wait.until(ExpectedConditions.urlContains("cart"));
    }

    public void sortBy(String option) {
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown))).selectByVisibleText(option);
    }

    public List<String> getProductNames() { return getAllTexts(productNames); }
}
