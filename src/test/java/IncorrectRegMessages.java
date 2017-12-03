import MethodsCommon.MethodsCommon;
import Pages.AuthPage;
import Pages.PltMainPage;
import Pages.RegistrationPage;
import com.thoughtworks.gauge.ContinueOnFailure;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

import static MethodsCommon.Driver.driver;

public class IncorrectRegMessages extends MethodsCommon {
    private static PltMainPage pltMainPage = new PltMainPage(driver);
    private static RegistrationPage regPage = new RegistrationPage(driver);
    private static IncorrectRegMessages incorrectRegMessages = new IncorrectRegMessages();
    private static AuthPage authPage = new AuthPage(driver);
    Log log = LogFactory.getLog(IncorrectRegMessages.class);

    @ContinueOnFailure(AssertionError.class)
    @Step("Open registration page")
    /* Кликаем по кнопке "Увійти"
    На открывшейся странице регистрации ожидаем появления ссылки "Не зареєстровані?"
    Кликаем ссылку "Не зареєстровані?" */
    public void openRegPage() throws InterruptedException {
        pltMainPage.sign_in_btn.click();
        waitForElementDisplay(authPage.not_registered_link);
        authPage.not_registered_link.click();
    }

    public void refreshRegistrationForm() throws InterruptedException {
       driver.navigate().refresh();// F5
       waitForElementDisplay(authPage.not_registered_link);
       authPage.not_registered_link.click();// Клик по ссылке "Не зареєcтровані?"
    }

    @ContinueOnFailure(AssertionError.class)
    @Step("Click registration button in registration form")
    public void clickRegBtn() throws InterruptedException {
        waitForElementDisplay(regPage.registr_btn);
        regPage.registr_btn.click();
    }

    @ContinueOnFailure(AssertionError.class)
    @Step("Check message for empty <field> ")
    public void testEmptyField(String fieldName) throws InterruptedException {
        switch (fieldName) {
            case "First name":
                regPage.first_name_field.clear();
                regPage.after_reg_text.click();
                shouldSeeElementWithText(regPage.empty_first_name_message, "Поле Ім'я має бути заповнене");
                break;
            case "Last name":
                regPage.last_name_field.clear();
                regPage.after_reg_text.click();
                shouldSeeElementWithText(regPage.empty_last_name_message, "Поле Прізвище має бути заповнене");
                break;
            case "Email":
                regPage.email_field.clear();
                regPage.after_reg_text.click();
                shouldSeeElementWithText(regPage.empty_email_message, "Поле e-mail має бути заповнене");
                break;
            case "Phone":
                regPage.phone_field.clear();
                regPage.after_reg_text.click();
                shouldSeeElementWithText(regPage.empty_phone_message, "Поле Телефон має бути заповнене");
                break;
            case "Company name":
                regPage.company_field.clear();
                regPage.after_reg_text.click();
                shouldSeeElementWithText(regPage.empty_company_message, "Поле Назва компанії має бути заповнене");
                break;
            default:
                log.info("Неверное название поля в файла.spec");
        }
    }

    @ContinueOnFailure(AssertionError.class)
    @Step("Check message for empty field <Table>")
     /*Шаги (для каждого значения из строк таблицы):
    1. Очистить тестируемое поле
    2. Кликнуть по тексту мимо поля (чтобы поле было не в фокусе)
    ОР: Отображется соответствующее проверяемому полю сообщение
     */
    public void checkMessageEmptyField(Table table) throws InterruptedException {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            incorrectRegMessages.testEmptyField(row.getCell(columnNames.get(0))); //первый столбец
        }
    }

    @ContinueOnFailure(AssertionError.class)
    @Step("Refresh page")
    public void refreshPage() throws InterruptedException {
        driver.navigate().refresh();
    }

    @ContinueOnFailure(AssertionError.class)
    @Step("Click social <network button>")
    public void clickSocNetworkBtn(String socNetwork) throws InterruptedException {
        switch (socNetwork) {
            case "facebook": regPage.facebook_btn.click();
                break;
            case "google": regPage.google_btn.click();
                break;
            case "linkedin": regPage.linkedin_btn.click();
                break;
            default:
                log.info("Неверное название поля в файла.spec");
        }
    }

    @ContinueOnFailure(AssertionError.class)
    @Step("Check message for social network unmarked checkbox <Table>")
    /*Шаги (для каждого значения из строк таблицы):
    1. Обновить страницу - перейти на страницу регистрации через ссылку "Не зарегистрированы?"
    2. Не отмечая чекбокс "Я ознайомлений та..." нажать на кнопку соц. сети
    ОР: Отображется сообщение
     */
    public void checkMessageSocNetworkUnmarkedCheckbox(Table table) throws InterruptedException {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            refreshRegistrationForm();
            waitForElementDisplay(regPage.facebook_btn);
            incorrectRegMessages.clickSocNetworkBtn(row.getCell(columnNames.get(0))); //первый столбец
            shouldSeeElementWithText(regPage.unmarked_checkbox_message, "Ви не погодилися з правилами обробки персональних даних");
                    }
    }

    @ContinueOnFailure(AssertionError.class)
    @Step("User input incorrect Phone <user input>")
    public void incorrectPhoneInput(String userInput) throws InterruptedException {
        switch (userInput) {
            case "Symbols": inputText(regPage.phone_field, "(380)50-455-6856");
                regPage.after_reg_text.click();
                break;
            case "Less than 7 digits": inputText(regPage.phone_field, "445698");
                regPage.after_reg_text.click();
                break;
            case "More than 16 digits": inputText(regPage.phone_field, "+11112222333344445");
                regPage.after_reg_text.click();
                break;
            default:
                log.info("Неверное название поля в файла.spec");
        }
    }

    @ContinueOnFailure(AssertionError.class)
    @Step("Check message for phone field <Table>")
    /*Шаги (для каждого значения из строк таблицы):
     1. Обновить страницу - перейти на страницу регистрации через ссылку "Не зарегистрированы?"
     2. Ввести невалидный номер тел.
     ОР: Отображается сообщение
         */
    public void checkMessagePhoneField(Table table) throws InterruptedException {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            refreshRegistrationForm();
            waitForElementDisplay(regPage.phone_field);
            incorrectRegMessages.incorrectPhoneInput(row.getCell(columnNames.get(0))); //первый столбец
            shouldSeeElementWithText(regPage.incorrect_phone_message, "Поле Телефон може містити від 7 до 16 цифр та знак +");
        }
    }

    @ContinueOnFailure(AssertionError.class)
    @Step("User input incorrect e-mail <user input>")
    public void incorrectEmailInput(String userInput) throws InterruptedException {
        switch (userInput) {
            case "Cyrillic": inputText(regPage.email_field, "вдоавдавд@gmail.com");
                regPage.after_reg_text.click();
                break;
            case "Without @": inputText(regPage.email_field, "some_mail");
                regPage.after_reg_text.click();
                break;
            case "Without .": inputText(regPage.email_field, "some_mail@gmailcom");
                regPage.after_reg_text.click();
                break;
            case "With $*": inputText(regPage.email_field, "some*mail&@gmail.com");
                regPage.after_reg_text.click();
                break;
            default:
                log.info("Неверное название поля в файла.spec");
        }

    }

    @ContinueOnFailure(AssertionError.class)
    @Step("Check message for e-mail field <Table>")
    /*Шаги (для каждого значения из строк таблицы):
    1. Обновить страницу - перейти на страницу регистрации через ссылку "Не зарегистрированы?"
    2. Ввести невалидный e-mail
    ОР: Отображается сообщение*/
    public void checkMessageEmailField(Table table) throws InterruptedException {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            refreshRegistrationForm();
            waitForElementDisplay(regPage.email_field);
            incorrectRegMessages.incorrectEmailInput(row.getCell(columnNames.get(0))); //первый столбец
            shouldSeeElementWithText(regPage.incorrect_email_message, "Використовуйте лише літери (a–z), - , _ , цифри та крапки.");
        }
    }
}

