package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
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

import com.sdet34l1.genericUtility.FileDataUtility;
import com.sdet34l1.genericUtility.IconstantPathOfPropertyUtility;
import com.sdet34l1.genericUtility.JavaJdkUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchOrganizationFetchData 
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

		FileInputStream fis1 = new FileInputStream("./src/test/resources/testData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);

		Sheet sheet = wb.getSheet("organization");
		Row row = sheet.getRow(4);
		Cell cell = row.getCell(1);
		String organizationName = cell.getStringCellValue()+randomNumber;
		wb.close();

		Sheet sheet1 = wb.getSheet("organization");
		Row row1= sheet.getRow(4);
		Cell cell1 = row.getCell(2);
		String ContactsLastName = cell.getStringCellValue()+randomNumber;
		wb.close();
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
			break;
		}

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();

		driver.findElement(By.xpath("//a[.='Organizations']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(organizationName);
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
		String orgname = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		System.out.println(orgname);

		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(ContactsLastName);

		String browser2 = driver.getWindowHandle();
		driver.findElement(By.xpath("//img[@title='Select']")).click();
		Set<String> windows = driver.getWindowHandles();
		String wind1 = null;
		for(String win:windows)
		{
			wind1=win;
		}

		driver.switchTo().window(wind1);
		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(organizationName);
		driver.findElement(By.xpath("//input[@type='button']")).click();
		driver.findElement(By.xpath("//tr[2]/td/a")).click();
		driver.switchTo().window(browser2);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		if(ContactsLastName.equals(lastname))
		{
			System.out.println("contact added");
			System.out.println("Tc pass");
		}
		else
		{
			System.out.println("tc is fail");
		}
		
		WebElement admi = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(admi).perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		driver.close();

	}
}

