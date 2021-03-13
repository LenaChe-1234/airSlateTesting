package baseTest;

import io.qameta.allure.Attachment;
import libs.ConfigProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pages.DomainSelectPage;
import pages.LoginPage;
import pages.WorkspacePage;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static pages.ParentPage.configProperties;

public class BaseTest {

    public WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected WorkspacePage workspacePage;
    protected DomainSelectPage domainSelectPage;

    @Rule
    public TestName testName = new TestName();
    @Before
    public void setUp() throws Exception {
        logger.info("-------" + testName.getMethodName() + " was started ------");

//            for mac OS
//            File fileFF = new File("./drivers/chromedriver");
//
//            for Win
//          File fileFF = new File("drivers/chromedriver.exe");
//          System.setProperty("webdriver.chrome.driver",fileFF.getAbsolutePath());
//          webDriver = new ChromeDriver();

        webDriver = initDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(configProperties.TIME_FOR_DFFAULT_WAIT(), TimeUnit.SECONDS);
        logger.info("Browser was opened");

        loginPage = new LoginPage(webDriver);
        workspacePage = new WorkspacePage(webDriver);
        domainSelectPage = new DomainSelectPage(webDriver);


    }
    
    private WebDriver initDriver() throws Exception {
        String browser = System.getProperty("browser");
        if ((browser == null) || ("chrome".equalsIgnoreCase(browser))){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }else if("firefox".equalsIgnoreCase(browser)){
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }else  if ("ie".equalsIgnoreCase(browser)){
            WebDriverManager.iedriver().arch32().setup();
            return new InternetExplorerDriver();
        } else {
            throw new Exception("Check browser var");
        }
    }

    @After
//    public void tearDown(){
//        logger.info("-------" + testName.getMethodName() + " was ended ------");
//        webDriver.quit();
//        logger.info("Browser was closed");
//    }

    public void tearDown(){

        logger.info("Browser was closed");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            screenshot();
        }
        @Attachment(value = "Page screenshot", type = "image/png")
        public byte[] saveScreenshot(byte[] screenShot) {
            return screenShot;
        }
        public void screenshot() {
            if (webDriver == null) {
                logger.info("Driver for screenshot not found");
                return;
            }
            saveScreenshot(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES));
        }
        @Override
        protected void finished(Description description) {
            logger.info(String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
            try {
                webDriver.quit();
                logger.info("Browser was closed");
            } catch (Exception e) {
                logger.error(e);
            }
        }
    };

    protected void checkExpectedResult(String message, boolean actualResult){
        Assert.assertTrue(message, actualResult);
    }



}
