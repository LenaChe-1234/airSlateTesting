package registration;

import baseTest.BaseTest;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
@Epic("Allure examples")
@Feature("Junit 4 support")
public class ValidationOfLoginPageTest extends BaseTest {
    @Description("Some detailed test description")
    @Story("Base support for bdd annotations")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void validationRegistrationSpaceSymbols()  {
        loginPage.openLoginPage();
        loginPage.enterUserEmail("invalid.emal.com");
        loginPage.enterUserPass("12");
        loginPage.clickButtonLogIn();
        loginPage.checkErrorsMessages("Enter a valid email address using the following format: myname@mail.com;Password must contain at least 6 characters");

    }

    @Test
    public void validationRegistrationShortPass()  {
        loginPage.openLoginPage();
        loginPage.enterUserEmail("  ");
        loginPage.enterUserPass("password with space symbols");
        loginPage.clickButtonLogIn();
        loginPage.checkErrorsMessages("Enter your email address;Space symbols is prohibited");

    }

    @Test
    @Parameters({
            "Enter your email address;Space symbols is prohibited,  , password with space symbols",
            "Enter a valid email address using the following format: myname@mail.com;Password must contain at least 6 characters,invalid.email.com, 12"
    })
    @TestCaseName("validationRegistration: errorMessages = {1}, email = {2}, pass = {3}")
    public  void validationRegistration(String errorMessages, String email, String pass){
        loginPage.openLoginPage();
        loginPage.enterUserEmail(email);
        loginPage.enterUserPass(pass);
        loginPage.clickButtonLogIn();
        loginPage.checkErrorsMessages(errorMessages);
    }

}
