package com.vtiger.practice2;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.sdet34l1.genericUtility.ExcelsheetUtility;
import com.sdet34l1.genericUtility.FileDataUtility;
import com.sdet34l1.genericUtility.IconstantPathOfPropertyUtility;
import com.sdet34l1.genericUtility.JavaJdkUtility;
import com.sdet34l1.genericUtility.WebDriverBrowserUtility;
import com.vtiger.ObjectRepository.CampaignValidatePage;
import com.vtiger.ObjectRepository.CreateCampaignPage;
import com.vtiger.ObjectRepository.CreateNewCampaignPage;
import com.vtiger.ObjectRepository.HomePage;
import com.vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignNameTest 
{
	public static void main(String[] args) throws Throwable 
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
		String campaignname = ExcelsheetUtility.getDataFromExcel("Organization",4,1)+randomNumber;

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
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}

		LoginPage loginPage=new LoginPage(driver);
		HomePage homePage=new HomePage(driver);
		CreateCampaignPage campaignPage=new CreateCampaignPage(driver);
		CreateNewCampaignPage createnewCampaign=new CreateNewCampaignPage(driver);
		CampaignValidatePage campaignValidatePage=new CampaignValidatePage(driver);
		
		WebDriverBrowserUtility.navigateApp(url, driver);
		WebDriverBrowserUtility.maximizeBrowser(driver);
		WebDriverBrowserUtility.waitTillPageLoad(longTimeOut, driver);
		
		loginPage.loginActions(username, password);
		
		homePage.clickCampaign(driver);
		Thread.sleep(2000);
		createnewCampaign.clickNewCampaign();
		Thread.sleep(2000);
		campaignPage.enterDatatoTextfeild(campaignname);
		campaignPage.saveCampaignName();
	    //String actualRes = campaignValidatePage.campaignNameInformText();
	    //jutil.assertionThroughIfCondition(actualRes, campaignname);
		//Thread.sleep(5000);
		homePage.signOut(driver);
		
		ExcelsheetUtility.saveExcelData(IconstantPathOfPropertyUtility.EXCELFILEPATH);
		ExcelsheetUtility.closeExcel();
		WebDriverBrowserUtility.quitBrowser(driver);

	}
}
