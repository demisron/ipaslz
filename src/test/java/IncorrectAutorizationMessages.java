import MethodsCommon.MethodsCommon;
import Pages.AuthPage;
import Pages.PltMainPage;
import com.thoughtworks.gauge.ContinueOnFailure;
import com.thoughtworks.gauge.Step;
import static org.junit.Assert.assertEquals;
import static MethodsCommon.Driver.driver;

public class IncorrectAutorizationMessages {
    private static AuthPage authPage;
    private static PltMainPage pltMainPage;
    @Step("Open authorization page")
    public void openAuthPage() throws InterruptedException {
        authPage = new AuthPage(driver);
        pltMainPage = new PltMainPage(driver);
        pltMainPage.clickAndWaitNextElement(pltMainPage.sign_in_btn, authPage.login_btn);
    }
    @ContinueOnFailure(AssertionError.class)
    @Step("Check authorization messages for empty fields")
    /* Не заполняя поля email и password нажать "Увійти"
    ОР: Сообщения "Поле ... має бути заповнене" */
    public void checkAuthEmptyFieldsMessage() throws InterruptedException {
        authPage.email_input.clear();
        authPage.passwd_input.clear();
        authPage.login_btn.click();
        assertEquals("Сообщение, если не заполнено e-mail (укр.)", "Поле e-mail має бути заповнене", authPage.empty_email_message.getText());
        assertEquals("Сообщение, если не заполнен пароль", "Поле Пароль має бути заповнене", authPage.empty_passwd_message.getText());
    }
    @ContinueOnFailure(AssertionError.class)
    @Step("Check message for incorrect passwd")
    /* Шаги: Ввести логин существующего пользователя
    Ввести неверный пароль
    ОР: Сообщение "Невірний логін або пароль"    */
    public void testIncorrectPasswd() throws InterruptedException {
        MethodsCommon user_inputs_passwd = new MethodsCommon();
        authPage.email_input.clear();
        authPage.email_input.sendKeys("487873737_some@mailinator.com");
        authPage.passwd_input.clear();

        authPage.passwd_input.sendKeys(user_inputs_passwd.inputRandString());
        authPage.login_btn.click();

        assertEquals("Сообщение, если неверный пароль (укр.)", "Невірний логін або пароль", authPage.incorrect_passwd.getText());
    }
    @ContinueOnFailure(AssertionError.class)
    @Step("Check message for email without @")
    /* Шаги: Ввести email без @
    ОР: Сообщение "Введіть правильний e-mail" */
    public void testIncorrectEmail() throws InterruptedException {
        MethodsCommon user_inputs_email = new MethodsCommon();
        authPage.email_input.clear();
        authPage.email_input.sendKeys(user_inputs_email.inputRandString());
        authPage.login_btn.click();

        assertEquals("Сообщение, если email без @ (укр.)", "Введіть правильний e-mail", authPage.incorrect_email.getText());
    }
}