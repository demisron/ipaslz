import Pages.IpsMainPage;
import com.thoughtworks.gauge.Step;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Set;

import static MethodsCommon.Driver.driver;
import static MethodsCommon.Func.*;
import static java.lang.String.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by FedorchukR on 17.03.2017.
 */

public class BallsOnMainPage {

    @FindBy(xpath = "//div[@class='theses-search']//span[starts-with(@class, 'input-group-addon')]/i")
    private WebElement btnSearch;

    @FindBy(css = ".dropdown-icon.fa.fa-chevron-down")
    private WebElement lnkSwitchFieldSearch;

    @FindBy(xpath = "//ul//span[contains(text(), 'Лента событий')]")
    private WebElement txtFieldSearch;

    @FindBy(xpath = "//div[@class='theses-search']//div[starts-with(@class, 'input-group')]/input")
    private WebElement inputFieldSearchL;

    private static Long CModule, CBallBalls, CNews;
    private static String strClickBalls = "//div[@class='balls']//div[@class='btn-downup']/i", strAny, link_after, title_after;
    private static String strListTitle = "//div[@class='ul ng-scope active']//div[@class='li ng-scope'][position()=icount]/div[@class='title']//span";
    private static String strListNews = "//div[@id='themesConteiner']//div[starts-with(@class, 'wrap-ball-theme')][position()=${i}]//a//p[@class='tj bmf']";
    String title_before;

    // Text
    //private static final By ITEM_TXT = By.xpath("//div[starts-with(@class, 'item-field')]/div[starts-with(@class, 'item ng-binding')]");
    private static final By ITEM_TXT = By.cssSelector(".item.ng-binding");

    // Links
    private static final By OPEN_SLIDE = By.xpath("//div[@class='slider-title']//b");
    private static final By THESSES_TITLE = By.xpath("//div[@class='ul ng-scope active']//div[@class='li ng-scope active']//span");
    private static final By THESSES_LINK = By.xpath("//div[@class='ul ng-scope active']//div[@class='li ng-scope active']//div[@class='mainTag']//p");
    private static final By CRAB_LINK = By.xpath("//div[starts-with(@class, 'ul ng-scope active')]//div[starts-with(@class, 'li ng-scope active')]//div[starts-with(@class, 'title')]/a/i");
    private static final By NEWS_LNK = By.xpath("//div[@class='theses-from']//div[starts-with(@class, 'from-title')]");
    private static final By LAW_LNK = By.xpath("//div[@class='theses-status']//div[starts-with(@class, 'from-info')]");

    // Button
    private static final By BACK_BUTTON = By.cssSelector(".back-button i");

    JavascriptExecutor js;
    Log log = LogFactory.getLog(BallsOnMainPage.class);
    HashMap<String, String> values = new HashMap<>();
    IpsMainPage mainpage = new IpsMainPage(MethodsCommon.Driver.driver);

    @Step("Switching to all balls <downup> and check content theses and links")
    public void switchBalls(String downup) throws InterruptedException {
        if (!visibleElement(THESSES_TITLE)) {
            byClickElement(OPEN_SLIDE);
            waitElement(THESSES_LINK);

            if (driver instanceof JavascriptExecutor) {
                js = (JavascriptExecutor) driver;
            } // else throw...

            CModule = (Long) js.executeScript("return window.angular.element('.balls li').length");
        }

        for (int c = 1; c < CModule; c++) {
            title_before = byGetTextElement(THESSES_TITLE);
            byClickElement(By.xpath(strClickBalls.replace("downup", downup)));
            title_after = byGetTextElement(THESSES_TITLE);

            if (title_after.contains("Исполнительное производство")) {
                byClickElement(CRAB_LINK);
                assertEquals(byGetTextElement(ITEM_TXT), title_after);
                //final Set<String> oldWindowsSet1 = driver.getWindowHandles();
                //assertEquals("Don't match", getTxtNameDocLeftDoc().getText(), title_after);
                mainpage.openDocWithListAfterContextSearch(true);
                clickMainPage();
            }
            assertNotEquals(title_before, title_after);
            if (c == 3) {
                CBallBalls = (Long) js.executeScript("return window.angular.element('.list .ul.ng-scope.active .li.ng-scope').length");
                for (int i = 1; i < CBallBalls; i++) {
                    strAny = strListTitle.replace("icount", "" + i + "");
                    String link_before = byGetTextElement(THESSES_LINK);
                    byClickElement(By.xpath(strAny));
                    link_after = byGetTextElement(THESSES_LINK);
                    //assertNotEquals(link_before, link_after);
                }
                byClickElement(THESSES_LINK);

                waitContains(link_after);
                values = selectNewWindow(NEWS_LNK);
                //HashMap<String, String> val = fn.SelectFNewWindow(lnkNews);
                mainpage.checkOpenDocument();
                driver.switchTo().window(values.get("nWindow")).close();
                driver.switchTo().window(values.get("pWindow"));

                values = selectNewWindow(LAW_LNK);
                //values = fn.SelectFNewWindow(lnkLaw);
                mainpage.checkOpenDocument();
                driver.switchTo().window(values.get("nWindow")).close();
                driver.switchTo().window(values.get("pWindow"));
                byClickElement(BACK_BUTTON);
            }
        }
    }

    @Step("Search within balls <strSearch>")
    public void Search_within_balls(String strSearch) throws InterruptedException {
        BallsOnMainPage ballspage = PageFactory.initElements(driver, BallsOnMainPage.class);
        title_before = byGetTextElement(THESSES_TITLE);

        ballspage.inputFieldSearchL.sendKeys(strSearch);
        js.executeScript("window.scrollBy(1, -1000)");
        ballspage.btnSearch.click();
        title_after = byGetTextElement(THESSES_TITLE);
        assertNotEquals(title_before, title_after);

        ballspage.lnkSwitchFieldSearch.click();
        ballspage.txtFieldSearch.click();
        ballspage.inputFieldSearchL.sendKeys(strSearch);
        ballspage.btnSearch.click();

        CNews = (Long) js.executeScript("return window.angular.element('#themesConteiner div.wrap-ball-theme').length");

        for (int c = 1; c <= CNews; c++) {
            String count = valueOf(c);
            strAny = byGetTextElement(By.xpath(strListNews.replace("${i}", count)));
            if (!strAny.contains(strSearch)) {
                throw new InterruptedException("Ссылка с название - " + strAny + " - НЕ СОДЕРЖИТ ТЕКСТ - " + strSearch);
            }
        }
    }
}

