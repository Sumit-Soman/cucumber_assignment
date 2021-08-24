package stepDefinitions;

import static configs.Constants.BASE_URL;
import configs.NumberEnum;
import pages.TestPage;
import support.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.ConfigReader;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class TaskSteps {

    private final WebDriver driver = DriverFactory.getDriver();
    TestPage testPage = new TestPage();

    @Given("user is on Test page")
    public void userIsOnTestPage() {
        driver.get(ConfigReader
                .getConfigProperties()
                .getProperty(BASE_URL));
    }

    @When("user Shows {int} rows")
    public void userSelectInShowMoreFilter(int rows) {
        testPage.selectOptionFromShowMoreDrop(rows);
    }

    @Then("user should see {int} entries")
    public void userShouldSeeEntries(int rows) {
        Assert.assertTrue(testPage.getShowRowsValue(rows));
        Assert.assertEquals(rows, testPage.getDisplayedRows());
    }

    @When("user filter MarketCap with range {string}")
    public void userFilterMarketCapWithRange(String range) throws InterruptedException {
        testPage.filterByMarketCap(range);
        Thread.sleep(40000);
    }

    @And("user filter Price with range {string}")
    public void userFilterPriceWithRange$$(String range) {
        testPage.filterByPrice(range);
    }

    @Then("user should see filter records by Market {string} and Price {string}")
    public void userShouldSeeFilterRecordsByMarketAndPrice(String marketRange, String priceRange) throws ParseException {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);

        String[] marketSplits = marketRange.replaceAll("\\s+","").split("-");
        String[] priceSplits = priceRange.replaceAll("\\s+","").split("-");

        float minMarket = numberFormat.parse(marketSplits[0]).intValue() * NumberEnum.valueOf(marketSplits[0].substring(marketSplits[0].length() - 1)).getValue();
        float maxMarket = numberFormat.parse(marketSplits[1]).intValue() * NumberEnum.valueOf(marketSplits[1].substring(marketSplits[1].length() - 1)).getValue();
        int minPrice = numberFormat.parse(priceSplits[0]).intValue();
        int maxPrice = numberFormat.parse(priceSplits[1]).intValue();

        Assert.assertTrue(testPage.verifyRecordWithinMarketCap(minMarket, maxMarket));
        Assert.assertTrue(testPage.verifyRecordWithinPrice(minPrice, maxPrice));
    }


}
