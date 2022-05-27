package com.sdet34l1.genericUtility;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This is class is used to maintain all webdriver common actions
 * @author DELL
 *
 */
public class WebDriverBrowserUtility 
{
	static WebDriverWait wait;
	static Actions act;
	static Select select;
	static JavascriptExecutor js;
	static JavaJdkUtility jutil=new JavaJdkUtility();

	/**
	 * This method is used to navigate to the application
	 * @param url
	 * @param driver
	 */
	public static void navigateApp(String url, WebDriver driver)
	{
		driver.get(url);
	}


	/**
	 * This method is used to maximize the browser
	 * @param driver
	 */
	public static void maximizeBrowser(WebDriver driver)
	{
		driver.manage().window().maximize();
	}

	/**
	 * This methhod is used to use the browser settings
	 * @param longTimeOut
	 * @param driver
	 */

	public static void browserSetting(long longTimeOut, WebDriver driver)
	{
		maximizeBrowser(driver);
		waitTillPageLoad(longTimeOut, driver);
	}

	/** This method is used to implicitly wait till page load
	 * 
	 * @param longTimeOut
	 * @param driver
	 */
	public static void waitTillPageLoad(long longTimeOut, WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
	}

	/**
	 * This method is used to initialize the actions variable
	 * @param driver
	 */
	public static void initializeActions(WebDriver driver)
	{
		act=new Actions(driver);

	}


	/**
	 * This method is used to double click on the element
	 * @param element
	 */
	public static void doubleclick(WebElement element)
	{
		act.moveToElement(element).click().perform();
	}

	/**
	 * This method is used to mouse hover on the element
	 * @param moreLink
	 */
	public static void mouseHoverOnTheElement(WebElement moreLink, WebDriver driver)
	{
		act=new Actions(driver);
		act.moveToElement(moreLink).perform();
	}


	/**
	 * This method is used to close the browser
	 * @param driver
	 */
	public static void quitBrowser(WebDriver driver)
	{
		driver.quit();
	}

	/**
	 * This method is used to wait the control till the particular element clickable
	 * @param element
	 */
	public static void waitUntillElementClickable(WebElement element)
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	/**
	 * This method is used to wait the control till the particular element visible
	 * @param element
	 */
	public static void waitUntillElementVisible(WebElement element)
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method is used to wait 
	 * @param driver
	 * @param timeOut
	 */
	public static void explicitlyWait(WebDriver driver, long timeOut)
	{
		wait = new WebDriverWait(driver, timeOut);
	}

	/**
	 * This method is used to switch the window based on title
	 * @param driver
	 * @param partialText
	 */
	public static void switchToWindowBasedOnTitle(WebDriver driver, String partialText)
	{
		Set<String> sessionIDs=driver.getWindowHandles();
		for(String id:sessionIDs)
		{
			driver.switchTo().window(id);
			if(driver.getTitle().contains(partialText))
			{
				break;
			}
		}
	}

	/**
	 * This method is used to initialize the Select class
	 * @param element
	 */
	public static void InitializeSelect(WebElement element)
	{
		select=new Select(element);
	}
	/**
	 * This method is used to handle dropdown by visible text
	 * @param element
	 */
	public static void dropDownHandleByVisibleText(WebElement element,String visibletext)
	{
		Select s =new Select(element);
		select.selectByVisibleText(visibletext);
	}

	/**
	 * This method is used to handle dropdown by Index values
	 * @param element
	 * @param index
	 */
	public static void dropDownnHandleByIndex(WebElement element, int index)
	{
		select.selectByIndex(index);
	}

	/**
	 * This method is used to handle dropdown by value
	 * @param value
	 * @param element
	 */
	public static void dropDownHandleByvalue(String value, WebElement element)
	{
		select.selectByValue(value);
	}

	/**
	 * This method is used to switch to frame
	 * @param driver
	 * @param index
	 */
	public static void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}

	/**
	 * This method is used to switch to the main frame
	 * @param driver
	 */
	public static void switchBackToHome(WebDriver driver)
	{
		driver.switchTo().defaultContent();

	}


	/**
	 * this method is used to initialize driver
	 * @param driver
	 */
	public static void initializeJs(WebDriver driver)
	{
		js=(JavascriptExecutor) driver;
	}

	/**
	 * 
	 * @param driver
	 * @param element
	 * @param data
	 */
	public static void enterDataThroughJs(WebDriver driver, WebElement element,String data )

	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].value=arguments[1]", element,data);
	}

	/**
	 * This method is used navigate to the application
	 * @param url
	 */
	public static void navigateApplicationThroughJs(String url)
	{
		js.executeScript("window location=arguments[0]", url);
	}

	/**
	 * This method is used to srollbar action 
	 * @param height
	 */
	public static void scrollToSpecificHeight(String height) 
	{
		js.executeScript("window.scrollBy(0,document.body.scrollHeight");
	}
	/**
	 * This method is used to srollbar action
	 */
	public static void scrollToBottom()
	{
		js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
	}

	/**
	 * This method is used to srollbar action
	 * @param element
	 */
	public static void scrollTillElement(WebElement element)
	{
		js.executeScript("arguments[0].scrollIntoVeiw()",element);
	}

	/**
	 * This method is used to take screenshot
	 * @param FileName
	 * @param driver
	 * @return 
	 */
	public static String takeScreenShot(String FileName, WebDriver driver)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File("./screenshot/"+FileName+"_"+jutil.dateTimeInFormat()+".png");
		System.out.println(dst.getAbsolutePath());
		try {
			FileUtils.copyFile(src, dst);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dst.getAbsolutePath();
	}


	/**
	 * This method is used accept the popup
	 * @param driver
	 */
	public static void alertAccept(WebDriver driver) 
	{
		driver.switchTo().alert().accept();
	}

	/**
	 * This method is used to dismiss the popup
	 * @param driver
	 */
	public static void alertDismiss(WebDriver driver) 
	{
		driver.switchTo().alert().dismiss();
	}

	/**
	 * This method is used to send the data to popup
	 * @param driver
	 * @param data
	 */
	public static void alertsendData(WebDriver driver, String data) 
	{
		driver.switchTo().alert().sendKeys(data);
	}
	/**
	 * 
	 * @param driver
	 * @return
	 */
	public static String getalertText(WebDriver driver) 
	{
		return driver.switchTo().alert().getText();
	}

	/**
	 * This method is used to right click 
	 * @param element
	 */
	public static void rightClick(WebElement element)
	{
		act.contextClick(element).perform();
	}

	/**
	 * This method is used to switch to the child browser
	 * @param driver
	 * @param title
	 */
	public static void switchToChild(WebDriver driver, String title)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String w_id = it.next();
			driver.switchTo().window(w_id);
			String current_title = driver.getTitle();
			if(current_title.contains(title))
			{
				break;
			}
		}
	}

}


