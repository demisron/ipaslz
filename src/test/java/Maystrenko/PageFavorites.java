package Maystrenko;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

public class PageFavorites extends PageObject {
    public PageFavorites(WebDriver driver) {
        super(driver);
    }

    //@FindBy(xpath="//span[text()='Мої закладки']")
    @FindBy(xpath = "//span[text()='Мои закладки']")
    public static WebElement visibilityOfFavorities;

    @FindBy(xpath = "//*[@id='search']")
    public WebElement searchInFaforities; //строка поиска в Избранном;

    @FindBy(xpath = "//*[@id='openSearch']/a/i")
    public WebElement searchMagnifier;

    @FindBy(xpath = "//*[@id='main-header']/div/div[1]/a")
    public WebElement logoMain;

    @FindBy(xpath = "//div[@class='ioco ioco-ips']")
    public WebElement logoIpas;

    @FindBy(xpath = "//search//input")
    public WebElement searchField;

    @FindBy(xpath = "//div[@class='search-button']")
    public WebElement searchMagnifierMain;

    @FindBy(xpath = "//div[@class='search-list']//lz-list/div[1]/div/a") //локатор для первого элемента рез-тов поиска
    public WebElement firstElemetnOfList;

    @FindBy(xpath = "//span[contains(.,'Письмо от 18.05.2009 № 10191/7/16-1517')]")
    //Кликнем по сохраненному документу в папке "Тестовая папка1"
    public WebElement testDocInListOfFolders;

    //ТРИ ТОЧКИ ДОКУМЕНТА "Письмо..." в папке
    @FindBy(xpath = "//span[contains(.,'Письмо от 18.05.2009 № 10191/7/16-1517')]/parent::div/parent::div/i[@class='fa fa-ellipsis-v points-icon']")
    public WebElement treePointsOfDoc;

    //ТРИ ТОЧКИ ДОКУМЕНТА "New Name Of Doc" в папке
    @FindBy(xpath = "//span[contains(.,'New Name Of Doc')]/parent::div/parent::div/i[@class='fa fa-ellipsis-v points-icon']")
    public WebElement treePointsOfDoc2;

    //пункт "редактировать" ДОКУМЕНТА "Письмо..." в папке
    @FindBy(xpath = "//span[contains(.,'Письмо от 18.05.2009 № 10191/7/16-1517')]/parent::div/parent::div//span[text()='Редактировать']")
    public WebElement editDocOfFavorities;

    //пункт "редактировать" ДОКУМЕНТА "Письмо..." в папке
    @FindBy(xpath = "//span[contains(.,'New Name Of Doc')]/parent::div/parent::div//span[text()='Редактировать']")
    public WebElement editDocOfFavorities2;

    //пункт "Удалить" ДОКУМЕНТА "Письмо..." в папке
    @FindBy(xpath = "//span[contains(.,'New Name Of Doc')]/parent::div/parent::div//span[text()='Удалить']")
    public WebElement dellDocOfFavorities;

    @FindBy(xpath = "//*[@id='submitBtn']") //Кнопка "Сохранить" при редактировании документа Избранного
    public WebElement buttonSubmitBtn;

    @FindBy(xpath = "//*[@id='inputName']") //Поле названия сохраненного документа Поле коммента к документу в Избранном
    public WebElement inputNameOfDoc;

    @FindBy(xpath = "//*[@id='textareaComments']") //Поле коммента к документу в Избранном
    public WebElement inputCommentOfDoc;

    //Облачко коммента к документу в списке документов Избранного
    @FindBy(xpath = "//span[contains(.,'New Name Of Doc')]/parent::div/parent::div/i[@class='fa fa-comment-o info-icon']")
    public WebElement cloudOfComment;

    //Кнопка "Да" окна подтверждения удаления документа
    @FindBy(xpath = "//div[@ng-click=\"yes()\"]")
    public WebElement clickYesToDell;

    //Поле ввода названия в красной рамке при пустом имени
    @FindBy(xpath = "//input[contains(@class, 'ng-invalid')]")
    public WebElement invalidEmptyForm;

    //Крестик очистки поля названия документа
    @FindBy(xpath = "//span[@ng-click=\"clearInput('name')\"]")
    public WebElement clearNameOfDoc;

    //крестик создание папки из меню редактирования документа
    @FindBy(xpath = "//i[@class='plus-sign fa fa-plus-circle']")
    public WebElement CreatingFolderFromDocAddingMenuToFavorites;

    //лупа в строке поиска по избранному
    @FindBy(xpath = "//i[@class='fa fa-search search-icon']")
    public WebElement MagnifierInSearchBarOfFavorites;

    //Страница содержит после удачного поиска
    @FindBy(xpath = "//span[contains(.,'Результат поиска')]")
    public WebElement SearchingResults;

    //Страница содержит после удачного поиска также название документа
    @FindBy(xpath = "//span[contains(.,'New Name Of Doc')]")
    public WebElement SearchingResultsNameDoc;

    //Страница содержит после удачного поиска также имя папки документа ("хлебные крошки")
    @FindBy(xpath = "//span[@class='file-path-text ng-scope']/span[contains(.,'Тестовая папка3')]")
    public WebElement SearchingResultsNameFolder;

    //Текст "Ничего не найдено" после неудачного поиска
    @FindBy(xpath = "//span[contains(.,'Ничего не найдено')]")
    public WebElement NothingFound;

    //Текст "Страница добавлена в избранное" после добавления документа в Избранное
    @FindBy(xpath = "//span[contains(.,'Страница добавлена в избранное')]")
    public WebElement PageAddedToFavorites;

    //Крестик очистки строки поиска в Избранном
    @FindBy(xpath = "//i[@class='fa fa-times-circle clear-icon']")
    public WebElement СircleСlearIcon;

    //Кнопка импорта-экспорта Избранного
    @FindBy(xpath = "//span[contains(., 'Сохранить / Загрузить')]")
    public WebElement BtnImportExport;

    //Чекбокс выбора папок для экспорта - все папки
    @FindBy(xpath = "//span[contains(., 'Выбрать все')]")
    public WebElement CheckboxImportExport;

    //Кнопка "Сохранить" экспорта
    @FindBy(xpath = "//div[@class='import-export-footer-btns']/div[contains(string(), 'Сохранить')]")
    public WebElement BtnSaveExport;

    //Таб "Загрузить" окна иморта-экспорта
    @FindBy(xpath = "//span[contains(., 'Загрузить')]")
    public WebElement TabDownload;

    //Выбор файла для загрузки
    @FindBy(xpath = "//div[@class='load-icon']")
    public WebElement LoadIcon;

    //Кнопка "Загрузить" окна импорта файла Избранного
    @FindBy(xpath = "//div[@class='import-export-footer-btns']/div[contains(string(), 'Загрузить')]")
    public WebElement BtnDownload;

    //Окно загрузки файла
    @FindBy(xpath = "//input[@type='file']")
    public WebElement WindowDownloadFile;

    //Успешный импорт файла Избранного
    @FindBy(xpath = "//span[contains(., 'Избранное добавлено')]")
    public WebElement SuccessfulImportText;

    //Папка с импортированным Избранным
    @FindBy(xpath = "//span[contains(., 'Імпорт з онлайн системи')]")
    public WebElement SuccessfulImportFolder;

    //Текст на вкладке "Загрузить"
    @FindBy(xpath = "//div[@ng-if='!isExport']/div[contains(string(), 'Загрузить избранное из файла')]")
    public WebElement TextLoadFavoritesFromFile;


    public void searchRequest1() {
        searchMagnifier.click();
    }

    public void clickMainFolder() { //Клик по главной папке "Мои закладки"
        visibilityOfFavorities.click();
    }

    public void searchRequest2(String search) {
        searchField.clear();
        searchField.sendKeys(search);
        searchMagnifierMain.click();
    }

    public void clickTreePointsOfDoc() {
        treePointsOfDoc.click();
    }

    public void clickTreePointsOfDoc2() {
        treePointsOfDoc2.click();
    }

    public void clickEditDocOfFavorities() {
        editDocOfFavorities.click();
    }

    public void clickEditDocOfFavorities2() {
        editDocOfFavorities2.click();
    }

    public void clickDellDocOfFavorities() {
        dellDocOfFavorities.click();
    }

    public void bottonYesToDell() {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(clickYesToDell));
        clickYesToDell.click();
    }

    public void clickOnTestDocInListOfFolders() throws InterruptedException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        sleep(500);
        wait.until(ExpectedConditions.visibilityOf(testDocInListOfFolders));
        testDocInListOfFolders.click();
    }

    public void clickFirstElementOfList() {
        firstElemetnOfList.click();
    }

    public void clickInputCommentOfDoc() throws InterruptedException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        //sleep(500);
        wait.until(ExpectedConditions.visibilityOf(inputCommentOfDoc));
        inputCommentOfDoc.click();
        inputCommentOfDoc.clear();
        inputCommentOfDoc.sendKeys("NewCommentOfDoc");
    }

    public void renameOfDoc() throws InterruptedException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        //sleep(500);
        wait.until(ExpectedConditions.visibilityOf(inputNameOfDoc));
        inputNameOfDoc.click();
        inputNameOfDoc.clear();
        inputNameOfDoc.sendKeys("New Name Of Doc");
    }

    public void mouseOnCloudOfComment() {
        Actions actions = new Actions(driver);
        actions.moveToElement(cloudOfComment).build().perform();
    }

    public void clickClearNameOfDocAndSave() {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(inputCommentOfDoc));
        clearNameOfDoc.click();
        buttonSubmitBtn.click();
    }

    public void clickCreatingFolderFromDocAddingMenuToFavorites() throws InterruptedException {
        sleep(500);
        CreatingFolderFromDocAddingMenuToFavorites.click();
    }

    public void clickSearchingResultsNameFolder() { //клик по "хлебным крошкам" - имени папки, где найден результат после поиска
        SearchingResultsNameFolder.click();
    }

    /* Есть лучший метод ниже с параметром, этот комментирую
        public void searchInFaforities() throws InterruptedException{

        final Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        //sleep(500);
        wait.until(ExpectedConditions.visibilityOf(searchInFaforities));
        searchInFaforities.click();
        searchInFaforities.clear();
        searchInFaforities.sendKeys("New Name Of Doc");
        MagnifierInSearchBarOfFavorites.click();
    }
    */

    public void searchInFaforitiesParam(String textOfSearch) throws InterruptedException {
        final Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        //sleep(500);
        wait.until(ExpectedConditions.visibilityOf(searchInFaforities));
        searchInFaforities.click();
        searchInFaforities.clear();
        searchInFaforities.sendKeys(textOfSearch);
        MagnifierInSearchBarOfFavorites.click();
    }


    public void clickСircleСlearIcon() { //клик на крестик очистки строки поиска в Избранном
        СircleСlearIcon.click();
    }

    public void clickBtnImportExport() { //клик по кнопке импорта-экспорта Избранного
        BtnImportExport.click();
    }

    public void clickCheckboxImportExport() { //клик по чекбоксу выбора всех папок для экспорта Избранного
        CheckboxImportExport.click();
    }

    public void clickBtnSaveExport() { //клик по кнопке "Сохранить" экспорта Избранного
        BtnSaveExport.click();
    }

    public void clickTabDownload() { //клик по кнопке "Сохранить" экспорта Избранного
        TabDownload.click();
    }

    public void clickLoadIcon() { //клик по иконке загрузки файла Избранного
        LoadIcon.click();
    }

    public void clickSuccessfulImportFolder() { //клик по папке с экспортом
        SuccessfulImportFolder.click();
    }

    public void clickSearchingResultsNameDoc() throws InterruptedException { //клик по документу в папке Избранного - открываем его
        final Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        sleep(500);
        wait.until(ExpectedConditions.visibilityOf(SearchingResultsNameDoc));
        SearchingResultsNameDoc.click();
    }
}

	/*@FindBy(xpath="//div[@ng-click='addFolder(0)']/span")
	public WebElement buttonCreateFolder;

	@FindBy(xpath="//*[@id='inputName']")
	public WebElement nameFolderField;

	@FindBy(xpath="//*[@id='submitBtn']")
	public WebElement buttonSubmitBtn;


	//Пункт меню удалить у i-той ОДИНАКОВОЙ папки:
	final int j = 1; //Без final не работает @FindBy с переменной внутри
	@FindBy(xpath="//bookmark-folders-list/div/div["+j+"]/bookmark-folders-list/div[3]/div/div[2]/ul/li[2]/div[1]/span[text()='Удалить']")
	public WebElement delFolderDuplicates;

	//Создадим локатор для имени папки с переменной, которую в последствии будем перебирать в цикле
	final int i = 1; //Без final не работает @FindBy с переменной внутри
	@FindBy(xpath="//bookmark-folders-list/div["+i+"]/div/div[1]/span")
	public WebElement folderNameCheckDuplicates;

	//String getTextFolderNameCheckDuplicates = folderNameCheckDuplicates.getText().toString();

	final String folderName="Тестовая папка1"; //Задаем имя папки. Без final не работает @FindBy с переменной внутри
	//@FindBy(xpath="//span[text()='Тестовая папка1']/parent::div/parent::div/i[@class='fa fa-ellipsis-v points-icon ng-scope']")

	//Опишем локатор контекстного меню папки (три точки)
	@FindBy(xpath="//span[text()='" + "Тестовая папка1" + "']/parent::div/parent::div/i[@class='fa fa-ellipsis-v points-icon ng-scope']")
	public WebElement treePointsOfFolder;

	public void clickTreePointsOfFolder(String folderName) {
		treePointsOfFolder.click();
	}

	//public void clickDellFolderDuplicates (int i) {
		//delFolderDuplicates.click();
	//	}

	public String getFolderName () {
		String getTextFolderNameCheckDuplicates = folderNameCheckDuplicates.getText().toString();
		return getTextFolderNameCheckDuplicates;
	}
	*/


/** ******************************

 @FindBy(xpath="descendant::input[@id='email'][position()=3]")
 public WebElement loginField;

 @FindBy(xpath="//input[@id='password']")
 private WebElement passwordField;

 @FindBy(xpath="//button[@class='btn btn-primary log-btn ng-binding']")
 private WebElement loginButton;

 @FindBy(xpath="//div[@class='row auth-title ng-binding']")
 private WebElement textOfAuthorizationPage;

 public void enterLogin (String login) {
 loginField.clear();
 loginField.sendKeys(login);
 //	***************************

 */



	/*
	public void enterPassword (String password) {
		passwordField.clear();
		passwordField.sendKeys(password);
	}

	public void submitAuthorizationForm () {
		loginButton.click();
	}

	public String compareTextOfAuthorizationPage () {
		return textOfAuthorizationPage.getText();
	}
	*/
