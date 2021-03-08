package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.Matchers.containsString;

public class LandingPage extends ParentPage{
    @FindBy (xpath =".//*[@aria-label = 'Main Site Menu']")
    private WebElement mainMenu;

    public LandingPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Override
    String getFirstPartOfRelativeUrl() {
        return null;
    }

//    public LandingPage checkIsRedirectToEditPostPage(){
//        webDriverWait10.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By) mainMenu));
//        Assert.assertThat("Invalid Page",webDriver.getCurrentUrl(), containsString(getRelativeUrl()));
//        return this;
//    }


}
