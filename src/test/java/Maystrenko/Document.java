package Maystrenko;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

public class Document {//extends PageObject {
    private WebDriver driver; //Добавил для отказа от extends PageObject

    public Document(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='100']")
    public WebElement paragrafToFavorities;

    @FindBy(xpath = "//*[@id='b100']")
    public WebElement starParagrafToFavorities;

    @FindBy(xpath = "//div[@class='bc_ic']")
    public WebElement arrowUp;

    //одна из горизонтальных палок в синей кнопке, признак того, что кнопка есть
    @FindBy(xpath = "//div[@class='btn_cont']/div[1]")
    public WebElement blueBtn;

    //Пункт меню "Шрифт" в синей кнопке - признак, что она нажалась и видим меню кнопки
    @FindBy(xpath = "//span[contains(.,'Шрифт')]")
    public WebElement font;

    //Шапка полного документа
    @FindBy(xpath = "//div[@class='lz-header-secondary']")
    public WebElement headerOfFullDoc;

    //Пункт меню синей кнопки "Добавить на контроль"
    @FindBy(xpath = "//i[@class='ico fa fa-bell']")
    public WebElement addToControl;

    //Название документа в окне постановки его на контроль
    @FindBy(xpath = "//*[@id='DocAttributes']")
    public WebElement nameOfDocInReferentForm;

    //Кнопка "Сохранить" при постановке на контроль
    @FindBy(xpath = "//*[@id='submitBtn']")
    public WebElement buttonSubmitBtn;

    public void scrollDoc(int numberOfScroll) throws InterruptedException {
        //Прокрутим документ вниз - прокрутка окна на его высоту с контролем появления новых абзацев
        for (int z = 1; z <= numberOfScroll; z++) {
            //Убеждаюсь, что прокрутка успела крутануть на следующие 10 абзацев
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='"+z*10+"']")));
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, window.innerHeight)");
            Thread.sleep(600);
        }
    }

    public void addParagrafToFavorities() throws InterruptedException {
        //final Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(paragrafToFavorities));
        sleep(1500);
        paragrafToFavorities.click();
        wait.until(ExpectedConditions.visibilityOf(starParagrafToFavorities));
        starParagrafToFavorities.click();
    }

    public void arrowUpPress() throws InterruptedException {
        //final Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(paragrafToFavorities));
        sleep(1500);
        arrowUp.click();
    }

    public void pressBlueBtn() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(blueBtn));
        for (int j = 1; j <= 200; j++) {
            try {
                blueBtn.click();
                break;
            } catch (Exception e) {
                sleep(20);
            }
        }
        wait.until(ExpectedConditions.visibilityOf(font));
    }

    //Добавление на контроль из меню синей кнопки + выбор папки для сохранения
    public void clickAddToControl(String nameOfFolderToSave) throws InterruptedException {
        FolderReferent folderReferent = new FolderReferent(driver);
        addToControl.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(nameOfDocInReferentForm));
        folderReferent.saveInFolderReferent(nameOfFolderToSave);
    }

    public void clickButtonSubmitBtn() {
        buttonSubmitBtn.click();
    }
}