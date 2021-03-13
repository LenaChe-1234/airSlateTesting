package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.Matchers.containsString;

public class BusinessCloudPage extends ParentPage{

    public BusinessCloudPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/business-cloud";
    }

    @Override
    String getFirstPartOfRelativeUrl() {
        return null;
    }

    @Step
    public BusinessCloudPage checkIsRedirectToBusinessCloudPage(){
        //webDriverWait10.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By) mainMenu));
        Assert.assertThat("Invalid Page",webDriver.getCurrentUrl(), containsString(getRelativeUrl()));
        return this;
    }

}
