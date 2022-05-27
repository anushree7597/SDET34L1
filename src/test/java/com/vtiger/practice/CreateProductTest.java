package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductTest 
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

		FileInputStream fisexe = new FileInputStream("./src/test/resources/excelTestData.xlsx");
		Workbook wb = WorkbookFactory.create(fisexe);

		String pname = ExcelsheetUtility.getDataFromExcel("Organization", 6, 1)+randomNumber;
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
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			break;
		}

		WebDriverBrowserUtility.navigateApp(url, driver);
		WebDriverBrowserUtility.browserSetting(10, driver);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();

		driver.findElement(By.xpath("//a[.='Products']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Product...']")).click();
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(pname);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String pname1 = driver.findElement(By.xpath("//span[@id='dtlview_Product Name']")).getText();
		System.out.println(pname1);
		Thread.sleep(1000);

		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		WebDriverBrowserUtility.mouseHoverOnTheElement(ele, driver);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		ExcelsheetUtility.saveExcelData(IconstantPathOfPropertyUtility.EXCELFILEPATH);
		ExcelsheetUtility.closeExcel();
		WebDriverBrowserUtility.quitBrowser(driver);


	}
}
