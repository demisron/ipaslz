package Maystrenko;
/*
  Created by MaystrenkoE on 14.06.2017.
  - Самая любимая примочка — Ctrl+Alt+L. Форматит код в красоту, с пробелами, отступами и т.п.
  - с помощью ctrl+alt+t можно генерировать вокруг выделенного кода try/catch, if/else, while и т.д.
  - alt+insert может генерировать конструкторы к выделенным полям класса, геттеры и сеттеры
  - ctrl+shift+blank — вызов smart comlition — фильтрует список из методов и переменных ожидаемого типа.
 */

import Pages.AuthPage;
import com.thoughtworks.gauge.Step;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static MethodsCommon.Driver.driver;
import static MethodsCommon.Func.*;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

public class TestMGaugeFaforites {//extends PageObject {
    //public TestMGauge3 (WebDriver driver) {
    //  super(driver);
    // }
    WebDriverWait wait = new WebDriverWait(driver, 100);

    private static String strClickBalls = "//div[@class='balls']//div[@class='btn-downup']/i", myprod, strAny, link_after;
    private static String strListTitle = "//div[@class='ul ng-scope active']//div[@class='li ng-scope'][position()=icount]/div[@class='title']//span";

    // Text
    private static final By NAME_PRODS = By.xpath("//div[@class='red-item']//span[starts-with(@ng-show, '!isAnonymous')]");

    // Links
    private static final By OPEN_SLIDE = By.xpath("//div[@class='slider-title']//b");
    private static final By THESSES_TITLE = By.xpath("//div[@class='ul ng-scope active']//div[@class='li ng-scope active']//span");
    private static final By THESSES_LINK = By.xpath("//div[@class='ul ng-scope active']//div[@class='li ng-scope active']//div[@class='mainTag']//p");

    // Button
    private static final By BALLS_BTN = By.xpath("//div[@class='balls']//div[@class='btn-down']/i");
    private static final By BACK_BUTTON = By.cssSelector(".back-button i");

    JavascriptExecutor js;
    Log log = LogFactory.getLog(AuthPage.class);


    @Step("Authorisation user2 - <lg> and pwd - <pw> with localization page <number> for <config>")
    //Авторизация с выбранными параметрами
    public void authorisationLogin(String login, String password, Integer number, String config) throws InterruptedException {
        navigateToUrl();
        log.info("Browser - opened");

        AuthPage page = PageFactory.initElements(driver, AuthPage.class);

        page.InputFieldClick(login, password);
        log.info("Authorization - done");

        myprod = byGetTextElement(NAME_PRODS);
        if (myprod.compareTo("Мої продукти ЛІГА:ЗАКОН") == 0 && number == 2 ||
                (myprod.compareTo("Мои продукты ЛІГА:ЗАКОН") == 0 && number == 1)) {
            setLocalLanguage(number);
            log.info("Localization - switched");
        }
    }


    @Step("FavoritiesAbzacStar2")
    /*В данном тесте проверяю наличие папки "Тестовая папка 1", если нахожу одну или несколько - удаляю все
     (вероятные хвосты прошлых тестов), затем добавляю в Избранное конкретный документ, перехожу к его абзацу, добавляю
     в Избранное конерктный абзац, возвращаюсь в Избранное, нахожу нсохраненный документ, открываю его и убеждаюсь, что
      он прокрутился до выбранного абзаца и что возле этого абзаца видна черная звездочка
      */
    public void FavoritiesAbzac() throws InterruptedException {
        PageFavorites pageFavorites = new PageFavorites(driver);
        Document document = new Document(driver);
        Folder folder = new Folder(driver);
        MainPagePlatforma mainPagePlatforma = new MainPagePlatforma(driver);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        String nameOfFolder = "Тестовая папка1";
        String nameOfFolder2 = "Тестовая папка2";
        String nameOfFolder3 = "Тестовая папка3";

        //WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver,20,500).withMessage("Element was not found");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Проверим наличие папок "Тестовая папка1" и "Тестовая папка 2"  и удалим все, если найдем
        this.goToFavorites();
        /*wait.until(ExpectedConditions.visibilityOf(mainPagePlatforma.bookmark1star));
        mainPagePlatforma.clickBookmark1star();  //Звездочка в шапке
        wait.until(ExpectedConditions.visibilityOf(mainPagePlatforma.bookmark2star));
        mainPagePlatforma.clickBookmark2star();// //Перейти в Избранное
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.visibilityOfFavorities));*/

        folder.delFolder(nameOfFolder);
        folder.delFolder(nameOfFolder2);
        folder.delFolder(nameOfFolder3);

        //Создадим папку Тестовая папка1
        folder.createFolder(nameOfFolder);

        //Откроем нужный документ по прямой ссылке и сразу перейдем к сотому абзацу
        String rootUrl = System.getenv("ROOTURL");
        driver.get(rootUrl + "/document/view/GDPI9556?an=100");

        //Проверяем, что там есть нужный текст
        //List<WebElement> list = driver.findElements(By.xpath("//span[text()='№ 10191/7/16-1517']")); //Эта строка если документ открыт в начале, а не на абзаце 100
        List<WebElement> list = driver.findElements(By.xpath("//b[contains(.,'Порядок оподаткування податком на додану вартість операцій з реалізації за дорученням')]"));
        Assert.assertTrue("Text not found!", list.size() > 0);

        //document.scrollDoc(9); //Прокрутим документ до нужного абзаца - 9 раз

        //Добавим абзац в избранное
        document.addParagrafToFavorities();
        folder.saveInFolderFavorities("Тестовая папка1");
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.PageAddedToFavorites)); //Текст "Страница добавлена в избранное" после добавления документа в Избранное

        //Поднимемся верх, чтобы было видно шапку - уже не нужно, шапку видно всегда. //document.arrowUpPress();

        //Перейдем в Избранное
        this.goToFavorites();

        //Кликнем по папке "Тестовая папка1"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + nameOfFolder + "']")));
        driver.findElement(By.xpath("//span[text()='" + nameOfFolder + "']")).click();

        //Кликнем по сохраненному документу в папке "Тестовая папка1"
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'Письмо от 18.05.2009 № 10191/7/16-1517')]")));
        //driver.findElement(By.xpath("//span[contains(.,'Письмо от 18.05.2009 № 10191/7/16-1517')]")).click();
        //wait.until(ExpectedConditions.visibilityOf(pageFavorites.testDocInListOfFolders));
        pageFavorites.clickOnTestDocInListOfFolders();

        //Переходим на вкладку с открывшимся документом, а родительскую вкладку закрываем
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        Thread.sleep(1000);
        driver.switchTo().window(tabs2.get(1));

        //Проверяем, что там есть нужный текст
        List<WebElement> list2 = driver.findElements(By.xpath("//span[text()='№ 10191/7/16-1517']"));
        Assert.assertTrue("Text not found!", list2.size() > 0);

        //Получим текущий URL и убедимся, что он содержит 100-тый номер абзаца
        String url = driver.getCurrentUrl();
        CharSequence urlNumber = "100"; //CharSequence - аналог String
        boolean retval = url.contains(urlNumber);
        assertTrue(retval);

        //Проверяем, что звездочка сотого абзаца закрашена
        List<WebElement> list3 = driver.findElements(By.xpath("//i[@id='b" + urlNumber + "']/parent::u[@class='par_option']/i[@class='add_bookmark active ng-scope']"));
        Assert.assertTrue("Star of paragraph is not painted!", list3.size() > 0);

        //Поднимемся верх, чтобы было видно шапку - уже не нужно, шапку видно всегда
        //sleep(500); document.arrowUpPress();

        //Перейдем в Избранное
        this.goToFavorites();
        System.out.println("STEP FavoritiesAbzacStar2 - WELL DONE!");
    }

    @Step("Favorite's doc save and edit")
    /*Сохранение и редактирование элемента Избранного. Сохранить в Тестовая папка 1 какой-либо документ (уже сохранен
    с прошлого степа). Перейти в Тестовая папка 1, отредактировать сохраненный документ (изменить название
    +проверка на пустое название), комментарий, + пересохранить документ в Тестовая папка 2. Убедиться, что
    в Тестовая папка 1 он исчез, а в Тестовая папка 2 появился с учетом внесенных изменений.*/

    public void favoritiesSaveAndEdit() throws InterruptedException {
        PageFavorites pageFavorites = new PageFavorites(driver);
        Document document = new Document(driver);
        Folder folder = new Folder(driver);
        MainPagePlatforma mainPagePlatforma = new MainPagePlatforma(driver);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        String nameOfFolder = "Тестовая папка1";
        String nameOfFolder2 = "Тестовая папка2";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        folder.createFolder(nameOfFolder2);//Создадим папку Тестовая папка2

        //Кликнем по папке "Тестовая папка1"
        sleep(500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + nameOfFolder + "']")));
        driver.findElement(By.xpath("//span[text()='" + nameOfFolder + "']")).click();

        //Начнем редактировать сохраненный документ
        sleep(500);
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.treePointsOfDoc)); //три точки документа
        pageFavorites.clickTreePointsOfDoc();

        wait.until(ExpectedConditions.visibilityOf(pageFavorites.editDocOfFavorities)); //пункт меню "редактировать" документа
        sleep(500);
        pageFavorites.clickEditDocOfFavorities();

        pageFavorites.renameOfDoc(); //переименуем документ
        pageFavorites.clickInputCommentOfDoc(); //добавим комментарий
        folder.saveInFolderFavorities("Тестовая папка2"); //пересохраним документ в другую папку
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.cloudOfComment)); //облачко комментария к документу
        sleep(500);
        pageFavorites.mouseOnCloudOfComment(); //наведем мышку на облачко комментария к документу
        waitContains("NewCommentOfDoc"); //Использую метод из класа Ромы, убеждаюсь, что вижу коммент к документу в облачке

        /*
        //Удалим документ
        sleep(500);
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.treePointsOfDoc2)); //три точки документа
        pageFavorites.clickTreePointsOfDoc2();
        pageFavorites.clickDellDocOfFavorities(); //пункт меню "Удалить"
        pageFavorites.bottonYesToDell(); //Кнопка "да" окна подтверждения

        //Кликнем по папке "Тестовая папка2" - закроем ее. Нужно для корректного ее удаления
        sleep(500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+nameOfFolder2+"']")));
        driver.findElement(By.xpath("//span[text()='"+nameOfFolder2+"']")).click();
        */
        System.out.println("STEP Favorite's doc save and edit - WELL DONE!");
    }


    @Step("Creating a folder from the document adding menu to favorites")
    //Проверяем создание папки "на лету" в процессе добавления/редактирования документа в Избранное
    public void favoritiesCreateFolderFromAddDoc() throws InterruptedException {
        PageFavorites pageFavorites = new PageFavorites(driver);
        Document document = new Document(driver);
        Folder folder = new Folder(driver);
        MainPagePlatforma mainPagePlatforma = new MainPagePlatforma(driver);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        String nameOfFolder = "Тестовая папка1";
        String nameOfFolder2 = "Тестовая папка2";
        String nameOfFolder3 = "Тестовая папка3";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Начнем редактировать сохраненный документ
        sleep(500);
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.treePointsOfDoc2)); //три точки документа
        pageFavorites.clickTreePointsOfDoc2();

        wait.until(ExpectedConditions.visibilityOf(pageFavorites.editDocOfFavorities2)); //пункт меню "редактировать" документа
        sleep(500);
        pageFavorites.clickEditDocOfFavorities2();

        pageFavorites.clickCreatingFolderFromDocAddingMenuToFavorites(); //нажмем плюсик создания новой папки
        folder.createFolder2(nameOfFolder3); //Создадим новую папку и переместим туда документ
        System.out.println("STEP Creating a folder from the document adding menu to favorites - WELL DONE!");

    }

    @Step("Search in favorites")
    //Будем искать добавленный документ в Избранном. Сразу не найдем, т.к. сперва нужно переиндексировать избранное вызовом реста

    public void favoritiesSearch() throws InterruptedException {
        String nameOfFolder = "Тестовая папка1";
        String nameOfFolder2 = "Тестовая папка2";
        String nameOfFolder3 = "Тестовая папка3";
        PageFavorites pageFavorites = new PageFavorites(driver);
        Document document = new Document(driver);
        Folder folder = new Folder(driver);
        MainPagePlatforma mainPagePlatforma = new MainPagePlatforma(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        sleep(500);
        pageFavorites.clickMainFolder(); //клик по "Мои закладки" - свернем Избранное
        pageFavorites.searchInFaforitiesParam("New Name Of Doc"); //Поищем в Избранном документ "New Name Of Doc"
        //Сразу не найдем, т.к. сперва нужно переиндексировать избранное вызовом реста
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.NothingFound)); //сообщение "Ничего не найдено"

        this.RestIndexFavorites(); //Переиндексируем Избранное

        this.favoritiesSearchRequest("New Name Of Doc"); //Поищем в Избранном документ "New Name Of Doc"
        pageFavorites.clickСircleСlearIcon(); //очистим строку поиска, т.е. переоткроем Избранное

        //Выполним несколько поисков с использованием регулярных выражений с "*" и "?"
        this.favoritiesSearchRequest("New Name Of Do?");
        pageFavorites.clickСircleСlearIcon(); //очистим строку поиска, т.е. переоткроем Избранное
        this.favoritiesSearchRequest("N?w *am? Of Do*");

        //Кликнем по "хлебным крошкам" в результатах поиска и перейдем в папку, где находится найденный документ
        pageFavorites.clickSearchingResultsNameFolder();
        sleep(500);
        //На странице должна быть папка, гда находится найденный документ:
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + nameOfFolder3 + "']")));

        //На странице должен быть виден сам документ в этой папке
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.SearchingResultsNameDoc));


        //Проверим наличие папок "Тестовая папка1" и "Тестовая папка 2"  и "Тестовая папка 3" и удалим все, если найдем
        folder.delFolder(nameOfFolder);
        folder.delFolder(nameOfFolder2);
        folder.delFolder(nameOfFolder3);

        /*Еще раз повторно переиндексируем Избранное, чтобы в БД было знание, что такой документ удален из Избранного и тест не
        падал сразу после перезапуска на первом поиске документа сразу после добавления. Штатно после первого поиска
        документ не должен находиться поиском (а без повторной переиндексации он будет находиться, БД еще не знает,
        что документ удален.  Изменения в Избранном актуализируются после запуска реста либо же после окончания ближайшего
         часа (каждый час рест переиндексации автоматом запускается кроном на dev и test, на боевой - каждые 15  минут)
        */
        //this.RestIndexFavorites(); //Переиндексируем Избранное - решил, что не нужно, если элемент удален, то ошибочного поиска в следующих запусках теста быть не должно

        System.out.println("STEP Search in favorites - WELL DONE!");
    }


    @Step("Favorite's export and import")
    //По названию все ясно
    public void favoritesExportImport() throws InterruptedException {
        PageFavorites pageFavorites = new PageFavorites(driver);
        Document document = new Document(driver);
        Folder folder = new Folder(driver);
        MainPagePlatforma mainPagePlatforma = new MainPagePlatforma(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String nameOfFolder = "Тестовая папка1";
        String winUserName = System.getProperty("user.name"); //виндовое имя текущего юзера
        int Date, Month, Year;

        /*
        wait.until(ExpectedConditions.visibilityOf(mainPagePlatforma.bookmark1star));
        mainPagePlatforma.clickBookmark1star();  //Звездочка в шапке
        wait.until(ExpectedConditions.visibilityOf(mainPagePlatforma.bookmark2star));
        mainPagePlatforma.clickBookmark2star();// //Перейти в Избранное
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.visibilityOfFavorities));
        */

        //Создадим папку и файл, наличие которых будем контролировать после экспорта

        //Создадим папку Тестовая папка1
        folder.createFolder(nameOfFolder);

        //Откроем документ и перейдем к сотому абзацу
        String rootUrl = System.getenv("ROOTURL");
        driver.get(rootUrl + "/document/view/GDPI9556?an=100");

        //Проверяем, что там есть нужный текст
        List<WebElement> list = driver.findElements(By.xpath("//b[contains(.,'Порядок оподаткування податком на додану вартість операцій з реалізації за дорученням')]"));
        Assert.assertTrue("Text not found!", list.size() > 0);

        //Добавим абзац в избранное
        document.addParagrafToFavorities();
        folder.saveInFolderFavorities("Тестовая папка1");
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.PageAddedToFavorites)); //Текст "Страница добавлена в избранное" после добавления документа в Избранное

        //Получим имя пользователя Платформы. Найдем его в шапке
        MainPagePlatforma.clickMyProfile(); //клик по чувачку в шапке
        wait.until(ExpectedConditions.visibilityOf(MainPagePlatforma.mansEmail));//подождем выпадающего меню чувачка с e-mail
        String mansEmailtext = MainPagePlatforma.mansEmail.getText();

        //Зададим путь к файлу экспорта Избранного, склеив его из кусков пути к Загрузкам, имени виндового юзера и имени юзера Платформы
        //File ipasExport = new File("C:\\Users\\"+winUserName+"\\Downloads", "prime@binka.me.ipas");
        File ipasExport = new File("C:\\Users\\" + winUserName + "\\Downloads", mansEmailtext + ".ipas");

        //Проверим, не осталось ли файла с прошлого экспорта, если найдем - удалим
        if (ipasExport.exists()) {
            //Удалим экспортированный файл
            boolean deleted = ipasExport.delete();
            if (deleted)
                System.out.println("Экспортированный файл с прошлых тестов удален");
        }

        //Проверим, есть ли импортированные сегодня папки и удалим, если найдем
        //Удаляемая папка имеет вид "Імпорт з онлайн системи (dd.mm.yyyy). Нужно найти текущую дату
        Calendar calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Date = calendar.get(Calendar.DAY_OF_MONTH);
        Month = calendar.get(Calendar.MONTH) + 1; //Нумерация месяцев начинается с нуля, приходится добавлять 1

        //Если длина строки месяца меньше двух символов - допишем к месяцу ноль, предварительно приведя Month к string
        String MonthString = String.valueOf(Month);
        if (MonthString.length() < 2)
            MonthString = "0" + MonthString;
        //Если длина строки числа меньше двух символов - допишем к числу ноль, предварительно приведя Date к string
        String DateString = String.valueOf(Date);
        if (DateString.length() < 2)
            DateString = "0" + DateString;

        String ActualDate = "(" + DateString + "." + MonthString + "." + Year + ")";

        //Получим имя папки с импортом за текущую дату
        String nameOfFolder4 = "Імпорт з онлайн системи " + ActualDate; //приклеим к имени папки импорта текущую дату

        //Перейдем в Избранное
        this.goToFavorites();

        pageFavorites.clickMainFolder(); //клик по "Мои закладки" - свернем Избранное
        pageFavorites.clickMainFolder(); //клик по "Мои закладки" - развернем Избранное. Нужно для корректного удаления папок ниже
        System.out.println(nameOfFolder4);
        for (int j = 1; j <= 5; j++) {
            folder.delFolder(nameOfFolder4); //удалим папки с сегодняшним импортом от прошлых тестов, если найдем
        }

        //Экспортируем Избранное
        pageFavorites.clickBtnImportExport(); //нажмем на кнопку импорта-экспорта
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.CheckboxImportExport));
        pageFavorites.clickCheckboxImportExport();//Выберем все папки для экспорта
        pageFavorites.clickBtnSaveExport();//Кнопка "Сохранить"

        //----------Импортируем ранее сохраненное Избранное-------------------------------------
        //Удалим папку Тестовая папка1, чтобы не мешала
        folder.delFolder(nameOfFolder);
        pageFavorites.clickBtnImportExport(); //нажмем на кнопку импорта-экспорта
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.CheckboxImportExport));//подождем загрузку формы
        waitElement(pageFavorites.TabDownload);
        pageFavorites.clickTabDownload(); //нажмем на таб "Загрузить"

        //Проверим существование файла после экспорта. Выше проверять нельзя, он пишется некоторое время, а sleep использовать не хочется
        Assert.assertTrue("File not created!", ipasExport.exists());
        //Проверим, что файл не нулевого размера
        Assert.assertTrue("File is empty!", ipasExport.length() > 0);

        //загрузим файл
        waitElement(pageFavorites.LoadIcon);
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.LoadIcon)); //подождем иконоку загрузки файла
        pageFavorites.clickLoadIcon();//нажмем на иконоку загрузки файла
        pageFavorites.WindowDownloadFile.sendKeys(ipasExport.getAbsolutePath()); //отправим кнопке загрузки путь к файлу
        pageFavorites.BtnDownload.click(); //кликнем по кнопке "Загрузить" окна импорта файла Избранного

        wait.until(ExpectedConditions.visibilityOf(pageFavorites.SuccessfulImportText)); //подождем текст об успешном импорте
        sleep(500);
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.SuccessfulImportFolder)); //подождем папку с импортом

        //Проверим, что в папке с импортом есть наша папка Тестовая папка1, в которой лежит сохраненный ранее документ
        //-------------------------------------------------------------
        pageFavorites.clickSuccessfulImportFolder(); //клик по папке экспорта
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + nameOfFolder + "']")));
        driver.findElement(By.xpath("//span[text()='" + nameOfFolder + "']")).click(); //клик по Тестовая папка1
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.testDocInListOfFolders)); //подождем документ в папке Тестовая папка1
        pageFavorites.clickOnTestDocInListOfFolders(); //клик по документу

        //Переходим на вкладку с открывшимся документом, а родительскую вкладку закрываем
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        Thread.sleep(1000);
        driver.switchTo().window(tabs2.get(1));

        //Проверяем, что там есть нужный текст
        List<WebElement> list5 = driver.findElements(By.xpath("//span[text()='№ 10191/7/16-1517']"));
        Assert.assertTrue("Text not found!", list5.size() > 0);
        //-----------------------------------Конец проверки наличия папки и документа в экспорте

        //Перейдем в Избранное
        this.goToFavorites();

        //удалим папки с сегодняшним импортом
        pageFavorites.clickMainFolder(); //клик по "Мои закладки" - свернем Избранное
        pageFavorites.clickMainFolder(); //клик по "Мои закладки" - развернем Избранное. Нужно для корректного удаления папок ниже
        folder.delFolder(nameOfFolder4);

        //Удалим экспортированный файл
        boolean deleted = ipasExport.delete();
        if (deleted)
            System.out.println("Экспортированный файл удален");

        //-------------------------------Конец импорта Избранного

    }

    //Данный метод реализует поиск по Избранному запроса, который передается в метод в качетсве параметра
    public void favoritiesSearchRequest(String requestSearchOfFavorites) throws InterruptedException {
        PageFavorites pageFavorites = new PageFavorites(driver);
        Document document = new Document(driver);
        Folder folder = new Folder(driver);
        MainPagePlatforma mainPagePlatforma = new MainPagePlatforma(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait.until(ExpectedConditions.visibilityOf(pageFavorites.visibilityOfFavorities)); //проверяем, страница Избранного загрузилась
        pageFavorites.searchInFaforitiesParam(requestSearchOfFavorites); //Повторно поищем в Избранном документ "requestSearchOfFavorites", должны найти
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.SearchingResults)); //Текст "Результаты поиска"
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.SearchingResultsNameDoc)); //название найденого документа
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.SearchingResultsNameFolder)); //имя папки найденного документа ("хлебные крошки")
    }


    //Данный метод делает переход в Избранное
    public void goToFavorites() throws InterruptedException {
        MainPagePlatforma mainPagePlatforma = new MainPagePlatforma(driver);
        PageFavorites pageFavorites = new PageFavorites(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait.until(ExpectedConditions.visibilityOf(mainPagePlatforma.bookmark1star));  //Звездочка в шапке
        sleep(500);
        mainPagePlatforma.clickBookmark1star();
        wait.until(ExpectedConditions.visibilityOf(mainPagePlatforma.bookmark2IPSstar)); //Переход в Избранное
        sleep(500);
        mainPagePlatforma.bookmark2IPSstar();
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.visibilityOfFavorities));
    }

    //Данный метод переиндексирует Избранное
    public void RestIndexFavorites() {
        String tailRestSearchFavorites = "/lzviewservice/favorites/bookmark/recreatefts";//"хвост" реста переиндексации Избранного без площадки
        //сольем имя площадки и хвост реста, получим полноценный рест для вызова переиндексации Избранного
        String tailRestSearchFavoritesAndROOTURL = getUrl() + tailRestSearchFavorites;

        driver.get(tailRestSearchFavoritesAndROOTURL); //вызов реста переиндексации Избранного
        //проверяем, что рест отработал. Страница должна содержать текст "name"
        List<WebElement> listRest2 = driver.findElements(By.xpath("//*[contains(text(),'name')]")); //считаем, сколько раз есть такой текст
        Assert.assertTrue("Rest don't work!", listRest2.size() > 0);//проверяем, что больше нуля раз
        driver.navigate().back(); //возвращаемся на страницу Избранного
    }


    //Данный метод проверяет наличие папки nameOfFolder в Избранном и удаляет все найденные
    public void Folders() throws InterruptedException {
        //WebDriverWait wait = new WebDriverWait(driver,10,200);
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        MainPagePlatforma mainPagePlatforma = new MainPagePlatforma(driver);
        PageFavorites pageFavorites = new PageFavorites(driver);
        Folder folder = new Folder(driver);
        String nameOfFolder = "Тестовая папка1";
        String nameOfFolder2 = "Тестовая папка2";
        String nameOfFolder3 = "Тестовая папка3";

        //authorizationPage.SwitchUAtoRU(); //Проверим язык и переключимся на рус, если текущий укр.
/*
        //Звездочка в шапке
        wait.until(ExpectedConditions.visibilityOf(mainPagePlatforma.bookmark1star));
        mainPagePlatforma.clickBookmark1star();

        //Перейти в Избранное
        wait.until(ExpectedConditions.visibilityOf(mainPagePlatforma.bookmark2star));
        mainPagePlatforma.clickBookmark2star();
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.visibilityOfFavorities));
*/
        //Проверим наличие папок "Тестовая папка1" и удалим все, если найдем
        folder.delFolder(nameOfFolder);

    }
}

/*
//bookmark-folders-list/div[1]/div/div[1]/span

//bookmark-folders-list/div[1]/div/div[1]/span


		<bookmark-folders-list class="ng-isolate-scope" folders="folder.subFolders" level="level + 1">
	<div class="bookmark-list-item ng-scope empty-folder"
		<div class="folder-name-text-block">
			<span class="folder-name-text ng-binding ng-scope" ng-if="folder.parentId != -1">Тестовая_папка1</span>

	<div class="bookmark-list-item ng-scope empty-folder"
		<div class="folder-name-text-block">
			<span class="folder-name-text ng-binding ng-scope" ng-if="folder.parentId != -1">Тестовая_папка1</span>


+
//div[@class='bookmark-list-item ng-scope empty-folder'][1]

-
//div[@class='bookmark-list-item ng-scope empty-folder']//span[text()='Тестовая_папка1']
//span[text()='Удалить']
//<span class="ng-binding">Удалить</span>
*

//div[@class='bookmark-list-item ng-scope empty-folder']//div[@class='folder-name-text-block'][1]/span[text()='Тестовая_папка1']
<div class="folder-name-text-block">



//div[@class='bookmark-list-item ng-scope empty-folder']//span[text()='Тестовая папка1']

html/body/ui-view/div/div[2]/div/div/div[2]/bookmark-folders-list/div/div[3]/bookmark-folders-list/div[2]/div
html/body/ui-view/div/div[2]/div/div/div[2]/bookmark-folders-list/div/div[3]/bookmark-folders-list/div[3]/div
html/body/ui-view/div/div[2]/div/div/div[2]/bookmark-folders-list/div/div[3]/bookmark-folders-list/div[4]/div

Проверим наличие папок Тестовая папка1

Алгоритм:
//1) В цикле по i перебираем локатор //bookmark-folders-list/div[i]/div/div[1]/span[text()='Тестовая папка1']
1) В цикле по i перебираем локатор //bookmark-folders-list/div[i]/div/div[1]/span
Убеждаемся, что get text from //bookmark-folders-list/div[i]/div/div[1]/span равен "Тестовая папка1" и считаем количество таких совпадений
(после каждого true добавляем +1 к переменной-счетчику count)
Если count>0 запускаем цикл перебора //bookmark-folders-list/div[i]/div/div[1]/span[text()='Тестовая папка1'],
по найденному выше локатору определяем соответствующие "три точки" каждой папки 'Тестовая папка1' и через контекстное меню удаляем такую папку.

Посчитать количество папок Тестовая папка1 по локатору //span[text()='Тестовая папка1']

//bookmark-folders-list/div[4]/div/div[1]/span[text()='Тестовая папка1']
2)



Три точки по имени:
//span[text()='Тестовая папка1']/parent::div/parent::div/i[@class='fa fa-ellipsis-v points-icon ng-scope']

i-тая папка 'Тестовая папка1':
//bookmark-folders-list/div[i]/div/div[1]/span[text()='Тестовая папка1']

Три точки по имени в i-той папке 'Тестовая папка1':
//bookmark-folders-list/div[i]/div/div[1]/span[text()='Тестовая папка1']/parent::div/parent::div/i[@class='fa fa-ellipsis-v points-icon ng-scope']

Пункт меню удалить у папки:
html/body/ui-view/div/div[2]/div/div/div[2]/bookmark-folders-list/div/div[3]/bookmark-folders-list/div[3]/div/div[2]/ul/li[2]/div[1]/span

для i-той ОДИНАКОВОЙ папки:
//bookmark-folders-list/div/div[3]/bookmark-folders-list/div[i]/div/div[2]/ul/li[2]/div[1]/span
 xpath, которому соответствуют все папки Избранного:
 //bookmark-folders-list//bookmark-folders-list/div/div

Три точки по имени:
//span[text()='Тестовая папка1']/parent::div/parent::div/i[@class='fa fa-ellipsis-v points-icon ng-scope']


Пункт меню редактировать:
html/body/ui-view/div/div[2]/div/div/div[2]/bookmark-folders-list/div/div[3]/bookmark-folders-list/div[3]/div/div[2]/ul/li[3]/div[1]/span



Пункт меню удалить у папки:
html/body/ui-view/div/div[2]/div/div/div[2]/bookmark-folders-list/div/div[3]/bookmark-folders-list/div[3]/div/div[2]/ul/li[2]/div[1]/span

для i-той ОДИНАКОВОЙ папки:
		  //span[text()='Тестовая папка1']//bookmark-folders-list/div/div[3]/bookmark-folders-list/div[i]/div/div[2]/ul/li[2]/div[1]/span

		  //span[text()='Тестовая папка1']/parent::div/parent::div/i[@class='fa fa-ellipsis-v points-icon ng-scope']/



Меню папки по названию:
//span[text()='Тестовая папка1']/parent::div/parent::div//ul
<li ng-click="addFolder(folder); $event.stopPropagation();">
	<div class="btn-folder" uib-tooltip="Добавить подпапку">
		<i class="fa fa-plus-circle"/>
		<span class="ng-binding">Добавить подпапку</span>
	</div>
</li>
<li class="ng-scope" ng-if="folder.parentId != -1" ng-click="deleteFolder(folder); $event.stopPropagation();">
	<div class="btn-folder" uib-tooltip="Удалить">
		<i class="fa fa-trash-o"/>
		<span class="ng-binding">Удалить</span>
	</div>
</li>
<li class="ng-scope" ng-if="folder.parentId != -1" ng-click="editFolder(folder); $event.stopPropagation();">
	<div class="btn-folder" uib-tooltip="Редактировать">
		<i class="fa fa-pencil"/>
		<span class="ng-binding">Редактировать</span>
	</div>
</li>
<li class="ng-scope" ng-if="folder.parentId != -1" ng-click="moveFolder(folder); $event.stopPropagation();">
	<div class="btn-folder" uib-tooltip="Переместить">
		<i class="fa fa-folder-open"/>
		<span class="ng-binding">Переместить</span>
	</div>
</li>
<li class="ng-scope" ng-if="folder.parentId != -1" ng-click="sendToDesktop(folder); $event.stopPropagation();">
	<div class="btn-folder" uib-tooltip="Отправить на Рабочий стол">
		<i class="fa fa-laptop"/>
		<span class="ng-binding">Отправить на Рабочий стол</span>
	</div>
</li>

Окончательный вариант Меню папки по названию:
//span[text()='Тестовая папка1']/parent::div/parent::div//ul//span[text()='Добавить подпапку']
//span[text()='Тестовая папка1']/parent::div/parent::div//ul//span[text()='Удалить']
//span[text()='Тестовая папка1']/parent::div/parent::div//ul//span[text()='Редактировать']
//span[text()='Тестовая папка1']/parent::div/parent::div//ul//span[text()='Переместить']
//span[text()='Тестовая папка1']/parent::div/parent::div//ul//span[text()='Отправить на Рабочий стол']

-----------------------------------
Меню документа в папке любого уровня Избранного
//span[text()='gggg']/parent::div/parent::div/i[@class='fa fa-ellipsis-v points-icon'] - Три точки документа gggg при открытой папке, которая его содержит
//span[text()='gggg']/parent::div/parent::div//span[text()='Редактировать'] - пункт "Редактировать" документа ggg
//span[text()='gggg']/parent::div/parent::div//span[text()='Удалить'] - пункт "Удалить" документа ggg

Заменим в этих span жесткое название документа "gggg" на название, содержащее 'Письмо от 18.05.2009 № 10191/7/16-1517')
//span[contains(.,'Письмо от 18.05.2009 № 10191/7/16-1517')]

ИТОГ Меню документа в папке любого уровня Избранного:
//span[contains(.,'Письмо от 18.05.2009 № 10191/7/16-1517')]/parent::div/parent::div/i[@class='fa fa-ellipsis-v points-icon'] - ТРИ ТОЧКИ ДОКУМЕНТА ggg из папки Тестовая папка1
//span[contains(.,'Письмо от 18.05.2009 № 10191/7/16-1517')]/parent::div/parent::div//span[text()='Редактировать'] - пункт "редактировать" документа ggg из папки Тестовая папка1
//span[contains(.,'Письмо от 18.05.2009 № 10191/7/16-1517')]/parent::div/parent::div//span[text()='Удалить']
------------------------------------

 Первый элемент результатов поиска:
 //div[@class='search-list']//lz-list/div[1]/div/a

  j-тый элемент результатов поиска:
 //div[@class='search-list']//lz-list/div[j]/div/a

 //a[contains(text(),'Керівнику')]
 //div[text()='СИСТЕМА']
  By.xpath("//*[contains(text(),'Про особливості окремих норм Закону')]"));
 //span[text()='№ 10191/7/16-1517']

search for all elements containing the given text:
List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
Assert.assertTrue("Text not found!", list.size() > 0);
 *
 */

//Перейдем в документ
        /*	В данном блоке я находил нужный документ поиском и открывал его. В открывшемся документе есть предзаполненное окно
        с поисковым запросом. Оно мешает кликать на абзац - джава почему-то теряет фокус из-за таких окон.
        Вынужден открывать документ по прямой ссылке

        //зададим поисковый запрос
        driver.get("https://ips-dev.ligazakon.net/");
        wait.until(ExpectedConditions.visibilityOf(pageFavorites.searchField));
        pageFavorites.searchRequest2("18.05.2009 N 10191/7/16-1517 Про особливості окремих норм Закону письмо");

        //откроем первый результат
        pageFavorites.clickFirstElementOfList();

        //ArrayList<String> tabs_windows = new ArrayList<String> (driver.getWindowHandles());
        //driver.switchTo().window(tabs_windows.get(tab_index));

        //Переходим на вкладку с открывшимся документом, а родительскую вкладку закрываем
        driver.close();
        driver.switchTo().window(tabs.get(1));
        */

//Откроем нужный документ по прямой ссылке
//driver.navigate().to("https://ips-"+LogInClass.host+".ligazakon.net/document/view/GDPI9556");

