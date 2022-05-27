package com.vtiger.compaignsTest;

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
import org.testng.annotations.Test;

import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelsheetUtility;
import com.sdet34l1.genericUtility.FileDataUtility;
import com.sdet34l1.genericUtility.IconstantPathOfPropertyUtility;
import com.sdet34l1.genericUtility.JavaJdkUtility;
import com.sdet34l1.genericUtility.WebDriverBrowserUtility;
import com.vtiger.ObjectRepository.ClickOnCreateProductPage;
import com.vtiger.ObjectRepository.ClickSearchProductPage;
import com.vtiger.ObjectRepository.CreateCampaignPage;
import com.vtiger.ObjectRepository.CreateNewCampaignPage;
import com.vtiger.ObjectRepository.CreateProductNamePage;
import com.vtiger.ObjectRepository.HomePage;
import com.vtiger.ObjectRepository.LoginPage;
import com.vtiger.ObjectRepository.SearchProductIntextPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignwithProductTest  extends BaseClass
{
	String campaignName;
	String productname;
	CreateCampaignPage campaignPage;
	CreateNewCampaignPage createnewCampaign;
	ClickOnCreateProductPage clickonproduct;
	ClickSearchProductPage clickOnCreate;
	SearchProductIntextPage searchProduct;
	CreateCampaignPage saveCampaign;

	ClickOnCreateProductPage clickCreatebutton;
	CreateProductNamePage createProductname;
	CreateProductNamePage savebutton;
	@Test

	public  void createCampaignProductTest()
	{
		campaignName = ExcelsheetUtility.getDataFromExcel("Organization", 5, 1)+randomNumber;
		productname = ExcelsheetUtility.getDataFromExcel("Organization", 6, 1)+randomNumber;
		
		clickCreatebutton=new ClickOnCreateProductPage(driver);
		createProductname=new CreateProductNamePage(driver);
		savebutton=new CreateProductNamePage(driver);
		
		campaignPage=new CreateCampaignPage(driver);
		createnewCampaign=new CreateNewCampaignPage(driver);
		clickonproduct=new ClickOnCreateProductPage(driver);
		clickOnCreate=new ClickSearchProductPage(driver);
		searchProduct=new SearchProductIntextPage(driver);
		saveCampaign=new CreateCampaignPage(driver);

		homePage.clickOnProduct();
		clickCreatebutton.clickOnProductbtn();
		createProductname.createProductname(productname);
		savebutton.saveProductname();
		
		homePage.clickCampaign(driver);
		createnewCampaign.clickNewCampaign();
		campaignPage.enterDatatoTextfeild(campaignName);
		clickOnCreate.clickOnSearchProduct();
		searchProduct.switchToChild(driver, "Products&action");
		searchProduct.selectProducts(productname, driver);
		searchProduct.switchToChild(driver, "Campaigns&action");
		saveCampaign.saveCampaignName();
		
		jutil.assertionThroughIfCondition(campaignName, campaignName);
		jutil.assertionThroughIfCondition(productname, productname);

	}
}

