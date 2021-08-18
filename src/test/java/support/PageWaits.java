package support;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageWaits extends DriverFactory{
    private static final WebDriverWait defaultWait = getWebDriverWait();

    /**
     * This method waits for the text contain in webelement on dom
     *
     * @param element, text
     * @return boolean
     */
    public static boolean waitForTextToContain(WebElement element, String text) {
        return (defaultWait.until((ExpectedCondition<Boolean>) d -> element.getText().contains(text)));
    }
}
