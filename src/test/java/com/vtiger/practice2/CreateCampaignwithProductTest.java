package com.vtiger.practice2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.sdet34l1.genericUtility.ExcelsheetUtility;
import com.sdet34l1.genericUtility.FileDataUtility;
import com.sdet34l1.genericUtility.IconstantPathOfPropertyUtility;
import com.sdet34l1.genericUtility.JavaJdkUtility;
import com.sdet34l1.genericUtility.WebDriverBrowserUtility;
import com.vtiger.ObjectRepository.ClickOnCreateProductPage;
import com.vtiger.ObjectRepository.ClickSearchProductPage;
import com.vtiger.ObjectRepository.CreateCampaignPage;
import com.vtiger.ObjectRepository.CreateNewCampaignPage;
import com.vtiger.ObjectRepository.HomePage;
import com.vtiger.ObjectRepository.LoginPage;
import com.vtiger.ObjectRepository.SearchProductIntextPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignwithProductTest 
{
	public static void main(String[] args) throws IOException, InterruptedException {


		JavaJdkUtility jutil = new JavaJdkUtility();
		FileDataUtility.openPropertyFile(IconstantPathOfPropertyUtility.PROPERTYFILEPATH);
		
		ExcelsheetUtility.openExcel(IconstantPathOfPropertyUtility.EXCELFILEPATH);
		int randomNumber=jutil.getRandomNumber(1000);
		
		String url = FileDataUtility.getDataFromPropertyFile("url");
		String username = FileDataUtility.getDataFromPropertyFile("username");
		String password = FileDataUtility.getDataFromPropertyFile("password");
		String timeout = FileDataUtility.getDataFromPropertyFile("timeout");
		String browser = FileDataUtility.getDataFromPropertyFile("browser");
		
		String camName = ExcelsheetUtility.getDataFromExcel("Organization", 5, 1)+randomNumber;
		String prodname = ExcelsheetUtility.getDataFromExcel("Organization", 6, 1)+randomNumber;
		long longTimeOut=Long.parseLong(timeout);
		WebDriver driver=null;
		switch (browser) {
//		case "firefox":
//			WebDriverManager.firefoxdriver().setup();
//			driver=new FirefoxDriver();
//			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		default:
			System.out.println("please specify proper browser key");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}

		LoginPage loginPage=new LoginPage(driver);
		HomePage homePage=new HomePage(driver);
		CreateCampaignPage campaignPage=new CreateCampaignPage(driver);
		CreateNewCampaignPage createnewCampaign=new CreateNewCampaignPage(driver);
		ClickOnCreateProductPage clickonproduct=new ClickOnCreateProductPage(driver);
		ClickSearchProductPage clickOnCreate=new ClickSearchProductPage(driver);
		SearchProductIntextPage searchProduct=new SearchProductIntextPage(driver);
		CreateCampaignPage saveCampaign=new CreateCampaignPage(driver);

		WebDriverBrowserUtility.navigateApp(url, driver);
		WebDriverBrowserUtility.browserSetting(10, driver);

		loginPage.loginActions(username, password);
		homePage.clickCampaign(driver);
		//Thread.sleep(2000);
		createnewCampaign.clickNewCampaign();
		//Thread.sleep(2000);
		campaignPage.enterDatatoTextfeild(camName);
		
		clickOnCreate.clickOnSearchProduct();
		searchProduct.switchToChild(driver, "Products&action");
		searchProduct.selectProducts(prodname, driver);
		searchProduct.switchToChild(driver, "Campaigns&action");
		saveCampaign.saveCampaignName();

		ExcelsheetUtility.closeExcel();

		homePage.signOut(driver);

		WebDriverBrowserUtility.quitBrowser(driver);

	}
}

