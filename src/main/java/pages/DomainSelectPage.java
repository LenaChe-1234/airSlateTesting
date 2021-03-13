package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DomainSelectPage extends ParentPage{
    @FindBy(xpath = ".//span[contains(@class, 'sl-button__text') and text()= 'Create a new Workspace']")
    private WebElement buttonCreateNewWorkspace;
    @FindBy (xpath = ".//span[contains(text(),'aqa-test1.airslate-dev07.xyz')]")
    private WebElement linkedDomainName;
    @FindBy (xpath = ".//span[text() = 'aqa-test1.airslate-dev07.xyz']")
    private WebElement workspaceLocator;

    public DomainSelectPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/domain-select";
    }

    @Override
    String getFirstPartOfRelativeUrl() {
        return "my.";
    }

    @Step
    public DomainSelectPage checkIsRedirectedToDomainSelectPage() {
        webDriverWait15.until(ExpectedConditions.visibilityOf(buttonCreateNewWorkspace));
    Assert.assertEquals("The domain-select page was loaded", "https://my.airslate-dev07.xyz/domain-select", webDriver.getCurrentUrl());
      logger.info(webDriver.getCurrentUrl());
       return this;
    }

    @Step
    public void selectDomainName(){
        webDriver.findElement((By) linkedDomainName).click();
    }

    @Step
    public WorkspacePage loadWorkspace(){
        clickOnElement(workspaceLocator);
        logger.info(" The page " + webDriver.getTitle() + " was loaded");
        return new WorkspacePage(webDriver);
    }



}
