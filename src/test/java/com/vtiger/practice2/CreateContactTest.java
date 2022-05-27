package com.vtiger.practice2;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdet34l1.genericUtility.ExcelsheetUtility;
import com.sdet34l1.genericUtility.FileDataUtility;
import com.sdet34l1.genericUtility.IconstantPathOfPropertyUtility;
import com.sdet34l1.genericUtility.JavaJdkUtility;
import com.sdet34l1.genericUtility.WebDriverBrowserUtility;
import com.vtiger.ObjectRepository.ClickOnCreateProductPage;
import com.vtiger.ObjectRepository.ClickOnNewContactPage;
import com.vtiger.ObjectRepository.ContactLastNamePage;
import com.vtiger.ObjectRepository.HomePage;
import com.vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactTest 
{
	public static void main(String[] args) throws InterruptedException, IOException 
	{
		JavaJdkUtility jutil = new JavaJdkUtility();
		FileDataUtility.openPropertyFile(IconstantPathOfPropertyUtility.PROPERTYFILEPATH);
		ExcelsheetUtility.openExcel(IconstantPathOfPropertyUtility.EXCELFILEPATH);
		String url = FileDataUtility.getDataFromPropertyFile("url");
		String username = FileDataUtility.getDataFromPropertyFile("username");
		String password = FileDataUtility.getDataFromPropertyFile("password");
		String timeout = FileDataUtility.getDataFromPropertyFile("timeout");
		String browser = FileDataUtility.getDataFromPropertyFile("browser");

		long longTimeOut=jutil.stringToLong(timeout);
		int randomNumber=jutil.getRandomNumber(1000);

		String lastname = ExcelsheetUtility.getDataFromExcel("Organization", 4, 2)+randomNumber;

		WebDriver driver=null;
		
		switch (browser) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		default:
			System.out.println("please specify proper browser key");
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}

		WebDriverBrowserUtility.navigateApp(url, driver);
		WebDriverBrowserUtility.browserSetting(longTimeOut, driver);

		LoginPage loginPage=new LoginPage(driver);
		HomePage homePage=new HomePage(driver);
		ClickOnNewContactPage contactbtn=new ClickOnNewContactPage(driver);
		ContactLastNamePage enterlastcontactname=new ContactLastNamePage(driver);
		ContactLastNamePage save=new ContactLastNamePage(driver);

		WebDriverBrowserUtility.navigateApp(url, driver);
		WebDriverBrowserUtility.maximizeBrowser(driver);
		WebDriverBrowserUtility.waitTillPageLoad(longTimeOut, driver);
		
		loginPage.loginActions(username, password);
		homePage.clickContactTab();
		contactbtn.clickNewcontactButton();
		enterlastcontactname.enterLastName(lastname);
		Thread.sleep(2000);
		save.saveLastName();

		homePage.signOut(driver);

		ExcelsheetUtility.saveExcelData(IconstantPathOfPropertyUtility.EXCELFILEPATH);
		ExcelsheetUtility.closeExcel();
		WebDriverBrowserUtility.quitBrowser(driver);
		
	}
}