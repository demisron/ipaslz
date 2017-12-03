import MethodsCommon.MethodsCommon;
import Pages.MenuBlueButtonPage;
import Pages.PersonalCabinetPage;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

import static MethodsCommon.Driver.driver;
import static MethodsCommon.Func.navigateToUrl;

public class DocEntryProcedure extends MethodsCommon {
    private static MenuBlueButtonPage mBlueButton = new MenuBlueButtonPage(driver);
    private static SearchPhrasesCheckSuggest srchPhrases = new SearchPhrasesCheckSuggest();
    Log log = LogFactory.getLog(PersonalCabinetPage.class);
    private static final String DOC_ENTRY_PROC = "Порядок вступления в силу:";

    @Step("Check DocEntryProcedure info is displayed in About doc panel <Table>")
     /*Шаги (для каждого значения из строк таблицы):
    1. Перейти на главную платформы.
    2. Открыть страницу поиска ИПС.
    3. Выполнить точный поиск по фрагменту из названия документа - открыть документ.
    4. В синей кнопке меню нажать пукнт "О документе".
    ОР: В панельке "О документе" отображается поле "Порядок вступления в силу"
    5. Закрыть вкладку документа - вернуться к первой открытой вкладке.*/
    public void docEntryIsDisplay(Table table) throws InterruptedException {
        String ipsTab = driver.getWindowHandle();
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();

        for (TableRow row : rows) {
            navigateToUrl();
            srchPhrases.EntranceIPSSearch();
            srchPhrases.openDocAfterContextSearch(row.getCell(columnNames.get(0)), true);//первый столбец
            mBlueButton.openAboutDocMenu();
            shouldSeeElementWithText(mBlueButton.getDocEntryProcName(), DOC_ENTRY_PROC);
            shouldSeeElement(mBlueButton.getDocEntryProcText());
            driver.close();
            driver.switchTo().window(ipsTab);

        }
    }

    @Step("Check DocEntryProcedure info is NOT displayed in About doc panel <Table>")
     /*Шаги (для каждого значения из строк таблицы):
    1. Перейти на главную платформы.
    2. Открыть страницу поиска ИПС.
    3. Выполнить точный поиск по фрагменту из названия документа - открыть документ.
    4. В синей кнопке меню нажать пукнт "О документе".
    ОР: В панельке "О документе" НЕ отображается поле "Порядок вступления в силу"
    5. Закрыть вкладку документа - вернуться к первой открытой вкладке.*/
    public void docEntryIsNotDisplay(Table table) throws InterruptedException {
        String ipsTab = driver.getWindowHandle();
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();

        for (TableRow row : rows) {
            navigateToUrl();
            srchPhrases.EntranceIPSSearch();
            srchPhrases.openDocAfterContextSearch(row.getCell(columnNames.get(0)), true);//первый столбец
            mBlueButton.openAboutDocMenu();
            shouldNotSeeElement(mBlueButton.getDocEntry());
            driver.close();
            driver.switchTo().window(ipsTab);

        }
    }
}

