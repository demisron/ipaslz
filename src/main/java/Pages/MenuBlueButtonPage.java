package Pages;

import MethodsCommon.MethodsCommon;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuBlueButtonPage extends MethodsCommon {
    private WebDriver driver;
    private static final String DOC_ENTRY = "//*[contains(text(),'Порядок вступления в силу:')]";

    public MenuBlueButtonPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@class='big-blue-btn']/div")
    private WebElement blue_btn; //Синяя кнопка меню

    @FindBy(xpath = "//*[text()='О документе']")
    private WebElement about_doc; // иконка i в меню "О документе"

    @FindBy(xpath = "//*[contains(@class, 'close-panel')]")
    private WebElement close_text_search; // кнопка закрытия панели поиска по тексту

    @FindBy(xpath = DOC_ENTRY)
    private WebElement doc_entry_procedure_name;// заголовок "Порядок вступления в силу"

    @FindBy(xpath = DOC_ENTRY + "/following-sibling::*[contains(@class, 'desc')]")
    private WebElement doc_entry_proc_text;// блок, где отображается текст, описывающий, когда документ вступает в силу

    public WebElement getDocEntryProcName(){return doc_entry_procedure_name;}
    public WebElement getDocEntryProcText(){return doc_entry_proc_text;}
    public String getDocEntry(){return DOC_ENTRY;}

    public void openAboutDocMenu() throws InterruptedException {
        waitAndClickElement(blue_btn);// Нажать на синюю кнопку меню в документе
        waitAndClickElement(about_doc); // Нажать на пункт "О документе"
        if(close_text_search.isDisplayed()){
            waitAndClickElement(close_text_search); // Если открыта панелька "Поиск по тексту", закрыть ее.
        }

    }
}

