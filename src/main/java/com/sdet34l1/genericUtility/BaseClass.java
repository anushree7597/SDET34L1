package com.sdet34l1.genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.vtiger.ObjectRepository.HomePage;
import com.vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * This class contains implementation of all basic configuration annotations
 * @author DELL
 * all the test scripts are extended from base class
 * hh
 */
public class BaseClass {
	public static WebDriver staticdriver;
	public int randomNumber;
	public String password;
	public String username;
	public long longTimeOut;
	public WebDriver driver;
	public JavaJdkUtility jutil;
	public LoginPage loginPage;
	public HomePage homePage;


	/**
	 * In this annotation we open database, open the property file, open the excel
	 * @throws IOException
	 */

	@BeforeSuite(groups="baseclass")

	public void beforesuitetest() throws IOException 
	{
		//open database if it is required
		FileDataUtility.openPropertyFile(IconstantPathOfPropertyUtility.PROPERTYFILEPATH);
		ExcelsheetUtility.openExcel(IconstantPathOfPropertyUtility.EXCELFILEPATH);

	}

	/**
	 * In this annotation we fetch the data from the property file, create the instance 
	 */
//	@Parameters("browser")
	@BeforeClass(groups="baseclass")

	public void beforeClassTest(/*String browser*/) {
		jutil = new JavaJdkUtility();
		String url = FileDataUtility.getDataFromPropertyFile("url");
		String timeout = FileDataUtility.getDataFromPropertyFile("timeout");
		username = FileDataUtility.getDataFromPropertyFile("username");
		password = FileDataUtility.getDataFromPropertyFile("password");
		String browser = FileDataUtility.getDataFromPropertyFile("browser");
		longTimeOut=jutil.stringToLong(timeout);
		randomNumber=jutil.getRandomNumber(1000);

		switch (browser) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		default:
			jutil.printStatement("please specify proper browser key");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		}
		staticdriver=driver;
		WebDriverBrowserUtility.navigateApp(url, driver);
		WebDriverBrowserUtility.browserSetting(longTimeOut, driver);
		WebDriverBrowserUtility.initializeActions(driver);
		WebDriverBrowserUtility.explicitlyWait(driver, longTimeOut);
		loginPage=new LoginPage(driver);
		homePage=new HomePage(driver);
	}


	/**
	 * In this annotation login action is performed
	 */
	@BeforeMethod(groups="baseclass")

	public void beforeMethod1Test()
	{
		loginPage.loginActions(username, password);
	}
	/**
	 * in this annotation signout action is performed
	 * @throws Throwable 
	 */

	@AfterMethod(groups="baseclass")
	public void afterMethod1Test() 
	{


		try {
			homePage.signOut(driver);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * in this annotation we will perform the close browser
	 */
	@AfterClass(groups="baseclass")

	public void afterClass1()
	{
		WebDriverBrowserUtility.quitBrowser(driver);
	}
	
	/**
	 * in this annotation we will close the excel
	 */
	@AfterSuite(groups="baseclass")

	public void aftersuite1Test()
	{
		ExcelsheetUtility.closeExcel();
	}
}


