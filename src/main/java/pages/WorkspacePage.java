package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WorkspacePage extends ParentPage{



    public WorkspacePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/workflows";
    }

    @Override
    String getFirstPartOfRelativeUrl() {
        return "aqa-test1.";
    }


}
