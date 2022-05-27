package com.rmgyantra.projectTest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.mysql.cj.jdbc.Driver;
import com.sdet34l1.genericUtility.DataBaseSQLUtility;
import com.sdet34l1.genericUtility.ExcelsheetUtility;
import com.sdet34l1.genericUtility.FileDataUtility;
import com.sdet34l1.genericUtility.IconstantPathOfPropertyUtility;
import com.sdet34l1.genericUtility.JavaJdkUtility;
import com.sdet34l1.genericUtility.WebDriverBrowserUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidationDatabaseprojectTest 
{
	public static void main(String[] args) throws SQLException, IOException
	{
		JavaJdkUtility jutil=new JavaJdkUtility();
		FileDataUtility.openPropertyFile(IconstantPathOfPropertyUtility.RMGYANTRAPROPERTYFILEPATH);
		ExcelsheetUtility.openExcel(IconstantPathOfPropertyUtility.EXCELFILEPATH);
		int randomNumber=jutil.getRandomNumber(1000);
		String projectName=ExcelsheetUtility.getDataFromExcel("projects", 1, 1)+"_"+randomNumber;
		System.out.println(projectName);
		DataBaseSQLUtility.openDBConnection(IconstantPathOfPropertyUtility.DATABASEURL+FileDataUtility.getDataFromPropertyFile("dbName"),
				FileDataUtility.getDataFromPropertyFile("dbUserName"),FileDataUtility.getDataFromPropertyFile("dbPassword"));
		DataBaseSQLUtility.setDataInDataBase("insert into project values('TY_PROJ_"+randomNumber+"', 'Sanjay', 9/05/2022','"+projectName+"', 'on Going', 13);");
		DataBaseSQLUtility.closeDBConnection();


		//		Connection connection=null;
		//		String projectName="SDET34L1-4";
		//		try
		//		{
		//			Driver d = new Driver();
		//			DriverManager.registerDriver(d);
		//			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		//			Statement statement = connection.createStatement();
		//			statement.executeUpdate("insert into project values('TY_PROJ_108','Sanath','28/04/2022','"+projectName+"', 'on Going',13);");
		//		}
		//		finally {
		//			connection.close();
		//		}
		//		try 
		//		{

		
			WebDriverManager.firefoxdriver().setup();
			WebDriver driver=new FirefoxDriver();


			WebDriverBrowserUtility.navigateApp("http://localhost:8084/", driver);
			WebDriverBrowserUtility.browserSetting(10, driver);

			driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("rmgyantra");
			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("rmgy@9999");
			driver.findElement(By.xpath("//button[.='Sign in']")).click();
			driver.findElement(By.xpath("//a[.='Projects']")).click();
			driver.findElement(By.xpath("//span[.='Create Project']")).click();

			List<WebElement> listOfProjects = driver.findElements(By.xpath("//table//tbody/tr/td[4]"));

			for(WebElement project:listOfProjects)
				if(project.getText().equalsIgnoreCase(projectName))
				{
					System.out.println("project name is visible in GUI");
					System.out.println("tc is pass");
					break;
				}

		
		WebDriverBrowserUtility.quitBrowser(driver);
	}

}




