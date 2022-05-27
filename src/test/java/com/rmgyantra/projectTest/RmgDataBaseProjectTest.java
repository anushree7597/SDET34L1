package com.rmgyantra.projectTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RmgDataBaseProjectTest 
{
	public static void main(String[] args) throws SQLException 
	{
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8084/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("rmgyantra");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		driver.findElement(By.xpath("//span[.='Create Project']")).click();

		String projectName="SDET34L1-2";
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(projectName);
		//	JavascriptExecutor js=(JavascriptExecutor)driver;
		//	js.executeScript("document.getelementsByname('teamSize'),value='7'");
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("mohan");
		WebElement projectStatusDropdown = driver.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));

		Select select = new Select(projectStatusDropdown);
		select.selectByVisibleText("Created");
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();

		Connection connection=null;
		try
		
		{
			Driver d = new Driver();
			DriverManager.registerDriver(d);
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select project_name from project;");

			while(result.next())
			{
				if(result.getString("project_name").equals(projectName))
				{
					System.out.println("project name is present in the database");
					System.out.println("TC is pass");
				}
			}
		}catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		finally 
		{
			try {
				connection.close();
				driver.quit();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}








}

