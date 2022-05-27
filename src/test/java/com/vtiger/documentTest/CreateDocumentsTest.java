package com.vtiger.documentTest;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelsheetUtility;
import com.sdet34l1.genericUtility.FileDataUtility;
import com.sdet34l1.genericUtility.IconstantPathOfPropertyUtility;
import com.sdet34l1.genericUtility.JavaJdkUtility;
import com.sdet34l1.genericUtility.WebDriverBrowserUtility;
import com.vtiger.ObjectRepository.ClickOnNewDocumentsPage;
import com.vtiger.ObjectRepository.CreateTitleDocumentPage;
import com.vtiger.ObjectRepository.HomePage;
import com.vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentsTest extends BaseClass
{
	String titlename;
	String frametext;
	ClickOnNewDocumentsPage document;
	CreateTitleDocumentPage titledocument;

	@Test
	public  void createDocument()
	{

		titlename = ExcelsheetUtility.getDataFromExcel("Organization", 7, 1)+randomNumber;
		frametext =ExcelsheetUtility.getDataFromExcel("Organization", 10, 0);

		document=new ClickOnNewDocumentsPage(driver);
		titledocument=new CreateTitleDocumentPage(driver);

		homePage.clickOnDocument();
		document.clickDocumentbutton();
		titledocument.createDocumentTitle();
		titledocument.createTextTitle(titlename);
		WebDriverBrowserUtility.switchToFrame(driver, 0);
		titledocument.enterframetext(frametext);
		WebDriverBrowserUtility.switchBackToHome(driver);
		titledocument.boldTheText();
		titledocument.italicTheText();
		titledocument.documentFile("C:\\Users\\DELL\\Desktop\\vtiger");
		titledocument.saveTheDoc();

	}
}
