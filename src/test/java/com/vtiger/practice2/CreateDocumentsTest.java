package com.vtiger.practice2;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdet34l1.genericUtility.ExcelsheetUtility;
import com.sdet34l1.genericUtility.FileDataUtility;
import com.sdet34l1.genericUtility.IconstantPathOfPropertyUtility;
import com.sdet34l1.genericUtility.JavaJdkUtility;
import com.sdet34l1.genericUtility.WebDriverBrowserUtility;
import com.vtiger.ObjectRepository.ClickOnNewDocumentsPage;
import com.vtiger.ObjectRepository.CreateTitleDocumentPage;
import com.vtiger.ObjectRepository.HomePage;
import com.vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentsTest 
{
	public static void main(String[] args) throws IOException, InterruptedException
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

		String titlename = ExcelsheetUtility.getDataFromExcel("Organization", 7, 1)+randomNumber;
		String frametext =ExcelsheetUtility.getDataFromExcel("Organization", 10, 0);
		WebDriver driver=null;

		switch (browser)
		{
		//		case "firefox":
		//			WebDriverManager.firefoxdriver().setup();
		//			driver=new FirefoxDriver();
		//			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;

		default:
			System.out.println("please specify the browser key");
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			break;
		}

		LoginPage loginPage=new LoginPage(driver);
		HomePage homePage=new HomePage(driver);
		ClickOnNewDocumentsPage document=new ClickOnNewDocumentsPage(driver);
		CreateTitleDocumentPage titledoc=new CreateTitleDocumentPage(driver);
		//		CreateTitleDocumentPage enterdoc=new CreateTitleDocumentPage(driver);
		//		CreateTitleDocumentPage frametxt=new CreateTitleDocumentPage(driver);


		WebDriverBrowserUtility.navigateApp(url, driver);
		WebDriverBrowserUtility.browserSetting(10, driver);

		loginPage.loginActions(username, password);
		homePage.clickOnDocument();
		document.clickDocumentbutton();
		titledoc.createDocumentTitle();
		titledoc.createTextTitle(titlename);

		WebDriverBrowserUtility.switchToFrame(driver, 0);

		titledoc.enterframetext(frametext);
		WebDriverBrowserUtility.switchBackToHome(driver);
		titledoc.boldTheText();
		titledoc.italicTheText();
		titledoc.saveTheDoc();

		homePage.signOut(driver);

		ExcelsheetUtility.saveExcelData(IconstantPathOfPropertyUtility.EXCELFILEPATH);
		ExcelsheetUtility.closeExcel();
		WebDriverBrowserUtility.quitBrowser(driver);













	}
}
