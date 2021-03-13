package pages;

import org.openqa.selenium.WebDriver;

public class BotsPage extends ParentPage{

    public BotsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/bots";
    }

    @Override
    String getFirstPartOfRelativeUrl() {
        return "";
    }
}
