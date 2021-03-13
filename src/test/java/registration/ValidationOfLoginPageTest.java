package registration;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationOfLoginPageTest extends BaseTest {
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

}
