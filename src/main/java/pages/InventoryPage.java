package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

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
    private By cartIconLocator = By.cssSelector(".shopping_cart_link"); 
    private By cartBadgeLocator = By.cssSelector(".shopping_cart_badge"); 
    // Added a locator to verify the page is visible (usually the main product container or title)
    private By inventoryContainerLocator = By.id("inventory_container"); 

    // ==========================================
    // 3. Methods
    // ==========================================

    /**
     * Verifies if the Inventory Page is currently displayed to the user.
     * @return true if visible, false otherwise.
     */
    public boolean isDisplayed() {
        try {
            return driver.findElement(inventoryContainerLocator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Adds an item to the cart dynamically based on the item's name.
     * @param itemName The string name of the product to add.
     */
    public void addToCart(String itemName) {
        // Finds the "Add to cart" button associated with a specific item name.
        String dynamicXPath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button[contains(text(), 'Add to cart')]", itemName);
        driver.findElement(By.xpath(dynamicXPath)).click();
    }

    /**
     * Removes an item from the cart dynamically based on the item's name.
     * @param itemName The string name of the product to remove.
     */
    public void removeFromCart(String itemName) {
        String dynamicXPath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button[contains(text(), 'Remove')]", itemName);
        driver.findElement(By.xpath(dynamicXPath)).click();
    }

    /**
     * Clicks the shopping cart icon to navigate to the cart page.
     */
    public void openCart() {
        // Wait until it's actually clickable before clicking
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(cartIconLocator)).click();
    }

    /**
     * Reads the number on the cart badge and returns it as an integer.
     * @return The number of items currently in the cart.
     */
    public int getCartCount() {
        try {
            // Wait up to 5 seconds for the badge to show up
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadgeLocator));
            return Integer.parseInt(badge.getText());
        } catch (Exception e) {
            return 0; 
        }
    }
}
