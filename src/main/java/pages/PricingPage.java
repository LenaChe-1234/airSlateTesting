package pages;

import org.openqa.selenium.WebDriver;

public class PricingPage extends ParentPage{


    public PricingPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/pricing?fromHeader=1";
    }
}
