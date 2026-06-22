package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = { "hooks", "stepdefinitions" },
        tags = "not @wip",       // skip work-in-progress scenarios; override from CLI: -Dcucumber.filter.tags="@smoke"
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/report.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"   // Allure adapter
        },
        monochrome = true,
        publish = false
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * Override the DataProvider to run scenarios in parallel.
     * Remove parallel=true to run sequentially (easier for local debugging).
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
