package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OpenDocPage {

    private final WebDriver driver;
    public OpenDocPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(className = "lz-btn")
    private WebElement btnAllResultBackLinks;    // Все обратные связи

    @FindBy(xpath = "//span[@ng-if='item.date_accept']")
    private List<WebElement> lstDate;            // Даты в правом меню

    @FindBy(css = ".doc_number span")
    private WebElement lstWrd;                    // Номер в списках вердиктума

    @FindBy(xpath = "//div[starts-with(@class, 'backlinks ipas')]")
    private WebElement lnkBackLinks;              // Обратные связи в документе

    @FindBy(css = ".wrap-item div")
    private WebElement lnkBackLinksInTree;        // Переход на обратные связи

    @FindBy(xpath = "//div[starts-with(@class, 'mainTag')]//b[contains(text(), 'НАУКОВО-ПРАКТИЧНИЙ КОМЕНТАР')]")
    private WebElement lnkKK;

    @FindBy(xpath = "//div[starts-with(@class, 'information back-links')]//div[starts-with(@class, 'name')]")
    private WebElement lnkFolderInTree;           // Ссылка на получени е списка обратных связей

    // String
    private static String strVrdBackLinks = "//div[starts-with(@class, 'ui-doc-part')]//div[starts-with(@class, 'mainTag')][33]//*[starts-with(@class, 'backlinks string')]";

    // Links
    private static By LNK_BACK_LINKS = By.xpath("//div[starts-with(@class, 'backlinks ipas')]");

    public By getLnkBackLinks() { return LNK_BACK_LINKS; }
    public WebElement getLnkFolderInTree() { return lnkFolderInTree; }
    public WebElement getLnkBackLinksInTree() { return lnkBackLinksInTree; }
    public WebElement getWaitListVrd() { return lstWrd; }
    public WebElement getLnkKk() { return lnkKK; }
    public By setWaitLinkVrd(String lnk) {
        return By.xpath(strVrdBackLinks.replace("string", lnk));
    }
    public WebElement getLnkAllResultBackLinks(){ return btnAllResultBackLinks; }
    public List<WebElement> getLstDate() { return lstDate; }
}
