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

public class Folder extends PageObject { //Класс относится к папкам Избранного
    public Folder (WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver,100);

    final String nameFolder="Тестовая папка1"; //final нужен для корректной работы вызова @FindBy с использованием String nameFolder
    final String nameFolder2="Тестовая папка2"; //final нужен для корректной работы вызова @FindBy с использованием String nameFolder

    @FindBy(xpath="//div[@ng-click='addFolder(0)']/span")
    public WebElement buttonCreateFolder;

    @FindBy(xpath="//*[@id='inputName']")
    public WebElement nameFolderField;

    @FindBy(xpath="//label[.='Название папки']//parent::div/*[@id='inputName']") //При создании папки на лету локатор поля имени папки другой
    public WebElement nameFolderField2;

    @FindBy(xpath="//*[@id='submitBtn']")
    public WebElement buttonSubmitBtn;

    //@FindBy(xpath="//span[text()='Тестовая папка1']/parent::div/parent::div/i[@class='fa fa-ellipsis-v points-icon ng-scope']")
    @FindBy(xpath="//span[text()='"+nameFolder+"']/parent::div/parent::div/i[@class='fa fa-ellipsis-v points-icon ng-scope']")
    public WebElement threePointsOfFolder;

    @FindBy(xpath="//span[text()='"+nameFolder+"']/parent::div/parent::div//ul//span[text()='Редактировать']")
    public WebElement clickEditFolder;

    public void createFolder(String nameFolder) {
        buttonCreateFolder.click();
        wait.until(ExpectedConditions.visibilityOf(buttonSubmitBtn));
        nameFolderField.clear();
        nameFolderField.sendKeys(nameFolder);
        buttonSubmitBtn.click();
    }

    public void createFolder2(String nameFolder) throws InterruptedException { //Создание папки из меню редактирования докеумента Избранного. Кнопку Создать жмем в другом месте
        wait.until(ExpectedConditions.visibilityOf(buttonSubmitBtn));
        wait.until(ExpectedConditions.visibilityOf(nameFolderField2));
        nameFolderField.clear();
        nameFolderField.sendKeys(nameFolder);
        buttonSubmitBtn.click();
        sleep(500);
        buttonSubmitBtn.click(); //дважды buttonSubmitBtn.click - верно, нужно подтвердить две формы подряд
    }

    public void editFolder(String nameFolder) throws InterruptedException {
        threePointsOfFolder.click();
        clickEditFolder.click();
        //this.createFolder(nameFolder2);
        nameFolderField.clear();
        nameFolderField.sendKeys(nameFolder2);
        //Thread.sleep(500);
        buttonSubmitBtn.click();
    }

    public void delFolder (String nameFolder) throws InterruptedException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        int countAllFolders = 0;
        //Переопределим время ожидания для того, чтобы в случае отсутствия папок тест не останавливался на 10 дефолтовых секунд ожидания их появления
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        //Проверим наличие хотя бы одной папки.
        //Проверка того, что список папок не пустой. Если да - идем дальше искать и удалять "Тестовая папка1"
        //if (!((List<WebElement>)driver.findElement(By.xpath("//bookmark-folders-list//bookmark-folders-list/div/div"))).isEmpty()){
        countAllFolders = driver.findElements(By.xpath("//bookmark-folders-list//bookmark-folders-list/div/div")).size();
        //System.out.println(countAllFolders);
        //System.out.println(nameFolder);
        if (!(0==countAllFolders)){
            int countAllFolders2=countAllFolders; //Запомним количество папок, чтобы не запускать внешний цикл больше раз, чем нужно
            //В цикле по i перебираем локатор на имя папки //bookmark-folders-list/div[i]/div/div[1]/span.
            for (int z = 1; z <= countAllFolders2; z++) {
                countAllFolders = driver.findElements(By.xpath("//bookmark-folders-list//bookmark-folders-list/div/div")).size();
                //System.out.println(countAllFolders);

                for (int j = 1; j <= countAllFolders; j++) {
                    //Получим имя текущей папки
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//bookmark-folders-list/div/div[3]/bookmark-folders-list/div["+j+"]/div/div[1]/span")));
                    String getFolderNameXpath = driver.findElement(By.xpath("//bookmark-folders-list/div/div[3]/bookmark-folders-list/div["+j+"]/div/div[1]/span")).getText().toString();

                    String getFolderNameXpathEtalon = nameFolder;

                    //Если имя текущей папки nameFolder - переходим к ее удалению
                    //Boolean compare=false;
                    Boolean compare=getFolderNameXpath.equals(getFolderNameXpathEtalon);
                    if (compare==true) {
                        //if assertTrue(getFolderNameXpath.contains(nameFolder));
                        //Нажмем на три точки i-той найденной папки с именем nameFolder
                        sleep(500);
                        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//bookmark-folders-list/div["+j+"]/div/div[1]/span[text()='"+nameFolder+"']/parent::div/parent::div/i[@class='fa fa-ellipsis-v points-icon ng-scope']"))));
                        driver.findElement(By.xpath("//bookmark-folders-list/div["+j+"]/div/div[1]/span[text()='"+nameFolder+"']/parent::div/parent::div/i[@class='fa fa-ellipsis-v points-icon ng-scope']")).click();

                        //Нажмем пункт меню удалить для i-той ОДИНАКОВОЙ папки:
                        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//bookmark-folders-list/div/div[3]/bookmark-folders-list/div["+j+"]/div/div[2]/ul/li[2]/div[1]/span"))));
                        driver.findElement(By.xpath("//bookmark-folders-list/div/div[3]/bookmark-folders-list/div["+j+"]/div/div[2]/ul/li[2]/div[1]/span")).click();

                        //Подтвердим удаление
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn-primary.btn-lg.ng-binding")));
                        driver.findElement(By.xpath("//div[1]/div/div/div[3]/div[1]")).click();

                        //После удаления первой же папки локаторы остальных меняются, смещаясь на один, поэтому нужно начать все сначала
                        j = countAllFolders+1; //насильно прекратим цикл
                        sleep(800);
                    }
                }
            }
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//вернем дефолтное время ожидания в 10 секунд
    }

    public void saveInFolderFavorities (String nameFolder) throws InterruptedException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='selectFolderPlus']/button[2]")));
        sleep (1000);
        driver.findElement(By.xpath("//*[@id='selectFolderPlus']/button[2]")).click(); //Нажмем выпадающий список
        sleep(800);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"+nameFolder+"')]")));
        //driver.findElement(By.xpath("//span[contains(text(),'"+nameFolder+"')]")).click(); //#Выберем для сохранения папку
        //Переделал локаторы ожидания и клика так, чтобы они относились только к папкам 1-го уровня
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-ng-repeat='node in bookmark.folders']/span[contains(text(),'"+nameFolder+"')]")));
        driver.findElement(By.xpath("//li[@data-ng-repeat='node in bookmark.folders']/span[contains(text(),'"+nameFolder+"')]")).click(); //#Выберем для сохранения папку
        sleep(800);
        driver.findElement(By.id("submitBtn")).click(); //#Нажмем синюю кнопку Добавить
        sleep(500);
    }
}