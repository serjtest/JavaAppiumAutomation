package test;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String title_before_rotaition = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotaition = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotaition,
                title_after_rotaition
        );

        this.rotateScreenPortrait();
        String title_after_second_rotaition = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotaition,
                title_after_second_rotaition
        );
    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
