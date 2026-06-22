package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * Provides a thread-local WebDriver instance so Cucumber scenarios can run in
 * parallel without different threads sharing driver sessions.
 *
 * Usage pattern:
 *   Hooks.java  → calls initDriver() in @Before, quitDriver() in @After
 *   Step Defs   → call getDriver() to access the session for that thread
 */
public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        String browser = ConfigReader.getBrowser().toLowerCase();
        WebDriver webDriver;

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ffOpts = new FirefoxOptions();
                if (ConfigReader.isHeadless()) ffOpts.addArguments("--headless");
                webDriver = new FirefoxDriver(ffOpts);
                break;

            default: // chrome
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOpts = new ChromeOptions();
                if (ConfigReader.isHeadless()) chromeOpts.addArguments("--headless=new");
                chromeOpts.addArguments("--window-size=1920,1080", "--disable-gpu", "--no-sandbox");
                webDriver = new ChromeDriver(chromeOpts);
        }

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        webDriver.manage().window().maximize();
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("Driver not initialised — check that @Before hook ran correctly.");
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
