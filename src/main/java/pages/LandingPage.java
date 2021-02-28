package pages;

import org.openqa.selenium.WebDriver;

public class LandingPage extends ParentPage{

    public LandingPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }
}
