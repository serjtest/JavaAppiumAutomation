import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/Serj/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                 15
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java' ",
                15
        );
    }

    @Test
    public void testCancelSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                15
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                10
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'Cancel Search' input",
                5
        );

        waitForElementNotPrecent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }


    @Test
    public void  testCompareArticleTitle()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java' ",
                5
        );

        WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title topic ",
                15
        );


        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title!",
                "Java (programming language)",
                article_title
        );

    }

    @Test
    public void testSearchWordAndCancel()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "quality assurance",
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Quality assurance']"),
                "Cannot find 'Quality assurance'",
                15
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Quality Assurance Agency for Higher Education']"),
                "Cannot find 'Quality Assurance Agency for Higher Education' ",
                15
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Quality Assurance International']"),
                "Cannot find 'Quality Assurance International' ",
                15
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                10
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'Cancel Search' input",
                5
        );

        waitForElementNotPrecent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }


    private WebElement waitForElementPresent(By by, String error_massage, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_massage)
    {
        return waitForElementPresent(by, error_massage, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return  element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return  element;
    }


    private boolean waitForElementNotPrecent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementAndClick(by, error_message, timeoutInSeconds);
        element.clear();
        return element;

    }

    private WebElement waitForElementAndCheckText(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementAndClick(by, error_message, timeoutInSeconds);
        String search_text = element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title!",
                "Search…",
                search_text
        );
        return element;
    }

}