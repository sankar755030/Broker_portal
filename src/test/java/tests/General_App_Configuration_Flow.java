package tests;

import base.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.GeneralPage;
import pages.LoginPage;
import utils.BrowserUtility;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


@Listeners(ExtentReportListener.class)
public class General_App_Configuration_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    GeneralPage generalPage;

    @BeforeMethod
    public void setupBrowser() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Open Chrome in Guest mode
        options.addArguments("--guest");

        // Disable Password Manager
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        // Create ChromeDriver
        driver = new ChromeDriver(options);
        DriverManager.setDriver(driver);

        driver.manage().window().maximize();



        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        generalPage = new GeneralPage(driver);

    }

    @Test(description = "Update General Configuration")
    public void updateGeneralConfiguration() {

        String NFW_URL = JsonDataReader.get(0,"NFW_URL");
        String userName = JsonDataReader.get(0,"sankarUsername");
        String password = JsonDataReader.get(0,"sankarPassword");
        String searchHistoryHours = BrowserUtility.RandomUtils.getRandomNumber(1, 200);
        String similarityScore = BrowserUtility.RandomUtils.getRandomNumber(1, 200);
        String Searchhistoryhours = JsonDataReader.get(0,"Searchhistoryhours");
        String Similarityscore = JsonDataReader.get(0,"Similarityscore");
        String SearchhistoryhoursNegativecase = JsonDataReader.get(0,"SearchhistoryhoursNegativecase");
        String SimilarityscoreNegativecase = JsonDataReader.get(0,"SimilarityscoreNegativecase");


        driver.get(NFW_URL);
        ExtentReportListener.getExtentTest().info("Opened NFW application URL.");

        loginPage.LoginIntoApplication(userName, password);
        ExtentReportListener.getExtentTest().info("Entered username and password.");

        loginPage.clickSignIn();
        Assert.assertTrue(generalPage.VerifyReasonsDescription());
        ExtentReportListener.getExtentTest().info("Clicked on Sign In button.");

        generalPage.clickGeneral();
        Assert.assertTrue(generalPage.VerifySimilarityScoreLabel());
        ExtentReportListener.getExtentTest().info("Navigated to General configuration page.");

        generalPage.enterSearchHistoryHours(searchHistoryHours);
        Assert.assertTrue(generalPage.VerifySimilarityScoreLabel());
        ExtentReportListener.getExtentTest().info("Entered Search History Hours: " + searchHistoryHours);

        generalPage.saveSearchHistoryHours();
        Assert.assertTrue(generalPage.VerifySimilarityScoreLabel());
        ExtentReportListener.getExtentTest().pass("Search History Hours updated successfully.");

        generalPage.enterSimilarityScore(similarityScore);
        Assert.assertTrue(generalPage.VerifySimilarityScoreHint());
        ExtentReportListener.getExtentTest().info("Entered Similarity Score: " + similarityScore);

        generalPage.saveSimilarityScore();
        Assert.assertTrue(generalPage.VerifySimilarityScoreHint());
        ExtentReportListener.getExtentTest().pass("Similarity Score updated successfully.");

        generalPage.toggleConfiguration("Pick Photo From Device");
        Assert.assertTrue(generalPage.VerifySimilarityScoreHint());

        generalPage.toggleConfiguration("Declaration Page");
        Assert.assertTrue(generalPage.VerifySimilarityScoreHint());

        generalPage.toggleConfiguration("Display First Name and Last Name");
        Assert.assertTrue(generalPage.VerifySimilarityScoreHint());

        generalPage.enterSearchHistoryHours(Searchhistoryhours);
        Assert.assertTrue(generalPage.VerifySimilarityScoreLabel());
        ExtentReportListener.getExtentTest().info("Entered Search History Hours: " + Searchhistoryhours);

        generalPage.enterSimilarityScore(Similarityscore);
        Assert.assertTrue(generalPage.VerifySimilarityScoreLabel());
        ExtentReportListener.getExtentTest().info("Entered Similarity Score: " + Similarityscore);

        generalPage.saveSimilarityScore();
        Assert.assertTrue(generalPage.VerifySimilarityScoreLabel());
        ExtentReportListener.getExtentTest().pass("Similarity Score updated successfully.");

        generalPage.enterSearchHistoryHours(SearchhistoryhoursNegativecase);
        Assert.assertTrue(generalPage.VerifySimilarityScoreLabel());
        ExtentReportListener.getExtentTest().info("Entered Search History Hours: " + Searchhistoryhours);

        generalPage.enterSimilarityScore(SimilarityscoreNegativecase);
        Assert.assertTrue(generalPage.VerifySimilarityScoreLabel());
        ExtentReportListener.getExtentTest().info("Entered Similarity Score: " + Similarityscore);

        generalPage.saveSimilarityScore();
        Assert.assertTrue(generalPage.VerifySimilarityScoreLabel());
        ExtentReportListener.getExtentTest().pass("Similarity Score updated successfully.");

        generalPage.toggleConfiguration("Pick Photo From Device");
        Assert.assertTrue(generalPage.VerifySimilarityScoreHint());

        generalPage.toggleConfiguration("Declaration Page");
        Assert.assertTrue(generalPage.VerifySimilarityScoreHint());

        generalPage.toggleConfiguration("Display First Name and Last Name");
        Assert.assertTrue(generalPage.VerifySimilarityScoreHint());
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}