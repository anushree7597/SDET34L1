package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
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

public class FetchDataFromExcelOrganization 
{
	public static void main(String[] args) throws IOException 
	{
		JavaJdkUtility jutil = new JavaJdkUtility();
		FileDataUtility.openPropertyFile(IconstantPathOfPropertyUtility.PROPERTYFILEPATH);

		String url = FileDataUtility.getDataFromPropertyFile("url");
		String username = FileDataUtility.getDataFromPropertyFile("username");
		String password = FileDataUtility.getDataFromPropertyFile("password");
		String timeout =FileDataUtility.getDataFromPropertyFile("timeout");
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

		WebElement ActualOrganizationName = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']"));

		if(ActualOrganizationName.getText().equalsIgnoreCase(organizationName))
		{
			System.out.println("organization created successfully tc is passed");
		}

		WebElement administartonIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(administartonIcon).perform();
		driver.findElement(By.xpath("//a[@class='drop_down_usersettings']")).click();
		driver.quit();
	}
}
