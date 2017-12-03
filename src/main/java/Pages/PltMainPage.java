package Pages;

import MethodsCommon.MethodsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static MethodsCommon.Func.waitElement;

public class PltMainPage extends MethodsCommon {
    private WebDriver driver;

    public PltMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(className = "name-block")
    private WebElement nameBlock;

    @FindBy(css = "#openSearch i.fa.fa-search")
    private WebElement lnkSearch;

    @FindBy(className = "name")
    private WebElement lstElement;

    @FindBy(xpath="//*[contains(@class, 'signin-btn')]")//Кнопка в шапке платформы
    public WebElement sign_in_btn;

    @FindBy(xpath = "//*[@id='profile-btn']/*[contains(@ng-click, 'profileMenu')]")
    private WebElement profile_menu; //иконка пользователя в хедере

    @FindBy(xpath = "//*[@id='myProfile']")
    private WebElement my_profile_link;

    @FindBy(xpath = "(//*[@data-test='open-profile-mail'])[1]")
    private WebElement change_email_menu; // Ссылка "Изменение e-mail" в кабинете

    @FindBy(xpath = "//div[@class='red-item']//span[starts-with(@ng-show, '!isAnonymous')]")
    private WebElement lbRedName; // Название "Мои продукты ЛІГА:ЗАКОН"

    private static final By GET_NAME_BLOCK = By.className("name-block");


    public String getStrProduct() { return strProduct; }
    public WebElement getLbRedName() { return lbRedName; }
    public WebElement getLstElement() { return lstElement; }
    public WebElement getLnkContextSearch() {  return lnkSearch;  }
    public String getStrBlockConfig() { return strBlockConfig; }
    public WebElement getNameBlock() { return nameBlock; }
    public By getGetNameBlock() { return GET_NAME_BLOCK; }

    // String
    private String strProduct = "//div[starts-with(@class, 'info')]//h3[contains(text(), '{np}')]";
    private String strBlockConfig = "//div[starts-with(@class, 'six-block-col')]//div[contains(text(), '{nameConfig}')]";


    public void clickAndWaitNextElement(WebElement element, WebElement next_elelment){
        Wait<WebDriver> wait;

        element.click();
        wait = new WebDriverWait(driver, 5, 1000).withMessage("Элемент не найден");
        wait.until(ExpectedConditions.visibilityOf(next_elelment));
    }

    public void openProfilePage() throws InterruptedException {
        waitAndClickElement(profile_menu);
        waitAndClickElement(my_profile_link);
        //Switch to new tab opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        // Perform the actions on new tab
        waitAndClickElement(change_email_menu);
    }

    public WebDriver getDriver() {
        return driver;
    }
}

