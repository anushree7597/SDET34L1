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
import com.vtiger.ObjectRepository.ClickOnCreateOrgPage;
import com.vtiger.ObjectRepository.ClickOnNewContactPage;
import com.vtiger.ObjectRepository.ClickOnOrganizationButtonpage;
import com.vtiger.ObjectRepository.ContactLastNamePage;
import com.vtiger.ObjectRepository.CreateNewOrgNamePage;
import com.vtiger.ObjectRepository.HomePage;
import com.vtiger.ObjectRepository.LoginPage;
import com.vtiger.ObjectRepository.SearchOrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganizationTest extends BaseClass
{
	String lastname;
	String orgname;
	ClickOnOrganizationButtonpage clickonOrgbtn;
	CreateNewOrgNamePage createnameorg;
	CreateNewOrgNamePage saveorg;
	ClickOnNewContactPage newcontact;
	ContactLastNamePage contactlastname;
	ContactLastNamePage save;
	SearchOrganizationPage searchOrganization;

	public  void createorganizationcontact()
	{
		lastname = ExcelsheetUtility.getDataFromExcel("Organization", 4, 2)+randomNumber;
		orgname = ExcelsheetUtility.getDataFromExcel("Organization", 4, 1)+randomNumber;

		clickonOrgbtn=new ClickOnOrganizationButtonpage(driver);
		createnameorg=new CreateNewOrgNamePage(driver);
		saveorg=new CreateNewOrgNamePage(driver);

		newcontact=new ClickOnNewContactPage(driver);
		contactlastname=new ContactLastNamePage(driver);
		save=new ContactLastNamePage(driver);
		searchOrganization=new SearchOrganizationPage(driver);

		homePage.clickOrgTab();
		clickonOrgbtn.clickOnNewOrg();
		createnameorg.createnewOrgname(orgname);
		saveorg.saveOrganization(driver);
		homePage.clickContactTab();
		newcontact.clickNewcontactButton();
		contactlastname.enterLastName(lastname);
		searchOrganization.newOrganizationButton();
		searchOrganization.selectOrganization(orgname, driver);
		save.saveLastName();
	}
}
