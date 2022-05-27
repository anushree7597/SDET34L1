package com.vtiger.practice2;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import com.vtiger.ObjectRepository.ClickOnCreateProductPage;
import com.vtiger.ObjectRepository.CreateProductNamePage;
import com.vtiger.ObjectRepository.HomePage;
import com.vtiger.ObjectRepository.LoginPage;
import com.vtiger.ObjectRepository.ValidateProductnamePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductNameTest 
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

		String pname = ExcelsheetUtility.getDataFromExcel("Organization", 6, 1)+randomNumber;
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
		ClickOnCreateProductPage clickcreatebutton=new ClickOnCreateProductPage(driver);
		CreateProductNamePage createproductname=new CreateProductNamePage(driver);
		CreateProductNamePage savebtn=new CreateProductNamePage(driver);
		ValidateProductnamePage validatepname=new ValidateProductnamePage(driver);

		WebDriverBrowserUtility.navigateApp(url, driver);
		WebDriverBrowserUtility.browserSetting(10, driver);

		loginPage.loginActions(username, password);
		homePage.clickOnProduct();
		clickcreatebutton.clickOnProductbtn();
		createproductname.createProductname(pname);
		savebtn.saveProductname();
		String actualRes = validatepname.confirmProductName(pname);
		jutil.assertionThroughIfCondition(actualRes, pname);

		homePage.signOut(driver);

		ExcelsheetUtility.closeExcel();
		WebDriverBrowserUtility.quitBrowser(driver);

	}
}

