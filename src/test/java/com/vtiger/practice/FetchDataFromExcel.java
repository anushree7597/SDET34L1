package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FetchDataFromExcel
{
	public static void main(String[] args) throws IOException 
	{
		
		Random ran = new Random();
		int randomNumber = ran.nextInt(1000);
		
		FileInputStream fis1 = new FileInputStream("./src/test/resources/excelTestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sheet = wb.getSheet("Organization");
		Row row = sheet.getRow(4);
		Cell cell = row.getCell(1);
		String organizationName = cell.getStringCellValue()+randomNumber;
		System.out.println(organizationName);
		wb.close();
	}
}
		  

			