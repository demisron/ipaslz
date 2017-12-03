package Maystrenko;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static MethodsCommon.Func.waitElement;
import static java.lang.Thread.sleep;

public class PageReferent extends PageObject {
    public PageReferent(WebDriver driver) {
        super(driver);
    }

    //Кнопка подтверждения манипуляции с папкой референтов
    @FindBy(css = ".btn.btn-primary.btn-lg.ng-binding")
    public WebElement submitBtn;

    @FindBy(css = "div.name-row > div.add-folder > i.fa-plus-circle")//Кнопка "+" создания папки на странице референтов
    public WebElement plusBtnCreateFolderReferent;

    public void clickSubmitBtn() throws InterruptedException {
        //wait.until(ExpectedConditions.visibilityOf(submitBtn));
        //sleep (500);
        waitElement(submitBtn);
        submitBtn.click();
    }

    //Метод для определения наличия на странице текста с классом Span
    public void myAssertSpan(String textSpan) {
        List<WebElement> listAssertSpan = driver.findElements(By.xpath("//span[contains(.,'"+textSpan+"')]"));
        Assert.assertTrue("Text not found!", listAssertSpan.size() > 0);
    }

    //Метод для определения наличия на странице текста с классом Div
    public void myAssertDiv(String textDiv) {
        List<WebElement> listAssertDiv = driver.findElements(By.xpath("//div[contains(text(),'" + textDiv + "')]"));
        Assert.assertTrue("Text not found!", listAssertDiv.size() > 0);
    }

    //Универсальный метод для определения наличия текста на странице,
    // потом в тесте его необходимо вызывать как assertTrue(isTextPresent("Your text"));
    protected boolean isTextPresent(String text){
        try{
            boolean b = driver.getPageSource().contains(text);
            return b;
        }
        catch(Exception e){
            return false;
        }
    }

    //Универсальный метод попытки кликнуть по вебэлементу. 200 попыток с паузами по 20 мс
    public void tryToClick(WebElement whatToClick) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        for (int j = 1; j <= 200; j++) {
            try {
                whatToClick.click();
                break;
            } catch (Exception e) {
                sleep(20);
            }
        }
    }
}