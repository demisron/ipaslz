package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static MethodsCommon.Driver.driver;
import static MethodsCommon.Func.byClickElement;
import static MethodsCommon.Func.waitElement;

public class AuthPage {
    private WebDriver driver;

    public AuthPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    JavascriptExecutor js;
    @FindBy(xpath="(//input[@id='email'])[3]")
    public WebElement email_input;

    @FindBy(id = "password")
    public WebElement passwd_input;

    @FindBy(xpath = "//button[contains(text(),'Увійти')]")
    public WebElement login_btn;

    @FindBy(xpath = "//a[@href='/signinup/auth']")
    public WebElement lnkLogin;

    @FindBy(xpath = "//span[contains(@ng-show, 'sign_in.email.$error.required')]")
    public WebElement empty_email_message;

    @FindBy(xpath = "//span[contains(@ng-show, 'sign_in.password.$error.required')]")
    public WebElement empty_passwd_message;

    @FindBy(xpath = "//div[contains(@ng-if, 'isShowErrorLoginAndPassword')]")
    public WebElement incorrect_passwd;

    @FindBy(xpath = "//span[contains(@ng-show, 'sign_in.email.$error.pattern')]")
    public WebElement incorrect_email;

    @FindBy(xpath = "//a[contains(text(),'Не зареєстровані?')]")
    public WebElement not_registered_link;

    public void InputFieldClick(String login, String pwd) throws InterruptedException {

        lnkLogin.click();
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;
        } // else throw...

        waitElement(not_registered_link);
        js.executeScript("window.angular.element('.auth #email').scope().reg.email = \'"+login+"\';");
        js.executeScript("window.angular.element('.auth #password').scope().auth.password = \'"+pwd+"\';");
        js.executeScript("window.angular.element('.forms.auth button').trigger('click')");
    }

    public static AuthPage loadUsing( WebDriver driver) {
        String url = System.getenv("ROOTURL");
        driver.get(url);
        return new AuthPage(driver);
    }

}

