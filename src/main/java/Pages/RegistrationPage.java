package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "send-comment-rating-form")
    public WebElement registr_btn;// Кнопка "Зарегистрироваться" на форме регистрации

    @FindBy(id = "name")
    public WebElement first_name_field;

    @FindBy(xpath = "//span[contains(@ng-show, 'regForm.name')]")
    public WebElement empty_first_name_message;

    @FindBy(id = "lastname")
    public WebElement last_name_field;

    @FindBy(xpath = "//span[contains(@ng-show, 'regForm.lastname')]")
    public WebElement empty_last_name_message;

    @FindBy(xpath = "(//*[@id='email'])[4]")
    public WebElement email_field;

    @FindBy(xpath = "//span[contains(@ng-show, 'regForm.email')]")
    public WebElement empty_email_message;

    @FindBy(xpath = "//*[contains(@ng-show, 'regForm.email.$error.pattern')]")
    public WebElement incorrect_email_message;

    @FindBy(xpath = "(//*[@id='phone'])[2]")
    public WebElement phone_field;

    @FindBy(xpath = "//span[contains(@ng-show, 'regForm.phone')]")
    public WebElement empty_phone_message;

    @FindBy(xpath = "//span[contains(@ng-show, 'regForm.phone.$error.pattern')]")
    public WebElement incorrect_phone_message;

    @FindBy(id = "company")
    public WebElement company_field;

    @FindBy(xpath = "//span[contains(@ng-show, 'regForm.company')]")
    public WebElement empty_company_message;

    @FindBy(xpath = "//span[contains(@ng-show, 'regForm.confirmDataSaving')]")
    public WebElement unmarked_checkbox_message;

    @FindBy(xpath = "//*[@class='row social-btns']//*[contains(@class, 'facebook-icon')]")
    public WebElement facebook_btn;

    @FindBy(xpath = "//*[@class='row social-btns']//*[contains(@class, 'google-icon')]")
    public WebElement google_btn;

    @FindBy(xpath = "//*[@class='row social-btns']//*[contains(@class, 'linkedin-icon')]")
    public WebElement linkedin_btn;

    @FindBy(xpath = "//*[@id='help-container']//*[@class='sub-email-title ng-binding']")
    public WebElement after_reg_text;// текст "Після реєстрации на електронну пошту надійде пароль..."
}

