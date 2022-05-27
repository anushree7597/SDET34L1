package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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

public class FetchDataFromContactspropertyTest 
{
	public static void main(String[] args) throws IOException 
	{
		// convert the physical file into java readable object
		//FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");

		//create object for properties class
		//Properties property = new Properties();

		//load all keys 
		//property.load(fis);
		JavaJdkUtility jutil = new JavaJdkUtility();
		//fetch the data by using key
		FileDataUtility.openPropertyFile(IconstantPathOfPropertyUtility.PROPERTYFILEPATH);
		
		String url = FileDataUtility.getDataFromPropertyFile("url");
		String username = FileDataUtility.getDataFromPropertyFile("username");
		String password = FileDataUtility.getDataFromPropertyFile("password");
		String timeout =FileDataUtility.getDataFromPropertyFile("timeout");
		String browser = FileDataUtility.getDataFromPropertyFile("browser");

		long longTimeOut=jutil.stringToLong(timeout);
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
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		
		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		String firstName="sdet34l111";
		String lastName="15";
		
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		WebElement ActualFirstName = driver.findElement(By.xpath("//span[@id='dtlview_First Name']"));
		WebElement ActualLastName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']"));
		
		if(ActualFirstName.getText().equalsIgnoreCase("firstName")&&ActualLastName.getText().equalsIgnoreCase("lastName"))
		{
			System.out.println("contact created Successfully and pass");
		}
		
		}
		/*WebElement administartonIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));

		Actions act=new Actions(driver);
		act.moveToElement(administartonIcon).perform();
		driver.findElement(By.xpath("//a[@class='drop_down_usersettings']")).click();
		driver.quit();*/
		
		}
	

