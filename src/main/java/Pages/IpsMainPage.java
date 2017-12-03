package Pages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static MethodsCommon.Func.*;

public class IpsMainPage {

    private final WebDriver driver;

    public IpsMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    JavascriptExecutor js;

    private String totalBefore, totalAfter, parrent, vSortBefore;
    private static String nModule;
    int i=1, c=1;

    @FindBy(xpath = "//a//h4[contains(text(), 'Помощь')]")
    private WebElement lnkListHelp;

    @FindBy(css = "a i.fa.fa-user.lz-fa")
    private WebElement lnkUserMenu;

    @FindBy(css = "input[id=search-open]")
    private WebElement f_search;

    @FindBy(id = "situationBusiness")
    private WebElement moduleSituationBiz;

    @FindBy(xpath = "//div[@class='container']//form[starts-with(@class, 'ng-pristine')]//input[@ng-model='searchValue']")
    private WebElement fldSituationSearch;

    @FindBy(css = ".wrap-item.ng-scope a")
    private WebElement linkDoc;

    @FindBy(css = ".item.open a")
    private WebElement lnkLeftTree;

    @FindBy(css = ".mainTag p")
    private WebElement txtOpenDoc;

    @FindBy(className = "snippet-text")
    private WebElement txtOpenDocSnippetInSituation;

    @FindBy(css = ".header-secondary-lz-back i")
    private WebElement lnkBackWithModule;

    @FindBy(css = ".fa.fa-angle-left")
    private WebElement lnkBackWithSituation;

    @FindBy(xpath = "//span[@class='dropdown-toggle']//span[@class='ng-binding']")
    private WebElement v_sortlist;

    @FindBy(css = ".lz-container-right div.count")
    private WebElement countDocs;

    @FindBy(css = ".total-results")
    private WebElement gTotalResult;

    @FindBy(css = ".lz-header-secondary-inner.container h1")
    private WebElement headerModule;

    @FindBy(css = ".fa.fa-home")
    private WebElement lnkMainPage;

    @FindBy(xpath = "//div[starts-with(@class, 'type-item all row')]//span[starts-with(@class, 'title')]")
    private WebElement firstModule;

    @FindBy(css = ".greedy-nav input")
    private WebElement inputForSearch;

    @FindBy(xpath = "//ul[starts-with(@class, 'dropdown-menu')]//a[starts-with(@class, 'ng-binding')]")
    private WebElement titleAfterSearch;

    @FindBy(className = "fa-angle-right")
    private WebElement lnkRightArrow;

    @FindBy(className = "fa-angle-left")
    private WebElement lnkLeftArrow;

    @FindBy(css = "lz-message.lz-message span")
    private WebElement txtErrorMessage;

    @FindBy(css = ".search-button span")
    private WebElement btnSearch;                        // Кнопка контексного поискана главной

    @FindBy(css = ".filter-button-inactive.searchType-lg")
    private WebElement btnScopeSearch;                   // Кнопка областей поиска

    @FindBy(className = "search-categories-items")
    private List<WebElement> nScopeSearch;               // Кнопка контексного поискана главной

    @FindBy(className = "reset-button")
    private WebElement btnResetSearch;                   // Кнопка контексного поиска на главной

    @FindBy(css = ".check-box-strict i")
    private WebElement cBoxExactSearch;                  // ЧекБокс для точного поиска

    @FindBy(css = "fa.fa-square-o.check-false.check-true")
    private WebElement boolExactSearch;                  // Флаг для точного поиска

    @FindBy(css = ".title span")
    private WebElement lbTitleList;                      // Название списка после контекстного поиска

    @FindBy(css = ".button-search i")
    private WebElement btnSearchSituation;

    @FindBy(className = "conf-name")
    private WebElement txtConfigName;

    @FindBy(className = "snippet")
    private List<WebElement> lstDocsAfterSearch;

    @FindBy(className = "name")
    private WebElement lstElement;                         // Элемент в списке после поиска

    @FindBy(css = ".item.ng-binding")
    private WebElement txtNameDocLeftDoc;

    @FindBy(css = ".item.open a")
    private List<WebElement> txtElementLeftMenu;                 // Список в леовом меню внутри модуля

    @FindBy(className = "fa fa-level-up")
    private WebElement lnkLevelUp;


    // String
    //private String strTxtSituation = "//div[starts-with(@class, 'box')]//div[contains(text(), '{nm}')]";
    //private String strElLeftInnerModule = "//ul[starts-with(@class, 'lz-nav')]//a[contains(text(), 'nm')]";
    private static final String strElLeftInnerModule = "//ul[starts-with(@class, 'lz-nav')]//a[contains(text(), 'nm')]";
    private String strTxtModule = "//div[starts-with(@class, 'box')]//span[contains(text(), '{nm}')]";
    public String strTabListAfterSearch = "//div[starts-with(@class, 'category-tabs')]//div[contains(text(), '{nm}')]";
    private String lnkCatalogInnerSituation = "//div[starts-with(@class, 'item-folder-title')]//div[contains(text(), '{nc}')]";

    static Log log = LogFactory.getLog(IpsMainPage.class);

    public WebElement getTotalResult() { return gTotalResult; }
    public WebElement getLstElement() { return lstElement; }
    public WebElement getInputForSearch() { return inputForSearch; }
    public WebElement getTitleAfterSearch() { return titleAfterSearch; }
    public WebElement getLnkBackWithSituation() { return lnkBackWithSituation; }
    public WebElement getLnkMainPage() { return lnkMainPage; }
    public WebElement getLnkBackWithModule() { return lnkBackWithModule; }

    public WebElement getBtnSearch() { return btnSearch; }
    public WebElement getBtnScopeSearch() { return btnScopeSearch; }
    public List<WebElement> getnScopeSearch() { return nScopeSearch; }
    public WebElement getBtnResetSearch() { return btnResetSearch; }
    public WebElement getLbTitleListContextSearch() { return lbTitleList; }
    public WebElement getLnkRightArrow() { return lnkRightArrow; }
    public WebElement getTxtConfigName() { return txtConfigName; }
    public WebElement getFirstModule() { return firstModule; }
    public WebElement getTxtNameDocLeftDoc() { return txtNameDocLeftDoc; }

    public WebElement getElLeftInnerModule(String nElement){
        return driver.findElement(By.xpath(strElLeftInnerModule.toString().replace("nm", nElement))); }
    public List<WebElement> getTxtElementLeftMenu() { return txtElementLeftMenu; }
    public WebElement getLnkLevelUp() { return lnkLevelUp; }

    public void contextSearch(String phrases, boolean ex) throws InterruptedException {
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;
        }
        js.executeScript("window.angular.element('.input-block').scope().search = ''");
        js.executeScript("window.angular.element('.input-block').scope().search = \"" + phrases + "\";");
        //js.executeScript("window.angular.element('.search-button span').trigger('click')");
        waitElement(lstElement);
        if(ex){
              do{
                  cBoxExactSearch.click();
                  totalBefore = cBoxExactSearch.getAttribute("class");
              }while(!totalBefore.contains("fa fa-square-o check-false check-true"));
            totalBefore = getTotalResult().getText();
            if(btnSearch.isDisplayed())
                btnSearch.click();
            WaitCountAnyList(totalBefore, gTotalResult);

            List<WebElement> lstElement = lstDocsAfterSearch;
            Iterator<WebElement> itr = lstElement.iterator();
            while(itr.hasNext()) {
                if(!itr.next().getText().toLowerCase().contains(phrases.toLowerCase())){
                    throw new InterruptedException("Snippet - "+itr.next().getText().toLowerCase()+" don`t compare with phrases - "+phrases);
                }
            }
        }else
            if(btnSearch.isDisplayed()) btnSearch.click();
    }

    public String openDocWithListAfterContextSearch(Boolean closeW) throws InterruptedException {
        totalBefore = lstElement.getText();
        HashMap<String, String> values = selectNewWindow(lstElement);
        checkOpenDocument();
        if(closeW){
            driver.switchTo().window(values.get("nWindow")).close();
            driver.switchTo().window(values.get("pWindow"));
        }
        return totalBefore;
    }

    public IpsMainPage searchInnerModule(String nameModule, String strSearch, String v_sort) throws InterruptedException {
        vSortBefore = v_sortlist.getText();
        totalBefore = countDocs.getText();
        if (strSearch.contains("Законы в тезисах") || strSearch.contains("Опубликование в периодике"))
            getTxtElementLeftMenu().get(1).click();

        checkErrorMessage();
        f_search.sendKeys(strSearch);

        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;
        }
        js.executeScript("window.angular.element('.button-search i').trigger('click')");

        if(!nameModule.contains("Отрасли экономики") && !nameModule.contains("Законодательство Украины") && !strSearch.equals(""))
            WaitCountAnyList(vSortBefore, v_sortlist);
        assertEquals(v_sort, v_sortlist.getText());
        if(!strSearch.equals(""))
          WaitCountAnyList(totalBefore, countDocs);
        if(!waitElement(txtOpenDocSnippetInSituation) && !nameModule.contains("Типовые договоры и шаблоны")
                && !nameModule.contains("Законодательство Украины"))
            throw new InterruptedException("No found snippet in list within module - "+nameModule);
        return this;
    }

    public IpsMainPage searchInnerSituation(String strSearch, String sort) throws Exception {
        try {
            vSortBefore = v_sortlist.getText();
            fldSituationSearch.sendKeys(strSearch);
        }catch (RuntimeException e){
            if(e instanceof NoSuchElementException)
                throw new Exception(" NOT FOUND ELEMENT -" + e.getMessage());
            if(e instanceof StaleElementReferenceException)
                fldSituationSearch.sendKeys(strSearch);
        }
        btnSearchSituation.click();
        WaitCountAnyList(vSortBefore, v_sortlist);
        assertEquals(sort, v_sortlist.getText());
        return this;
    }

    public IpsMainPage openDocumentModule(String nModule) throws InterruptedException {
        HashMap<String, String> values = selectNewWindow(linkDoc);
        try{
            //checkErrorMessage();
            waitElement(txtOpenDoc);
        }catch (RuntimeException e) {
            if(e instanceof NoSuchElementException && !waitElement(txtOpenDocSnippetInSituation))
                 throw new InterruptedException("Opened document is empty (usually white page) in module - "+ nModule + e.getMessage());
        }
        driver.switchTo().window(values.get("nWindow")).close();
        driver.switchTo().window(values.get("pWindow"));
        return this;
    }

    @Deprecated
    public IpsMainPage checkOpenDocument() throws InterruptedException {
        try {
            txtOpenDoc.isDisplayed();
        } catch (RuntimeException e) {
            if (e.toString().contains("NoSuchElementException"))
                throw new InterruptedException("Opened document is empty (usually white page) or not found document"+ e.getMessage());
        }
        return this;
    }

    public IpsMainPage openModulesIPS(String nmodule) throws InterruptedException {
        try {
            byClickElement(By.xpath(strTxtModule.replace("{nm}", nmodule)));
            nModule = headerModule.getText();
            if (nmodule.equals("Cудебные прецеденты"))
                assertEquals("Судебные прецеденты", nModule);
            else
                assertTrue(nModule.contains(nmodule));
        } catch (RuntimeException e) {
            if (e.toString().contains("NoSuchElementException"))
                throw new InterruptedException("COULDN`T OPEN MODULES - " + nmodule+e.getMessage());
        }
        return this;
    }

    public IpsMainPage openSituationIPS(String nameModule, String nameCatalog, String sort) throws Exception {
        try {
            if(nameModule.equals("для бизнеса")) moduleSituationBiz.click();
            else
                byClickElement(By.xpath(strTxtModule.replace("{nm}", nameModule)));
            byClickElement(By.xpath(lnkCatalogInnerSituation.replace("{nc}", nameCatalog)));
            assertEquals(sort, v_sortlist.getText());
        } catch (RuntimeException e) {
            if (e.toString().contains("NoSuchElementException"))
                throw new InterruptedException("COULDN'T OPEN SITUATION - " + e.getMessage());
        }
        return null;
    }

    public IpsMainPage checkErrorMessage() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MICROSECONDS);
        final Wait<WebDriver> waitElement = new WebDriverWait(driver, 1);
        try {
            WebElement we = waitElement.until(ExpectedConditions.visibilityOf(txtErrorMessage));
            totalBefore = txtErrorMessage.getText();
            throw new InterruptedException(totalBefore);
        } catch (TimeoutException ignored) {
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        return this;
    }

//    public String WaitCountAnyList(String before, WebElement sElement) throws InterruptedException {
//               totalAfter = sElement.getText();
//               while (totalAfter.equals(before)) {
//                   i++;
//                   if (i > 1200 && totalAfter.equals(before))
//                       throw new InterruptedException("Counter not updated");
//                   totalAfter = WaitCountAnyList(before, sElement);break;
//               }
//       return totalAfter;
//    }

    public String WaitCountAnyList(String before, WebElement sElement) throws InterruptedException {
               totalAfter = sElement.getText();
               while (totalAfter.equals(before)) {
                   i++;
                   if (i > 1200 && totalAfter.equals(before))
                       throw new InterruptedException("Counter not updated");
                   //totalAfter = WaitCountAnyList(before, sElement);
                   new WebDriverWait(driver, 5)
                           .ignoring(StaleElementReferenceException.class, InvocationTargetException.class)
                           .until((WebDriver d) -> {
                               totalAfter = sElement.getText();
                               return true;
                           });
               }
           return totalAfter;
    }

    public String WaitCountAnyList(String before, By by) throws InterruptedException {
        totalAfter = byGetTextElement(by);
        while (totalAfter.equals(totalBefore)) {
            totalAfter = WaitCountAnyList(before, by);
        }
        return totalAfter;
    }
}
