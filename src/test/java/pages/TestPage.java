package pages;

import logger.Log;
import support.PageActions;
import support.PageWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

public class TestPage extends PageActions{
    private final WebDriver driver;

    public TestPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = ".table-control-area > div:nth-of-type(1) > div")
    WebElement showRowsDropDwn;

    @FindBy(css = "[class*='cmc-table'] tbody > tr")
    List<WebElement> itemRows;

    @FindBy(css = ".table-control-area .table-control-filter")
    WebElement filterBtn;

    @FindBy(css = ".cmc-body-wrapper ul")
    WebElement filterPanel;

    @FindBy(css = ".cmc-body-wrapper li:nth-of-type(5) > button")
    WebElement addFilterBtn;

    @FindBy(xpath = "//button[contains(., 'Market Cap')]")
    WebElement marketCapFilter;

    @FindBy(xpath = "//button[contains(., 'Price')]")
    WebElement priceFilter;

    @FindBy(css = "[data-qa-id=\"filter-dd-button-apply\"]")
    WebElement applyFilterBtn;

    @FindBy(xpath = "//button[contains(., 'Close')]")
    WebElement closeFilterBtn;

    @FindBy(xpath = "//button[contains(., 'Show results')]")
    WebElement showResultsBtn;

    @FindBy(css=".cmc-table td:nth-child(4)")
    List<WebElement> priceRecordsList;

    @FindBy(css=".cmc-table td:nth-child(7)")
    List<WebElement> marketCapRecordsList;

    public static final String searchRange = "//button[contains(., '$RANGE')]";

    public static final String showRowsDrpDwnList = "//button[contains(., '$VALUE')]";

    public boolean getShowRowsValue(int rows) {
        return PageWaits
                .waitForTextToContain(showRowsDropDwn, String.valueOf(rows));
    }

    public void selectOptionFromShowMoreDrop(int value) {
        click(showRowsDropDwn);
        click(driver.findElement(By.xpath(showRowsDrpDwnList.replace("$VALUE",
                String.valueOf(value)))));
    }

    public long getDisplayedRows() {
        return itemRows.size();
    }

    public void openFilterOptions() {
        // check if filter panel is not visible to open filter panel
        if (!PageActions.isDisplayed(filterPanel)) {
            click(filterBtn);
        }
    }

    public void clickAddFilter() {
        click(addFilterBtn);
    }

    public void filterByMarketCap(String range) {
        openFilterOptions();
        clickAddFilter();
        click(marketCapFilter);
        searchRange(range);
        applyFilterAndShowResults();
    }

    public void filterByPrice(String range) {
//        openFilterOptions();
        clickAddFilter();
        click(priceFilter);
        searchRange(range);
        applyFilterAndShowResults();
    }

    private void applyFilterAndShowResults() {
        click(applyFilterBtn);
        click(showResultsBtn);
        waitUntilJSReady();
    }

    private void searchRange(String range) {
        click(driver.findElement(By.xpath(searchRange.replace("$RANGE", range))));
    }

    public boolean verifyRecordWithinMarketCap(float minMarket, float maxMarket) {
        return validateRange(marketCapRecordsList, minMarket, maxMarket);
    }

    public boolean verifyRecordWithinPrice(float minPrice, float maxPrice) {
        return validateRange(priceRecordsList, minPrice, maxPrice);
    }

    private boolean validateRange(List<WebElement> element, float minPrice, float maxPrice) {
        NumberFormat number = NumberFormat.getCurrencyInstance(Locale.US);

        return element.stream().allMatch(webElement -> {
            try {
                float value = number.parse(webElement.getText()).floatValue();
                return value >= minPrice && value <= maxPrice;
            } catch (ParseException e) {
                Log.logError("Error received : "+ e.getMessage());
                return false;
            }
        });
    }
}
