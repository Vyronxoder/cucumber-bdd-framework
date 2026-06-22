package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private final By firstNameField  = By.id("first-name");
    private final By lastNameField   = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueBtn     = By.id("continue");
    private final By finishBtn       = By.id("finish");
    private final By confirmHeader   = By.className("complete-header");
    private final By errorMsg        = By.cssSelector("h3[data-test='error']");

    public CheckoutPage(WebDriver driver) { super(driver); }

    public void fillInfo(String first, String last, String zip) {
        type(firstNameField, first);
        type(lastNameField, last);
        type(postalCodeField, zip);
        click(continueBtn);
    }

    public void finish()                  { click(finishBtn); }
    public String getConfirmation()        { return getText(confirmHeader); }
    public boolean isErrorVisible()        { return isVisible(errorMsg); }
    public String getErrorMessage()        { return getText(errorMsg); }
}
