package baseTest;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());


    @Before
    public void setUp(){
        // for mac OS
        // File fileFF = new File("./drivers/chromedriver");
        // for Win
        File fileFF = new File(".drivers/chromedriver");
        System.setProperty("webdriver.chrome.driver",fileFF.getAbsolutePath());
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");


    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser was closed");
    }

    protected void checkExpectedResult(String message, boolean actualResult){
        Assert.assertTrue(message, actualResult);
    }

}
