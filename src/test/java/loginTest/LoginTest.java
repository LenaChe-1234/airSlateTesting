package loginTest;

import baseTest.BaseTest;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.LoginPage;

@RunWith(JUnitParamsRunner.class)
@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTest extends BaseTest {
    final String VALID_USER_EMAIL = "qtfreelancezh+aqa1@gmail.com";
    final String VALID_USER_PASS = "AQATestUser";
    final String INVALID_USER_EMAIL = "qtfreelancezh+aqa1.gmail.com";
    final String INVALID_USER_PASS = "AQA";

    @Description("Some detailed test description")
    @Story("Base support for bdd annotations")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void loggerCheck(){
        loginPage.openLoginPage();
        logger.info(webDriver.getCurrentUrl());
    }

    @Test
    public void validLogin() {
        loginPage.openLoginPage()
                .waitTillLoginPageIsLoaded()
                .loginWithValidData(VALID_USER_EMAIL,VALID_USER_PASS)
                   .checkIsRedirectedToDomainSelectPage()
        ;
    }

    @Test
    public void loginWithInvalidEmail(){
        loginPage.openLoginPage()
                .waitTillLoginPageIsLoaded()
                .loginWithInvalidEmail(INVALID_USER_EMAIL,VALID_USER_PASS)
                .waitTillErrorWillDisplayed()
                .checkIsRedirectedToLoginPage()
        ;
    }

    @Test
    public void loginPageTestOfErrors(){
        loginPage.loginWithInvalidEmail(INVALID_USER_EMAIL, INVALID_USER_PASS);
        Assert.assertEquals("Wrong number of error messages",2,loginPage.numberOfErrorMessageWereDisplayed());
    }


    @Test
    @Parameters({
            "qtfreelancezh+aqa1@gmail.com, AQATestUser,0 ",
            "qtfreelancezh+aqa1.gmail.com, AQATestUser, 1",
            "qtfreelancezh+aqa1.gmail.com, 1, 2"
    })
    @TestCaseName("loginPageTestOfErrorsWithParam: login = {1}, pass = {2}, numberOfErrors = {3}")
    public void loginPageTestOfErrorsWithParam(String login, String pass, int numberOfErrors){
        loginPage.loginWithInvalidEmail(login, pass);
        Assert.assertEquals("Wrong number of error messages",numberOfErrors,loginPage.numberOfErrorMessageWereDisplayed());
    }

    @Test
    public  void loginToTheWorkspace(){
        loginPage.loginWithValidData(VALID_USER_EMAIL,VALID_USER_PASS)
                     .checkIsRedirectedToDomainSelectPage()
                .loadWorkspace()
        ;
    }
}
