package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactexcelTest 
{
	public static void main(String[] args) throws IOException 
	{
		JavaJdkUtility jutil = new JavaJdkUtility();
		FileDataUtility.openPropertyFile(IconstantPathOfPropertyUtility.PROPERTYFILEPATH);

		String url = FileDataUtility.getDataFromPropertyFile("url");
		String username = FileDataUtility.getDataFromPropertyFile("username");
		String password = FileDataUtility.getDataFromPropertyFile("password");
		String timeout = FileDataUtility.getDataFromPropertyFile("timeout");
		String browser = FileDataUtility.getDataFromPropertyFile("browser");

		long longTimeOut=jutil.stringToLong(timeout);
		int randomNumber=jutil.getRandomNumber(1000);

	    String lastname = ExcelsheetUtility.getDataFromExcel("Organization", 4, 2)+randomNumber;
		
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
		default:
			System.out.println("please specify proper browser key");
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		}

		WebDriverBrowserUtility.navigateApp(url, driver);
		WebDriverBrowserUtility.maximizeBrowser(driver);
		WebDriverBrowserUtility.waitTillPageLoad(longTimeOut, driver);
	
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();

		if(driver.getTitle().contains("Home"))
		{
			ExcelsheetUtility.setDataToExcel("Organization", 3, 8, "home page is dispalyed");
			ExcelsheetUtility.setDataToExcel("Organization", 4, 8, "tc pass");
			
		}
         
		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		String get = driver.getTitle();
		{
			System.out.println(get);
		}
		if(driver.getTitle().contains(" vtiger CRM 5 - Commercial Open Source CRM"))
		{
			ExcelsheetUtility.setDataToExcel("Organization", 5, 8, "contact is created");
			ExcelsheetUtility.setDataToExcel("Organization", 6, 8, "tc pass");
			
		}

		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname);
		driver.findElement(By.xpath("//input[@name='button']")).click();
		WebElement ActualLastName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']"));

		if(ActualLastName.getText().equalsIgnoreCase(lastname))
		{
			System.out.println("contact created successfully");
		}
		
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		WebDriverBrowserUtility.mouseHoverOnTheElement(ele, driver);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		ExcelsheetUtility.saveExcelData(IconstantPathOfPropertyUtility.EXCELFILEPATH);
		ExcelsheetUtility.closeExcel();
		WebDriverBrowserUtility.quitBrowser(driver);

		
	}
}
