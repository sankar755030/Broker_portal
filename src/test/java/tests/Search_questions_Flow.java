package tests;

import base.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.GeneralPage;
import pages.LoginPage;
import pages.application.SearchQuestionsPage;
import utils.BrowserUtility;
import utils.DriverManager;
import utils.JsonDataReader;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

    @Listeners(ExtentReportListener.class)
    public class Search_questions_Flow {

        WebDriver driver;
        WebDriverWait wait;
        BasePage basePage;
        LoginPage loginPage;
        DashboardPage dashboardPage;
        GeneralPage generalPage;
        SearchQuestionsPage searchQuestionspage;

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
            searchQuestionspage = new SearchQuestionsPage(driver);

        }

        @Test(description = "Update General Configuration")
        public void Searchquestions_Flow() {

            String NFW_URL = JsonDataReader.get(0, "NFW_URL");
            String userName = JsonDataReader.get(0, "sankarUsername");
            String password = JsonDataReader.get(0, "sankarPassword");


            driver.get(NFW_URL);
            ExtentReportListener.getExtentTest().info("Opened NFW application URL.");

            loginPage.LoginIntoApplication(userName, password);
            ExtentReportListener.getExtentTest().info("Entered username and password.");

            loginPage.clickSignIn();
            Assert.assertTrue(generalPage.VerifyReasonsDescription());
            ExtentReportListener.getExtentTest().info("Clicked on Sign In button.");

            ExtentReportListener.getExtentTest().info("Clicking on Search Questions menu.");
            searchQuestionspage.clickSearchQuestions();

            ExtentReportListener.getExtentTest().info("Entering 'Test' in Search Filter.");
            searchQuestionspage.enterSearchFilter("Test");

            ExtentReportListener.getExtentTest().info("Entering special characters '!@#$%^&*()_+' in Search Filter.");
            searchQuestionspage.enterSearchFilter("!@#$%^&*()_+");

            ExtentReportListener.getExtentTest().info("Clearing Search Filter.");
            searchQuestionspage.clearSearchFilter();

            ExtentReportListener.getExtentTest().info("Selecting category: Pre Search - Before Image Capture.");
            searchQuestionspage.selectCategory("Pre Search - Before Image Capture");

            ExtentReportListener.getExtentTest().info("Selecting category: Pre Search - After Image Capture.");
            searchQuestionspage.selectCategory("Pre Search - After Image Capture");

            ExtentReportListener.getExtentTest().info("Selecting category: Post Search.");
            searchQuestionspage.selectCategory("Post Search");

            ExtentReportListener.getExtentTest().info("Clicking on Add New button.");
            searchQuestionspage.clickAddNew();

            ExtentReportListener.getExtentTest().info("Verifying Add New Question popup is displayed.");
            Assert.assertTrue(searchQuestionspage.VerifyAddNewQuestionPopup());

            ExtentReportListener.getExtentTest().info("Entering question: Automation Question.");
            searchQuestionspage.enterQuestion("Automation Question");

            ExtentReportListener.getExtentTest().info("Selecting Response Type: Text Based.");
            searchQuestionspage.selectResponseType("Text Based");

            ExtentReportListener.getExtentTest().info("Selecting Question Group: Pre Search - Before Image Capture.");
            searchQuestionspage.selectQuestionGroup("Pre Search - Before Image Capture");

            ExtentReportListener.getExtentTest().info("Enabling 'Is Enabled' toggle.");
            searchQuestionspage.enableIsEnabled();

            ExtentReportListener.getExtentTest().info("Enabling 'Mandatory' toggle.");
            searchQuestionspage.enableMandatory();

            ExtentReportListener.getExtentTest().info("Clicking Add button to save the question.");
            searchQuestionspage.clickAdd();

            ExtentReportListener.getExtentTest().info("Clicking on Add New button again.");
            searchQuestionspage.clickAddNew();

            ExtentReportListener.getExtentTest().info("Verifying Add New Question popup is displayed.");
            Assert.assertTrue(searchQuestionspage.VerifyAddNewQuestionPopup());

            ExtentReportListener.getExtentTest().info("Entering question: Cancel Question.");
            searchQuestionspage.enterQuestion("Cancel Question");

            ExtentReportListener.getExtentTest().info("Selecting Response Type: Binary.");
            searchQuestionspage.selectResponseType("Binary");

            ExtentReportListener.getExtentTest().info("Selecting Question Group: Post Search.");
            searchQuestionspage.selectQuestionGroup("Post Search");

            ExtentReportListener.getExtentTest().info("Enabling 'Is Enabled' toggle.");
            searchQuestionspage.enableIsEnabled();

            ExtentReportListener.getExtentTest().info("Enabling 'Mandatory' toggle.");
            searchQuestionspage.enableMandatory();

            ExtentReportListener.getExtentTest().info("Clicking Cancel button to close the popup without saving.");
            searchQuestionspage.clickCancel();


        }

        @AfterMethod
        public void tearDown() {
//            DriverManager.quitDriver();
        }
}



