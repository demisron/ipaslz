package Maystrenko;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static MethodsCommon.Driver.driver;


public class FuncM {

	private static String strAnything;
	private static String url;
	public static Long CModule, CBallBalls;
	private static Integer c=1;
	private static Boolean tf;
	static JavascriptExecutor js;
	static Log log = LogFactory.getLog(FuncM.class);

	private static final String TXT_NO_MESSAGE = "Отсутствует сообщение о покупке для Анонимуса";
	private static final String TXT_FOUND = "Необходимый текст на странице не найден - должен был быть = ";
	private static final String TXT_FEED_UPDATES = "Продукты ЛІГА:ЗАКОН";

  public static void navigateToUrl() throws InterruptedException {
		url = System.getenv("ROOTURL");
		driver.get(url);
  }

  //Пытался сделать свой метод, принимающий не xpath, а webelement, не получилось. Не использовать!
 /* public static void WaitElementM(WebElement myElement) throws InterruptedException {
	  final Wait<WebDriver> wait = new WebDriverWait(driver, 10);
	  if(wait.equals(myElement)){
		  throw new InterruptedException("Element - "+myElement+" - not found" );
	  }
	  wait.until(ExpectedConditions.visibilityOfElementLocated(myElement));
  }
  public static void ByClickElementM(By by) throws InterruptedException{
	  while(!VisibleElementM(by)){ WaitElementM(by); }
	  WebElement obj = driver.findElement(by);
	  //log.info(obj);
	  obj.click();
  }
*/
  public static boolean WaitContainsM(String str) throws InterruptedException {
	  final Wait<WebDriver> waitSrt = new WebDriverWait(driver, 10);
	  if(waitSrt.equals(driver.getPageSource().contains(str)))
	    	throw new InterruptedException(TXT_FOUND+str);
	  return true;
  }


  public static Boolean VisibleElementM(By by) throws InterruptedException{
	  tf = driver.findElement(by).isDisplayed();
	  return tf;
  }
}