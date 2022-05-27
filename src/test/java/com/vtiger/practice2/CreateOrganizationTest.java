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
import com.vtiger.ObjectRepository.ClickOnOrganizationButtonpage;
import com.vtiger.ObjectRepository.CreateNewOrgNamePage;
import com.vtiger.ObjectRepository.HomePage;
import com.vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationTest 
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
		
		String orgname = ExcelsheetUtility.getDataFromExcel("Organization", 4, 1)+randomNumber;
		
		WebDriver driver=null;

		switch (browser)
		{
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
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
		
		WebDriverBrowserUtility.navigateApp(url, driver);
		WebDriverBrowserUtility.browserSetting(10, driver);

		LoginPage loginPage=new LoginPage(driver);
		HomePage homePage=new HomePage(driver);
		ClickOnOrganizationButtonpage clickonOrgbtn=new ClickOnOrganizationButtonpage(driver);
		CreateNewOrgNamePage createnameorg=new CreateNewOrgNamePage(driver);
		CreateNewOrgNamePage saveorg=new CreateNewOrgNamePage(driver);
		
		WebDriverBrowserUtility.navigateApp(url, driver);
		WebDriverBrowserUtility.maximizeBrowser(driver);
		WebDriverBrowserUtility.waitTillPageLoad(longTimeOut, driver);
		loginPage.loginActions(username, password);
		homePage.clickOrgTab();
		clickonOrgbtn.clickOnNewOrg();
		createnameorg.createnewOrgname(orgname);
		createnameorg.saveOrganization(driver);
		Thread.sleep(3000);
		homePage.signOut(driver);
		
		ExcelsheetUtility.saveExcelData(IconstantPathOfPropertyUtility.EXCELFILEPATH);
		ExcelsheetUtility.closeExcel();
		WebDriverBrowserUtility.quitBrowser(driver);
	
	}
}

