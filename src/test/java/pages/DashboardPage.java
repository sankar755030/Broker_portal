package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // Menu
    private final By menuButton =
            By.xpath("//mat-icon[text()='menu']");

    private By logo = By.xpath("//div[@class='logo']//img");

//    private By logo = By.cssSelector("div.logo img[src='assets/logo/logo.svg']");

    public void waitForLogoToBeVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logo));
    }

    // Locator
    private By okButton = By.xpath("//button[.//span[text()='OK'] or text()='OK']");

    public void clickPasswordPopupOK() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
    }

    // General Menu
    private final By generalMenu =
            By.xpath("//a[text()='General']");

    public void openMenu() {
        click(menuButton);
    }

}