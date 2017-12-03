import Pages.IpsMainPage;
import Pages.PltMainPage;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;

import static MethodsCommon.Driver.driver;
import static MethodsCommon.Func.byClickElement;
import static MethodsCommon.Func.waitElement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by FedorchukR on 28.08.2017.
 */
public class SearchPhrasesCheckSuggest {

    private String parrent, totalBefore, strAnyValueBefore="", strAnyValueAfter="";
    HashMap<String, String> values = new HashMap<>();
    final Wait<WebDriver> wait = new WebDriverWait(driver, 2, 100);
    IpsMainPage sPage = new IpsMainPage(driver);
    Log log = LogFactory.getLog(SearchPhrasesCheckSuggest.class);

    @Step("Go IPS Search")
    public void EntranceIPSSearch() throws InterruptedException {
        PltMainPage pltMainPage = new PltMainPage(driver);
        pltMainPage.getLnkContextSearch().click();
        waitElement(pltMainPage.getLstElement());
        parrent = driver.getWindowHandle();
    }

    @Step("Сontext search any phrases with switching tabs <>")
    public void context_search_any_phrases(Table table) throws InterruptedException {
        IpsMainPage sPage = new IpsMainPage(driver);
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            totalBefore = sPage.getTotalResult().getText();
            sPage.contextSearch(row.getCell(columnNames.get(0)), false);
            sPage.WaitCountAnyList(totalBefore, sPage.getTotalResult());

            sPage.openDocWithListAfterContextSearch(true);
        }
        for (TableRow row : rows) {
            sPage.contextSearch("", false);
            totalBefore = sPage.getTotalResult().getText();
            if (sPage.getBtnSearch().isDisplayed()) {
                    if ((row.getCell(columnNames.get(1)).equals("Издания")
                        || row.getCell(columnNames.get(1)).equals("Правовая картина дня")) && sPage.getLnkRightArrow().isDisplayed())
                    sPage.getLnkRightArrow().click();
                waitElement(By.xpath(sPage.strTabListAfterSearch.replace("{nm}", row.getCell(columnNames.get(1)))));
                byClickElement(By.xpath(sPage.strTabListAfterSearch.replace("{nm}", row.getCell(columnNames.get(1)))));
            }
            sPage.WaitCountAnyList(totalBefore, sPage.getTotalResult());
        }
    }

    @Step("Suggest after the context search <>")
    public void suggest_after_the_context_search(Table table) throws InterruptedException {
        //IpsMainPage sPage = new IpsMainPage(driver);
        // Заполняем поле поиска и ожидаем контент подсказок который указан в таблице
        IpsMainPage sPage = new IpsMainPage(driver);
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            sPage.getInputForSearch().sendKeys(row.getCell(columnNames.get(0)));
            wait.until(ExpectedConditions.visibilityOf(sPage.getTitleAfterSearch()));

            String suggest = sPage.getTitleAfterSearch().getAttribute("title");
            assertEquals(suggest, row.getCell(columnNames.get(1)));
            sPage.contextSearch("", false);
        }
    }

    @Step("Open found first document with list after context search phrases <> with exact <>")
    public void openDocAfterContextSearch(String phrases, boolean eSearch) throws InterruptedException {
        IpsMainPage sPage = new IpsMainPage(driver);

        driver.switchTo().window(parrent);
        totalBefore = sPage.getTotalResult().getText();
        sPage.contextSearch(phrases, eSearch); // Второй параметр в случае true, говорит о точном поиске
        sPage.WaitCountAnyList(totalBefore, sPage.getTotalResult());

        sPage.openDocWithListAfterContextSearch(false); // Открываем документ из списка, проверяем наличие контента
    }

    @Step("Open search area list <table>")
    public void openSearchAreaList(Table table) throws InterruptedException {
        // Проверяем открытие областей поиска
        IpsMainPage sPage = new IpsMainPage(driver);

        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        List<WebElement> nameScopeSearch = sPage.getnScopeSearch();

        int countM=0;
        sPage.getBtnScopeSearch().click();
        for (TableRow row : rows) {            // Проверка на отсутствие модуля в областях
            for(WebElement nSS : nameScopeSearch){
               if(nSS.getText().contains(row.getCell(columnNames.get(0))) && row.getCell(columnNames.get(1)).contains("false"))
                  throw new InterruptedException("In scope search should be absence module - "+row.getCell(columnNames.get(0)));

               if(!nSS.getText().contains(row.getCell(columnNames.get(0))) && row.getCell(columnNames.get(1)).contains("true")) countM++;
               if(nSS.getText().contains(row.getCell(columnNames.get(0)))){
                   countM=0;break;
               }
               if(countM==nameScopeSearch.size()) throw new InterruptedException("Module - "+row.getCell(columnNames.get(0))+" - is absence in list scope search");
            }
        }
        assertEquals("The number in the search scope does not match", rows.size()-1, nameScopeSearch.size());
        sPage.getBtnScopeSearch().click();
        for(WebElement nSS : nameScopeSearch) {  // Открытие каждого элемента в переделах областей поиска
            totalBefore = sPage.getTotalResult().getText(); // Проверка того, что списки разные
            sPage.getBtnScopeSearch().click();
            strAnyValueBefore = nSS.getText();

            nSS.click();
            assertEquals("Title list ->"+sPage.getLbTitleListContextSearch().getText()+"<- with module name ->"+strAnyValueBefore+"<- doesn't match" , strAnyValueBefore, sPage.getLbTitleListContextSearch().getText());
            sPage.WaitCountAnyList(totalBefore, sPage.getTotalResult());

            strAnyValueBefore = sPage.getLstElement().getText();
            assertNotEquals(strAnyValueBefore, strAnyValueAfter);
            strAnyValueAfter = sPage.getLstElement().getText();

            totalBefore = sPage.getTotalResult().getText();
            sPage.getBtnResetSearch().click();
            sPage.WaitCountAnyList(totalBefore, sPage.getTotalResult());
        }
        log.info("Check modules in scope - DONE");
    }
}
