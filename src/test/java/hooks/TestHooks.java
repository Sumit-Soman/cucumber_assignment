package hooks;

import logger.Log;
import support.DriverFactory;
import utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestHooks {

    private DriverFactory driverFactory;
    private WebDriver driver;
    private ConfigReader configReader;
    Properties prop;

    @Before(order = 0)
    public void getProperty() {
        configReader = new ConfigReader();
        prop = configReader.initialize();
    }

    @Before(order = 1)
    public void launchBrowser() {
        String browserName = Optional.ofNullable(System.getProperty("browser"))
                .orElse(prop.getProperty("browser"));
        String isHeadless =Optional.ofNullable(System.getProperty("isHeadless"))
                .orElse(prop.getProperty("isHeadless"));

        driverFactory = new DriverFactory();
        driver = driverFactory.init_driver(browserName, isHeadless);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After(order = 0)
    public void quitBrowser() {
        driver.quit();
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            Log.info(scenario.getName() + " failed");
            // take screenshot:
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);
        }
    }
}
