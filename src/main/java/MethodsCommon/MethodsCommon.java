package MethodsCommon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static MethodsCommon.Driver.driver;

public class MethodsCommon {
    public String inputRandString() throws InterruptedException {
        Random random = new Random();
        int rand_int = random.nextInt();
        String rand_symb = String.valueOf(rand_int);
        return rand_symb;
    }
    public void shouldNotSeeElement(String xpath) throws InterruptedException{
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        assertTrue("Элемент отображается", driver.findElements(By.xpath(xpath)).isEmpty());
    }

    public void shouldSeeElement(WebElement element) throws InterruptedException{
        assertTrue("Элемент не отображается", element.isDisplayed());
    }

    public void shouldSeeElementWithText(WebElement element, String text) throws InterruptedException{
        waitForElementDisplay(element);
        assertEquals("Текст не соответствует ожидаемому", text, element.getText());
    }

    public void shouldSeeElementContainsText(WebElement element, String text) throws InterruptedException{
        waitForElementDisplay(element);
        assertTrue("Текст не содержит ожидаемый", element.getText().contains(text));
    }

    public void waitForElementDisplay(WebElement element) throws InterruptedException{
        Wait<WebDriver> wait;
        wait = new WebDriverWait(driver, 5, 1000).withMessage("Элемент не найден");
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void inputText(WebElement element, String text)throws InterruptedException{
        waitForElementDisplay(element);
        element.clear();
        element.sendKeys(text);
    }

    public void waitAndClickElement(WebElement element)throws InterruptedException{
        waitForElementDisplay(element);
        element.click();
    }

}