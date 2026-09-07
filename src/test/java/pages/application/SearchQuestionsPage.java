package pages.application;
import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class SearchQuestionsPage extends BasePage {

    public SearchQuestionsPage(WebDriver driver) {
        super(driver);
    }


    By btnIsEnabled = By.xpath("//input[@formcontrolname='isEnabled']/following-sibling::span");

    By btnMandatory = By.xpath("//input[@formcontrolname='isMandatory']/following-sibling::span");
    By lnkSearchQuestions = By.xpath("//a[text()='Search Questions']");

    By txtSearchFilter = By.xpath("//input[@placeholder='Search Filter']");

//    By ddlCategory = By.xpath("//mat-select");
    By ddlCategory = By.xpath("(//mat-select[@role='combobox'])[1]");
//    By ddlCategory = By.xpath("//mat-select[contains(@formcontrolname,'category') or contains(@aria-labelledby,'category')]");

    By optPreSearchBeforeImageCapture = By.xpath("//mat-option//span[normalize-space()='Pre Search - Before Image Capture']");

    By optPreSearchAfterImageCapture = By.xpath("//mat-option//span[normalize-space()='Pre Search - After Image Capture']");

    By optPostSearch = By.xpath("//mat-option//span[normalize-space()='Post Search']");

    By btnAddNew = By.xpath("//button[contains(.,'Add New')]");

    By lblAddNewQuestionPopup = By.xpath("//h1[text()='Add New Question']");

    By txtQuestion = By.xpath("//input[@placeholder='Enter question']");

    By ddlResponseType = By.xpath("//mat-select[@formcontrolname='questionTypeId']");

    By ddlQuestionGroup = By.xpath("//mat-select[@formcontrolname='questionCategoryId']");
    By chkIsEnabled = By.xpath("//input[@formcontrolname='isEnabled']/following-sibling::span");

    By chkMandatory = By.xpath("//input[@formcontrolname='isMandatory']/following-sibling::span");

//    By chkIsEnabled = By.xpath("//label[contains(.,'Is Enabled')]/following-sibling::label/input");
//
//    By chkMandatory = By.xpath("//label[contains(.,'Mandatory')]/following-sibling::label/input");

    By btnAdd = By.xpath("//button[text()=' Add ']");

    By btnCancel = By.xpath("//button[text()='Cancel']");

    public void enableIsEnabled() {

        waitForClickable(btnIsEnabled);
        driver.findElement(btnIsEnabled).click();
        pause(1000);
    }

    public void enableMandatory() {

        waitForClickable(btnMandatory);
        driver.findElement(btnMandatory).click();
        pause(1000);
    }

    public void enableMandatory(boolean value) {

        WebElement input = driver.findElement(By.xpath("//input[@formcontrolname='isMandatory']"));

        if (input.isSelected() != value) {

            driver.findElement(chkMandatory).click();
        }
    }


    public void enableIsEnabled(boolean value) {

        WebElement input = driver.findElement(By.xpath("//input[@formcontrolname='isEnabled']"));

        if (input.isSelected() != value) {

            driver.findElement(chkIsEnabled).click();
        }
    }




    public void selectQuestionGroup(String group) {

        waitForClickable(ddlQuestionGroup);
        driver.findElement(ddlQuestionGroup).click();

        By option = By.xpath("//div[@role='listbox']//span[normalize-space()='" + group + "']");

        waitForClickable(option);
        driver.findElement(option).click();
        pause(3000);
    }

    public void enterQuestion(String question) {

        waitForPresence(txtQuestion);

        WebElement txt = driver.findElement(txtQuestion);

        txt.click();
        txt.clear();
        txt.sendKeys(question);
        pause(3000);
    }

    public void selectResponseType(String responseType) {

        waitForClickable(ddlResponseType);
        driver.findElement(ddlResponseType).click();

        By option = By.xpath(
                "//div[@role='listbox']//span[normalize-space()='" + responseType + "']"
        );

        waitForPresence(option);
        waitForClickable(option);

        driver.findElement(option).click();
        pause(3000);
    }


    public void clickSearchQuestions() {
        waitForClickable(lnkSearchQuestions);
        driver.findElement(lnkSearchQuestions).click();
        pause(3000);
    }
    public void enterSearchFilter(String value) {

        waitForPresence(txtSearchFilter);

        WebElement txt = driver.findElement(txtSearchFilter);

        txt.click();
        txt.clear();
        txt.sendKeys(value);
        pause(3000);
    }
    public void clearSearchFilter() {

        WebElement txt = driver.findElement(txtSearchFilter);

        txt.click();
        txt.sendKeys(Keys.CONTROL + "a");
        txt.sendKeys(Keys.DELETE);
        pause(3000);
    }
    public void selectCategory(String category) {

        driver.findElement(ddlCategory).click();
        pause(3000);

        driver.findElement(By.xpath("//mat-option//span[normalize-space()='" + category + "']")).click();
        pause(3000);
    }
    public void clickAddNew() {

        waitForClickable(btnAddNew);

        driver.findElement(btnAddNew).click();
        pause(3000);
    }
    public boolean VerifyAddNewQuestionPopup() {

        boolean result = false;

        waitForPresence(lblAddNewQuestionPopup);

        String text = driver.findElement(lblAddNewQuestionPopup).getText();

        if(Objects.equals(text,"Add New Question"))
        {
            result = true;
        }

        return result;

    }

    public void fillQuestionPopup(String question,
                                  String responseType,
                                  String questionGroup,
                                  boolean enabled,
                                  boolean mandatory)
    {

        driver.findElement(txtQuestion).sendKeys(question);

        driver.findElement(ddlResponseType).click();

        driver.findElement(By.xpath("//mat-option//span[text()='"+responseType+"']")).click();

        driver.findElement(ddlQuestionGroup).click();

        driver.findElement(By.xpath("//mat-option//span[text()='"+questionGroup+"']")).click();

        if(enabled)
            driver.findElement(chkIsEnabled).click();
        pause(3000);

        if(mandatory)
            driver.findElement(chkMandatory).click();
        pause(3000);
    }
    public void clickAdd() {

        driver.findElement(btnAdd).click();
        pause(3000);
    }

    public void clickCancel() {

        driver.findElement(btnCancel).click();
        pause(3000);
    }



}