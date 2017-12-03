package MethodsCommon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xpath.SourceTree;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.String.valueOf;
import static MethodsCommon.Driver.driver;

public class Func {
	
	private static String strAnything;
	private static String url;
	private static Integer c=1;
	private static Boolean tf;
	static JavascriptExecutor js;

	private static String strSwitchLanguageXpath = "//div[starts-with(@id, 'profile-lang')]//h4[position()=${i}]";
	private static String strSwitchLanguageAnonymousXpath = "//div[starts-with(@id, 'profile-lang')]//div[position()=${i}]";

    private static final String TXT_NO_MESSAGE = "Отсутствует сообщение о покупке для Анонимуса";
    private static final String TXT_FOUND = "Необходимый текст на странице не найден - должен был быть = ";
    private static final String TXT_FEED_UPDATES = "Продукты ЛІГА:ЗАКОН";
    
    //private static final By SWITCH_LANGUAGE_XPATH_Anonymous = By.xpath("//div[starts-with(@id, 'profile-lang')]//div[position()=2]");
    private static final By CLOSE_BUY_FORM = By.xpath("//div[@class='close']//i[@class='fa fa-times']");
    private static final By GO_MAIN_ICO = By.xpath("//div[@class='part_ico_block']//a");
    
    // Get Text
 	private static final By GET_TEXT_DOC = By.xpath("//div[starts-with(@class, 'mainTag')]//p[@class='tj bmf']");
 	private static final By GET_TEXT_FORM = By.xpath("//div[contains(@ng-if, 'isFreeInIdLevel')]");
 	private static final By GET_FORM_BUTTON = By.xpath("//div[@class='row']//a[contains(@ng-if, 'isFreeInIdLevel')]");
	static Log log = LogFactory.getLog(Func.class);

	public static String getUrl() { return url;	}

  public static void navigateToUrl() throws InterruptedException {
		url = System.getenv("ROOTURL");
		driver.get(url);
  }

  public static void closeAnonymousMessage(String str) throws InterruptedException {
	  waitElement(GET_FORM_BUTTON);
      while(byGetTextElement(GET_TEXT_FORM).isEmpty()){
		  waitElement(GET_TEXT_FORM);
	  }
	  if(byGetTextElement(GET_TEXT_FORM).equals(str)) {
	      byClickElement(CLOSE_BUY_FORM);
  	  }
	  else{
  		    throw new InterruptedException(TXT_NO_MESSAGE);
  	      }
  }

  public static void setUKRRUSLanguageAnonymous(Integer n) throws InterruptedException {
	  java.lang.String numb = valueOf(n);
  	  byClickElement(By.xpath(strSwitchLanguageAnonymousXpath.replace("${i}", numb)));
  }

  public static void setLocalLanguage(Integer num) throws InterruptedException {
		if (driver instanceof JavascriptExecutor) {
			js = (JavascriptExecutor) driver;
		} // else throw...

		js.executeScript("window.angular.element('#profile-btn i').trigger('click');");

		String numb = valueOf(num);
		byClickElement(By.xpath(strSwitchLanguageXpath.replace("${i}", numb)));

  	    //waitContains(conf);
  }

  public static void waitElement(By by) throws InterruptedException {
	  final Wait<WebDriver> wait = new WebDriverWait(driver, Driver.getDefaultSecondsWait());
	  if(wait.equals(by)){
		  throw new InterruptedException("Element - "+by+" - not found" );
	  }
	  wait.until(ExpectedConditions.visibilityOfElementLocated(by));
  }

  public static boolean waitElement(final WebElement el) throws InterruptedException {
  	try{
		Driver.driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
		Wait<WebDriver> wait = new WebDriverWait(Driver.driver, 1, 1);
		wait.until(ExpectedConditions.visibilityOf(el));
  		return true;
	}catch (RuntimeException ex) {
		if (ex instanceof NoSuchElementException || ex instanceof StaleElementReferenceException)
			waitElement(el);
		return false;
	}
	finally{ Driver.driver.manage().timeouts().implicitlyWait(Driver.getDefaultSecondsWait(), TimeUnit.SECONDS); }
  }

  public static String waitForElement(WebElement webElement) throws InterruptedException{
		Boolean isFound = false;
		for (int i = 0; i < 10; i++)
		{
			if (IsElementPresentAndVisible(webElement))
			{
				isFound = true;
				break;
			}
			else
			{
				Thread.sleep(1000);
			}
		}
		if (isFound)
		{
			return webElement.getText();
		}
		else
		{
			Assert.fail("ERROR! It's impossible to detect web-element" + webElement.toString() + ".");
			return null;
		}
  }

	public static Boolean IsElementPresentAndVisible(WebElement webElement){
		try
		{
			return webElement.isDisplayed();
		}
		catch (Exception ex)
		{
			if (ex instanceof NoSuchElementException || ex instanceof ElementNotVisibleException || ex instanceof StaleElementReferenceException)
			{
				return false;
			}
			else if (ex.getCause() != null && ex.getCause().getCause() instanceof InvocationTargetException)
			{
				return false;
			}
			throw ex;
		}
  }

  public static boolean waitContains(String str) throws InterruptedException {
	  final Wait<WebDriver> waitSrt = new WebDriverWait(driver, Driver.getDefaultSecondsWait());
	  if(waitSrt.equals(driver.getPageSource().contains(str)))
	    	throw new InterruptedException(TXT_FOUND+str);
	  return true;
  }

  public static void clickMainPage() throws InterruptedException {
	  byClickElement(GO_MAIN_ICO);
	  waitContains(TXT_FEED_UPDATES);
  }

  public static void checkOpenDoc() throws InterruptedException {
		if(byGetTextElement(GET_TEXT_DOC).isEmpty()){
			 throw new InterruptedException("We have empty doc");
		}
  }

  public static void byClickElement(By by) throws InterruptedException{
  	   while(!visibleElement(by)){ waitElement(by); }
	   WebElement obj = driver.findElement(by);
	   obj.click();
  }

  public static String byGetTextElement(By by) throws InterruptedException{
	  waitElement(by);
	  if(!driver.findElements(by).isEmpty()){
	       WebElement obj = driver.findElement(by);
	       strAnything = obj.getText();
	  }
	  return strAnything;
  }

  public static Boolean visibleElement(By by) throws InterruptedException{
	  tf = driver.findElement(by).isDisplayed();
	  return tf;
  }

  public static void waitAndClickElement(By by, int waitTime)throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
	    wait.until(ExpectedConditions.presenceOfElementLocated(by)).click();
  }

  public static String waitAndGetTextElement(WebElement element, int waitTime)throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		return wait.until(ExpectedConditions.visibilityOf(element)).getText();
  }

  public static void scrollToElementClick(By locator){
	  WebElement element = driver.findElement(locator);
	  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	  ((JavascriptExecutor) driver).executeScript("window.scrollBy(1, -250)");
	  element.click();
  }

  public static HashMap scrollToElementClickNewWindow(By locator) throws InterruptedException {
  	    waitElement(locator);
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(1, -150)");

		HashMap <String, String> cW = selectNewWindow(element);
		return cW;
  }

  public static void scrollToElementClick(WebElement el){
		WebElement element = el;
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(1, -50)");
		element.click();
  }

  public static HashMap<String, String> selectNewWindow(WebElement webElement) throws InterruptedException {
		String parentWindowHandle = driver.getWindowHandle();
		final Set<String> oldWindowsSet = driver.getWindowHandles();
		try {
			webElement.click();
		}catch (RuntimeException ex) {
			if (ex instanceof StaleElementReferenceException)
				webElement.click();
		}
		String newWindowHandle = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<String>() {
																		   public String apply(WebDriver driver) {
																			   Set<String> newWindowsSet = driver.getWindowHandles();
																			   newWindowsSet.removeAll(oldWindowsSet);
																			   return newWindowsSet.size() > 0 ?
																					   newWindowsSet.iterator().next() : null;
																		   }
																	   }
		);
		driver.switchTo().window(newWindowHandle);

		HashMap<String, String> tmpHashMap = new HashMap<>();
		tmpHashMap.put("nWindow", newWindowHandle);
		tmpHashMap.put("pWindow", parentWindowHandle);
		return tmpHashMap;
  }

  public static HashMap<String, String> selectNewWindow(By by) throws InterruptedException {
	  String parentWindowHandle = driver.getWindowHandle();
	  final Set<String> oldWindowsSet = driver.getWindowHandles();
	  try {
		  byClickElement(by);
		  //waitAndClickElement(by, 5);
	  }
	  catch (RuntimeException ex){
	  	if(ex instanceof StaleElementReferenceException)
			byClickElement(by);
	  }
	  String newWindowHandle = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<String>() {
																		 public String apply(WebDriver driver) {
																			 Set<String> newWindowsSet = driver.getWindowHandles();
																			 newWindowsSet.removeAll(oldWindowsSet);
																			 return newWindowsSet.size() > 0 ?
																					 newWindowsSet.iterator().next() : null;
																		 }
																	 }
	  );
	  driver.switchTo().window(newWindowHandle);

	  HashMap<String, String> tmpHashMap = new HashMap<>();
	  tmpHashMap.put("nWindow", newWindowHandle);
	  tmpHashMap.put("pWindow", parentWindowHandle);
	  return tmpHashMap;
  }

  public static void checkSortDateList(List<WebElement> elValue, String regex,Boolean trf) throws InterruptedException {
		List<String> yearAfter = new ArrayList<String>();
		List<String> yearBefore = new ArrayList<String>();
		int i, c;
		String strAfterSplit[] = new String[elValue.size()];


		for(i = 0; i < strAfterSplit.length; i++) {
			for (c = 0; c < elValue.size(); c++) {
				strAfterSplit = elValue.get(c).getText().split(regex);
				if(i==0){
					yearAfter.add(c, strAfterSplit[i]);
					yearBefore.add(c, strAfterSplit[i]);
				}else{
					yearAfter.set(c, strAfterSplit[i]);
					yearBefore.set(c, strAfterSplit[i]);
				}
			}
			if (trf){
				Comparator<String> col = Collections.reverseOrder();
				Collections.sort(yearAfter, col);
			}else Collections.sort(yearAfter);

			for (c = 0; c < yearAfter.size(); c++) {
				if (!yearAfter.get(c).equals(yearBefore.get(c)) && trf)
					throw new InterruptedException("Incorrect sort list "+yearAfter.get(c) + " more than " + yearBefore.get(c));
				if(!yearAfter.get(c).equals(yearBefore.get(c)) && !trf)
					throw new InterruptedException("Incorrect sort list "+yearAfter.get(c) + " less than " + yearBefore.get(c));
			}
		}
		System.out.println("The list is sorted correctly");
  }

	public static void sortNameList(List<WebElement> alpha, Boolean trf) throws InterruptedException {
		List<String> listAfter = new ArrayList<String>();
		List<String> listBefore= new ArrayList<String>();

		for (int i = 0; i<alpha.size(); i++){
			String oneChar = alpha.get(i).getText().substring(0, 1);
			if(!oneChar.contains("І")) {
				listBefore.add(oneChar); listAfter.add(oneChar);
			}
		}
		if(trf)
			Collections.sort(listAfter, Collections.reverseOrder());  //decrease from finish to begin
		else Collections.sort(listAfter);

		for (int i = 0; i < listAfter.size(); i++) {
			if (!listBefore.get(i).equals(listAfter.get(i)) && trf)
				throw new InterruptedException("Incorrect sort list "+listAfter.get(i) + " early than " + listBefore.get(i));
			if(!listBefore.get(i).equals(listAfter.get(i)) && !trf)
				throw new InterruptedException("Incorrect sort list "+listAfter.get(i) + " later than " + listBefore.get(i));
		}
		System.out.println("Name list is sorted correctly");
	}

}  