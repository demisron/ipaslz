package Maystrenko;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class FolderReferent extends PageObject { //Класс относится к папкам Референта
    public FolderReferent(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, 100);

    //final String nameFolder="Тестовая папка Референт"; //final нужен для корректной работы вызова @FindBy с использованием String nameFolder

    @FindBy(css = "div.name-row > div.add-folder > i.fa-plus-circle")
    public WebElement buttonCreateFolder;

    @FindBy(xpath = "//*[@id='inputName']")
    public WebElement nameFolderField;

    @FindBy(xpath = "//*[@id='submitBtn']")
    public WebElement buttonSubmitBtn;

    @FindBy(xpath = "//span[contains(text(),'TestFolderReferent')]")
    //@FindBy не работает с переменными, хардкодим имя папки(
    public static WebElement folderOfDocReferent;


    @FindBy(xpath = "")
    public WebElement threePointsOfFolder;

    @FindBy(xpath = "")
    public WebElement clickEditFolder;

    public void createFolder(String nameFolder) {
        buttonCreateFolder.click();
        wait.until(ExpectedConditions.visibilityOf(buttonSubmitBtn));
        nameFolderField.clear();
        nameFolderField.sendKeys(nameFolder);
        buttonSubmitBtn.click();
    }

    public void createFolder2(String nameFolder) throws InterruptedException { //Создание папки из меню редактирования документа Референта.
        wait.until(ExpectedConditions.visibilityOf(buttonSubmitBtn));

        nameFolderField.clear();
        nameFolderField.sendKeys(nameFolder);
        buttonSubmitBtn.click();
        sleep(500);
        buttonSubmitBtn.click(); //дважды buttonSubmitBtn.click - верно, нужно подтвердить две формы подряд
    }

    public void editFolder(String nameFolder) throws InterruptedException {
        threePointsOfFolder.click();
        clickEditFolder.click();
        nameFolderField.clear();
        //nameFolderField.sendKeys(nameFolder2);
        buttonSubmitBtn.click();
    }

    public void clickFolderOfDocReferent() throws InterruptedException{ //клик по папке c референтом
        sleep(500);
        folderOfDocReferent.click();
    }

    public void delFolder(String nameOfFolder2) throws InterruptedException {
        PageReferent pageReferent = new PageReferent(driver);
        final Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        int countAllFoldersTestFolder = 0;
        //Переопределим время ожидания для того, чтобы в случае отсутствия папок тест не останавливался на 10 дефолтовых секунд ожидания их появления
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        //1) Считаем сколько есть папок c названием nameOfFolder2
        //++++countAllFoldersTestFolder = driver.findElements(By.xpath("//div[@class='name']/span[contains(.,'TestFolderReferent')]")).size();
        countAllFoldersTestFolder = driver.findElements(By.xpath("//div[@class='name']/span[contains(.,'" + nameOfFolder2 + "')]")).size();
        if (!(0 == countAllFoldersTestFolder)) { //Если насчитали больше нуля папок - идем их всех удалять
            for (int z = 1; z <= countAllFoldersTestFolder; z++) {
                //кликнем по папке
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='name']/span[contains(.,'" + nameOfFolder2 + "')]")));
                driver.findElement(By.xpath("//div[@class='name']/span[contains(.,'" + nameOfFolder2 + "')]")).click();
                //Нажмем кнопку удалить на папке верхнего уровня
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = '" + nameOfFolder2 + "']/parent::*/parent::*/parent::*/*//div[@class='right']//i[@class='fa fa-trash-o']")));
                driver.findElement(By.xpath("//span[text() = '" + nameOfFolder2 + "']/parent::*/parent::*/parent::*/*//div[@class='right']//i[@class='fa fa-trash-o']")).click();
                //Нажмем кнопку подтверждения удаления
                wait.until(ExpectedConditions.visibilityOf(pageReferent.submitBtn));
                pageReferent.clickSubmitBtn();
                sleep(500);
            }
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//вернем дефолтное время ожидания в 10 секунд
    }

    public void saveInFolderReferent(String nameFolder) throws InterruptedException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='selectFolderPlus']/button[2]")));
        sleep(500);
        //driver.findElement(By.xpath("//*[@id='selectFolderPlus']/button[2]")).click(); //Нажмем выпадающий список
        driver.findElement(By.xpath("//div[@id='selectFolderPlus']")).click(); //Нажмем выпадающий список
        sleep(500);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"+nameFolder+"')]")));
        //Переделал локаторы ожидания и клика так, чтобы они относились только к папкам 1-го уровня
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='name ng-binding' and contains(text(),'" + nameFolder + "')]")));
        driver.findElement(By.xpath("//div[@class='name ng-binding' and contains(text(),'" + nameFolder + "')]")).click(); //Выберем для сохранения папку
        //driver.findElement(By.id("submitBtn")).click(); //Нажмем синюю кнопку Добавить
        //sleep(500);
    }
}