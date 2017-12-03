package Maystrenko;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthorizationPage extends PageObject {

    public AuthorizationPage (WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="descendant::input[@id='email'][position()=3]")
    public WebElement loginField;

    @FindBy(xpath="//input[@id='password']")
    private WebElement passwordField;

    //@FindBy(xpath="//button[@class='btn btn-primary log-btn ng-binding']")
    @FindBy(xpath="//div[@class='row btn-set']/button")
    private WebElement loginButton;

    @FindBy(xpath="//div[@class='row auth-title ng-binding']")
    private WebElement textOfAuthorizationPage;

    public void enterLogin (String login) {
        loginField.clear();
        loginField.sendKeys(login);
    }

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

}