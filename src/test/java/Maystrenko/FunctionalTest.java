package Maystrenko;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class FunctionalTest {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    //@Before
    public void setUp() throws Exception {
        //System.setProperty("webdriver.gecko.driver", "D:\\Install\\java,webdriver\\geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", "D:\\SeleniumDriversBrowsers\\FF\\geckodriver.exe");
        driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver,40,2000); //40- время timeout в секундах, 2000- в milliseconds, которое нужно ожидать перед очередным вызовом проверки наличия элемента (500 milliseconds by default).
        //baseUrl = "https://webep-dev.ligazakon.net/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        LogInClass loginIn = new LogInClass();
        loginIn.logIn();
    }

    //@After
    public void cleanUp(){
        //driver.manage().deleteAllCookies();
        //driver.close();
        //driver.quit();
    }
}	
/*	protected static WebDriver driver; 
	protected static WebDriverWait wait;
	
	@BeforeClass 
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:\\toolsQA\\geckodriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
	}
	

/*	@AfterClass 
	public static void tearDown(){
		driver.close();
*/		