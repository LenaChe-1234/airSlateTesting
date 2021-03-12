package pages;

import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class LoginPage extends ParentPage{

    //TODO create base_url
    //private String loginPageUrl = "https://my.airslate-dev07.xyz/login";
    private String loginPageUrl = String.format(baseUrl,getFirstPartOfRelativeUrl(),getRelativeUrl());

    @FindBy (name = "username")
    private WebElement inputUserEmail;
    @FindBy (name = "password")
    private WebElement inputUserPass;
    @FindBy (xpath = ".//span[contains(@class, 'sl-button__text')and text()='Log in']")
    private WebElement buttonLogIn;
    @FindBy (xpath = ".//body[contains (@class, 'grecaptcha-badge-visible grecaptcha-badge-over-chat' )]")
    private WebElement buttonEye;
    @FindBy (xpath = ".//span[contains(@class, 'sl-button__text') and text()= 'Create a new Workspace']")
    private WebElement buttonCreateNewWorkspace;
    @FindBy (xpath = ".//*[@type = 'error']")
    private WebElement errorMessageEmail;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }
//TODO format url https://my.airslate-dev07.xyz/login!!!

    //add my. to the BASE_URL
    @Override
    String getRelativeUrl() {
        return "/login";
    }

    @Override
    String getFirstPartOfRelativeUrl() {
        return "my.";
    }

    @Step
    public LoginPage openLoginPage(){
        try {
            //webDriver.get(loginPageUrl);
            //webDriver.get("https://my.airslate.com/login");
            webDriver.get("https://my.airslate-dev07.xyz"+getRelativeUrl());
            logger.info("Login Page was opened");
        } catch (Exception e){
            logger.error("Can not open Login page");
            Assert.fail("Can not open Login page");
            Assert.assertEquals("Login page is not loaded","https://my.airslate.com/login",webDriver.getCurrentUrl());
        }
        return this;
    }

    @Step
    public LoginPage enterUserEmail(String userEmail) {
        webDriverWait10.until(ExpectedConditions.elementToBeClickable(inputUserEmail));
        enterTextIntoElement(inputUserEmail, userEmail);
        return this;
    }

    @Step
    public LoginPage enterUserPass(String userPass) {
        webDriverWait10.until(ExpectedConditions.elementToBeClickable(inputUserPass));
        enterTextIntoElement(inputUserPass, userPass);
        return this;
    }

    @Step
    public LoginPage clickEyeButton() {
        clickOnElement(buttonEye);
        return this;
    }


    @Step
    public void clickButtonLogIn() {
        clickOnElement(buttonLogIn);
    }


    @Step
    public DomainSelectPage loginWithValidData(String userEmail, String userPass){
        openLoginPage();
        enterUserEmail(userEmail);
        enterUserPass(userPass);
        waitTillLoginPageIsLoaded();
        clickButtonLogIn();
        return new DomainSelectPage(webDriver);
    }

    @Step
    public LoginPage waitTillLoginPageIsLoaded(){
        webDriverWait15.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@role = 'presentation']")));
        return this;
    }


    @Step
    public LoginPage loginWithInvalidEmail(String invalidUserEmail, String userPass) {
        openLoginPage();
        enterUserEmail(invalidUserEmail);
        enterUserPass(userPass);
        waitTillLoginPageIsLoaded();
        clickButtonLogIn();
        return this;
    }


    @Step
    public String getErrorMessageText(){
        WebElement error = webDriver.findElement((By) errorMessageEmail);
        String textOfError = error.getText();
        logger.info(textOfError);
        return textOfError;
    }

    @Step
    public LoginPage waitTillErrorWillDisplayed(){
        webDriverWait15.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@type = 'error']")));
       return this;
    }

    @Step
    public LoginPage checkIsRedirectedToLoginPage(){
        Assert.assertEquals("Wrong Page url " ,loginPageUrl,webDriver.getCurrentUrl());
        return this;
    }

    @Step
    public int numberOfErrorMessageWereDisplayed (){
        visibilityOfAllElementsLocatedBy(By.xpath(".//*[@type = 'error']"));
        List<WebElement> list = new ArrayList<>();
        list = webDriver.findElements(By.xpath(".//*[@type = 'error']"));
        int listSize = list.size();
        return listSize;
    }



    @Step
    public void checkErrorsMessages(String errorMessage)  {
        webDriverWait15.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@type ='error']")));
        String [] errorsArray = errorMessage.split(";");

        List<WebElement> actualErrorsList = webDriver.findElements(By.xpath(".//*[@type ='error']"));

        Assert.assertEquals("Number os Messages", errorsArray.length, actualErrorsList.size());
        SoftAssertions softAssertions = new SoftAssertions();
        ArrayList <String > textFromErrors = new ArrayList<>();
        for (WebElement element : actualErrorsList){
            textFromErrors.add(element.getText());
        }

        for (int i = 0; i < errorsArray.length; i++) {
            softAssertions.assertThat(errorsArray[i]).isIn(textFromErrors);
        }
        softAssertions.assertAll();


    }
}
