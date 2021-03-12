package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.Matchers.containsString;

public class PricingPage extends ParentPage{

    @FindBy(xpath = ".//*[ @data-plan-name=\"free\"]//button[@type='button' and contains(text(), 'Choose plan')]")
    private WebElement basicPlan;

    public PricingPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/pricing?fromHeader=1";
    }

    @Override
    String getFirstPartOfRelativeUrl() {
        return null;
    }

    @Step
    public PricingPage checkIsRedirectToPricingPage(){
        //webDriverWait10.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By) mainMenu));
        Assert.assertThat("Invalid Page",webDriver.getCurrentUrl(), containsString(getRelativeUrl()));
        return this;
    }

    @Step
    public void checkRedirectionInBasicPlan(){
        clickOnElement(basicPlan);
    }
}
