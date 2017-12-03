import java.io.File;
import java.lang.String;
import java.util.HashMap;

import org.junit.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.*;
import com.thoughtworks.gauge.Step;
import org.openqa.selenium.*;

import static MethodsCommon.Func.*;
import static org.junit.Assert.*;
import static MethodsCommon.Driver.driver;

public class PermissionsAnonymous {

    private static final Integer ONEWAIT =1000;
    private Long CModule;
    Log log = LogFactory.getLog(PermissionsAnonymous.class);

    JavascriptExecutor js;

    // String
    private String strAnything, strAnyPath;
    private String StrForSearch = "налоговый кодекс Украины";
    private StringBuffer verificationErrors = new StringBuffer();

    //private String[] elementMenuDoc = {"word", "saveSession"};
    private String[] elementMenuDoc = {"Экспорт документа", "Cохранить вкладки"};
    private String[] elementFindDoc = {"Навигатор", "По тексту", "По реквизитам"};
    //private String strElementMenuDoc = "//div[@class='item-wrap elementMenuDoc']//span";
    private String strElementMenuDoc = "//span[contains(text(), 'elementMenuDoc')]";
    private String strElementAppXpath = "//new-index-products[starts-with(@class, 'container my-prod')]//div[@class='row prodParentBlock'][count]//div[@class='prodBlock']//div[starts-with(@class, 'my-prod-item')][icount]//div[@class='info']//h3[@class='ng-binding']";
    private String strElementFindDoc = "//div[@class='type-buttons']//div[contains(text(), 'elementFindDoc')]";
    //private String strModuleXpath = "//div[starts-with(@class, 'type-item  row')]//div[contains(@class,' button ')][position()=${number}]//div[@class='title ng-binding']";
    private String strModuleXpath = "//div[contains(@class, 'grand_excess')]//div[starts-with(@class, 'col-lg-2')][position()=${number}]//*[starts-with(@class, 'title')]";
    //private String strModuleXpath = "//div[starts-with(@class, 'conf-title')]";


    // Text String
    private static final String TXT_FOUND = "Необходимый текст на странице не найден";
    private static final String TXT_BUY_CONFIG = "Для полного доступа необходимо";
    //private static final String TXT_BUY_CONFIG = "Для полного доступа к этой функциональности необходимо купить лицензию информационно-правовой системы ЛІГА:ЗАКОН";
    //private static final String TXT_BUY_LICENCE = "Для полного доступа к этому продукту необходимо купить лицензию";
    private static final String TXT_BUY_LICENCE = "Для полного доступа необходимо";
    private static final String TXT_BUY_SMSM = "Получите доступ к этому сервису сразу после регистрации";
    private static final String TXT_BUY_FUNC = "Получите доступ к этой функциональности Платформы LIGA:ZAKON сразу после регистрации";

    // Links
    private static final By LIST_DOC = By.className("resource-list");
    private static final By LIST_BLOCK = By.xpath("//div[starts-with(@class, 'snippet-text')]//span[@class='snippet']");
    private static final By TXT_NAMEMODULE = By.xpath("//div[@class='lz-header-secondary-inner container']//h1");
    //private static final By LNK_IPS = By.cssSelector(".mainTag a");
    private static final By LNK_IPS = By.cssSelector(".tc.bmf a");

    // Button
    private static final By EXTERNAL_FIND_ID = By.id("search-submit");
    private static final By BTN_MENU = By.className("btn_cont");
    private static final By BTN_PRIME = By.xpath("//div[@class='six-block-col configprime_lpr']");
    private static final By ENTRANCE_EP = By.xpath("//a[starts-with(@class, 'config-price-button')]");
    private static final By ENTRANCE_WEBEP = By.xpath("//a[starts-with(@class, 'config-price-button')]");
    private static final By EXCEES_ANGLE = By.xpath("//div[contains(@class, 'angle')]//i[@class='fa fa-angle-down showButtIcon']");
    private static final By BTN_YES = By.xpath("//div[starts-with(@class, 'modal-footer')]//div[starts-with(@class, 'btn btn-primary')]");

    //private static Func fn = new Func();

    @Step("Navigate to main page")
    public void navigateTo() throws InterruptedException {
        navigateToUrl();
        log.info("Main page opened");
    }
    @Step("Set UKR-RUS language")
    public void chooseLang() throws InterruptedException {
        setUKRRUSLanguageAnonymous(2);
        log.info("Language - changed");

        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;
        } // else throw...
    }
    @Step("Check access denied to search")
    public void checkAccessSearch() throws InterruptedException {
        HashMap<String, String> values = selectNewWindow(LNK_IPS);
        for (int i = 0; i < elementFindDoc.length; i++) {
            byClickElement(By.xpath(strElementFindDoc.replace("elementFindDoc", elementFindDoc[i])));

            if (elementFindDoc[i] == "По реквизитам") {
                byClickElement(EXTERNAL_FIND_ID);
            }

            if (elementFindDoc[i] == "По тексту") {
                js.executeScript("window.angular.element('.input-block').scope().search = \'"+StrForSearch+"\';");
                js.executeScript("window.angular.element('.search-button span').trigger('click')");

                waitElement(LIST_BLOCK);
                waitContains(StrForSearch);
            } else closeAnonymousMessage(TXT_BUY_CONFIG);
        }
        log.info("Access to search - Checked");
    }
    @Step("Verifying access inner the document <idDoc>")
    public void verifyInDoc(String id) throws InterruptedException {
        String rootUrl = System.getenv("ROOTURL");
        driver.get(rootUrl + "/document/view/" + id + "?from=legislation");
        chooseLang();
        checkOpenDoc();
        for (int i = 0; i < elementMenuDoc.length; i++) {
            Thread.sleep(ONEWAIT);
            byClickElement(BTN_MENU);
            waitElement(By.xpath(strElementMenuDoc.replace("elementMenuDoc", elementMenuDoc[i])));
            byClickElement(By.xpath(strElementMenuDoc.replace("elementMenuDoc", elementMenuDoc[i])));
            if (elementMenuDoc[i].equals("Экспорт документа")) {
                byClickElement(BTN_YES);
                closeAnonymousMessage(TXT_BUY_CONFIG);
            } else {
                closeAnonymousMessage(TXT_BUY_FUNC);
            }
        }

        log.info("Right menu after open document - Checked");
        clickMainPage();
    }
    @Step("Check All module on main page")
    public void checkAllModule() throws InterruptedException {
        byClickElement(BTN_PRIME);
        HashMap<String, String> values = selectNewWindow(LNK_IPS);
        chooseLang();
        /*if (!driver.findElements(EXCEES_ANGLE).isEmpty()) {
            driver.findElement(EXCEES_ANGLE).click();
        }*/
// ***************************************************************************************

        CModule = (Long) js.executeScript("return window.angular.element('.lz-index').scope().buttons.tabs.lawyer.buttonsArr.length");

// ****************************************************************************************
        String urlMain = driver.getCurrentUrl();
        for (int i = 1; i < CModule + 14; i++) {
            strAnyPath = strModuleXpath.replace("${number}", "" + i + "");
            strAnything = byGetTextElement(By.xpath(strAnyPath));

            if (!strAnything.equals("ЗАКОНОДАТЕЛЬСТВО УКРАИНЫ") && !strAnything.equals("ПРАВОВАЯ КАРТИНА ДНЯ")) {
                byClickElement(By.xpath(strAnyPath));
                if (strAnything.equals("БУХГАЛТЕР И ЗАКОН") || strAnything.equals("АЛГОРИТМЫ ДЕЙСТВИЙ") || strAnything.equals("НАЛОГОВАЯ РЕФОРМА") || strAnything.equals("НОВАЦИИ") || strAnything.equals("КАЛЕНДАРЬ БУХГАЛТЕРА") || strAnything.equals("КАЛЕНДАРЬ ЮРИСТА")) {
                    closeAnonymousMessage(TXT_BUY_FUNC);
                } else closeAnonymousMessage(TXT_BUY_CONFIG);

            } else {
                byClickElement(By.xpath(strAnyPath));
                if (strAnything.equals("ЗАКОНОДАТЕЛЬСТВО УКРАИНЫ") && byGetTextElement(TXT_NAMEMODULE).equals("Законодавство України")){
                    setUKRRUSLanguageAnonymous(2);
                }

                if (strAnything.equals("ЗАКОНОДАТЕЛЬСТВО УКРАИНЫ")) {
                    waitElement(LIST_DOC);
                }
                if (!driver.getPageSource().contains("Законодательство Украины") && !driver.getPageSource().contains("Правовая картина дня")) {
                    throw new InterruptedException(TXT_FOUND + strAnything);
                }

                driver.navigate().to(urlMain);
                //fn.ByClickElement(EXCEES_ANGLE);
                waitElement(By.xpath(strAnyPath));
            }
        }
        log.info("All modules - Checked");
        driver.switchTo().window(values.get("nWindow")).close();
        driver.switchTo().window(values.get("pWindow"));
    }

    @Step("Check show-case application")
    public void checkShowCase() throws InterruptedException {
        for (int i = 1; i <= 4; i++) {
            strAnything = strElementAppXpath.replace("[icount]", "[" + i + "]");
            strAnything = strAnything.replace("[count]", "[1]");

            byClickElement(By.xpath(strAnything));
            closeAnonymousMessage(TXT_BUY_LICENCE);

        }
        for (int i = 1; i <= 3; i++) {
            strAnything = strElementAppXpath.replace("[icount]", "[" + i + "]");
            strAnything = strAnything.replace("[count]", "[2]");

            byClickElement(By.xpath(strAnything));
            if (byGetTextElement(By.xpath(strAnything)).equals("SMS-МАЯК")) {
                closeAnonymousMessage(TXT_BUY_SMSM);
            } else closeAnonymousMessage(TXT_BUY_LICENCE);
        }
        log.info("Showcase application - Checked");
    }
    @Step("Tear down step 1")
    public void tearDown1() {
        log.info("Test - Finished");
        driver.close();
        //driver.quit();
    }
    public void CaptScreenShort() throws Exception {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(".Hotscreenshot.png"));
    }
    @After
    public void tearDown() throws Exception {
        //driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}