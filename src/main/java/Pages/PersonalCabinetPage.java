package Pages;

import MethodsCommon.MethodsCommon;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalCabinetPage extends MethodsCommon{
    private WebDriver driver;

    public PersonalCabinetPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    Log log = LogFactory.getLog(PersonalCabinetPage.class);

    @FindBy(id = "input1")
    private WebElement new_email_field;

    @FindBy(xpath = "//*[contains(@data-test, 'save-mail')]")
    private WebElement save_button;

    @FindBy(xpath = "//*[contains(@ng-if, 'isErrorLength')]")
    private WebElement new_email_message;

    @FindBy(xpath = "//*[@class ='user-email ng-binding']")
    private WebElement email_name;

    public WebElement getSaveButton() { return save_button; }
    public WebElement getNewEmailMessage() {return new_email_message;}
    public WebElement getNewEmailField() {return new_email_field;}
    public WebElement getEmailName(){return email_name;}

    public void BannedDomainInput(String domainInput) throws InterruptedException {
        switch (domainInput) {
            case "mail.ru": inputText(new_email_field, "some.name@mail.ru");
                break;
            case "mail.ua": inputText(new_email_field, "some.name@mail.ua");
                break;
            case "yandex.ru": inputText(new_email_field, "some.name@yandex.ru");
                break;
            case "yandex.ua": inputText(new_email_field, "some.name@yandex.ua");
                break;
            case "inbox.ru": inputText(new_email_field, "some.name@inbox.ru");
                break;
            case "inbox.ua": inputText(new_email_field, "some.name@inbox.ua");
                break;
            case "list.ru": inputText(new_email_field, "some.name@list.ru");
                break;
            case "bk.ru": inputText(new_email_field, "some.name@bk.ru");
                break;
            case "ok.ru": inputText(new_email_field, "some.name@ok.ru");
                break;
            case "vk.ru": inputText(new_email_field, "some.name@vk.ru");
                break;
            default:
                log.info("Неверное название поля в файла.spec");
        }

    }
    public void incorrectNewEmailInput(String newEmailInput) throws InterruptedException {
        switch (newEmailInput) {
            case "Cyrillic":
                inputText(new_email_field, "двлотст@gmail.com");
                break;
            case "Without @":
                inputText(new_email_field, "my.mail");
                break;
            case "Without .":
                inputText(new_email_field, "email@gmailcom");
                break;
            case "With $#":
                inputText(new_email_field, "some#mail$@gmail.com");
                break;
            default:
                log.info("Неверное название поля в файла.spec");
        }
    }
    public void refreshEmailPage()throws InterruptedException {
        driver.navigate().refresh();
        waitForElementDisplay(new_email_field);
    }

}

//import static test.java.Driver.driver;

