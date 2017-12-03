import Pages.IpsMainPage;
import Pages.PltMainPage;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.openqa.selenium.By;

import java.util.List;

import static MethodsCommon.Driver.driver;
import static MethodsCommon.Func.*;
import static org.junit.Assert.assertEquals;

public class OpenModulesSituations {

    IpsMainPage mPage = new IpsMainPage(driver);

    @Step("Go IPS Configuration <config>")
    public void  entranceIPS(String config) throws Throwable {
        PltMainPage pltMainPage = new PltMainPage(driver);
        IpsMainPage ipsPage = new IpsMainPage(driver);

        selectNewWindow(By.xpath(pltMainPage.getStrBlockConfig().replace("{nameConfig}", config)));
        assertEquals("ЗАКОНОДАТЕЛЬСТВО УКРАИНЫ", ipsPage.getFirstModule().getText());
        assertEquals(config.toUpperCase(), ipsPage.getTxtConfigName().getText());
    }

    @Step("Go product <>")
    public void entranceProduct(String nameProduct) throws InterruptedException {
        PltMainPage pltMainPage = new PltMainPage(driver);
        selectNewWindow(By.xpath(pltMainPage.getStrProduct().replace("{np}", nameProduct)));
    }

    @Step("Open module <NameModule>")
    public void openModuleIPS(String NameModule) throws InterruptedException {
        mPage.openModulesIPS(NameModule);
    }

    @Step("Open situation, search in module with check after sort list on and open document <table>")
    public void openSituation(Table table) throws Exception {
        IpsMainPage mPage = new IpsMainPage(driver);
        // Проверяем переход с каталога, поиск, открытие документов и варианты сортировок до поиск и после в пределах ситуаций
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            mPage.openSituationIPS(row.getCell(columnNames.get(0)), row.getCell(columnNames.get(1)), row.getCell(columnNames.get(2)));
            mPage.getLnkBackWithSituation().click(); // Выход из ситуации на страницу с модулями
            mPage.searchInnerSituation(row.getCell(columnNames.get(1)), row.getCell(columnNames.get(3)));
            mPage.openDocumentModule(row.getCell(columnNames.get(0)));
            mPage.getLnkMainPage().click(); // Переход на главную
        }
    }

    @Step("Open module, search in module with check after sort list on and open document <table>")
    public void openModule(Table table) throws InterruptedException {
        IpsMainPage mPage = new IpsMainPage(driver);
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            mPage.openModulesIPS(row.getCell(columnNames.get(0)));
            //
               if(row.getCell(columnNames.get(0)).contains("Законодательство Украины")){
                   String nMenuBefore = mPage.getTxtElementLeftMenu().get(1).getText();
                   mPage.getElLeftInnerModule(row.getCell(columnNames.get(1))).click();
                   mPage.WaitCountAnyList(nMenuBefore, mPage.getTxtElementLeftMenu().get(1));
//                   Thread.sleep(2000);
//
//                   waitElement(mPage.getLnkLevelUp());
                   sortNameList(mPage.getTxtElementLeftMenu(), false);
               }
            //
            mPage.searchInnerModule(row.getCell(columnNames.get(0)), row.getCell(columnNames.get(1)), row.getCell(columnNames.get(2)));
            mPage.openDocumentModule(row.getCell(columnNames.get(0)));
            mPage.getLnkBackWithModule().click();
        }
    }
}
