package Maystrenko;
/*
  Created by MaystrenkoE on 14.06.2017.
 */

import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static MethodsCommon.Driver.driver;
import static MethodsCommon.Func.getUrl;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestMGaugeReferent {//extends PageObject {

    WebDriverWait wait = new WebDriverWait(driver, 100);

    @Step("ReferentCreateEdit")
    /*Сервис Референт. Проверяем возможность постановки документа на контроль и факт его наличия после постановки на котнтроль.
    Проверяем, что при постановке на контроль того документа, который уже контролируется, открывается окно редактирования
    существующего референта.
    Кроме того проверяю перемещение референта в другую папку, которую создаю на лету при редактировании референта.
    */

    public void ReferentCreateEdit() throws InterruptedException {
        PageReferent pageReferent = new PageReferent(driver);
        Document document = new Document(driver);
        FolderReferent folderReferent = new FolderReferent(driver);
        MainPagePlatforma mainPagePlatforma = new MainPagePlatforma(driver);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        String nameOfFolder = "TestFolderReferent";
        String nameOfFolder2 = "TestFolderForTransfer";
        String nameOfFolder3 = "TestFolderReferent3";
        String TextWeAreLookingFor = "Пегінтерферон Лазоришинець";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        mainPagePlatforma.clickGoToReferent();//Перейдем в Референты
        folderReferent.delFolder(nameOfFolder); //Удалим все папки TestFolderReferent, которые найдем
        folderReferent.delFolder(nameOfFolder2); //Удалим все папки TestFolderForTransfer, которые найдем
        folderReferent.createFolder(nameOfFolder); //Создадим папку TestFolderReferent

        mainPagePlatforma.SearchDoc(TextWeAreLookingFor); //Поищем документ по запросу в интеллект.поиске
        wait.until(ExpectedConditions.visibilityOf(mainPagePlatforma.firstFoundDocInList)); //первый документ в списке найденного
        mainPagePlatforma.clickFirstFoundDocInList();//Клик по 1-му документу списка
        mainPagePlatforma.GoToNextTab();//Переходим на вкладку с открывшимся документом, а родительскую вкладку закрываем
        wait.until(ExpectedConditions.visibilityOf(document.headerOfFullDoc));//ждем шапку

        document.pressBlueBtn();
        document.clickAddToControl("TestFolderReferent");

        //Сохраним название референта, в дельнейшем используем для поиска среди референтов
        String getTextNameOfDocInReferentForm = document.nameOfDocInReferentForm.getText().toString();

        //Проверим, что в форме есть текст про контроль и нет текста про редактирование
        assertTrue(pageReferent.isTextPresent("Контроль документа"));
        assertFalse(pageReferent.isTextPresent("Редактирование референта"));
        //folderReferent.saveInFolderReferent("TestFolderReferent");
        document.clickButtonSubmitBtn(); //сохраним референт

        //Повторно поставим документ на контроль, теперь форма должна называться "Редактирование референта"
        document.pressBlueBtn();
        document.clickAddToControl(nameOfFolder);

        //Проверим, что в форме есть редактирование "Редактирование референта".
        //Проверять отсутствие "Контроль документа" нельзя, он есть, но скрыт
        assertTrue(pageReferent.isTextPresent("Редактирование референта"));
        //folderReferent.saveInFolderReferent("TestFolderReferent");
        document.clickButtonSubmitBtn(); //сохраним референт

        mainPagePlatforma.clickGoToReferent();//Перейдем в Референт
        wait.until(ExpectedConditions.visibilityOf(pageReferent.plusBtnCreateFolderReferent));//ждем загрузку страницы
        folderReferent.clickFolderOfDocReferent(); //Переходим в папку с созданным референтом

        System.out.println("STEP Referent-CreateEdit - WELL DONE!");
    }


    //Данный метод переиндексирует Референт - Пока исправлено только название
    public void RestIndexFavorites() {
        String tailRestSearchFavorites = "/lzviewservice/favorites/bookmark/recreatefts";//"хвост" реста переиндексации Избранного без площадки
        //сольем имя площадки и хвост реста, получим полноценный рест для вызова переиндексации Избранного
        String tailRestSearchFavoritesAndROOTURL = getUrl() + tailRestSearchFavorites;

        driver.get(tailRestSearchFavoritesAndROOTURL); //вызов реста переиндексации Избранного
        //проверяем, что рест отработал. Страница должна содержать текст "name"
        List<WebElement> listRest2 = driver.findElements(By.xpath("//*[contains(text(),'name')]")); //считаем, сколько раз есть такой текст
        assertTrue("Rest don't work!", listRest2.size() > 0);//проверяем, что больше нуля раз
        driver.navigate().back(); //возвращаемся на страницу Избранного
    }


}