package com.vtiger.contactsTest;

import java.io.IOException;

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
import com.vtiger.ObjectRepository.ClickOnCreateProductPage;
import com.vtiger.ObjectRepository.ClickOnNewContactPage;
import com.vtiger.ObjectRepository.ContactLastNamePage;
import com.vtiger.ObjectRepository.HomePage;
import com.vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class CreateContactTest extends BaseClass
{
	
	
	
	String lastname;
	ClickOnNewContactPage contactbutton;
	ContactLastNamePage enterlastcontactname;
	ContactLastNamePage save;

	@Test
	public  void createcontactTest()
	{
		String lastname = ExcelsheetUtility.getDataFromExcel("Organization", 4, 2)+randomNumber;
		contactbutton=new ClickOnNewContactPage(driver);
		enterlastcontactname=new ContactLastNamePage(driver);
		save=new ContactLastNamePage(driver);
		homePage.clickContactTab();
		contactbutton.clickNewcontactButton();
		enterlastcontactname.enterLastName(lastname);
		save.saveLastName();
	}
}