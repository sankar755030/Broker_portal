package pages;

import base.BasePage;
import listeners.ExtentReportListener;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class GeneralPage extends BasePage {

    public GeneralPage(WebDriver driver) {
        super(driver);
    }

    // Search History Hours

    private final By txtHours = By.cssSelector("input.hours-input");
    private final By btnHoursSave = By.xpath("//span[text()='Search History Hours Limit']/ancestor::div[contains(@class,'search-history-section')]//button[contains(@class,'save-btn')]");
    private final By txtSimilarity = By.xpath("//span[text()='Similarity Score Label']/ancestor::div[contains(@class,'search-history-section')]//input");
    private final By btnSimilaritySave = By.xpath("//span[text()='Similarity Score Label']/ancestor::div[contains(@class,'search-history-section')]//button[contains(@class,'save-btn')]");
    By lblReasonsHeading = By.xpath("//h3[text()='Reasons']");
    By lblReasonsDescription = By.xpath("//h3[text()='Reasons']/following-sibling::p");
    By lblSimilarityScoreLabel = By.xpath("//span[text()='Similarity Score Label']");
    By lblSimilarityScoreHint = By.xpath("//span[text()='Similarity Score Label']/following-sibling::p");


    public boolean VerifySimilarityScoreHint() {
        boolean result = false;
        waitForPresence(lblSimilarityScoreHint);

        String text = driver.findElement(lblSimilarityScoreHint).getText();

        if (Objects.equals(text, "Set similarity label for search results (maximum 16 characters)")) {
            result = true;
        }

        return result;
    }

    public boolean VerifySimilarityScoreLabel() {
        boolean result = false;
        waitForPresence(lblSimilarityScoreLabel);

        String text = driver.findElement(lblSimilarityScoreLabel).getText();

        if (Objects.equals(text, "Similarity Score Label")) {
            result = true;
        }

        return result;
    }
    public boolean VerifyReasonsDescription() {
        boolean result = false;
        waitForPresence(lblReasonsDescription);

        String text = driver.findElement(lblReasonsDescription).getText();

        if (Objects.equals(text, "\"Reason for Processing\" explains why face search action was taken, helping users understand decision-making clearly.")) {
            result = true;
        }

        return result;
    }


    public boolean VerifyReasonsHeading() {
        boolean result = false;
        waitForPresence(lblReasonsHeading);

        String text = driver.findElement(lblReasonsHeading).getText();

        if (Objects.equals(text, "Reasons")) {
            result = true;
        }

        return result;
    }
    public void toggleConfiguration(String configurationName) {

        By checkbox = By.xpath(
                "//td[normalize-space()='" + configurationName + "']" +
                        "/following-sibling::td//input[@type='checkbox']");

        By slider = By.xpath(
                "//td[normalize-space()='" + configurationName + "']" +
                        "/following-sibling::td//span[contains(@class,'slider')]");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement checkBoxElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(checkbox));

        boolean currentState = checkBoxElement.isSelected();

        if (currentState) {
            ExtentReportListener.getExtentTest().info(configurationName + " is ON. Turning OFF.");
        } else {
            ExtentReportListener.getExtentTest().info(configurationName + " is OFF. Turning ON.");
        }

        WebElement sliderElement = wait.until(
                ExpectedConditions.elementToBeClickable(slider));

        sliderElement.click();
        pause(5000);

        ExtentReportListener.getExtentTest().pass(configurationName + " status changed successfully.");
    }

    public void enterSearchHistoryHours(String value) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement txt = wait.until(ExpectedConditions.visibilityOfElementLocated(txtHours));

        txt.click();
        txt.sendKeys(Keys.CONTROL + "a");   // Select existing value
        txt.sendKeys(Keys.DELETE);          // Delete old value
        txt.sendKeys(value);                // Enter new value
        txt.sendKeys(Keys.TAB);             // Trigger Angular change event
        pause(5000);
    }


    public void saveSearchHistoryHours() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement saveButton = wait.until(driver -> {
            WebElement btn = driver.findElement(btnHoursSave);
            return btn.isEnabled() ? btn : null;
        });

        saveButton.click();
        pause(5000);
    }

    public void enterSimilarityScore(String value) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement txt = wait.until(ExpectedConditions.visibilityOfElementLocated(txtSimilarity));

        txt.click();
        txt.sendKeys(Keys.CONTROL + "a");   // Select existing value
        txt.sendKeys(Keys.DELETE);          // Delete old value
        txt.sendKeys(value);                // Enter new value
        txt.sendKeys(Keys.TAB);             // Trigger Angular change event
        pause(3000);

    }

    public void saveSimilarityScore() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(btnSimilaritySave));

        saveButton.click();

        pause(3000);
    }



    private final By txtSearchHistoryHours =
            By.xpath("//span[text()='Search History Hours Limit']/ancestor::div[contains(@class,'search-history-section')]//input");

    private final By btnSearchHistorySave =
            By.xpath("//span[text()='Search History Hours Limit']/ancestor::div[contains(@class,'search-history-section')]//button");

    // Similarity Score

    private final By txtSimilarityScore =
            By.xpath("//span[text()='Similarity Score Label']/ancestor::div[contains(@class,'search-history-section')]//input");

    private final By searchHistoryHeading = By.xpath("//span[text()='Search History Hours Limit']");



    private final By lnkGeneral = By.xpath("//a[normalize-space()='General']");

    public void clickGeneral() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement general = wait.until(
                ExpectedConditions.visibilityOfElementLocated(lnkGeneral));

        wait.until(ExpectedConditions.elementToBeClickable(general));

        general.click();
        pause(3000);
    }


    public void updateSearchHistoryHours(String hours) {

        type(txtSearchHistoryHours, hours);
        click(btnSearchHistorySave);

    }

    public void updateSimilarityScore(String score) {

        type(txtSimilarityScore, score);
        click(btnSimilaritySave);

    }

}