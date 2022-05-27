package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.support.ui.Select;

import com.sdet34l1.genericUtility.ExcelsheetUtility;
import com.sdet34l1.genericUtility.FileDataUtility;
import com.sdet34l1.genericUtility.IconstantPathOfPropertyUtility;
import com.sdet34l1.genericUtility.JavaJdkUtility;
import com.sdet34l1.genericUtility.WebDriverBrowserUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationWithIndustry 
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

//		FileInputStream fis1 = new FileInputStream("./src/test/resources/excelTestData.xlsx");
//		Workbook wb = WorkbookFactory.create(fis1);
		
		String orgname = ExcelsheetUtility.getDataFromExcel("Organization", 4, 1)+randomNumber;

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
			break;
		}
		
		WebDriverBrowserUtility.navigateApp(url, driver);
		WebDriverBrowserUtility.maximizeBrowser(driver);
		WebDriverBrowserUtility.waitTillPageLoad(longTimeOut, driver);

		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();

		driver.findElement(By.xpath("//a[.='Organizations']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgname);
		
	
		WebElement drop = driver.findElement(By.xpath("//select[@name='industry']"));
		WebDriverBrowserUtility.InitializeSelect(drop);
		//WebDriverBrowserUtility.dropDownHandleByvalue("industry");
		WebDriverBrowserUtility.dropDownHandleByVisibleText(drop, "Education");
		
		WebElement drop1=driver.findElement(By.xpath("//select[@name='accounttype']"));
		WebDriverBrowserUtility.InitializeSelect(drop1);
		//WebDriverBrowserUtility.dropDownHandleByvalue("accounttype", drop);
		WebDriverBrowserUtility.dropDownHandleByVisibleText(drop1, "Press");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
//		WebElement drop = driver.findElement(By.xpath("//select[@name='industry']"));
//		Select s = new Select(drop);
//		s.selectByVisibleText("Education");
//		WebElement drop1 = driver.findElement(By.xpath("//select[@name='accounttype']"));
//		Select s1=new Select(drop1);
//		s1.selectByVisibleText("Press");
		Thread.sleep(3000);
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		WebDriverBrowserUtility.mouseHoverOnTheElement(ele, driver);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		ExcelsheetUtility.saveExcelData(IconstantPathOfPropertyUtility.EXCELFILEPATH);
		ExcelsheetUtility.closeExcel();
		WebDriverBrowserUtility.quitBrowser(driver);
	}
}
