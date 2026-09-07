package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtility;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    public void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Pause interrupted: " + e.getMessage());
        }
    }

    // Wait until element is visible
    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait until element is clickable
    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Click
    public void click(By locator) {
        waitForClickable(locator).click();
    }

    // Enter Text
    public void sendKeys(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.sendKeys(text);
    }

    // Clear Text
    public void clear(By locator) {
        waitForVisibility(locator).clear();
    }

    // Clear and Enter Text
    public void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Get Text
    public String getText(By locator) {
        return waitForVisibility(locator).getText().trim();
    }

    // Check Displayed
    public boolean isDisplayed(By locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Scroll Into View
    public void scrollIntoView(By locator) {
        WebElement element = waitForVisibility(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // JavaScript Click
    public void jsClick(By locator) {
        WebElement element = waitForVisibility(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // Hover
    public void hover(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(waitForVisibility(locator)).perform();
    }

    // Wait for Invisibility
    public void waitForInvisibility(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Get Attribute
    public String getAttribute(By locator, String attribute) {
        return waitForVisibility(locator).getAttribute(attribute);
    }

    // Check Checkbox
    public void check(By locator) {
        WebElement element = waitForVisibility(locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    // Uncheck Checkbox
    public void uncheck(By locator) {
        WebElement element = waitForVisibility(locator);
        if (element.isSelected()) {
            element.click();
        }
    }


    public WebElement waitForElement(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void scrollIntoView01(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    protected void jsClick01(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    public WebElement waitVisible01(By by, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitClickable01(By by, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitForOverlayToDisappear(By overlayBy, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        try {
            wait.until(d -> d.findElements(overlayBy).isEmpty());
        } catch (Exception ignored) {
            // don't fail the test because overlay locator may not match in some pages
        }
    }

    public WebElement waitForElement50(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForPresence(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(120))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void scrollAndJsClick(By locator, int timeoutInSeconds) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void scrollToBottomOfModal(By locator, int timeoutInSeconds) {
        WebElement scrollableDiv = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", scrollableDiv);
    }



    public void switchToFrame(By locator, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void dragAndDrop(By sourceLocator, By targetLocator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        // Wait until both source and target are visible & clickable
        WebElement source = wait.until(ExpectedConditions.elementToBeClickable(sourceLocator));
        WebElement target = wait.until(ExpectedConditions.visibilityOfElementLocated(targetLocator));

        // Scroll both into view (optional but avoids "not clickable at point" issues)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", source);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", target);

        // Perform drag and drop
        Actions actions = new Actions(driver);
        actions.clickAndHold(source)
                .pause(Duration.ofMillis(300))   // small stability pause
                .moveToElement(target)
                .pause(Duration.ofMillis(300))
                .release()
                .build()
                .perform();
    }


    public void waitAdobeFormToBeVisible(){
        String xpath = "//span[normalize-space(.)='Add form fields for']";
        WaitUtility.waitForVisibility(driver,By.xpath(xpath),60,"Adobe Form");
    }
}