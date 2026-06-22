package hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.ConfigReader;
import utils.DriverManager;

public class Hooks {

    /**
     * Initialises a fresh driver session before every scenario.
     * The @Before order=10 tag means this runs before any other @Before hooks.
     */
    @Before(order = 10)
    public void setUp(Scenario scenario) {
        DriverManager.initDriver();
        DriverManager.getDriver().get(ConfigReader.getBaseUrl());
        System.out.printf("[BEFORE] Starting scenario: %s (Tags: %s)%n",
                scenario.getName(), scenario.getSourceTagNames());
    }

    /**
     * After each step, if the scenario has failed, capture a screenshot and
     * embed it in the Allure / Cucumber report immediately — before the driver quits.
     */
    @AfterStep
    public void captureScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }
    }

    /**
     * Quits the driver and cleans the ThreadLocal after every scenario,
     * regardless of pass/fail, so no dangling sessions in parallel runs.
     */
    @After(order = 10)
    public void tearDown(Scenario scenario) {
        System.out.printf("[AFTER] Scenario '%s' — Status: %s%n",
                scenario.getName(), scenario.getStatus());
        DriverManager.quitDriver();
    }
}
