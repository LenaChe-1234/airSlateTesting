package pages;

import org.openqa.selenium.WebDriver;

public class AcademyPage extends ParentPage{
    public AcademyPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/academy";
    }

    @Override
    String getFirstPartOfRelativeUrl() {
        return null;
    }
}
