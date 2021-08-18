package support;

import io.github.bonigarcia.wdm.WebDriverManager;
import logger.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DriverFactory {

	public static WebDriver driver;

	public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the thradlocal driver on the basis of given
	 * browser
	 *
	 * @param browser
	 * @return this will return webdriver.
	 */
	public WebDriver init_driver(String browser, String isHeadless) {

		Log.info("Browser type: " + browser);
		Log.info("IsHeadless: " + isHeadless);

		switch (browser) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();

				if (isHeadless.equals("false")) {
					chromeOptions.addArguments("--start-maximized");
				} else {
					chromeOptions.addArguments("--headless");
					chromeOptions.addArguments("--disable-gpu");
					chromeOptions.addArguments("window-size=1920,1080");
				}

				threadDriver.set(new ChromeDriver(chromeOptions));
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				threadDriver.set(new FirefoxDriver());
				break;
			default:
				Log.logError("Please pass the correct browser value: " + browser);
				break;
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().pageLoadTimeout(20, SECONDS);
		getWebDriverWait();
		return getDriver();
	}

	/**
	 * this is used to get the driver with ThreadLocal
	 * 
	 * @return WebDriver
	 */
	public static synchronized WebDriver getDriver() {
		return threadDriver.get();
	}

	/**
	 * this is used to get the initialize WebdriverWait
	 *
	 * @return WebDriverWait instance
	 */
	public static WebDriverWait getWebDriverWait() {
		return new WebDriverWait(getDriver(), 10);
	}
}
