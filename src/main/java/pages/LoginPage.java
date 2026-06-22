package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By username    = By.id("user-name");
    private final By password    = By.id("password");
    private final By loginBtn    = By.id("login-button");
    private final By errorMsg    = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) { super(driver); }

    public void enterUsername(String user) { type(username, user); }
    public void enterPassword(String pass) { type(password, pass); }
    public void clickLogin()              { click(loginBtn); }
    public String getErrorMessage()       { return getText(errorMsg); }
    public boolean isErrorVisible()       { return isVisible(errorMsg); }
}
