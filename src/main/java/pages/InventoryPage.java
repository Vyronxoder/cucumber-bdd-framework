package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage {

    private WebDriver driver;

    // ==========================================
    // 1. Constructor
    // ==========================================
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // ==========================================
    // 2. Locators (Update these to match your app)
    // ==========================================
    private By cartIconLocator = By.cssSelector(".shopping_cart_link"); // Example: standard cart icon class
    private By cartBadgeLocator = By.cssSelector(".shopping_cart_badge"); // Example: the red number on the cart

    // ==========================================
    // 3. The Methods Missing from your Build
    // ==========================================

    /**
     * Clicks the shopping cart icon to navigate to the cart page.
     */
    public void openCart() {
        driver.findElement(cartIconLocator).click();
    }

    /**
     * Removes an item from the cart dynamically based on the item's name.
     * * @param itemName The string name of the product to remove.
     */
    public void removeFromCart(String itemName) {
        // This is an example XPath that finds the "Remove" button associated with a specific item name.
        // You MUST update this structure if your website's HTML is different.
        String dynamicXPath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button[contains(text(), 'Remove')]", itemName);
        
        driver.findElement(By.xpath(dynamicXPath)).click();
    }

    /**
     * Reads the number on the cart badge and returns it as an integer.
     * * @return The number of items currently in the cart.
     */
    public int getCartCount() {
        try {
            // Attempt to find the badge and parse the number
            WebElement badge = driver.findElement(cartBadgeLocator);
            return Integer.parseInt(badge.getText());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // If the badge is not present in the DOM, it usually means the cart is empty
            return 0; 
        }
    }
}
