package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganization 
{
	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException 
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
		
		String lastname = ExcelsheetUtility.getDataFromExcel("Organization", 4, 2)+randomNumber;
		String orgname = ExcelsheetUtility.getDataFromExcel("Organization", 4, 1)+randomNumber;
		
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
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}

		WebDriverBrowserUtility.navigateApp(url, driver);
		WebDriverBrowserUtility.browserSetting(longTimeOut, driver);

		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();

		driver.findElement(By.xpath("//a[.='Contacts']")).click();
    	driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
    	driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname);
		driver.findElement(By.xpath("//img[@tabindex='']")).click();
		WebDriverBrowserUtility.switchToWindowBasedOnTitle(driver, "Organizations");
		
		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(orgname);
		driver.findElement(By.xpath("//input[@class='crmbutton small create']")).click();
		driver.findElement(By.xpath("//tr[2]/td/a")).click();

		WebDriverBrowserUtility.switchToWindowBasedOnTitle(driver, "Contacts");
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		Thread.sleep(2000);
		WebElement ele1 = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		WebDriverBrowserUtility.mouseHoverOnTheElement(ele1, driver);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		ExcelsheetUtility.saveExcelData(IconstantPathOfPropertyUtility.EXCELFILEPATH);
		ExcelsheetUtility.closeExcel();
		WebDriverBrowserUtility.quitBrowser(driver);

	}
}
