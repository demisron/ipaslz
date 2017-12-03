package Maystrenko;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MainPagePlatforma extends PageObject {

    public MainPagePlatforma(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "descendant::li[@class='open-level-li'][position()=2]/a")
    public WebElement loginButton;

    @FindBy(xpath = "//div[@class='red-item']/span[@class='ng-binding']")
    public WebElement myProductsLZ;

    @FindBy(xpath = "descendant::div[@class='test ng-binding'][position() = 5]")
    public WebElement cntrAgent;

    @FindBy(xpath = "//i[@class='fa fa-user lz-fa']")
    public static WebElement myProfileButton;

    @FindBy(xpath = "//a[@id='profileExit']")
    public WebElement logoutButton;

    @FindBy(xpath = "//*[@id='favorites']/a")//Звездочка шапки
    public WebElement bookmark1star;

    @FindBy(xpath = "//a[@href='/bookmark']/div")//Перейти в Избранное из Платформы
    public WebElement bookmark2star;

    @FindBy(xpath = "//search//input")//поле ввода интеллектуального поиска
    public WebElement searchField;

    @FindBy(xpath = "//div[@class='search-button']")//лупа строки интеллектуального поиска
    public WebElement MagnifierOfSearchField;

    //Поле с e-mail в меню чувачка
    @FindBy(xpath = "//div[@id='profMenu']//h5[@class='lz-user-email ng-binding']")//Перейти в Избранное из Платформы
    public static WebElement mansEmail;

    @FindBy(xpath = "//div[@class='lz-ico']/i[@class='fa fa-star-o lz-fa']")//Перейти в Избранное из ИПС
    public WebElement bookmark2IPSstar;

    @FindBy(xpath = "//*[@id='desktop-btn']/a")//Перейти в Референт
    public WebElement goToReferent;

    @FindBy(xpath = "//i[@class='fa fa-search']") //лупа поиска в шапке
    public WebElement MagnifierOfHeader;

    @FindBy(xpath = "//div[@class='block']//div[starts-with(@ng-repeat, 'item')][position()=1]//a")
    //1-й найденный документ после поиска
    public WebElement firstFoundDocInList;


    public void clickBookmark1star() {
        bookmark1star.click();
    }

    public void bookmark2IPSstar() {
        bookmark2IPSstar.click();
    }

    public void clickBookmark2star() {
        bookmark2star.click();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public void clickFirstFoundDocInList() {
        firstFoundDocInList.click();
    }

    public boolean isInitializedLoginButton() {
        return loginButton.isDisplayed();
    }

    public static void clickMyProfile() {
        myProfileButton.click();
    }

    public void submitAuthorizationForm() {
        loginButton.click();
    }

    public void clickGoToReferent() throws InterruptedException {
        PageReferent pageReferent = new PageReferent(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(goToReferent));
        for (int j = 1; j <= 200; j++) {
            try {
                goToReferent.click();
                break;
            } catch (Exception e) {
                sleep(20);
            }
        }
        wait.until(ExpectedConditions.visibilityOf(pageReferent.plusBtnCreateFolderReferent));
    }

    public String compareMainPageText() {
        return myProductsLZ.getText();
    }


    public void SearchDoc(String search) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        sleep(500);
        MagnifierOfHeader.click();
        wait.until(ExpectedConditions.visibilityOf(searchField));
        sleep(500);
        searchField.sendKeys(search);
        MagnifierOfSearchField.click();
    }

    public void GoToNextTab() throws InterruptedException {//Переходим на вкладку с открывшимся документом, а родительскую вкладку закрываем
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        Thread.sleep(1000);
        //waitElement()
        driver.switchTo().window(tabs2.get(1));
    }
}