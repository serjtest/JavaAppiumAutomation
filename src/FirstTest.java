import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FirstTest extends CoreTestCase {

    @Test
    public void testSearchWordAndCancel()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "quality assurance",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Quality assurance']"),
                "Cannot find 'Quality assurance'",
                15
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Quality Assurance Agency for Higher Education']"),
                "Cannot find 'Quality Assurance Agency for Higher Education' ",
                15
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Quality Assurance International']"),
                "Cannot find 'Quality Assurance International' ",
                15
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'Cancel Search' input",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }

    @Test
    public void testSearchWordAndCheck()
    {
        String someWord = "Film";
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                someWord,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Film']"),
                "Cannot find word",
                15
        );
    }





    @Test
    public void testSaveTwoArticles()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String name_of_first_article = "Java";
        String name_of_second_article = "Appium";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                name_of_first_article,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + name_of_first_article,
                15
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title topic ",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "Cannot find button to open article options",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find button to add article to reading list",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'GO IT' button ",
                5
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        String name_of_folder = "Some folder";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                name_of_second_article,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='"+ name_of_second_article +"']"),
                "Cannot find topic searching by " + name_of_second_article,
                15
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title topic ",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "Cannot find button to open article options",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find button to add article to reading list",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"), //*[@text'"+ name_of_folder +"']
                "Cannot find folder name " + name_of_folder,
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button 'My lists'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='"+ name_of_folder +"']"),
                "Cannot find created folder",
                10
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='"+ name_of_second_article +"']"),
                "Cannot find article " + name_of_second_article,
                7
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='"+ name_of_second_article +"']"),
                //By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']//*[@text='"+ name_of_second_article +"']"),
                "Cannot find topic searching by " + name_of_second_article,
                15
        );

        WebElement title_element = MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title topic ",
                15
        );

        String title_of_second_article = title_element.getAttribute("text");

        assertEquals(
                "We see unexpected title!",
                name_of_second_article,
                title_of_second_article
        );

    }

    @Test
    public void testAssertTitle()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String name_of_article = "Java";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                name_of_article,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + name_of_article,
                15
        );

        String search_title_locator = "org.wikipedia:id/view_page_title_text";

        MainPageObject.assertElementPresent(
                By.id(search_title_locator),
                "Cannot find title by" + search_title_locator
        );
    }


}