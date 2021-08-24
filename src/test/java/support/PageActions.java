package support;

import logger.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
            Log.info("Element not displayed on UI :" + element.toString() + " " + exception.getMessage());
            return false;
        }
    }

    /**
     * This method is used to click web element on browser
     *
     * @param element
     */
    public static void click(final WebElement element) {
        try {
            defaultWait.until(ExpectedConditions.visibilityOf(element));
            element.click();
        } catch (Exception exception) {
            Log.logError("Element not displayed on UI :" + element + " " + exception.getMessage());
            throw new ElementNotVisibleException("Cant Find Element" + element.toString());
        }
    }

    public static void waitUntilJSReady() {
        try {
            ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState").toString().equals("complete");

            boolean jsReady = jsExec.executeScript("return document.readyState").toString().equals("complete");

            if (!jsReady) {
                defaultWait.until(jsLoad);
            }
        } catch (WebDriverException exception) {
            Log.logError(exception.toString());
        }
    }
}
