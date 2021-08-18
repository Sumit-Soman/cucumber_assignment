package support;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageActions extends DriverFactory{
    private static final WebDriverWait defaultWait = getWebDriverWait();

    /**
     * This method is used to check webElment isdisplay on browser
     *
     * @param element
     * @return boolean
     */
    public static boolean isDisplayed(final WebElement element) {
        try {
            defaultWait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
