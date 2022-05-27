package com.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatafetchConnectionTest 
{
	public static void main(String[] args)  throws SQLException
	{
		String url=null, userName=null, password=null,timeout=null, browserName=null;

		Driver d = new Driver();
		DriverManager.registerDriver(d);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet34l1", "root","root");
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select *from vtiger");

		while(result.next())
		{
			url = result.getString("url");
			userName = result.getString("username");
			password = result.getString("password");
			timeout = result.getString("timeout");
			//browserName = result.getString("browsername");
		}
		long longTimeout=Long.parseLong(timeout);
		WebDriver driver=null;

		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		
		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		String firstName="sdet34l1";
		String lastName="l1";

		driver.findElement(By.name("firstname")).sendKeys(firstName);
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		WebElement ActualFirstName = driver.findElement(By.xpath("//span[@id='dtlview_Contacts Name']"));
		WebElement ActualLastName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']"));

		if(ActualLastName.getText().equalsIgnoreCase(firstName)&&ActualLastName.getText().equalsIgnoreCase(lastName))
		{
			System.out.println("contact created Successfully");
			System.out.println("TC pass");
		}

		WebElement administartonIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(administartonIcon).perform();
		driver.findElement(By.xpath("//a[@class='drop_down_usersettings']")).click();
		driver.quit();
		
		
	}
}
