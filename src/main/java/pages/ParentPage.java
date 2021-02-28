package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public abstract class ParentPage {
    //protected final String baseUrl = "https://airslate-dev07.xyz"
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10, webDriverWait15;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    protected final String baseUrl = configProperties.base_url();

    Logger logger = Logger.getLogger(getClass());
    public ParentPage(WebDriver webDriver){
        this.webDriver = webDriver;
        // PageFactory.initElements(webDriver, this);

        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                ,this);
        webDriverWait10 = new WebDriverWait(webDriver,10);
        webDriverWait15 = new WebDriverWait(webDriver,15);
    }

    abstract String getRelativeUrl ();

    private void printErrorMessageAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }


    private String getElementName(WebElement webElement) {
        String elementName = "";
        if(webElement instanceof TypifiedElement){
            elementName = (" '" + ((TypifiedElement) webElement).getName() + "' ");
        }
        return elementName;
    }

    public void enterTextIntoElement(WebElement webElement, String text){
        try{
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into the" +getElementName(webElement));
        }catch (Exception e){
            printErrorMessageAndStopTest(e);
        }

    }

    public void clickOnElement(WebElement webElement){
        try{
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(getElementName(webElement)+ " was clicked");
        }catch (Exception e){
            printErrorMessageAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement webElement){
        try{
            boolean state = webElement.isDisplayed();
            logger.info(getElementName(webElement) + " was displayed");
            return state;
        }catch (Exception e){
            logger.info("ERROR : Attempt to display " + getElementName(webElement) + "FAILED");
            return false;
        }
    }

    protected void isElementVisible(WebElement webElement){
        Assert.assertTrue(getElementName(webElement) + " is not visible", isElementDisplayed(webElement));
    }

    protected void selectTextInDropdown(WebElement webElement, String text){
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in " + getElementName(webElement));
        }catch (Exception e){
            printErrorMessageAndStopTest(e);
        }
    }

}
