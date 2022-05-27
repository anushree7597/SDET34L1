package com.vtiger.practice;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignWithProductTest
{
	public static void main(String[] args) throws IOException {

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

		String camName = ExcelsheetUtility.getDataFromExcel("Organization", 5, 1)+randomNumber;
		String prodname = ExcelsheetUtility.getDataFromExcel("Organization", 6, 1)+randomNumber;

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

		WebElement ele=driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']"));
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();

		driver.findElement(By.xpath("//a[@name='Campaigns']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Campaign...']")).click();
		driver.findElement(By.xpath("//input[@class='detailedViewTextBox']")).sendKeys(camName);
		driver.findElement(By.xpath("//img[@alt='Select']")).click();
		WebDriverBrowserUtility.switchToWindowBasedOnTitle(driver, "Products");

		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(prodname);
		driver.findElement(By.xpath("//input[@class='crmbutton small create']")).click();
		driver.findElement(By.xpath("//tr[2]/td/a")).click();

		WebDriverBrowserUtility.switchToWindowBasedOnTitle(driver, "Campaigns");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		WebElement ele1 = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		WebDriverBrowserUtility.mouseHoverOnTheElement(ele1, driver);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		ExcelsheetUtility.saveExcelData(IconstantPathOfPropertyUtility.EXCELFILEPATH);
		ExcelsheetUtility.closeExcel();
		WebDriverBrowserUtility.quitBrowser(driver);

	}
}
