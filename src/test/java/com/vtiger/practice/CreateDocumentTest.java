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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;
import com.sdet34l1.genericUtility.ExcelsheetUtility;
import com.sdet34l1.genericUtility.FileDataUtility;
import com.sdet34l1.genericUtility.IconstantPathOfPropertyUtility;
import com.sdet34l1.genericUtility.JavaJdkUtility;
import com.sdet34l1.genericUtility.WebDriverBrowserUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentTest 
{
	public static void main(String[] args) throws IOException, InterruptedException
	{

		JavaJdkUtility jutil = new JavaJdkUtility();
		FileDataUtility.openPropertyFile(IconstantPathOfPropertyUtility.PROPERTYFILEPATH);
		ExcelsheetUtility.openExcel(IconstantPathOfPropertyUtility.EXCELFILEPATH);
		String url = FileDataUtility.getDataFromPropertyFile("url");
		String username = FileDataUtility.getDataFromPropertyFile("username");
		String password =FileDataUtility.getDataFromPropertyFile("password");
		String timeout = FileDataUtility.getDataFromPropertyFile("timeout");
		String browser = FileDataUtility.getDataFromPropertyFile("browser");

		long longTimeOut=jutil.stringToLong(timeout);
		int randomNumber=jutil.getRandomNumber(1000);

		String titlename = ExcelsheetUtility.getDataFromExcel("Organization", 7, 1);
		
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

		driver.findElement(By.xpath("//a[.='Documents']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Document...']")).click();
		driver.findElement(By.xpath("//input[@name='notes_title']")).sendKeys(titlename);
		
		WebDriverBrowserUtility.switchToFrame(driver, 0);
		

        //driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Rich text editor, notecontent, press ALT 0 for help.']")));
		WebElement element = driver.findElement(By.xpath("//body[@class='cke_show_borders']"));
		Thread.sleep(1000);
		
		element.sendKeys("vtiger project two thousand nineteen",Keys.CONTROL+"a");
		WebDriverBrowserUtility.switchBackToHome(driver);
	//	driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[@id='cke_5']")).click();
		driver.findElement(By.xpath("//a[@id='cke_6']")).click();
  //    driver.findElement(By.xpath("//input[@type='file']")).sendKeys("\"C:\\Users\\DELL\\Desktop\\vtiger\"");
		driver.findElement(By.xpath("//b[.='File Information']/../../following-sibling::tr[4]//input[@title='Save [Alt+S]']")).click();
		WebElement docname = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		WebDriverBrowserUtility.mouseHoverOnTheElement(ele, driver);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		ExcelsheetUtility.saveExcelData(IconstantPathOfPropertyUtility.EXCELFILEPATH);
		ExcelsheetUtility.closeExcel();
		WebDriverBrowserUtility.quitBrowser(driver);

	}
}