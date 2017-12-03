package Maystrenko;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class LogInClass extends FunctionalTest {
    public static String host = "test";
    //public static String host = "dev";
    public void logIn () throws InterruptedException {
        driver.navigate().to("https://webep-"+host+".ligazakon.net/signinup/auth");
        //driver.navigate().to("https://webep-test.ligazakon.net/signinup/auth");
        driver.manage().window().maximize();

        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        MainPagePlatforma mainPagePlatforma = new MainPagePlatforma(driver);
        WebDriverWait wait = new WebDriverWait(driver,10,1000);

        //wait.until(ExpectedConditions.visibilityOf(authorizationPage.loginField));
        authorizationPage.enterLogin("grand2@binka.me");
        authorizationPage.enterPassword("1111");
        authorizationPage.submitAuthorizationForm();

        Thread.sleep(2000);

        int countkeys = 0;
        //Переопределим время ожидания для того, чтобы в случае отсутствия папок тест не останавливался на 10 дефолтовых секунд ожидания их появления
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        //Посчитаем, сколько раз текст "Керівнику" есть на странице. На самом не считаем, а проверяем 0 или больше, т.е. факт наличия. Если найдем, значит язык страницы - укр
        countkeys = driver.findElements(By.xpath("//a[contains(text(),'Керівнику')]")).size();
        if (!(0==countkeys)){
            wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id='profile-btn']/a/i")))); //ждем чувачка
            driver.findElement(By.xpath("//*[@id='profile-btn']/a/i")).click();//жмем чувачка

            wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id='profile-lang']/h4[2]")))); //ждем чувачка
            driver.findElement(By.xpath("//*[@id='profile-lang']/h4[2]")).click();//жмем РУС
            Thread.sleep(800);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//вернем дефолтное время ожидания в 10 секунд

        wait.until(ExpectedConditions.visibilityOf(mainPagePlatforma.myProductsLZ));
        //assertEquals("Мої продукти ЛІГА:ЗАКОН", mainPagePlatforma.compareMainPageText());
        assertEquals("Мои продукты ЛІГА:ЗАКОН", mainPagePlatforma.compareMainPageText());
        System.out.println("Test authorization done.");
    }
}