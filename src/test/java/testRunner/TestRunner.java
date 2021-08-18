package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"stepDefinitions", "hooks"},
        tags = "",
        monochrome = true,
        plugin = {"json:target/cucumber-report/cucumber.json",
                "pretty",
                "html:target/cucumber-reports/cucumber-html-report"}
)

public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

