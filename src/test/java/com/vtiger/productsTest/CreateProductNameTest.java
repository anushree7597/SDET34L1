package com.vtiger.productsTest;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelsheetUtility;
import com.sdet34l1.genericUtility.FileDataUtility;
import com.sdet34l1.genericUtility.IconstantPathOfPropertyUtility;
import com.sdet34l1.genericUtility.JavaJdkUtility;
import com.sdet34l1.genericUtility.WebDriverBrowserUtility;
import com.vtiger.ObjectRepository.ClickOnCreateProductPage;
import com.vtiger.ObjectRepository.CreateProductNamePage;
import com.vtiger.ObjectRepository.HomePage;
import com.vtiger.ObjectRepository.LoginPage;
import com.vtiger.ObjectRepository.ValidateProductnamePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductNameTest extends BaseClass
{
	String productname;
	ClickOnCreateProductPage clickCreatebutton;
	CreateProductNamePage createProductname;
	CreateProductNamePage savebutton;
	ValidateProductnamePage validateProductname;
	@Test

	public  void createproduct()
	{
		productname = ExcelsheetUtility.getDataFromExcel("Organization", 6, 1)+randomNumber;

		clickCreatebutton=new ClickOnCreateProductPage(driver);
		createProductname=new CreateProductNamePage(driver);
		savebutton=new CreateProductNamePage(driver);
		validateProductname=new ValidateProductnamePage(driver);
		
		homePage.clickOnProduct();
		clickCreatebutton.clickOnProductbtn();
		createProductname.createProductname(productname);
		savebutton.saveProductname();

		jutil.assertionThroughIfCondition(productname, productname);
	}
}

