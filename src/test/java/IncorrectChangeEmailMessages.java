import MethodsCommon.MethodsCommon;
import Pages.PersonalCabinetPage;
import Pages.PltMainPage;
import com.thoughtworks.gauge.ContinueOnFailure;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

import static MethodsCommon.Driver.driver;
//import static test.java.Driver.driver;

public class IncorrectChangeEmailMessages extends MethodsCommon {
    private static PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(driver);
    Log log = LogFactory.getLog(PersonalCabinetPage.class);

    @Step("Open change e-mail page in Profile")
    /*1.Кликнуть по иконке пользователя в хедере.
    2. Кликнуть по ссылке "Мой профиль"
    3. Переключиться к новой вкладке
    4. Кликнуть по ссылке "Изменение e-mail"*/
    public void openProfilePage() throws InterruptedException {
        PltMainPage pltPage = new PltMainPage(driver);
        pltPage.openProfilePage();
        }

    @ContinueOnFailure(AssertionError.class)
    @Step("Check message for empty New e-mail field")
    /*Шаги: 1. Ничего не вводить в поле "Новый e-mail"
    2. Нажать кнопку "Сохранить"
    ОР: отображается сообщение */
    public void checkMessageEmailEmpty() throws InterruptedException {
      personalCabinetPage.getSaveButton().click();
      shouldSeeElementWithText(personalCabinetPage.getNewEmailMessage(), "Поле e-mail должно быть заполнено");
      }

    @ContinueOnFailure(AssertionError.class)
    @Step("Check message for other users e-mail input")
    /*Шаги: 1. Ввести e-mail, используемый другим пользователем"
    2. Нажать кнопку "Сохранить"
    ОР: отображается сообщение */
    public void checkMessageOtherUserEmail() throws InterruptedException {
      inputText(personalCabinetPage.getNewEmailField(),"dealer91@mailinator.com");
      personalCabinetPage.getSaveButton().click();
      shouldSeeElementContainsText(personalCabinetPage.getNewEmailMessage(),"E-mail уже зарегистрирован в системе:");
      }

    @ContinueOnFailure(AssertionError.class)
    @Step("Check message for current e-mail input")
    /*Шаги: 1. Ввести e-mail текущего пользователя"
    2. Нажать кнопку "Сохранить"
    ОР: отображается сообщение */
    public void checkMessageCurrentUserEmail() throws InterruptedException {
      String currentEmail = personalCabinetPage.getEmailName().getText();
      inputText(personalCabinetPage.getNewEmailField(),currentEmail);
      personalCabinetPage.getSaveButton().click();
      shouldSeeElementContainsText(personalCabinetPage.getNewEmailMessage(),"Попытка сменить e-mail на такой же:");
      }

    @ContinueOnFailure(AssertionError.class)
    @Step("Check messages for banned domains <Table>")
     /*Шаги (для каждого значения из строк таблицы):
    1. Ввести e-mail, входящий в список запрещенных ресурсов
    2. Нажать кнопку "Сохранить"
    ОР: Отображется сообщение о том, что введенный e-mail зарегистрирован в запрещенном домене*/
    public void checkMessageBannedDomain(Table table) throws InterruptedException {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();

        for (TableRow row : rows) {
            personalCabinetPage.BannedDomainInput(row.getCell(columnNames.get(0))); //первый столбец
            personalCabinetPage.getSaveButton().click();
            shouldSeeElementContainsText(personalCabinetPage.getNewEmailMessage(),"E-mail");
            shouldSeeElementContainsText(personalCabinetPage.getNewEmailMessage(),"зарегистрирован в запрещенном домене: " +
                    "@mail.ru, @mail.ua, @yandex.ru, @yandex.ua, @inbox.ru, @inbox.ua, @list.ru, @bk.ru, @ok.ru, @vk.ru");

        }
    }

    @ContinueOnFailure(AssertionError.class)
    @Step("Check message for 4th change e-mail request")
    /* Предусловие: выполнена авторизация под пользователем, который уже три раза запросил смену e-mail
    Шаги: 1. Ввести валидный e-mail "
    2. Нажать кнопку "Сохранить"
    ОР: отображается сообщение, что пользователь забронировал другой e-mail*/
    public void checkMessageFourthRequest() throws InterruptedException {
        inputText(personalCabinetPage.getNewEmailField(),"new121@gmail.com" );
        personalCabinetPage.getSaveButton().click();
        shouldSeeElementContainsText(personalCabinetPage.getNewEmailMessage(),"Этот пользователь уже забронировал другой e-mail:");
    }

    @ContinueOnFailure(AssertionError.class)
    @Step("Check message for invalid new e-mail <Table>")
    /*Шаги (для каждого значения из строк таблицы):
    1. Ввести невалидный e-mail
    2. Нажать "Сохранить"
    ОР: Отображается сообщение, что нужно ввести правильный e-mail*/
    public void checkMessageInvalidNewEmail(Table table) throws InterruptedException {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            personalCabinetPage.refreshEmailPage();
            personalCabinetPage.incorrectNewEmailInput(row.getCell(columnNames.get(0))); //первый столбец
            personalCabinetPage.getSaveButton().click();
            shouldSeeElementWithText(personalCabinetPage.getNewEmailMessage(), "Введите правильный e-mail");
        }
    }
}

